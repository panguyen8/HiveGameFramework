/*
*
*
*
*
 */

package com.example.hivegamestate;
/*
Remove static variables

 */

import android.net.wifi.p2p.WifiP2pGroup;

import java.util.ArrayList;

public class HiveGameState {
    final int BLACK_TURN = 0;

    //Stephen added this instance vars, delete if needed
    final int WHITE_TURN = 1;

    //using a 2d array for now, idk if we will used a linked list later on
    //once we get the class for the pieces set up, we can change string to that new class
    private piece[][] board = new piece[100][100];

    //int variable to tell whose turn it is
    //If 1, white moves, if 0, black moves
    //Removed static for now, not sure if it's needed
    //Let me (Stephen) know if it is
    private int turn = 1;  // Edit by Samuel Nguyen

    //Represents how many total pieces each player has
    private int player1Pieces;
    private int player2Pieces;

    public ArrayList<piece> bugList = new ArrayList<>();
    enum piece {
        BBEE, BSPIDER, BANT, BGHOPPER, BBEETLE, WBEE, WSPIDER, WANT, WGHOPPER, WBEETLE;
    }


    //Basic constructor
    public HiveGameState() {

        //1 BBEE, 2 BSPIDERS, 3 BANT, 3 BGHOPPER, 2,BBEETLE
        bugList.add(piece.BBEE);
        bugList.add(piece.BSPIDER);
        bugList.add(piece.BSPIDER);
        bugList.add(piece.BANT);
        bugList.add(piece.BANT);
        bugList.add(piece.BANT);
        bugList.add(piece.BGHOPPER);
        bugList.add(piece.BGHOPPER);
        bugList.add(piece.BGHOPPER);
        bugList.add(piece.BBEETLE);
        bugList.add(piece.BBEETLE);

        //1 BBEE, 2 WSPIDERS, 3 WANT, 3 WGHOPPER, 2,WBEETLE
        bugList.add(piece.WBEE);
        bugList.add(piece.WSPIDER);
        bugList.add(piece.WSPIDER);
        bugList.add(piece.WANT);
        bugList.add(piece.WANT);
        bugList.add(piece.WANT);
        bugList.add(piece.WGHOPPER);
        bugList.add(piece.WGHOPPER);
        bugList.add(piece.WGHOPPER);
        bugList.add(piece.WBEETLE);
        bugList.add(piece.WBEETLE);

        this.turn = WHITE_TURN; // White goes first?
        this.player1Pieces = 11;
        this.player2Pieces = 11;

    }

    //Copy constructor (Stephen)
    public HiveGameState(HiveGameState hgs) {
        hgs.turn = this.turn;


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
                "White Bee: " + piece.WBEE + "/n" +
                "White Spider: " + piece.WSPIDER + "/n" +
                "White Ant: " + piece.WANT + "/n" +
                "White Beetle: " + piece.WBEETLE + "/n" +
                "White Grasshopper: " + piece.WGHOPPER + "/n" +
                "Black Bee: " + piece.BBEE + "/n" +
                "Black Spider: " + piece.BSPIDER + "/n" +
                "Black Ant: " + piece.BANT + "/n" +
                "Black Beetle: " + piece.BBEETLE + "/n" +
                "Black Grasshopper: " + piece.BGHOPPER + "/n" +
                board;
    }

    // Following methods started by Samuel Nguyen

    /**
     * Places a piece on the board, subtracts one from total piece count and appropriate piece count,
     * and sets the turn to that of the other player.
     *
     * @param id: the id of whose turn it is
     * @param newPiece: the piece being moved
     * @param boardX: row index of where the piece is being placed on board
     * @param boardY: col index
     *
     * @return true if successful, false otherwise
     */

    // Below citation by Samuel Nguyen
    /**
     * External Citation
     * Date: October 10, 2019
     * Problem: I got "'equals()' between objects of inconvertible types 'piece' and 'String'".
     * Resource: https://developer.android.com/reference/java/lang/Enum
     * Solution: I used toString().
     */
    boolean placePiece(int id, piece newPiece, int boardX, int boardY) {
        if (id == WHITE_TURN) {
            // Checks piece being placed
            if(newPiece.toString().equals("WBEE")) {
                bugList.remove(HiveGameState.piece.WBEE);
            }
            else if(newPiece.toString().equals("WSPIDER")) {
                bugList.remove(HiveGameState.piece.WSPIDER);
            }
            else if(newPiece.toString().equals("WANT")) {
                bugList.remove(HiveGameState.piece.WANT);
            }
            else if(newPiece.toString().equals("WBEETLE")) {
                bugList.remove(HiveGameState.piece.WBEETLE);
            }
            else if(newPiece.toString().equals("WGHOPPER")) {
                bugList.remove(HiveGameState.piece.WGHOPPER);
            }
            this.player1Pieces--;
            board[boardX][boardY] = newPiece;
            this.setTurn(BLACK_TURN);
            return true;
        }
        else if (id == BLACK_TURN) {
            // Checks piece being placed
            if(newPiece.toString().equals("BBEE")) {
                bugList.remove(HiveGameState.piece.BBEE);
            }
            else if(newPiece.toString().equals("BSPIDER")) {
                bugList.remove(HiveGameState.piece.BSPIDER);
            }
            else if(newPiece.toString().equals("BANT")) {
                bugList.remove(HiveGameState.piece.BANT);
            }
            else if(newPiece.toString().equals("BBEETLE")) {
                bugList.remove(HiveGameState.piece.BBEETLE);
            }
            else if(newPiece.toString().equals("BGHOPPER")) {
                bugList.remove(HiveGameState.piece.BGHOPPER);
            }
            this.player2Pieces--;
            board[boardX][boardY] = newPiece;
            this.setTurn(WHITE_TURN);
            return true;
        }
        return false;
    }

    /**
     *Moves a piece on the board and sets the turn to that of the other player.
     *The moving is the same for all pieces.
     *
     * @param id: the id of whose turn it is
     * @param pieceOnBoard: the piece that will be moved
     * @param startX: the piece's starting X position
     * @param startY: the piece's starting Y position
     * @param newX: the piece's new X position
     * @param newY: the piece's new Y position
     * @return true if successful, false otherwise
     */
    boolean movePiece(int id, piece pieceOnBoard, int startX, int startY, int newX, int newY) {
        // Can't move piece to space it's already at
        if(startX == newX && startY == newY) {
            return false;
        }

        // Cannot move to an occupied space (except beetles, not sure how to implement that)
        if(board[newX][newY] != null) {
            return false;
        }

        board[newX][newY] = pieceOnBoard;
        // Must remove what was at starting space
        board[startX][startY] = null;

        //Set next player turn accordingly.
        if(id == WHITE_TURN) {
            this.setTurn(BLACK_TURN);
        }
        else if(id == BLACK_TURN) {
            this.setTurn(WHITE_TURN);
        }

        return true;
    }

    /**
     * Undoes last two moves.
     * This is not fully implemented.
     *
     * @param id: the id of whose turn it is
     * @return true if successful, false otherwise
     */
    boolean undo(int id) {
        if (id == WHITE_TURN || id == BLACK_TURN) {
            return true;
        }
        return false;
    }

    /**
     * Quits the game.
     * This is not fully implemented.
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
     * This is not fully implemented.
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

    /**
     * Gets the id of whoever's move it is
     * @return id of player to move
     */
    int getTurn() {
        return this.turn;
    }

    /**
     * Sets the player to move
     * @param id: id of player that is being set to move
     */
    void setTurn(int id) {
        this.turn = id;
    }
}
