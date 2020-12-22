package view;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import controller.GameController;
import listener.InputListener;
import listener.Listenable;
import model.ChessBoard;
import model.Doc;
import model.LoadAndSave;
import model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class WelcomeFrame extends JFrame  implements Listenable<InputListener> {
     static JFrame jf = new JFrame("HALMA");
     static JLayeredPane JLP = new JLayeredPane();
     static String[] NameList = new String[4];
     static ArrayList<Player> playlist= new ArrayList<>();
     static Color[] colorlist;



    public static Color[] getColorlist() {
        return colorlist;
    }

    public static void setColorlist(Color[] colorlist) {
        WelcomeFrame.colorlist = colorlist;
    }

    public static void setPlaylist(ArrayList<Player> playlist) {
        WelcomeFrame.playlist = playlist;
    }

    public static ArrayList<Player> getPlaylist() {
        return playlist;
    }

    public static void firtPage(){
        int count=0;
        for( File file: Objects.requireNonNull(new File("playerList").listFiles())){
           count++;
        }
        if (count!=0){
           playlist = LoadAndSave.LoadList();
        }
        // 1.设置窗体大小和标题
        jf.setSize(700,600);
//        jf.setPreferredSize(new Dimension(700, 300));
        // 2.设置关闭窗口就是关闭程序
        jf.setLocationRelativeTo(null);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 最精准的布局模式空布局\
//        JLP.setPreferredSize(new Dimension(700, 300));
        JLP.setBounds(0,0,700,600);
        JLP.setLayout(null);
        // 设置定位
        ImageIcon picture = new ImageIcon("pic\\Halma.png");
        Image image = picture.getImage();  //create an Image to change the size of the picture
        ImageIcon newpicture = new ImageIcon(image.getScaledInstance(700 , 600 , Image.SCALE_SMOOTH));
        JLabel jl3 = new JLabel(newpicture);
        JLP.add(jl3,JLayeredPane.DEFAULT_LAYER);
        jl3.setBounds(0, 0, 700, 600);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 700, 300);
        JLabel jl = new JLabel("Welcome To  Halma ", JLabel.CENTER);
        jl.setPreferredSize(new Dimension(680, 30));
        jl.setOpaque(false);
        JLP.add(jl,JLayeredPane.MODAL_LAYER);
        jl.setBounds(0, 80, 690, 100);
        jl.setFont(new Font("Arial", Font.BOLD, 60));
        jl.setForeground(new Color(255, 255, 119));
        // 菜单栏
        // 新建一个菜单条
        JMenuBar jb = new JMenuBar();
        JLP.add(jb,JLayeredPane.MODAL_LAYER);
        jb.setBounds(170, 460, 350, 50);
        jb.setBackground(Color.black);
        // 新建一个菜单选项

        JMenu jmenu0 = new JMenu("NEW GAME");
        jmenu0.setFont(new Font("Arial", Font.BOLD, 17));
        jmenu0.setPreferredSize(new Dimension(200, 50));
        jmenu0.setForeground(Color.white);
        jmenu0.setLayout(new GridLayout(2,1));
        jmenu0.setOpaque(false);
        jb.add(jmenu0);
        // 新建一个菜单项
        JMenu jmenu01 = new JMenu("LOCAL");
        jmenu01.setFont(new Font("Arial", Font.BOLD, 15));
        jmenu01.setPreferredSize(new Dimension(150, 30));
        jmenu01.setForeground(Color.BLACK);
        jmenu01.setOpaque(false);
        jmenu0.add(jmenu01);

        JMenuItem jm = new JMenuItem("2 Players");
        jm.setFont(new Font("Arial", Font.BOLD, 15));
        JMenuItem jmi = new JMenuItem("4 Players");
        jmi.setFont(new Font("Arial", Font.BOLD, 15));
        jmenu01.add(jm);
        jmenu01.add(jmi);

        JMenu jmenu02 = new JMenu("COMPUTER");
        jmenu02.setFont(new Font("Arial", Font.BOLD, 15));
        jmenu02.setPreferredSize(new Dimension(150, 30));
        jmenu02.setForeground(Color.BLACK);
        jmenu02.setOpaque(false);
        jmenu0.add(jmenu02);

        JMenuItem jm4 = new JMenuItem("2 Players");
        jm4.setFont(new Font("Arial", Font.BOLD, 15));
        JMenuItem jmi4 = new JMenuItem("4 Players");
        jmi4.setFont(new Font("Arial", Font.BOLD, 15));
        jmenu02.add(jm4);
        jmenu02.add(jmi4);

        JMenu jmenu1 = new JMenu("RELOAD");
        jmenu1.setFont(new Font("Arial", Font.BOLD, 17));
        jmenu1.setPreferredSize(new Dimension(150, 50));
        jmenu1.setForeground(Color.white);
        jmenu1.setOpaque(false);
        jb.add(jmenu1);
        // 新建一个菜单项
        JMenuItem jm2 = new JMenuItem("Read The Document ");
        jm2.setFont(new Font("Arial", Font.BOLD, 15));
        jmenu1.add(jm2);
        jm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DocumentFrame();
            }
        });

        // 新建一个菜单选项

        JMenu jmenu2 = new JMenu("  TASK");
        jmenu2.setFont(new Font("Arial", Font.BOLD, 17));
        jmenu2.setOpaque(false);
        jmenu2.setForeground(Color.white);
        jmenu2.setPreferredSize(new Dimension(150, 50));
        jb.add(jmenu2);
        JMenuItem jm8 = new JMenuItem("Task Options");
        jm8.setFont(new Font("Arial", Font.BOLD, 15));
        jmenu2.add(jm8);


        JMenu jmenu3 = new JMenu("MORE");
        jmenu3.setFont(new Font("Arial", Font.BOLD, 17));
        jmenu3.setOpaque(false);
        jmenu3.setForeground(Color.white);
        jmenu3.setPreferredSize(new Dimension(150, 50));
        jb.add(jmenu3);
        // 新建一个菜单项
        JMenuItem jm0 = new JMenuItem("Exit Game");
        jm0.setFont(new Font("Arial", Font.BOLD, 15));
        JMenuItem jm1 = new JMenuItem("About Halma");
        jm1.setFont(new Font("Arial", Font.BOLD, 15));
        JMenuItem jm7 = new JMenuItem("Scoreboard");
        jm7.setFont(new Font("Arial", Font.BOLD, 15));
        jmenu3.add(jm7);
        jmenu3.add(jm1);
        jmenu3.add(jm0);

