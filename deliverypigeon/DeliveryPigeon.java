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
}