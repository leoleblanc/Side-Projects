package Main;

import java.util.ArrayList;

/**
 * Created by Me on 5/3/17.
 */
public class Pawn extends Piece{

    public Pawn(String name) {
        super(name);
    }

    /**
     * A nonsensical constructor for consistency purposes only*/
    public Pawn(String name, boolean promoted) {
        super(name, promoted);
    }

    @Override
    public ArrayList<String> possibleMoves(Tile tile, Board board, Piece piece) {
        Coordinate coordinate = tile.getCoordinate();
        int xCoord = coordinate.x;
        int yCoord = coordinate.y;
        ArrayList<String> moves = new ArrayList<String>();

        if (team) {
            return whiteMoves(board, xCoord, yCoord, moves, piece);
        }
        return blackMoves(board, xCoord, yCoord, moves, piece);
    }

    private ArrayList<String> blackMoves(Board board, int x, int y, ArrayList<String> moves, Piece piece) {
        if (y - 1 < 0) {
            return moves;
        } else {
            Tile tile;
            if (x != 7) { //check right side
                if (Board.inBounds(x+1, y-1)) {
                    tile = board.getTile(x+1, y-1);
                    if (tile.isOccupied() && Piece.canCapture(piece, tile.getOccupiedPiece())) {
                        moves.add(tile.getSpot());
                        if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                            attackSame = true;
                        }
                    }
                }
            }
            if (x != 0) { //check left side
                if (Board.inBounds(x-1, y-1)) {
                    tile = board.getTile(x-1, y-1);
                    if (tile.isOccupied() && Piece.canCapture(piece, tile.getOccupiedPiece())) {
                        moves.add(tile.getSpot());
                        if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                            attackSame = true;
                        }
                    }
                }
            }
            for (int i = y-1; i > y-3 && Board.inBounds(x, i); i--) {
                tile = board.getTile(x, i);
                if (tile.isOccupied()) {
                    break;
                } else {
                    if (i == y-2 && moved) {
                        break;
                    } else {
                        moves.add(tile.getSpot());
                    }
                }
            }
        }
        return moves;
    }

    private ArrayList<String> whiteMoves(Board board, int x, int y, ArrayList<String> moves, Piece piece) {
        if (y + 1 > 7) {
            return moves;
        } else {
            Tile tile;
            if (x != 7) { //check right side
                if (Board.inBounds(x+1, y+1)) {
                    tile = board.getTile(x+1, y+1);
                    if (tile.isOccupied() && Piece.canCapture(piece, tile.getOccupiedPiece())) {
                        moves.add(tile.getSpot());
                        if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                            attackSame = true;
                        }
                    }
                }
            }
            if (x != 0) { //check left side
                if (Board.inBounds(x-1, y+1)) {
                    tile = board.getTile(x-1, y+1);
                    if (tile.isOccupied() && Piece.canCapture(piece, tile.getOccupiedPiece())) {
                        moves.add(tile.getSpot());
                        if (tile.isOccupied() && piece.getClass().equals(tile.getOccupiedPiece().getClass())) {
                            attackSame = true;
                        }
                    }
                }
            }
            for (int i = y+1; i < y+3 && Board.inBounds(x, i); i++) {
                tile = board.getTile(x, i);
                if (tile.isOccupied()) {
                    break;
                } else {
                    if (i == y+2 && moved) {
                        break;
                    } else {
                        moves.add(tile.getSpot());
                    }
                }
            }
        }
        return moves;
    }
}
