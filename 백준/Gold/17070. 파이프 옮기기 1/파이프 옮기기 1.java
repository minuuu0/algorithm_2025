import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        System.out.println(recur(0, 1, 0));
    }


    static int recur(int x, int y, int dir) {

        if (x == N - 1 && y == N - 1) {
            return 1;
        }

        if (dp[x][y][dir] != -1) return dp[x][y][dir];

        int count = 0;
        if (dir == 0 || dir == 2) {
            int ny = y + 1;
            if (ny < N && map[x][ny] == 0) {
                count += recur(x, ny, 0);
            }
        }

        // 2. 세로로 이동
        int b = Integer.MIN_VALUE;
        if (dir == 1 || dir == 2) {
            int nx = x + 1;
            if (nx < N && map[nx][y] == 0) {
                count += recur(nx, y, 1);
            }
        }

        // 3. 대각선으로 이동
        int c = Integer.MIN_VALUE;
        int nx = x + 1;
        int ny = y + 1;
        if (nx < N && ny < N && map[x][ny] == 0 && map[nx][y] == 0 && map[nx][ny] == 0) {
            count += recur(nx, ny, 2);
        }
        dp[x][y][dir] = count;
        return dp[x][y][dir];
    }
}