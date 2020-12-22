import controller.GameController;
import model.ChessBoard;
import model.Doc;
import view.ChessBoardComponent;
import view.GameFrame;
import view.WelcomeFrame;

import javax.swing.*;
import java.awt.*;

public class Halma {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomeFrame::firtPage);
    }
}