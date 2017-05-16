package Main;
/**
 * Created by Me on 5/3/17.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
public class Main {

    /**
     * automated main method, to be used in testing/when a list of arguments already exists
     */
    public static void autoMain(String[] args) {
        Board board = new Board();
        Scanner s = new Scanner(System.in);
        board.visualizeBoard();
        String str;
        int counter = 0;
        while (!board.endGame()) {
            str = args[counter];
            counter++;
            if (str.equals("QUIT")) {
                break;
            } else {
                String[] strings = str.split(" ");
                if (strings.length != 2) {
                } else {
                    if (!board.movePiece(strings[0], strings[1])) {
                    }
                }
            }

            while (board.promoting) {
                str = s.nextLine().toUpperCase();
                board.promotePawn(str);
            }
        }
        System.out.println("Game Over");
    }

    public static void main(String[] args) {
        //GUI.showGUI();

        Board board = new Board();
        Scanner s = new Scanner(System.in);
        board.visualizeBoard();
        String str;
        while (!board.endGame()) {
            str = s.nextLine().toUpperCase();
            if (str.equals("QUIT")) {
                break;
            } else {
                String[] strings = str.split(" ");
                if (strings.length != 2) {
                    System.out.println("please enter move as 'first_square second_square'");
                } else {
                    if (!board.movePiece(strings[0], strings[1])) {
                        System.out.println("invalid move!");
                    }
                }
            }

            while (board.promoting) {
                System.out.println("Specify what to promote to");
                str = s.nextLine().toUpperCase();
                board.promotePawn(str);
            }
        }
        System.out.println("Game Over");

    }
}
