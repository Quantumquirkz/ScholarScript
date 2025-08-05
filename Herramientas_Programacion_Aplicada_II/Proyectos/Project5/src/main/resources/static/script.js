document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('caidaLibreForm');
    const resultados = document.getElementById('resultados');
    const API_URL = 'http://localhost:8080/calcular';

    // Función para mostrar mensajes de error
    function mostrarError(mensaje) {
        alert(mensaje);
        resultados.classList.add('d-none');
    }

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const altura = parseFloat(document.getElementById('altura').value);
        const planeta = document.getElementById('planeta').value;

        if (isNaN(altura) || altura <= 0) {
            mostrarError('Por favor, ingrese una altura válida mayor que 0');
            return;
        }

        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify({
                    altura: altura,
                    planeta: planeta
                })
            });

            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.message || `Error del servidor: ${response.status}`);
            }
            
            // Mostrar resultados
            document.getElementById('resultadoAltura').textContent = data.altura.toFixed(2);
            document.getElementById('resultadoGravedad').textContent = data.gravedad.toFixed(2);
            document.getElementById('resultadoTiempo').textContent = data.tiempo.toFixed(2);
            
            resultados.classList.remove('d-none');
        } catch (error) {
            console.error('Error:', error);
            if (error.message.includes('Failed to fetch')) {
                mostrarError('No se pudo conectar con el servidor. Por favor, asegúrese de que el servidor esté en ejecución en http://localhost:8080');
            } else {
                mostrarError(error.message || 'Error al realizar el cálculo');
            }
        }
    });
}); 