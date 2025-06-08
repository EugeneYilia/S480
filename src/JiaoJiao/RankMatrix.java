package JiaoJiao;

import java.util.*;

public class RankMatrix {
    class Pair{
        int row;
        int col;

        public Pair(int row,int col){
            this.row=row;
            this.col=col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof Pair
                    && this.row == ((Pair) obj).row
                    && this.col == ((Pair) obj).col;
        }

        @Override
        public String toString() {
            return "[" + row+","+col + "]";
        }
    }
    public int[][] matrixRankTransform(int[][] matrix) {
        var rank = new int[matrix.length][matrix[0].length];

        HashMap<Integer, TreeMap<Integer, HashSet<Pair>>> rows = new HashMap<>();
        HashMap<Integer, TreeMap<Integer, HashSet<Pair>>> cols = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            rows.put(i, new TreeMap<>());
            for (int j = 0; j < matrix[i].length; j++) {
                cols.putIfAbsent(j, new TreeMap<>());
                try {
                    rows.get(i).computeIfAbsent(matrix[i][j], _ -> new HashSet<Pair>()).add(new Pair(i, j));
                    cols.get(j).computeIfAbsent(matrix[i][j], _ -> new HashSet<Pair>()).add(new Pair(i, j));
                } catch (Exception e) {
                    System.out.println("rows.get(" + i + ") = " + rows.get(i));
                    System.out.println("matrix[" + i + "][" + j + "] = " + matrix[i][j]);
                    throw e;  // 或 e.printStackTrace();
                }
            }
        }

//        System.out.println("rows:" + rows);
//        System.out.println("cols:" + cols);

        var minRows = new HashSet<Pair>();
        var minCols = new HashSet<Pair>();

        for (var row : rows.entrySet()) {
            for (var value : row.getValue().firstEntry().getValue()){
                minRows.add(value);
            }
        }

        for (var col : cols.entrySet()) {
            for (var value : col.getValue().firstEntry().getValue()){
                minCols.add(value);
            }
        }

        System.out.println("minRows = " + minRows);
        System.out.println("minCols = " + minCols);
        var resultSet = new HashSet<Pair>(minRows);
        resultSet.retainAll(minCols);

        System.out.println("resultSet:" + resultSet);

        return rank;
    }
}
