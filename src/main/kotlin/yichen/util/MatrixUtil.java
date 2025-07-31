package yichen.util;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtil {
    public static int[][] parseMatrix(String input) {
        // 去掉首尾空白与中括号
        input = input.trim();
        if (input.startsWith("[")) input = input.substring(1);
        if (input.endsWith("]")) input = input.substring(0, input.length() - 1);

        // 处理空数组
        if (input.isEmpty()) return new int[0][0];

        // 按子数组分割
        List<int[]> rows = new ArrayList<>();
        for (String rowStr : input.split("],")) {
            rowStr = rowStr.replaceAll("\\[", "").replaceAll("]", "").trim();
            if (rowStr.isEmpty()) {
                rows.add(new int[0]);
            } else {
                String[] nums = rowStr.split(",");
                int[] row = new int[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    row[i] = Integer.parseInt(nums[i].trim());
                }
                rows.add(row);
            }
        }

        return rows.toArray(new int[0][]);
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