//
//        JMenu jmenu3 = new JMenu("Single");
//        jmenu3.setFont(new Font("Arial",Font.BOLD,17));
//        jmenu3.setOpaque(false);
//        jmenu3.setForeground(Color.white);
//        jmenu3.setPreferredSize(new Dimension(150, 50));
//        jb.add(jmenu3);


        // 以下是显示位移的地方
        // 放置图片

        //开始监听事件
        jm0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeThis();
            }
        });
        jm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutHalma();
            }
        });
        jm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoPlayer();
            }
        });
        jm4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoPlayerComputer();
            }
        });
        jmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FourPlayer();
            }
        });
        jm7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Scoreboard();
            }
        });
        jm8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TaskChoose();
            }
        });
        // 3.设置窗体可见
//        设置窗体可见
        jf.setContentPane(JLP);
        jf.setVisible(true);
    }
    public static void closeThis(){
        jf.dispose();
    }

        @Override
    public void registerListener(InputListener listener) {
    }

    @Override
    public void unregisterListener(InputListener listener) {
    }

    public static void TwoPlayerComputer(){
        colorlist=new Color[4];
        colorlist[1]=Color.BLACK;
        TextField name1;

        Dialog d = new Dialog(jf,"Player Information",true);
        Label note = new Label("Please Enter Your English Name And Choose Your Color");


        Panel pa = new Panel();
        pa.setLayout(new GridLayout(2,1));
        pa.add(new Label("Player"));
        pa.add(new Label("Color:"));

        Panel pc = new Panel();
        Panel color1 = new Panel();
        pc.setLayout(new GridLayout(2,1));
        name1 = new TextField();
        pc.add(name1);
        pc.add(color1);
        color1.setLayout(new GridLayout(1,8));
        JButton but1 = new JButton();
        but1.setBackground(Color.BLACK);
        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.BLACK;
                }else if (colorlist[1]==Color.BLACK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.BLACK;
                }
            }
        });
        color1.add(but1);
        JButton but2 = new JButton();
        but2.setBackground(Color.GREEN);
        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.GREEN;
                }else if (colorlist[1]==Color.GREEN){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.GREEN;
                }

            }
        });
        color1.add(but2);
        JButton but3 = new JButton();
        but3.setBackground(Color.BLUE);
        but3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.BLUE;
                }else if (colorlist[1]==Color.BLUE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.BLUE;
                }

            }
        });
        color1.add(but3);
        JButton but4 = new JButton();
        but4.setBackground(Color.RED);
        but4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.RED;
                }else if (colorlist[1]==Color.RED){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.RED;
                }
            }
        });
        color1.add(but4);
        JButton but5 = new JButton();
        but5.setBackground(Color.PINK);
        but5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.PINK;
                }else if (colorlist[1]==Color.PINK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.PINK;
                }

            }
        });
        color1.add(but5);
        JButton but6 = new JButton();
        but6.setBackground(Color.LIGHT_GRAY);
        but6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.LIGHT_GRAY;
                }else if (colorlist[1]==Color.LIGHT_GRAY){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.LIGHT_GRAY;
                }

            }
        });
        color1.add(but6);
        JButton but7 = new JButton();
        but7.setBackground(Color.ORANGE);
        but7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.ORANGE;
                }else if (colorlist[1]==Color.ORANGE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.ORANGE;
                }

            }
        });
        color1.add(but7);
        JButton but8 = new JButton();
        but8.setBackground(Color.YELLOW);
        but8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.YELLOW;
                }else if (colorlist[1]==Color.YELLOW){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.YELLOW;
                }

            }
        });
        color1.add(but8);
        Panel pb = new Panel();
        pb.setLayout(new GridLayout(1,2));
        Button submit = new Button("Enter");
        Button cancel = new Button("Return");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NameList[0] = name1.getText();
                if (!NameList[0].equals("")&&colorlist[0]!=null){
                    if (!NameList[0].equals("RobotPlayer")){
                        d.dispose();
                        closeThis();
                        setRobotGameFrame(2);
                    }else {
                        JOptionPane.showMessageDialog(null,"The Name is Duplication!");
                    }
                }else {
                    if (NameList[0].equals("")){
                        JOptionPane.showMessageDialog(null,"Player Please Enter Your Name!");
                    }else if (colorlist[0]==null){
                        JOptionPane.showMessageDialog(null,"Player Please Choose Your Color!");
                    }
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.dispose();
            }
        });
        pb.add(submit);
        pb.add(cancel);

        d.add(note,"North");
        d.add(pa,"West");
        d.add(pc,"Center");
        d.add(pb,"South");
        d.setSize(500,200);
        d.setLocation(770,450);
        d.setVisible(true);
    }

    public static void TwoPlayer(){
        colorlist=new Color[4];
        TextField name1;
        TextField name2;

        Dialog d = new Dialog(jf,"Player Information",true);
        Label note = new Label("Please Enter Your English Name And Choose Your Color");


        Panel pa = new Panel();
        pa.setLayout(new GridLayout(4,1));
        pa.add(new Label("Player1"));
        pa.add(new Label("Color:"));
        pa.add(new Label("Player2"));
        pa.add(new Label("Color:"));

        Panel pc = new Panel();
        Panel color1 = new Panel();
        Panel color2 = new Panel();
        pc.setLayout(new GridLayout(4,1));
        name1 = new TextField();
        color1.setLayout(new GridLayout(1,8));
        JButton but1 = new JButton();
        but1.setBackground(Color.BLACK);
        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.BLACK;
                }else if (colorlist[1]==Color.BLACK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.BLACK;
                }
            }
        });
        color1.add(but1);
        JButton but2 = new JButton();
        but2.setBackground(Color.GREEN);
        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.GREEN;
                }else if (colorlist[1]==Color.GREEN){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.GREEN;
                }

            }
        });
        color1.add(but2);
        JButton but3 = new JButton();
        but3.setBackground(Color.BLUE);
        but3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.BLUE;
                }else if (colorlist[1]==Color.BLUE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.BLUE;
                }

            }
        });
        color1.add(but3);
        JButton but4 = new JButton();
        but4.setBackground(Color.RED);
        but4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.RED;
                }else if (colorlist[1]==Color.RED){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.RED;
                }
            }
        });
        color1.add(but4);
        JButton but5 = new JButton();
        but5.setBackground(Color.PINK);
        but5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.PINK;
                }else if (colorlist[1]==Color.PINK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.PINK;
                }

            }
        });
        color1.add(but5);
        JButton but6 = new JButton();
        but6.setBackground(Color.LIGHT_GRAY);
        but6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.LIGHT_GRAY;
                }else if (colorlist[1]==Color.LIGHT_GRAY){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.LIGHT_GRAY;
                }

            }
        });
        color1.add(but6);
        JButton but7 = new JButton();
        but7.setBackground(Color.ORANGE);
        but7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.ORANGE;
                }else if (colorlist[1]==Color.ORANGE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.ORANGE;
                }

            }
        });
        color1.add(but7);
        JButton but8 = new JButton();
        but8.setBackground(Color.YELLOW);
        but8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null){
                    colorlist[0]=Color.YELLOW;
                }else if (colorlist[1]==Color.YELLOW){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.YELLOW;
                }

            }
        });
        color1.add(but8);
        name2 = new TextField();
        color2.setLayout(new GridLayout(1,8));
        JButton But1 = new JButton();
        But1.setBackground(Color.BLACK);
        But1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null){
                    colorlist[1]=Color.BLACK;
                }else if (colorlist[0]==Color.BLACK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.BLACK;
                }

            }
        });
        color2.add(But1);
        JButton But2 = new JButton();
        But2.setBackground(Color.GREEN);
        But2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null){
                    colorlist[1]=Color.GREEN;
                }else if (colorlist[0]==Color.GREEN){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.GREEN;
                }

            }
        });
        color2.add(But2);
        JButton But3 = new JButton();
        But3.setBackground(Color.BLUE);
        But3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null){
                    colorlist[1]=Color.BLUE;
                }else if (colorlist[0]==Color.BLUE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.BLUE;
                }

            }
        });
        color2.add(But3);
        JButton But4 = new JButton();
        But4.setBackground(Color.RED);
        But4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null){
                    colorlist[1]=Color.RED;
                }else if (colorlist[0]==Color.RED){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.RED;
                }
            }
        });
        color2.add(But4);
        JButton But5 = new JButton();
        But5.setBackground(Color.PINK);
        But5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null){
                    colorlist[1]=Color.PINK;
                }else if (colorlist[0]==Color.PINK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.PINK;
                }
            }
        });
        color2.add(But5);
        JButton But6 = new JButton();
        But6.setBackground(Color.LIGHT_GRAY);
        But6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null){
                    colorlist[1]=Color.LIGHT_GRAY;
                }else if (colorlist[0]==Color.LIGHT_GRAY){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.LIGHT_GRAY;
                }

            }
        });
        color2.add(But6);
        JButton But7 = new JButton();
        But7.setBackground(Color.ORANGE);
        But7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null){
                    colorlist[1]=Color.ORANGE;
                }else if (colorlist[0]==Color.ORANGE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.ORANGE;
                }

            }
        });
        color2.add(But7);
        JButton But8 = new JButton();
        But8.setBackground(Color.YELLOW);
        But8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null){
                    colorlist[1]=Color.YELLOW;
                }else if (colorlist[0]==Color.YELLOW){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.YELLOW;
                }

            }
        });
        color2.add(But8);
