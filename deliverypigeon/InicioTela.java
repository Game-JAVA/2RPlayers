import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class InicioTela extends JPanel {

    private TransparentButton btnIniciarJogo;
    private TransparentButton btnSair;
    private JLabel lblPontos;

    private JFrame parentFrame;
    private DeliveryPigeon gamePanel;

    private BufferedImage backgroundImage;

    // Fonte Press Start 2P
    private Font pressStartFont;

    public InicioTela(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(new GridBagLayout());

        // Carregar a fonte Press Start 2P
        try {
            // Substitua o caminho da fonte conforme necessário
            pressStartFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/PressStart2P-Regular.ttf"));
            pressStartFont = pressStartFont.deriveFont(Font.PLAIN, 18);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pressStartFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Em caso de erro, usar uma fonte alternativa
            pressStartFont = new Font("Arial", Font.PLAIN, 18);
        }

        // Carregar a imagem de fundo
        try {
            // Substitua o caminho da imagem conforme necessário
            backgroundImage = ImageIO.read(new File("img/banner.jpeg")); // Exemplo: "src/fundo.jpg"
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Label para exibir os pontos
        lblPontos = new JLabel("", JLabel.CENTER);
        lblPontos.setFont(pressStartFont);
        lblPontos.setForeground(Color.WHITE); // Cor do texto branca
        lblPontos.setBackground(Color.BLACK); // Cor de fundo preta
        lblPontos.setOpaque(true); // Garante que o fundo seja visível
        GridBagConstraints pontosConstraints = new GridBagConstraints();
        pontosConstraints.gridx = 0;
        pontosConstraints.gridy = 1;
        pontosConstraints.insets = new Insets(0, 0, 0, 400); // Espaçamento top-bottom
        add(lblPontos, pontosConstraints);

        // Botão Iniciar Jogo
        btnIniciarJogo = new TransparentButton("Iniciar Jogo");
        btnIniciarJogo.setPreferredSize(new Dimension(300, 50));
        btnIniciarJogo.setFont(pressStartFont.deriveFont(Font.BOLD, 18));
        btnIniciarJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });
        GridBagConstraints btnStartConstraints = new GridBagConstraints();
        btnStartConstraints.gridx = 0;
        btnStartConstraints.gridy = 2;
        btnStartConstraints.insets = new Insets(200, 0, 20, 0); // Espaçamento top-bottom
        add(btnIniciarJogo, btnStartConstraints);

        // Botão Sair
        btnSair = new TransparentButton("Sair");
        btnSair.setPreferredSize(new Dimension(300, 50));
        btnSair.setFont(pressStartFont.deriveFont(Font.BOLD, 18));
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });
        GridBagConstraints btnExitConstraints = new GridBagConstraints();
        btnExitConstraints.gridx = 0;
        btnExitConstraints.gridy = 3;
        btnExitConstraints.insets = new Insets(20, 0, 230, 0); // Espaçamento top-bottom
        add(btnSair, btnExitConstraints);

        // Adicionando o KeyListener para bloquear a tecla de espaço
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Bloqueia a tecla de espaço (código 32)
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    e.consume(); // Consumir o evento para impedir o processamento adicional
                }
            }
        });

        // Necessário para o JPanel receber eventos de teclado
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenhar a imagem de fundo se estiver carregada
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void showScore(int score) {
        lblPontos.setText("Pontuação: " + score);
    }

    private void iniciarJogo() {
        gamePanel = new DeliveryPigeon(parentFrame, this); // Passando a própria tela inicial como argumento
        parentFrame.getContentPane().removeAll();
        parentFrame.add(gamePanel);
        parentFrame.revalidate();
        parentFrame.repaint();
        parentFrame.pack();
        parentFrame.setLocationRelativeTo(null);
        parentFrame.setVisible(true);
        gamePanel.requestFocusInWindow(); // Garante que o jogo tenha foco para detectar eventos de teclado
    }

    private void sair() {
        System.exit(0);
    }

    public void showInicioTela() {
        parentFrame.getContentPane().removeAll();
        parentFrame.add(this); // Adicionando a própria instância de InicioTela de volta
        parentFrame.setSize(500, 650); // Mantendo as dimensões padrão da tela
        parentFrame.revalidate();
        parentFrame.repaint();
        parentFrame.setLocationRelativeTo(null); // Centraliza o JFrame na tela
        parentFrame.setVisible(true); // Tornar o JFrame visível
    }