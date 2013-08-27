import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name;
    private int score = 0;
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
