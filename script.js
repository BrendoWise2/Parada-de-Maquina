let startStop = null;
let intervalTime;

document.addEventListener('DOMContentLoaded', () => {
    const bntStart = document.querySelector('button');
    bntStart.addEventListener('click', startStopAction);
});

function startStopAction() {
    const operator = document.getElementById('operator').value;
    const equipamento = document.getElementById('equipamento').value;

    if (!operator && !equipamento) {
        alert('Preencha todos os campos!');
        return;
    }

    startStop = new Date();
    document.getElementById('startHour').textContent = startStop.toLocaleTimeString();
    document.getElementById('startDate').textContent = "-" + startStop.toLocaleDateString();

    intervalTime = setInterval(updateTime, 1000);

    fetch("http://localhost:8080/parada/iniciar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ operator, equipamento, start: startStop })
    }).catch(error => console.error("Erro ao registrar inicio:", error));
}

function stopAction() {
    if (!startStop) {
        alert('Nenhuma parada foi iniciada!')
        return;
    }
}