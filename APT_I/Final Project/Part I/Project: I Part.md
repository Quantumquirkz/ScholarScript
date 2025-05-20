# Descripción Detallada del Juego y Lore

## Lore del Juego:
Bienvenido a **"Fábrica Délfica de Diversión"**, un centro de entretenimiento en ruinas que alguna vez fue famoso por sus avanzados animatrónicos y espectáculos. Sin embargo, un misterioso accidente ocurrió hace años, forzando el cierre del lugar. La empresa, desesperada por reabrir y limpiar su reputación, contrató a un guardia nocturno para vigilar las instalaciones mientras se realizan las reparaciones finales.

El problema es que los animatrónicos parecen haber desarrollado una "personalidad" peligrosa después del accidente. Algo oscuro los controla, y no están interesados en quedarse quietos. Tú, como el guardia nocturno, debes sobrevivir 5 noches mientras recopilas pruebas de las anomalías para enviar a las autoridades antes de que la empresa las destruya. ¿Podrás sobrevivir al pasado mecánico y oscuro de esta fábrica?

## Descripción General del Mapa
El mapa refleja el caos y deterioro de la fábrica. Está diseñado para aumentar la tensión al obligar al jugador a moverse y gestionar recursos. Incluye áreas clave con funcionalidades específicas y rutas interconectadas que los animatrónicos usarán estratégicamente.

### Áreas Principales:

#### Oficina del Jugador (Nodo 0):
La base de operaciones del jugador. Equipado con:
- **Puertas de Seguridad**: Una a cada lado para bloquear el acceso de los animatrónicos.
- **Panel de Cámaras**: Permite monitorear nodos clave, pero consume energía.
- **Control de Luces**: Ayuda a verificar pasillos cercanos.

**Limitación**: Todo en la oficina consume energía, lo que obliga al jugador a usarla con cuidado.

#### Sala de Generador (Nodo 8):
Aquí se encuentra el sistema eléctrico principal. Si se agota la energía, el jugador debe llegar físicamente para reiniciarla.
- **Interacción**: Reiniciar el generador requiere 10 segundos sin interrupción.
- **Riesgo**: Es una de las habitaciones más peligrosas, ya que no tiene mecanismos defensivos.

#### Sala de Seguridad (Nodo 9):
Un cuarto secundario donde las cámaras tienen menos retraso y muestran más detalles.
- **Ventaja**: Reduce la probabilidad de ser sorprendido por animatrónicos al monitorear rutas.
- **Desventaja**: No tiene puertas ni luces defensivas.

#### Pasillos Principales (Nodos 1 y 2):
Los corredores izquierdo y derecho que conectan la Oficina con las habitaciones iniciales de los animatrónicos.
- **Riesgo**: Son las rutas más directas y frecuentadas.

#### Habitaciones Iniciales (Nodos 3 y 4):
Donde los animatrónicos comienzan su actividad cada noche.
- **Nodos Activos**: Los animatrónicos se activan aleatoriamente en cada habitación.

#### Zonas de Distracción (Nodos 5 y 6):
Habitaciones con elementos ruidosos o en mal estado. Los animatrónicos pueden usarlas para generar ruido y forzar al jugador a gastar energía o abandonar su posición.

#### Pasadizo Oculto (Nodo 7):
Una ruta menos visible que conecta varias áreas del mapa.
- **Usos**: Los animatrónicos lo usan para rodear al jugador.
- **Limitación**: No es visible en las cámaras normales.

#### Zona de Mantenimiento (Nodos 10 y 11):
Antiguas salas de reparación que ahora son usadas por los animatrónicos para reiniciar su patrón si son bloqueados repetidamente.

## Interacciones Clave y Dinámicas del Mapa

### Oficina como Refugio Central:
El jugador tiene la ventaja de controlar puertas, luces y cámaras desde aquí, pero debe mantener un equilibrio entre vigilancia y consumo de energía.
- **Ejemplo**: Cerrar las puertas demasiado tiempo puede dejarte vulnerable si la energía se agota.

### Estrategia del Generador:
Si la energía se agota, el jugador deberá salir de la Oficina y moverse al Nodo 8, exponiéndose al ataque.  
Para agregar tensión, los animatrónicos pueden ser atraídos por el sonido del generador cuando está apagado.

