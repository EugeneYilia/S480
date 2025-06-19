package yichen;


import java.util.HashSet;

public class CanPartition {
    public static void main(String[] args) {
        System.out.println(new CanPartition().canPartition(new int[]{1,2,5}));
    }

    public boolean canPartition(int[] nums) {
        var sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) return false;

        var target = sum / 2;

        var path = new boolean[target];
        path[0] = true;

        for (int num : nums) {
            for (int i = path.length -1; i >= 0; i--) {
                if (path[i]) {
                    if(i + num == target) {
                        return true;
                    }

                    if(i + num < target) {
                        path[i + num] = true;
                    }

                }
            }
        }

        return false;
    }
}