//        name2.setEchoChar('*');
        pc.add(name1);
        pc.add(color1);
        pc.add(name2);
        pc.add(color2);

        Panel pb = new Panel();
        pb.setLayout(new GridLayout(1,2));
        Button submit = new Button("Enter");
        Button cancel = new Button("Return");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NameList[0] = name1.getText();
                NameList[1] = name2.getText();
                if (!NameList[0].equals("")&&!NameList[1].equals("")&&colorlist[0]!=null&&colorlist[1]!=null){
                    if (!NameList[0].equals(NameList[1])){
                        d.dispose();
                        closeThis();
                        setGameFrame(2);
                    }else {
                        JOptionPane.showMessageDialog(null,"The Name is Duplication!");
                    }
                }else {
                    if (NameList[0].equals("")){
                        JOptionPane.showMessageDialog(null,"Player1 Please Enter Your Name!");
                    }else if (NameList[1].equals("")){
                        JOptionPane.showMessageDialog(null,"Player2 Please Enter Your Name!");
                    }else if (colorlist[0]==null){
                        JOptionPane.showMessageDialog(null,"Player1 Please Choose Your Color!");
                    }else {
                        JOptionPane.showMessageDialog(null,"Player2 Please Choose Your Color!");
                    }
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.dispose();
            }
        });
        pb.add(submit);
        pb.add(cancel);

        d.add(note,"North");
        d.add(pa,"West");
        d.add(pc,"Center");
        d.add(pb,"South");
        d.setSize(500,200);
        d.setLocation(770,450);
        d.setVisible(true);
    }
    public static void FourPlayer(){
        colorlist=new Color[4];
        TextField name1;
        TextField name2;
        TextField name3;
        TextField name4;

        Dialog d = new Dialog(jf,"Player Information",true);
        Label note = new Label("Please Enter Your Name");

        Panel pa = new Panel();
        pa.setLayout(new GridLayout(8,1));
        pa.add(new Label("Player1"));
        pa.add(new Label("Color:"));
        pa.add(new Label("Player2"));
        pa.add(new Label("Color:"));
        pa.add(new Label("Player3"));
        pa.add(new Label("Color:"));
        pa.add(new Label("Player4"));
        pa.add(new Label("Color:"));

        Panel pc = new Panel();
        Panel color1 = new Panel();
        Panel color2 = new Panel();
        Panel color3 = new Panel();
        Panel color4 = new Panel();
        pc.setLayout(new GridLayout(8,1));
        name1 = new TextField();
        color1.setLayout(new GridLayout(1,8));
        JButton but1 = new JButton();
        but1.setBackground(Color.BLACK);
        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[0]=Color.BLACK;
                }else if (colorlist[1]==Color.BLACK||colorlist[2]==Color.BLACK||colorlist[3]==Color.BLACK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.BLACK;
                }
            }
        });
        color1.add(but1);
        JButton but2 = new JButton();
        but2.setBackground(Color.GREEN);
        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[0]=Color.GREEN;
                }else if (colorlist[1]==Color.GREEN||colorlist[2]==Color.GREEN||colorlist[3]==Color.GREEN){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.GREEN;
                }

            }
        });
        color1.add(but2);
        JButton but3 = new JButton();
        but3.setBackground(Color.BLUE);
        but3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[0]=Color.BLUE;
                }else if (colorlist[1]==Color.BLUE||colorlist[2]==Color.BLUE||colorlist[3]==Color.BLUE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.BLUE;
                }

            }
        });
        color1.add(but3);
        JButton but4 = new JButton();
        but4.setBackground(Color.RED);
        but4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[0]=Color.RED;
                }else if (colorlist[1]==Color.RED||colorlist[2]==Color.RED||colorlist[3]==Color.RED){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.RED;
                }
            }
        });
        color1.add(but4);
        JButton but5 = new JButton();
        but5.setBackground(Color.PINK);
        but5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[0]=Color.PINK;
                }else if (colorlist[1]==Color.PINK||colorlist[2]==Color.PINK||colorlist[3]==Color.PINK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.PINK;
                }

            }
        });
        color1.add(but5);
        JButton but6 = new JButton();
        but6.setBackground(Color.LIGHT_GRAY);
        but6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[0]=Color.LIGHT_GRAY;
                }else if (colorlist[1]==Color.LIGHT_GRAY||colorlist[2]==Color.LIGHT_GRAY||colorlist[3]==Color.LIGHT_GRAY){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.LIGHT_GRAY;
                }

            }
        });
        color1.add(but6);
        JButton but7 = new JButton();
        but7.setBackground(Color.ORANGE);
        but7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[0]=Color.ORANGE;
                }else if (colorlist[1]==Color.ORANGE||colorlist[2]==Color.ORANGE||colorlist[3]==Color.ORANGE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.ORANGE;
                }

            }
        });
        color1.add(but7);
        JButton but8 = new JButton();
        but8.setBackground(Color.YELLOW);
        but8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[1]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[0]=Color.YELLOW;
                }else if (colorlist[1]==Color.YELLOW||colorlist[2]==Color.YELLOW||colorlist[3]==Color.YELLOW){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[0]=Color.YELLOW;
                }

            }
        });
        color1.add(but8);

        name2 = new TextField();
        color2.setLayout(new GridLayout(1,8));
        JButton But1 = new JButton();
        But1.setBackground(Color.BLACK);
        But1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[1]=Color.BLACK;
                }else if (colorlist[0]==Color.BLACK||colorlist[2]==Color.BLACK||colorlist[3]==Color.BLACK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.BLACK;
                }

            }
        });
        color2.add(But1);
        JButton But2 = new JButton();
        But2.setBackground(Color.GREEN);
        But2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[1]=Color.GREEN;
                }else if (colorlist[0]==Color.GREEN||colorlist[2]==Color.GREEN||colorlist[3]==Color.GREEN){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.GREEN;
                }

            }
        });
        color2.add(But2);
        JButton But3 = new JButton();
        But3.setBackground(Color.BLUE);
        But3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[1]=Color.BLUE;
                }else if (colorlist[0]==Color.BLUE||colorlist[2]==Color.BLUE||colorlist[3]==Color.BLUE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.BLUE;
                }

            }
        });
        color2.add(But3);
        JButton But4 = new JButton();
        But4.setBackground(Color.RED);
        But4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[1]=Color.RED;
                }else if (colorlist[0]==Color.RED||colorlist[2]==Color.RED||colorlist[3]==Color.RED){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.RED;
                }
            }
        });
        color2.add(But4);
        JButton But5 = new JButton();
        But5.setBackground(Color.PINK);
        But5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[1]=Color.PINK;
                }else if (colorlist[0]==Color.PINK||colorlist[2]==Color.PINK||colorlist[3]==Color.PINK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.PINK;
                }
            }
        });
        color2.add(But5);
        JButton But6 = new JButton();
        But6.setBackground(Color.LIGHT_GRAY);
        But6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[1]=Color.LIGHT_GRAY;
                }else if (colorlist[0]==Color.LIGHT_GRAY||colorlist[2]==Color.LIGHT_GRAY||colorlist[3]==Color.LIGHT_GRAY){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.LIGHT_GRAY;
                }

            }
        });
        color2.add(But6);
        JButton But7 = new JButton();
        But7.setBackground(Color.ORANGE);
        But7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[1]=Color.ORANGE;
                }else if (colorlist[0]==Color.ORANGE||colorlist[2]==Color.ORANGE||colorlist[3]==Color.ORANGE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.ORANGE;
                }

            }
        });
        color2.add(But7);
        JButton But8 = new JButton();
        But8.setBackground(Color.YELLOW);
        But8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[2]==null&&colorlist[3]==null){
                    colorlist[1]=Color.YELLOW;
                }else if (colorlist[0]==Color.YELLOW||colorlist[2]==Color.YELLOW||colorlist[3]==Color.YELLOW){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[1]=Color.YELLOW;
                }

            }
        });
        color2.add(But8);

        name3 = new TextField();
        color3.setLayout(new GridLayout(1,8));
        JButton bUt1 = new JButton();
        bUt1.setBackground(Color.BLACK);
        bUt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[3]==null){
                    colorlist[2]=Color.BLACK;
                }else if (colorlist[0]==Color.BLACK||colorlist[1]==Color.BLACK||colorlist[3]==Color.BLACK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[2]=Color.BLACK;
                }

            }
        });
        color3.add(bUt1);
        JButton bUt2 = new JButton();
        bUt2.setBackground(Color.GREEN);
        bUt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[3]==null){
                    colorlist[2]=Color.GREEN;
                }else if (colorlist[0]==Color.GREEN||colorlist[1]==Color.GREEN||colorlist[3]==Color.GREEN){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[2]=Color.GREEN;
                }

            }
        });
        color3.add(bUt2);
        JButton bUt3 = new JButton();
        bUt3.setBackground(Color.BLUE);
        bUt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[3]==null){
                    colorlist[2]=Color.BLUE;
                }else if (colorlist[0]==Color.BLUE||colorlist[1]==Color.BLUE||colorlist[3]==Color.BLUE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[2]=Color.BLUE;
                }

            }
        });
        color3.add(bUt3);
        JButton bUt4 = new JButton();
        bUt4.setBackground(Color.RED);
        bUt4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[3]==null){
                    colorlist[2]=Color.RED;
                }else if (colorlist[0]==Color.RED||colorlist[1]==Color.RED||colorlist[3]==Color.RED){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[2]=Color.RED;
                }
            }
        });
        color3.add(bUt4);
        JButton bUt5 = new JButton();
        bUt5.setBackground(Color.PINK);
        bUt5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[3]==null){
                    colorlist[2]=Color.PINK;
                }else if (colorlist[0]==Color.PINK||colorlist[1]==Color.PINK||colorlist[3]==Color.PINK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[2]=Color.PINK;
                }
            }
        });
        color3.add(bUt5);
        JButton bUt6 = new JButton();
        bUt6.setBackground(Color.LIGHT_GRAY);
        bUt6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[3]==null){
                    colorlist[2]=Color.LIGHT_GRAY;
                }else if (colorlist[0]==Color.LIGHT_GRAY||colorlist[1]==Color.LIGHT_GRAY||colorlist[3]==Color.LIGHT_GRAY){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[2]=Color.LIGHT_GRAY;
                }

            }
        });
        color3.add(bUt6);
        JButton bUt7 = new JButton();
        bUt7.setBackground(Color.ORANGE);
        bUt7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[3]==null){
                    colorlist[2]=Color.ORANGE;
                }else if (colorlist[0]==Color.ORANGE||colorlist[1]==Color.ORANGE||colorlist[3]==Color.ORANGE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[2]=Color.ORANGE;
                }

            }
        });
        color3.add(bUt7);
        JButton bUt8 = new JButton();
        bUt8.setBackground(Color.YELLOW);
        bUt8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[3]==null){
                    colorlist[2]=Color.YELLOW;
                }else if (colorlist[0]==Color.YELLOW||colorlist[1]==Color.YELLOW||colorlist[3]==Color.YELLOW){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[2]=Color.YELLOW;
                }

            }
        });
        color3.add(bUt8);
        name4 = new TextField();
        color4.setLayout(new GridLayout(1,8));
        JButton buT1 = new JButton();
        buT1.setBackground(Color.BLACK);
        buT1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[2]==null){
                    colorlist[2]=Color.BLACK;
                }else if (colorlist[0]==Color.BLACK||colorlist[1]==Color.BLACK||colorlist[2]==Color.BLACK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[2]=Color.BLACK;
                }

            }
        });
        color4.add(buT1);
        JButton buT2 = new JButton();
        buT2.setBackground(Color.GREEN);
        buT2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[2]==null){
                    colorlist[3]=Color.GREEN;
                }else if (colorlist[0]==Color.GREEN||colorlist[1]==Color.GREEN||colorlist[2]==Color.GREEN){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[3]=Color.GREEN;
                }

            }
        });
        color4.add(buT2);
        JButton buT3 = new JButton();
        buT3.setBackground(Color.BLUE);
        buT3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[2]==null){
                    colorlist[3]=Color.BLUE;
                }else if (colorlist[0]==Color.BLUE||colorlist[1]==Color.BLUE||colorlist[2]==Color.BLUE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[3]=Color.BLUE;
                }

            }
        });
        color4.add(buT3);
        JButton buT4 = new JButton();
        buT4.setBackground(Color.RED);
        buT4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[2]==null){
                    colorlist[3]=Color.RED;
                }else if (colorlist[0]==Color.RED||colorlist[1]==Color.RED||colorlist[2]==Color.RED){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[3]=Color.RED;
                }
            }
        });
        color4.add(buT4);
        JButton buT5 = new JButton();
        buT5.setBackground(Color.PINK);
        buT5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[2]==null){
                    colorlist[3]=Color.PINK;
                }else if (colorlist[0]==Color.PINK||colorlist[1]==Color.PINK||colorlist[2]==Color.PINK){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[3]=Color.PINK;
                }
            }
        });
        color4.add(buT5);
        JButton buT6 = new JButton();
        buT6.setBackground(Color.LIGHT_GRAY);
        buT6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[2]==null){
                    colorlist[3]=Color.LIGHT_GRAY;
                }else if (colorlist[0]==Color.LIGHT_GRAY||colorlist[1]==Color.LIGHT_GRAY||colorlist[2]==Color.LIGHT_GRAY){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[3]=Color.LIGHT_GRAY;
                }

            }
        });
        color4.add(buT6);
        JButton buT7 = new JButton();
        buT7.setBackground(Color.ORANGE);
        buT7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[2]==null){
                    colorlist[3]=Color.ORANGE;
                }else if (colorlist[0]==Color.ORANGE||colorlist[1]==Color.ORANGE||colorlist[2]==Color.ORANGE){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[3]=Color.ORANGE;
                }

            }
        });
        color4.add(buT7);
        JButton buT8 = new JButton();
        buT8.setBackground(Color.YELLOW);
        buT8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (colorlist[0]==null&&colorlist[1]==null&&colorlist[2]==null){
                    colorlist[3]=Color.YELLOW;
                }else if (colorlist[0]==Color.YELLOW||colorlist[1]==Color.YELLOW||colorlist[2]==Color.YELLOW){
                    JOptionPane.showMessageDialog(null,"The Color Has Been Chosen!");
                }else {
                    colorlist[3]=Color.YELLOW;
                }

            }
        });
        color4.add(buT8);
