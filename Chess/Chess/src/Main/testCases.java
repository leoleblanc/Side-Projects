package Main;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Me on 5/3/17.
 */
public class testCases {

    @Test
    public void testMakeLayout() {
        Board board = new Board();
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++, counter++) {
                if (!Arrays.asList(Board.spots).contains(board.getAllTiles()[i][j].getSpot())) {
                    fail();
                }
                if (!board.getAllTiles()[i][j].getClass().equals(Tile.class)) {
                    fail();
                }
            }
        }

        if (counter != 64) {
            fail();
        }
    }

    @Test
    public void testMakeBoard() {
        Board board = new Board();
        board.visualizeBoard();
    }

    @Test
    public void testMovePiece() {
        Board board = new Board();
        board.movePiece("A2", "A4");
        board.visualizeBoard();
    }

    @Test
    public void testMovePawnsAndTurns() {
        Board board = new Board();
        assertTrue(board.movePiece("A2", "A3"));
        assertFalse(board.movePiece("A3", "A4"));
        assertTrue(board.movePiece("D7", "D5"));
        assertFalse(board.movePiece("E7", "E6"));
        board.visualizeBoard();
    }

    @Test
    public void testCapturePawn() {
        Board board = new Board();
        assertTrue(board.movePiece("D2", "D4"));
        assertTrue(board.movePiece("D7", "D5"));
        assertTrue(board.movePiece("E2", "E4"));
        assertTrue(board.movePiece("D5", "E4"));
        board.visualizeBoard();
    }

    @Test
    public void testRookCapture() {
        Board board = new Board();
        assertTrue(board.movePiece("A2", "A4"));
        assertTrue(board.movePiece("A7", "A5"));
        assertTrue(board.movePiece("A1", "A3"));
        assertTrue(board.movePiece("D7", "D6"));
        assertTrue(board.movePiece("A3", "D3"));
        assertTrue(board.movePiece("C7", "C6"));
        assertFalse(board.movePiece("D3", "D7"));
        assertTrue(board.movePiece("D3", "D6"));
        assertTrue(board.movePiece("E7", "D6"));
    }

    @Test
    public void testDancingKnights() {
        Board board = new Board();
        assertFalse(board.movePiece("B1", "B3"));
        assertTrue(board.movePiece("B1", "C3"));
        assertFalse(board.movePiece("B1", "B3"));
        assertFalse(board.movePiece("B1", "B3"));
        assertTrue(board.movePiece("B8", "C6"));
        assertTrue(board.movePiece("G1", "F3"));
        assertTrue(board.movePiece("G8", "F6"));
        assertTrue(board.movePiece("C3", "D5"));
        assertTrue(board.movePiece("F6", "E4"));
        assertFalse(board.movePiece("B1", "A3"));
        assertFalse(board.movePiece("B1", "A3"));
        assertTrue(board.movePiece("F3", "D4"));
        assertTrue(board.movePiece("C6", "D4"));
    }

    @Test
    public void testKingMove() {
        Board board = new Board();
        assertFalse(board.movePiece("B1", "B2"));
        assertTrue(board.movePiece("B1", "C3"));
        assertTrue(board.movePiece("D7", "D5"));
        assertTrue(board.movePiece("C3", "D5"));
        assertTrue(board.movePiece("D8", "D5"));
        assertTrue(board.movePiece("E2", "E4"));
        assertTrue(board.movePiece("D5", "D2"));
        assertTrue(board.movePiece("E1", "D2"));
        assertFalse(board.movePiece("E1", "D2"));
        assertFalse(board.movePiece("D1", "D2"));
    }

    @Test
    public void testKingInCheck() {
        Board board = new Board();
        assertTrue(board.movePiece("D2", "D4"));
        assertTrue(board.movePiece("C7", "C5"));
        assertTrue(board.movePiece("E2", "E4"));
        assertTrue(board.movePiece("D8", "A5"));
        assertTrue(board.isWhiteKingCheck());
    }

    @Test
    public void testChangeState() {
        Board board = new Board();
        Tile t1 = board.getTile(3, 1);
        Tile t2 = board.getTile(3, 3);
        String first = board.visualizeBoard();

        board.changeState(t1, t2);

        String transition = board.visualizeBoard();
        assertNotEquals(first, transition);

        board.undoChangeState(t1, t2);

        String second = board.visualizeBoard();

        assertNotEquals(transition, second);

        assertEquals(first, second);
    }

    @Test
    public void testRevivePieceChangeState() {
        Board board = new Board();
        Tile t1 = board.getTile(3, 1);
        Tile t2 = board.getTile(3, 7);

        String first = board.visualizeBoard();

        board.changeState(t1, t2);

        String transition = board.visualizeBoard();

        board.undoChangeState(t1, t2);

        String second = board.visualizeBoard();

        assertEquals(first, second);
    }

    @Test
    public void testKingInCheckMoves() {
        Board board = new Board();
        assertTrue(board.movePiece("D2", "D4"));
        assertTrue(board.movePiece("C7", "C5"));
        assertTrue(board.movePiece("E2", "E4"));
        assertTrue(board.movePiece("D8", "A5"));
        assertFalse(board.movePiece("E1", "D2"));
        assertFalse(board.movePiece("H2", "H4"));
        assertFalse(board.movePiece("H2", "H5"));
        assertTrue(board.movePiece("E1", "E2"));
    }

    @Test
    public void testCheeseCheckmate() { //a 2 move checkmate
        Board board = new Board();
        assertTrue(board.movePiece("F2", "F4"));
        assertTrue(board.movePiece("E7", "E5"));
        assertTrue(board.movePiece("G2", "G4"));
        assertTrue(board.movePiece("D8", "H4"));
        assertTrue(board.isWhiteKingCheck());
        assertTrue(board.endGame());
    }

    @Test
    public void testLongerCheeseCheckmate() {
        Board board = new Board();
        assertTrue(board.movePiece("E2", "E3"));
        assertTrue(board.movePiece("A7", "A6"));
        assertTrue(board.movePiece("F1", "C4"));
        assertTrue(board.movePiece("B7", "B5"));
        assertTrue(board.movePiece("D1", "H5"));
        assertTrue(board.movePiece("B5", "B4"));
        assertTrue(board.movePiece("H5", "F7"));
        assertTrue(board.endGame());
    }

    @Test
    public void testIntricateMate() {
        Board board = new Board();
        assertTrue(board.movePiece("E2", "E4"));
        assertTrue(board.movePiece("E7", "E6"));
        assertTrue(board.movePiece("G1", "F3"));
        assertTrue(board.movePiece("B8", "C6"));
        assertTrue(board.movePiece("F1", "C4"));
        assertTrue(board.movePiece("F8", "C5"));
        assertTrue(board.movePiece("B1", "C3"));
        assertTrue(board.movePiece("G8", "F6"));
        assertTrue(board.movePiece("F3", "G5"));
        assertTrue(board.movePiece("F6", "E4"));
        assertTrue(board.movePiece("G5", "F7"));
        assertTrue(board.movePiece("D8", "F6"));
        assertTrue(board.movePiece("F7", "H8"));
        assertTrue(board.movePiece("F6", "F2"));
        assertTrue(board.endGame());
    }

    @Test
    public void testStalemate() {
        Board board = new Board();
        assertTrue(board.movePiece("E2", "E3"));
        assertTrue(board.movePiece("A7", "A5"));
        assertTrue(board.movePiece("D1", "H5"));
        assertTrue(board.movePiece("A8", "A6"));
        assertTrue(board.movePiece("H5", "A5"));
        assertTrue(board.movePiece("H7", "H5"));
        assertTrue(board.movePiece("H2", "H4"));
        assertTrue(board.movePiece("A6", "H6"));
        assertTrue(board.movePiece("A5", "C7"));
        assertTrue(board.movePiece("F7", "F6"));
        assertTrue(board.movePiece("C7", "D7"));
        assertTrue(board.movePiece("E8", "F7"));
        assertTrue(board.movePiece("D7", "B7"));
        assertTrue(board.movePiece("D8", "D3"));
        assertTrue(board.movePiece("B7", "B8"));
        assertTrue(board.movePiece("D3", "H7"));
        assertTrue(board.movePiece("B8", "C8"));
        assertTrue(board.movePiece("F7", "G6"));
        assertTrue(board.movePiece("C8", "E6"));
        assertTrue(board.isStalemate());
    }

    @Test
    public void testBasicCastling() {
        Board board = new Board();
        assertTrue(board.movePiece("E2", "E4"));
        assertTrue(board.movePiece("E7", "E5"));
        assertTrue(board.movePiece("F1", "C4"));
        assertTrue(board.movePiece("F8", "C5"));
        assertTrue(board.movePiece("G1", "F3"));
        assertTrue(board.movePiece("G8", "F6"));
        assertTrue(board.movePiece("E1", "G1")); //a legal castle right
        assertTrue(board.movePiece("E8", "G8")); //another legal castle
    }

    @Test
    public void testCastlingLeftAndRight() {
        Board board = new Board();
        assertTrue(board.movePiece("E2", "E4"));
        assertTrue(board.movePiece("D7", "D5"));
        assertTrue(board.movePiece("F1", "D3"));
        assertTrue(board.movePiece("C8", "E6"));
        assertTrue(board.movePiece("G1", "H3"));
        assertTrue(board.movePiece("D8", "D7"));
        assertTrue(board.movePiece("E1", "G1"));
        assertTrue(board.movePiece("B8", "C6"));
        assertTrue(board.movePiece("F1", "E1"));
        assertTrue(board.movePiece("E8", "C8"));
    }

    @Test
    public void testBasicIllegalCastle() {
        Board board = new Board();
        assertTrue(board.movePiece("E2", "E4"));
        assertTrue(board.movePiece("E7", "E5"));
        assertTrue(board.movePiece("F1", "A6"));
        assertTrue(board.movePiece("F8", "C5"));
        assertTrue(board.movePiece("G1", "H3"));
        assertTrue(board.movePiece("C5", "D4"));
        assertTrue(board.movePiece("F2", "F4"));
        assertTrue(board.movePiece("B7", "B6"));
        assertFalse(board.movePiece("E1", "G1")); //an illegal castle
    }

    @Test
    public void testFullGame() { //Henrique Mecking vs Jan Hein Donner... a surrender
        Board board = new Board();
        assertTrue(board.movePiece("D2", "D4"));
        assertTrue(board.movePiece("G8", "F6"));
        assertTrue(board.movePiece("C2", "C4"));
        assertTrue(board.movePiece("E7", "E6"));
        assertTrue(board.movePiece("B1", "C3"));
        assertTrue(board.movePiece("F8", "B4"));
        assertTrue(board.movePiece("E2", "E3"));
        assertTrue(board.movePiece("E8", "G8"));
        assertTrue(board.movePiece("F1", "D3"));
        assertTrue(board.movePiece("D7", "D5"));
        assertTrue(board.movePiece("G1", "F3"));
        assertTrue(board.movePiece("B7", "B6"));
        assertTrue(board.movePiece("A2", "A3"));
        assertTrue(board.movePiece("B4", "E7"));
        assertTrue(board.movePiece("C4", "D5"));
        assertTrue(board.movePiece("E6", "D5"));
        assertTrue(board.movePiece("B2", "B4"));
        assertTrue(board.movePiece("C7", "C5"));
        assertTrue(board.movePiece("D4", "C5"));
        assertTrue(board.movePiece("B6", "C5"));
        assertTrue(board.movePiece("B4", "C5"));
        assertTrue(board.movePiece("E7", "C5"));
        assertTrue(board.movePiece("E1", "G1"));
        assertTrue(board.movePiece("D8", "E7"));
        assertTrue(board.movePiece("C1", "B2"));
        assertTrue(board.movePiece("B8", "C6"));
        assertTrue(board.movePiece("D1", "A4"));
        assertTrue(board.movePiece("C8", "B7"));
        assertTrue(board.movePiece("F1", "C1"));
        assertTrue(board.movePiece("C5", "D6"));
        assertTrue(board.movePiece("A4", "H4"));
        assertTrue(board.movePiece("C6", "E5"));
        assertTrue(board.movePiece("F3", "E5"));
    }

    @Test
    public void testFullGameCheckmate() { //Donald Byrne vs Robert James Fischer, "the game of the century"
        Board board = new Board();
        assertTrue(board.movePiece("G1", "F3"));
        assertTrue(board.movePiece("G8", "F6"));
        assertTrue(board.movePiece("C2", "C4"));
        assertTrue(board.movePiece("G7", "G6"));
        assertTrue(board.movePiece("B1", "C3"));
        assertTrue(board.movePiece("F8", "G7"));
        assertTrue(board.movePiece("D2", "D4"));
        assertTrue(board.movePiece("E8", "G8"));
        assertTrue(board.movePiece("C1", "F4"));
        assertTrue(board.movePiece("D7", "D5"));
        assertTrue(board.movePiece("D1", "B3"));
        assertTrue(board.movePiece("D5", "C4"));
        assertTrue(board.movePiece("B3", "C4"));
        assertTrue(board.movePiece("C7", "C6"));
        assertTrue(board.movePiece("E2", "E4"));
        assertTrue(board.movePiece("B8", "D7"));
        assertTrue(board.movePiece("A1", "D1"));
        assertTrue(board.movePiece("D7", "B6"));
        assertTrue(board.movePiece("C4", "C5"));
        assertTrue(board.movePiece("C8", "G4"));
        assertTrue(board.movePiece("F4", "G5"));
        assertTrue(board.movePiece("B6", "A4"));
        assertTrue(board.movePiece("C5", "A3"));
        assertTrue(board.movePiece("A4", "C3"));
        assertTrue(board.movePiece("B2", "C3"));
        assertTrue(board.movePiece("F6", "E4"));
        assertTrue(board.movePiece("G5", "E7"));
        assertTrue(board.movePiece("D8", "B6"));
        assertTrue(board.movePiece("F1", "C4"));
        assertTrue(board.movePiece("E4", "C3"));
        assertTrue(board.movePiece("E7", "C5"));
        assertTrue(board.movePiece("F8", "E8"));
        assertTrue(board.movePiece("E1", "F1"));
        assertTrue(board.movePiece("G4", "E6"));
        assertTrue(board.movePiece("C5", "B6"));
        assertTrue(board.movePiece("E6", "C4"));
        assertTrue(board.movePiece("F1", "G1"));
        assertTrue(board.movePiece("C3", "E2"));
        assertTrue(board.movePiece("G1", "F1"));
        assertTrue(board.movePiece("E2", "D4"));
        assertTrue(board.movePiece("F1", "G1"));
        assertTrue(board.movePiece("D4", "E2"));
        assertTrue(board.movePiece("G1", "F1"));
        assertTrue(board.movePiece("E2", "C3"));
        assertTrue(board.movePiece("F1", "G1"));
        assertTrue(board.movePiece("A7", "B6"));
        assertTrue(board.movePiece("A3", "B4"));
        assertTrue(board.movePiece("A8", "A4"));
        assertTrue(board.movePiece("B4", "B6"));
        assertTrue(board.movePiece("C3", "D1"));
        assertTrue(board.movePiece("H2", "H3"));
        assertTrue(board.movePiece("A4", "A2"));
        assertTrue(board.movePiece("G1", "H2"));
        assertTrue(board.movePiece("D1", "F2"));
        assertTrue(board.movePiece("H1", "E1"));
        assertTrue(board.movePiece("E8", "E1"));
        assertTrue(board.movePiece("B6", "D8"));
        assertTrue(board.movePiece("G7", "F8"));
        assertTrue(board.movePiece("F3", "E1"));
        assertTrue(board.movePiece("C4", "D5"));
        assertTrue(board.movePiece("E1", "F3"));
        assertTrue(board.movePiece("F2", "E4"));
        assertTrue(board.movePiece("D8", "B8"));
        assertTrue(board.movePiece("B7", "B5"));
        assertTrue(board.movePiece("H3", "H4"));
        assertTrue(board.movePiece("H7", "H5"));
        assertTrue(board.movePiece("F3", "E5"));
        assertTrue(board.movePiece("G8", "G7"));
        assertTrue(board.movePiece("H2", "G1"));
        assertTrue(board.movePiece("F8", "C5"));
        assertTrue(board.movePiece("G1", "F1"));
        assertTrue(board.movePiece("E4", "G3"));
        assertTrue(board.movePiece("F1", "E1"));
        assertTrue(board.movePiece("C5", "B4"));
        assertTrue(board.movePiece("E1", "D1"));
        assertTrue(board.movePiece("D5", "B3"));
        assertTrue(board.movePiece("D1", "C1"));
        assertTrue(board.movePiece("G3", "E2"));
        assertTrue(board.movePiece("C1", "B1"));
        assertTrue(board.movePiece("E2", "C3"));
        assertTrue(board.movePiece("B1", "C1"));
        assertTrue(board.movePiece("A2", "C2")); //the checkmate
        assertTrue(board.endGame());
    }

    @Test
    public void testPromote() {
        Board board = new Board();
        assertTrue(board.movePiece("B2", "B4"));
        assertTrue(board.movePiece("B7", "B5"));
        assertTrue(board.movePiece("C2", "C4"));
        assertTrue(board.movePiece("B5", "C4"));
        assertTrue(board.movePiece("B4", "B5"));
        assertTrue(board.movePiece("B8", "A6"));
        assertTrue(board.movePiece("B5", "B6"));
        assertTrue(board.movePiece("H7", "H6"));
        assertTrue(board.movePiece("B6", "B7"));
        assertTrue(board.movePiece("H6", "H5"));
        assertTrue(board.movePiece("B7", "B8"));
        assertTrue(board.promoting);
        board.promotePawn("queen");
        assertFalse(board.promoting);
    }

    /**
     * this just to see that inputting moves as specified in README doesn't result
     * in some weirdness~
     * same board ending as testIntricateMate
     */
    @Test
    public void testMain() {
        String[] moves = new String[] {"E2 E4", "E7 E6", "G1 F3", "B8 C6", "F1 C4", "F8 C5",
                                       "B1 C3", "G8 F6", "F3 G5", "F6 E4", "G5 F7", "D8 F6",
                                       "F7 H8", "F6 F2"};
        Main.autoMain(moves);
    }
}
