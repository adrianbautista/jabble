import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class Game {
  public ArrayList<Character> tileBag;
  final HashSet<String> wordDictionary;
  private Player players[];


  public Game() {
    this.tileBag = generateTileBag();
    this.wordDictionary = generateWordDictionary(Paths.get("words.txt"));
    this.players = new Player[2];
    this.players[0] = new Player("Player 1");
    this.players[1] = new Player("Player 2");

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

    return tiles;
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
}
