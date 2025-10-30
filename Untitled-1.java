<script>
    // 1. Obtener los elementos del HTML
    const form = document.getElementById('formulario-contacto');
    const mensajeExito = document.getElementById('mensaje-exito');

    // 2. Escuchar el evento de envío del formulario
    form.addEventListener('submit', async function (e) {
        e.preventDefault(); // <--- ESTO ES LO CRUCIAL: Detiene la recarga/redirección

        const data = new FormData(form);
        const button = form.querySelector('button');

        button.textContent = 'Enviando...'; // Cambia el texto del botón
        button.disabled = true; // Deshabilita el botón

        try {
            // 3. Envía los datos a Formspree usando Ajax (fetch)
            const respuesta = await fetch(form.action, {
                method: 'POST',
                body: data,
                headers: {
                    'Accept': 'application/json' // Indicamos que esperamos una respuesta JSON
                }
            });

            // 4. Comprueba si fue exitoso
            if (respuesta.ok) {
                form.style.display = 'none'; // Oculta todo el formulario
                mensajeExito.style.display = 'block'; // Muestra el mensaje de éxito
            } else {
                alert('Hubo un error al enviar. Por favor, inténtalo de nuevo.');
                button.textContent = 'Enviar';
                button.disabled = false;
            }
        } catch (error) {
            alert('Error de conexión. Verifica tu internet.');
            button.textContent = 'Enviar';
            button.disabled = false;
        }
    });
</script>