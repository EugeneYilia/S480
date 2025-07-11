package yichen;

import yichen.util.MatrixUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RankTransformOfAMatrixBFS {
//    public static void main(String[] args) {
//
//        var originalMatrix = new int[][]{
//                {-37,-50,-3,44},
//                {-37,46,13,-32},
//                {47,-42,-3,-40},
//                {-17,-22,-39,24}};
//
//        var matrix  = new RankMatrix();
//        matrix.matrixRankTransform(originalMatrix);
//    }

    public static void main(String[] args) throws IOException {

        Solution solution = new Solution();
//        var originalMatrix = new int[][]{{1, 2}, {3, 4}};

//        var originalMatrix = new int[][]{{7,7}, {7,7}};

//        var originalMatrix = new int[][]{{20,-21,14}, {-19,4,19}, {22,-47,24}, {-19,4,19}};

//        var originalMatrix = new int[][]{
//                {-37,-50,-3,44},
//                {-37,46,13,-32},
//                {47,-42,-3,-40},
//                {-17,-22,-39,24}};

//        var originalMatrix = new int[][]{
//                {-2, -35, -32, -5, -30, 33, -12},
//                {7, 2, -43, 4, -49, 14, 17},
//                {4, 23, -6, -15, -24, -17, 6},
//                {-47, 20, 39, -26, 9, -44, 39},
//                {-50, -47, 44, 43, -22, 33, -36},
//                {-13, 34, 49, 24, 23, -2, -35},
//                {-40, 43, -22, -19, -4, 23, -18}
//        };

//        var originalMatrix = new int[][]{
//                {25, 8, 31, 42, -39, 8, 31, -10, 33, -44, 7, -30, 9, 44, 15, 26},
//                {-3, -48, -17, -18, 9, -12, -21, 10, 1, 44, -17, 14, -27, 48, -21, -6},
//                {49, 28, 27, -18, -31, 4, -13, 34, 49, 48, 47, -18, 33, 40, 15, 38},
//                {5, -28, -49, -38, 1, 32, -25, -50, 29, -32, 35, -46, -43, 48, -49, -6},
//                {-27, -24, 23, -14, -47, -12, 7, 6, 25, -16, 47, -26, 13, -12, -33, -18},
//                {45, -48, 3, -26, -23, -36, -17, 38, 17, 12, 15, 46, 37, 40, 47, 26},
//                {-19, -24, -21, -2, -7, -48, 47, 30, 5, -8, 23, -46, 21, -32, -33, -26},
//                {-27, 32, 27, -26, 21, -32, -49, -10, 5, 20, -29, 46, -43, -44, 39, 22},
//                {-43, 48, 27, 26, -27, 12, -1, -10, -27, 12, -29, -34, 41, -28, -25, -30},
//                {25, -36, 35, -26, 37, -20, 31, 14, -19, -40, -29, -2, -39, -28, 11, 46},
//                {49, -32, -29, -6, -47, 32, -17, -18, -23, 24, 23, 22, -47, -44, 27, 14},
//                {37, -44, -33, -18, -47, 24, -17, -46, -43, -32, 15, -46, -27, -8, -25, 46},
//                {41, -40, 31, -30, 13, -24, -29, 22, -15, -16, 47, 2, -39, 4, -25, -42},
//                {-3, 12, 7, 14, -7, 8, -37, -34, -7, -12, 39, -38, 1, 44, 27, -34},
//                {-47, 4, 7, -2, -43, -32, 27, 2, -43, -8, -33, 14, 49, -48, -5, 30},
//                {-15, 8, -33, -26, -23, -32, -25, 22, 13, -20, -9, 26, 29, 4, -1, 2}
//        };


        var originalMatrix = MatrixUtil.parseMatrix(
                Files.readString(Path.of("data"))
        );

//        var originalMatrix = parseMatrix(
//                Files.readString(Path.of("data2"))
//        );

//        var originalMatrix = parseMatrix(
//                Files.readString(Path.of("data3"))
//        );

        System.out.println("Row count: " + originalMatrix.length + "  Column count: " + originalMatrix[0].length);
        long start = System.nanoTime();
        var result = solution.matrixRankTransform(originalMatrix);
        long end = System.nanoTime();
//        System.out.println("Result:");
//        printFormattedMatrix(result);

        var standardAnswer = new RankMatrixSolutionUnionSet().matrixRankTransform(originalMatrix);
//        System.out.println("Standard Result:");
//        printFormattedMatrix(standardAnswer);

        System.out.printf("【%s】耗时: %.3f ms%n", "printFormattedMatrix", (end - start) / 1_000_000.0);

//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("matrix.ser"))) {
//            oos.writeObject(result);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        System.out.println("Answer is right: " + MatrixUtil.areMatricesEqual(result, standardAnswer));
    }


}


