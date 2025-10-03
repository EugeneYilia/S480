public class SudokuBoolean {

    // 只检查已有数字是否冲突
    // 不要求判断数独能否填满，也不要求判断是否有解
    // 如果原始数字没有冲突，但因为空格排列无法得到完整数独，这种情况仍然可以返回true
    // 只是局部合法性判断，不是全局可解性判断
    public boolean isValidSudoku(char[][] board) {

        // 9 9
        // 18
        // 0-17
        boolean[][] connectedAreaChars = new boolean[board.length + board.length][9];

        // 81
        // 3 * 3 * 3 * 3 = 81.
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                boolean[] matrixNumber = new boolean[9];
                for (int row = i; row < i + 3; row++) {
                    for (int col = j; col < j + 3; col++) {
                        if (board[row][col] == '.') {
                            continue;
                        }

                        if (connectedAreaChars[row][board[row][col] - '1']) {
                            return false;
                        } else {
                            connectedAreaChars[row][board[row][col] - '1'] = true;
                        }

                        if (connectedAreaChars[col + 9][board[row][col] - '1']) {
                            return false;
                        } else {
                            connectedAreaChars[col + 9][board[row][col] - '1'] = true;
                        }

                        if (matrixNumber[board[row][col] - '1']) {
                            return false;
                        } else {
                            matrixNumber[board[row][col] - '1'] = true;
                        }
                    }
                }
            }
        }

        return true;
    }
}