//        name2.setEchoChar('*');
        pc.add(name1);
        pc.add(color1);
        pc.add(name2);
        pc.add(color2);
        pc.add(name3);
        pc.add(color3);
        pc.add(name4);
        pc.add(color4);

        Panel pb = new Panel();
        pb.setLayout(new GridLayout(1,2));
        Button submit = new Button("Enter");
        Button cancel = new Button("Return");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NameList[0] = name1.getText();
                NameList[1] = name2.getText();
                NameList[2] = name3.getText();
                NameList[3] = name4.getText();
                if (!NameList[0].equals("")&&!NameList[1].equals("")&&!NameList[2].equals("")&&!NameList[3].equals("")&&colorlist[0]!=null&&colorlist[1]!=null&&colorlist[2]!=null&&colorlist[3]!=null){
                    if (!NameList[0].equals(NameList[1])&&!NameList[0].equals(NameList[2])&&!NameList[0].equals(NameList[3])&&!NameList[1].equals(NameList[2])&&!NameList[1].equals(NameList[3])&&!NameList[2].equals(NameList[3])){
                        d.dispose();
                        closeThis();
                        setGameFrame(4);
                    }else {
                        JOptionPane.showMessageDialog(null,"The Name is Duplication!");
                    }
                }else {
                    if (NameList[0].equals("")){
                        JOptionPane.showMessageDialog(null,"Player1 Please Enter Your Name!");
                    }else if (NameList[1].equals("")){
                        JOptionPane.showMessageDialog(null,"Player2 Please Enter Your Name!");
                    }else if (NameList[2].equals("")){
                        JOptionPane.showMessageDialog(null,"Player3 Please Enter Your Name!");
                    }else if (NameList[3].equals("")){
                        JOptionPane.showMessageDialog(null,"Player4 Please Enter Your Name!");
                    } else if (colorlist[0]==null){
                        JOptionPane.showMessageDialog(null,"Player1 Please Choose Your Color!");
                    }else if (colorlist[1]==null){
                        JOptionPane.showMessageDialog(null,"Player2 Please Choose Your Color!");
                    }else if (colorlist[2]==null){
                        JOptionPane.showMessageDialog(null,"Player3 Please Choose Your Color!");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Player4 Please Choose Your Color!");
                    }
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.dispose();
            }
        });
        pb.add(submit);
        pb.add(cancel);

        d.add(note,"North");
        d.add(pa,"West");
        d.add(pc,"Center");
        d.add(pb,"South");
        d.setSize(500,310);
        d.setLocation(770,450);
        d.setVisible(true);
    }
    public static void setGameFrame(int playerNumber){
        GameFrame Game = new GameFrame(playerNumber);
        Game.setVisible(true);
    }

    public static void setRobotGameFrame(int playerNumber){
        GameFrame Game = new GameFrame(playerNumber, true);
        Game.setVisible(true);
    }
    public static String[] getNameList() {
        return NameList;
    }

}

