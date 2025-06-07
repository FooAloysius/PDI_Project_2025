
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HomePage {
  private JTextField nameField;
  private JTextField emailField;
  private JTextField petsField;
  public void gui (JPanel panel) {
    panel.removeAll();

    JPanel homePanel = new JPanel();
    homePanel.setBackground(Color.WHITE);
    homePanel.setLayout(new GridLayout(4,1,0,10));

    JLabel pageTitle = new JLabel("Home Page");

    homePanel.add(pageTitle);

    panel.add(homePanel);
    panel.revalidate();
    panel.repaint();
  } 
}
