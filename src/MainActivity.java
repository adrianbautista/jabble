public class MainActivity {
  public static void main(String[] args) {
    Board board = new Board();
    Game jabbleGame = Game.initializationPrompt();
    board.displayBoard();
  }
}
