import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int min = Integer.MAX_VALUE;
    static int[] dp;

    static int recur(int num) {
        if (num == 1) {
            return 0;
        }
        
        if (dp[num] != -1) return dp[num];
            
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        if (num % 3 == 0) {
            a = recur(num / 3) + 1;
        }
        if (num % 2 == 0) {
            b = recur(num / 2) + 1;
        }
        int c = recur(num - 1) + 1;

        dp[num] = Math.min(a, Math.min(b, c));
        return dp[num];
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        System.out.println(recur(N));
    }
}