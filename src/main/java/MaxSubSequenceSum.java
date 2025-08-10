public class MaxSubSequenceSum {
    public static void main(String[] args) {
        System.out.println(calcMaxValue(new int[]{3, -5, 7, -2, 8}) == 13);          // ✅ 子段 [7, -2, 8]
        System.out.println(calcMaxValue(new int[]{-6, -9, -10}) == 0);               // ✅ 全负
        System.out.println(calcMaxValue(new int[]{5}) == 0);                         // ✅ 只有一个数
        System.out.println(calcMaxValue(new int[]{-1}) == 0);                        // ✅ 只有一个负数
        System.out.println(calcMaxValue(new int[]{1, 2}) == 3);                      // ✅ 整段最大
        System.out.println(calcMaxValue(new int[]{1, 2, 3}) == 6);                   // ✅ 整段最大
        System.out.println(calcMaxValue(new int[]{1, -10, 2, 3}) == 5);              // ✅ 子段 [2, 3]
        System.out.println(calcMaxValue(new int[]{1, 2, 3, 4, 5}) == 15);            // ✅ 整段
        System.out.println(calcMaxValue(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6); // ✅ [4, -1, 2, 1]
        System.out.println(calcMaxValue(new int[]{0, 0, 0}) == 0);                   // ✅ 全 0
        System.out.println(calcMaxValue(new int[]{-1, 2, 3, -2, 4, -10, 10}) == 7);  // ✅ [2, 3, -2, 4]
        System.out.println(calcMaxValue(new int[]{10, -3, 2, -1, 2, -5, 4, 6}) == 15); // ✅ 整段
        System.out.println(calcMaxValue(new int[]{-2, -3, 4, -1, -2, 1, 5, -3}) == 7); // ✅ [4, -1, -2, 1, 5]
        System.out.println(calcMaxValue(new int[]{5, -1}) == 4);    // ✅ 返回 4
        System.out.println(calcMaxValue(new int[]{3, 2}) == 5);     // ✅ 返回 5
    }
    public static int calcMaxValue(int[] src){
        int left = 0;
        int right = 0;
        int sum = 0;
        int maxSum = 0;

        for (int i = 0; i < src.length; i++) {
            if (sum < 0) {
                sum = 0;
                left = i;
            }
            right = i;

            sum += src[i];

            if(right != left) {
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}
