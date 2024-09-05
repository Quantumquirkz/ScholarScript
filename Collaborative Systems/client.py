import socket

# Crear el socket del cliente
mi_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
mi_socket.connect(('localhost', 8000))

# Recibir el primer mensaje del servidor (Toc toc)
mensaje = mi_socket.recv(1024).decode()
print("Servidor dice:", mensaje)

# Enviar la respuesta (¿Quién es?)
mi_socket.send("¿Quién es?".encode())

# Recibir el segundo mensaje del servidor
mensaje = mi_socket.recv(1024).decode()
print("Servidor dice:", mensaje)

# Enviar la respuesta correspondiente (Ejemplo: Lechuga quién?)
mi_socket.send(f"{mensaje} quién?".encode())

# Recibir el remate del chiste
mensaje = mi_socket.recv(1024).decode()
print("Servidor dice:", mensaje)

# Cerrar la conexión
mi_socket.close()
