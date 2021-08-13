var urlBaseEpisodio = "http://localhost:9193/episodio/"
var episodio = {
    "nome":"",
    "numero":"",
    "temporada": {
        "id":""
    }
}

function consultarTodosEpisodio() {
    $.ajax({
        url: urlBaseEpisodio + "todos",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        success: function (data) {
            $("#wait").hide();
            listarEpisodio(data)
        },
        beforeSend: function () {
            $("#wait").show();
        },
        error: function () {
            alert("error");
            $("#wait").hide();
        }
    })
}

function listarTemporadasEpisodio() {
    $.ajax({
        url: urlBaseEpisodio + "temporadaSerie",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        success: function (data) {
            $("#wait").hide();
            construiComboTemporada(data);
        },
        beforeSend: function () {
            $("#wait").show();
        },
        error: function () {
            alert("error");
            $("#wait").hide();
        }
    })
}
function construiComboTemporada(data) {
    $("#temporadaEpisodio").empty();
    $("#temporadaEpisodio").append($("<option>",{
        text: "------------------",
        value: ""
    }));
    var group ="";
    $.each(data, function (indice, valor) {
        group += "<optgroup label=\""+valor.nome+"\">";
        var option="";
        $.each(valor.temporadas, function (indiceTemp, valorTemp) {
            option+="<option value=\""+valorTemp.id+"\">"+valorTemp.numero+"</option>";

        });
        group+=option;
        group +="</optgroup>"

    });
    $("#temporadaEpisodio").append(group);
}
function listarEpisodio(data) {
    $("tbody").empty();
    $.each(data, function (indice, valor) {
        var linha = "" +
            "<tr>\n " +
            "<td>" + valor.id + "</td>" +
            "<td>" + valor.nome + "</td>" +
            "<td>" + valor.numero + "</td>" +
            "<td>" + valor.temporada.numero + "</td>" +
            "</tr>";
        $("#listEpisodio table tbody").append(linha);
    })
}

function cadastrarEpisodio() {
    episodio.nome = $("#nomeEpisodio").val();
    episodio.numero = $("#numeroEpisodio").val();
    episodio.temporada.id = $("#temporadaEpisodio").val();

    $.ajax({
        url: urlBaseEpisodio + "criar",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        data: JSON.stringify(episodio),
        timeout: 4000,
        success: function (data) {
            $("#wait").hide();
            $("input").val("");
            $("select").val("");
            $("#menssagem").show();
            $("#menssagem > div").text("Dados cadastrados com sucesso");
        },
        beforeSend: function () {
            $("#wait").show();
        },
        error: function () {
            alert("error");
            $("#wait").hide();
        }
    })
}
function consultarEpisodioId() {
    $.ajax({
        url: urlBaseEpisodio + "episodioId/"+$("#idEpisodio").val(),
        type: "GET",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        success: function (data) {
            $("#wait").hide();
            carregarEpisodio(data);
        },
        beforeSend: function () {
            $("#wait").show();
        },
        error: function () {
            alert("error");
            $("#wait").hide();
        }
    })
}
function carregarEpisodio(data) {
    if (data.id>0) {
        $("#idEpisodio").val(data.id);
        $("#numeroEpisodio").val(data.numero);
        $("#nomeEpisodio").val(data.nome);
        $("#temporadaEpisodio").val(data.temporada.id)
        $("#btnRegistrarEpisodio").hide();
        $("#btnAlterarEpisodio").show();
        $("#btnEpisodioDelete").show();
    } else{
        zerarDados();
    }
}
function zerarDadosEpisodio() {
    $("input").val("");
    $("select").val("");
    $("#btnRegistrarEpisodio").show();
    $("#btnAlterarEpisodio").hide();
    $("#btnEpisodioDelete").hide();
    $("#wait").hide();

}

function alterarEpisodio() {
    episodio.nome = $("#nomeEpisodio").val();
    episodio.numero = $("#numeroEpisodio").val();
    episodio.temporada.id = $("#temporadaEpisodio").val();

    $.ajax({
        url: urlBaseEpisodio + "alterar/"+$("#idEpisodio").val(),
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        data: JSON.stringify(episodio),
        timeout: 4000,
        success: function (data) {
            $("#wait").hide();
            $("input").val("");
            $("select").val("");
            $("#menssagem").show();
            $("#menssagem > div").text("Dados alterados com sucesso");
        },
        beforeSend: function () {
            $("#wait").show();
        },
        error: function () {
            alert("error");
            $("#wait").hide();
        }
    })
}

function deletarEpisodio() {

    $.ajax({
        url: urlBaseEpisodio + "apagar/"+$("#idEpisodio").val(),
        type: "DELETE",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        timeout: 4000,
        success: function (data) {
            $("#wait").hide();
            $("input").val("");
            $("#menssagem").show();
            $("#menssagem > div").text("Dados APAGADOS com sucesso");
        },
        beforeSend: function () {
            $("#wait").show();
        },
        error: function () {
            alert("error");
            $("#wait").hide();
        }
    })
}