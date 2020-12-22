package listener;

import model.ChessBoardLocation;

import java.util.ArrayList;

public interface TaskListener extends Listener {

    ArrayList<ChessBoardLocation> win();

}
