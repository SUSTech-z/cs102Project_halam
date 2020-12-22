package model;

import java.awt.*;

public class Player {
    private Color currentColor;
    private String name;
    private int win;

    public Player(String name) {
        this.name = name;
    }
    public Player(){ }
    public Player(String name, int win) {
        this.name = name;
        this.win = win;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWin() {
        return win;
    }



    public void setWin(int win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ",win=" + win +
                '}';
    }
}
