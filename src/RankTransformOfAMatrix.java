import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RankTransformOfAMatrix {
    public static void main(String[] args) {

        Solution solution = new Solution();
//        System.out.println("result: " + Arrays.deepToString(solution.matrixRankTransform(new int[][]{{1, 2}, {3, 4}})));
//        System.out.println("result: " + Arrays.deepToString(solution.matrixRankTransform(new int[][]{{7,7}, {7,7}})));
//
//        System.out.println("result: " + Arrays.deepToString(solution.matrixRankTransform(new int[][]{{20,-21,14}, {-19,4,19}, {22,-47,24}, {-19,4,19}})));
//        System.out.println("result: " + Arrays.deepToString(solution.matrixRankTransform(new int[][]{
//                {-37,-50,-3,44},
//                {-37,46,13,-32},
//                {47,-42,-3,-40},
//                {-17,-22,-39,24}})));

//        System.out.println("result: " + Arrays.deepToString(solution.matrixRankTransform(new int[][]{
//                {-2, -35, -32, -5, -30, 33, -12},
//                {7, 2, -43, 4, -49, 14, 17},
//                {4, 23, -6, -15, -24, -17, 6},
//                {-47, 20, 39, -26, 9, -44, 39},
//                {-50, -47, 44, 43, -22, 33, -36},
//                {-13, 34, 49, 24, 23, -2, -35},
//                {-40, 43, -22, -19, -4, 23, -18}
//        })));

        var originalMatrix = new int[][]{
                {25, 8, 31, 42, -39, 8, 31, -10, 33, -44, 7, -30, 9, 44, 15, 26},
                {-3, -48, -17, -18, 9, -12, -21, 10, 1, 44, -17, 14, -27, 48, -21, -6},
                {49, 28, 27, -18, -31, 4, -13, 34, 49, 48, 47, -18, 33, 40, 15, 38},
                {5, -28, -49, -38, 1, 32, -25, -50, 29, -32, 35, -46, -43, 48, -49, -6},
                {-27, -24, 23, -14, -47, -12, 7, 6, 25, -16, 47, -26, 13, -12, -33, -18},
                {45, -48, 3, -26, -23, -36, -17, 38, 17, 12, 15, 46, 37, 40, 47, 26},
                {-19, -24, -21, -2, -7, -48, 47, 30, 5, -8, 23, -46, 21, -32, -33, -26},
                {-27, 32, 27, -26, 21, -32, -49, -10, 5, 20, -29, 46, -43, -44, 39, 22},
                {-43, 48, 27, 26, -27, 12, -1, -10, -27, 12, -29, -34, 41, -28, -25, -30},
                {25, -36, 35, -26, 37, -20, 31, 14, -19, -40, -29, -2, -39, -28, 11, 46},
                {49, -32, -29, -6, -47, 32, -17, -18, -23, 24, 23, 22, -47, -44, 27, 14},
                {37, -44, -33, -18, -47, 24, -17, -46, -43, -32, 15, -46, -27, -8, -25, 46},
                {41, -40, 31, -30, 13, -24, -29, 22, -15, -16, 47, 2, -39, 4, -25, -42},
                {-3, 12, 7, 14, -7, 8, -37, -34, -7, -12, 39, -38, 1, 44, 27, -34},
                {-47, 4, 7, -2, -43, -32, 27, 2, -43, -8, -33, 14, 49, -48, -5, 30},
                {-15, 8, -33, -26, -23, -32, -25, 22, 13, -20, -9, 26, 29, 4, -1, 2}
        };
        var result = solution.matrixRankTransform(originalMatrix);

        System.out.println("Result:");
        Solution.printFormattedMatrix(result);

        System.out.println("Answer is right: " + Solution.areMatricesEqual(result, new Solution2().matrixRankTransform(originalMatrix)));
    }
}


class Solution {
    static class Pair{
        public int row;
        public int column;
        public int value;
        public int rankValue;
        public boolean canBeUsed = true;

        public Pair(
                int row,
                int column){
            this.row = row;
            this.column = column;
        }

        public Pair(
                int row,
                int column,
                int value,
                int rankValue) {
            this.row = row;
            this.column = column;
            this.value = value;
            this.rankValue = rankValue;
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Pair && this.row == ((Pair)obj).row && this.column == ((Pair)obj).column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }

        @Override
        public String toString() {
            return "["+row+","+column+","+value+","+rankValue+"]";
        }
    }

