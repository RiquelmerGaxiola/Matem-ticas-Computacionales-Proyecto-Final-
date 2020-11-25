//Jesus Riquelmer Gaxiola Higuera A01740223
import javax.swing.JFrame;

public class Ventana extends JFrame {
  private Panel p;
  
  public Ventana() {
    super("CriptoSistema RSA");
    this.p = new Panel();
    add(this.p);
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  public static void main(String[] args) {
    Ventana v = new Ventana();
  }
}
