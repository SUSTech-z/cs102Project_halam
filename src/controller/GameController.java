package controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import listener.*;
import model.*;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GameController implements Listenable<TaskListener>, InputListener{
    private List<TaskListener> listenerList = new ArrayList<>();
    private ChessBoardComponent view;
    private ChessBoard model;
    private GameFrame WIN;
    private ChessBoardLocation src;
    private ChessBoardLocation dest;
    private ChessBoardLocation selectedLocation;
    private Color currentPlayer;
    private int playerNumber;
    private Doc DOCUMENT;
    private Color regretPlayer;
    public static Player gamer1;
    public static Player gamer2;
    public static Player gamer3;
    public static Player gamer4;


    public static Player getGamer1() {
        return gamer1;
    }
    public static void setGamer1(Player gamer1) {
        GameController.gamer1 = gamer1;
    }
    public static Player getGamer2() {
        return gamer2;
    }
    public static void setGamer2(Player gamer2) {
        GameController.gamer2 = gamer2;
    }

    public static Player getGamer3() {
        return gamer3;
    }

    public static void setGamer3(Player gamer3) {
        GameController.gamer3 = gamer3;

    }

    public static Player getGamer4() {
        return gamer4;
    }

    public static void setGamer4(Player gamer4) {
        GameController.gamer4 = gamer4;

    }

    public Color getRegretPlayer() {
        return regretPlayer;
    }

    public void setRegretPlayer(Color regretPlayer) {
        this.regretPlayer = regretPlayer;
    }

    public ChessBoardLocation getSrc() {
        return src;
    }

    public void setSrc(ChessBoardLocation src) {
        this.src = src;
    }

    public ChessBoardLocation getDest() {
        return dest;
    }

    public void setDest(ChessBoardLocation dest) {
        this.dest = dest;
    }

    public GameController(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard, GameFrame gameFrame) {
        this.view = chessBoardComponent;
        this.model = chessBoard;
        this.selectedLocation = null;
        this.WIN = gameFrame;
        view.registerListener(this);
        model.registerListener(view);
        model.placeInitialPieces(playerNumber);

    }
    public GameController(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard, int playerNumber, GameFrame gameFrame) {
        this.view = chessBoardComponent;
        this.model = chessBoard;
        this.playerNumber = playerNumber;
        this.selectedLocation = null;
        this.WIN = gameFrame;
        String[] loadNameList = new String[WelcomeFrame.getPlaylist().size()];
        int count = 0;
        for (Player P: WelcomeFrame.getPlaylist()) {
            loadNameList[count]=P.getName();
            count++;
        }
        if (WelcomeFrame.getPlaylist().size()!=0){
            if (playerNumber == 4) {
                Player gamer1 = new Player();
                Player gamer2 = new Player();
                Player gamer3 = new Player();
                Player gamer4 = new Player();

                gamer1.setCurrentColor(WelcomeFrame.getColorlist()[0]);
                gamer2.setCurrentColor(WelcomeFrame.getColorlist()[1]);
                gamer3.setCurrentColor(WelcomeFrame.getColorlist()[2]);
                gamer4.setCurrentColor(WelcomeFrame.getColorlist()[3]);
                gamer1.setName(WelcomeFrame.getNameList()[0]);
                gamer2.setName(WelcomeFrame.getNameList()[1]);
                gamer3.setName(WelcomeFrame.getNameList()[2]);
                gamer4.setName(WelcomeFrame.getNameList()[3]);
                int Count = 0;
                int number1=0;
                int number2=0;
                int number3=0;
                int number4=0;
                for (String s:loadNameList) {
                    if (s.equals(WelcomeFrame.getNameList()[0])){
                        setGamer1(WelcomeFrame.getPlaylist().get(Count));
                        number1++;
                    }
                    if (s.equals(WelcomeFrame.getNameList()[1])){
                        setGamer2(WelcomeFrame.getPlaylist().get(Count));
                        number2++;
                    }
                    if (s.equals(WelcomeFrame.getNameList()[2])){
                        setGamer3(WelcomeFrame.getPlaylist().get(Count));
                        number3++;
                    }
                    if (s.equals(WelcomeFrame.getNameList()[3])){
                        setGamer4(WelcomeFrame.getPlaylist().get(Count));
                        number4++;
                    }
                    Count++;
                }

                if (number1==0){
                    WelcomeFrame.getPlaylist().add(gamer1);
                }
                if (number2==0){
                    WelcomeFrame.getPlaylist().add(gamer2);
                }
                if (number3==0){
                    WelcomeFrame.getPlaylist().add(gamer3);
                }
                if (number4==0){
                    WelcomeFrame.getPlaylist().add(gamer4);
                }

            } else if (playerNumber==2){
                Player gamer1 = new Player();
                Player gamer2 = new Player();
                gamer1.setName(WelcomeFrame.getNameList()[0]);
                gamer2.setName(WelcomeFrame.getNameList()[1]);
                gamer1.setCurrentColor(WelcomeFrame.getColorlist()[0]);
                gamer2.setCurrentColor(WelcomeFrame.getColorlist()[1]);
                int Count=0 ;
                int number1=0;
                int number2=0;
                for (String s:loadNameList) {
                    if (s.equals(WelcomeFrame.getNameList()[0])){
                        setGamer1(WelcomeFrame.getPlaylist().get(Count));
                        number1++;
                    }
                    if (s.equals(WelcomeFrame.getNameList()[1])){
                        setGamer2(WelcomeFrame.getPlaylist().get(Count));
                        number2++;
                    }
                    Count++;
                }
                if (number1==0){
                    WelcomeFrame.getPlaylist().add(gamer1);
                }
                if (number2==0){
                    WelcomeFrame.getPlaylist().add(gamer2);
                }
            }

        }else {
            if (playerNumber == 4) {
                Player gamer1 = new Player();
                Player gamer2 = new Player();
                Player gamer3 = new Player();
                Player gamer4 = new Player();

                gamer1.setCurrentColor(WelcomeFrame.getColorlist()[0]);
                gamer2.setCurrentColor(WelcomeFrame.getColorlist()[1]);
                gamer3.setCurrentColor(WelcomeFrame.getColorlist()[2]);
                gamer4.setCurrentColor(WelcomeFrame.getColorlist()[3]);
                gamer1.setName(WelcomeFrame.getNameList()[0]);
                gamer2.setName(WelcomeFrame.getNameList()[1]);
                gamer3.setName(WelcomeFrame.getNameList()[2]);
                gamer4.setName(WelcomeFrame.getNameList()[3]);
                WelcomeFrame.getPlaylist().add(gamer1);
                WelcomeFrame.getPlaylist().add(gamer2);
                WelcomeFrame.getPlaylist().add(gamer3);
                WelcomeFrame.getPlaylist().add(gamer4);

//                for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                    if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer1.getName())) {
//                        WelcomeFrame.getPlaylist().add(gamer1);
//                    } else {
//                        setGamer1(WelcomeFrame.getPlaylist().get(i));
//                    }
//                }
//
//                for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                    if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer2.getName())) {
//                        WelcomeFrame.getPlaylist().add(gamer2);
//                    } else {
//                        setGamer2(WelcomeFrame.getPlaylist().get(i));
//                    }
//                }
//                for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                    if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer3.getName())) {
//                        WelcomeFrame.getPlaylist().add(gamer3);
//                    } else {
//                        setGamer3(WelcomeFrame.getPlaylist().get(i));
//                    }
//                }
//                for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                    if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer4.getName())) {
//                        WelcomeFrame.getPlaylist().add(gamer4);
//                    } else {
//                        setGamer4(WelcomeFrame.getPlaylist().get(i));
//                    }
//                }

            } else if (playerNumber==2){
                Player gamer1 = new Player();
                Player gamer2 = new Player();
                gamer1.setName(WelcomeFrame.getNameList()[0]);
                gamer2.setName(WelcomeFrame.getNameList()[1]);
                gamer1.setCurrentColor(WelcomeFrame.getColorlist()[0]);
                gamer2.setCurrentColor(WelcomeFrame.getColorlist()[1]);
//                for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                    if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer1.getName())) {
//                        WelcomeFrame.getPlaylist().add(gamer1);
//                    } else {
//                        setGamer1(WelcomeFrame.getPlaylist().get(i));
//                    }
//                }
//                for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                    if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer2.getName())) {
//                        WelcomeFrame.getPlaylist().add(gamer2);
//                    } else {
//                        setGamer2(WelcomeFrame.getPlaylist().get(i));
//                    }
//                }
                WelcomeFrame.getPlaylist().add(gamer1);
                WelcomeFrame.getPlaylist().add(gamer2);

            }
        }
//        this.currentPlayer = WelcomeFrame.getColorlist()[0];
        if (playerNumber!=1) this.currentPlayer = WelcomeFrame.getColorlist()[0]; else this.currentPlayer = Color.BLACK;
        view.registerListener(this);
        model.registerListener(view);
        this.registerListener(view);
        model.placeInitialPieces(playerNumber);
    }

    public GameController(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard, Doc doc, GameFrame gameFrame) {
        this.view = chessBoardComponent;
        this.model = chessBoard;
        this.playerNumber = doc.getPlayerNumber();
        this.selectedLocation = null;
        this.WIN = gameFrame;
        setDOCUMENT(doc);
        WelcomeFrame.setColorlist(doc.getPlayerColor());
        String[] loadNameList = new String[WelcomeFrame.getPlaylist().size()];
        int count = 0;
        for (Player P: WelcomeFrame.getPlaylist()) {
            loadNameList[count]=P.getName();
            count++;
        }
        if (playerNumber == 4) {




//            gamer1.setName(doc.getNameList()[0]);
//            gamer2.setName(doc.getNameList()[1]);
//            gamer3.setName(doc.getNameList()[2]);
//            gamer4.setName(doc.getNameList()[3]);
            int Count = 0 ;
            for (String s:loadNameList
            ) {
                if (s.equals(doc.getName(0))){
                    setGamer1(WelcomeFrame.getPlaylist().get(Count));
                    gamer1.setCurrentColor(doc.getPlayerColor()[0]);
                }
                if (s.equals(doc.getName(1))){
                    setGamer2(WelcomeFrame.getPlaylist().get(Count));
                    gamer2.setCurrentColor(doc.getPlayerColor()[1]);
                }
                if (s.equals(doc.getName(2))){
                    setGamer3(WelcomeFrame.getPlaylist().get(Count));
                    gamer3.setCurrentColor(doc.getPlayerColor()[2]);
                }
                if (s.equals(doc.getName(3))){
                    setGamer4(WelcomeFrame.getPlaylist().get(Count));
                    gamer4.setCurrentColor(doc.getPlayerColor()[3]);
                }
                Count++;
            }
//            WelcomeFrame.setPlaylist(doc.getPlayerlist());
//            for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer1.getName())) {
//                    WelcomeFrame.getPlaylist().add(gamer1);
//                } else {
//                    setGamer1(WelcomeFrame.getPlaylist().get(i));
//                }
//            }
//
//            for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer2.getName())) {
//                    WelcomeFrame.getPlaylist().add(gamer2);
//                } else {
//                    setGamer2(WelcomeFrame.getPlaylist().get(i));
//                }
//            }
//            for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer3.getName())) {
//                    WelcomeFrame.getPlaylist().add(gamer3);
//                } else {
//                    setGamer3(WelcomeFrame.getPlaylist().get(i));
//                }
//            }
//            for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer4.getName())) {
//                    WelcomeFrame.getPlaylist().add(gamer4);
//                } else {
//                    setGamer4(WelcomeFrame.getPlaylist().get(i));
//                }
//            }

        } else {
//            Player gamer1 = new Player();
//            Player gamer2 = new Player();
//
//
//            gamer1.setName(doc.getNameList()[0]);
//            gamer2.setName(doc.getNameList()[1]);
//            Player gamer1 ;
//            Player gamer2 ;
            int Count = 0 ;
            for (String s:loadNameList
            ) {
                if (s.equals(doc.getName(0))){
                   setGamer1(WelcomeFrame.getPlaylist().get(Count));
                    gamer1.setCurrentColor(doc.getPlayerColor()[0]);
                }
                if (s.equals(doc.getName(1))){
                    setGamer2(WelcomeFrame.getPlaylist().get(Count));
                    gamer2.setCurrentColor(doc.getPlayerColor()[1]);
                }
                Count++;
            }
//            for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer1.getName())) {
//                    WelcomeFrame.getPlaylist().add(gamer1);
//                } else {
//                    setGamer1(WelcomeFrame.getPlaylist().get(i));
//                }
//            }
//
//            for(int i =0; i<WelcomeFrame.getPlaylist().size();i++) {
//                if (!WelcomeFrame.getPlaylist().get(i).getName().contains(gamer2.getName())) {
//                    WelcomeFrame.getPlaylist().add(gamer2);
//                } else {
//                    setGamer2(WelcomeFrame.getPlaylist().get(i));
//                }
//            }
        }
        this.currentPlayer = doc.getCurrentPlayer();
        view.registerListener(this);
        model.registerListener(view);
        this.registerListener(view);
        model.placeInitialPieces(doc.getChessBoard());
    }

    public ChessBoardLocation getSelectedLocation() {
        return selectedLocation;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void setSelectedLocation(ChessBoardLocation location) {
        this.selectedLocation = location;
    }

    public void setCurrentPlayer(Color currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void resetSelectedLocation() {
        setSelectedLocation(null);
    }

    public boolean hasSelectedLocation() {
        return selectedLocation != null;
    }

    public Color nextPlayer() {
        if (playerNumber == 4) {
            JPanel turn11 = new JPanel();
            turn11.setVisible(true);
            turn11.setLocation(10, 40);
            turn11.setSize(40, 20);
            WIN.add(turn11);
            JButton turn1 = new JButton();
            turn1.setBackground(Color.GREEN);
            turn1.setVisible(false);
            turn1.setLocation(10, 40);
            turn1.setSize(40, 15);
            turn11.add(turn1);
            JPanel turn22 = new JPanel();
            turn22.setVisible(true);
            turn22.setLocation(10, 790);
            turn22.setSize(40, 20);
            WIN.add(turn22);
            JButton turn2 = new JButton();
            turn2.setBackground(Color.GREEN);
            turn2.setVisible(false);
            turn2.setLocation(10, 790);
            turn2.setSize(40, 15);
            turn22.add(turn2);
            JPanel turn33 = new JPanel();
            turn33.setVisible(true);
            turn33.setLocation(835, 790);
            turn33.setSize(40, 20);
            WIN.add(turn33);
            JButton turn3 = new JButton();
            turn3.setBackground(Color.GREEN);
            turn3.setVisible(false);
            turn3.setLocation(835, 790);
            turn3.setSize(40, 15);
            turn33.add(turn3);
            JPanel turn44 = new JPanel();
            turn44.setVisible(true);
            turn44.setLocation(835, 40);
            turn44.setSize(40, 20);
            WIN.add(turn44);
            JButton turn4 = new JButton();
            turn4.setBackground(Color.GREEN);
            turn4.setVisible(false);
            turn4.setLocation(835, 40);
            turn4.setSize(40, 15);
            turn44.add(turn4);
            Player gamer1 = new Player();
            Player gamer2 = new Player();
            Player gamer3 = new Player();
            Player gamer4 = new Player();
            gamer1.setCurrentColor(WelcomeFrame.getColorlist()[0]);
            gamer2.setCurrentColor(WelcomeFrame.getColorlist()[1]);
            gamer3.setCurrentColor(WelcomeFrame.getColorlist()[2]);
            gamer4.setCurrentColor(WelcomeFrame.getColorlist()[3]);
            if (regretPlayer != null) {

                if (currentPlayer.equals(gamer1.getCurrentColor())) {
                    currentPlayer = gamer2.getCurrentColor();
                    turn1.setVisible(false);
                    turn2.setVisible(true);
                    turn3.setVisible(false);
                    turn4.setVisible(false);
                } else if (currentPlayer.equals(gamer2.getCurrentColor())) {
                    currentPlayer = gamer3.getCurrentColor();
                    turn1.setVisible(false);
                    turn2.setVisible(false);
                    turn3.setVisible(true);
                    turn4.setVisible(false);
                } else if (currentPlayer.equals(gamer3.getCurrentColor())) {
                    currentPlayer = gamer4.getCurrentColor();
                    turn1.setVisible(false);
                    turn2.setVisible(false);
                    turn3.setVisible(false);
                    turn4.setVisible(true);
                } else {
                    currentPlayer = gamer1.getCurrentColor();
                    turn1.setVisible(true);
                    turn2.setVisible(false);
                    turn3.setVisible(false);
                    turn4.setVisible(false);
                }
                turn11.repaint();
                turn22.repaint();
                turn33.repaint();
                turn44.repaint();
            } else {
                if (currentPlayer.equals(gamer1.getCurrentColor())) {
                    turn1.setVisible(true);
                    turn2.setVisible(false);
                    turn3.setVisible(false);
                    turn4.setVisible(false);
                } else if (currentPlayer.equals(gamer2.getCurrentColor())) {
                    turn1.setVisible(false);
                    turn2.setVisible(true);
                    turn3.setVisible(false);
                    turn4.setVisible(false);
                } else if (currentPlayer.equals(gamer3.getCurrentColor())) {
                    turn1.setVisible(false);
                    turn2.setVisible(false);
                    turn3.setVisible(true);
                    turn4.setVisible(false);
                } else {
                    turn1.setVisible(false);
                    turn2.setVisible(false);
                    turn3.setVisible(false);
                    turn4.setVisible(true);
                }
                turn11.repaint();
                turn22.repaint();
                turn33.repaint();
                turn44.repaint();
            }
        } else if (playerNumber==2){
            //case ����ģʽ
            JButton turn2 = new JButton();
            turn2.setBackground(Color.GREEN);
            turn2.setVisible(false);
            turn2.setLocation(835, 790);
            turn2.setSize(40, 15);
            JPanel turn22 = new JPanel();
            turn22.setVisible(true);
            turn22.setLocation(835, 790);
            turn22.setSize(40, 20);
            turn22.add(turn2);
            WIN.add(turn22);
            JButton turn1 = new JButton();
            turn1.setBackground(Color.GREEN);
            turn1.setVisible(false);
            turn1.setLocation(10, 40);
            turn1.setSize(40, 15);
            JPanel turn11 = new JPanel();
            turn11.setVisible(true);
            turn11.setLocation(10, 40);
            turn11.setSize(40, 20);
            turn11.add(turn1);
            WIN.add(turn11);
            Player gamer1 = new Player();
            Player gamer2 = new Player();

            gamer1.setCurrentColor(WelcomeFrame.getColorlist()[0]);
            gamer2.setCurrentColor(WelcomeFrame.getColorlist()[1]);

            if (regretPlayer != null) {
                if (currentPlayer.equals(gamer1.getCurrentColor())) {
                    currentPlayer = gamer2.getCurrentColor();
                    turn2.setVisible(true);
                    turn1.setVisible(false);
                    turn11.repaint();
                    turn22.repaint();
                } else {
                    currentPlayer = gamer1.getCurrentColor();
                    turn1.setVisible(true);
                    turn2.setVisible(false);
                    turn11.repaint();
                    turn22.repaint();
                }
            } else {
                if (currentPlayer.equals(gamer1.getCurrentColor())) {
                    turn1.setVisible(true);
                    turn2.setVisible(false);
                    turn11.repaint();
                    turn22.repaint();
                } else {
                    turn2.setVisible(true);
                    turn1.setVisible(false);
                    turn11.repaint();
                    turn22.repaint();
                }
            }
//             currentPlayer=(currentPlayer==Color.BLACK?Color.LIGHT_GRAY:Color.BLACK);
        }
        else currentPlayer = Color.BLACK;

        return currentPlayer;

    }

//    @Override
//    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
//        WIN.getShowCurrentPlayer().setVisible(false);
//        if (hasSelectedLocation() && model.isValidMove(getSelectedLocation(), location)) {
//            model.moveChessPiece(selectedLocation, location);
////            MusicPlay.playMusic("sound\\MOVE2.WAV");
//            src=selectedLocation;
//            dest=location;
//            regretPlayer=currentPlayer;
//            resetSelectedLocation();
//            if (model.winner(playerNumber)!=null) {
//                int res = JOptionPane.showConfirmDialog(null, "YOU HAVE WIN\nWILL YOU RETURN TO STARTFRAME", "Congratulation Message", JOptionPane.YES_NO_OPTION);
//                if (res == JOptionPane.YES_OPTION) {
//                    WIN.dispose();
//                    WelcomeFrame.firtPage();
//                } else {
//                    WIN.dispose();
//                }
//            }
//            nextPlayer();
//            WIN.setTime(0);
//        }
////        else MusicPlay.playMusic("sound\\ILLEGAL.WAV");
//    }


    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
        if (!hasPossibleMove()) {
            if (playerNumber!=1){
                WIN.getShowCurrentPlayer().setVisible(false);
            }
            if (hasSelectedLocation() && model.isValidMove(getSelectedLocation(), location)) {
                model.moveChessPiece(selectedLocation, location);
                MusicPlay.playMusic("sound\\MOVE2.WAV");
                src = selectedLocation;
                dest = location;
                regretPlayer = currentPlayer;
                resetSelectedLocation();
                if (playerNumber!=1){
                    if (model.winner(playerNumber) != null) {
                        String current = "";
                        if (gamer1.getCurrentColor().equals(model.winner(playerNumber))) {
                            for(int i=0;i<WelcomeFrame.getPlaylist().size();i++){
                                if(WelcomeFrame.getPlaylist().get(i).getName().equals(gamer1.getName())){
                                    WelcomeFrame.getPlaylist().get(i).setWin( WelcomeFrame.getPlaylist().get(i).getWin()+1);
                                    current = WelcomeFrame.getPlaylist().get(i).getName();
                                }
                            }
                        } else if (gamer2.getCurrentColor().equals(model.winner(playerNumber))) {
                            for(int i=0;i<WelcomeFrame.getPlaylist().size();i++){
                                if(WelcomeFrame.getPlaylist().get(i).getName().equals(gamer2.getName())){
                                    WelcomeFrame.getPlaylist().get(i).setWin( WelcomeFrame.getPlaylist().get(i).getWin()+1);
                                    current = WelcomeFrame.getPlaylist().get(i).getName();
                                }
                            }
                        } else if (gamer3.getCurrentColor().equals(model.winner(playerNumber))) {
                            for(int i=0;i<WelcomeFrame.getPlaylist().size();i++){
                                if(WelcomeFrame.getPlaylist().get(i).getName().equals(gamer3.getName())){
                                    WelcomeFrame.getPlaylist().get(i).setWin( WelcomeFrame.getPlaylist().get(i).getWin()+1);
                                    current = WelcomeFrame.getPlaylist().get(i).getName();
                                }
                            }
                        } else {
                            for(int i=0;i<WelcomeFrame.getPlaylist().size();i++){
                                if(WelcomeFrame.getPlaylist().get(i).getName().equals(gamer4.getName())){
                                    WelcomeFrame.getPlaylist().get(i).setWin( WelcomeFrame.getPlaylist().get(i).getWin()+1);
                                    current = WelcomeFrame.getPlaylist().get(i).getName();
                                }
                            }
                        }
                        File document = new File("playerList\\playerlist.txt") ;
                        document.delete();
                        LoadAndSave.SaveList();

                        int res = JOptionPane.showConfirmDialog(null, String.format("Player : %s Have Won !!!\nWill You Return to StartFrame",current), "Congratulation Message", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION) {
                            WIN.dispose();
                            File Doc = new File("doc\\"+DOCUMENT.getTime()+".txt") ;
                            Doc.delete();
                            WelcomeFrame.firtPage();
                        } else {
                            WIN.dispose();
                        }

                    }
                }else {

                    if (taskWin()){
                        int res = JOptionPane.showConfirmDialog(null,"You Have Achieve The Task !!!\nThe Figure is Beautiful!!!", "Congratulation Message", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION) {
                            WIN.dispose();
                            WelcomeFrame.firtPage();
                        } else {
                            WIN.dispose();
                        }
                    }
                }
                model.setClickTimes(0);
                nextPlayer();
                WIN.setTime(0);
            }
            else { MusicPlay.playMusic("sound\\ILLEGAL.WAV"); }
        }
        else { MusicPlay.playMusic("sound\\ILLEGAL.WAV"); }
    }

//
//    @Override
//    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
//        ChessPiece piece = model.getChessPieceAt(location);
//        if ((piece.getColor().equals(currentPlayer)) && ((!hasSelectedLocation()) || (location.equals(getSelectedLocation())))) {
//            if (!hasSelectedLocation()) {
//                setSelectedLocation(location);
//            } else {
//                resetSelectedLocation();
//            }
//            component.setSelected(!component.isSelected());
////            MusicPlay.playMusic("sound\\CAPTURE2.WAV");
//            component.repaint();
//        }
//    }


    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        ChessPiece piece = model.getChessPieceAt(location);
        if (model.getClickTimes() == 0) {
            if (piece.getColor().equals(currentPlayer)) {
                model.showPossibleMove(location);
                if ((piece.getColor().equals(currentPlayer)) && ((!hasSelectedLocation()) || (location.equals(getSelectedLocation())))) {
                    if (!hasSelectedLocation()) {
                        setSelectedLocation(location);
                    } else {
                        resetSelectedLocation();
                    }
                    component.setSelected(!component.isSelected());
                    MusicPlay.playMusic("sound\\CAPTURE2.WAV");
                    component.repaint();
                }
                model.addClickTimes();
            }
            else { MusicPlay.playMusic("sound\\ILLEGAL.WAV"); }
        } else {
            if (piece.getColor().equals(currentPlayer)) {
                for (int i = 0; i < model.getDimension(); i++) {
                    for (int j = 0; j < model.getDimension(); j++) {
                        if (model.getChessPieceAt(i, j) != null) {
                            if (model.getChessPieceAt(i, j).getColor().equals(Color.white)) {
                                model.removeChessPieceAt(new ChessBoardLocation(i, j));
                            }
                        }
                    }
                }
                model.showPossibleMove(location);
                resetSelectedLocation();
                if ((piece.getColor().equals(currentPlayer)) && ((!hasSelectedLocation()) || (location.equals(getSelectedLocation())))) {
                    if (!hasSelectedLocation()) {
                        setSelectedLocation(location);
                    } else {
                        resetSelectedLocation();
                    }
                }
                component.setSelected(!component.isSelected());
                MusicPlay.playMusic("sound\\CAPTURE2.WAV");
                component.repaint();
                model.addClickTimes();
            } else if (piece.getColor().equals(Color.white)) {
                for (int i = 0; i < model.getDimension(); i++) {
                    for (int j = 0; j < model.getDimension(); j++) {
                        if (model.getChessPieceAt(i, j) != null) {
                            if (model.getChessPieceAt(i, j).getColor().equals(Color.white)) {
                                model.removeChessPieceAt(new ChessBoardLocation(i, j));
                            }
                        }
                    }
                }
                onPlayerClickSquare(location, view.getGridAt(location));
            }
            else { MusicPlay.playMusic("sound\\ILLEGAL.WAV"); }
        }
    }


    public boolean hasPossibleMove() {
        for (int i = 0; i < model.getDimension(); i++) {
            for (int j = 0; j < model.getDimension(); j++) {
                if (model.getChessPieceAt(i, j) != null) {
                    if (model.getChessPieceAt(i, j).getColor().equals(Color.white)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public void setWIN(GameFrame WIN) {
        this.WIN = WIN;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setDOCUMENT(Doc DOCUMENT) {
        this.DOCUMENT = DOCUMENT;
    }

    public boolean taskWin() {
        boolean is=true;
        ArrayList<ChessBoardLocation> win= new ArrayList<ChessBoardLocation>();
        for (TaskListener listener:listenerList) {
            win=listener.win();
        }
        for (ChessBoardLocation c:win) {
            if (model.getGridAt(c).getPiece()==null) {is=false; break;}
        }
        return is;
    }
    @Override
    public void registerListener(TaskListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(TaskListener listener) {
        listenerList.remove(listener);
    }
}