class Solution {
    static class Pair{
        public int row;
        public int column;
        public int value;
        public int rankValue;
        public boolean canBeUsed = true;
        public boolean inUse = false;

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
            return obj instanceof Pair
                    && this.row == ((Pair)obj).row
                    && this.column == ((Pair)obj).column;
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

    // 并查集数量比较而不是每一个contains
    public int[][] matrixRankTransform(int[][] matrix) {
//        var valueToPairsMap = new HashMap<Integer, HashSet<Pair>>();

        var pairSet = new HashSet<Pair>();

//        var roundTurn = 2;

        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        var pairs = new Pair[rowCount][colCount];

        var rowMap = new HashMap<Integer, TreeMap<Integer, HashSet<Pair>>>();
        var colMap = new HashMap<Integer, TreeMap<Integer, HashSet<Pair>>>();

        for (int i = 0; i < rowCount; i++) {
            rowMap.put(i, new TreeMap<Integer, HashSet<Pair>>());

            for (int j = 0; j < colCount; j++) {
                colMap.putIfAbsent(j, new TreeMap<>());
                var pair = new Pair(i, j, matrix[i][j], 0);
                pairs[i][j] = pair;

                rowMap.get(i).computeIfAbsent(matrix[i][j], _ -> new HashSet<Pair>()).add(pair);
                colMap.get(j).computeIfAbsent(matrix[i][j], _ -> new HashSet<Pair>()).add(pair);

//                valueToPairsMap.computeIfAbsent(pair.value, _ -> new HashSet<Pair>()).add(pair);
            }
        }

        var minimalRowPairs = new HashSet<Pair>();
        var minimalColumnPairs = new HashSet<Pair>();

        for (var row : rowMap.entrySet()) {
            for (var value : row.getValue().firstEntry().getValue()){
                minimalRowPairs.add(value);
            }
        }

        for (var col : colMap.entrySet()) {
            for (var value : col.getValue().firstEntry().getValue()){
                minimalColumnPairs.add(value);
            }
        }

        minimalRowPairs.retainAll(minimalColumnPairs);

        pairSet.addAll(minimalRowPairs);

        while (true){
            var isUsedChanged = false;
            for (Pair pair : pairSet) {
                var currentValue = pair.value;
                var specificRowMap = rowMap.get(pair.row);
                var specificColMap = colMap.get(pair.column);
                var sameValueRow = specificRowMap.get(currentValue);
                var sameValueCol = specificColMap.get(currentValue);

                for (Pair p : sameValueRow) {
                    if (!pairSet.contains(p)) {
                        pair.canBeUsed = false;
                        isUsedChanged = true;
                        break;
                    }
                }

                if (pair.canBeUsed) {
                    for (Pair p : sameValueCol) {
                        if (!pairSet.contains(p)) {
                            pair.canBeUsed = false;
                            isUsedChanged = true;
                            break;
                        }
                    }
                }

                if (pair.canBeUsed && !isUsedChanged) {
                    pair.rankValue = 1;
                }
            }
            pairSet.removeIf(pair -> !pair.canBeUsed);

            if(!isUsedChanged){
                break;
            }
        }

        // 构建连通图
//        var valueGraphMap = new HashMap<Integer, ArrayList<HashSet<Pair>>>();
//
//        for (Map.Entry<Integer, HashSet<Pair>> entry : valueToPairsMap.entrySet()) {
//            var regions = new ArrayList<HashSet<Pair>>();
//
//            for (var pair : entry.getValue()) {
//                var isFindRegion = false;
//
//                for (var region: regions){
//                    var canAddCurrentRegion = false;
//                    for (var otherPair: region){
//                        if(pair.row == otherPair.row || pair.column == otherPair.column){
//                            isFindRegion = true;
//                            canAddCurrentRegion = true;
//                            break;
//                        }
//                    }
//
//                    if(canAddCurrentRegion){
//                        region.add(pair);
//                    }
//                }
//
//                if(!isFindRegion){
//                    var newRegion = new HashSet<Pair>();
//                    newRegion.add(pair);
//                    regions.add(newRegion);
//                }
//            }
//
//            var finalRegions = new ArrayList<HashSet<Pair>>();
//            for (var region: regions){
//                for (var otherRegion: regions){
//                    if(!Collections.disjoint(region, otherRegion)){
//                        region.addAll(otherRegion);
//                        finalRegions.add(region);
//                    }
//                }
//            }
//            valueGraphMap.put(entry.getKey(), finalRegions);
//        }

//        System.out.println("valueGraphMap: " + valueGraphMap);

        while (!pairSet.isEmpty()){
//            System.out.println("Current round: " + roundTurn);
//            long roundTimeStart = System.nanoTime();
            var currentPairList = new HashSet<>(pairSet);
            pairSet.clear();
//            System.out.println("currentPairList size " + currentPairList.size());
            currentPairList.forEach(pair -> {
                pair.inUse = false;
                var specificRowMap = rowMap.get(pair.row);
                var specificColMap = colMap.get(pair.column);

                var higherColKey = specificColMap.higherKey(pair.value);
                var higherCol = higherColKey == null ? new ArrayList<Pair>() : specificColMap.get(higherColKey);

                var higherRowKey = specificRowMap.higherKey(pair.value);
                var higherRow = higherRowKey == null ? new ArrayList<Pair>() : specificRowMap.get(higherRowKey);

                for (Pair currentPair : higherRow) {
                    var newRankValue = pair.rankValue + 1;
                    if(currentPair.rankValue < newRankValue) {
                        currentPair.rankValue = newRankValue;
                        if (!currentPair.inUse) {
                            pairSet.add(currentPair);
                            currentPair.inUse = true;
                        }
                    }
                }

                for (Pair currentPair : higherCol) {
                    var newRankValue = pair.rankValue + 1;
                    if(currentPair.rankValue < newRankValue) {
                        currentPair.rankValue = newRankValue;
                        if (!currentPair.inUse) {
                            pairSet.add(currentPair);
                            currentPair.inUse = true;
                        }
                    }
                }

//                for (var currentSet : valueGraphMap.get(pair.value)){
//                    if(currentSet.contains(pair)){
//                        for (Pair currentPair : currentSet) {
//                            var newRankValue = pair.rankValue;
//                            if (newRankValue > currentPair.rankValue) {
//                                currentPair.rankValue = newRankValue;
//
//                                if (!currentPair.inUse) {
//                                    pairSet.add(currentPair);
//                                    currentPair.inUse = true;
//                                }
//                            }
//                        }
//                        break;
//                    }
//                }

                var sameRow = specificRowMap.get(pair.value);
                var sameColumn = specificColMap.get(pair.value);

                for (Pair currentPair : sameRow) {
                    var newRankValue = pair.rankValue;
                    if (newRankValue > currentPair.rankValue) {
                        currentPair.rankValue = newRankValue;

                        var pairX = pairs[currentPair.row][currentPair.column];
                        pairX.rankValue = newRankValue;
                        if (!pair.inUse) {
                            pairSet.add(pairX);
                            pairX.inUse = true;
                        }
                    }
                }

                for (Pair currentPair : sameColumn) {
                    var newRankValue = pair.rankValue;
                    if (newRankValue > currentPair.rankValue) {
                        currentPair.rankValue = newRankValue;

                        var pairX = pairs[currentPair.row][currentPair.column];
                        pairX.rankValue = newRankValue;
                        if (!pair.inUse) {
                            pairSet.add(pairX);
                            pairX.inUse = true;
                        }
                    }
                }
            });

//            roundTurn++;
//            long roundTimeEnd = System.nanoTime();
//            System.out.printf("【%s】耗时: %.3f ms%n", "CalcRank", (roundTimeEnd - roundTimeStart) / 1_000_000.0);
        }

//        System.out.println("Used round turn: " + roundTurn);
        return Arrays.stream(pairs)
                .map(row -> Arrays.stream(row).mapToInt(p -> p.rankValue).toArray())
                .toArray(int[][]::new);
    }
}