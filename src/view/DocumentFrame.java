package view;

import controller.GameController;
import model.Doc;
import model.LoadAndSave;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class DocumentFrame extends JFrame {
//    private static int count=0 ;
    private  JFrame Window = new JFrame();
    private ArrayList<Doc> docList = new ArrayList<>();

    public DocumentFrame(){
        Window.setTitle("History Document");
        Window.setLayout(null);
//        加一个读取LoadAndSave到docList的方法
        for( File file: Objects.requireNonNull(new File("doc").listFiles())){
            docList.add(LoadAndSave.Load(file.getName()));
        }
        int count = 0;
        if (docList!=null){

            for (Doc doc:docList) {
                addDoc(doc,count);
                count++;
            }
        }
        Window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Window.setSize(850,45+60*(count));
        Window.setVisible(true);
        Window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public void addDoc(Doc doc ,int number){
        JPanel window = new JPanel();
        window.setSize(850,60);
        window.setLayout(null);
//        window.setBackground(new Color(87, 145, 250));
        JButton button1 = new JButton("Access");
        JButton button2 = new JButton("Delete");
        String str1 , str2;
        if (doc.getPlayerNumber()==2){
            String current;
            Player gamer1 = new Player();
            Player gamer2 = new Player();
            gamer1.setCurrentColor(doc.getPlayerColor()[0]);
            gamer2.setCurrentColor(doc.getPlayerColor()[1]);
            if (doc.getCurrentPlayer().equals(gamer1.getCurrentColor())){
                current = doc.getName(0);
            }else {
               current = doc.getName(1);
            }
             str1 = " Time:"+doc.getTime().substring(0,13)+":"+doc.getTime().substring(14,16)+":"+doc.getTime().substring(17,19);
             str2 = "  "+doc.getPlayerNumber()+" Players  Player1:"+doc.getName(0)+" Player2:"+doc.getName(1)+"  CurrentPlayer:"+ current ;
        }else {
            Player gamer1 = new Player();
            Player gamer2 = new Player();
            Player gamer3 = new Player();
            Player gamer4 = new Player();
            gamer1.setCurrentColor(doc.getPlayerColor()[0]);
            gamer2.setCurrentColor(doc.getPlayerColor()[1]);
            gamer3.setCurrentColor(doc.getPlayerColor()[2]);
            gamer4.setCurrentColor(doc.getPlayerColor()[3]);
            String current;
            if (doc.getCurrentPlayer().equals(gamer1.getCurrentColor())){
                current = doc.getName(0);
            }else if (doc.getCurrentPlayer().equals(gamer2.getCurrentColor())){
                current = doc.getName(1);
            }else if (doc.getCurrentPlayer().equals(gamer3.getCurrentColor())){
                current = doc.getName(2);
            }else {
                current = doc.getName(3);
            }
             str1 = " Time:"+doc.getTime().substring(0,13)+":"+doc.getTime().substring(14,16)+":"+doc.getTime().substring(17,19);
             str2 = "  "+doc.getPlayerNumber()+" Players  Player2:"+doc.getName(0)+" Player2:"+doc.getName(1)+" Player3:"+doc.getName(2)+" Player4:"+doc.getName(3)+"  CurrentPlayer:"+current;
        }
//        TextField number = new TextField(count);
        TextField field1 = new TextField(str1);
        TextField field2 = new TextField(str2);
//        number.setVisible(true);
        field1.setFont(new Font("Arial", Font.BOLD, 20));
        field2.setFont(new Font("Arial", Font.BOLD, 16));
        field1.setBackground( new Color(152, 220, 250));
        field2.setBackground( new Color(152, 220, 250));
        field1.setEditable(false);
        field2.setEditable(false);
        field1.setVisible(true);
        field2.setVisible(true);
        window.add(field1);
        window.add(field2);
        field1.setLocation(0,0);
        field1.setSize(670,30);
        field2.setLocation(0,30);
        field2.setSize(670,25);

        button1.setVisible(true);
        button1.setBackground(Color.white);
        button1.setLocation(720, 0);
        button1.setSize(100, 20);

        button2.setVisible(true);
        button2.setBackground(Color.white);
        button2.setLocation(720, 30);
        button2.setSize(100, 20);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (doc.isValid()){
                    Window.dispose();
                    WelcomeFrame.closeThis();
                    GameFrame Game = new GameFrame(docList.get(number));
                    Game.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Invalid Document!");
                    docList.remove(doc);
                    File document = new File("doc\\"+doc.getTime()+".txt") ;
                    document.delete();
                    Window.dispose();
                    new DocumentFrame();
                }
//                Window.dispose();
//                WelcomeFrame.closeThis();
//                GameFrame Game = new GameFrame(docList.get(number));
//                Game.setVisible(true);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                docList.remove(doc);
                File document = new File("doc\\"+doc.getTime()+".txt") ;
                document.delete();
                Window.dispose();
                new DocumentFrame();
            }
        });
        window.add(button1);
        window.add(button2);
        Window.add(window);
        window.setLocation(0,60*number);
        window.setVisible(true);
//        count++;
    }

    public Doc getDoc(int i) {
        return docList.get(i);
    }
}
