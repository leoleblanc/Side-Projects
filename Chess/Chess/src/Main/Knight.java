package Main;

import java.util.ArrayList;

/**
 * Created by Me on 5/3/17.
 */
public class Knight extends Piece {

    public Knight(String name) {
        super(name);
    }

    public Knight(String name, boolean promoted) {
        super(name, promoted);
    }

    @Override
    public ArrayList<String> possibleMoves(Tile tile, Board board, Piece piece) {
        Coordinate coordinate = tile.getCoordinate();
        int x = coordinate.x;
        int y = coordinate.y;
        ArrayList<String> moves = new ArrayList<String>();

        if (Board.inBounds(x-1, y-2)) {
            tile = board.getTile(x-1, y-2);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
        }
        if (Board.inBounds(x-2, y-1)) {
            tile = board.getTile(x-2, y-1);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
        }
        if (Board.inBounds(x-2, y+1)) {
            tile = board.getTile(x-2, y+1);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
        }
        if (Board.inBounds(x-1, y+2)) {
            tile = board.getTile(x-1, y+2);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
        }
        if (Board.inBounds(x+1, y-2)) {
            tile = board.getTile(x+1, y-2);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
        }
        if (Board.inBounds(x+2, y-1)) {
            tile = board.getTile(x+2, y-1);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
        }
        if (Board.inBounds(x+2, y+1)) {
            tile = board.getTile(x+2, y+1);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
        }
        if (Board.inBounds(x+1, y+2)) {
            tile = board.getTile(x+1, y+2);
            if (!tile.isOccupied() || Piece.canCapture(piece, tile.getOccupiedPiece())) {
                moves.add(tile.getSpot());
                if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                    attackSame = true;
                }
            }
        }
        return moves;
    }
}
