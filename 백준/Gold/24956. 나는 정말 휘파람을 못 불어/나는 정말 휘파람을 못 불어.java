import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static String S;
    static long[][] dp; // dp[index][state]: 메모이제이션을 위한 DP 테이블
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = br.readLine();

        // dp 테이블을 -1로 초기화하여 아직 계산되지 않은 상태임을 표시합니다.
        dp = new long[N][5];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        long result = solve(0, 0);
        System.out.println(result % 1000000007);
    }

    static long solve(int index, int state) {
        if (index == N) {
            // state가 4이면 'WHEE'가 완성
            return state == 4 ? 1 : 0;
        }

        if (dp[index][state] != -1) {
            return dp[index][state];
        }

        // 1. 현재 index의 문자를 사용하지 않는 경우(기본값)
        long count = solve(index + 1, state);

        // 2. 현재 index의 문자를 사용하는 경우
        char currentChar = S.charAt(index);

        if (state == 0 && currentChar == 'W') {
            count = (count + solve(index + 1, 1)) % MOD; // 'W'를 찾았으니 다음엔 'H'(state=1)
        } else if (state == 1 && currentChar == 'H') {
            count = (count + solve(index + 1, 2)) % MOD; // 'H'를 찾았으니 다음엔 'E'(state=2)
        } else if (state == 2 && currentChar == 'E') {
            count = (count + solve(index + 1, 3)) % MOD; // 첫 'E'를 찾았으니 다음엔 두 번째 'E'(state=3)
        } else if (state == 3 && currentChar == 'E') {
            count = (count + solve(index + 1, 4)) % MOD; // 두 번째 'E'를 찾아 'WHEE'가 완성(state=4)
        } else if (state == 4 && currentChar == 'E') {
            // WHEE가 완성되어도 E추가되도됨
            // 유효한 경우이니 state=4를 유지하며 경우의 수 추가
            count = (count + solve(index + 1, 4)) % MOD;
        }
        
        // 계산 결과를 DP 테이블에 저장하고 반환합니다.
        return dp[index][state] = count;
    }
}