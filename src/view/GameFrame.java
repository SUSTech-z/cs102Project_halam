package view;

import controller.GameController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class GameFrame extends JFrame {
    private JButton showCurrentPlayer;
    private ChessBoard chessBoard;
    private GameController controller;
    private ChessBoardComponent chessBoardComponent;
    private int time=0;

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public GameFrame(int playerNumber) {
        chessBoardComponent = new ChessBoardComponent(760, 16);
        chessBoard = new ChessBoard(16);
        controller = new GameController(chessBoardComponent, chessBoard, playerNumber, this);
//        GameController.setWIN(this);
        setTitle("HALMA");
        setSize(930, 860);
        setLocationRelativeTo(null); // Center the window
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int count=0;
                for( File file: Objects.requireNonNull(new File("playerList").listFiles())){
                    count++;
                }
                if (count!=0){
                    File document = new File("playerList\\playerlist.txt") ;
                    document.delete();
                }
                LoadAndSave.SaveList();
                Doc doc = new Doc();
                for (int i = 0; i <chessBoard.getDimension() ; i++) {
                    for (int j = 0; j <chessBoard.getDimension() ; j++) {
                        if (chessBoard.getChessPieceAt(i,j)!=null){
                            if (chessBoard.getChessPieceAt(i,j).getColor().equals(Color.white)){
                                chessBoard.removeChessPieceAt(new ChessBoardLocation(i,j));
                            }
                        }
                    }
                }
                doc.setChessBoard(chessBoard);
                doc.setNameList(WelcomeFrame.getNameList());
                doc.setPlayerNumber(playerNumber);
                doc.setCurrentPlayer(controller.getCurrentPlayer());
                LoadAndSave.Save(doc);
                dispose();
                WelcomeFrame.firtPage();

            }
        });
        setLayout(null);
        if (playerNumber == 2) {
            JLabel player1 = new JLabel("Player1:");
            player1.setFont(new Font("Arial", Font.BOLD, 17));
            player1.setLocation(5, 0);
            player1.setSize(200, 20);
            add(player1);
            JLabel name1 = new JLabel(WelcomeFrame.getNameList()[0]);
            name1.setFont(new Font("Arial", Font.BOLD, 17));
            name1.setLocation(10, 20);
            name1.setSize(200, 20);
            add(name1);

            JLabel player2 = new JLabel("Player2:");
            player2.setFont(new Font("Arial", Font.BOLD, 17));
            player2.setLocation(831, 750);
            player2.setSize(200, 20);
            add(player2);
            JLabel name2 = new JLabel(WelcomeFrame.getNameList()[1]);
            name2.setFont(new Font("Arial", Font.BOLD, 17));
            name2.setLocation(835, 770);
            name2.setSize(200, 20);
            add(name2);

        } else if (playerNumber == 4) {
            JLabel player1 = new JLabel("Player1:");
            player1.setFont(new Font("Arial", Font.BOLD, 17));
            player1.setLocation(5, 0);
            player1.setSize(200, 20);
            add(player1);
            JLabel name1 = new JLabel(WelcomeFrame.getNameList()[0]);
            name1.setFont(new Font("Arial", Font.BOLD, 17));
            name1.setLocation(10, 20);
            name1.setSize(200, 20);
            add(name1);

            JLabel player2 = new JLabel("Player2:");
            player2.setFont(new Font("Arial", Font.BOLD, 17));
            player2.setLocation(5, 750);
            player2.setSize(200, 20);
            add(player2);
            JLabel name2 = new JLabel(WelcomeFrame.getNameList()[1]);
            name2.setFont(new Font("Arial", Font.BOLD, 17));
            name2.setLocation(10, 770);
            name2.setSize(200, 20);
            add(name2);

            JLabel player3 = new JLabel("Player3:");
            player3.setFont(new Font("Arial", Font.BOLD, 17));
            player3.setLocation(831, 750);
            player3.setSize(200, 20);
            add(player3);
            JLabel name3 = new JLabel(WelcomeFrame.getNameList()[2]);
            name3.setFont(new Font("Arial", Font.BOLD, 17));
            name3.setLocation(835, 770);
            name3.setSize(200, 20);
            add(name3);

            JLabel player4 = new JLabel("Player4:");
            player4.setFont(new Font("Arial", Font.BOLD, 17));
            player4.setLocation(831, 0);
            player4.setSize(200, 20);
            add(player4);
            JLabel name4 = new JLabel(WelcomeFrame.getNameList()[3]);
            name4.setFont(new Font("Arial", Font.BOLD, 17));
            name4.setLocation(835, 20);
            name4.setSize(200, 20);
            add(name4);
        }
        showCurrentPlayer = new JButton();
        showCurrentPlayer.setBackground(Color.GREEN);
        showCurrentPlayer.setVisible(true);
        showCurrentPlayer.setLocation(10, 40);
        showCurrentPlayer.setSize(40, 15);
        this.add(showCurrentPlayer);
        JButton show = new JButton();
        show.setBackground(Color.GREEN);
        show.setVisible(true);
        show.setLocation(75, 775);
        show.setSize(40, 15);
        add(show);

        JLabel statusLabel = new JLabel("Button Below the Name Shows the CurrentPlayer");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 17));
        statusLabel.setLocation(120, 775);
        statusLabel.setSize(400, 20);
        add(statusLabel);
        JLabel statusLabel1 = new JLabel("Remind : White Chesses Show the Possible Move");
        statusLabel1.setFont(new Font("Arial", Font.BOLD, 17));
        statusLabel1.setLocation(77, 795);
        statusLabel1.setSize(400, 15);
        add(statusLabel1);

        JButton button1 = new JButton();
        button1.setFont(new Font("Arial", Font.BOLD, 13));
        button1.setText("Regret");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getTime()==0&&controller.getDest()!=null&&controller.getSrc()!=null) {
                    MusicPlay.playMusic("sound\\NEWGAME.WAV");
                    chessBoard.moveChessPiece(controller.getDest(), controller.getSrc());
                    controller.setCurrentPlayer(controller.getRegretPlayer());
                    controller.setRegretPlayer(null);
                    controller.nextPlayer();
                    setTime(1);
                }
            }
        });
        button1.setLocation(630, 775);
        button1.setSize(90, 20);
        add(button1);
        chessBoardComponent.setLocation(75, 20);
        this.add(chessBoardComponent);


        JButton button2 = new JButton();
        button2.setText("Save");
        button2.setFont(new Font("Arial", Font.BOLD, 13));
