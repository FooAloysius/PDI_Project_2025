
import javax.swing.JButton;
import javax.swing.JPanel;

public class CustomerPage {
  public void gui (JPanel panel) {
    // panel.removeAll();
    // panel.add(new JButton("Add User Page"));
    panel.removeAll();

    JButton button = new JButton("Hello");

    panel.add(button);
    panel.revalidate();
    panel.repaint();
  } 
}
