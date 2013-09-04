import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Game {
  public static ArrayList<Character> tileBag = generateTileBag();
  public static final HashSet<String> wordDictionary = generateWordDictionary(Paths.get("words.txt"));
  public static final HashMap<Character, Integer> pointGuide = generatePointGuide();
  public static Scanner scanner = new Scanner(System.in);
  private Player players[];
  private boolean gameOn;
  public static Board board;
  private int turnCount;
  private HashMap lastTilePlayed;

  public Game(String p1, String p2) {
    this.players = new Player[2];
    this.players[0] = new Player(p1);
    this.players[1] = new Player(p2);
    this.board = new Board();
    this.turnCount = 0;
    this.lastTilePlayed = new HashMap();
  }

  public static boolean isWordReal(String word) {
    return wordDictionary.contains(word);
  }

  private static ArrayList<Character> generateTileBag() {
    ArrayList<Character> tiles = new ArrayList<Character>(100);

    for (int i = 0; i < 12; i++) {
      tiles.add('e');

      if (i < 10) {
        tiles.add('a');
        tiles.add('i');
      }

      if (i < 8) {
        tiles.add('o');
      }

      if (i < 6) {
        tiles.add('n');
        tiles.add('r');
        tiles.add('t');
      }

      if (i < 4) {
        tiles.add('l');
        tiles.add('s');
        tiles.add('u');
        tiles.add('d');
      }

      if (i < 3) {
        tiles.add('g');
      }

      if (i < 2) {
        tiles.add('b');
        tiles.add('c');
        tiles.add('m');
        tiles.add('p');
        tiles.add('f');
        tiles.add('h');
        tiles.add('v');
        tiles.add('w');
        tiles.add('y');
      }

      if (i < 1) {
        tiles.add('k');
        tiles.add('j');
        tiles.add('x');
        tiles.add('q');
        tiles.add('z');
      }
    }

    Collections.shuffle(tiles);
    return tiles;
  }

  public static boolean getTile(Player player) {
    if (tileBag.size() > 0) {
      player.rack.add(tileBag.remove(0));
      return true;
    }
    else {
      return false;
    }
  }

  private static HashSet<String> generateWordDictionary(Path path) {
    HashSet<String> words = new HashSet<String>();
    final Charset ENCODING = StandardCharsets.UTF_8;

    try (BufferedReader reader = Files.newBufferedReader(path, ENCODING)) {
      String line = null;
      while((line = reader.readLine()) != null) {
        words.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return words;
  }

  private static HashMap<Character, Integer> generatePointGuide() {
    HashMap<Character, Integer> points = new HashMap<>(26);
    for (int i = 1; i <= 10; i++ ) {

      switch (i) {
        case 1: points.put('a', i);
                points.put('e', i);
                points.put('i', i);
                points.put('o', i);
                points.put('t', i);
                points.put('r', i);
                points.put('n', i);
                points.put('u', i);
                points.put('s', i);
                points.put('l', i);
                break;

        case 2: points.put('d', i);
                points.put('g', i);
                break;

        case 3: points.put('b', i);
                points.put('c', i);
                points.put('m', i);
                points.put('p', i);
                break;

        case 4: points.put('f', i);
                points.put('h', i);
                points.put('v', i);
                points.put('w', i);
                points.put('y', i);
                break;

        case 5: points.put('k', i);
                break;

        case 8: points.put('j', i);
                points.put('x', i);
                break;

        case 10: points.put('q', i);
                 points.put('z', i);
                 break;
      }
    }

    return points;
  }

  public Player whoseTurn() {
    if (this.turnCount % 2 == 0) {
      return this.players[0];
    }
    else {
      return this.players[1];
    }
  }

  public static Game initializationPrompt() {
    System.out.println("Welcome to Jabble");
    System.out.println("Player 1, please enter your name: ");
    String player1Name = scanner.next();

    System.out.println("Player 2, please enter your name: ");
    String player2Name = scanner.next();

    return new Game(player1Name, player2Name);
  }

  public void gamePrompt() {
    gameOn = true;
    while (gameOn) {
        board.displayBoard();
        Player currentPlayer = this.whoseTurn();
        System.out.println(currentPlayer.getName() + " it is your turn.");

        boolean someonePlaying = true;

        while (someonePlaying == true) {
          System.out.println("Show your tile (r)ack, (g)ame board, (p)lay a word, (q)uit");
          char arg = scanner.next().charAt(0);
          switch (arg) {
            case 'r':
              System.out.println(currentPlayer.getRack().toString());
              break;

            case 'g':
              board.displayBoard();
              break;

            case 'p':
              if (this.turnCount == 0) {
                playCenterTile(currentPlayer);
              }

              boolean turnCheck = true;
              while (turnCheck == true) {
                turnCheck = playPrompt(currentPlayer);
              }

              if (currentPlayer.validateTurn()) {
                int tilesToGrab = 7 - currentPlayer.rack.size();
                while (tilesToGrab > 0) {
                  getTile(currentPlayer);
                  tilesToGrab -= 1;

                }
              }

              someonePlaying = false;
              this.turnCount += 1;
              break;

            case 'q':
              int score1 = this.players[0].getScore();
              int score2 = this.players[1].getScore();
              System.out.println("Player 1's score: " + score1);
              System.out.println("Player 2's score: " + score2);
              someonePlaying = false;
              gameOn = false;
              break;

            default:
              System.out.println("Did not compute, please try again");
              break;
          }

        }


        System.out.println("Show (g)ame board, (p)lay a word, (f)inish game, (q)uit");
        char arg = scanner.next().charAt(0);

        if ( arg == 'q') {
            int score1 = this.players[0].getScore();
            int score2 = this.players[1].getScore();
            System.out.println("Player 1's score: " + score1);
            System.out.println("Player 2's score: " + score2);
            gameOn = false;
        }
        if ( arg == 'p') {


        }
        else if (arg == 'g') {
            board.displayBoard();
        }
    }
  }

  public boolean playPrompt(Player player) {
    System.out.println("(a)dd tile or (f)inish turn?");
    char arg = scanner.next().charAt(0);
    if ( arg == 'a') {
      this.board.displayBoard();
      System.out.println("Do you want to make a (v)ertical or ho(r)izontal word?");
      char direction = scanner.next().charAt(0);

      System.out.println("Your rack: " + player.rack.toString());
      System.out.println("Which tile?");
      char tile = scanner.next().charAt(0);

      System.out.println("Which row?");
      int x = scanner.nextInt();

      System.out.println("Which column?");
      int y = scanner.nextInt();
      player.playSingleTile(tile, direction, x, y, this.board);

      this.lastTilePlayed.put('x', x);
      this.lastTilePlayed.put('y', y);
      this.lastTilePlayed.put('d', direction);

      return true;
    }
    else {
      int x = (int) this.lastTilePlayed.get('x');
      int y = (int) this.lastTilePlayed.get('y');
      char direction = (char) this.lastTilePlayed.get('d');
      player.finshTurn(direction, x, y, this.board);

      return false;
    }
  }

  public void playCenterTile(Player player) {
    this.board.displayBoard();
    System.out.println("Play the center tile first");

    System.out.println("Your rack: " + player.rack.toString());
    System.out.println("Which tile?");
    char tile = scanner.next().charAt(0);

    player.playFirstTile(tile, this.board);
  }

}