//        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Button !"));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count=0;
                for( File file: Objects.requireNonNull(new File("playerList").listFiles())){
                    count++;
                }
                if (count!=0){
                    File document = new File("playerList\\playerlist.txt") ;
                    document.delete();
                }
                LoadAndSave.SaveList();
                Doc doc = new Doc();
                for (int i = 0; i <chessBoard.getDimension() ; i++) {
                    for (int j = 0; j <chessBoard.getDimension() ; j++) {
                        if (chessBoard.getChessPieceAt(i,j)!=null){
                            if (chessBoard.getChessPieceAt(i,j).getColor().equals(Color.white)){
                                chessBoard.removeChessPieceAt(new ChessBoardLocation(i,j));
                            }
                        }
                    }
                }
                doc.setChessBoard(chessBoard);
                doc.setNameList(WelcomeFrame.getNameList());
                doc.setPlayerNumber(playerNumber);
                doc.setCurrentPlayer(controller.getCurrentPlayer());
                LoadAndSave.Save(doc);
                dispose();
                WelcomeFrame.firtPage();
            }
        });
        button2.setLocation(740, 775);
        button2.setSize(70, 20);
        add(button2);
        chessBoardComponent.setLocation(75, 20);
        this.add(chessBoardComponent);
    }

    public GameFrame(Doc doc) {
        chessBoardComponent = new ChessBoardComponent(760, 16);
        chessBoard = new ChessBoard(16);
        controller = new GameController(chessBoardComponent, chessBoard, doc,this);
//        GameController.setWIN(this);
        setTitle("HALMA");
        setSize(930, 860);
        setLocationRelativeTo(null); // Center the window
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Doc newDoc = new Doc();
                for (int i = 0; i <chessBoard.getDimension() ; i++) {
                    for (int j = 0; j <chessBoard.getDimension() ; j++) {
                        if (chessBoard.getChessPieceAt(i,j)!=null){
                            if (chessBoard.getChessPieceAt(i,j).getColor().equals(Color.white)){
                                chessBoard.removeChessPieceAt(new ChessBoardLocation(i,j));
                            }
                        }
                    }
                }
                newDoc.setChessBoard(chessBoard);
                newDoc.setNameList(doc.getNameList());
                newDoc.setPlayerNumber(doc.getPlayerNumber());
                newDoc.setCurrentPlayer(controller.getCurrentPlayer());
                File document = new File("doc\\"+ doc.getTime() +".txt") ;
                document.delete();
                LoadAndSave.Save(newDoc);
                dispose();
                WelcomeFrame.firtPage();
            }
        });
        setLayout(null);
        if (doc.getPlayerNumber() == 2) {
            Player gamer1 = new Player();
            Player gamer2 = new Player();
            gamer1.setCurrentColor(doc.getPlayerColor()[0]);
            gamer2.setCurrentColor(doc.getPlayerColor()[1]);

            JLabel player1 = new JLabel("Player1:");
            player1.setFont(new Font("Arial", Font.BOLD, 17));
            player1.setLocation(5, 0);
            player1.setSize(200, 20);
            add(player1);
            JLabel name1 = new JLabel(doc.getNameList()[0]);
            name1.setFont(new Font("Arial", Font.BOLD, 17));
            name1.setLocation(10, 20);
            name1.setSize(200, 20);
            add(name1);

            JLabel player2 = new JLabel("Player2:");
            player2.setFont(new Font("Arial", Font.BOLD, 17));
            player2.setLocation(831, 750);
            player2.setSize(200, 20);
            add(player2);
            JLabel name2 = new JLabel(doc.getNameList()[1]);
            name2.setFont(new Font("Arial", Font.BOLD, 17));
            name2.setLocation(835, 770);
            name2.setSize(200, 20);
            add(name2);

            showCurrentPlayer = new JButton();
            showCurrentPlayer.setBackground(Color.GREEN);
            showCurrentPlayer.setVisible(true);
            if (doc.getCurrentPlayer().equals(gamer1.getCurrentColor())){
                showCurrentPlayer.setLocation(10, 40);
            }else{
                showCurrentPlayer.setLocation(835, 790);
            }
            showCurrentPlayer.setSize(40, 10);
            this.add(showCurrentPlayer);
        } else if (doc.getPlayerNumber() == 4) {
            JLabel player1 = new JLabel("Player1:");
            player1.setFont(new Font("Arial", Font.BOLD, 17));
            player1.setLocation(5, 0);
            player1.setSize(200, 20);
            add(player1);
            JLabel name1 = new JLabel(doc.getNameList()[0]);
            name1.setFont(new Font("Arial", Font.BOLD, 17));
            name1.setLocation(10, 20);
            name1.setSize(200, 20);
            add(name1);

            JLabel player2 = new JLabel("Player2:");
            player2.setFont(new Font("Arial", Font.BOLD, 17));
            player2.setLocation(5, 750);
            player2.setSize(200, 20);
            add(player2);
            JLabel name2 = new JLabel(doc.getNameList()[1]);
            name2.setFont(new Font("Arial", Font.BOLD, 17));
            name2.setLocation(10, 770);
            name2.setSize(200, 20);
            add(name2);

            JLabel player3 = new JLabel("Player3:");
            player3.setFont(new Font("Arial", Font.BOLD, 17));
            player3.setLocation(831, 750);
            player3.setSize(200, 20);
            add(player3);
            JLabel name3 = new JLabel(doc.getNameList()[2]);
            name3.setFont(new Font("Arial", Font.BOLD, 17));
            name3.setLocation(835, 770);
            name3.setSize(200, 20);
            add(name3);

            JLabel player4 = new JLabel("Player4:");
            player4.setFont(new Font("Arial", Font.BOLD, 17));
            player4.setLocation(831, 0);
            player4.setSize(200, 20);
            add(player4);
            JLabel name4 = new JLabel(doc.getNameList()[3]);
            name4.setFont(new Font("Arial", Font.BOLD, 17));
            name4.setLocation(835, 20);
            name4.setSize(200, 20);
            add(name4);

            showCurrentPlayer = new JButton();
            showCurrentPlayer.setBackground(Color.GREEN);
            showCurrentPlayer.setVisible(true);
            Player gamer1 = new Player();
            Player gamer2 = new Player();
            Player gamer3 = new Player();
            Player gamer4 = new Player();
            gamer1.setCurrentColor(doc.getPlayerColor()[0]);
            gamer2.setCurrentColor(doc.getPlayerColor()[1]);
            gamer3.setCurrentColor(doc.getPlayerColor()[2]);
            gamer4.setCurrentColor(doc.getPlayerColor()[3]);
            if (doc.getCurrentPlayer().equals(gamer1.getCurrentColor())){
                showCurrentPlayer.setLocation(20, 40);
            }else if (doc.getCurrentPlayer().equals(gamer2.getCurrentColor())){
                showCurrentPlayer.setLocation(20, 790);
            }else if (doc.getCurrentPlayer().equals(gamer3.getCurrentColor())){
                showCurrentPlayer.setLocation(845, 790);
            }else {
                showCurrentPlayer.setLocation(845, 40);
            }
            showCurrentPlayer.setSize(20, 15);
            this.add(showCurrentPlayer);
        }

        JButton show = new JButton();
        show.setBackground(Color.GREEN);
        show.setVisible(true);
        show.setLocation(75, 775);
        show.setSize(40, 15);
        add(show);

        JLabel statusLabel = new JLabel("Button Below the Name Shows the CurrentPlayer");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 17));
        statusLabel.setLocation(120, 775);
        statusLabel.setSize(400, 15);
        add(statusLabel);

        JLabel statusLabel1 = new JLabel("Remind : White Chesses Show the Possible Move");
        statusLabel1.setFont(new Font("Arial", Font.BOLD, 17));
        statusLabel1.setLocation(77, 795);
        statusLabel1.setSize(400, 15);
        add(statusLabel1);

        JButton button1 = new JButton();
        button1.setText("Regret");
        button1.setFont(new Font("Arial", Font.BOLD, 13));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getTime()==0&&controller.getDest()!=null&&controller.getSrc()!=null) {
                    MusicPlay.playMusic("sound\\NEWGAME.WAV");
                    chessBoard.moveChessPiece(controller.getDest(), controller.getSrc());
                    controller.setCurrentPlayer(controller.getRegretPlayer());
                    controller.setRegretPlayer(null);
                    controller.nextPlayer();
                    setTime(1);
                }
            }
        });
        button1.setLocation(630, 775);
        button1.setSize(90, 20);
        add(button1);
        chessBoardComponent.setLocation(75, 20);
        this.add(chessBoardComponent);


        JButton button2 = new JButton();
        button2.setText("Save");
        button2.setFont(new Font("Arial", Font.BOLD, 13));
