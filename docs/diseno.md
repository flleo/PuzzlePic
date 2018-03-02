---
typora-root-url: imagenes
typora-copy-images-to: imagenes
---

# Diseño

**PuzzlePic**, ha sido desarrollado en ***Java***, con la ayuda del framework ***JavaFX*** y mediante la utilización de la herramienta  gráfica ***Scene Builder***.

La aplicación esta formada por un menú principal que agrupa todas las funciones permitidas al usuario, con una interfaz simple y sencilla, divide las distintas funcionalidades del juego.

Por cada botón del menú que hagas clic, saldrá una ventana que te muestra las opciones y parámetros disponibles en **PuzzlePic**.

![menuprincipal](/menuprincipal.PNG)



El botón de ***Tema***, nos permitirá cambiar la apariencia de la interfaz de usuario de la aplicación dandole un estilo más personalizado e informal.

![selectortheme](/selectortheme.PNG)

El botón de ***Sonido***, nos permitirá habilitar/deshabilitar la canción de fondo que tiene la aplicación.

![sound](/sound.PNG)

![soundoff](/soundoff.PNG)

El botón ***About***, muestra los nombres del equipo de desarrollo.

![about](/about.PNG)

El botón marcador, abre una ventana que muestra las mejores 10 puntuaciones de los usuarios realizadas en el juego.

El botón ***salir***, finalmente sale de la aplicación.



El botón de ***jugar***, abrirá una nueva ventana, con los parametros de la partida. El jugador deberá introducir su nombre o alias, el tiempo a resolver los puzzles (viene establecido en segundos), el número de rondas a jugar, la dificultad y la carpeta de imágenes a utilizar.

![opcionesdepartida](/opcionesdepartida.PNG)



##### Puzzle Pieces:

Las piezas se colocarán de forma desordenada a la derecha, mientras que el recuadro de la izquierda será el tablero donde se montará el puzzle propuesto.  La etiqueta "Tiempo" es una cuenta atrás que, una vez llegado a cero, parará el juego y te enviará al puzzle siguiente o te llevará a la pantalla de inicio si es el último puzzle. Abandonar te lleva a la pantalla de inicio. Siguiente te llevará a la próxima ronda.

![puzzlepieces](/puzzlepieces.JPG)



##### Puzzle Sliding:

Las piezas se colocarán de forma aleatoria en el recuadro. Para montarlo, tan solo se han de clicar a las piezas y estan rotarán en conjunto de 4 hacia la derecha. Abandonar te lleva a la pantalla de inicio

![puzzleslicing](/puzzleslicing.JPG)