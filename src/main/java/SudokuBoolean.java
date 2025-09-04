import java.util.HashMap;
import java.util.HashSet;

public class SudokuBoolean {

    // 只检查已有数字是否冲突
    // 不要求判断数独能否填满，也不要求判断是否有解
    // 如果原始数字没有冲突，但因为空格排列无法得到完整数独，这种情况仍然可以返回true
    // 只是局部合法性判断，不是全局可解性判断
    public boolean isValidSudoku(char[][] board) {

        HashMap<Integer, HashSet<Character>> connectedAreaChars = new HashMap<Integer, HashSet<Character>>();

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                HashSet<Character> matrixNumber = new HashSet<Character>();
                for (int row = i; row < i + 3; row++) {
                    for (int col = j; col < j + 3; col++) {
                        if (board[row][col] == '.') {
                            continue;
                        }

                        connectedAreaChars.computeIfAbsent(row, _ -> new HashSet<Character>());
                        if (connectedAreaChars.get(row).contains(board[row][col])) {
                            return false;
                        } else {
                            connectedAreaChars.get(row).add(board[row][col]);
                        }

                        connectedAreaChars.computeIfAbsent(col + 9, _ -> new HashSet<Character>());
                        if (connectedAreaChars.get(col + 9).contains(board[row][col])) {
                            return false;
                        } else {
                            connectedAreaChars.get(col + 9).add(board[row][col]);
                        }

                        if (matrixNumber.contains(board[row][col])) {
                            return false;
                        } else {
                            matrixNumber.add(board[row][col]);
                        }
                    }
                }
            }
        }

        return true;
    }

}
