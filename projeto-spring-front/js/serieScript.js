$(document).ready(function () {
    $("#btnMensagem").click(function () {
        $("#menssagem > div").text("");
        $("#menssagem").hide();
        zerarDadosSerie();
        zerarDadosTemporada();
        zerarDadosEpisodio();
    })
    $("nav ul li").click(function () {
        zerarDadosSerie();
        zerarDadosTemporada();
        zerarDadosEpisodio();
        $("main section").hide();
        var tag = $(this).data("target");
        $(tag).show();
        if ($(this).data("target")=="#cadTemporada"){
            listarSeriesTemporada();
        }
        if ($(this).data("target")=="#cadEpisodio"){
            listarTemporadasEpisodio();
        }
        if ($(this).data("tipo") == "listar") {
            if ($(this).data("objeto")=="serie") {
                consultarTodos();
            } else {
                if ($(this).data("objeto")=="temporada"){
                    consultarTodosTemporada();
                } else
                if ($(this).data("objeto")=="episodio"){
                    consultarTodosEpisodio();
                }

            }
        }
    });

    $("#btnRegistrar").click(function () {
        cadastrarSerie();
    });
    $("#btnRegistrarTemporada").click(function () {
        cadastrarTemporada();
    });
    $("#btnRegistrarEpisodio").click(function () {
        cadastrarEpisodio();
    });
    $("#btnAlterar").click(function () {
        alterarSerie();
    });
    $("#btnAlterarTemporada").click(function () {
        alterarTemporada();
    });
    $("#btnAlterarEpisodio").click(function () {
        alterarEpisodio();
    });
    $("#btnSerieDelete").click(function () {
        deletarSerie();
    });
    $("#btnEpisodioDelete").click(function () {
        deletarEpisodio();
    });
    $("#btnTemporadaDelete").click(function () {
        deletarTemporada();
    });

    $("#searchId").click(function () {
        consultarSerieId();
    })
    $("#searchTempordaId").click(function () {
        consultarTemporadaId();
    });
    $("#searchEpisodioId").click(function () {
        consultarEpisodioId();
    })
});
var urlBase = "http://localhost:9193/serie/"
var serie = {
    "nome": "",
    "anoCriacao": "",
    "descricao": ""
}

function consultarTodos() {
    $.ajax({
        url: urlBase + "todas",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        success: function (data) {
            $("#wait").hide();
            listar(data)
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

function listar(data) {
    $("tbody").empty();
    $.each(data, function (indice, valor) {
        var linha = "" +
            "<tr>\n " +
            "<td>" + valor.id + "</td>" +
            "<td>" + valor.nome + "</td>" +
            "<td>" + valor.anoCriacao + "</td>" +
            "<td>" + valor.descricao + "</td>" +
            "</tr>";
        $("tbody").append(linha);
    })
}

function cadastrarSerie() {
    serie.nome = $("#nomeSerie").val();
    serie.anoCriacao = $("#anoSerie").val();
    serie.descricao = $("#descricaoSerie").val();

    $.ajax({
        url: urlBase + "criar",
        type: "POST",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        data: JSON.stringify(serie),
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
function consultarSerieId() {
    $.ajax({
        url: urlBase + "serieId/"+$("#idSerie").val(),
        type: "GET",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        success: function (data) {
            $("#wait").hide();
            carregarSerie(data);
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
function carregarSerie(data) {
    if (data.id>0) {
        $("#idSerie").val(data.id);
        $("#nomeSerie").val(data.nome);
        $("#anoSerie").val(data.anoCriacao);
        $("#descricaoSerie").val(data.descricao);
        $("#btnRegistrar").hide();
        $("#btnAlterar").show();
        $("#btnSerieDelete").show();
    } else{
        zerarDados();
    }
}
function zerarDadosSerie() {
    $("input").val("");
    $("#anoSerie").val(2000);
    $("#btnRegistrar").show();
    $("#btnAlterar").hide();
    $("#btnSerieDelete").hide();

}

function alterarSerie() {
    serie.nome = $("#nomeSerie").val();
    serie.anoCriacao = $("#anoSerie").val();
    serie.descricao = $("#descricaoSerie").val();

    $.ajax({
        url: urlBase + "alterar/"+$("#idSerie").val(),
        type: "PUT",
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        cache: false,
        data: JSON.stringify(serie),
        timeout: 4000,
        success: function (data) {
            $("#wait").hide();
            $("input").val("");
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

function deletarSerie() {

    $.ajax({
        url: urlBase + "apagar/"+$("#idSerie").val(),
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