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
    private char[] tiles;


   /* constructor */
   public Player(String name) {
       name = name;
   }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
