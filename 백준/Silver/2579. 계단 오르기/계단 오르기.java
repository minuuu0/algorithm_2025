import java.util.*;
import java.lang.*;
import java.io.*;

// 계단을 오를 때 규칙
// 1. 한번에 한 계단 혹은 두 계단씩 오를 수 있음
// 2. 연속된 3개의 계단을 모두 밟으면 안됨
// 3. 마지막 계단은 반드시 밟아야함.

class Main {

    static int N;
    static int[] arr;
    static int[][] dp;

    static int recur(int idx, int step) {
        if (idx >= N) {
            return -1000000000;
        }
        
        if (idx == N - 1) {
            return arr[idx];
        }

        if (dp[idx][step] != -1) return dp[idx][step];
        
        // 한 계단을 오르는 경우
        int a = Integer.MIN_VALUE;
        if (step < 2) {
            a = recur(idx + 1, step + 1) + arr[idx];
        }
        
        // 두 계단을 오르는 경우 -> 연속된 스텝 초기화
        int b = recur(idx + 2, 1) + arr[idx];
        dp[idx][step] = Math.max(a, b);
        return dp[idx][step];
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N][3];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            Arrays.fill(dp[i], -1);
        }

        // 첫번재 밟고
        int a = recur(0, 1);
        // 두번째 밟고
        int b = recur(1, 1);
        System.out.println(Math.max(a, b));
    }
}