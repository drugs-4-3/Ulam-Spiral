/**
 * Created by Michał on 09.08.2016.
 *
 * Inspired by: https://en.wikipedia.org/wiki/Ulam_spiral
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        add(new MainPanel());
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainFrame();
                frame.setTitle("Spiral");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
