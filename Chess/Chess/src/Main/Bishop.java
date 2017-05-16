package Main;

import java.util.ArrayList;

/**
 * Created by Me on 5/3/17.
 */
public class Bishop extends Piece {
    public Bishop(String name) {
        super(name);
    }

    public Bishop(String name, boolean promoted) {
        super(name, promoted);
    }

    @Override
    public ArrayList<String> possibleMoves(Tile tile, Board board, Piece piece) {
        Coordinate coordinate = tile.getCoordinate();
        int x = coordinate.x;
        int y = coordinate.y;
        ArrayList<String> moves = new ArrayList<String>();

        for (int i = 1; i < 8; i++) {
            if (!Board.inBounds(x+i, y+i)) {
                break;
            }
            tile = board.getTile(x+i, y+i);
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

        for (int i = 1; i < 8; i++) {
            if (!Board.inBounds(x-i, y-i)) {
                break;
            }
            tile = board.getTile(x-i, y-i);
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

        for (int i = 1; i < 8; i++) {
            if (!Board.inBounds(x+i, y-i)) {
                break;
            }
            tile = board.getTile(x+i, y-i);
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

        for (int i = 1; i < 8; i++) {
            if (!Board.inBounds(x-i, y+i)) {
                break;
            }
            tile = board.getTile(x-i, y+i);
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
