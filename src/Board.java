import java.util.List;
import static java.util.Arrays.asList;

/**
 * Created with IntelliJ IDEA.
 * User: cgoodmac
 * Date: 8/21/13
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    public List<List<Character>> board;

    public Board() {
        this.board = asList(
            asList('a', 'b', 'c'),
            asList('e', 'f', 'g'),
            asList('h', 'j',  'i') );
//       this.board = new ArrayList<List<Character>>(15);
    }
}
