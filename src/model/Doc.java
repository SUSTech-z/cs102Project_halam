package model;

import java.awt.*;
import java.util.ArrayList;

public class Doc {
    private String[] nameList;
    private int playerNumber;
    private ChessBoard chessBoard;
    private Color currentPlayer;
    private Color[] playerColor=new Color[4];
    private String time;
    private boolean isValid = true;

    public Color[] getPlayerColor() {
        return playerColor;
    }
    public void setPlayerColor(Color[] playerColor) {
        this.playerColor = playerColor;
    }
    public void setNameList(String[] nameList) {
        this.nameList = nameList;
    }
    public void setName(int num, String s) {
        nameList[num] = s;
    }
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }
    public void setCurrentPlayer(Color currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setValid(boolean valid) {
        isValid = valid;
    }
    public String[] getNameList() {
        return nameList;
    }
    public String getName(int num) {
        return nameList[num];
    }
    public int getPlayerNumber() {
        return playerNumber;
    }
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
    public Color getCurrentPlayer() {
        return currentPlayer;
    }
    public String getTime() {
        return time;
    }
    public boolean isValid() {
        return isValid;
    }
}
