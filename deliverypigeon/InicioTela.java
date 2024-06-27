import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class InicioTela extends JPanel {

    private JButton btnIniciarJogo;
    private JButton btnSair;
    private JLabel lblPontos;

    private JFrame parentFrame;
    private DeliveryPigeon gamePanel;

    private BufferedImage backgroundImage;

    public InicioTela(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(new GridBagLayout());

        // Carregar a imagem de fundo
        try {
            // Substitua o caminho da imagem conforme necessário
            backgroundImage = ImageIO.read(new File("img/banner.jpeg")); // Exemplo: "src/fundo.jpg"
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}