package com.orange.voice.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leet {

    public int[] arrayRankTransform(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] b = Arrays.copyOf(arr, arr.length);
        Arrays.sort(b);
        int count = 1;
        for (int i = 0; i < b.length; i++) {
            if (!map.containsKey(b[i])) {
                map.put(b[i], count++);
            }
        }

        int[] ans = new int[b.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = map.get(arr[i]);
        }

        return ans;
    }

    public int maximum69Number (int num) {
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '6') {
                sb.setCharAt(i, '9');
                break;
            }
        }

        return Integer.parseInt(sb.toString());
    }

    public int[] decompressRLElist(int[] nums) {
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                al.add(nums[i+1]);
            }
        }

        int[] ans = al.stream().mapToInt(Integer::valueOf).toArray();

        return ans;
    }

    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (i + 2 < s.length() && s.charAt(i+2) == '#') {
                sb.append((char) ('a' - 1 + Integer.parseInt(s.substring(i, i + 2))));
                i += 2;
            }else {
                sb.append((char)('a' - 1 + Integer.parseInt(s.substring(i, i + 1))));
            }
        }

        return sb.toString();
    }

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
