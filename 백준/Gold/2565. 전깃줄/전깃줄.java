import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] wires;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        wires = new int[N][2];
        dp = new int[N][N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            wires[i][0] = Integer.parseInt(st.nextToken());
            wires[i][1] = Integer.parseInt(st.nextToken());
            
            Arrays.fill(dp[i], -1);
        }

        Arrays.sort(wires, (a, b) -> Integer.compare(a[0], b[0]));

        int maxNonCrossing = recur(0, 0);
        System.out.println(N - maxNonCrossing);
    }

    private static int recur(int idx, int prev) {
        if (idx == N) {
            return 0;
        }

        if (dp[idx][prev] != -1) {
            return dp[idx][prev];
        }

        // 이전에 선택된 전깃줄의 B 전봇대 위치 가져오기
        // prev가 0이면, 아직 아무것도 선택되지 않았으므로 비교 기준을 0으로
        int prevB = (prev == 0) ? 0 : wires[prev - 1][1];

        // 전깃줄을 선택하지 않거나
        int skip = recur(idx + 1, prev);

        // 전깃줄을 선택하거나
        int take = 0;
        // 현재 전깃줄의 B 위치가 이전 전깃줄의 B 위치보다 커야만 선택
        if (wires[idx][1] > prevB) {
            take = 1 + recur(idx + 1, idx + 1);
        }

        dp[idx][prev] = Math.max(take, skip);
        return dp[idx][prev];
    }
}