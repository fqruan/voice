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
}
