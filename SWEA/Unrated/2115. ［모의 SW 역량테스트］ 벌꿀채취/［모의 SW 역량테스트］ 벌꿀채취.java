import java.util.*;
import java.lang.*;
import java.io.*;
 
// 완탐으로도 뚫리지만, 일꾼1은 고정되어있고 일꾼2가 움직일 때 일꾼 1을 다시 계산한다. 이를 DP로 개선하자.
// n <= 10 ; m <= 5 ; 10 <= C <= 30
class Solution {
 
    static int n, m, c;
    static int[][] arr;
    static int max;
    static int[][] dp;
 
    static int calculate(int x, int y) {
        int[] block = new int[m];
        for (int i = 0; i < m; i++) {
            block[i] = arr[x][y + i];
        }
 
        dp = new int[n][c + 1];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return recur(0, 0, block);
    }
 
    static int recur(int idx, int currentSum, int[] block) {
        if (currentSum > c) return -1000000000;
 
        if (idx == m) {
            return 0;
        }
 
        if (dp[idx][currentSum] != -1) {
            return dp[idx][currentSum];
        }
        // 현재 idx 벌통을 선택하지 않은 경우
        int a = recur(idx + 1, currentSum, block);
 
        // 선택한경우
        int honey = block[idx];
        int b = recur(idx + 1, currentSum + honey, block) + honey * honey;
         
        return dp[idx][currentSum] = Math.max(a, b);
    }
     
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            arr = new int[n][n];
             
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            int[][] maxProfit = new int[n][n];
            // 1. 모든 1 x m 구간에 대해 DP를 이용해 최대 이익을 먼저 계산
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n - m; j++) {
                    maxProfit[i][j] = calculate(i, j);
                }
            }
 
            // 첫 번째 일꾼
            int result = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n - m; j++) {
 
                    // 두 번째 일꾼
                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l <= n - m; l++) {
                            if (i == k && j + m > l) continue;
 
                            // 각 일꾼이 선택한 벌통에서 최대 이익 계산
                            int profit1 = maxProfit[i][j];
                            int profit2 = maxProfit[k][l];
 
                            result = Math.max(result, profit1 + profit2);
                        }
                    }
                }
            }
             
            System.out.println("#" + tc + " " + result);
        }
    }
}