package Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Me on 5/3/17.
 * When I get a chance to make a pretty chess UI
 * For now, nonfunctional, methods adapted from various internet sources:
 *
 */
public class GUI {

    public static void showGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("FrameDemo");
        frame.setSize(new Dimension(500, 400));
        frame.setLayout(new GridLayout(0, 9));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        //add buttons
        JButton A1 = new JButton(" ");
        JButton A2 = new JButton(" ");
        JButton A3 = new JButton(" ");
        JButton A4 = new JButton(" ");
        JButton A5 = new JButton(" ");
        JButton A6 = new JButton(" ");
        JButton A7 = new JButton(" ");
        JButton A8 = new JButton(" ");

        JButton B1 = new JButton(" ");
        JButton B2 = new JButton(" ");
        JButton B3 = new JButton(" ");
        JButton B4 = new JButton(" ");
        JButton B5 = new JButton(" ");
        JButton B6 = new JButton(" ");
        JButton B7 = new JButton(" ");
        JButton B8 = new JButton(" ");

        JButton C1 = new JButton(" ");
        JButton C2 = new JButton(" ");
        JButton C3 = new JButton(" ");
        JButton C4 = new JButton(" ");
        JButton C5 = new JButton(" ");
        JButton C6 = new JButton(" ");
        JButton C7 = new JButton(" ");
        JButton C8 = new JButton(" ");

        JButton D1 = new JButton(" ");
        JButton D2 = new JButton(" ");
        JButton D3 = new JButton(" ");
        JButton D4 = new JButton(" ");
        JButton D5 = new JButton(" ");
        JButton D6 = new JButton(" ");
        JButton D7 = new JButton(" ");
        JButton D8 = new JButton(" ");

        JButton E1 = new JButton(" ");
        JButton E2 = new JButton(" ");
        JButton E3 = new JButton(" ");
        JButton E4 = new JButton(" ");
        JButton E5 = new JButton(" ");
        JButton E6 = new JButton(" ");
        JButton E7 = new JButton(" ");
        JButton E8 = new JButton(" ");

        JButton F1 = new JButton(" ");
        JButton F2 = new JButton(" ");
        JButton F3 = new JButton(" ");
        JButton F4 = new JButton(" ");
        JButton F5 = new JButton(" ");
        JButton F6 = new JButton(" ");
        JButton F7 = new JButton(" ");
        JButton F8 = new JButton(" ");

        JButton G1 = new JButton(" ");
        JButton G2 = new JButton(" ");
        JButton G3 = new JButton(" ");
        JButton G4 = new JButton(" ");
        JButton G5 = new JButton(" ");
        JButton G6 = new JButton(" ");
        JButton G7 = new JButton(" ");
        JButton G8 = new JButton(" ");

        JButton H1 = new JButton(" ");
        JButton H2 = new JButton(" ");
        JButton H3 = new JButton(" ");
        JButton H4 = new JButton(" ");
        JButton H5 = new JButton(" ");
        JButton H6 = new JButton(" ");
        JButton H7 = new JButton(" ");
        JButton H8 = new JButton(" ");

        //add buttons to panel
        panel.add(A1);
        panel.add(A2);
        panel.add(A3);
        panel.add(A4);
        panel.add(A5);
        panel.add(A6);
        panel.add(A7);
        panel.add(A8);

        panel.add(B1);
        panel.add(B2);
        panel.add(B3);
        panel.add(B4);
        panel.add(B5);
        panel.add(B6);
        panel.add(B7);
        panel.add(B8);

        panel.add(C1);
        panel.add(C2);
        panel.add(C3);
        panel.add(C4);
        panel.add(C5);
        panel.add(C6);
        panel.add(C7);
        panel.add(C8);

        panel.add(D1);
        panel.add(D2);
        panel.add(D3);
        panel.add(D4);
        panel.add(D5);
        panel.add(D6);
        panel.add(D7);
        panel.add(D8);

        panel.add(E1);
        panel.add(E2);
        panel.add(E3);
        panel.add(E4);
        panel.add(E5);
        panel.add(E6);
        panel.add(E7);
        panel.add(E8);

        panel.add(F1);
        panel.add(F2);
        panel.add(F3);
        panel.add(F4);
        panel.add(F5);
        panel.add(F6);
        panel.add(F7);
        panel.add(F8);

        panel.add(G1);
        panel.add(G2);
        panel.add(G3);
        panel.add(G4);
        panel.add(G5);
        panel.add(G6);
        panel.add(G7);
        panel.add(G8);

        panel.add(H1);
        panel.add(H2);
        panel.add(H3);
        panel.add(H4);
        panel.add(H5);
        panel.add(H6);
        panel.add(H7);
        panel.add(H8);

        frame.getContentPane().add(panel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
