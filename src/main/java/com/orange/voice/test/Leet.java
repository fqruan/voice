package com.orange.voice.test;

import org.apache.ibatis.annotations.Mapper;

import java.util.*;
import java.util.stream.IntStream;

public class Leet {

    public static void main(String[] args) {
        Leet l = new Leet();
        int[] a = new int[]{1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0};
        System.out.println(Arrays.toString(l.beautifulArray(10)));
    }

    public int shortestBridge(int[][] A) {
        int n = A.length;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        //find 1st island
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    dfs(A, i, j, queue, n, visited);
                    break;
                }
            }
            if (!queue.isEmpty()) break;
        }

        // bfs to find the shortest bridge
        int res = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                int row = point[0];
                int col = point[1];
                for (int[] dir : dirs) {
                    int r = row + dir[0];
                    int c = col + dir[1];
                    if (r < 0 || r >= n || c < 0 || c >= n) continue;
                    if (A[r][c] == 1) return res;
                    if (!visited[r][c]) {
                        visited[r][c] = true;
                        queue.add(new int[]{r, c});
                    }
                }
            }
            res++;
        }
        return -1;
    }

    public void dfs(int[][] A, int i, int j, Queue<int[]> queue, int n, boolean[][] visited) {
        if (i < 0 || i >= n || j < 0 || j >= n || A[i][j] == 0) return;
        A[i][j] = 0;
        visited[i][j] = true;
        queue.add(new int[]{i, j});
        dfs(A, i + 1, j, queue, n, visited);
        dfs(A, i - 1, j, queue, n, visited);
        dfs(A, i, j + 1, queue, n, visited);
        dfs(A, i, j - 1, queue, n, visited);
    }

    private int[] arr;

    public int[] beautifulArray(int N) {
        arr = new int[N];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        beautifulArrayHelper(0, N-1);

        return arr;
    }

    public void beautifulArrayHelper(int start, int end) {
        if (start == end || start + 1 == end) return;
        int[] copy = Arrays.copyOf(arr, arr.length);
        int mid = (start + end) / 2 + 1;
        int mid2 = mid;
        int start2 = start;
        int sid = start;
        for (int i = start; i <= end; i += 2) {
            copy[start++] = arr[sid++];
            if (mid < copy.length) copy[mid++] = arr[sid++];
        }
        arr = copy;
        beautifulArrayHelper(start2, mid2 - 1);
        beautifulArrayHelper(mid2, end);
    }

    private Map<Integer, HashMap<Integer, Integer>> minFallingPathSumMap = new HashMap<>();

    public int minFallingPathSum(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int m1 = minFallingPathSumHelper(A, 1, i);
            int m2 = i > 0 ? minFallingPathSumHelper(A, 1, i-1) : Integer.MAX_VALUE;
            int m3 = i < n-1 ? minFallingPathSumHelper(A, 1, i+1) : Integer.MAX_VALUE;
            int cur = A[0][i] + Math.min(m1, Math.min(m2, m3));
            ans = Math.min(ans, cur);
        }

        return ans;
    }

    public int minFallingPathSumHelper(int[][] A, int row, int col) {
        if (minFallingPathSumMap.containsKey(row) && minFallingPathSumMap.get(row).containsKey(col)) {
            return minFallingPathSumMap.get(row).get(col);
        }
        int m = A.length;
        int n = A[0].length;
        if (row == m) return 0;
        int m1 = minFallingPathSumHelper(A, row + 1, col);
        int m2 = col > 0 ? minFallingPathSumHelper(A, row + 1, col-1) : Integer.MAX_VALUE;
        int m3 = col < n-1 ? minFallingPathSumHelper(A, row + 1, col+1) : Integer.MAX_VALUE;
        int cur = A[row][col] + Math.min(m1, Math.min(m2, m3));
        if (!minFallingPathSumMap.containsKey(row)) minFallingPathSumMap.put(row, new HashMap<>());
        minFallingPathSumMap.get(row).put(col, cur);

        return cur;
    }

    public int numSubarraysWithSum(int[] A, int S) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) list.add(i);
        }

        int ans = 0;
        if (S == 0) {
            if (list.size() == 0) return (1 + A.length) * A.length / 2;

            int tm = list.get(0);
            ans += (tm + 1) * tm / 2;
            //System.out.println(tm);
            for (int i = 1; i < list.size(); i++) {
                tm = list.get(i) - list.get(i-1) - 1;
                ans += (tm + 1) * tm / 2;
                //System.out.println(tm);
            }

            tm = A.length - list.get(list.size()-1) - 1;
            ans += (tm + 1) * tm / 2;
            //System.out.println(tm);
            return ans;
        }
        for (int i = 0; i <= list.size() - S; i++) {
            int left = list.get(i) - (i == 0 ? -1 : list.get(i-1));
            int right = (i + S < list.size() ? list.get(i + S) : A.length) - list.get(i + S - 1);
            ans += left * right;
        }

        return ans;
    }

    public int minFlipsMonoIncr(String S) {
        int n = S.length();
        int[] cnt0 = new int[n];

        cnt0[n-1] = S.charAt(n-1) == '0' ? 1 : 0;
        for (int i = n - 2; i  >= 0; i--) {
            if (S.charAt(i) == '0') cnt0[i] = cnt0[i+1] + 1;
            else cnt0[i] = cnt0[i+1];
        }

        int ans = minFlipsMonoIncrHelper(S, 0, cnt0);

        return ans;
    }

    private int minFlipsMonoIncrHelper(String s, int start, int[] cnt0) {
        if (start >= s.length()) return 0;
        if (s.charAt(start) == '0') return minFlipsMonoIncrHelper(s, start + 1, cnt0);
        return Math.min(start == s.length() - 1 ? 0 : cnt0[start + 1], 1 + minFlipsMonoIncrHelper(s, start + 1, cnt0));
    }

    private static int mod = 1000000007;
    public int threeSumMulti(int[] a, int t) {
        int count = 0, n = a.length;
        for(int i=0; i < n; i++){
            Map<Integer, Integer> partner = new HashMap<>();
            int t1 = t-a[i];
            for(int j=i+1; j < n; j++){
                count = count % mod + partner.getOrDefault(t1-a[j], 0)  % mod;
                partner.put(a[j], partner.getOrDefault(a[j], 0)+1);
            }
        }
        return count % mod;
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] arr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = map.get(nums[i]);
        }

        return nums;
    }

    public int[] sortByBits(int[] arr) {
        Integer[] items = IntStream.of(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(items, (a,b) -> countBits(a) == countBits(b) ? a - b : countBits(a) - countBits(b));
        return Arrays.stream(items).mapToInt(i->i).toArray();
    }

    private int countBits(int n) {
        int count = 0;
        int mask = 1;
        while(n != 0) {
            if(n % 2 == 1) count++;
            n = n >> mask;
        }
        return count;
    }

    private int oneCount(int num) {
        int ans = 0;
        while (num != 0) {
            num = num & (num-1);
            ans++;
        }

        return ans;
    }

    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int c = 0;
        for (int x : arr) {
            set.add(x);
            if (x == 0) c++;
        }

        for (int x : arr) {
            if (set.contains(2 * x) && x != 0) return true;
            if (x == 0 && c > 1) return true;
        }

        return false;
    }

    public int numberOfSteps (int num) {
        int ans = 0;
        while (num != 1) {
            if (num % 2 == 0)
                num /= 2;
            else
                num--;
            ans++;
        }

        return ans;
    }

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
