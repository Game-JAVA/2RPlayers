# 2RPlayers 🕹️

Game Project  
Language: Java 21  
Framework: Java Swing  
Agile Framework: Scrum  

# 🪶 Delivery Pigeon
<div align = "center">
    <img src="./arquivos/imgs/logo-jogo.jpg" alt="cap-do-jogo" height="300px" width="300px">
</div>

# 📽️ Presentation
Our team, consisting of analysts João Gabriel, João Pedro, Rafael Serio, and Luiz Gustavo, have developed a game inspired by the basic yet addictive mechanics of the classic Flappy Bird. We drew inspiration from the minimalist gameplay of this globally renowned game; however, our narrative and artistic style are entirely original. Under the title 'Delivery Pigeon', this project aims to showcase and enhance our skills in both back-end and front-end development, while applying the rapid methodology known as 'SCRUM'

# 🏔️ Prologue
In the game "Delivery Pigeon," players will dive into a captivating adventure that features 3 stages: countryside, city, and the sewage system. Controlling a carrier pigeon, they will navigate through these environments, challenged to avoid collisions with obstacles such as poles and pipes. With each stage completed, the player progresses to a new environment, but a collision with any obstacle signifies the end of the journey.

# 🎮 Gameplay
"Delivery Pigeon" is a game where players will face the challenge of controlling a pigeon, attempting to keep it airborne with simple taps on specific keys, guiding it through spaces between obstacles while traversing different environments until the end of the Delivery Pigeon's journey.

# ⌨️ Controls
- 'Space' key to make the pigeon jump.
- 'P' key to pause the game.
<div align = "center">
    <img src="./arquivos/imgs/controls.png" alt="controls" height="300px" width="350px">
</div>

# 📄 Class Diagram
The class diagram of the "DeliveryPigeon" game outlines the structure and responsibilities of each class:

App: The main class that starts the game with the main method.

InicioTela: Manages the game's start screen. It contains buttons to start and exit the game, a score label, and methods to start the game and display the start screen.

DeliveryPigeon: Contains the main game logic, including board setup, pigeon control, pipe management, and event handling. It has methods to load images, position pipes, draw graphical components, and check for collisions.

Pigeon: Represents the pigeon in the game with its position and image properties.

Pipe: Represents the pipes in the game with their position and image properties.

```mermaid
classDiagram
    class App {
      +main(args: String[])
    }

    class InicioTela {
      -btnIniciarJogo: JButton
      -btnSair: JButton
      -lblPontos: JLabel
      -parentFrame: JFrame
      -gamePanel: DeliveryPigeon
      -backgroundImage: BufferedImage
      +InicioTela(parentFrame: JFrame)
      +paintComponent(g: Graphics)
      +showScore(score: int)
      +iniciarJogo()
      +sair()
      +showInicioTela()
      +main(args: String[])
    }

    class DeliveryPigeon {
      -boardWidth: int
      -boardHeight: int
      -backgroundImg: Image
      -pigeonImg: Image
      -topPipeImg: Image
      -bottomPipeImg: Image
      -pigeonX: int
      -pigeonY: int
      -pigeonWidth: int
      -pigeonHeight: int
      -pipeX: int
      -pipeY: int
      -pipeWidth: int
      -pipeHeight: int
      -pigeon: Pigeon
      -velocityX: int
      -velocityY: int
      -gravity: int
      -pipes: ArrayList~Pipe~
      -random: Random
      -gameLoop: Timer
      -placePipeTimer: Timer
      -gameOver: boolean
      -score: double
      -highScore: double
      -paused: boolean
      -parentFrame: JFrame
      -inicioTela: InicioTela
      -preferences: Preferences
      +DeliveryPigeon(parentFrame: JFrame, inicioTela: InicioTela)
      -loadImage(fileName: String): Image
      -placePipes()
      +paintComponent(g: Graphics)
      +draw(g: Graphics)
      +showInicioTela()
      +restartGame()
      +move()
      +collision(a: Pigeon, b: Pipe): boolean
      +actionPerformed(e: ActionEvent)
      +keyPressed(e: KeyEvent)
      +keyTyped(e: KeyEvent)
      +keyReleased(e: KeyEvent)
      +main(args: String[])
    }

    class Pigeon {
      -x: int
      -y: int
      -width: int
      -height: int
      -img: Image
      +Pigeon(img: Image)
    }

    class Pipe {
      -x: int
      -y: int
      -width: int
      -height: int
      -img: Image
      -passed: boolean
      +Pipe(img: Image)
    }

    App --> InicioTela
    InicioTela --> DeliveryPigeon
    DeliveryPigeon --> Pigeon
    DeliveryPigeon --> Pipe
```

# 📁 Download
```
git clone https://github.com/Game-JAVA/2RPlayers.git
```
