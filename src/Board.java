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

    public boolean isSpaceOpen(int x, int y) {
      if ((x > 14) || (y > 14)) {
        return false;
      }

      char currentOccupier = this.board[x][y];

      if (currentOccupier == '_') {
        return true;
      } else {
        return false;
      }
    }

    public void addTileToBoard(char tile, int x, int y) {
      this.board[x][y] = tile;
    }

    public String checkForVerticalWord(int x, int y) {
        StringBuilder verticalWord = new StringBuilder();

      switch (y) {
        case 0:
          while (checkSpace(x, y) != '_') {
            verticalWord.append(this.board[x][y]);
            y += 1;
          }
          break;

        case 14:
          while (checkSpace(x, y) != '_') {
            verticalWord.insert(0, this.board[x][y]);
            y -= 1;
          }
          break;

        default:
          int originalTile = y;
          while (checkSpace(x, y) != '_') {
            verticalWord.append(this.board[x][y]);
            y += 1;
          }
          while (checkSpace(x, originalTile) != '_') {
            verticalWord.insert(0, this.board[x][originalTile]);
            originalTile -= 1;
          }
          break;
      }

      return verticalWord.toString();
    }

    public char checkSpace(int x, int y) {
      return this.board[x][y];
    }

    public String checkForHorizontalWord(int x, int y) {
      StringBuilder horizontalWord = new StringBuilder();

      switch (x) {
        case 0:
          while (checkSpace(x, y) != '_') {
            horizontalWord.append(this.board[x][y]);
            x +=1;
          }
          break;

        case 14:
          while (checkSpace(x, y) != '_') {
            horizontalWord.insert(0, this.board[x][y]);
            x -= 1;
          }
          break;

        default:
          int originalTile = x;
          while (checkSpace(x, y) != '_') {
            horizontalWord.append(this.board[x][y]);
            x +=1;
          }
          while (checkSpace(originalTile, y) != '_') {
            horizontalWord.insert(0, this.board[originalTile][y]);
            originalTile -= 1;
          }
          break;
      }

      return horizontalWord.toString();
    }

    /* view board */

    public void displayBoard() {
        String result = "      0 1 2 3 4 5 6 7 8 9 10 11 12 13 14\n\n";
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
        result += "\n      0 1 2 3 4 5 6 7 8 9 10 11 12 13 14\n";
        System.out.println(result);
    }
}