### Salas de Seguridad y Distracción:
- **Sala de Seguridad (Nodo 9)**: Atrae al jugador con sus cámaras mejoradas, pero su aislamiento la convierte en un riesgo.
- **Nodos de Distracción (5 y 6)**: Animatrónicos con IA diseñada para ruido pueden desviar al jugador hacia estas habitaciones.

### Pasillos y Rutas Alternativas:
Los pasillos principales (1 y 2) son puntos críticos, pero los pasadizos alternativos (7, 10, 11) aumentan la imprevisibilidad al abrir rutas secundarias para los animatrónicos.

## Lógica del Mapa y Uso por Animatrónicos

### Animatrónicos Agresivos:
Prefieren los pasillos principales y habitaciones cercanas a la Oficina (Nodos 1, 2, 5, 6).

### Animatrónicos Sigilosos:
Tienden a usar los pasadizos ocultos (Nodo 7) o rutas alternativas (Nodos 10 y 11).

### Distractores:
Permanecen más tiempo en las Zonas de Distracción (5 y 6), generando ruido o desactivando cámaras cercanas para forzar al jugador a gastar recursos.
# Detalles del Mapa de Nodos

## Nodo 0: Oficina del Jugador
**Propósito:**
La base central del jugador. Aquí se encuentran los controles principales de defensa: puertas, luces y cámaras. Es el punto más seguro del mapa, pero limitado por la energía.

**Interacciones:**
- **Puertas**: Se pueden cerrar para bloquear la entrada de animatrónicos desde Nodos 1 y 2. Consume energía mientras están cerradas.
- **Luces**: Iluminan los pasillos conectados (Nodos 1 y 2). Ayudan a verificar si un animatrónico está cerca sin usar cámaras.
- **Cámaras**: El jugador puede acceder al sistema de cámaras desde aquí. Tiene un retraso de actualización al monitorear nodos distantes.

**Conexiones:**
- Nodo 1 (Pasillo Izquierdo)
- Nodo 2 (Pasillo Derecho)

## Nodo 1: Pasillo Izquierdo
**Propósito:**
Uno de los dos pasillos principales que conectan la Oficina con el resto del mapa. Es una ruta directa hacia la Oficina para los animatrónicos.

**Interacciones:**
- **Animatrónicos**: Frecuentan este pasillo en rutas directas hacia el Nodo 0.
- **Jugador**: Puede usar luces o cámaras para monitorear la actividad en este nodo.

**Conexiones:**
- Nodo 0 (Oficina)
- Nodo 3 (Habitación Inicial 1)
- Nodo 5 (Zona de Distracción Izquierda)

## Nodo 2: Pasillo Derecho
**Propósito:**
El segundo pasillo principal hacia la Oficina. Similar al Nodo 1, es una ruta directa pero con diferentes intersecciones.

**Interacciones:**
- **Animatrónicos**: Usan este pasillo para acercarse a la Oficina.
- **Jugador**: Puede bloquearlo con la puerta derecha o usar luces para detectar presencia.

**Conexiones:**
- Nodo 0 (Oficina)
- Nodo 4 (Habitación Inicial 2)
- Nodo 6 (Zona de Distracción Derecha)

## Nodo 3: Habitación Inicial 1
**Propósito:**
El punto de activación de un animatrónico al inicio de la noche.

**Interacciones:**
- **Animatrónicos**: Uno o más animatrónicos comienzan aquí y deciden su ruta inicial hacia la Oficina.

**Conexiones:**
- Nodo 1 (Pasillo Izquierdo)
- Nodo 7 (Pasadizo Oculto)

## Nodo 4: Habitación Inicial 2
**Propósito:**
El punto de activación de otro animatrónico. Sirve como espejo del Nodo 3 en el lado derecho del mapa.

**Interacciones:**
- **Animatrónicos**: Pueden permanecer aquí en un estado de espera o dirigirse hacia rutas cercanas.

**Conexiones:**
- Nodo 2 (Pasillo Derecho)
- Nodo 7 (Pasadizo Oculto)

## Nodo 5: Zona de Distracción Izquierda
**Propósito:**
Una habitación diseñada para generar tensión. Los animatrónicos pueden usarlas para desviar al jugador o atacar indirectamente.

**Interacciones:**
- **Animatrónicos**: Pueden generar ruido para atraer la atención del jugador. Permanecen aquí si están en modo "Distracting" (Distracción).
- **Jugador**: Debe decidir si investigar el ruido usando cámaras o luces, lo que consume recursos.

**Conexiones:**
- Nodo 1 (Pasillo Izquierdo)
- Nodo 7 (Pasadizo Oculto)

