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

        System.out.println("result: " + Arrays.deepToString(solution.matrixRankTransform(new int[][]{
                {-2, -35, -32, -5, -30, 33, -12},
                {7, 2, -43, 4, -49, 14, 17},
                {4, 23, -6, -15, -24, -17, 6},
                {-47, 20, 39, -26, 9, -44, 39},
                {-50, -47, 44, 43, -22, 33, -36},
                {-13, 34, 49, 24, 23, -2, -35},
                {-40, 43, -22, -19, -4, 23, -18}
        })));
    }
}


class Solution {
    class Pair{
        public int row;
        public int column;
        public int value;
        public boolean isUseful = true;
        public boolean useLastValue = false;
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
        public String toString() {
            return "["+row+","+column+","+value+","+rankValue+"]";
        }
    }

    public int[][] matrixRankTransform(int[][] matrix) {
        System.out.println("Original Matrix");
        printFormattedMatrix(matrix);

        var matrixSize = matrix.length * matrix[0].length;
        var filledSize = 0;
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
                    }
                }

                for (int col = 0; col < colCount; col++) {
                    if (currentValue > matrix[i][col]) {
                        isMinimal = false;
                    }
                }

                if(isMinimal){
                    filledSize++;
                    pairList.add(new Pair(i,j, matrix[i][j], 1));
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
                        if(!pairList.contains(new Pair(row,pair.column))){
                            canBeUsed = false;
                        }
                    }
                }
            }

            for (int col = 0; col < colCount; col++) {
                if (col != pair.column){
                    if (currentValue == matrix[pair.row][col]) {
                        if(!pairList.contains(new Pair(pair.row,col))){
                            canBeUsed = false;
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
//        System.out.println("Init" + Arrays.deepToString(rank));
//        System.out.println(pairList);
//        System.out.println();

        while (!pairList.isEmpty()){
            System.out.println("start round " + roundTurn);
            var currentPairList = new ArrayList<Pair>(pairList);
            pairList.clear();
            var refPairList = pairList;

            currentPairList.forEach(pair -> {
                var minRowPositions = new ArrayList<Integer>();
                var minValue = 9999;
                var currentValue = matrix[pair.row][pair.column];

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

                minRowPositions.forEach(row -> {
//                    if(!currentPairList.contains(new Pair(row,pair.column))){
                        refPairList.add(new Pair(row, pair.column, matrix[row][pair.column], pair.rankValue + 1));
//                    }
                });

                minColPositions.forEach(col -> {
//                    if(!currentPairList.contains(new Pair(pair.row,col))){
                        refPairList.add(new Pair(pair.row, col, matrix[pair.row][col], pair.rankValue + 1));
//                    }
                });
            });


//            System.out.println(roundTurn + "  preparedData: " + pairList);


//            for (int i = 0; i < pairList.size(); i++) {
//                var currentPair = pairList.get(i);
//                var currentPairValue = matrix[currentPair.row][currentPair.column];
//                for (var j = 0; j < pairList.size(); j++) {
//                    if(i == j){
//                        continue;
//                    }
//                    var currentPair2 = pairList.get(j);
//                    var currentPair2Value = matrix[currentPair2.row][currentPair2.column];
//
//                    if(currentPair.row == currentPair2.row || currentPair.column == currentPair2.column){
//                        if(currentPairValue < currentPair2Value){
//                            currentPair2.isUseful = false;
//                        } else if(currentPairValue > currentPair2Value){
//                            currentPair.isUseful = false;
//                        }
//                    }
//
//                }
//            }
//
//            pairList = pairList.stream().filter(pair -> pair.isUseful).collect(Collectors.toCollection(ArrayList::new));


            for (Pair currentPair : pairList) {
                if(rank[currentPair.row][currentPair.column] < currentPair.rankValue) {
                    rank[currentPair.row][currentPair.column] = currentPair.rankValue;
                    filledSize ++;
                } else {
                    currentPair.rankValue = rank[currentPair.row][currentPair.column];
                }
            }

            roundTurn++;
//            System.out.println(Arrays.deepToString(rank));
            System.out.println("Original:");
            printFormattedMatrix(matrix);
            System.out.println();

            System.out.println("Rank:");
            printFormattedMatrix(rank);
//            System.out.println("FilledSize: " + filledSize + "  allSize: " + matrixSize);
            System.out.println();
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
}