package model;

import controller.GameController;
import listener.Listenable;
import listener.RobotListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RobotPlayer extends Player implements Listenable<RobotListener> {
    private List<RobotListener> listenerList = new ArrayList<>();
    private final String name="RobotPlayer";
    private Color virtualColor;
    private final Color currentColor;
    private int playerNumber;
    private int playerNo;
    private ChessBoardLocation src;
    private ChessBoardLocation dest;
    private int[] score;
    private ChessBoard currentBoard;

    public RobotPlayer(Color player, int playerNumber, int playerNo) {
        this.virtualColor = player;
        this.currentColor = player;
        this.playerNumber = playerNumber;
        this.playerNo = playerNo;
        if (playerNumber==2) score=new int[19]; else score=new int[13];
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    public void setSrc(ChessBoardLocation src) {
        this.src = src;
    }
    public void setDest(ChessBoardLocation dest) {
        this.dest = dest;
    }
    public void setScore(int[] score) {
        this.score = score;
    }
    public void setCurrentBoard(ChessBoard currentBoard) {
        this.currentBoard = currentBoard;
    }
    public void setVirtualColor(Color virtualColor) {
        this.virtualColor = virtualColor;
    }
    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }
    public int getPlayerNumber() {
        return playerNumber;
    }
    public ChessBoardLocation getSrc() {
        return src;
    }
    public ChessBoardLocation getDest() {
        return dest;
    }
    public int[] getScore() {
        return score;
    }
    public ChessBoard getCurrentBoard() {
        return currentBoard;
    }
    public Color getCurrentPlayer() {
        return currentColor;
    }
    public int getPlayerNo() {
        return playerNo;
    }
    public String getName() {
        return name;
    }
    public Color getVirtualColor() {
        return virtualColor;
    }

    public void getRun(ChessBoard chessBoard) {
        setCurrentBoard(chessBoard);
        virtualColor= currentColor;
        ArrayList<ChessBoardLocation[]> validMove = initialAnalysis();
        for (int i=0; i<validMove.size(); i++) {
            setCurrentBoard(chessBoard);
            virtualMove(validMove.get(i));
            virtualMove(Analysis(i,3));
            virtualMove(Analysis(i,2));
            virtualMove(Analysis(i,1));
        }
        ChessBoardLocation[] finalMove = bestRun(validMove,score);
        src=finalMove[0]; dest=finalMove[1];
        listenerList.forEach(listener -> {listener.RobotEvent(src,dest);});
    }

    public Color nextPlayerForRobot() {
        if (playerNumber==4) {
            if (virtualColor.equals(GameController.getGamer1().getCurrentColor())) {
                return GameController.getGamer2().getCurrentColor();
            } else if (virtualColor.equals(GameController.getGamer2().getCurrentColor())) {
                return GameController.getGamer3().getCurrentColor();
            } else if (virtualColor.equals(GameController.getGamer3().getCurrentColor())) {
                return GameController.getGamer4().getCurrentColor();
            } else {
                return GameController.getGamer1().getCurrentColor();
            }
        } else {
            if (virtualColor.equals(GameController.getGamer1().getCurrentColor())) {
                return GameController.getGamer2().getCurrentColor();
            } else {
                return GameController.getGamer1().getCurrentColor();
            }
        }
    }

    public ChessBoardLocation[] bestRun(ArrayList<ChessBoardLocation[]> locations, int[] currentScore) {
        ChessBoardLocation currentSrc=locations.get(0)[0], currentDest=locations.get(0)[1];
        int Score = currentScore[0];
        for (int i=0; i<locations.size(); i++) {
            if (currentScore[i]>Score) {
                Score=currentScore[i];
                currentSrc=locations.get(i)[0]; currentDest=locations.get(i)[1];
            }
        }
        ChessBoardLocation[] currentBest = new ChessBoardLocation[2];
        currentBest[0]=currentSrc; currentBest[1]=currentDest;
        return currentBest;
    }

    public ArrayList<ChessBoardLocation[]> getValid() {
        ArrayList<ChessBoardLocation[]> locations = new ArrayList<ChessBoardLocation[]>();
        for (int i=0; i<16; i++) {
            for (int j=0; j<16; j++) {
                if (currentBoard.getGridAt(i,j).getPiece().getColor().equals(virtualColor)) {
                    locations.add(new ChessBoardLocation[2]);
                    locations.get(locations.size()-1)[0] = new ChessBoardLocation(i,j);
                }
            }
        }
        return locations;
    }

    public ArrayList<ChessBoardLocation[]> initialAnalysis() {
        ArrayList<ChessBoardLocation[]> locations = getValid();
        for (int i=0; i<locations.size(); i++) {
            int max=0; ChessBoardLocation maxLocation=locations.get(i)[0];
            for (ChessBoardLocation dest: currentBoard.ValidMove(locations.get(i)[0])) {
                if (playerNo==1) {
                    if (dest.getRow()+dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn())>max) {
                        max = dest.getRow()+dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn());
                        maxLocation = dest;
                    }
                }
                if ((playerNo==2) && (playerNumber==4)) {
                    if (16-dest.getRow()+dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn())>max) {
                        max = 16-dest.getRow()+dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn());
                        maxLocation = dest;
                    }
                }
                if (((playerNo==3) && (playerNumber==4)) || ((playerNo==2) && (playerNumber==2))) {
                    if (32-dest.getRow()-dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn())>max) {
                        max = 32-dest.getRow()-dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn());
                        maxLocation = dest;
                    }
                }
                if (playerNo==4) {
                    if (dest.getRow()+16-dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn())>max) {
                        max = dest.getRow()+16-dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn());
                        maxLocation = dest;
                    }
                }
            }
            locations.get(i)[1]=maxLocation; score[i]=4*max;
        }
        return locations;
    }

    public void virtualMove(ChessBoardLocation[] move) {
        currentBoard.getGridAt(move[0]).setPiece(null);
        currentBoard.getGridAt(move[1]).setPiece(new ChessPiece(virtualColor));
        virtualColor=nextPlayerForRobot();
        playerNo+=1;
        if (playerNo>playerNumber) playerNo-=playerNumber;
    }

    public ChessBoardLocation[] Analysis(int num, int counter) {
        ArrayList<ChessBoardLocation[]> locations = getValid();
        int[] count = new int[locations.size()];
        for (int i=0; i<locations.size(); i++) {
            int max=0; ChessBoardLocation maxLocation=locations.get(i)[0];
            for (ChessBoardLocation dest: currentBoard.ValidMove(locations.get(i)[0])) {
                if (playerNo==1) {
                    if (dest.getRow()+dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn())>max) {
                        max = dest.getRow()+dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn());
                        maxLocation = dest;
                    }
                }
                if ((playerNo==2) && (playerNumber==4)) {
                    if (16-dest.getRow()+dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn())>max) {
                        max = 16-dest.getRow()+dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn());
                        maxLocation = dest;
                    }
                }
                if (((playerNo==3) && (playerNumber==4)) || ((playerNo==2) && (playerNumber==2))) {
                    if (32-dest.getRow()-dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn())>max) {
                        max = 32-dest.getRow()-dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn());
                        maxLocation = dest;
                    }
                }
                if (playerNo==4) {
                    if (dest.getRow()+16-dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn())>max) {
                        max = dest.getRow()+16-dest.getColumn()+Math.abs(dest.getRow()-locations.get(i)[0].getRow())+Math.abs(dest.getColumn()-locations.get(i)[0].getColumn());
                        maxLocation = dest;
                    }
                }
            }
            locations.get(i)[1]=maxLocation; count[i]=max;
        }
        ChessBoardLocation[] bestRun = bestRun(locations,count);
        for (int i=0; i<locations.size(); i++) {
            if (Arrays.equals(bestRun, locations.get(i))) {
                if ((playerNumber==2) && (counter%2==0)) score[num]+=counter*count[i];
                else if ((playerNumber==2) && (counter%2==1)) score[num]-=counter*count[i];
                else score[num]-=count[i];
                break;
            }
        }
        return bestRun;
    }

    @Override
    public void registerListener(RobotListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(RobotListener listener) {
        listenerList.remove(listener);
    }
}
