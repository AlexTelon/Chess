package main.game;

import main.board.Board;
import main.globals.Globals;
import main.player.Player;


/**
 * @author Alex Telon
 */
public class Game {
    private Board board = new Board();
    private Player player = new Player();
    private Globals.Side turn = Globals.Side.White;

/*
    private static void gameLogic() {
        while(board.getWinner() != null) {

        }
    }
*/
}
