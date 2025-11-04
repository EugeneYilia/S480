public class Sudoku {
    private final short[] rows = new short[9];
    private final short[] cols = new short[9];
    private final short[] boxes = new short[9];

    private final byte[] emptyPointsRow = new byte[81];
    private final byte[] emptyPointsCol = new byte[81];

    private final int[] emptyPointsValues = new int[81];

    private byte emptyPointsSize = 0;
    private byte filledPointsSize = 0;

    //  1             2              3            4          5              6              7                8             9
    // 00000001      00000010      00000100     00001000   00010000      00100000       01000000        10000000
    private static final int allValueNum = 511;

    // row 0-8
    // col 0-8
    // * 9
    // (0,0) 0   (0,8) 8
    // (1,0) 10  (1,8) 17
    // (2,0) 18  (2,8) 26

    // 20 -> 2,2
    public void checkMatrix(char[][] src) {
        for (byte i = 0; i < src.length; i++) {
            for (byte j = 0; j < src[0].length; j++) {
                char c = src[i][j];
                if (c == '.') {
                    byte curPointIndex = emptyPointsSize++;
                    emptyPointsRow[curPointIndex] = i;
                    emptyPointsCol[curPointIndex] = j;
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
        byte row = -1;
        byte col = -1;
        byte minPointIndex = -1;
        byte existNumCount = Byte.MIN_VALUE;
        short pointValue = -1;

        // all 5   filled 3
        // 0-4     0-2    3-4
        for (byte i = filledPointsSize; i < emptyPointsSize; i++) {
            byte curRow = emptyPointsRow[i];
            byte curCol = emptyPointsCol[i];

            int possibleNums = rows[curRow] | cols[curCol] | boxes[curRow / 3 * 3 + curCol / 3];
            int possibleNumsCount = Integer.bitCount(possibleNums);
            emptyPointsValues[i] = possibleNums;
            if (possibleNumsCount > existNumCount) {
                minPointIndex = i;
                existNumCount = (byte) possibleNumsCount;
                row = curRow;
                col = curCol;
                pointValue = (short) possibleNums;

                if (possibleNumsCount == 8) {
                    break;
                }
            }
        }

        // 单值优化
        if (existNumCount != 8) {
            for (byte i = filledPointsSize; i < emptyPointsSize; i++) {
                int possibleNums = emptyPointsValues[i];

                boolean isValueSingle = true;
                int singleValue = 0;
                for (int value = 1; value <= 9; value++) {
                    isValueSingle = true;
                    if ((value & possibleNums) == 0) {
                        for (int j = filledPointsSize + 1; j < emptyPointsSize; j++) {
                            if (emptyPointsRow[i] == emptyPointsRow[j]
                                    || emptyPointsCol[i] == emptyPointsCol[j]
                                    || emptyPointsRow[i] / 3 * 3 + emptyPointsCol[i] / 3 == emptyPointsRow[j] / 3 * 3 + emptyPointsCol[j] / 3) {
                                if ((value & emptyPointsValues[j]) == 0) {
                                    isValueSingle = false;
                                    break;
                                }
                            }
                        }

                        if (isValueSingle) {
                            singleValue = value;
                            break;
                        }
                    } else {
                        isValueSingle = false;
                    }
                }
                if (isValueSingle) {
                    minPointIndex = i;
                    pointValue = (short) (allValueNum ^ singleValue);
                    row = emptyPointsRow[i];
                    col = emptyPointsCol[i];
                    break;
                }
            }
        }

        emptyPointsRow[minPointIndex] = emptyPointsRow[filledPointsSize];
        emptyPointsRow[filledPointsSize] = row;

        emptyPointsCol[minPointIndex] = emptyPointsCol[filledPointsSize];
        emptyPointsCol[filledPointsSize] = col;

        filledPointsSize++;


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