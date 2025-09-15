import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<List<Integer>> graph;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        dp = new int[N + 1][2];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int result = Math.min(
            solve(1, false, 0), // 루트가 얼리 어답터가 아닐 때
            solve(1, true, 0)   // 루트가 얼리 어답터일 때
        );

        System.out.println(result);
    }

    public static int solve(int idx, boolean isEarlyAdopter, int parent) {
        int stateIndex = isEarlyAdopter ? 1 : 0;

        if (dp[idx][stateIndex] != -1) {
            return dp[idx][stateIndex];
        }

        int count;
        if (isEarlyAdopter) {
            // 얼리 어답터인 경우
            count = 1; // 자기 자신을 포함
            for (int child : graph.get(idx)) {
                if (child != parent) { // 부모 노드를 제외한 자식 노드들을 탐색
                    // 내가 얼리 어답터이므로 자식은 아무 상태나 가능 (더 저렴한 쪽 선택)
                    count += Math.min(solve(child, false, idx), solve(child, true, idx));
                }
            }
        } else {
            // 얼리 어답터가 아닌 경우
            count = 0; // 자기 자신 포함x
            for (int child : graph.get(idx)) {
                if (child != parent) {
                    // 내가 얼리 어답터가 아니므로 자식은 '반드시' 얼리 어답터여야 함
                    count += solve(child, true, idx);
                }
            }
        }

        return dp[idx][stateIndex] = count;
    }
}