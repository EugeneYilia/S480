public class Sudoku {
    private final short[] rows = new short[9];
    private final short[] cols = new short[9];
    private final short[] boxes = new short[9];

    private final byte[] emptyPoints = new byte[81];
    private byte emptyPointsSize = 0;
    private byte filledPointsSize = 0;

    //  1             2              3            4          5              6              7                8             9
    // 00000001      00000010      00000100     00001000   00010000      00100000       01000000        10000000
    private static final short allValueNum = 511;

    public void checkMatrix(char[][] src) {
        for (byte i = 0; i < src.length; i++) {
            for (byte j = 0; j < src[0].length; j++) {
                char c = src[i][j];
                if (c == '.') {
                    emptyPoints[emptyPointsSize++] = (byte) (i * 10 + j);
                } else {
                    // [0,8]
                    short mask = (short) (1 << (c - '1'));
                    // 1 1
                    // 2 2
                    // 3 4
                    // 4 8
                    rows[i] |= mask;
                    cols[j] |= mask;
                    boxes[i / 3 * 3 + j / 3] |= mask;
                }
            }
        }
    }

    public void solveSudoku(char[][] board) {
        checkMatrix(board);

        solveSudokuInternal(board);
    }

    public void solveSudokuInternal(char[][] board) {
        byte curPoint = -1;
        byte minPointIndex = -1;
        byte existNumCount = Byte.MIN_VALUE;
        short pointValue = -1;

        // all 5   filled 3
        // 0-4     0-2    3-4
        for (byte i = filledPointsSize; i < emptyPointsSize; i++) {
            byte point = emptyPoints[i];
            byte row = (byte) (point / 10);
            byte col = (byte) (point % 10);

            int possibleNums = rows[row] | cols[col] | boxes[row / 3 * 3 + col / 3];
            int possibleNumsCount = Integer.bitCount(possibleNums);
            if (possibleNumsCount > existNumCount) {
                minPointIndex = i;
                existNumCount = (byte) possibleNumsCount;
                curPoint = point;
                pointValue = (short) possibleNums;

                if(possibleNumsCount == 8){
                    break;
                }
            }
        }

        emptyPoints[minPointIndex] = emptyPoints[filledPointsSize];
        emptyPoints[filledPointsSize] = curPoint;

        filledPointsSize++;

        byte row = (byte) (curPoint / 10);
        byte col = (byte) (curPoint % 10);

        // 1111 ^ 1101 = 0010
        //               可选元素结果集
        short possibleNumber = (short) (allValueNum ^ pointValue);
        // 1 2 4 8 16 32

        for (byte i = 0; i < 9; i++) {
            short newBitValue = (short) (1 << i);
            if ((newBitValue & possibleNumber) != 0) {
                board[row][col] = (char) (i + 1 + '0');

                rows[row] |= newBitValue;
                cols[col] |= newBitValue;
                boxes[row / 3 * 3 + col / 3] |= newBitValue;

                // 填当前的看看是否可以填满
                if (filledPointsSize == emptyPointsSize) {
                    return;
                }

                // 递归下去寻找解决方案
                solveSudokuInternal(board);

                // 递归下去如果填满了直接返回，不用进入到下面的回溯部分
                if (filledPointsSize == emptyPointsSize) {
                    return;
                }

                // 4 =   0000100
                // ~4 =  1111011
                short backTrackNum = (short) ~newBitValue;
                rows[row] &= backTrackNum;
                cols[col] &= backTrackNum;
                boxes[row / 3 * 3 + col / 3] &= backTrackNum;
            }
        }

        board[row][col] = '.';
        filledPointsSize--;
    }
}


// 2，2   0
// 2，5   1
// 2，8   2
// 5，2   3
// 5，5   4
// 5，8   5
// 8，2   6
// 8，5   7
// 8，8   8