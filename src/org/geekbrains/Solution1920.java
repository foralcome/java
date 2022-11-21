package org.geekbrains;

public class Solution1920 {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 2, 1, 5, 3, 4};
        print(buildArray(nums));
    }

    public static int[] buildArray(int[] nums) {
        int indexAns = 0;
        int[] ans = new int[nums.length];

        for (int index : nums) {
            if (index >= 0 && index < nums.length) {
                ans[indexAns] = nums[index];
                indexAns++;
            }
        }

        return ans;
    }

    public static void print(int[] arr) {
        for (int a : arr) {
            System.out.println(a);
        }
    }
}
