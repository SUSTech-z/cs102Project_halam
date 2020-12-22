package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import model.LoadAndSave;
import model.Player;


public class Scoreboard extends JFrame {
    private JFrame jf = new JFrame("Scoreboard");
    private JLayeredPane JLP = new JLayeredPane();
    private ArrayList<Player> playerlist =new ArrayList<>();
    private int[] winTimes ;
    private Player[] players;

    public  Scoreboard() {
        playerlist = LoadAndSave.LoadList();
        Player[] ranklist = rankTheWin();
        if (ranklist!=null){
            jf.setSize(700,630);
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLP.setBounds(0,0,700,630);
            JLP.setLayout(null);

            ImageIcon picture = new ImageIcon("pic\\Rank.png");
            Image image = picture.getImage();  //create an Image to change the size of the picture
            ImageIcon newpicture = new ImageIcon(image.getScaledInstance(700 , 630 , Image.SCALE_SMOOTH));
            JLabel jl3 = new JLabel(newpicture);
            JLP.add(jl3,JLayeredPane.DEFAULT_LAYER);
            jl3.setBounds(0, 0, 700, 600);

            JPanel panel = new JPanel();
            panel.setBounds(0, 0, 700, 300);
            JLabel jl = new JLabel(" Top Three", JLabel.CENTER);
            jl.setPreferredSize(new Dimension(680, 30));
            jl.setOpaque(false);
            JLP.add(jl,JLayeredPane.MODAL_LAYER);
            jl.setBounds(0, 40, 690, 100);
            jl.setFont(new Font("Arial", Font.BOLD, 75));
            jl.setForeground(new Color(255, 228, 76));

            if (players.length<=3){
                for (int i = 0; i <players.length ; i++) {
                    AddPlayer(players[i],i);
                }
            }else {
                for (int i = 0; i <3 ; i++) {
                    AddPlayer(players[i],i);
                }
            }

            jf.setContentPane(JLP);
            jf.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(null,"Sorry,Scoreboard is Empty!");
            jf.dispose();
        }


    }
        public Player[] rankTheWin() {
           if (playerlist.size()!=0){
               winTimes = new int[playerlist.size()];
               players = new Player[playerlist.size()];
               int count = 0;
               for (Player p:playerlist
               ) {
                   players[count]=p;
                   winTimes[count]=p.getWin();
                   count++;
               }
               for (int i = 0; i <winTimes.length ; i++) {
                   for (int j = i+1; j <winTimes.length ; j++) {
                       if (winTimes[i]<winTimes[j]){
                           int temp = winTimes[i];
                           Player play = players[i];
                           winTimes[i]=winTimes[j];
                           players[i]=players[j];
                           winTimes[j]=temp;
                           players[j]=play;
                       }
                   }
               }
               return players;
           }else {
               return null;
           }
        }

        public void AddPlayer(Player player,int i){
            if (i == 0){
                JPanel panel = new JPanel();
                panel.setBounds(0, 0, 700, 300);
                String str1 = player.getName();
                JLabel jl = new JLabel(str1, JLabel.CENTER);
                jl.setPreferredSize(new Dimension(680, 30));
                jl.setOpaque(false);
                JLP.add(jl,JLayeredPane.MODAL_LAYER);
                jl.setBounds(0, 135, 690, 100);
                jl.setFont(new Font("Arial", Font.BOLD, 40));
                jl.setForeground(new Color(255, 52, 63));
                String str2 = "Win "+player.getWin()+" Times";
                JLabel j2 = new JLabel(str2, JLabel.CENTER);
                j2.setPreferredSize(new Dimension(680, 30));
                j2.setOpaque(false);
                JLP.add(j2,JLayeredPane.MODAL_LAYER);
                j2.setBounds(0, 175, 690, 100);
                j2.setFont(new Font("Arial", Font.BOLD, 25));
                j2.setForeground(new Color(255, 52, 63));
            }else if (i == 1){
                JPanel panel = new JPanel();
                panel.setBounds(0, 0, 700, 300);
                String str1 = player.getName();
                JLabel jl = new JLabel(str1, JLabel.CENTER);
                jl.setPreferredSize(new Dimension(680, 30));
                jl.setOpaque(false);
                JLP.add(jl,JLayeredPane.MODAL_LAYER);
                jl.setBounds(-195, 230, 690, 100);
                jl.setFont(new Font("Arial", Font.BOLD, 40));
                jl.setForeground(new Color(255, 52, 63));
                String str2 = "Win "+player.getWin()+" Times";
                JLabel j2 = new JLabel(str2, JLabel.CENTER);
                j2.setPreferredSize(new Dimension(680, 30));
                j2.setOpaque(false);
                JLP.add(j2,JLayeredPane.MODAL_LAYER);
                j2.setBounds(-195, 270, 690, 100);
                j2.setFont(new Font("Arial", Font.BOLD, 25));
                j2.setForeground(new Color(255, 52, 63));

            }else {
                JPanel panel = new JPanel();
                panel.setBounds(0, 0, 700, 300);
                String str1 = player.getName();
                JLabel jl = new JLabel(str1, JLabel.CENTER);
                jl.setPreferredSize(new Dimension(680, 30));
                jl.setOpaque(false);
                JLP.add(jl,JLayeredPane.MODAL_LAYER);
                jl.setBounds(200, 260, 690, 100);
                jl.setFont(new Font("Arial", Font.BOLD, 40));
                jl.setForeground(new Color(255, 52, 63));
                String str2 = "Win "+player.getWin()+" Times";
                JLabel j2 = new JLabel(str2, JLabel.CENTER);
                j2.setPreferredSize(new Dimension(680, 30));
                j2.setOpaque(false);
                JLP.add(j2,JLayeredPane.MODAL_LAYER);
                j2.setBounds(200, 300, 690, 100);
                j2.setFont(new Font("Arial", Font.BOLD, 25));
                j2.setForeground(new Color(255, 52, 63));

            }
        }
}
