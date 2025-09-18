import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int n;
    static int[] arr;
    static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        dp = new Integer[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 재귀의 가장 끝에 도달하는 기저 사례(base case)를 미리 초기화
        dp[0] = arr[0];

        int overallMax = dp[0];
        // 모든 지점(1 ~ n-1)을 '끝'으로 하는 최대합을 계산
        for (int i = 1; i < n; i++) {
            overallMax = Math.max(overallMax, recur(i));
        }

        System.out.println(overallMax);
    }

    public static int recur(int i) {
        if (dp[i] != null) {
            return dp[i];
        }

        // i에서 새로 시작하거나(arr[i]), 
        // i-1에서 끝나는 최대합에 이어붙이거나(solve(i-1) + arr[i])
        // 둘 중 더 큰 값을 찾는다.
        int result = Math.max(arr[i], recur(i - 1) + arr[i]);
        
        dp[i] = result;
        
        return result;
    }
}