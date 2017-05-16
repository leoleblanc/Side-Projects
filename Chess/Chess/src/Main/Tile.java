package Main;

/**
 * Created by Me on 5/3/17.
 */
public class Tile {

    private String spot;
    private Piece occupiedPiece;
    private Coordinate coordinate;
    private boolean occupied = false;
    private Piece previousPiece;

    public Tile(String spot, Coordinate coordinate) {
        this.spot = spot;
        this.coordinate = coordinate;
    }

    /**
     * This constructor exists to create 'Memory Tiles' for when move reversals are needed
     * ~legacy code~
     */
    public Tile(String spot, Piece occupiedPiece, Coordinate coordinate, boolean occupied) {
        this.spot = spot;
        this.occupiedPiece = occupiedPiece;
        this.coordinate = coordinate;
        this.occupied = occupied;
    }

    /**
     * This method is used placing/moving pieces
     * */
    public void placePiece(Piece piece) {
        this.previousPiece = this.occupiedPiece;
        this.occupiedPiece = piece;
        occupied = true;
    }

    public String getSpot() {
        return this.spot;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Piece getOccupiedPiece() {
        return this.occupiedPiece;
    }

    public void removePiece() {
        if (this.isOccupied()) {
            this.previousPiece = this.occupiedPiece;
        } else {
            this.previousPiece = null;
        }
        this.occupiedPiece = null;
        this.occupied = false;
    }

    public void reinstatePiece() {
        this.occupiedPiece = this.previousPiece;
        this.occupied = !(this.occupiedPiece == null);
    }


    /**
     * Given a board position, return the coordinates of the tile array*/
    public static Coordinate constructCoordinate(String spot) {
        int firstCoord = 0;
        int secondCoord = Character.getNumericValue(spot.charAt(1))-1;
        char character = spot.charAt(0);
        switch (character) {
            case 'A': {
                break;
            }
            case 'B': {
                firstCoord = 1;
                break;
            }
            case 'C': {
                firstCoord = 2;
                break;
            }
            case 'D': {
                firstCoord = 3;
                break;
            }
            case 'E': {
                firstCoord = 4;
                break;
            }
            case 'F': {
                firstCoord = 5;
                break;
            }
            case 'G': {
                firstCoord = 6;
                break;
            }
            case 'H': {
                firstCoord = 7;
                break;
            }
        }
        return new Coordinate(firstCoord, secondCoord);
    }

    /**
     * boolean for if the promotion was successful*/
    public static boolean promotePawn(Tile tile, Pawn pawn, boolean knight, boolean bishop, boolean queen, boolean rook) {
        String teamName = pawn.getName().substring(2);
        String newKnightName = "NX".concat(teamName);
        String newBishopName = "BX".concat(teamName);
        String newRookName = "RX".concat(teamName);
        String newQueenName = "QX".concat(teamName);

        if (knight) {
            tile.placePiece(new Knight(newKnightName, true));
        } else if (bishop) {
            tile.placePiece(new Bishop(newBishopName, true));
        } else if (rook) {
            tile.placePiece(new Rook(newRookName, true));
        } else if (queen) {
            tile.placePiece(new Queen(newQueenName, true));
        }
        return knight || bishop || rook || queen;

    }
}
