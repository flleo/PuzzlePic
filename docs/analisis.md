# Análisis

#### *Descripción general de la aplicación.*

**PuzzlePic** es una apilcación de entretenimiento en la cual podrá jugar un jugador en local con la meta de completar los puzzles propuestos en una cierta cantidad de tiempo e intentar batir los records de otros usuarios, ya que cualquiera que juegue, sus datos se subirán a un marcador en la nube.



#### *Opciones de Partida*

Las opciones de personalización de la partida son los siguientes:

- Nombre del jugador: Con la que guardará su marca.
- El número de rompecabezas a resolver.
- Dificultad (número de piezas del puzzle [no implementado en la versión]).
- Tiempo: segundos para resolver cada puzzle.  
- Seleccionar Carpeta: selecciona la colección de imágenes que tendrá que resolver. Se elegirán de forma  aleatoria

Ninguno de los campos podrá ser nulo. 



#### *Partida en juego*

El jugador deberá de montar los puzzles mediante la colocación correcta de los fragmentos de la imagen resultado. Cuando todas las piezas estén colocadas, la cuenta atrás se detendrá y su puntuación se guardará. Al completar todos los puzzles propuestos, la puntuación total se sumará y su puntuación se guardará en el marcador en la nube. Si por alguna razón, el jugador desiste del rompecabezas pulsando el botón "Abandonar", su puntuación será evaluada dependiendo del número de puzzles resueltos hasta el momento, guardará la puntuación y saldrá directamente a la pantalla de inicio.

##### Puzzle Pieces

Todas las piezas se podrán mover a excepción si se coloca en el lugar que le corresponde. 

##### Puzzle Slicing

Las piezas rotan en círculo a la derecha cuando son pulsadas.




#### Marcador y sistema de puntuación

Aquí se mostrarán las diez mejores puntuaciones obtenidas por la comunidad.

El sistema de puntuación es el siguiente:

- Cada puzzle tiene una puntuación:
  - Fácil (10).
  - Medio (25).
  - Difícil (50).
- Resolverlo, sumará dicho número.
- No acabarlo a tiempo o abandonarlo no sumará puntos.
- El tiempo sobrante de cada puzzle (en segundos) se sumará.



### *Conclusión*

El objetivo final de esta aplicación es fomentar la agilidad mental de los jugadores gracias a la competitividad creada por el sistema de puntuaciones globales