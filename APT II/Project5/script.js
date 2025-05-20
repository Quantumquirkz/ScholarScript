document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('caidaLibreForm');
    const resultados = document.getElementById('resultados');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const altura = parseFloat(document.getElementById('altura').value);
        const planeta = document.getElementById('planeta').value;

        try {
            const response = await fetch('http://localhost:8080/calcular', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    altura: altura,
                    planeta: planeta
                })
            });

            if (!response.ok) {
                throw new Error('Error en el cálculo');
            }

            const data = await response.json();
            
            // Mostrar resultados
            document.getElementById('resultadoAltura').textContent = data.altura.toFixed(2);
            document.getElementById('resultadoGravedad').textContent = data.gravedad.toFixed(2);
            document.getElementById('resultadoTiempo').textContent = data.tiempo.toFixed(2);
            
            resultados.classList.remove('d-none');
        } catch (error) {
            alert('Error al realizar el cálculo: ' + error.message);
        }
    });
}); 