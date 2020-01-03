package com.orange.voice.test;

public class Leetcode {
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        for (int i = 1; i < points.length; i++) {
            int row = Math.abs(points[i][0] - points[i-1][0]);
            int col = Math.abs(points[i][1] - points[i-1][1]);
            ans += Math.max(row, col);
        }

        return ans;
    }

    public int findSpecialInteger(int[] arr) {
        int t = arr.length / 4;
        for (int i = 0; i + t < arr.length; i++) {
            if (arr[i] == arr[i+t]) {
                return arr[i];
            }
        }

        return 0;
    }

    public int getDecimalValue(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }

        return Integer.parseInt(sb.toString(), 2);
    }

    public int findNumbers(int[] nums) {
        int count = 0;
        for (int i : nums){
            if ((i >= 10) && (i <= 99) || (i >= 1000) && (i <= 9999) || i == 100000)
                count++;
        }
        return count;
    }

    public int[] replaceElements(int[] arr) {
        int l = arr.length;
        int[] dp = new int[l];
        dp[l-1] = arr[l-1];
        for (int i = l-2; i >= 0; i--) {
            dp[i] = Math.max(arr[i], dp[i+1]);
        }
        for (int i = 0; i < l - 1; i++) {
            arr[i] = dp[i+1];
        }
        arr[l-1] = -1;
        return arr;
    }
}
