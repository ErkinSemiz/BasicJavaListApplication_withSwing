import javax.swing.*;

public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run( ) {
				JFrame mainFrame = new mainFrame("Vize Soru 3 Erkin Semiz 152120141057");
				mainFrame.setSize(600,400);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			}
		});

	}

}
