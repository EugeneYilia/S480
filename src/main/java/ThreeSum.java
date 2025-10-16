// deprecated

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
    // 代码中已指定的类名、方法名、参数名，请勿修改，直接返回方法规定的值即可

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            ArrayList<Integer> buffer = new ArrayList<>();
            buffer.add(nums[i]);
            calcSum(result, buffer, nums, i + 1);
        }

        return result;
    }

    public void calcSum(List<List<Integer>> result, ArrayList<Integer> buffer, int[] nums, int index) {
        for (int i = index; i < nums.length; i++) {
            ArrayList<Integer> newBuffer = new ArrayList<Integer>(buffer);
            newBuffer.add(nums[i]);
            if (newBuffer.size() == 3) {
                if (newBuffer.get(0) + newBuffer.get(1) + newBuffer.get(2) == 0) {
                    Collections.sort(newBuffer);

                    for(List<Integer> list: result) {
                        if(list.get(0) == newBuffer.get(0) && list.get(1) == newBuffer.get(1) && list.get(2) == newBuffer.get(2)){
                            return;
                        }
                    }

                    result.add(newBuffer);
                }
            } else {
                calcSum(result, newBuffer, nums, i + 1);
            }
        }
    }
}
