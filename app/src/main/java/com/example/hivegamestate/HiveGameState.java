package com.example.hivegamestate;

public class HiveGameState {
    static final int BLACK_TURN = 0;

    //Stephen added this instance vars, delete if needed
    static final int WHITE_TURN = 1;

    //holds a 2d array of strings for what the board would look like
    //using a 2d array for now, idk if we will used a linked list later on
    //once we get the class for the pieces set up, we can change string to that new class
    static String[][] board = new String[100][100];

    //holds the value for how many pieces the white and black player have in their hand (not on board)
    static int wBee, wSpider, wAnt, wBeetle, wGrasshopper;
    static int bBee, bSpider, bAnt, bBeetle, bGrasshopper;

    //int variable to tell whose turn it is
    //If 1, white moves, if 2, black moves
    //Removed static for now, not sure if it's needed
    //Let me (Stephen) know if it is
    private int turn = 1;  // Edit by Samuel Nguyen

    //Represents how many total pieces each player has
    private int player1Pieces;
    private int player2Pieces;

    //How many of each piece each player has
    private int whiteBees;
    private int blackBees;
    private int whiteSpiders;
    private int blackSpiders;
    private int whiteAnts;
    private int blackAnts;
    private int whiteBeetles;

    //Basic constructor
    private int blackBeetles;
    private int whiteGHoppers;
    private int blackGHoppers;

    public HiveGameState() {
        this.turn = WHITE_TURN; // White goes first?
        this.player1Pieces = 11;
        this.player2Pieces = 11;
        this.whiteBees = 1;
        this.blackBees = 1;
        this.whiteSpiders = 2;
        this.blackSpiders = 2;
        this.whiteBeetles = 2;
        this.blackBeetles = 2;
        this.whiteAnts = 3;
        this.blackAnts = 3;
        this.whiteGHoppers = 3;
        this.blackGHoppers = 3;

    }

    //Copy constructor (Stephen)
    public HiveGameState(HiveGameState hgs) {
        hgs.turn = this.turn;
        hgs.board = this.board;
        hgs.player1Pieces = this.player1Pieces;
        hgs.player2Pieces = this.player2Pieces;
        hgs.whiteBees = this.whiteBees;
        hgs.blackBees = this.blackBees;
        hgs.whiteSpiders = this.whiteSpiders;
        hgs.blackSpiders = this.blackSpiders;
        hgs.whiteBeetles = this.whiteBeetles;
        hgs.blackBeetles = this.blackBeetles;
        hgs.whiteAnts = this.whiteAnts;
        hgs.blackAnts = this.blackAnts;
        hgs.whiteGHoppers = this.whiteGHoppers;
        hgs.blackGHoppers = this.blackGHoppers;
    }

    //Returns a formatted string that describes the game's state
    @Override
    public String toString() {
        return "Turn: " + turn + "/n" +
                "BLACK_TURN: " + BLACK_TURN + "/n" +
                "WHITE_TURN: " + WHITE_TURN + "/n" +
                "White Bee: " + wBee + "/n" +
                "White Spider: " + wSpider + "/n" +
                "White Ant: " + wAnt + "/n" +
                "White Beetle: " + wBeetle + "/n" +
                "White Grasshopper: " + wGrasshopper + "/n" +
                "Black Bee: " + bBee + "/n" +
                "Black Spider: " + bSpider + "/n" +
                "Black Ant: " + bAnt + "/n" +
                "Black Beetle: " + bBeetle + "/n" +
                "Black Grasshopper: " + bGrasshopper + "/n" +
                board;
    }

    // Following methods done by Samuel Nguyen
    // Feel free to edit these (if so, delete this comment)

    /**
     * Places a piece on the board, subtracts one from total piece count and appropriate piece count,
     * and sets the turn to that of the other player.
     *
     * @param id: the id of whose turn it is
     * @return true if successful, false otherwise
     */
    boolean placePiece(int id, Piece piece) {
        if (id == WHITE_TURN) {
            // Checks piece being placed
            if(piece instanceof QueenBee) {
                this.whiteBees--;
            }
            else if(piece instanceof Spider) {
                this.whiteSpiders--;
            }
            else if(piece instanceof Ant) {
                this.whiteAnts--;
            }
            else if(piece instanceof Beetle) {
                this.whiteAnts--;
            }
            else if(piece instanceof Grasshopper) {
                this.whiteGHoppers--;
            }
            this.player1Pieces--;
            this.setTurn(BLACK_TURN);
            return true;
        }
        else if (id == BLACK_TURN) {
            // Checks piece being placed
            if(piece instanceof QueenBee) {
                this.blackBees--;
            }
            else if(piece instanceof Spider) {
                this.blackSpiders--;
            }
            else if(piece instanceof Ant) {
                this.blackAnts--;
            }
            else if(piece instanceof Beetle) {
                this.blackBeetles--;
            }
            else if(piece instanceof Grasshopper) {
                this.blackGHoppers--;
            }
            this.player2Pieces--;
            this.setTurn(WHITE_TURN);
            return true;
        }
        return false;
    }

    /**
     * Moves a piece on the board and sets the turn to that of the other player.
     *
     * @param id: the id of whose turn it is
     * @return true if successful, false otherwise
     */
    boolean movePiece(int id) {
        if(id == WHITE_TURN) {
            return true;
        }
        else if(id == BLACK_TURN) {
            return true;
        }
        return false;
    }

    // Not sure if we're implementing this, go ahead and delete if so
    boolean undo(int id) {
        if (id == WHITE_TURN || id == BLACK_TURN) {
            return true;
        }
        return false;
    }

    /**
     * Quits the game.
     *
     * @param id: the id of whose turn it is
     * @return true if successful, false otherwise
     */
    boolean quit(int id) {
        if(id == WHITE_TURN || id == BLACK_TURN) {
            // Quit method here
            return true;
        }
        return false;
    }

    /**
     * Zooms in or out on the screen.
     *
     * @param id: the id of whose turn it is
     * @return true if successful, false otherwise
     */
    boolean zoom(int id) {
        if (id == WHITE_TURN || id == BLACK_TURN) {
            // Zoom method here
            return true;
        }
        return false;
    }

    int getTurn() {
        return this.turn;
    }

    void setTurn(int id) {
        this.turn = id;
    }
}
