package Main;

import java.util.ArrayList;

/**
 * Created by Me on 5/3/17.
 */
public class Rook extends Piece {

    public Rook(String name) {
        super(name);
    }

    public Rook(String name, boolean promoted) {
        super(name, promoted);
    }

    @Override
    public ArrayList<String> possibleMoves(Tile tile, Board board, Piece piece) {
        Coordinate coordinate = tile.getCoordinate();
        int x = coordinate.x;
        int y = coordinate.y;
        ArrayList<String> moves = new ArrayList<String>();

        for (int i = x+1; i < 8; i++) {
            if (!Board.inBounds(i, y)) {
                break;
            }
            tile = board.getTile(i, y);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
            if (tile.isOccupied()) {
                break;
            }
        }
        for (int i = x-1; i > -1; i--) {
            if (!Board.inBounds(i, y)) {
                break;
            }
            tile = board.getTile(i, y);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
            if (tile.isOccupied()) {
                break;
            }
        }
        for (int i = y+1; i < 8; i++) {
            if (!Board.inBounds(x, i)) {
                break;
            }
            tile = board.getTile(x, i);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
            if (tile.isOccupied()) {
                break;
            }
        }
        for (int i = y-1; i > -1; i--) {
            if (!Board.inBounds(x, i)) {
                break;
            }
            tile = board.getTile(x, i);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
            if (tile.isOccupied()) {
                break;
            }
        }
        return moves;
    }
}
