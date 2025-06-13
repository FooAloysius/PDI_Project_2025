import java.awt.*;
import javax.swing.*;

public class Toast {
  private static JWindow toastWindow;
  private static Window mainWindow;

  // initialize main window (JFrame)
  public static void setMainWindow(Window window) {
    Toast.mainWindow = window;
  }

  public static void showToast(String message, int durationMillis) {
    if (mainWindow == null) {
      throw new IllegalStateException("Toast main window not set. Call Toast.setMainWindow(frame) first.");
    }

    if (toastWindow != null && toastWindow.isVisible()) {
      toastWindow.setVisible(false);
      toastWindow.dispose();
    }

    toastWindow = new JWindow(mainWindow);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(60, 63, 65));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    JLabel label = new JLabel(message);
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Arial", Font.PLAIN, 14));

    panel.add(label);
    toastWindow.add(panel);
    toastWindow.pack();

    int x = mainWindow.getX() + mainWindow.getWidth() - 200;
    int y = mainWindow.getY()+ mainWindow.getHeight() - 150;
    toastWindow.setLocation(x, y);

    toastWindow.setAlwaysOnTop(true);
    toastWindow.setVisible(true);

    new javax.swing.Timer(durationMillis, e -> {
      toastWindow.setVisible(false);
      toastWindow.dispose();
    }).start();
  }
}
