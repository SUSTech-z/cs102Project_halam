package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskChoose extends JFrame {
   public TaskChoose(){
       JFrame window = new JFrame("Task Options");
       window.setLocation(550,170);
       window.setLayout(new GridLayout(4,4));
       JButton but1 = new JButton();
       ImageIcon picture1 = new ImageIcon("pic\\Task1.png");
       Image image1 = picture1.getImage();
       ImageIcon newpicture1 = new ImageIcon(image1.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but1.setIcon(newpicture1);
       but1.setSize(200,200);
       but1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
              GameFrame game = new GameFrame(1,1);
              game.setVisible(true);
              window.dispose();
              WelcomeFrame.closeThis();
           }
       });
       but1.setVisible(true);
       window.add(but1);
       JButton but2 = new JButton();
       ImageIcon picture2 = new ImageIcon("pic\\Task2.png");
       Image image2 = picture2.getImage();
       ImageIcon newpicture2 = new ImageIcon(image2.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but2.setIcon(newpicture2);
       but2.setSize(200,200);
       but2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,2);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but2.setVisible(true);
       window.add(but2);
       JButton but3 = new JButton();
       ImageIcon picture3 = new ImageIcon("pic\\Task3.png");
       Image image3 = picture3.getImage();
       ImageIcon newpicture3 = new ImageIcon(image3.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but3.setIcon(newpicture3);
       but3.setSize(200,200);
       but3.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,3);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but3.setVisible(true);
       window.add(but3);
       JButton but4 = new JButton();
       ImageIcon picture4 = new ImageIcon("pic\\Task4.png");
       Image image4 = picture4.getImage();
       ImageIcon newpicture4 = new ImageIcon(image4.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but4.setIcon(newpicture4);
       but4.setSize(200,200);
       but4.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,4);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but4.setVisible(true);
       window.add(but4);
       JButton but5 = new JButton();
       ImageIcon picture5 = new ImageIcon("pic\\Task5.png");
       Image image5 = picture5.getImage();
       ImageIcon newpicture5 = new ImageIcon(image5.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but5.setIcon(newpicture5);
       but5.setSize(200,200);
       but5.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,5);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but5.setVisible(true);
       window.add(but5);
       JButton but6 = new JButton();
       ImageIcon picture6 = new ImageIcon("pic\\Task6.png");
       Image image6 = picture6.getImage();
       ImageIcon newpicture6 = new ImageIcon(image6.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but6.setIcon(newpicture6);
       but6.setSize(200,200);
       but6.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,6);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but6.setVisible(true);
       window.add(but6);
       JButton but7 = new JButton();
       ImageIcon picture7 = new ImageIcon("pic\\Task7.png");
       Image image7 = picture7.getImage();
       ImageIcon newpicture7 = new ImageIcon(image7.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but7.setIcon(newpicture7);
       but7.setSize(200,200);
       but7.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,7);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but7.setVisible(true);
       window.add(but7);
       JButton but8 = new JButton();
       ImageIcon picture8 = new ImageIcon("pic\\Task8.png");
       Image image8 = picture8.getImage();
       ImageIcon newpicture8 = new ImageIcon(image8.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but8.setIcon(newpicture8);
       but8.setSize(200,200);
       but8.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,8);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but8.setVisible(true);
       window.add(but8);
       JButton but9 = new JButton();
       ImageIcon picture9 = new ImageIcon("pic\\Task9.png");
       Image image9 = picture9.getImage();
       ImageIcon newpicture9 = new ImageIcon(image9.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but9.setIcon(newpicture9);
       but9.setSize(200,200);
       but9.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,9);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but9.setVisible(true);
       window.add(but9);
       JButton but10 = new JButton();
       ImageIcon picture10 = new ImageIcon("pic\\Task10.png");
       Image image10 = picture10.getImage();
       ImageIcon newpicture10 = new ImageIcon(image10.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but10.setIcon(newpicture10);
       but10.setSize(200,200);
       but10.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,10);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but10.setVisible(true);
       window.add(but10);
       JButton but11 = new JButton();
       ImageIcon picture11 = new ImageIcon("pic\\Task11.png");
       Image image11 = picture11.getImage();
       ImageIcon newpicture11 = new ImageIcon(image11.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but11.setIcon(newpicture11);
       but11.setSize(200,200);
       but11.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,11);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but11.setVisible(true);
       window.add(but11);
       JButton but12 = new JButton();
       ImageIcon picture12 = new ImageIcon("pic\\Task12.png");
       Image image12 = picture12.getImage();
       ImageIcon newpicture12 = new ImageIcon(image12.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but12.setIcon(newpicture12);
       but12.setSize(200,200);
       but12.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,12);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but12.setVisible(true);
       window.add(but12);
       JButton but13 = new JButton();
       ImageIcon picture13 = new ImageIcon("pic\\Task13.png");
       Image image13 = picture13.getImage();
       ImageIcon newpicture13 = new ImageIcon(image13.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but13.setIcon(newpicture13);
       but13.setSize(200,200);
       but13.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,13);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but13.setVisible(true);
       window.add(but13);
       ImageIcon picture14 = new ImageIcon("pic\\Task14.png");
       Image image14 = picture14.getImage();
       ImageIcon newpicture14 = new ImageIcon(image14.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       JButton but14 = new JButton();
       but14.setIcon(newpicture14);
       but14.setSize(200,200);
       but14.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,14);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but14.setVisible(true);
       window.add(but14);
       JButton but15 = new JButton();
       ImageIcon picture15 = new ImageIcon("pic\\Task15.png");
       Image image15 = picture15.getImage();
       ImageIcon newpicture15 = new ImageIcon(image15.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but15.setIcon(newpicture15);
       but15.setSize(200,200);
       but15.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,15);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but15.setVisible(true);
       window.add(but15);
       JButton but16 = new JButton();
       ImageIcon picture16 = new ImageIcon("pic\\Task16.png");
       Image image16 = picture16.getImage();
       ImageIcon newpicture16 = new ImageIcon(image16.getScaledInstance(180 , 180 , Image.SCALE_SMOOTH));
       but16.setIcon(newpicture16);
       but16.setSize(200,200);
       but16.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               GameFrame game = new GameFrame(1,16);
               game.setVisible(true);
               window.dispose();
               WelcomeFrame.closeThis();
           }
       });
       but16.setVisible(true);
       window.add(but16);

       window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       window.setSize(800,820);
       window.setVisible(true);
   }

}
