package model;

import view.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LoadAndSave {

    public static String getTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static void Save(Doc doc) {
        try {
            File file = new File("doc");
            String filename = file+"\\"+getTime()+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            if (doc.getPlayerNumber()==2) writer.write("2players:");
            else writer.write("4players:");
            for (String s:WelcomeFrame.getNameList()) if (s!=null) writer.write(s+",");
            for (Color c:WelcomeFrame.getColorlist()) if (c!=null) writer.write("["+c.getRed()+","+c.getGreen()+","+c.getBlue()+"]");
            writer.write("currentPlayer{color="+doc.getCurrentPlayer().getRed()+" "+
                    doc.getCurrentPlayer().getGreen()+" "+doc.getCurrentPlayer().getBlue()+"}");
            for (int i=0; i<doc.getChessBoard().getDimension(); i++) {
                for (int j=0; j<doc.getChessBoard().getDimension(); j++) {
                    if (doc.getChessBoard().getGridAt(i,j).getPiece()!=null)
                        writer.write(doc.getChessBoard().getGridAt(i,j).toString());
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {e.printStackTrace();}
    }
    public static void SaveList() {
        try {
            File file = new File("playerList");
            String filename = file+"\\"+"playerlist"+".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Player player:WelcomeFrame.getPlaylist()) {
                writer.write(player.toString());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {e.printStackTrace();}
    }
    public static Doc Load(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("doc\\"+fileName));
            Doc doc = new Doc();
            doc.setTime(fileName.substring(0,fileName.indexOf(".txt")));
            StringBuilder s = new StringBuilder();
            s.append(reader.readLine());
            reader.close();
            String info = s.toString();
            doc.setPlayerNumber(s.charAt(0)-'0');
            String[] nameList = new String[doc.getPlayerNumber()];
            int cou=info.indexOf(":",0)+1;
            for (int i=0; i<doc.getPlayerNumber(); i++) {
                nameList[i] = info.substring(cou,info.indexOf(",",cou));
                cou = info.indexOf(",",cou)+1;
            }
            doc.setNameList(nameList);
            int R,G,B;
            int pls=info.indexOf("[");
            Color[] colorList= new Color[doc.getPlayerNumber()];
            for (int i=0; i<doc.getPlayerNumber(); i++) {
                R = Integer.parseInt(info.substring(pls+1,info.indexOf(",",pls)));
                int lo1 = info.indexOf(",",pls);
                G = Integer.parseInt(info.substring(lo1+1,info.indexOf(",",lo1+1)));
                int lo2 = info.indexOf(",",lo1+1);
                B = Integer.parseInt(info.substring(lo2+1,info.indexOf("]",lo2)));
                pls=info.indexOf("[",lo2);
                colorList[i]=new Color(R,G,B);
            }
            doc.setPlayerColor(colorList);
            R = Integer.parseInt(info.substring(info.indexOf("color")+6,info.indexOf(" ")));
            int loc1 = info.indexOf(" ");
            G = Integer.parseInt(info.substring(loc1+1,info.indexOf(" ",loc1+1)));
            int loc2 = info.indexOf(" ",loc1+1);
            B = Integer.parseInt(info.substring(loc2+1,info.indexOf("}")));
            doc.setCurrentPlayer(new Color(R,G,B));
            ChessBoard chessBoard = new ChessBoard();
            int count=0;
            while (count < info.length()) {
                count = info.indexOf("Square",count+1);
                if (count == -1) break;
                int row,col;
                row = Integer.parseInt(info.substring(count+16,info.indexOf(",",count)));
                col = Integer.parseInt(info.substring(info.indexOf(",",count)+1,info.indexOf(";",count)));
                R = Integer.parseInt(info.substring(info.indexOf("Color",count)+6,info.indexOf(" ",count)));
                loc1 = info.indexOf(" ",count);
                G = Integer.parseInt(info.substring(loc1+1,info.indexOf(" ",loc1+1)));
                loc2 = info.indexOf(" ",loc1+1);
                B = Integer.parseInt(info.substring(loc2+1,info.indexOf("}",count)));
                if ((row>=0) && (row<16) && (col>=0) && (col<16)) chessBoard.placePieces(new ChessBoardLocation(row,col), new Color(R,G,B));
                else doc.setValid(false);
            }
            doc.setChessBoard(chessBoard);
            ArrayList<Color> colors = new ArrayList<Color>();
            for (int i=0; i<16; i++) {
                for (int j=0; j<16; j++) {
                    if (doc.getChessBoard().getGridAt(i,j).getPiece()!=null) {
                        if (colors.size()==0) colors.add(doc.getChessBoard().getGridAt(i,j).getPiece().getColor());
                        else {
                            boolean is=true;
                            for (Color c: colors) {
                                if (doc.getChessBoard().getGridAt(i,j).getPiece().getColor().equals(c)) {is=false; break;}
                            }
                            if (is) colors.add(doc.getChessBoard().getGridAt(i,j).getPiece().getColor());
                        }
                    }
                }
            }
            if (colors.size()!=doc.getPlayerNumber()) doc.setValid(false);
            else {
                int[] co = new int[colors.size()];
                for (int i=0; i<16 ;i++) {
                    for (int j=0 ;j<16; j++) {
                        for (int k=0; k<colors.size(); k++) {
                            if (doc.getChessBoard().getGridAt(i,j).getPiece()!=null) {
                                if (doc.getChessBoard().getGridAt(i,j).getPiece().getColor().equals(colors.get(k))) {co[k]+=1; break;}
                            }
                        }
                    }
                }
                for (int k=0; k<colors.size(); k++) {
                    if (((colors.size()==2) && (co[k]!=19)) || (colors.size()==4) && (co[k]!=13)) {doc.setValid(false); break;}
                }
            }
            return doc;
        } catch (IOException e) {e.printStackTrace(); return null;}
    }
    public static ArrayList<Player> LoadList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("playerList\\playerlist.txt"));
            ArrayList<Player> players=new ArrayList<Player>();
            StringBuilder s = new StringBuilder();
            s.append(reader.readLine());
            reader.close();
            String info = s.toString();
            int cou= -1;
            while (true) {
                cou=info.indexOf("Player",cou+1);
                if (cou==-1) break;
                String name; int win;
                name=info.substring(info.indexOf("name",cou)+5,info.indexOf(",",cou));
                win=Integer.parseInt(info.substring(info.indexOf("win",cou)+4,info.indexOf("}",cou)));
                players.add(new Player(name,win));
            }
            return players;
        } catch (IOException e) {e.printStackTrace(); return null;}
    }
}
