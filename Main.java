import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Display display = new Display();

        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(display);
        frame.add(new Calculator(display));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}