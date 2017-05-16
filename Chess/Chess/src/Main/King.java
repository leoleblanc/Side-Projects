package Main;

import java.util.ArrayList;

/**
 * Created by Me on 5/3/17.
 */
public class King extends Piece{

    boolean checkingAttacked = false;
    public King(String name) {
        super(name);
        king = true;
    }

    /**
     * for consistency
     */
    public King(String name, boolean promoted) {
        super(name, promoted);
        king = true;
    }

    @Override
    public ArrayList<String> possibleMoves(Tile tile, Board board, Piece piece) {
        Coordinate coordinate = tile.getCoordinate();
        int x = coordinate.x;
        int y = coordinate.y;
        ArrayList<String> moves = new ArrayList<String>();

        Tile dummyTile;
        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                if (!Board.inBounds(i, j)) {
                    continue;
                }
                dummyTile = board.getTile(i, j);
                if (!dummyTile.isOccupied() || Piece.canCapture(piece, dummyTile.getOccupiedPiece())) {
                    moves.add(dummyTile.getSpot());
                    if (dummyTile.isOccupied() && piece.getClass().equals(dummyTile.getOccupiedPiece().getClass())) {
                        attackSame = true;
                    }
                }
            }
        }

        if (!checkingAttacked && canCastleLeft(tile, board, piece)) {
            moves.add(board.getTile(x-2, y).getSpot());
        }
        if (!checkingAttacked && canCastleRight(tile, board, piece)) {
            moves.add(board.getTile(x+2, y).getSpot());
        }

        return moves;
    }

    /**
     * This method to be called after a move is made, to check that the king is safe
     * If false, a king is in check
     * If false and the player just tried to move one of their pieces, it is an invalid move!
     * */
    public boolean isAttacked(Tile tile, Board board, String kingSpot) {
        Pawn pawn = new Pawn(this.name);
        Rook rook = new Rook(this.name);
        Bishop bishop = new Bishop(this.name);
        Knight knight = new Knight(this.name);
        Queen queen = new Queen(this.name);
        King king = new King(this.name);
        king.checkingAttacked = true;

        pawn.possibleMoves(tile, board, pawn);
        rook.possibleMoves(tile, board, rook);
        bishop.possibleMoves(tile, board, bishop);
        knight.possibleMoves(tile, board, knight);
        queen.possibleMoves(tile, board, queen);
        king.possibleMoves(tile, board, king);

        return pawn.attackSame || rook.attackSame || bishop.attackSame ||
                knight.attackSame || queen.attackSame || king.attackSame;
    }

    /**
     * Castling logic, for when core functionality is done
     * */

    public boolean canCastleLeft(Tile tile, Board board, Piece piece) {
        if (board.isP1turn() && board.whiteKingCheck) {
            return false;
        }
        if (!board.isP1turn() && board.blackKingCheck) {
            return false;
        }
        if (this.hasMoved()) {
            return false;
        }
        Coordinate coordinate = tile.getCoordinate();
        int x = coordinate.x;
        int y = coordinate.y;
        Tile rookTile = board.getTile(x-4, y);
        if ((rookTile == null) || !rookTile.isOccupied() || rookTile.getOccupiedPiece().hasMoved()) {
            return false;
        }

        for (int i = x-1; i > x - 4; i--) {
            tile = board.getTile(i, y);
            if (tile.isOccupied() || isAttacked(tile, board, " ")) {
                return false;
            }
        }
        return true;
    }

    public boolean canCastleRight(Tile tile, Board board, Piece piece) {
        if (board.isP1turn() && board.whiteKingCheck) {
            return false;
        }
        if (!board.isP1turn() && board.blackKingCheck) {
            return false;
        }
        if (this.hasMoved()) {
            return false;
        }
        Coordinate coordinate = tile.getCoordinate();
        int x = coordinate.x;
        int y = coordinate.y;
        Tile rookTile = board.getTile(x+3, y);
        if ( (rookTile == null) || !rookTile.isOccupied() || rookTile.getOccupiedPiece().hasMoved()) {
            return false;
        }

        for (int i = x+1; i < x + 3; i++) {
            tile = board.getTile(i, y);
            if (tile.isOccupied() || isAttacked(tile, board, " ")) {
                return false;
            }
        }
        return true;
    }
}
