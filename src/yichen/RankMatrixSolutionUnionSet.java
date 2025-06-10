package yichen;

import test.RankMatrixSolution2;
import yichen.util.MatrixUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


// Leetcode
// https://leetcode.com/problems/rank-transform-of-a-matrix/submissions/1657762154/?source=submission-ac
// 1632. Rank Transform of a Matrix

// Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
//
// The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
//
// The rank is an integer starting from 1.
// If two elements p and q are in the same row or column, then:
// If p < q then rank(p) < rank(q)
// If p == q then rank(p) == rank(q)
// If p > q then rank(p) > rank(q)
// The rank should be as small as possible.
// The test cases are generated so that answer is unique under the given rules.
public class RankMatrixSolutionUnionSet {
    public static void main(String[] args) throws IOException {
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
        var result = matrixRankTransform(originalMatrix);
        long end = System.nanoTime();
        System.out.printf("【%s】耗时: %.3f ms%n", "printFormattedMatrix", (end - start) / 1_000_000.0);

        var standardAnswer = new RankMatrixSolution2().matrixRankTransform(originalMatrix);
        System.out.println("Answer is right: " + MatrixUtil.areMatricesEqual(result, standardAnswer));
    }

    public static int[][] matrixRankTransform(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] rank = new int[row][col];

        // 现存每行和每列的最大值记录
        int[] maxRow = new int[row];
        int[] maxCol = new int[col];

        // 把同一个大小的数，归并起来
        var sortedNumber = new TreeMap<Integer, HashSet<int[]>>();

        // [1] -> [(1,2), (3,4)]     [2] -> [(0,0), (1,1)]
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                sortedNumber.computeIfAbsent(matrix[i][j], _ -> new HashSet<int[]>()).add(new int[]{i, j});
            }
        }

        // 不连通的值就不会互相影响
        // 连通的值才会互相影响
        // 只应该更新连通的值

        // entry  key 就是实际的值   value 就是对应的点阵列表

        // pairs: 每一个点数列表
        for(var pairs : sortedNumber.values()){
            // 第一步构建连通空间
            var region = new ConnectRegion(row + col);
            for(int[] pair : pairs){
                // pair[0] 横坐标    pair[1] 纵坐标
                // 列坐标需要用列本身的数值加上行数对应的偏移
                region.connect(pair[0], pair[1] + row);
            }

            // key就是每一个连通空间编号，值就是点位集合
            var connectMap = new HashMap<Integer, HashSet<int[]>>();
            // 第二步，归属每一个点，到对应的连通空间里
            for (int[] pair: pairs){
                // 用列坐标进行查询的时候，要加上行数对应的偏移
                int daddy = region.find(pair[1] + row);

                connectMap.computeIfAbsent(daddy, _ -> new HashSet<int[]>()).add(pair);
            }


            // 第三步，将每一个连通区域进行统一的rank计算和更新
            for (var connectedPairs: connectMap.values()){
                int rankScore = 1;
                for(int[] connectedPair: connectedPairs){
                    var rowMax = maxRow[connectedPair[0]];
                    var colMax = maxCol[connectedPair[1]];
                    rankScore = Math.max(rankScore, Math.max(rowMax, colMax) + 1);
                }

                for(int[] connectedPair: connectedPairs) {
                    rank[connectedPair[0]][connectedPair[1]] = rankScore;
                    maxRow[connectedPair[0]] = rankScore;
                    maxCol[connectedPair[1]] = rankScore;
                }
            }
        }

        return rank;
    }

    public static class ConnectRegion{
        int[] whoIsYourDaddy;

        public ConnectRegion(int size){
            whoIsYourDaddy = new int[size];
            for(int i=0;i<size;i++){
                whoIsYourDaddy[i]=i;
            }
        }

        public void connect(int i, int j){
            whoIsYourDaddy[find(i)] = find(j);
        }

        public int find(int i){
            if (whoIsYourDaddy[i] != i) {
                whoIsYourDaddy[i] = find(whoIsYourDaddy[i]);
            }

            return whoIsYourDaddy[i];
        }
    }
}