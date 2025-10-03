import java.util.Arrays;
import java.util.HashSet;

public class Sudoku {

    public static Character[] getPossibleNumbers(char[][] src, int row, int column) {
        HashSet<Character> possibleValues = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9'));

        for (char ele : src[row]) {
            possibleValues.remove(ele);
        }

        for (char[] ele : src) {
            possibleValues.remove(ele[column]);
        }

        int rowMatrixId = row / 3;
        int columnMatrixId = column / 3;

        for (int i = rowMatrixId * 3; i < (rowMatrixId + 1) * 3; i++) {
            for (int j = columnMatrixId * 3; j < (columnMatrixId + 1) * 3; j++) {
                possibleValues.remove(src[i][j]);
            }
        }

        return possibleValues.toArray(Character[]::new);
    }

    public static boolean checkMatrixIsRight(char[][] src) {
        for (char[] chars : src) {
            for (int j = 0; j < src[0].length; j++) {
                if (chars[j] == '.') {
                    return false;
                }
            }
        }

        return true;
    }

    public void solveSudoku(char[][] board) {
        int rowStart = -1;
        int columnStart = -1;
        Character[] possibleNumbers = new Character[0];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    rowStart = i;
                    columnStart = j;
                    possibleNumbers = getPossibleNumbers(board, i, j);
                    break;
                }
            }
            if (rowStart != -1) {
                break;
            }
        }

        for (Character possibleNumber : possibleNumbers) {
            board[rowStart][columnStart] = possibleNumber;
            if (checkMatrixIsRight(board)) {
                return;
            }

            solveSudoku(board);
            if (checkMatrixIsRight(board)) {
                return;
            }
        }

        board[rowStart][columnStart] = '.';
    }
}
