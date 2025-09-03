import java.util.*;
import java.lang.*;
import java.io.*;

class Solution {

    static int[] price;
    static int[] arr;
    static int[] dp;

    static int recur(int idx) {       
        if (idx >= 12) { // 12월 이후에 벌수 있는 돈은 0원
            return 0;
        }

        if (dp[idx] != - 1) return dp[idx];

        // 수영장을 이용하지 않는 경우
        if (arr[idx] == 0) {
            return dp[idx] = recur(idx + 1);
        }
        // 1일 이용권으로 이용하는경우
        int a = recur(idx + 1) + price[0] * arr[idx];
        // 1월
        int b = recur(idx + 1) + price[1];
        // 3월
        int c = recur(idx + 3) + price[2];
        dp[idx] = Math.min(a, Math.min(b, c));
        return dp[idx];
        
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            price = new int[4];
            arr = new int[12];
            dp = new int[12];

            Arrays.fill(dp, -1);
            
            for (int i = 0; i < 4; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int result = recur(0);
            result = Math.min(result, price[3]);
            
            System.out.println("#" + tc + " " + result);
        }
    }
}