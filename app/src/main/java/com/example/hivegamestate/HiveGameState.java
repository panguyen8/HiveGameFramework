package com.example.hivegamestate;
/*
Remove static variables

 */

public class HiveGameState {
    static final int BLACK_TURN = 0;

    //Stephen added this instance vars, delete if needed
    static final int WHITE_TURN = 1;

    //holds a 2d array of strings for what the board would look like
    //using a 2d array for now, idk if we will used a linked list later on
    //once we get the class for the pieces set up, we can change string to that new class
    private Piece[][] board = new Piece[100][100];

    //holds the value for how many pieces the white and black player have in their hand (not on board)
    private int wBee, wSpider, wAnt, wBeetle, wGrasshopper;
    private int bBee, bSpider, bAnt, bBeetle, bGrasshopper;

    //int variable to tell whose turn it is
    //If 1, white moves, if 2, black moves
    //Removed static for now, not sure if it's needed
    //Let me (Stephen) know if it is
    private int turn = 1;  // Edit by Samuel Nguyen

    //Represents how many total pieces each player has
    private int player1Pieces;
    private int player2Pieces;

    // Creates pieces
    Piece wBee1 = new Piece("QueenBee");
    Piece bBee1 = new Piece("QueenBee");

    Piece wAnt1 = new Piece("Ant");
    Piece wAnt2 = new Piece("Ant");
    Piece wAnt3 = new Piece("Ant");
    Piece bAnt1 = new Piece("Ant");
    Piece bAnt2 = new Piece("Ant");
    Piece bAnt3 = new Piece("Ant");

    Piece wGHopper1 = new Piece("Grasshopper");
    Piece wGHopper2 = new Piece("Grasshopper");
    Piece wGHopper3 = new Piece("Grasshopper");
    Piece bGHopper1 = new Piece("Grasshopper");
    Piece bGHopper2 = new Piece("Grasshopper");
    Piece bGHopper3 = new Piece("Grasshopper");

    Piece wSpider1 = new Piece("Spider");
    Piece wSpider2 = new Piece("Spider");
    Piece bSpider1 = new Piece("Spider");
    Piece bSpider2 = new Piece("Spider");

    Piece wBeetle1 = new Piece("Beetle");
    Piece wBeetle2 = new Piece("Beetle");
    Piece bBeetle1 = new Piece("Beetle");
    Piece bBeetle2 = new Piece("Beetle");

    //Basic constructor
    public HiveGameState() {
        this.wBee = 1;
        this.bBee = 1;
        this.wGrasshopper = 3;
        this.bGrasshopper = 3;
        this.wAnt = 3;
        this.bAnt = 3;
        this.wBeetle = 2;
        this.bBeetle = 2;
        this.wSpider = 2;
        this.bSpider = 2;
    }

    //Copy constructor (Stephen)
    public HiveGameState(HiveGameState hgs) {
        hgs.turn = this.turn;
        this.wBee = hgs.wBee;
        this.bBee = hgs.bBee;
        this.wGrasshopper = hgs.wGrasshopper;
        this.bGrasshopper = hgs.bGrasshopper;
        this.wAnt = hgs.wAnt;
        this.bAnt = hgs.bAnt;
        this.wBeetle = hgs.wBeetle;
        this.bBeetle = hgs.bBeetle;
        this.wSpider = hgs.wSpider;
        this.bSpider = hgs.bSpider;

        //Copies each board index/cell
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                this.board[i][j] = hgs.board[i][j];
            }
        }
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

    // Following methods started by Samuel Nguyen
    // Feel free to edit these (if so, delete this comment)

    /**
     * Places a piece on the board, subtracts one from total piece count and appropriate piece count,
     * and sets the turn to that of the other player.
     *
     * @param id: the id of whose turn it is
     * @return true if successful, false otherwise
     */
    boolean placePiece(int id, Piece piece, int boardX, int boardY) {
        if (id == WHITE_TURN) {
            // Checks piece being placed
            if(piece.getType().equals("QueenBee")) {
                this.wBee--;
            }
            else if(piece.getType().equals("Spider")) {
                this.wSpider--;
            }
            else if(piece.getType().equals("Ant")) {
                this.wAnt--;
            }
            else if(piece.getType().equals("Beetle")) {
                this.wBeetle--;
            }
            else if(piece.getType().equals("Grasshopper")) {
                this.wGrasshopper--;
            }
            this.player1Pieces--;
            board[boardX][boardY] = piece;
            this.setTurn(BLACK_TURN);
            return true;
        }
        else if (id == BLACK_TURN) {
            // Checks piece being placed
            if(piece.getType().equals("QueenBee")) {
                this.bBee--;
            }
            else if(piece.getType().equals("Spider")) {
                this.bSpider--;
            }
            else if(piece.getType().equals("Ant")) {
                this.bAnt--;
            }
            else if(piece.getType().equals("Beetle")) {
                this.bBeetle--;
            }
            else if(piece.getType().equals("Grasshopper")) {
                this.bGrasshopper--;
            }
            this.player2Pieces--;
            board[boardX][boardY] = piece;
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
    boolean movePiece(int id, Piece piece, int startX, int startY, int newX, int newY) {
        if(id == WHITE_TURN) {
            if(piece.getType().equals("QueenBee")) {

            }
            else if(piece.getType().equals("Spider")) {

            }
            else if(piece.getType().equals("Ant")) {

            }
            else if(piece.getType().equals("Beetle")) {

            }
            else if(piece.getType().equals("Grasshopper")) {

            }
            this.setTurn(BLACK_TURN);
            return true;
        }
        else if(id == BLACK_TURN) {
            if(piece.getType().equals("QueenBee")) {

            }
            else if(piece.getType().equals("Spider")) {

            }
            else if(piece.getType().equals("Ant")) {

            }
            else if(piece.getType().equals("Beetle")) {

            }
            else if(piece.getType().equals("Grasshopper")) {

            }
            this.setTurn(WHITE_TURN);
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
