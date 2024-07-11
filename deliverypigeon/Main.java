public class Main{
    public static void main(String[] args){
           SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Delivery Pigeon Game");
            InicioTela inicioTela = new InicioTela(frame);
            frame.add(inicioTela);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setSize(500, 650); // Mantendo as dimensões padrão da tela
        });
    }
}