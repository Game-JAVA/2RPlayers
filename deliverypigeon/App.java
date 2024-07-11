import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int boardWidth = 500;
            int boardHeight = 650;

            JFrame frame = new JFrame("Delivery Pigeon");
            frame.setSize(boardWidth, boardHeight);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            InicioTela inicioTela = new InicioTela(frame);
            frame.add(inicioTela);
        });
    }
}