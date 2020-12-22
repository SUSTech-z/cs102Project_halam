package listener;

import controller.GameController;
import model.ChessBoardLocation;
import model.RobotPlayer;
import view.ChessComponent;
import view.SquareComponent;

import java.awt.*;

public interface RobotListener extends Listener{

    void RobotEvent(ChessBoardLocation src, ChessBoardLocation dest);

}
