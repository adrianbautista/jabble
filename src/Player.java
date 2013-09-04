import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Player {
    private String name;
    private HashMap wordsPlayed;
    private ArrayList<String> wordsBeingPlayed;
    public ArrayList<Character> rack;

   /* constructor */
   public Player(String name) {
       this.name = name;
       this.rack = new ArrayList<Character>(7);
       for (int i = 0; i < 7; i++) {
           Game.getTile(this);
       }
       this.wordsPlayed = new HashMap();
       this.wordsBeingPlayed = new ArrayList<String>();
   }

    public int getScore() {
      /*
        wordsPlayed is a HashMap where every key is a string and its value is an ArrayList containing the point values of the word played
        this is because the same word can be played multiple times on the same board using different tiles.
        so we have to iterate through each of these "scoresArray" to find the total point score for a player.
       */
      int score = 0;
      Iterator allScoreArrays = this.wordsPlayed.values().iterator();
      while (allScoreArrays.hasNext()) {
        ArrayList<Integer> scoresArray = (ArrayList<Integer>) allScoreArrays.next();
        for (int i = 0; i < scoresArray.size(); i++) {
          score = score + scoresArray.get(i).intValue();
        }
      }
      return score;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Character> getRack(){
       return rack;
    }

    public void setWordsPlayed() {
      for (int i = 0; i < this.wordsBeingPlayed.size(); i++) {
        String word = this.wordsBeingPlayed.remove(i);
        this.addWordPlayed(word, Game.pointGuide);
      }
    }

    public void addWordPlayed(String word, HashMap pointGuide) {
      char[] letters = word.toCharArray();
      int wordLength = letters.length;
      int wordScore = 0;
      for (int i = 0; i < wordLength; i++) {
        int pointValue = (int) pointGuide.get(letters[i]);
        wordScore = wordScore + pointValue;
      }

      if (this.wordsPlayed.containsKey(word)) {
        // in the instance that a player is playing the same word a second time on the board...
        ArrayList<Integer> scoresArray = (ArrayList<Integer>) this.wordsPlayed.get(word);
        scoresArray.add(new Integer(wordScore));
        this.wordsPlayed.put(word, scoresArray);
      } else {
        ArrayList<Integer> scoresArray = new ArrayList<Integer>();
        scoresArray.add(new Integer(wordScore));
        this.wordsPlayed.put(word, scoresArray);
      }

    }

    public boolean playSingleTile(char tile, char direction, int x, int y, Board board) {
      Character tileObject = new Character(tile);

      if (this.rack.contains(tileObject)) {
        if (board.isSpaceOpen(x, y)) {

          // check for combo vertical words being made if the word being made is horizontal

          if (direction == 'r') {
            String newVerticalWord = board.checkForVerticalWord(x, y);
            if (newVerticalWord.length() != 1) {
              this.wordsBeingPlayed.add(newVerticalWord);
            }
          } // check for combo horizontal words if word being made is vertical
          else {
            String newHorizontalWord = board.checkForHorizontalWord(x, y);
            int test = newHorizontalWord.length();
            if (test > 1) {
              this.wordsBeingPlayed.add(newHorizontalWord);
            }
          }

          // add tile to the board
          board.addTileToBoard(tile, x, y);
          // remove the tile from the player's rack
          this.rack.remove(tileObject);

          // tile is successfully played, any words along the way are added to wordBeingPlayed
          return true;
        }
      }
      return false;
    }

    public void playFirstTile(char tile, Board board) {
      board.addTileToBoard(tile, 7, 7);
      this.rack.remove(new Character(tile));
    }

    // add the intended word being made by player to wordsBeingPlayed

    public void finshTurn(char direction, int x, int y, Board board) {
      if (direction == 'r') {
        String mainHorizontalWord = board.checkForHorizontalWord(x, y);
        if (mainHorizontalWord.length() != 1) {
          this.wordsBeingPlayed.add(mainHorizontalWord);
        }
      }
      else {
        String mainVerticalWord = board.checkForVerticalWord(x, y);
        if (mainVerticalWord.length() != 1) {
          this.wordsBeingPlayed.add(mainVerticalWord);
        }
      }
    }

    // validate all wordsBeingPlayed with the dictionary

    public boolean validateTurn() {
      if (this.wordsBeingPlayed.size() == 0) {
        return false;
      }

      for (int i = 0; i < this.wordsBeingPlayed.size(); i++) {
        String word = this.wordsBeingPlayed.get(i);
        if (Game.isWordReal(word) != true) {
          return false;
        }
      }

      setWordsPlayed();

      return true;
    }
}
