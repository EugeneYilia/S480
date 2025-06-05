import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}


class Solution {
    class Pair{
        public int row;
        public int column;
        public int value;
        public boolean isUseful = true;

        public Pair(
                int row,
                int column,
                int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }

    public int[][] matrixRankTransform(int[][] matrix) {
        var matrixSize = matrix.length * matrix[0].length;
        var filledSize = 0;
        ArrayList<Pair> pairList = new ArrayList<Pair>();

        var roundTurn = 2;

        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        var rank = new int[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                var currentValue = matrix[i][j];

                var isMinimal = true;
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        if (currentValue > matrix[row][col]) {
                            isMinimal = false;
                        }
                    }
                }

                if(isMinimal){
                    rank[i][j] = 1;
                    filledSize++;
                    pairList.add(new Pair(i, j, 1));
                }
            }
        }

        while (filledSize < matrixSize){
            var currentPairList = new ArrayList<Pair>(pairList);
            pairList.clear();

            currentPairList.forEach(pair -> {
                var minRowPositions = new ArrayList<Integer>();
                var minValue = 9999;
                for (int i = 0; i < rowCount; i++) {
                    if(matrix[i][pair.column] > pair.value && matrix[i][pair.column] < minValue){
                        minRowPositions.clear();
                        minRowPositions.add(i);
                        minValue = matrix[i][pair.column];
                    } else if (matrix[i][pair.column] > pair.value && matrix[i][pair.column] == minValue){
                        minRowPositions.add(i);
                    }
                }

                var minColPositions = new ArrayList<Integer>();
                var minColValue = 9999;
                for (int j = 0; j < colCount; j++) {
                    if(matrix[pair.row][j] > pair.value && matrix[pair.row][j] < minColValue){
                        minColPositions.clear();
                        minColPositions.add(j);
                        minColValue = matrix[pair.row][j];
                    } else if (matrix[pair.row][j] > pair.value && matrix[pair.row][j] == minValue){
                        minColPositions.add(j);
                    }
                }

                minRowPositions.forEach(row -> {
                    pairList.add(new Pair(row, pair.column, matrix[row][pair.column]));
                });

                minColPositions.forEach(col -> {
                    pairList.add(new Pair(pair.row, col, matrix[pair.row][col]));
                });
            });


            for (int i = 0; i < pairList.size(); i++) {
                var currentPair = pairList.get(i);
                for (var j = 0; j < pairList.size(); j++) {
                    if(i == j){
                        continue;
                    }
                    var currentPair2 = pairList.get(j);

                    if(currentPair.row == currentPair2.row || currentPair.column == currentPair2.column){
                        if(currentPair.value < currentPair2.value){
                            currentPair2.isUseful = false;
                        } else if(currentPair.value > currentPair2.value){
                            currentPair.isUseful = false;
                        }
                    }

                }
            }

            pairList = pairList.stream().filter(pair -> pair.isUseful).collect(Collectors.toCollection(ArrayList::new));
            for (Pair currentPair : pairList) {
                rank[currentPair.row][currentPair.column] = roundTurn;
            }

            roundTurn++;
        }

        return rank;
    }
}