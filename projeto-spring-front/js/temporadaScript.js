var urlBaseTemporada = "http://localhost:9193/temporada/"
var temporada = {
    "numero":"",
    "descricao":"",
    "serie": {
        "id":""
    }
}

function consultarTodosTemporada() {
    $.ajax({
        url: urlBaseTemporada + "todos",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        success: function (data) {
            $("#wait").hide();
            listarTemporada(data)
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

function listarSeriesTemporada() {
    $.ajax({
        url: urlBase + "todas",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        success: function (data) {
            $("#wait").hide();
            construiComboSerie(data);
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
function construiComboSerie(data) {
    $("#serieTemporada").empty();
    $("#serieTemporada").append($("<option>",{
        text: "------------------",
        value: ""
    }));
    $.each(data, function (indice, valor) {
        $("#serieTemporada").append($("<option>",{
            text: valor.nome,
            value: valor.id
        }));
    });
}
function listarTemporada(data) {
    $("tbody").empty();
    $.each(data, function (indice, valor) {
        var linha = "" +
            "<tr>\n " +
            "<td>" + valor.id + "</td>" +
            "<td>" + valor.numero + "</td>" +
            "<td>" + valor.descricao + "</td>" +
            "<td>" + valor.serie.nome + "</td>" +
            "</tr>";
        $("#listTemporada table tbody").append(linha);
    })
}

function cadastrarTemporada() {
    temporada.numero = $("#numeroTemporada").val();
    temporada.descricao = $("#descricaoTemporada").val();
    temporada.serie.id = $("#serieTemporada").val();

    $.ajax({
        url: urlBaseTemporada + "criar",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        data: JSON.stringify(temporada),
        timeout: 4000,
        success: function (data) {
            $("#wait").hide();
            $("input").val("");
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
function consultarTemporadaId() {
    $.ajax({
        url: urlBaseTemporada + "temporadaId/"+$("#idTemporada").val(),
        type: "GET",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        success: function (data) {
            $("#wait").hide();
            carregarTemporada(data);
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
function carregarTemporada(data) {
    if (data.id>0) {
        $("#idTemporada").val(data.id);
        $("#numeroTemporada").val(data.numero);
        $("#descricaoTemporada").val(data.descricao);
        $("#serieTemporada").val(data.serie.id)
        $("#btnRegistrarTemporada").hide();
        $("#btnAlterarTemporada").show();
        $("#btnTemporadaDelete").show();
    } else{
        zerarDados();
    }
}
function zerarDadosTemporada() {
    $("input").val("");
    $("select").val("");
    $("#btnRegistrarTemporada").show();
    $("#btnAlterarTemporada").hide();
    $("#btnTemporadaDelete").hide();
    $("#wait").hide();

}

function alterarTemporada() {
    temporada.numero = $("#numeroTemporada").val();
    temporada.descricao = $("#descricaoTemporada").val();
    temporada.serie.id = $("#serieTemporada").val();

    $.ajax({
        url: urlBaseTemporada + "alterar/"+$("#idTemporada").val(),
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        data: JSON.stringify(temporada),
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

function deletarTemporada() {

    $.ajax({
        url: urlBaseTemporada + "apagar/"+$("#idTemporada").val(),
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