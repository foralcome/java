package org.geekbrains;

class Solution88 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        int m = 3;
        int n = 3;

        /*int[] nums1 = new int[]{0,0,0,0,0};
        int[] nums2 = new int[]{1,2,3,4,5};
        int m = 0;
        int n = 5;*/

        /*int[] nums1 = new int[]{4,0,0,0,0,0};
        int[] nums2 = new int[]{1,2,3,4,5};
        int m = 1;
        int n = 5;*/

        /*int[] nums1 = new int[]{4,5,6,0,0,0};
        int[] nums2 = new int[]{1,2,3};
        int m = 3;
        int n = 3;*/

        merge(nums1, m, nums2, n);
        print(nums1);
    }
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int indexEnd1 = m + n - 1;
        int indexEnd2 = n - 1;
        int indexCheck = -1;
        if (m > 0)
            indexCheck = m - 1;

        while (indexEnd2 >= 0) {
            if (indexCheck < 0) {
                nums1[indexEnd1] = nums2[indexEnd2];
                indexEnd2--;
            } else {
                if (nums1[indexCheck] <= nums2[indexEnd2]) {
                    nums1[indexEnd1] = nums2[indexEnd2];
                    indexEnd2--;
                } else {
                    nums1[indexEnd1] = nums1[indexCheck];
                    indexCheck--;
                }
            }
            indexEnd1--;
        }
    }

    public static void print(int[] arr){
        for (int a : arr) {
            System.out.println(a);
        }
    }
}