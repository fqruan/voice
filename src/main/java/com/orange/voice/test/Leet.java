package com.orange.voice.test;

import org.apache.ibatis.annotations.Mapper;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Leet {

    public void test() {
        String s = "\\";
        System.out.println(s.length() + "_" + s);
    }

    public static void main(String[] args) {
        Leet l = new Leet();
        int[] a = new int[]{3,4,1,2};
        int[] b = new int[]{4,5,3,2,1};
        String[] strings = new String[]{"doeeqiy","yabhbqe","twckqte"};
        System.out.println(l.maxWidthRamp3(a));
//        l.test();
    }

    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < index.length; i++) {
            list.add(index[i], nums[i]);
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : arr2) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int ans = 0;
        A : for (int i = 0; i < arr1.length; i++) {
            for (int j = arr1[i] - d; j <= arr1[i] + d; j++) {
                if (map.containsKey(j)) continue A;
            }
            ans++;
        }

        return ans;
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exact = new HashSet();
        Map<String, String> cap = new HashMap();
        Map<String, String> vowels = new HashMap();

        for (String w : wordlist) {
            exact.add(w);
            if (!cap.containsKey(w.toLowerCase()))
                cap.put(w.toLowerCase(), w);
            String s = w.toLowerCase()
                    .replaceAll("i", "1")
                    .replaceAll("o", "1")
                    .replaceAll("e", "1")
                    .replaceAll("u", "1")
                    .replaceAll("a", "1");
            if (!vowels.containsKey(s))
                vowels.put(s, w);
        }

        String[] ret = new String[queries.length];
        int i = 0;

        for (String q : queries) {
            if (exact.contains(q)) { ret[i++] = q; continue; }
            if (cap.containsKey(q.toLowerCase())) { ret[i++] = cap.get(q.toLowerCase()); continue; }
            String s = q.toLowerCase()
                    .replaceAll("i", "1")
                    .replaceAll("o", "1")
                    .replaceAll("e", "1")
                    .replaceAll("u", "1")
                    .replaceAll("a", "1");

            ret[i++] = vowels.getOrDefault(s, "");
        }

        return ret;
    }

    public double minAreaFreeRect(int[][] points) {
        Map<String, Map<Double, List<int[]>>> map = new HashMap<>();
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double centerX = ((double)points[i][0] + points[j][0]) / 2;
                double centerY = ((double)points[i][1] + points[j][1]) / 2;
                double dist = ((double)points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                String str = centerX + " " + centerY;
                map.putIfAbsent(str, new HashMap<>());
                map.get(str).putIfAbsent(dist, new ArrayList<>());
                map.get(str).get(dist).add(points[i]);
                map.get(str).get(dist).add(points[j]);
            }
        }
        double min = Double.MAX_VALUE;
        for (String c : map.keySet()) {
            for (double d : map.get(c).keySet()) {
                List<int[]> list = map.get(c).get(d);
                if (list.size() >= 4) {
                    for (int i = 0; i + 2 < list.size(); i += 2) {
                        for (int j = i + 2; j + 1 < list.size(); j += 2) {
                            double dist1 = Math.sqrt(((double)list.get(i)[0] - list.get(j)[0]) * ((double)list.get(i)[0] - list.get(j)[0]) + ((double)list.get(i)[1] - list.get(j)[1]) * ((double)list.get(i)[1] - list.get(j)[1]));
                            double dist2 = Math.sqrt(((double)list.get(i)[0] - list.get(j + 1)[0]) * ((double)list.get(i)[0] - list.get(j + 1)[0]) + ((double)list.get(i)[1] - list.get(j + 1)[1]) * ((double)list.get(i)[1] - list.get(j + 1)[1]));
                            min = Math.min(dist1 * dist2, min);
                        }
                    }
                }
            }
        }
        return min == Double.MAX_VALUE ? 0 : min;
    }

    public int maxWidthRamp3(int[] A) {
        int[] indexes = new int[A.length];
        int num0 = A[0], len = 1;
        for(int i = 1; i < A.length; i++) {
            if(A[i] < num0) {
                num0 = A[i];
                indexes[len++] = i;
            }
        }
        num0 = Integer.MIN_VALUE;
        int maxWidth = 0;
        for(int i = A.length - 1; i > maxWidth; i--) {
            if(A[i] > num0) {
                num0 = A[i];
                for(;; len--) {
                    if(len == 0 || A[indexes[len - 1]] > A[i]) {
                        maxWidth = Math.max(maxWidth, i - indexes[len]);
                        break;
                    }
                }
            }
        }
        return maxWidth;
    }

    public int maxWidthRamp2(int[] A) {
        int[] indexes = new int[A.length];
        int num0 = A[0], len = 1;
        for(int i = 1; i < A.length; i++) {
            if(A[i] < num0) {
                num0 = A[i];
                indexes[len++] = i;
            }
        }
        num0 = Integer.MIN_VALUE;
        int maxWidth = 0;
        for(int i = A.length - 1; i > maxWidth; i--) {
            if(A[i] > num0) {
                num0 = A[i];
                for(;; len--) {
                    if(len == 0 || A[indexes[len - 1]] > A[i]) {
                        maxWidth = Math.max(maxWidth, i - indexes[len]);
                        break;
                    }
                }
            }
        }
        return maxWidth;
    }

    public int maxWidthRamp(int[] A) {
        for (int i = A.length - 1; i > 0; i--) {
            for (int j = 0; j + i < A.length; j++) {
                if (A[i + j] >= A[j]) return i;
            }
        }

        return 0;
    }

    public int regionsBySlashes(String[] grid) {
        int ans = 1;
        if (grid[0].charAt(0) == '/') ans++;
        else if (grid[0].charAt(0) == '\\') {
            if (grid[1].charAt(1) == '\\') ans++;
        }
        if (grid[0].charAt(1) == '\\') ans++;
        else if (grid[0].charAt(1) == '/') {
            if (grid[1].charAt(0) == '/') ans++;
        }
        if (grid[1].charAt(0) == '\\') ans++;
        if (grid[1].charAt(1) == '/') ans++;

        if (grid[0].charAt(1) == '/' && grid[1].charAt(0) == '/' && grid[0].charAt(0) == '\\' && grid[1].charAt(1) == '\\') ans++;
        return ans;
    }

    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return true;
        //res[0]: count of nodes
        //res[1]: index of nodes, if is complete tree, index = count - 1
        int [] res = new int[2];
        isCompleteTree(root, res, 1);
        return res[0] == res[1];
    }

    private void isCompleteTree(TreeNode root, int[] res, int index) {
        if(root != null) {
            res[0]++;
            res[1] = Math.max(res[1], index);
            isCompleteTree(root.left, res, 2 * index);
            isCompleteTree(root.right, res, 2 * index + 1);
        }
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        if (N == 0) return cells;
        if (cells[0] == 1 || cells[7] == 1) {
            cells = oneDay(cells);
            cells[0] = 0;
            cells[7] = 0;
            N--;
        }

        int[] arr = cells;
        int count = 0;

        while (N-- > 0) {
            arr = oneDay(arr);
            count++;
            if (Arrays.equals(arr, cells))
                N %= count;
        }
        return arr;
    }

    private int[] oneDay(int[] cells) {
        int[] r = new int[8];
        for (int i = 1; i < 7; i++) r[i] = 1 - (cells[i-1] ^ cells[i+1]);
        return r;
    }

    public int minDeletionSize(String[] A) {
        if (A.length == 0 || A[0].length() == 0) return 0;
        int ans = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i < A.length; i++) {
            set.add(i);
        }
        for (int i = 0; i < A[0].length(); i++) {
            HashSet<Integer> setCopy = new HashSet<>(set);
            for (int j = 1; j < A.length; j++) {
                if (A[j].charAt(i) > A[j-1].charAt(i)) {
                    if (set.contains(j)) set.remove(j);
                }else if (A[j].charAt(i) < A[j-1].charAt(i)) {
                    if (set.contains(j)) {
                        ans++;
                        set = new HashSet<>(setCopy);
                        break;
                    }
                }
            }
        }

        return ans;
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val)  return false;
        if (root1.left == null && root2.left == null && root1.right == null && root2.right == null) return true;
        if (root1.left == null && root2.left != null && root1.right != null && root2.right == null) {
            return flipEquiv(root1.right, root2.left);
        }
        if (root1.left != null && root2.left != null && root1.right == null && root2.right == null) {
            return flipEquiv(root1.left, root2.left);
        }
        if (root1.left != null && root2.left == null && root1.right == null && root2.right != null) {
            return flipEquiv(root1.left, root2.right);
        }
        if (root1.left == null && root2.left == null && root1.right != null && root2.right != null) {
            return flipEquiv(root1.right, root2.right);
        }
        if (root1.left != null && root2.left != null && root1.right != null && root2.right != null) {
            if (root1.left.val == root2.left.val && root1.right.val == root2.right.val) {
                return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
            }
            if (root1.left.val == root2.right.val && root1.right.val == root2.left.val) {
                return flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
            }
        }

        return false;
    }

    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        List<Integer> al = Arrays.stream(deck).boxed().collect(Collectors.toList());
        int s = al.size()-2;
        List<Integer> ansList = new ArrayList<>();
        ansList.add(al.get(al.size()-1));
        while (s >= 0) {
            int tm = ansList.get(ansList.size()-1);
            ansList.remove(ansList.size()-1);
            ansList.add(0, tm);
            ansList.add(0, al.get(s--));
        }

        int[] ans = ansList.stream().mapToInt(Integer::valueOf).toArray();

        return ans;
    }

    public int bagOfTokensScore(int[] tokens, int P) {
        int power = P;
        int point = 0;
        int ans = 0;
//        int sum = 0;
//        for (int x : tokens) {
//            sum += x;
//        }
        Arrays.sort(tokens);
        if (tokens.length == 0 || P < tokens[0]) return 0;
        int left = 0;
        int right = tokens.length-1;
        while (left <= right) {
            if (power >= tokens[left]) {
                power -= tokens[left];
                //sum -= tokens[left];
                left++;
                point++;
                ans = Math.max(ans, point);
            }else if (point == 0) {
                return ans;
            }else {
                point--;
                power += tokens[right];
                //sum -= tokens[right];
                right--;
            }
        }

        return ans;
    }

    public int removeStones(int[][] stones) {
        int[] fa = new int[stones.length];
        int[] num = new int[stones.length];
        for (int i = 0; i < stones.length; i++) {
            fa[i] = i;
            num[i] = 1;
        }

        for (int i = 0; i < stones.length; i++) {
            for (int j = i + 1; j < stones.length; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    stonesUnion(i, j, fa, num);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < num.length; i++) {
            if (fa[i] == i && num[i] > 1) {
                ans += num[i] - 1;
            }
        }

        return ans > 0 ? ans : 0;
    }

    private int findStoneGroup(int x, int[] fa) {
        if (x == fa[x]) return x;
        return findStoneGroup(fa[x], fa);
    }

    private void stonesUnion(int x, int y, int[] fa, int[] num) {
        int fx = findStoneGroup(x, fa);
        int fy = findStoneGroup(y, fa);
        if (fx != fy) {
            fa[fy] = x;
            num[fx] += num[fy];
        }
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int push = 0;
        int pop = 0;
        while (push < pushed.length || pop < popped.length) {
            if (stack.empty() || stack.peek() != popped[pop]) {
                if (push < pushed.length)
                    stack.push(pushed[push++]);
                else
                    return false;
            }else {
                stack.pop();
                pop++;
            }
        }

        return true;
    }

    public int minIncrementForUnique2(int[] A) {
        Queue<Integer> q = new LinkedList<>();
        int[] container = new int[50000];
        int ans = 0, maxNum = 0, i = 0;

        for(int element : A){
            container[element]++;
            maxNum = Math.max(maxNum, element);
        }

        while(i < maxNum+1 || q.size() > 0){
            if(container[i] > 1) q.add(i);
            else if(container[i] == 0){
                if (q.size() > 0) {
                    int x = q.poll();
                    ans += (i - x);
                    container[x]--;
                    if (container[x] != 1) q.add(x);
                }
            }
            i++;
        }
        return ans;
    }

    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        HashSet<Integer> set = new HashSet<>();
        int ans = 0;
        for (int x : A) {
            if (!set.contains(x)) {
                set.add(x);
            }else {
                while (set.contains(x)) {
                    x++;
                    ans++;
                }
                set.add(x);
            }
        }
        return ans;
    }

    public int minAreaRect2(int[][] points) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int w1 = points[i][0] - points[j][0];
                int w2 = points[i][1] - points[j][1];

                if (w1 == 0 || w2 == 0) continue;
                int area = Math.abs(w1) * Math.abs(w2);
                if (area > res) continue;

                boolean point3 = false, point4 = false;
                for (int[] p3 : points) {
                    if (p3[0] == points[i][0] && p3[1] == points[j][1]) {
                        point3 = true;
                        break;
                    }
                }

                for (int[] p4 : points) {
                    if (p4[0] == points[j][0] && p4[1] == points[i][1]) {
                        point4 = true;
                        break;
                    }
                }

                if (point3 && point4) res = area;
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int minAreaRect(int[][] points) {
        Map<Integer, HashSet<Integer>> map = new HashMap<>();
        int ans = 0;
        for (int[] a : points) {
            if (!map.containsKey(a[0])) map.put(a[0], new HashSet<>());
            map.get(a[0]).add(a[1]);
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++){
            for (int j = i+1; j < points.length; j++) {
                int[] a = points[i], b = points[j];
                if (a[0] == b[0] || a[1] == b[1]) continue;
                if (map.get(a[0]).contains(b[1]) && map.get(b[0]).contains(a[1])){
                    res = Math.min(res, Math.abs((a[0] - b[0]) * (a[1] - b[1])));
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    private static int mod = 1000000007;

    public int knightDialer(int N) {
        int[][] dirs = new int[][] {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0}, {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};
        int[][] dp = new int[N][10];
        int ans = 0;
        Arrays.fill(dp[0], 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int x : dirs[j]) {
                    dp[i][x] += dp[i-1][j];
                    dp[i][x] %= mod;
                }
            }
        }

        for (int x : dp[N-1]) {
            ans += x;
            ans %= mod;
        }

        return ans;
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