    public int[][] matrixRankTransform(int[][] matrix) {
//        System.out.println("Original Matrix");
//        printFormattedMatrix(matrix);

        var pairList = new ArrayList<Pair>();

        var roundTurn = 2;

        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        var rank = new int[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                var currentValue = matrix[i][j];

                var isMinimal = true;
                for (int row = 0; row < rowCount; row++) {
                    if (currentValue > matrix[row][j]) {
                        isMinimal = false;
                        break;
                    }
                }

                if(isMinimal) {
                    for (int col = 0; col < colCount; col++) {
                        if (currentValue > matrix[i][col]) {
                            isMinimal = false;
                            break;
                        }
                    }
                }

                if(isMinimal){
                    pairList.add(new Pair(i, j, matrix[i][j], 1));
                }
            }
        }

        for (int i = 0; i < pairList.size(); i++) {
            var pair = pairList.get(i);
            var currentValue = pair.value;
            var canBeUsed = true;

            for (int row = 0; row < rowCount; row++) {
                if (row != pair.row){
                    if (currentValue == matrix[row][pair.column]) {
                        if(!pairList.contains(new Pair(row, pair.column))){
                            canBeUsed = false;
                            break;
                        }
                    }
                }
            }

            if(canBeUsed) {
                for (int col = 0; col < colCount; col++) {
                    if (col != pair.column) {
                        if (currentValue == matrix[pair.row][col]) {
                            if (!pairList.contains(new Pair(pair.row, col))) {
                                canBeUsed = false;
                                break;
                            }
                        }
                    }
                }
            }

            pair.canBeUsed = canBeUsed;
            if (canBeUsed){
                rank[pair.row][pair.column] = pair.rankValue;
            }
        }

        pairList = pairList.stream().filter(pair -> pair.canBeUsed).collect(Collectors.toCollection(ArrayList::new));

        while (!pairList.isEmpty()){
//            System.out.println("start round " + roundTurn);
            var currentPairList = new ArrayList<>(pairList);
            pairList.clear();
            var refPairList = pairList;

            currentPairList.forEach(pair -> {
                var minRowPositions = new ArrayList<Integer>();
                var minValue = 9999;
                var currentValue = pair.value;

                for (int i = 0; i < rowCount; i++) {
                    if(i == pair.row){
                        continue;
                    }

                    if(matrix[i][pair.column] > currentValue && matrix[i][pair.column] < minValue){
                        minRowPositions.clear();
                        minRowPositions.add(i);
                        minValue = matrix[i][pair.column];
                    } else if (matrix[i][pair.column] > currentValue && matrix[i][pair.column] == minValue){
                        minRowPositions.add(i);
                    }

                    if (matrix[i][pair.column] == currentValue){
                        if(rank[pair.row][pair.column] > rank[i][pair.column]) {
                                refPairList.add(new Pair(i, pair.column, matrix[i][pair.column], pair.rankValue));
                        }
                    }
                }

                var minColPositions = new ArrayList<Integer>();
                var minColValue = 9999;
                for (int j = 0; j < colCount; j++) {
                    if(j == pair.column){
                        continue;
                    }

                    if(matrix[pair.row][j] > currentValue && matrix[pair.row][j] < minColValue){
                        minColPositions.clear();
                        minColPositions.add(j);
                        minColValue = matrix[pair.row][j];
                    } else if (matrix[pair.row][j] > currentValue && matrix[pair.row][j] == minValue){
                        minColPositions.add(j);
                    }

                    if (matrix[pair.row][j] == currentValue){
                        if(rank[pair.row][pair.column] > rank[pair.row][j]) {
                            refPairList.add(new Pair(pair.row, j, matrix[pair.row][j], pair.rankValue));
                        }
                    }
                }

                minRowPositions.forEach(row -> refPairList.add(new Pair(row, pair.column, matrix[row][pair.column], pair.rankValue + 1)));

                minColPositions.forEach(col -> refPairList.add(new Pair(pair.row, col, matrix[pair.row][col], pair.rankValue + 1)));
            });

            for (Pair currentPair : pairList) {
                if(rank[currentPair.row][currentPair.column] < currentPair.rankValue) {
                    rank[currentPair.row][currentPair.column] = currentPair.rankValue;
                } else {
                    currentPair.rankValue = rank[currentPair.row][currentPair.column];
                }
            }

            roundTurn++;

//            System.out.println("Original:");
//            printFormattedMatrix(matrix);
//            System.out.println();

//            System.out.println("Rank:");
//            printFormattedMatrix(rank);
//            System.out.println();
        }

        return rank;
    }

    public static void printFormattedMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val); // 宽度为4，右对齐
            }
            System.out.println();
        }
    }

    public static boolean areMatricesEqual(int[][] matrix1, int[][] matrix2) {
        // 检查是否为 null
        if (matrix1 == null || matrix2 == null) return false;

        // 检查行数是否相同
        if (matrix1.length != matrix2.length) return false;

        for (int i = 0; i < matrix1.length; i++) {
            // 检查列数是否相同
            if (matrix1[i].length != matrix2[i].length) return false;

            for (int j = 0; j < matrix1[i].length; j++) {
                if (matrix1[i][j] != matrix2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}