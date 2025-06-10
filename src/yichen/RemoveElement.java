package yichen;

import java.util.Arrays;

public class RemoveElement {
    public static void main(String[] args) {
        var nums = new int[] {3, 2, 2, 3};
        var result = removeElement(nums, 3);

        System.out.println(result);
        System.out.println(Arrays.toString(nums));
    }

    public static int removeElement(int[] nums, int val) {
        var validCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                nums[validCount] = nums[i];
                validCount++;
            }
        }
        return validCount;
    }
}
