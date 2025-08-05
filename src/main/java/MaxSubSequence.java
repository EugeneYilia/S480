public class MaxSubSequence {
    public static void main(String[] args) {
        var sut = new MaxSubSequence();
        System.out.println(sut.getMaxSubSequence(new int[]{1, 2, 3, 0, 4, 5, 5}, 7, 3));  // 输出: 4
        System.out.println(sut.getMaxSubSequence(new int[]{1, 1, 1, 1}, 2, 1));          // 输出: 2
        System.out.println(sut.getMaxSubSequence(new int[]{5, 1, 1, 5}, 3, 5));          // 输出: -1
        System.out.println(sut.getMaxSubSequence(new int[]{2, 2, 2}, 5, 2));             // 输出: 2
        System.out.println(sut.getMaxSubSequence(new int[]{1, 2, 3, 4, 5}, 5, 5));       // 输出: 2
    }
    
    public int getMaxSubSequence(int[] nums, int k, int target) {
        int maxSequenceCount = -1;
        if(target > k) {
            return maxSequenceCount;
        }

        int sum = 0;
        int left = 0;
        int right = 0;
        int currentSequenceCount = 0;
        int targetCount = 0;
        for(int i=0;i<nums.length;i++){
            right = i;
            if(nums[i] == target){
                targetCount++;
            }
            sum += nums[i];
            currentSequenceCount +=1;
            if(sum>k){
                while(sum > k && left <= right && currentSequenceCount >= 1){
                    sum -= nums[left];
                    if(nums[left] == target){
                        targetCount--;
                    }

                    if(left<right){
                        left++;
                    }
                    currentSequenceCount -= 1;
                }
            }
            if(targetCount >0){
                maxSequenceCount = Math.max(maxSequenceCount, currentSequenceCount);
            }
        }

        return maxSequenceCount;
    }
}
