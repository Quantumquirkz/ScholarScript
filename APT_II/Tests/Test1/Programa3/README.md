# Programa3 - Registro de Routers

Este programa Java permite registrar información de routers, incluyendo sus nombres, montos de venta y costos de compra, generando un reporte detallado tanto en consola como en archivo.

## Características

- Registro interactivo de routers
- Almacenamiento en ArrayList
- Cálculo automático de totales
- Generación de reportes formateados
- Guardado de datos en archivo de texto
- Cálculo de utilidad

## Estructura del Proyecto

```
Programa3/
├── JhuomarProg03.java    # Programa principal
└── README.md            # Este archivo
```

## Requisitos

- Java Development Kit (JDK) 8 o superior
- Compilador de Java (javac)

## Compilación

Para compilar el programa, ejecute el siguiente comando en la terminal:

```bash
javac Programa3/JhuomarProg03.java
```

## Ejecución

Para ejecutar el programa, use el siguiente comando:

```bash
java -cp . Programa3.JhuomarProg03
```

## Uso del Programa

1. Al iniciar, se mostrará un menú para ingresar los datos de los routers:
   - Nombre del Router (tipo String)
   - Monto de Venta (tipo double)
   - Costo de Compra (tipo double)

2. Para finalizar el ingreso de datos:
   - Deje el nombre en blanco (presione Enter sin escribir nada)

3. El programa mostrará:
   - Tabla con todos los registros
   - Totales de ventas y costos
   - Utilidad calculada
   - Confirmación de guardado en archivo

## Formato del Reporte

El reporte incluye:
- Encabezado con título
- Tabla con columnas: Nombre, Venta, Costo
- Línea separadora
- Totales de ventas y costos
- Utilidad calculada

## Manejo de Errores

El programa maneja los siguientes casos de error:
- Entrada de caracteres no numéricos en montos
- Errores de entrada/salida
- Problemas al guardar el archivo

## Notas

- Los montos se muestran con formato de moneda ($)
- El archivo de reporte se guarda en C:/PRUEBA/Jhuomar.txt
- Se puede ingresar cualquier cantidad de routers 