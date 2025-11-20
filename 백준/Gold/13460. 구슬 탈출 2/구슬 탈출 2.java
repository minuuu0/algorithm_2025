import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] board;
    static boolean[][][][] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int startRx = 0, startRy = 0, startBx = 0, startBy = 0;
        visited = new boolean[n][m][n][m];
        board = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    startRx = i;
                    startRy = j;
                } else if (board[i][j] == 'B') {
                    startBx = i;
                    startBy = j;
                }
            }
        }

        int result = bfs(startRx, startRy, startBx, startBy);
        System.out.println(result);
        
    }

    static int bfs(int startRx, int startRy, int startBx, int startBy) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startRx, startRy, startBx, startBy, 0));
        visited[startRx][startRy][startBx][startBy] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.cnt >= 10) {
                return - 1;
            }

            // 4방향으로 기울이기
            for (int i = 0; i < 4; i++) {
                int nrx = current.rx;
                int nry = current.ry;
                int nbx = current.bx;
                int nby = current.by;

                // 빨간 구슬 이동
                while (board[nrx + dx[i]][nry + dy[i]] != '#') {
                    nrx += dx[i];
                    nry += dy[i];
                    if (board[nrx][nry] == 'O') break;
                }

                // 파란 구슬 이동
                while (board[nbx + dx[i]][nby + dy[i]] != '#') {
                    nbx += dx[i];
                    nby += dy[i];
                    if (board[nbx][nby] == 'O') break;
                }

                // 파란 구슬이 빠지면 실패 다음 방향 탐색
                if (board[nbx][nby] == 'O') {
                    continue;
                }

                // 빨간 구슬만 구멍에 빠지면 성공
                if (board[nrx][nry] == 'O') {
                    return current.cnt + 1;
                }

                // 두 구슬이 같은 위치에 있을 경우, 위치 조정
                if (nrx == nbx && nry == nby) {
                    // 이동 거리를 계산하여 더 많이 움직인 구슬을 한 칸 뒤로
                    int red_dist = Math.abs(current.rx - nrx) + Math.abs(current.ry - nry);
                    int blue_dist = Math.abs(current.bx - nbx) + Math.abs(current.by - nby);

                    if (red_dist > blue_dist) { // 빨간 구슬이 더 많이 움직였으면
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else { // 파란 구슬이 더 많이 움직였으면
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.add(new Node(nrx, nry, nbx, nby, current.cnt + 1));
                }
                
            }
        }
        return -1;
    }

    static class Node{
        int rx;
        int ry;
        int bx;
        int by;
        int cnt;
        Node (int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
}