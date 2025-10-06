import java.util.ArrayList;
import java.util.HashSet;

public class Sudoku {

    private static final class Point {
        int row;
        int col;
        // 已有元素
        int existValues;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            return 10 * row + col;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Point && (row == ((Point) obj).row) && col == (((Point) obj).col);
        }
    }

    private static int[] rows = new int[9];
    private static int[] cols = new int[9];
    private static int[] boxes = new int[9];

    private static HashSet<Point> leftPoints = new HashSet<>();
    private static final int allValueNum = 511;

    private static ArrayList<Integer> getPossibleNumbers(Point point) {
        ArrayList<Integer> result = new ArrayList<>();
        // 1111 ^ 1101 = 0010
        //               可选元素结果集
        int possibleNumber = allValueNum ^ point.existValues;
        // 1 2 4 8 16 32
        for (int i = 0; i < 9; i++) {
            int mask = 1 << i;
            if ((mask & possibleNumber) != 0) {
                result.add(i + 1);
            }
        }

        return result;
    }

    public static void checkMatrix(char[][] src) {
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[0].length; j++) {
                char c = src[i][j];
                if (c == '.') {
                    leftPoints.add(new Point(i, j));
                } else {
                    // [0,8]
                    int mask = 1 << (c - '1');
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
        rows = new int[9];
        cols = new int[9];
        boxes = new int[9];
        leftPoints = new HashSet<>();

        checkMatrix(board);

        solveSudokuInternal(board);
    }

    private static Point searchMinChoicePoint() {
        Point minPoint = null;
        int existNumCount = Integer.MIN_VALUE;

        for (Point point : leftPoints) {
            int row = point.row;
            int col = point.col;

            int possibleNums = rows[row] | cols[col] | boxes[row / 3 * 3 + col / 3];
            int possibleNumsCount = Integer.bitCount(possibleNums);
            if (possibleNumsCount > existNumCount) {
                existNumCount = possibleNumsCount;
                minPoint = point;
                minPoint.existValues = possibleNums;
            }
        }

        return minPoint;
    }

    public void solveSudokuInternal(char[][] board) {
        Point curPoint = searchMinChoicePoint();
        leftPoints.remove(curPoint);

        int rowStart = curPoint.row;
        int columnStart = curPoint.col;
        ArrayList<Integer> possibleNumbers = getPossibleNumbers(curPoint);

        for (Integer possibleNumber : possibleNumbers) {
            board[rowStart][columnStart] = (char)(possibleNumber + '0');

            var newBitValue = 1 << (possibleNumber - 1);
            rows[rowStart] |= newBitValue;
            cols[columnStart] |= newBitValue;
            boxes[rowStart / 3 * 3 + columnStart / 3] |= newBitValue;

            // 填当前的看看是否可以填满
            if (leftPoints.isEmpty()) {
                return;
            }

            // 递归下去寻找解决方案
            solveSudokuInternal(board);

            // 递归下去如果填满了直接返回，不用进入到下面的回溯部分
            if (leftPoints.isEmpty()) {
                return;
            }

            // 4 =   0000100
            // ~4 =  1111011
            int backTrackNum = ~newBitValue;
            rows[rowStart] &= backTrackNum;
            cols[columnStart] &= backTrackNum;
            boxes[rowStart / 3 * 3 + columnStart / 3] &= backTrackNum;
        }

        board[rowStart][columnStart] = '.';
        leftPoints.add(curPoint);
    }
}
