package Main;

import java.util.ArrayList;

/**
 * Created by Me on 5/3/17.
 */
public class Board {

    private Tile[][] tiles = new Tile[8][8];
    private boolean P1turn = true;
    public boolean whiteKingCheck = false;
    public boolean blackKingCheck = false;
    private String whiteKingSpot = "E1";
    private String blackKingSpot = "E8";
    private boolean checkmate = false;
    private boolean stalemate = false;
    boolean promoting = false;
    Tile promotingTile = null;
    Pawn promotingPawn = null;

    final static String[] spots = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8",
                             "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8",
                             "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8",
                             "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8",
                             "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8",
                             "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8",
                             "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8",
                             "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8"};

    final static String[] pieceNames = {"R1W", "P1W", "P1B", "R1B",
            "N1W", "P2W", "P2B", "N1B",
            "B1W", "P3W", "P3B", "B1B",
            "QXW", "P4W", "P4B", "QXB",
            "KXW", "P5W", "P5B", "KXB",
            "B2W", "P6W", "P6B", "B2B",
            "N2W", "P7W", "P7B", "N2B",
            "R2W", "P8W", "P8B", "R2B",  };

    public Board() {
        makeBoardLayout();
        initializePieces();
    }

    public Tile[][] getAllTiles() {
        return tiles;
    }

    public Tile getTile(int x, int y) {
        if (inBounds(x, y)) {
            return tiles[x][y];
        }
        return null;
    }



    public boolean isP1turn() {
        return P1turn;
    }

    public boolean isWhiteKingCheck() {
        Coordinate coords = Tile.constructCoordinate(whiteKingSpot);
        Tile tile = tiles[coords.x][coords.y];
        King whiteKing = (King) tile.getOccupiedPiece();
        whiteKingCheck = whiteKing.isAttacked(tile, this, whiteKingSpot);
        return whiteKingCheck;
    }

    public boolean isBlackKingCheck() {
        Coordinate coords = Tile.constructCoordinate(blackKingSpot);
        Tile tile = tiles[coords.x][coords.y];
        King blackKing = (King) tile.getOccupiedPiece();
        blackKingCheck = blackKing.isAttacked(tile, this, blackKingSpot);
        return blackKingCheck;
    }

    public void checkWhiteKing() {
        whiteKingCheck = !whiteKingCheck;
    }

    public void checkBlackKing() {
        blackKingCheck = !blackKingCheck;
    }

    public boolean isAnyCheck() {
        return whiteKingCheck || blackKingCheck;
    }

    /**
     * When checking for checkmate, iterate through all pieces of the opposing team to
     * see if *any* move can be made that takes their king out of checkmate.
     * These methods to be called assuming someone is checked
     * This method is to generalize from the other two, since much of the same code is used.
     * Unusable as of now
     * */
    public boolean checkCheckmate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = getTile(i, j);
                if (tile.isOccupied()) {
                    Piece piece = tile.getOccupiedPiece();
                    if (piece.getTeam()) {
                        ArrayList<String> moves = piece.possibleMoves(tile, this, piece);
                        for (String s: moves) {
                            Coordinate coordinate = Tile.constructCoordinate(s);
                            Tile otherTile = tiles[coordinate.x][coordinate.y];
                            changeState(tile, otherTile);
                            if (!isWhiteKingCheck()) {
                                undoChangeState(tile, otherTile);
                                return false;
                            } else {
                                undoChangeState(tile, otherTile);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean whiteKingCheckmate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = getTile(i, j);
                if (tile.isOccupied()) {
                    Piece piece = tile.getOccupiedPiece();
                    if (piece.getTeam()) {
                        ArrayList<String> moves = piece.possibleMoves(tile, this, piece);
                        for (String s: moves) {
                            Coordinate coordinate = Tile.constructCoordinate(s);
                            Tile otherTile = tiles[coordinate.x][coordinate.y];
                            changeState(tile, otherTile);
                            if (!isWhiteKingCheck()) {
                                undoChangeState(tile, otherTile);
                                return false;
                            } else {
                                undoChangeState(tile, otherTile);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean blackKingCheckmate() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile tile = getTile(i, j);
                if (tile.isOccupied()) {
                    Piece piece = tile.getOccupiedPiece();
                    if (!piece.getTeam()) {
                        ArrayList<String> moves = piece.possibleMoves(tile, this, piece);
                        for (String s: moves) {
                            Coordinate coordinate = Tile.constructCoordinate(s);
                            Tile otherTile = tiles[coordinate.x][coordinate.y];
                            changeState(tile, otherTile);
                            if (!isBlackKingCheck()) {
                                undoChangeState(tile, otherTile);
                                return false;
                            } else {
                                undoChangeState(tile, otherTile);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Stalemates occur when it is your turn and you aren't being checked,
     * but it is impossible for you to make a move without placing your king in check
     */
    public boolean isStalemate() {
        boolean res = false;
        if (isAnyCheck()) {
            res = false;
        }
        if (isP1turn()) {
            if (whiteKingCheckmate()) {
                res = true;
            }
        } else {
            if (blackKingCheckmate()) {
                res = true;
            }
        }
        stalemate = res;
        return stalemate;
    }


    public boolean endGame() {
        return checkmate || stalemate;
    }


    public void makeBoardLayout(){
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++, counter++) {
                tiles[i][j] = new Tile(spots[counter], new Coordinate(i, j));
            }
        }
    }

    /**
     * Tediously go through and place each piece where it ought to be~
     * */
    public void initializePieces(){
        tiles[0][0].placePiece(new Rook(pieceNames[0]));
        tiles[0][1].placePiece(new Pawn(pieceNames[1]));
        tiles[0][6].placePiece(new Pawn(pieceNames[2]));
        tiles[0][7].placePiece(new Rook(pieceNames[3]));
        tiles[1][0].placePiece(new Knight(pieceNames[4]));
        tiles[1][1].placePiece(new Pawn(pieceNames[5]));
        tiles[1][6].placePiece(new Pawn(pieceNames[6]));
        tiles[1][7].placePiece(new Knight(pieceNames[7]));
        tiles[2][0].placePiece(new Bishop(pieceNames[8]));
        tiles[2][1].placePiece(new Pawn(pieceNames[9]));
        tiles[2][6].placePiece(new Pawn(pieceNames[10]));
        tiles[2][7].placePiece(new Bishop(pieceNames[11]));
        tiles[3][0].placePiece(new Queen(pieceNames[12]));
        tiles[3][1].placePiece(new Pawn(pieceNames[13]));
        tiles[3][6].placePiece(new Pawn(pieceNames[14]));
        tiles[3][7].placePiece(new Queen(pieceNames[15]));
        tiles[4][0].placePiece(new King(pieceNames[16]));
        tiles[4][1].placePiece(new Pawn(pieceNames[17]));
        tiles[4][6].placePiece(new Pawn(pieceNames[18]));
        tiles[4][7].placePiece(new King(pieceNames[19]));
        tiles[5][0].placePiece(new Bishop(pieceNames[20]));
        tiles[5][1].placePiece(new Pawn(pieceNames[21]));
        tiles[5][6].placePiece(new Pawn(pieceNames[22]));
        tiles[5][7].placePiece(new Bishop(pieceNames[23]));
        tiles[6][0].placePiece(new Knight(pieceNames[24]));
        tiles[6][1].placePiece(new Pawn(pieceNames[25]));
        tiles[6][6].placePiece(new Pawn(pieceNames[26]));
        tiles[6][7].placePiece(new Knight(pieceNames[27]));
        tiles[7][0].placePiece(new Rook(pieceNames[28]));
        tiles[7][1].placePiece(new Pawn(pieceNames[29]));
        tiles[7][6].placePiece(new Pawn(pieceNames[30]));
        tiles[7][7].placePiece(new Rook(pieceNames[31]));
    }

    /**
     * This method is to see the current state of the board
     */
    public String visualizeBoard(){
        String res = "";
        for (int i = 7; i >-1 ; i--) {
            String row = "|";
            for (int j = 0; j < 8; j++) {
                Tile tile = tiles[j][i];
                if (tile.isOccupied()) {
                    row = row.concat(tile.getOccupiedPiece().getName());
                    row = row.concat("|");
                } else {
                    row = row.concat("   |");
                }
            }
            res = res.concat(row);
            res = res.concat("\n");
        }
        System.out.println(res);
        return res;
    }

    /**
     * this method is to help enforce taking turns
     */
    public void turnSwitch() {
        P1turn = !P1turn;
    }

    public void changeState(Tile tile1, Tile tile2) {
        tile2.placePiece(tile1.getOccupiedPiece());
        tile1.removePiece();
        tile2.getOccupiedPiece().setMoved();
        if (tile2.getOccupiedPiece().isKing()) { //keep track of king movements
            String spot = tile2.getSpot();
            if (tile2.getOccupiedPiece().getTeam()) {
                whiteKingSpot = spot;
            } else {
                blackKingSpot = spot;
            }
        }
    }

    public void undoChangeState(Tile tile1, Tile tile2) {
        tile1.placePiece(tile2.getOccupiedPiece());
        tile2.reinstatePiece();
        if (tile1.getOccupiedPiece() != null) {
            tile1.getOccupiedPiece().undoMoved();
            if (tile1.getOccupiedPiece().isKing()) { //keep track of king movements
                String spot = tile1.getSpot();
                if (tile1.getOccupiedPiece().getTeam()) {
                    whiteKingSpot = spot;
                } else {
                    blackKingSpot = spot;
                }
            }
        }
    }

    /**
     * It has already been determined that this player can castle, previously
     * */
    public void castle(Tile kingTile, boolean left) {
        Coordinate coordinate = kingTile.getCoordinate();
        int x = coordinate.x;
        int y = coordinate.y;
        if (left) {
            changeState(getTile(x-2, y), getTile(x+1, y));
        } else {
            changeState(getTile(x+1, y), getTile(x-1, y));
        }
    }

    /**
     * Move a piece on the board with two tiles
     */
    public boolean movePiece(String spot1, String spot2) {
        Coordinate spot1Coords = Tile.constructCoordinate(spot1);
        Coordinate spot2Coords = Tile.constructCoordinate(spot2);
        Tile tile1 = tiles[spot1Coords.x][spot1Coords.y];
        Tile tile2 = tiles[spot2Coords.x][spot2Coords.y];

        boolean castling = false;
        boolean castlingLeft = false;

        if (isValid(tile1, tile2)) {

            if (tile1.getOccupiedPiece().isKing()) {
                char c1 = spot1.charAt(0);
                char c2 = spot2.charAt(0);
                int val = Math.abs(c1 - c2);
                if (val == 2 || val == 3) {
                    castling = true;
                    castlingLeft = c1 >= c2;
                }
            }

            changeState(tile1, tile2);
            if (isP1turn()) {
                if (isWhiteKingCheck()) {
                    undoChangeState(tile1, tile2);
                    return false;
                } else if (isBlackKingCheck()) {
                    System.out.println("Black King has been checked!");
                }
            } else {
                if (isBlackKingCheck()) {
                    undoChangeState(tile1, tile2);
                    return false;
                } else if (isWhiteKingCheck()) {
                    System.out.println("White King has been checked!");
                }
            }

            if (castling) {
                castle(tile2, castlingLeft);
            }

            turnSwitch();
            visualizeBoard();
            if (whiteKingCheck) {
                if (whiteKingCheckmate()) {
                    checkmate = true;
                    System.out.println("Checkmate! Black Wins!");
                } else {
                    whiteKingCheck = true;
                }
            }
            if (blackKingCheck) {
                if (blackKingCheckmate()) {
                    checkmate = true;
                    System.out.println("Checkmate!  White Wins!");
                } else {
                    blackKingCheck = true;
                }
            }
            if (checkmate) {
                endGame();
                return true;
            }
            if (isStalemate()) {
                System.out.println("Stalemate!");
                endGame();
            }

            if (tile2.isOccupied()) {
                if (tile2.getOccupiedPiece().getClass().equals(Pawn.class) && tile2.getSpot().charAt(1) == '8') {
                    promoting = true;
                    promotingTile = tile2;
                    promotingPawn = (Pawn) tile2.getOccupiedPiece();
                }
            }

            return true;
        }
        return false;
    }

    /**
     * Check to see if moving piece from t1 to t2 is possible
     * */
    public boolean isValid(Tile t1, Tile t2) {
        if (!t1.isOccupied()) {
            return false;
        }
        if (t1.getOccupiedPiece().getName().charAt(2) == 'W' && !P1turn) {
            return false;
        }
        if (t1.getOccupiedPiece().getName().charAt(2) == 'B' && P1turn) {
            return false;
        }
        ArrayList<String> moves = t1.getOccupiedPiece().possibleMoves(t1, this, t1.getOccupiedPiece());
        return moves.contains(t2.getSpot());
    }

    public static boolean inBounds(int x, int y) {
        return x > -1 && x < 8 && y > -1 && y < 8;
    }

    public boolean promotePawn(String str) {
        boolean knight = false;
        boolean bishop = false;
        boolean queen = false;
        boolean rook = false;

        str = str.toUpperCase();

        if (str.equals("KNIGHT")) {
            knight = true;
        } else if (str.equals("BISHOP")) {
            bishop = true;
        } else if (str.equals("QUEEN")) {
            queen = true;
        } else if (str.equals("ROOK")) {
            rook = true;
        }
        boolean success = Tile.promotePawn(promotingTile, promotingPawn, knight, bishop, queen, rook);
        if (success) {
            promoting = false;
            promotingTile = null;
            promotingPawn = null;
            visualizeBoard();
        }
        return promoting;
    }
}