//        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Button !"));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Doc newDoc = new Doc();
                for (int i = 0; i <chessBoard.getDimension() ; i++) {
                    for (int j = 0; j <chessBoard.getDimension() ; j++) {
                        if (chessBoard.getChessPieceAt(i,j)!=null){
                            if (chessBoard.getChessPieceAt(i,j).getColor().equals(Color.white)){
                                chessBoard.removeChessPieceAt(new ChessBoardLocation(i,j));
                            }
                        }
                    }
                }
                newDoc.setChessBoard(chessBoard);
                newDoc.setNameList(doc.getNameList());
                newDoc.setPlayerNumber(doc.getPlayerNumber());
                newDoc.setCurrentPlayer(controller.getCurrentPlayer());
                File document = new File("doc\\"+ doc.getTime() +".txt") ;
                document.delete();
                LoadAndSave.Save(newDoc);
                dispose();
                WelcomeFrame.firtPage();
            }
        });
        button2.setLocation(740, 775);
        button2.setSize(70, 20);
        add(button2);
        chessBoardComponent.setLocation(75, 20);
        this.add(chessBoardComponent);

    }
    public GameFrame(int playerNumber , int taskNumber) {
        chessBoardComponent = new ChessBoardComponent(760, 16, taskNumber);
        chessBoard = new ChessBoard(16);
        controller = new GameController(chessBoardComponent, chessBoard, playerNumber, this);
        controller.setWIN(this);

        setTitle("Task");
        setSize(930, 860);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
                WelcomeFrame.firtPage();
            }
        });
        setLayout(null);

        JLabel statusLabel = new JLabel("Please Move Your Chess So That You can Draw The Specified Figure!");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 17));
        statusLabel.setLocation(187, 785);
        statusLabel.setSize(600, 20);
        add(statusLabel);


        chessBoardComponent.setLocation(75, 20);
        this.add(chessBoardComponent);
    }
    public GameFrame(int playerNumber, boolean computer) {
        chessBoardComponent = new ChessBoardComponent(760, 16);
        chessBoard = new ChessBoard(16);
        controller = new GameController(chessBoardComponent, chessBoard, this);
        setTitle("HALMA");
        setSize(930, 860);
        setLocationRelativeTo(null); // Center the window
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(null);
        JLabel player1 = new JLabel("Player1:");
        player1.setFont(new Font("Arial", Font.BOLD, 17));
        player1.setLocation(5, 0);
        player1.setSize(200, 20);
        add(player1);
        JLabel name1 = new JLabel(WelcomeFrame.getNameList()[0]);
        name1.setFont(new Font("Arial", Font.BOLD, 17));
        name1.setLocation(10, 20);
        name1.setSize(200, 20);
        add(name1);

        JLabel player2 = new JLabel("Player2:");
        player2.setFont(new Font("Arial", Font.BOLD, 17));
        player2.setLocation(831, 750);
        player2.setSize(200, 20);
        add(player2);
        JLabel name2 = new JLabel("RobotPlayer");
        name2.setFont(new Font("Arial", Font.BOLD, 17));
        name2.setLocation(835, 770);
        name2.setSize(200, 20);
        add(name2);

        showCurrentPlayer = new JButton();
        showCurrentPlayer.setBackground(Color.GREEN);
        showCurrentPlayer.setVisible(true);
        showCurrentPlayer.setLocation(10, 40);
        showCurrentPlayer.setSize(40, 15);
        this.add(showCurrentPlayer);
        JButton show = new JButton();
        show.setBackground(Color.GREEN);
        show.setVisible(true);
        show.setLocation(75, 775);
        show.setSize(40, 15);
        add(show);

        JLabel statusLabel = new JLabel("Button Below the Name Shows the CurrentPlayer");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 17));
        statusLabel.setLocation(120, 775);
        statusLabel.setSize(400, 20);
        add(statusLabel);
        JLabel statusLabel1 = new JLabel("Remind : White Chesses Show the Possible Move");
        statusLabel1.setFont(new Font("Arial", Font.BOLD, 17));
        statusLabel1.setLocation(77, 795);
        statusLabel1.setSize(400, 15);
        add(statusLabel1);

        JButton button1 = new JButton();
        button1.setFont(new Font("Arial", Font.BOLD, 13));
        button1.setText("Regret");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getTime()==0&&controller.getDest()!=null&&controller.getSrc()!=null) {
                    MusicPlay.playMusic("sound\\NEWGAME.WAV");
                    chessBoard.moveChessPiece(controller.getDest(), controller.getSrc());
                    controller.setCurrentPlayer(controller.getRegretPlayer());
                    controller.setRegretPlayer(null);
                    controller.nextPlayer();
                    setTime(1);
                }
            }
        });
        button1.setLocation(630, 775);
        button1.setSize(90, 20);
        add(button1);
        chessBoardComponent.setLocation(75, 20);
        this.add(chessBoardComponent);
    }

    public JButton getShowCurrentPlayer() {
        return showCurrentPlayer;
    }
}
