public class BinarySearch {
    public int search(int[] nums, int target) {
        // write your code here.
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    // 2 3 4 6   5
    // 0 1 2  3
    // 1
    // 0 1   1
    int binarySearch(int[] nums, int start, int end, int target){
        if(start > end){
            return -1;
        }

        int middle = start + (end - start)/2;
        if(nums[middle] == target){
            return middle;
        } else if(nums[middle] > target){
            return binarySearch(nums, start, middle -1, target);
        } else {
            return binarySearch(nums, middle + 1, end, target);
        }
    }
}