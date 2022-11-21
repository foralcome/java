package org.geekbrains;

public class Solution27 {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;

        System.out.println("Count: " + removeElement(nums, val));
        print(nums);
    }

    public static int removeElement(int[] nums, int val) {
        int indexReplace = 0;
        int countVal = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[indexReplace] = nums[i];
                if (i != indexReplace) nums[i] = -1;
                indexReplace++;
                countVal++;
            }
        }
        for (int j = indexReplace; j < nums.length; j++) {
            nums[j] = -1;
        }

        return countVal;
    }

    public static void print(int[] arr) {
        for (int a : arr) {
            System.out.println(a);
        }
    }
}
