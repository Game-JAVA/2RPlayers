import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class DeliveryPigeon extends JPanel implements ActionListener, KeyListener {
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 650;

    // Variáveis de imagens
    private Image backgroundImg;
    private Image pigeonImg;
    private Image topPipeImg;
    private Image bottomPipeImg;

    // Classe Pigeon
    private Pigeon pigeon;
    private int pigeonX = BOARD_WIDTH / 8;
    private int pigeonY = BOARD_WIDTH / 2;
    private int pigeonWidth = 44; // 34
    private int pigeonHeight = 34; // 24

    // Classe Pipe
    private ArrayList<Pipe> pipes;
    private int pipeX = BOARD_WIDTH;
    private int pipeY = 0;
    private int pipeWidth = 64;
    private int pipeHeight = 512;

    // Lógica do jogo
    private int velocityX = -4;
    private int velocityY = 0;
    private int gravity = 1;

    private Timer gameLoop;
    private Timer placePipeTimer;
    private boolean gameOver = false;
    private double score = 0;
    private double highScore = 0; // Pontuação máxima
    private boolean paused = false; // Variável de controle de pausa

    private JFrame parentFrame;
    private InicioTela inicioTela;

    // Preferências para armazenar a pontuação máxima
    private Preferences preferences = Preferences.userNodeForPackage(DeliveryPigeon.class);
    private static final String HIGH_SCORE_KEY = "high_score";

    public DeliveryPigeon(JFrame parentFrame, InicioTela inicioTela) {
        this.parentFrame = parentFrame;
        this.inicioTela = inicioTela;

        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        // Carregar imagens
        backgroundImg = loadImage("fundo.gif");
        pigeonImg = loadImage("pigeon.gif");
        topPipeImg = loadImage("arvore.jpeg");
        bottomPipeImg = loadImage("arvore.jpeg");

        // Pigeon
        pigeon = new Pigeon(pigeonX, pigeonY, pigeonWidth, pigeonHeight, pigeonImg);
        pipes = new ArrayList<>();

        // Carregar a pontuação máxima salva
        highScore = preferences.getDouble(HIGH_SCORE_KEY, 0);

        // Timer para colocar os pipes
        placePipeTimer = new Timer(1500, e -> placePipes());
        placePipeTimer.start();

        // Timer do jogo
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    private Image loadImage(String fileName) {
        Image image = null;
        try {
            image = new ImageIcon(getClass().getResource("img/" + fileName)).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    private void placePipes() {
        int randomPipeY = (int) (pipeY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = BOARD_HEIGHT / 4;

        Pipe topPipe = new Pipe(pipeX, randomPipeY, pipeWidth, pipeHeight, topPipeImg);
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(pipeX, topPipe.getY() + pipeHeight + openingSpace, pipeWidth, pipeHeight,
                bottomPipeImg);
        pipes.add(bottomPipe);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Background
        g.drawImage(backgroundImg, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null);

        // Pigeon
        g.drawImage(pigeon.getImg(), pigeon.getX(), pigeon.getY(), pigeon.getWidth(), pigeon.getHeight(), null);

        // Pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImg(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        // Score
        g.setColor(Color.black); // Fundo preto para o contador de pontos
        g.fillRect(0, 0, 280, 40); // Retângulo preto para o fundo do contador de pontos
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.setColor(Color.red); // Cor vermelha para o texto de "Game Over"
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        } else {
            g.drawString("Score:" + String.valueOf((int) score), 10, 35);
            g.setColor(Color.black); // Fundo preto para o contador de pontos
            g.fillRect(0, 40, 280, 40); // Retângulo preto para o fundo do contador de pontos
            g.setColor(Color.white); // Fundo preto para o contador de pontos
            g.drawString("High Score:" + String.valueOf((int) highScore), 10, 70); // High score no canto inferior esquerdo
        }
    }

    private void showInicioTela() {
        parentFrame.getContentPane().removeAll();
        inicioTela.showScore((int) score); // Exibe os pontos na tela inicial
        parentFrame.add(inicioTela);
        parentFrame.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}