package view;

import java.io.File;
import javafx.scene.media.AudioClip;

public class MusicPlay {
    public static void playMusic(String filename) {
        AudioClip ac;
        ac = new AudioClip(new File(filename).toURI().toString());
        ac.play();   //开始播放
        //ac.setCycleCount(1000);  //设置循环次数
    }
}
