import java.util.ArrayList;

public class Game {
  public ArrayList<Character> tileBag;
  public Game() {
    this.tileBag = generateTileBag();
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
}
