//Jesus Riquelmer Gaxiola Higuera A01740223
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel {
  private JTextField mensaje;
  private JTextField encriptado;
  private JTextField resultado;
  
  private JLabel Res;
  
  private JButton but;
  
  private RSA rsa;
  
  public Panel() {
    setPreferredSize(new Dimension(1200, 200));
    setLayout((LayoutManager)null);
    this.rsa=new RSA();
    this.mensaje = new JTextField();
    this.encriptado = new JTextField();
    this.resultado = new JTextField();
    this.Res = new JLabel();
    this.but = new JButton("Encriptar");
    this.mensaje.setBounds(50, 50, 300, 30);
    this.encriptado.setBounds(450, 50, 300, 30);
    this.resultado.setBounds(850, 50, 300, 30);
    this.Res.setBounds(50, 150, 700, 30);
    this.but.setBounds(1000, 150, 120, 40);
    add(this.mensaje);
    add(this.encriptado);
    add(this.resultado);
    add(this.Res);
    add(this.but);
    this.but.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String mensaje=Panel.this.mensaje.getText();
            Panel.this.encriptado.setText(bytesToString(mensaje.getBytes()));
            // encrypt
            byte[] encrypted = rsa.encrypt(mensaje.getBytes());
            // decrypt
            byte[] decrypted = rsa.decrypt(encrypted);
            Panel.this.resultado.setText(new String(decrypted));
            Panel.this.Res.setText(" "+rsa.d.intValue()+","+rsa.n.intValue());
          }
        });
  }
  
  private static String bytesToString(byte[] encrypted) {
      String test = "";
      for (byte b : encrypted)
      {
          test += Byte.toString(b);
      }
      return test;
  }
  
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawString("Mensaje Original:", 50, 47);
    g.drawString("Mensaje Encriptado:", 450, 47);
    g.drawString("Mensaje Decifrado:", 850, 47);
    g.drawString("Llaves:", 50, 147);
  }
}
