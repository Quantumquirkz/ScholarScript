// Elementos del DOM
const velocityInput = document.getElementById('velocity');
const angleInput = document.getElementById('angle');
const calculateBtn = document.getElementById('calculateBtn');
const resultsDiv = document.getElementById('results');
const errorDiv = document.getElementById('error');
const totalTimeElement = document.getElementById('totalTime');
const maxHeightElement = document.getElementById('maxHeight');
const maxDistanceElement = document.getElementById('maxDistance');
const timeToMaxHeightElement = document.getElementById('timeToMaxHeight');
const trajectoryTable = document.getElementById('trajectoryTable');
const downloadTxtBtn = document.getElementById('downloadTxtBtn');
const downloadPdfBtn = document.getElementById('downloadPdfBtn');

// Variables globales
let currentResults = null;
const BACKEND_URL = 'http://localhost:4567';
let trajectoryChart = null;

// Función para inicializar la gráfica
function initChart() {
    const ctx = document.getElementById('trajectoryChart').getContext('2d');
    trajectoryChart = new Chart(ctx, {
        type: 'scatter',
        data: {
            datasets: [{
                label: 'Trayectoria del Proyectil',
                data: [],
                backgroundColor: 'rgba(59, 130, 246, 0.5)',
                borderColor: 'rgba(59, 130, 246, 1)',
                borderWidth: 1,
                pointRadius: 3,
                pointHoverRadius: 5
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                x: {
                    type: 'linear',
                    position: 'bottom',
                    title: {
                        display: true,
                        text: 'Distancia Horizontal (m)'
                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Altura (m)'
                    }
                }
            },
            plugins: {
                title: {
                    display: true,
                    text: 'Trayectoria del Proyectil'
                },
                tooltip: {
                    callbacks: {
                        label: function(context) {
                            const point = context.raw;
                            return `Tiempo: ${point.t}s\nAltura: ${point.y}m\nDistancia: ${point.x}m`;
                        }
                    }
                }
            }
        }
    });
}

// Función para actualizar la gráfica
function updateChart() {
    if (!currentResults || !trajectoryChart) return;

    const data = currentResults.trajectory.map(point => ({
        x: point.horizontalPosition,
        y: point.height,
        t: point.time
    }));

    trajectoryChart.data.datasets[0].data = data;
    trajectoryChart.update();
}

// Función para calcular el movimiento del proyectil
async function calculateProjectileMotion() {
    const v0 = parseFloat(velocityInput.value);
    const angle = parseFloat(angleInput.value);

    // Validación de entrada
    if (!v0 || !angle) {
        showError('Por favor, ingresa todos los valores requeridos');
        return;
    }

    if (v0 <= 0 || angle <= 0 || angle >= 90) {
        showError('Valores inválidos. La velocidad debe ser positiva y el ángulo entre 0 y 90 grados');
        return;
    }

    try {
        showError('Conectando con el servidor...');
        const response = await fetch(`${BACKEND_URL}/api/calculate`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                initialVelocity: v0,
                launchAngle: angle
            })
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.error || 'Error al calcular el movimiento del proyectil');
        }

        const data = await response.json();
        currentResults = data;
        displayResults();
        updateChart();
    } catch (err) {
        if (err.message === 'Failed to fetch') {
            showError('No se pudo conectar con el servidor. Asegúrate de que el backend esté ejecutándose en http://localhost:4567');
        } else {
            showError(err.message);
        }
    }
}

// Función para mostrar los resultados
function displayResults() {
    if (!currentResults) return;

    // Actualizar valores principales
    totalTimeElement.textContent = `${currentResults.totalFlightTime.toFixed(2)} s`;
    maxHeightElement.textContent = `${currentResults.maxHeight.toFixed(2)} m`;
    maxDistanceElement.textContent = `${currentResults.maxDistance.toFixed(2)} m`;
    timeToMaxHeightElement.textContent = `${currentResults.timeToMaxHeight.toFixed(2)} s`;

    // Actualizar tabla de trayectoria
    trajectoryTable.innerHTML = '';
    currentResults.trajectory.forEach(point => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${point.time}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${point.height}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${point.horizontalPosition}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${point.horizontalVelocity}</td>
            <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">${point.verticalVelocity}</td>
        `;
        trajectoryTable.appendChild(row);
    });

    // Mostrar sección de resultados
    resultsDiv.classList.remove('hidden');
    hideError();
}

// Función para mostrar errores
function showError(message) {
    errorDiv.querySelector('span').textContent = message;
    errorDiv.classList.remove('hidden');
    resultsDiv.classList.add('hidden');
}

// Función para ocultar errores
function hideError() {
    errorDiv.classList.add('hidden');
}

// Función para descargar resultados en formato TXT
async function downloadTXT() {
    try {
        // Primero generar el archivo
        const response = await fetch('http://localhost:4567/api/download/txt', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Error al generar el archivo TXT');
        }

        // Luego descargar el archivo
        window.location.href = 'http://localhost:4567/results.txt';
    } catch (error) {
        showError('Error al descargar el archivo TXT: ' + error.message);
    }
}

// Función para descargar resultados en formato PDF
async function downloadPDF() {
    try {
        // Primero generar el archivo
        const response = await fetch('http://localhost:4567/api/download/pdf', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Error al generar el archivo PDF');
        }

        // Luego descargar el archivo
        window.location.href = 'http://localhost:4567/results.pdf';
    } catch (error) {
        showError('Error al descargar el archivo PDF: ' + error.message);
    }
}

// Inicializar la gráfica cuando se carga la página
document.addEventListener('DOMContentLoaded', initChart);

// Event Listeners
calculateBtn.addEventListener('click', calculateProjectileMotion);
downloadTxtBtn.addEventListener('click', downloadTXT);
downloadPdfBtn.addEventListener('click', downloadPDF);

// Validación de entrada en tiempo real
velocityInput.addEventListener('input', () => {
    const value = parseFloat(velocityInput.value);
    if (value <= 0) {
        showError('La velocidad debe ser positiva');
    } else {
        hideError();
    }
});

angleInput.addEventListener('input', () => {
    const value = parseFloat(angleInput.value);
    if (value <= 0 || value >= 90) {
        showError('El ángulo debe estar entre 0 y 90 grados');
    } else {
        hideError();
    }
}); 