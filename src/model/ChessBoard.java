package model;

import controller.GameController;
import listener.GameListener;
import listener.Listenable;
import view.WelcomeFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard implements Listenable<GameListener> {
    private List<GameListener> listenerList = new ArrayList<>();
    private Square[][] grid;
    private int dimension;
    private int clickTimes = 0 ;

    public ChessBoard(int dimension) {//设置棋盘
        this.grid = new Square[dimension][dimension];
        this.dimension = dimension;
        initGrid();
    }

    public ChessBoard() {
        this.dimension = 16;
        this.grid = new Square[dimension][dimension];
        initGrid();
    }

    public Square getGridAt(int i,int j) {
        return grid[i][j];
    }

    private void initGrid() {//开辟棋盘大小的空间
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                grid[i][j] = new Square(new ChessBoardLocation(i, j));
            }
        }
    }

    public void placePieces(ChessBoardLocation location, Color color) {
        grid[location.getRow()][location.getColumn()].setPiece(new ChessPiece(color));
    }

//    public  void  clearAllChesses(){
//        for (int i=0;i<16;i++){
//            for(int j =0;j<16;j++){
//                getGridAt(i,j).setPiece(null);
//            }
//        }
//    }


    public void placeInitialPieces(int playerNumber) {//放置初始棋子
        if (playerNumber==2) {
            for (int i=0; i<5; i++) {
                for (int j=0; j<5-i; j++) {
                    grid[i][j].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[0]));
                }
                if (i!=0) grid[i][5-i].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[0]));
            }
            for (int i=0; i<5; i++) {
                for (int j=0; j<5-i; j++) {
                    grid[dimension-1-i][dimension-1-j].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[1]));
                }
                if (i!=0) grid[dimension-1-i][dimension-6+i].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[1]));
            }
            listenerList.forEach(listener -> listener.onChessBoardReload(this));
        }
        if (playerNumber==4) {
            for (int i=0; i<4; i++) {
                for (int j=0; j<4-i; j++) {
                    grid[i][j].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[0]));
                }
                if (i!=0) grid[i][4-i].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[0]));
            }
            for (int i=0; i<4; i++) {
                for (int j=0; j<4-i; j++) {
                    grid[dimension-1-i][dimension-1-j].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[2]));
                }
                if (i!=0) grid[dimension-1-i][dimension-5+i].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[2]));
            }
            for (int i=0; i<4; i++) {
                for (int j=0; j<4-i; j++) {
                    grid[dimension-1-i][j].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[1]));
                }
                if (i!=0) grid[dimension-1-i][4-i].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[1]));
            }
            for (int i=0; i<4; i++) {
                for (int j=0; j<4-i; j++) {
                    grid[i][dimension-1-j].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[3]));
                }
                if (i!=0) grid[i][dimension-5+i].setPiece(new ChessPiece(WelcomeFrame.getColorlist()[3]));
            }
            listenerList.forEach(listener -> listener.onChessBoardReload(this));
        }
        if (playerNumber==1) {
            for (int i=0; i<5; i++) {
                for (int j=0; j<5-i; j++) {
                    grid[dimension-1-i][j].setPiece(new ChessPiece(Color.BLACK));
                }
                if (i!=0) grid[dimension-1-i][5-i].setPiece(new ChessPiece(Color.BLACK));
            }
            listenerList.forEach(listener -> listener.onChessBoardReload(this));
        }
    }

    public void placeInitialPieces(ChessBoard board) {
        for (int i=0; i<dimension; i++) {
            for (int j=0; j<dimension; j++) {
                if (board.getChessPieceAt(i,j)!=null) grid[i][j].setPiece(new ChessPiece(board.getChessPieceAt(i,j).getColor()));
            }
        }
        listenerList.forEach(listener -> listener.onChessBoardReload(this));
    }



    public Square getGridAt(ChessBoardLocation location) {
        return grid[location.getRow()][location.getColumn()];
    }

    public ChessPiece getChessPieceAt(ChessBoardLocation location) {
        return getGridAt(location).getPiece();
    }

    public ChessPiece getChessPieceAt(int i, int j) {
        ChessBoardLocation src = new ChessBoardLocation(i,j);
        return getChessPieceAt(src);
    }

    public void setChessPieceAt(ChessBoardLocation location, ChessPiece piece) {
        getGridAt(location).setPiece(piece);
        listenerList.forEach(listener -> listener.onChessPiecePlace(location, piece));
    }

    public ChessPiece removeChessPieceAt(ChessBoardLocation location) {//移出棋子（拿出棋子-清空原处-返回棋子）
        ChessPiece piece = getGridAt(location).getPiece();
        getGridAt(location).setPiece(null);
        listenerList.forEach(listener -> listener.onChessPieceRemove(location));
        return piece;
    }

    public void moveChessPiece(ChessBoardLocation src, ChessBoardLocation dest) {//判断合法性-移入棋子
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal halma move");
        }
        setChessPieceAt(dest, removeChessPieceAt(src));
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isValidMove(ChessBoardLocation src, ChessBoardLocation dest) {//判断合法性：棋子有无-是否合法
        return (getChessPieceAt(src) != null) && (getChessPieceAt(dest) == null) && (ValidMove(src).contains(dest));
    }

    public ArrayList<ChessBoardLocation> ValidMove(ChessBoardLocation src) {
        ArrayList<ChessBoardLocation> valid = new ArrayList<ChessBoardLocation>(ValidMove2(src, new ArrayList<ChessBoardLocation>()));
        valid.addAll(ValidMove1(src));
        for (int i=valid.size()-1; i>=0; i--) {
            if (valid.get(i).equals(src)) valid.remove(i);
        }
        return valid;
    }

    public ArrayList<ChessBoardLocation> ValidMove1(ChessBoardLocation src) {
        ArrayList<ChessBoardLocation> valid = new ArrayList<ChessBoardLocation>();
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if ((src.getRow()+i>=0) && (src.getColumn()+j>=0) && (src.getRow()+i<dimension) && (src.getColumn()+j<dimension) && (!((i==0) && (j==0))))
                    valid.add(new ChessBoardLocation(src.getRow()+i,src.getColumn()+j));
            }
        }
        for (int i=valid.size()-1; i>=0; i--) {
            if (getChessPieceAt(valid.get(i))!=null) valid.remove(i);
        }
        return valid;
    }

    public ArrayList<ChessBoardLocation> ValidMove2(ChessBoardLocation src, ArrayList<ChessBoardLocation> pri) {
        ArrayList<ChessBoardLocation> valid = new ArrayList<ChessBoardLocation>(pri);
        int v=valid.size();
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if ((src.getRow()+i>=0) && (src.getColumn()+j>=0) && (src.getRow()+i<dimension)
                        && (src.getColumn()+j<dimension) && (!((i==0) && (j==0))) && (getChessPieceAt(src.getRow()+i,src.getColumn()+j)!=null)
                        && (src.getRow()+2*i>=0) && (src.getRow()+2*i<dimension)
                        && (src.getColumn()+2*j>=0) && (src.getColumn()+2*j<dimension) && (getChessPieceAt(src.getRow()+2*i,src.getColumn()+2*j)==null)) {
                    if (((valid.size()==0) || (!valid.contains(new ChessBoardLocation(src.getRow()+2*i,src.getColumn()+2*j))))
                            && (!new ChessBoardLocation(src.getRow()+2*i,src.getColumn()+2*j).equals(src)))
                        valid.add(new ChessBoardLocation(src.getRow()+2*i,src.getColumn()+2*j));
                }
            }
        }
        if (valid.size()>v) {
            for (ChessBoardLocation c:valid) {
                valid = ValidMove2(c,valid);
            }
        }
        return valid;
    }


    public void showPossibleMove(ChessBoardLocation location){
        for (ChessBoardLocation L: ValidMove(location)) {
            grid[L.getRow()][L.getColumn()].setPiece(new ChessPiece(Color.white));
        }
        listenerList.forEach(listener -> listener.onChessBoardReload(this));
    }

    public  void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }
    public void addClickTimes() {
        clickTimes ++;
    }
    public int getClickTimes() {
        return clickTimes;
    }

    public Color winner(int playerNumber) {
        boolean win=true;
        if (playerNumber==2) {
            for (int i=0; i<5; i++) {
                for (int j=0; j<5-i; j++) {
                    if ((grid[i][j].getPiece()==null) ||
                            (!grid[i][j].getPiece().getColor().equals(WelcomeFrame.getColorlist()[1]))) {
                        win = false;
                        break;
                    }
                }
                if (!win) break;
                if (i!=0) {if (((grid[i][5-i].getPiece()==null)) ||
                        (!grid[i][5-i].getPiece().getColor().equals(WelcomeFrame.getColorlist()[1]))) {win = false; break;}};
            }
            if (win) return WelcomeFrame.getColorlist()[1];
            win=true;
            for (int i=0; i<5; i++) {
                for (int j=0; j<5-i; j++) {
                    if ((grid[dimension - 1 - i][dimension - 1 - j].getPiece()==null) ||
                            (!grid[dimension - 1 - i][dimension - 1 - j].getPiece().getColor().equals(WelcomeFrame.getColorlist()[0]))) {
                        win = false;
                        break;
                    }
                }
                if (!win) break;
                if (i!=0) {if ((grid[dimension-1-i][dimension-6+i].getPiece()==null)||
                        (!grid[dimension-1-i][dimension-6+i].getPiece().getColor().equals(WelcomeFrame.getColorlist()[0]))) {win = false; break;}};
            }
            if (win) return WelcomeFrame.getColorlist()[0];
        }
        if (playerNumber==4) {
            for (int i=0; i<4; i++) {
                for (int j=0; j<4-i; j++) {
                    if ((grid[i][j].getPiece()==null) ||
                            (!grid[i][j].getPiece().getColor().equals(WelcomeFrame.getColorlist()[2]))) {
                        win = false;
                        break;
                    }
                }
                if (!win) break;
                if (i!=0) {if (((grid[i][4-i].getPiece()==null)) ||
                        (!grid[i][4-i].getPiece().getColor().equals(WelcomeFrame.getColorlist()[2]))) {win = false; break;}};
            }
            if (win) return WelcomeFrame.getColorlist()[2];
            win=true;
            for (int i=0; i<4; i++) {
                for (int j=0; j<4-i; j++) {
                    if ((grid[dimension - 1 - i][dimension - 1 - j].getPiece()==null) ||
                            (!grid[dimension - 1 - i][dimension - 1 - j].getPiece().getColor().equals(WelcomeFrame.getColorlist()[0]))) {
                        win = false;
                        break;
                    }
                }
                if (!win) break;
                if (i!=0) {if ((grid[dimension-1-i][dimension-5+i].getPiece()==null)||
                        (!grid[dimension-1-i][dimension-5+i].getPiece().getColor().equals(WelcomeFrame.getColorlist()[0]))) {win = false; break;}};
            }
            if (win) return WelcomeFrame.getColorlist()[0];
            win=true;
            for (int i=0; i<4; i++) {
                for (int j=0; j<4-i; j++) {
                    if (((grid[dimension - 1 - i][j].getPiece()==null)) ||
                            (!grid[dimension - 1 - i][j].getPiece().getColor().equals(WelcomeFrame.getColorlist()[3]))) {
                        win = false;
                        break;
                    }
                }
                if (!win) break;
                if (i!=0) {if (((grid[dimension - 1 - i][4-i].getPiece()==null)) ||
                        (!grid[dimension-1-i][4-i].getPiece().getColor().equals(WelcomeFrame.getColorlist()[3]))) {win = false; break;}};
            }
            if (win) return WelcomeFrame.getColorlist()[3];
            win=true;
            for (int i=0; i<4; i++) {
                for (int j=0; j<4-i; j++) {
                    if ((grid[i][dimension - 1 - j].getPiece()==null)||
                            (!grid[i][dimension - 1 - j].getPiece().getColor().equals(WelcomeFrame.getColorlist()[1]))) {
                        win = false;
                        break;
                    }
                }
                if (!win) break;
                if (i!=0) {if ((grid[i][dimension-5+i].getPiece()==null) ||
                        (!grid[i][dimension-5+i].getPiece().getColor().equals(WelcomeFrame.getColorlist()[1]))) {win = false; break;}};
            }
            if (win) return WelcomeFrame.getColorlist()[1];
        }
        return null;
    }

    public ArrayList<ChessBoardLocation> MovePath(ChessBoardLocation src, ChessBoardLocation dest) {
        ArrayList<ChessBoardLocation> movePath = new ArrayList<ChessBoardLocation>();
        if (ValidMove1(src).contains(dest)) {movePath.add(src); movePath.add(dest);}
        else {
            movePath.add(src);
            ArrayList<ChessBoardLocation> validMove = ValidMove2(src,new ArrayList<ChessBoardLocation>());
            do {
                ChessBoardLocation cur = movePath.get(movePath.size() - 1);
                for (ChessBoardLocation c : validMove) {
                    if (((Math.abs(cur.getColumn() - c.getColumn()) == 2) || (Math.abs(cur.getColumn() - c.getColumn()) == 0)) &&
                            ((Math.abs(cur.getRow() - c.getRow()) == 2) || (Math.abs(cur.getRow() - c.getRow()) == 0)) &&
                            (grid[(cur.getRow() + c.getRow()) / 2][(cur.getColumn() + c.getColumn()) / 2].getPiece() != null) && (!movePath.contains(c))) {
                        movePath.add(c);
                        break;
                    }
                }
                if ((movePath.get(movePath.size()-1).equals(cur)) && (!movePath.get(movePath.size() - 1).equals(dest))) {
                    for (int i=0; i<validMove.size(); i++) {
                        if (validMove.get(i).equals(cur)) {validMove.remove(i); break;}
                    }
                    movePath.remove(movePath.size()-1);
                }
            } while (!movePath.get(movePath.size() - 1).equals(dest));
        }
        return movePath;
    }

    @Override
    public void registerListener(GameListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(GameListener listener) {
        listenerList.remove(listener);
    }
}