## Nodo 6: Zona de Distracción Derecha
**Propósito:**
Similar al Nodo 5, pero en el lado derecho del mapa. Su objetivo es confundir al jugador y desviar su atención.

**Interacciones:**
- **Animatrónicos**: Generan eventos como apagones de cámaras o ruido ambiental.
- **Jugador**: Debe monitorear si el ruido es una distracción o una amenaza real.

**Conexiones:**
- Nodo 2 (Pasillo Derecho)
- Nodo 7 (Pasadizo Oculto)

## Nodo 7: Pasadizo Oculto
**Propósito:**
Un pasillo alternativo que conecta varias áreas del mapa. Es difícil de monitorear, lo que lo convierte en una ruta ideal para animatrónicos sigilosos.

**Interacciones:**
- **Animatrónicos**: Usan este pasillo para evitar ser detectados por las cámaras principales.
- **Jugador**: Puede monitorearlo solo desde cámaras específicas con alta probabilidad de retraso.

**Conexiones:**
- Nodo 3 (Habitación Inicial 1)
- Nodo 4 (Habitación Inicial 2)
- Nodo 5 (Zona de Distracción Izquierda)
- Nodo 6 (Zona de Distracción Derecha)

## Nodo 8: Sala de Generador
**Propósito:**
La ubicación del sistema de energía. Si la energía se agota, el jugador debe venir aquí para reiniciar manualmente.

**Interacciones:**
- **Jugador**: Reinicia el generador, pero debe permanecer 10 segundos sin interrupciones. Es vulnerable mientras realiza esta acción.
- **Animatrónicos**: Pueden acechar este nodo durante apagones.

**Conexiones:**
- Nodo 10 (Zona de Mantenimiento Izquierda)
- Nodo 11 (Zona de Mantenimiento Derecha)

## Nodo 9: Sala de Seguridad
**Propósito:**
Un cuarto secundario que mejora la eficacia de las cámaras. Aquí las actualizaciones son más rápidas, pero el jugador no tiene acceso a puertas ni luces.

**Interacciones:**
- **Jugador**: Puede cambiar temporalmente su base para monitorear cámaras de manera más eficiente.
- **Animatrónicos**: Pueden intentar atacar si detectan actividad frecuente en este nodo.

**Conexiones:**
- Nodo 11 (Zona de Mantenimiento Derecha)

## Nodo 10: Zona de Mantenimiento Izquierda
**Propósito:**
Un área secundaria usada por animatrónicos para reajustar sus patrones o como ruta alternativa al generador.

**Interacciones:**
- **Animatrónicos**: Pueden reaparecer aquí si son bloqueados repetidamente en otras rutas.

**Conexiones:**
- Nodo 8 (Sala de Generador)
- Nodo 1 (Pasillo Izquierdo)

## Nodo 11: Zona de Mantenimiento Derecha
**Propósito:**
Similar al Nodo 10, pero en el lado derecho. Su objetivo es servir como un punto de tránsito alternativo.

**Interacciones:**
- **Animatrónicos**: Pueden usarlo como ruta secundaria hacia el generador o la Sala de Seguridad.

