import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[] visited;
    static int max = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[26];
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String alpha = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = alpha.charAt(j) - 'A';
            }
        }
        visited[board[0][0]] = true;
        recur(0, 0, 1);
        System.out.println(max);
    }

    static void recur(int x, int y, int cnt) {
        max = Math.max(max, cnt);
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[board[nx][ny]]) {
                visited[board[nx][ny]] = true;
                recur(nx, ny, cnt + 1);
                visited[board[nx][ny]] = false;
            }
        }
    }
}