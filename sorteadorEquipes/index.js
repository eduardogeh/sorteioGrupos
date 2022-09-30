function textoGrupo(array) {

    return array.alunos.join(", ");
}

function addToTable(data) {
    const table = document.getElementById("listaGrupos");
    table.innerHTML = "";
    for(var i = 0; i < data.length; i++) {
        table.innerHTML += "<button type=\"button\" class=\"list-group-item list-group-item-action\"> Grupo "+ (i+1) + ": " + textoGrupo(data[i])+"</button>";
    }
    document.getElementById("divResultado").style.display = "block";
}

function formarGrupos(){
    const nomes = document.getElementById("nomes");
    const formacao = document.getElementById("formacao");
    const nomesArray = nomes.value.split(",");

    const data = {};
    data.alunos = nomesArray;
    data.qtdPorGrupo = formacao.value;

    fetch("http://localhost:8080/sortear-nomes", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data),
    })
        .then((response) => response.json())
        .then((data) => {
            addToTable(data);
        })
        .catch((error) => {
            console.error("Error:", error);
        });

}