import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: cgoodmac
 * Date: 8/26/13
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    private String name;
    private int score = 0;
    public ArrayList<Character> rack;

   /* constructor */
   public Player(String name) {
       name = name;
       this.rack = new ArrayList<Character>(7);
       for (int i = 0; i < 7; i++) {
           Game.getTile(this);
       }
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
}
