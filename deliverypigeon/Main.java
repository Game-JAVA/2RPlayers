public class Main{
    public static void main(String[] args){
           SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Delivery Pigeon Game");
            InicioTela inicioTela = new InicioTela(frame);
            frame.add(inicioTela);
            frame.pack();
        });
    }
}