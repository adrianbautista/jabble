/**
 * Created with IntelliJ IDEA.
 * User: cgoodmac
 * Date: 8/21/13
 * Time: 8:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    private final static int COLS = 15;
    private final static int ROWS = COLS;
    public static char EMPTY_CASE = '_';
    private char[][] board;
    public final static char DOUBLE_LETTER_DISPLAY = '1';
    public final static char TRIPLE_LETTER_DISPLAY = '2';
    public final static char DOUBLE_WORD_DISPLAY = '3';
    public final static char TRIPLE_WORD_DISPLAY = '4';

    /*boardmap scheme:
    1 : double letter
    2 : double word
    3 : triple letter
    4 : triple word
    */
    public final static int[][] BOARDMAP_SCHEME = {
            {4,0,0,1,0,0,0,4,0,0,0,1,0,0,4},
            {0,2,0,0,0,3,0,0,0,3,0,0,0,2,0},
            {0,0,2,0,0,0,1,0,1,0,0,0,2,0,0},
            {1,0,0,2,0,0,0,1,0,0,0,2,0,0,1},
            {0,0,0,0,2,0,0,0,0,0,2,0,0,0,0},
            {0,3,0,0,0,3,0,0,0,3,0,0,0,3,0},
            {0,0,1,0,0,0,1,0,1,0,0,0,1,0,0},
            {4,0,0,1,0,0,0,2,0,0,0,1,0,0,4},
            {0,0,1,0,0,0,1,0,1,0,0,0,1,0,0},
            {0,3,0,0,0,3,0,0,0,3,0,0,0,3,0},
            {0,0,0,0,2,0,0,0,0,0,2,0,0,0,0},
            {1,0,0,2,0,0,0,1,0,0,0,2,0,0,1},
            {0,0,2,0,0,0,1,0,1,0,0,0,2,0,0},
            {0,2,0,0,0,3,0,0,0,3,0,0,0,2,0},
            {4,0,0,1,0,0,0,4,0,0,0,1,0,0,4}
    };


    /* constructor */
    public Board() {
        board = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY_CASE;
            }
        }

    }

    public char[][] getBoard() {
        return board;
    }

    /* view board */

    public void displayBoard() {
        String result = "      A B C D E F G H I J K L M N O\n\n";
        char[][] b = getBoard();
        for(int i = 0; i < ROWS; i++) {
            if (i < 10) {
                result += "0" + i + "    ";
            }
            else {
                result += i + "    ";
            }
            for(int j = 0; j < ROWS; j++) {
                if(b[i][j] != EMPTY_CASE) {
                    result += b[i][j] + " ";
                }
                else if(BOARDMAP_SCHEME[i][j] == 0) {
                    result += b[i][j] + " ";
                }
                else if(BOARDMAP_SCHEME[i][j] == 1) {
                    result += DOUBLE_LETTER_DISPLAY + " ";
                }
                else if(BOARDMAP_SCHEME[i][j] == 3) {
                    result += TRIPLE_LETTER_DISPLAY + " ";
                }
                else if(BOARDMAP_SCHEME[i][j] == 2) {
                    result += DOUBLE_WORD_DISPLAY + " ";
                }
                else if(BOARDMAP_SCHEME[i][j] == 4) {
                    result += TRIPLE_WORD_DISPLAY + " ";
                }
            }
            if (i < 10) {
                result += "   " + "0" + i + "\n";
            }
            else {
                result += "   " + i + "\n";
            }
        }
        result += "\n      A B C D E F G H I J K L M N O\n";
        System.out.println(result);
    }
}
