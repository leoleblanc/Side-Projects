package Main;

import java.util.ArrayList;

/**
 * Created by Me on 5/3/17.
 */
public abstract class Piece {

    protected boolean king = false;
    protected String name;
    protected boolean team;
    protected boolean moved;
    protected boolean attackSame = false; //this only exists, for now, for the King piece for checks
    private int numMoves = 0;

    public Piece(String name) {
        this.name = name;
        moved = false;
        if (name.charAt(2) == 'W') {
            team = true;
        } else {
            team = false;
        }
    }

    public Piece(String name, boolean promoted) {
        this.name = name;
        moved = true;
        if (name.charAt(2) == 'W') {
            team = true;
        } else {
            team = false;
        }
    }

    public String getName() {
        return this.name;
    }

    public boolean getTeam() {
        return this.team;
    }

    public boolean hasMoved() {
        return this.moved;
    }

    public void setMoved() {
        this.numMoves++;
        this.moved = true;
    }

    public void undoMoved() {
        this.numMoves--;
        if (this.numMoves == 0) {
            this.moved = false;
        }
    }

    public boolean isKing() {
        return this.king;
    }

    public static boolean canCapture(Piece p1, Piece p2) {
        return p1.getTeam() != p2.getTeam();
    }


    /**
     * This method is for determining a piece's possible moves given its tile
     * and current board state.
     * Also to be used to check for if a move can be made given this piece.
     * Can additionally be used to see which tiles this piece defends/attacks.
     * Last parameter only for queen usage
     * */
    public abstract ArrayList<String> possibleMoves(Tile tile, Board board, Piece piece);
}
