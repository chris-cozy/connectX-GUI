# connectXGui: connect 4 with a twist  🎮 
<h2>Description</h2>
My ConnectX project. It's similar to Connect5 but customizable, the user has control over board customization. Instead of being played through the terminal, this version's implementation makes use of the Model View Controller schema, as well as JavaSwing in order to produce a GUI for playing. There are two implementations for this project, labeled GameBoard and GameBoardMem. The difference between them is the memory efficiency of the program.
<br>
GameBoard makes use of a 2D char array for implementation.
<br>
GameBoardMem makes use of hashmaps for implementation, and is the more efficient implementation.
<br>

<h2>Installation</h2>
The project can be compiled using the provided Makefile, then run.
<br>
<h2>Tech Stack</h2>
<div id ="badges">
  <img src="https://github.com/devicons/devicon/blob/master/icons/git/git-original.svg" title="Git" alt="Git" width="40" height="40">
  <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original.svg" title="Java" alt="Java" width="40" height="40">
  <img src="https://github.com/devicons/devicon/blob/master/icons/intellij/intellij-original.svg" title="intelliJ" alt="intelliJ" width="40" height="40">
</div>
<h2>Notes</h2>
GameBoard and GameBoardMem are both implementations of the interface IGameBoard, and extensions of AbsGameBoard. 
<br>
AbsGameBoard overwrites the toString() method, which displays the gameboard in the terminal.
<br>
This project was written using the IntelliJ IDE.
<br>
