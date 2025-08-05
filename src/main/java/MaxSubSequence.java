public class MaxSubSequence {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");

        MaxSubSequence sut = new MaxSubSequence();

        // ✅ 原始测试用例（转换为 assert）
        assert sut.getMaxSubSequence(new int[]{1, 2, 3, 0, 4, 5, 5}, 7, 3) == 4;
        assert sut.getMaxSubSequence(new int[]{1, 1, 1, 1}, 2, 1) == 2;
        assert sut.getMaxSubSequence(new int[]{5, 1, 1, 5}, 3, 5) == -1;
        assert sut.getMaxSubSequence(new int[]{2, 2, 2}, 5, 2) == 2;
        assert sut.getMaxSubSequence(new int[]{1, 2, 3, 4, 5}, 5, 5) == 1;
        assert sut.getMaxSubSequence(new int[]{6}, 5, 6) == -1;
        assert sut.getMaxSubSequence(new int[]{6}, 6, 6) == 1;

        // ✅ 新增测试用例

        // 空数组
        assert sut.getMaxSubSequence(new int[]{}, 5, 1) == -1;

        // 只有 target，等于 K
        assert sut.getMaxSubSequence(new int[]{5}, 5, 5) == 1;

        // 多个 target，只有其中一段合法
        assert sut.getMaxSubSequence(new int[]{5, 1, 1, 5, 1, 1, 5}, 7, 5) == 3;

        // target 出现在多个位置，但无法组成合法区间
        assert sut.getMaxSubSequence(new int[]{9, 9, 5, 9}, 8, 5) == -1;

        // target 出现在长合法窗口中
        assert sut.getMaxSubSequence(new int[]{1, 1, 1, 1, 5}, 9, 5) == 5;

        // target 最后一个才出现
        assert sut.getMaxSubSequence(new int[]{1, 1, 1, 1, 5}, 5, 5) == 2;

        // 所有元素等于 target
        assert sut.getMaxSubSequence(new int[]{3, 3, 3, 3}, 10, 3) == 3;

        // 大数组中只有一处合法且包含 target
        assert sut.getMaxSubSequence(new int[]{9, 8, 7, 1, 2, 3, 1, 2, 3, 9}, 6, 2) == 3;

        // K 太小，任何区间都不合法
        assert sut.getMaxSubSequence(new int[]{1, 2, 3}, 0, 1) == -1;

        // target 大于 K，直接不可能
        assert sut.getMaxSubSequence(new int[]{1, 2, 3}, 2, 3) == -1;

        // target 恰好位于最短区间内
        assert sut.getMaxSubSequence(new int[]{2, 3, 1, 2, 4, 3}, 5, 4) == 2;

        System.out.println("✅ 所有断言通过！");
    }

    public int getMaxSubSequence(int[] nums, int k, int target) {
        int maxSequenceCount = -1;
        if (target > k) {
            return maxSequenceCount;
        }

        int sum = 0;
        int left = 0;
        int right = 0;
        int currentSequenceCount = 0;
        int targetCount = 0;
        for (int i = 0; i < nums.length; i++) {
            right = i;
            if (nums[i] == target) {
                targetCount++;
            }
            sum += nums[i];
            currentSequenceCount += 1;
            if (sum > k) {
                while (sum > k && left <= right && currentSequenceCount >= 1) {
                    sum -= nums[left];
                    if (nums[left] == target) {
                        targetCount--;
                    }

                    if (left < right) {
                        left++;
                    }
                    currentSequenceCount -= 1;
                }
            }
            if (targetCount > 0) {
                maxSequenceCount = Math.max(maxSequenceCount, currentSequenceCount);
            }
        }

        return maxSequenceCount;
    }
}
