package twopointer;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        int [] numbers = {-1, 0, 1, 2, -1, -4};
        int target = 0;
        List<List<Integer>> res = calculateThreeSum(numbers, target);
        System.out.println(res);
    }

    private static List<List<Integer>> calculateThreeSum(int[] nums, int ans) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            //skip duplicate value for i
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int target = ans - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                int sum = nums[left] + nums[right];
                if(sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //skip left value if dup
                    if(left < right && nums[left] == nums[left+1]){
                        left++;
                    }

                    //Skip right value if dup
                    if(left < right && nums[right] == nums[right-1]){
                        right--;
                    }

                    left++;
                    right --;
                } else if (sum < target) {
                    left ++;
                } else {
                    right --;
                }

            }

        }
        return result;
    }
}
