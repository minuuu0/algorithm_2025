import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {

    static int N;
    static int[] arr;
    static int[][] dp;

    static int recur(int idx, int prev) {
        if (idx == N + 1) {
            return 0;
        }

        if (dp[idx][prev] != -1) return dp[idx][prev];
        
        // 현재 수를 선택하는 경우
        int a = Integer.MIN_VALUE;
        if (arr[prev] < arr[idx]) {
            a = recur(idx + 1, idx) + 1; 
        }
        // 고르지 않는 경우
        int b = recur(idx + 1, prev);
        dp[idx][prev] = Math.max(a, b);
        return dp[idx][prev];
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N + 1];
            dp = new int[N + 1][N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    dp[i][j] = -1;
                }
            }
            
            System.out.println("#" + tc + " " + recur(1, 0));
            
        }
    }
}