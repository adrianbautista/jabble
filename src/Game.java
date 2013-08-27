import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Game {
  public static ArrayList<Character> tileBag;
  final HashSet<String> wordDictionary;
  private Player players[];
  final HashMap<Character, Integer> pointGuide;

  public Game() {
    tileBag = generateTileBag();
    this.wordDictionary = generateWordDictionary(Paths.get("words.txt"));
    this.pointGuide = generatePointGuide();
    this.players = new Player[2];
    this.players[0] = new Player("Player 1");
    this.players[1] = new Player("Player 2");
  }

  public boolean isWordReal(String word) {
    return this.wordDictionary.contains(word);
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

  public static void getTile(Player player) {
    player.rack.add(tileBag.remove(0));
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
}
