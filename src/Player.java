import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Player {
    private String name;
    private HashMap wordsPlayed;
    public ArrayList<Character> rack;

   /* constructor */
   public Player(String name) {
       name = name;
       this.rack = new ArrayList<Character>(7);
       for (int i = 0; i < 7; i++) {
           Game.getTile(this);
       }
       this.wordsPlayed = new HashMap();
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

    public void setWordsPlayed(String word, HashMap pointGuide) {
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
}
