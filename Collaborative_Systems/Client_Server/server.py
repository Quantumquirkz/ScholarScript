import socket

# Crear el socket del servidor
mi_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
mi_socket.bind(('localhost', 8000))
mi_socket.listen(5)

# Definir el juego Knock Knock
knock_knock_jokes = [
    ("Toc toc", "¿Quién es?", "Lechuga", "Lechuga quién?", "Lechuga adentro que está lloviendo!"),
    ("Toc toc", "¿Quién es?", "Amapola", "Amapola quién?", "Amapola el que le toque!"),
    ("Toc toc", "¿Quién es?", "Boo", "Boo quién?", "No llores, es solo una broma!")
]

while True:
    # Aceptar la conexión del cliente
    conexion, addr = mi_socket.accept()
    print("Nueva conexión establecida desde:", addr)

    # Enviar la primera parte del chiste (Toc Toc)
    conexion.send("Toc toc".encode())
    
    # Recibir la respuesta del cliente (¿Quién es?)
    peticion = conexion.recv(1024).decode()
    print("Cliente dice:", peticion)

    if peticion.lower() == "¿quién es?":
        # Seleccionar un chiste al azar
        joke = knock_knock_jokes[0]

        # Enviar la siguiente parte del chiste
        conexion.send(joke[2].encode())  # Ejemplo: "Lechuga"
        
        # Recibir la respuesta del cliente (Lechuga quién?)
        peticion = conexion.recv(1024).decode()
        print("Cliente dice:", peticion)

        if peticion.lower().endswith("quién?"):
            # Enviar el remate del chiste
            conexion.send(joke[4].encode())  # Ejemplo: "Lechuga adentro que está lloviendo!"
    else:
        # Si el cliente no sigue el protocolo, cerrar la conexión
        conexion.send("Error en el protocolo. Adiós!".encode())

    # Cerrar la conexión
    conexion.close()
