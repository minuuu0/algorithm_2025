import java.util.*;
import java.io.*;

class Main {

    static int M, N;
    static int[][] arr;
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Node> queue = new LinkedList<>();
    static int result = 0;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) queue.offer(new Node(i, j));
            }
        }

        if (checkIsRipe()) {
            System.out.println(0);
            return;
        }

        bfs();

        if (checkIsRipe()) System.out.println(result - 1);
        else System.out.println(-1);
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] != 0) continue;
                arr[nx][ny] = arr[current.x][current.y] + 1;
                result = Math.max(result, arr[nx][ny]);
                queue.offer(new Node(nx, ny));
            }
        }
        
    }

    static boolean checkIsRipe() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) return false;
            }
        }
        return true;
    }
    
}