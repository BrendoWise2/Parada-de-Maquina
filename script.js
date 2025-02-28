let startTime = null;
let intervalTimer;
const btnStart = document.querySelector('#startButton');
const btnStop = document.querySelector('#stopButton');

let operatorName, machineName; // Declare globally

document.addEventListener('DOMContentLoaded', () => {
    btnStart.addEventListener('click', startMachineStop);
    btnStop.addEventListener('click', endMachineStop);
});

function startMachineStop() {
    operatorName = document.getElementById('operator').value;
    machineName = document.getElementById('equipamento').value;

    if (!operatorName || !machineName) {
        alert('Por favor, preencha todos os campos!');
        return;
    }

    startTime = new Date();
    document.getElementById('startHour').textContent = startTime.toLocaleTimeString();
    document.getElementById('startDate').textContent = startTime.toLocaleDateString();

    intervalTimer = setInterval(updateElapsedTime, 1000);

    fetch("http://localhost:8080/parada/iniciar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ operator: operatorName, equipamento: machineName, start: startTime })
    }).catch(error => console.error("Erro ao registrar início:", error));

    btnStart.disabled = true;
    btnStop.disabled = false;  // Enable the stop button once started
}

function updateElapsedTime() {
    if (!startTime) return;

    const currentTime = new Date();
    const elapsedTimeInSeconds = Math.floor((currentTime - startTime) / 1000);

    const hours = String(Math.floor(elapsedTimeInSeconds / 3600)).padStart(2, "0");
    const minutes = String(Math.floor((elapsedTimeInSeconds % 3600) / 60)).padStart(2, "0");
    const seconds = String(elapsedTimeInSeconds % 60).padStart(2, "0");

    document.getElementById("stopTime").textContent = `${hours}:${minutes}:${seconds}`;
}

function endMachineStop() {

    if (!startTime) {
        alert('Nenhuma parada foi iniciada!');
        return;
    }

    const endTime = new Date();
    document.getElementById('endHour').textContent = endTime.toLocaleTimeString();
    document.getElementById('endDate').textContent = endTime.toLocaleDateString();

    clearInterval(intervalTimer);

    const elapsedTimeInSeconds = (endTime - startTime) / 1000;
    const hours = String(Math.floor(elapsedTimeInSeconds / 3600)).padStart(2, "0");
    const minutes = String(Math.floor((elapsedTimeInSeconds % 3600) / 60)).padStart(2, "0");
    const seconds = String(Math.floor(elapsedTimeInSeconds % 60)).padStart(2, "0");

    document.getElementById("stopTime").textContent = `${hours}:${minutes}:${seconds}`;

    // Format the endTime to a string in a standard format (e.g., 'yyyy-mm-ddTHH:mm:ss')
    const formattedEndTime = endTime.toISOString();
    btnStart.disabled = false;
    btnStop.disabled = true;

    fetch("http://localhost:8080/parada/stop", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            operator: operatorName,
            equipamento: machineName,
            end: formattedEndTime // Use formattedEndTime here
        })
    }).catch(error => console.error("Erro ao registrar fim:", error));

    startTime = null;


}
