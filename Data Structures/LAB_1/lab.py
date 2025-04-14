def taller_de_frases():
    print("🛠️ Bienvenido al Taller de Reparación de Frases 🛠️")
    print("Aquí puedes modificar tus frases como todo un cirujano del lenguaje.\n")

    while True:
        frase_original = input("📝 Ingresa una frase completa: ").strip()

        if not frase_original:
            print("⚠️ La frase no puede estar vacía. Intenta de nuevo.\n")
            continue

        palabra_clave = input("🔍 Ingresa la palabra que deseas reemplazar: ").strip()

        if palabra_clave in frase_original:
            posicion = frase_original.index(palabra_clave)
            print(f"📍 Palabra encontrada en la posición {posicion}.")

            nueva_palabra = input("✏️ Ingresa la nueva palabra para reemplazarla: ").strip()

            # Extraer subcadena
            subcadena = frase_original[posicion:posicion+len(palabra_clave)]
            print(f"🧬 Subcadena extraída: '{subcadena}'")

            # Eliminar y reemplazar en la misma posición
            frase_modificada = frase_original[:posicion] + nueva_palabra + frase_original[posicion+len(palabra_clave):]
            print(f"🧩 Frase tras el reemplazo de la palabra: '{frase_modificada}'")

            # Reemplazo de caracteres
            char_original = input("🔡 Ingresa un carácter que deseas reemplazar: ")
            char_nuevo = input(f"🔁 Ingresa el nuevo carácter para reemplazar '{char_original}': ")

            frase_modificada = frase_modificada.replace(char_original, char_nuevo)

            # Mostrar resultados
            print("\n✅ RESULTADO FINAL:")
            print(f"💬 Frase modificada: {frase_modificada}")
            print(f"📏 Longitud de la nueva frase: {len(frase_modificada)} caracteres\n")

        else:
            print("❌ La palabra clave no se encontró en la frase. No se realizaron cambios.\n")

        # Preguntar si desea continuar
        continuar = input("🔁 ¿Deseas modificar otra frase? (sí/no): ").strip().lower()
        if continuar != "sí":
            print("\n👋 Gracias por visitar el Taller de Reparación de Frases. ¡Hasta la próxima!")
            break  
        
        

# Ejecutar el programa
taller_de_frases()