**Conexiones:**
- Nodo 8 (Sala de Generador)
- Nodo 9 (Sala de Seguridad)
- Nodo 2 (Pasillo Derecho)
#Representación visual del mapa
![image](https://github.com/user-attachments/assets/4cf75889-b996-4e09-9182-54d98ecb8969)

![image](https://github.com/user-attachments/assets/4a5575f0-759f-4d67-8336-636eb1d370bd)

![image](https://github.com/user-attachments/assets/f2017c76-cfd3-4b88-91f7-0bccfa306cdb)

# Descripción de la lógica de la IA en el programa

La lógica detrás de las IA en este proyecto está diseñada para simular un comportamiento semialeatorio e impredecible que desafíe al jugador, utilizando un enfoque basado en nodos y rutas dentro del mapa. Cada animatrónico tiene su propia personalidad, patrones de movimiento y condiciones de toma de decisiones que los hacen únicos.

## Componentes principales de la lógica de IA

### 1. Estados de la IA
Cada IA tiene varios estados que determinan sus acciones actuales. Los estados principales son:
- **Patrullaje**: El animatrónico se mueve entre nodos de manera semi-aleatoria buscando al jugador.
- **Caza**: El animatrónico ha detectado al jugador y se mueve hacia la posición más cercana conocida del jugador.
- **Escondido**: En ciertos casos (animatrónicos específicos), la IA puede detenerse en un nodo específico para emboscar.
- **Inactivo**: El animatrónico se "apaga" por un tiempo determinado (puede ser activado por el jugador, como con un sistema de distracción).

### 2. Decisiones basadas en nodos
La IA utiliza el mapa de nodos para decidir su próximo movimiento. La lógica de decisión incluye:
- **Determinación del nodo objetivo**: Basado en su estado, la IA selecciona su próximo destino:
  - **Patrullaje**: Selecciona un nodo conectado al nodo actual de manera aleatoria.
  - **Caza**: Utiliza un algoritmo como BFS (búsqueda en anchura) para encontrar el camino más corto hacia el nodo objetivo (la posición del jugador conocida más reciente).
  - **Escondido**: Elige un nodo que esté fuera de las rutas principales o cerca de pasadizos ocultos.
- **Detección del jugador**: Cada animatrónico tiene una "visión" que depende de su proximidad al jugador. Si el jugador está dentro de su rango de detección (un nodo adyacente, dependiendo de la IA), cambiará a estado de Caza.

### 3. Personalidades de los animatrónicos
Cada animatrónico tiene su propia lógica y comportamiento:
- **Animatrónico 1 (Agresivo)**: Alta frecuencia de movimientos. Siempre prioriza el estado de Caza si detecta al jugador.
- **Animatrónico 2 (Sigiloso)**: Prefiere rutas ocultas y pasadizos para acercarse al jugador. Puede quedarse en estado de Escondido para emboscar.
- **Animatrónico 3 (Distractor)**: Frecuentemente cambia a nodos de "Distracción" para atraer al jugador. Genera ruidos o activa elementos en el mapa.
- **Animatrónico 4 (Impredecible)**: Movimientos semi-aleatorios incluso cuando está en estado de Caza. Puede cambiar su objetivo de manera espontánea.
- **Animatrónico 5 (Lento pero letal)**: Se mueve con menor frecuencia, pero ignora distracciones. Puede bloquear pasillos estratégicos.

### 4. Interacción con el jugador
La IA interactúa con el jugador a través de:
- **Detección visual y auditiva**: Si el jugador está en un nodo conectado (visión directa), la IA detecta su presencia. Algunos animatrónicos responden a eventos auditivos (como el uso de consolas o el ruido generado por el jugador al moverse rápido entre nodos).
- **Reacciones a acciones del jugador**:
  - **Distracción**: Algunos animatrónicos pueden ser desviados si el jugador activa un dispositivo de distracción.
  - **Puertas bloqueadas**: Si el jugador bloquea un pasillo, algunos animatrónicos intentarán buscar rutas alternativas, mientras otros se quedarán en espera.

## Algoritmos principales

### 1. Movimiento entre nodos (Patrullaje)
Se utiliza un algoritmo de selección aleatoria con ponderación:
- Cada nodo conectado tiene una probabilidad diferente de ser seleccionado como próximo destino, dependiendo de:
  - **Distancia al jugador**: Preferencia por nodos más cercanos al jugador.
  - **Tipo de nodo**: Algunos animatrónicos prefieren ciertos tipos de nodos (e.g., nodos de "Distracción").
  - **Peso aleatorio**: Se introduce una pequeña variación aleatoria para aumentar la imprevisibilidad.

### 2. Rastrear al jugador (Caza)
Se utiliza el algoritmo de búsqueda en grafos BFS (Breadth-First Search) para encontrar la ruta más corta desde la posición actual del animatrónico hasta el nodo objetivo:
- Generar un árbol de búsqueda desde el nodo actual.
- Identificar el nodo más cercano donde se vio al jugador por última vez.
- Generar el camino hacia el nodo objetivo y moverse paso a paso.

### 3. Cambios de estado
Cada animatrónico revisa su estado en intervalos regulares basados en:
- **Distancia al jugador.**
- **Eventos activados en el mapa** (ruidos, distracciones).
- **Estado global del juego**: Ejemplo, si el jugador apaga la energía, algunos animatrónicos pueden entrar en modo agresivo.

## Condiciones de detección

- **Distancia visual**: Si el jugador está en el nodo actual o en uno adyacente, la IA lo detecta.
- **Ruido**: Las acciones del jugador (como correr o usar el generador) aumentan su probabilidad de ser detectado.
- **Cámaras de seguridad**: Algunos animatrónicos pueden ser detectados por el jugador si están en nodos monitoreados, lo que permite al jugador anticipar movimientos.

## Ejemplo de flujo lógico

- **Estado inicial**: El Animatrónico 1 comienza en el nodo "Pasillo Izq" y está en estado de **Patrullaje**.
- **Movimiento**: Selecciona aleatoriamente un nodo conectado, considerando su peso (e.g., prioriza la Oficina).
- **Cambio de estado**: Detecta al jugador usando una consola en la Oficina (rango de detección cumplido).
- **Cambia a estado de Caza**.
- **Ruta hacia el jugador**: Utiliza BFS para encontrar la ruta más corta hacia la Oficina.
- **Interacción**: Si el jugador bloquea la puerta, intenta buscar rutas alternativas.

# Lógica General del Programa en C

## Descripción General
La lógica general del programa se basa en la interacción entre un mapa de nodos (que representa la fábrica), las rutas de los animatrónicos (IA) y las acciones del jugador. A continuación, se detalla cómo se estructurará el programa en lenguaje C para que el mapa, las IA y el jugador trabajen juntos, creando una experiencia dinámica e interactiva.

## 1. Componentes del Programa

### Mapa de Nodos
- Representa la fábrica mediante un conjunto de nodos interconectados, cada uno con características específicas (como pasillos, habitaciones, áreas de distracción, etc.).
- Cada nodo tiene información sobre sus conexiones (adyacencias) con otros nodos.
- El mapa se representa como una matriz o lista de adyacencia, que se utiliza para gestionar las conexiones entre nodos y las decisiones de los animatrónicos y del jugador.

### IA de los Animatrónicos
- Los animatrónicos están diseñados para moverse de forma dinámica y cambian su comportamiento dependiendo de las interacciones del jugador y su entorno.
- Utilizan una lógica de estados (patrullaje, caza, etc.) para determinar su próximo movimiento. Estos estados se definen en el programa y se implementan mediante máquinas de estados.
- Los animatrónicos interactúan con el mapa de nodos utilizando algoritmos de búsqueda como BFS (Breadth-First Search) o Dijkstra para determinar la ruta más corta hacia el jugador o hacia áreas clave.

### Jugador
- El jugador interactúa con el entorno de la fábrica, moviéndose a través del mapa de nodos, utilizando las cámaras de seguridad, sistemas de distracción, y otras herramientas.
- El jugador puede bloquear o abrir pasillos, activar alarmas o cámaras, y manipular el generador de energía, lo cual afecta a la IA de los animatrónicos.

### Interacción entre IA y Jugador
- La IA de los animatrónicos sigue al jugador si está dentro de su rango de detección. Los animatrónicos pueden tener diferentes estrategias, como patrullar aleatoriamente, rastrear al jugador o esconderse en puntos estratégicos.
- El jugador debe tomar decisiones estratégicas para evitar ser alcanzado por los animatrónicos, como usar puertas, distracciones o activar sistemas que desactiven momentáneamente a los animatrónicos.

## 2. Estrategia de Implementación en C

### Estructura de Datos
- El mapa se representará utilizando una matriz de adyacencia o una lista de adyacencia. Para las rutas y movimientos de los animatrónicos, se puede usar una estructura de grafo en C.
- **Grafo de nodos**: Cada nodo tendrá información sobre sus conexiones (adyacencias) con otros nodos. Se puede usar un arreglo de listas enlazadas o una matriz para representar esto.
- **Estructuras de animatrónicos**: Se define una estructura para cada animatrónico que almacene su posición, su estado actual (patrullaje, caza, escondido), su comportamiento y las rutas preferidas.
- **Estructura de jugador**: El jugador tendrá una estructura que almacene su posición actual, sus acciones (si está usando las cámaras, el generador, etc.), y sus interacciones con el mapa.

## 3. Flujo de la Lógica del Programa

### 1. Inicialización del Juego
- El mapa de nodos se configura en una matriz de adyacencia.
- Se definen los nodos del mapa, incluyendo las conexiones entre ellos.
- El jugador y los animatrónicos son ubicados en nodos específicos en el mapa.

### 2. Ciclo de Juego
El juego se ejecuta en un ciclo continuo donde:
- **El jugador realiza una acción**: El jugador se mueve o activa una acción (cámara, generador, etc.).
- **Los animatrónicos se mueven**: Basado en su estado, los animatrónicos realizan su movimiento. Esto puede ser aleatorio, hacia el jugador o de manera sigilosa.
- **El juego verifica si un animatrónico ha alcanzado al jugador**: Si el animatrónico alcanza al jugador, el juego termina.

### 3. Finalización del Juego
El juego finaliza cuando:
- El jugador es atrapado por un animatrónico, o
- Se alcanza el objetivo (si es que hay uno, como sobrevivir una cantidad de turnos).


```c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX_NODOS 20
#define MAX_ANIMATRONICOS 5

// Definición de un nodo en el mapa
typedef struct {
    int id;
    char nombre[50];
    int conexiones[MAX_NODOS]; // Lista de nodos conectados
    int num_conexiones;
} Nodo;

// Definición de un animatrónico
typedef struct {
    int id;
    int estado; // 0 = patrullando, 1 = cazando, 2 = escondido
    int nodo_actual;
    int nodo_destino;
} Animatronico;

// Definición del jugador
typedef struct {
    int nodo_actual;
    int energia; // 0 = sin energía, 1 = con energía
} Jugador;

// Variables globales
Nodo mapa[MAX_NODOS];
Animatronico animatronicos[MAX_ANIMATRONICOS];
Jugador jugador;

// Funciones para interactuar con el mapa
void mover_jugador(Jugador* jugador, int nuevo_nodo);
void mover_animatronico(Animatronico* animatronico);
int obtener_ruta(int nodo_inicio, int nodo_destino);
void actualizar_estado_animatronico(Animatronico* animatronico);
void mostrar_mapa();

// Función principal
int main() {
    // Inicialización
    srand(time(NULL));

    // Inicializar el mapa de nodos
    // Aquí se pueden agregar nodos con sus conexiones
    // Nodo 0 -> Oficina, Nodo 1 -> Pasillo izquierdo, etc.

    // Inicializar jugador y animatrónicos
    jugador.nodo_actual = 0; // Empieza en la Oficina
    jugador.energia = 1; // Empieza con energía

    // Simulación del juego
    while (1) {
        mostrar_mapa(); // Mostrar el mapa actual (podría ser un mapa de texto simple)
        printf("Turno del jugador:\n");
        // El jugador decide qué hacer: mover, activar cámara, etc.

        // El jugador se mueve (por ejemplo, ir al nodo 2)
        mover_jugador(&jugador, 2); // Ejemplo de movimiento

        // Los animatrónicos se mueven
        for (int i = 0; i < MAX_ANIMATRONICOS; i++) {
            mover_animatronico(&animatronicos[i]);
        }

        // Verificar si los animatrónicos han alcanzado al jugador
        for (int i = 0; i < MAX_ANIMATRONICOS; i++) {
            if (animatronicos[i].nodo_actual == jugador.nodo_actual) {
                printf("¡Has sido atrapado por el animatrónico %d!\n", animatronicos[i].id);
                return 0;
            }
        }

        // Otros eventos del juego (ej., uso de generador)
    }

    return 0;
}

// Función para mover al jugador
void mover_jugador(Jugador* jugador, int nuevo_nodo) {
    jugador->nodo_actual = nuevo_nodo;
}

// Función para mover a un animatrónico
void mover_animatronico(Animatronico* animatronico) {
    if (animatronico->estado == 0) {
        // Movimiento aleatorio o patrullaje
        animatronico->nodo_actual = rand() % MAX_NODOS;
    } else if (animatronico->estado == 1) {
        // Movimiento hacia el jugador
        animatronico->nodo_actual = obtener_ruta(animatronico->nodo_actual, jugador.nodo_actual);
    } else if (animatronico->estado == 2) {
        // Esperando o moviéndose de manera sigilosa
        // Implementar lógica para esconderse
    }
}

// Función para obtener una ruta desde un nodo a otro (ejemplo: BFS)
int obtener_ruta(int nodo_inicio, int nodo_destino) {
    // Implementar un algoritmo como BFS o Dijkstra para obtener la ruta más corta
    return nodo_destino; // Placeholder, esta parte debería implementar el algoritmo de ruta
}

// Función para mostrar el mapa (simplificado)
void mostrar_mapa() {
    printf("Mapa Actual:\n");
    printf("Jugador en nodo %d\n", jugador.nodo_actual);
    for (int i = 0; i < MAX_ANIMATRONICOS; i++) {
        printf("Animatrónico %d en nodo %d\n", animatronicos[i].id, animatronicos[i].nodo_actual);
    }
}```




