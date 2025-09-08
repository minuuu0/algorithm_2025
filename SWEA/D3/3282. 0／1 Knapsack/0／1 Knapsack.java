import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {

    static int N, K;
    static int[][] arr;
    static int[][] dp;

    // 물건들의 가치의 최대값을 구하고 있음
    // 그렇다면 idx마다 얻을 수 있는 최대 가치를 구하도록 한다면?
    static int recur(int idx, int weight) {
        if (weight > K) return -1000000000;
        
        if (idx == N) {
            return 0;
        }

        if (dp[idx][weight] != -1) return dp[idx][weight];
        
        int a = recur(idx + 1, weight + arr[idx][0]) + arr[idx][1];
        int b = recur(idx + 1, weight);
        dp[idx][weight] = Math.max(a, b);
        return dp[idx][weight];
    }

    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N][2];
            dp = new int[N][K + 1];
    
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= K; j++) {
                    dp[i][j] = -1;
                }
            }
    
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 2; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
    
            System.out.println("#" + tc + " " + recur(0, 0));
        }
    }
}