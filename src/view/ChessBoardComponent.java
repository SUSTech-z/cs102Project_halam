package view;

import listener.*;
import model.ChessBoard;
import model.ChessBoardLocation;
import model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardComponent extends JComponent implements Listenable<InputListener>, GameListener, RobotListener, TaskListener {
    private static final Color BOARD_COLOR_1 = new Color(250, 250, 149);
    private static final Color BOARD_COLOR_2 = new Color(157, 156, 71);

    private List<InputListener> listenerList = new ArrayList<>();
    private SquareComponent[][] gridComponents;
    private int dimension;
    private int gridSize;
    private ArrayList<ChessBoardLocation> destination;


    public ChessBoardComponent(int size, int dimension) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null); // Use absolute layout
        setSize(size, size);
        this.gridComponents = new SquareComponent[dimension][dimension];
        this.dimension = dimension;
        this.gridSize = size / dimension;
        initGridComponents(0);
    }

    public ChessBoardComponent(int size, int dimension, int identifier) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setLayout(null); // Use absolute layout
        setSize(size, size);
        this.gridComponents = new SquareComponent[dimension][dimension];
        this.dimension = dimension;
        this.gridSize = size / dimension;
        initGridComponents(identifier);
    }

    private void addComponents(int row, int col, Color color) {
        gridComponents[row][col] = new SquareComponent(gridSize, color);
        gridComponents[row][col].setLocation(col * gridSize, row * gridSize);
        add(gridComponents[row][col]);
        if (color.equals(BOARD_COLOR_2)) destination.add(new ChessBoardLocation(row,col));
    }

    private void initGridComponents(int identifier) {
        destination = new ArrayList<ChessBoardLocation>();
        if (identifier == 0) {
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    gridComponents[row][col] = new SquareComponent(gridSize, (row + col) % 2 == 0 ? BOARD_COLOR_1 : BOARD_COLOR_2);
                    gridComponents[row][col].setLocation(col * gridSize, row * gridSize);
                    add(gridComponents[row][col]);
                }
            }
        }

        if (identifier == 1) {
            for (int i = 5; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    addComponents(i, j, BOARD_COLOR_1);
                }
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 11; j++) {
                    addComponents(i, j, BOARD_COLOR_1);
                }
            }
            addComponents(2, 11, BOARD_COLOR_1);
            addComponents(3, 11, BOARD_COLOR_1);
            addComponents(4, 11, BOARD_COLOR_1);
            addComponents(3, 12, BOARD_COLOR_1);
            addComponents(4, 12, BOARD_COLOR_1);
            addComponents(4, 13, BOARD_COLOR_1);
            for (int i = 0; i < 2; i++) {
                for (int j = 11; j < dimension; j++) {
                    addComponents(i, j, BOARD_COLOR_2);
                }
            }
            for (int j = 12; j < dimension; j++) {
                addComponents(2, j, BOARD_COLOR_2);
            }
            for (int j = 13; j < dimension; j++) {
                addComponents(3, j, BOARD_COLOR_2);
            }
            for (int j = 14; j < dimension; j++) {
                addComponents(4, j, BOARD_COLOR_2);
            }
        }

        if (identifier == 2) {
            for (int j = 0; j < dimension; j++) {
                if (j >= 6) {
                    addComponents(0, j, BOARD_COLOR_2);
                } else {
                    addComponents(0, j, BOARD_COLOR_1);
                }
            }
            for (int i = 1; i < dimension; i++) {
                if (i < 10) {
                    addComponents(i, 15, BOARD_COLOR_2);
                } else {
                    addComponents(i, 15, BOARD_COLOR_1);
                }
            }
            for (int i = 1; i < dimension; i++) {
                for (int j = 0; j < 15; j++) {
                    addComponents(i, j, BOARD_COLOR_1);
                }
            }
        }

        if (identifier == 3) {
            for(int i=0;i<dimension;i++){
                for(int j=0;j<dimension;j++){
                    if(j-i==3||(i+j==15&&i<6)){
                        addComponents(i,j,BOARD_COLOR_2);
                    }else {
                        addComponents(i,j,BOARD_COLOR_1);
                    }
                }
            }
        }

        if(identifier==4){
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    if ((row==5) && (col>=7) && (col<=13)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((col==10) && (row>=2) && (row<=8)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((row+col==15) && ((row==7) || ((row>=1) && (row<=3)))) addComponents(row, col, BOARD_COLOR_2);
                    else if ((col-row==5) && ((row==3) || (row==7))) addComponents(row, col, BOARD_COLOR_2);
                    else addComponents(row, col, BOARD_COLOR_1);
                }
            }
        }

        if(identifier==5){
            for(int i=0;i<dimension;i++){
                for(int j=0;j<dimension;j++){
                    if(((i==0)||(i==1))&&(j>=3)&&(j<=6))addComponents(i,j,BOARD_COLOR_2);
                    else if((i>=1)&&(i<=3)&&(j-i==11))addComponents(i,j,BOARD_COLOR_2);
                    else if((j==14||j==15)&&(i>=9)&&(i<=12))addComponents(i,j,BOARD_COLOR_2);
                    else addComponents(i,j,BOARD_COLOR_1);
                }
            }
        }
        if(identifier==6){
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    if ((row-col==5) && (col>=2) && (col<=8) && (col%2==0)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((row-col==1) && (col>=4) && (col<=10) && (col%2==0)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((col-row==3) && (col>=6) && (col<=12) && (col%2==0)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((col-row==7) && (col>=8) && (col<=14) && (col%2==0)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((col-row==9) && (row>=1) && (row<=5) && (row%2==1)) addComponents(row, col, BOARD_COLOR_2);
                    else addComponents(row, col, BOARD_COLOR_1);
                }
            }
        }

        if(identifier==7){
            for(int i=0;i<dimension;i++){
                for(int j=0;j<dimension;j++){
                    if(i==15&&j==15)addComponents(i,j,BOARD_COLOR_2);
                    else if((j-i==5)&&(i<=8)&&(i>=2)&&(i!=5))addComponents(i,j,BOARD_COLOR_2);
                    else if ((j-i==1)&&(i>=4)&&(i<=10)&&(i!=7))addComponents(i,j,BOARD_COLOR_2);
                    else if((j+i==11)&&(i>=2)&&(i<=6)&&(i!=5)&&(i!=3))addComponents(i,j,BOARD_COLOR_2);
                    else if((j+i==19)&&(i>=6)&&(i<=10)&&(i!=7)&&(i!=9))addComponents(i,j,BOARD_COLOR_2);
                    else addComponents(i,j,BOARD_COLOR_1);
                }
            }
        }
        if(identifier==8){
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    if ((col-row==6) && (row>=2) && (row<=7)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((col-row==5) && (row>=2) && (row<=8) && (row%3==2)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((col-row==3) && (row>=3) && (row<=9) && (row%3==0)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((col-row==2) && (row>=4) && (row<=9)) addComponents(row, col, BOARD_COLOR_2);
                    if ((row==7) && (col==8)) addComponents(row, col, BOARD_COLOR_2);
                    else addComponents(row, col, BOARD_COLOR_1);
                }
            }
        }
        if(identifier==9){
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    if ((15-col==row) && (row>=0) && (row<=7) && (row!=2)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((Math.abs(15-col-row)==1) && (col-row>=4) && (col-row<=10)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((Math.abs(15-col-row)==2) && (col-row>=11) && (col-row<=13)) addComponents(row, col, BOARD_COLOR_2);
                    else addComponents(row, col, BOARD_COLOR_1);
                }
            }
        }
        if(identifier==10){
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    if ((col==9) && (row>=1) && (row<=6)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((row==6) && (col>=9) && (col<=14)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((col==14) && (row>=4) && (row<=6)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((row==1) && (col>=9) && (col<=11)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((row>=2) && (row<=3) && (col>=12) && (col<=13)) addComponents(row, col, BOARD_COLOR_2);
                    else addComponents(row, col, BOARD_COLOR_1);
                }
            }
        }
        if(identifier==11){
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    if (((row==1) || (row==7)) && (col>=10) && (col<=12)) addComponents(row, col, BOARD_COLOR_2);
                    else if (((col==8) || (col==14)) && (row>=3) && (row<=5)) addComponents(row, col, BOARD_COLOR_2);
                    else if (((row==2) || (row==6)) && ((col==9) || (col==13))) addComponents(row, col, BOARD_COLOR_2);
                    else if (((row==3) && ((col==10) || (col==12))) || ((row==5) && (col==11))) addComponents(row, col, BOARD_COLOR_2);
                    else addComponents(row, col, BOARD_COLOR_1);
                }
            }
        }
        if(identifier==12){
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    if ((col==9) && (row>=2) && (row<=6)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((row==6) && (col>=9) && (col<=13)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((15-col==row) && (col-row>=11) && (col-row<=13)) addComponents(row, col, BOARD_COLOR_2);
                    else if ((Math.abs(15-col-row)==2) && (col-row>=7) && (col-row<=13)) addComponents(row, col, BOARD_COLOR_2);
                    else addComponents(row, col, BOARD_COLOR_1);
                }
            }
        }
        if(identifier==13){
            for (int row = 0; row < dimension; row++) {
                for (int col = 0; col < dimension; col++) {
                    if ((col>=9) && (col<=12) && (row>=3) && (row<=6)) addComponents(row, col, BOARD_COLOR_2);
                    else if (((row==2) || (row==7)) && ((col==8) || (col==13)) && (!((row==2) && (col==13)))) addComponents(row, col, BOARD_COLOR_2);
                    else addComponents(row, col, BOARD_COLOR_1);
                }
            }
        }
        if(identifier==14){
            for(int i=0;i<dimension;i++) {
                for (int j = 0; j < dimension; j++) {
                  if(i==2&&(j==8||j==10)) addComponents(i,j,BOARD_COLOR_2);
                  else if((i==3||i==4)&&(j>=7&&j<=11))addComponents(i,j,BOARD_COLOR_2);
                  else if((i==5||i==6)&&(j>=8&&j<=10))addComponents(i,j,BOARD_COLOR_2);
                  else if((i==7)&&(j==9))addComponents(i,j,BOARD_COLOR_2);
                  else addComponents(i,j,BOARD_COLOR_1);
                }
            }
        }
        if(identifier==15){
            for(int i=0;i<dimension;i++) {
                for (int j = 0; j < dimension; j++) {
                    if(i==1&&j==11)addComponents(i,j,BOARD_COLOR_2);
                    else if (i==3&&(j==14||j==7))addComponents(i,j,BOARD_COLOR_2);
                    else if(i==4&&(j==10||j==11))addComponents(i,j,BOARD_COLOR_2);
                    else if(i==9&&(j==13||j==8))addComponents(i,j,BOARD_COLOR_2);
                    else if(i==7&&(j==10||j==11))addComponents(i,j,BOARD_COLOR_2);
                    else if((i==5||i==6)&&(j>=9&&j<=12))addComponents(i,j,BOARD_COLOR_2);
                    else if(i==6&&(j==15||j==6))addComponents(i,j,BOARD_COLOR_2);
                    else addComponents(i,j,BOARD_COLOR_1);

                }
            }
        }
        if(identifier==16){
            for(int i=0;i<dimension;i++){
                for(int j=0;j<dimension;j++){
                    if((i==0||i==1)&&((j==2)||(j==3)||(j==8)||(j==9)||((j==14)&&(i!=1))||(j==15)))addComponents(i,j,BOARD_COLOR_2);
                    else if((i==6||i==7||i==12||i==13)&&(j==14||j==15))addComponents(i,j,BOARD_COLOR_2);
                    else addComponents(i,j,BOARD_COLOR_1);
                }
            }
        }

    }

    public ArrayList<ChessBoardLocation> win() {
        return destination;
    }


    public SquareComponent getGridAt(ChessBoardLocation location) {
        return gridComponents[location.getRow()][location.getColumn()];
    }

    public void setChessAtGrid(ChessBoardLocation location, Color color) {
        removeChessAtGrid(location);
        getGridAt(location).add(new ChessComponent(color));
    }

    public void removeChessAtGrid(ChessBoardLocation location) {
        // Note: re-validation is required after remove / removeAll
        getGridAt(location).removeAll();
        getGridAt(location).revalidate();
    }

    private ChessBoardLocation getLocationByPosition(int x, int y) {
        return new ChessBoardLocation(y / gridSize, x / gridSize);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);

        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            ChessBoardLocation location = getLocationByPosition(e.getX(), e.getY());
            for (InputListener listener : listenerList) {
                if (clickedComponent.getComponentCount() == 0) {
                    listener.onPlayerClickSquare(location, (SquareComponent) clickedComponent);
                } else {
                    listener.onPlayerClickChessPiece(location, (ChessComponent) clickedComponent.getComponent(0));
                }
            }
        }

    }

    @Override
    public void RobotEvent(ChessBoardLocation src, ChessBoardLocation dest) {
        JComponent Component1 = (JComponent) getComponentAt(gridSize * src.getColumn() + 1, gridSize * src.getRow() + 1);
        JComponent Component2 = (JComponent) getComponentAt(gridSize * dest.getColumn() + 1, gridSize * dest.getRow() + 1);
        for (InputListener listener : listenerList) {
            listener.onPlayerClickSquare(src, (SquareComponent) Component1);
            listener.onPlayerClickChessPiece(dest, (ChessComponent) Component2.getComponent(0));
        }
    }

    @Override
    public void onChessPiecePlace(ChessBoardLocation location, ChessPiece piece) {
        setChessAtGrid(location, piece.getColor());
        repaint();
    }

    @Override
    public void onChessPieceRemove(ChessBoardLocation location) {
        removeChessAtGrid(location);
        repaint();
    }

    @Override
    public void onChessBoardReload(ChessBoard board) {
        for (int row = 0; row < board.getDimension(); row++) {
            for (int col = 0; col < board.getDimension(); col++) {
                ChessBoardLocation location = new ChessBoardLocation(row, col);
                ChessPiece piece = board.getChessPieceAt(location);
                if (piece != null) {
                    setChessAtGrid(location, piece.getColor());
                }
            }
        }
        repaint();
    }

    @Override
    public void registerListener(InputListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(InputListener listener) {
        listenerList.remove(listener);
    }

}
