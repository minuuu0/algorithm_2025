import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][M + 1];
        int[][] prefix = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                prefix[i][j] = arr[i][j]
                                + prefix[i - 1][j]
                                + prefix[i][j - 1]
                                - prefix[i - 1][j - 1];
            }
        }

        long maxSum = Long.MIN_VALUE;

        // r1, c1: 왼쪽 위 꼭짓점
        for (int r1 = 1; r1 <= N; r1++) {
            for (int c1 = 1; c1 <= M; c1++) {
                // r2, c2: 오른쪽 아래 꼭짓점
                for (int r2 = r1; r2 <= N; r2++) {
                    for (int c2 = c1; c2 <= M; c2++) {
                        long currentSum = prefix[r2][c2]
                                        - prefix[r1 - 1][c2]
                                        - prefix[r2][c1 - 1]
                                        + prefix[r1 - 1][c1 - 1];

                        if (currentSum > maxSum) {
                            maxSum = currentSum;
                        }
                    }
                }
            }
        }

        System.out.println(maxSum);
    }
}