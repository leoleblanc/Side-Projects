package Main;

import java.util.ArrayList;

/**
 * Created by Me on 5/3/17.
 */
public class Queen extends Piece {
    public Queen(String name) {
        super(name);
    }

    public Queen(String name, boolean promoted) {
        super(name, promoted);
    }

    @Override
    public ArrayList<String> possibleMoves(Tile tile, Board board, Piece piece) {
        Rook rook = new Rook(this.name);
        Bishop bishop = new Bishop(this.name);


        ArrayList<String> moves = rook.possibleMoves(tile, board, piece);
        moves.addAll(bishop.possibleMoves(tile, board, piece));
        attackSame = rook.attackSame || bishop.attackSame;
        return moves;
    }
}
