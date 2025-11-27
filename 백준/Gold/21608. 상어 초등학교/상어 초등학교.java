import java.util.*;
import java.io.*;

class Main {

    static int N;
    static int[][] board;
    static int[][] preferences; // 학생 별 좋아하는 친구 목록 (2D 배열)
    
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        board = new int[N][N];
        preferences = new int[N * N + 1][4];
        
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            
            for (int j = 0; j < 4; j++) {
                preferences[student][j] = Integer.parseInt(st.nextToken());
            }

            findSeat(student);
        }

        System.out.println(getTotalSatisfaction());
    }

    static void findSeat(int student) {
        int bestR = -1, bestC = -1;
        int maxLike = -1, maxEmpty = -1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] != 0) continue; 

                int currentLike = 0;
                int currentEmpty = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = r + dx[d];
                    int ny = c + dy[d];

                    if (isOOB(nx, ny)) continue;

                    // "인접한 칸에 친구 확인
                    int neighbor = board[nx][ny];
                    for (int friend : preferences[student]) {
                        if (friend == neighbor) {
                            currentLike++;
                            break;
                        }
                    }
                    
                    // 빈 칸인지 확인
                    if (board[nx][ny] == 0) currentEmpty++;
                }

                if (currentLike > maxLike) {
                    maxLike = currentLike;
                    maxEmpty = currentEmpty;
                    bestR = r; bestC = c;
                } else if (currentLike == maxLike) {
                    if (currentEmpty > maxEmpty) {
                        maxEmpty = currentEmpty;
                        bestR = r; bestC = c;
                    }
                }
            }
        }
        board[bestR][bestC] = student;
    }

    static int getTotalSatisfaction() {
        int sum = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int student = board[r][c];
                int likeCount = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = r + dx[d];
                    int ny = c + dy[d];

                    if (isOOB(nx, ny)) continue;

                    int neighbor = board[nx][ny];
                    for (int friend : preferences[student]) {
                        if (friend == neighbor) {
                            likeCount++;
                            break;
                        }
                    }
                }

                if (likeCount == 1) sum += 1;
                else if (likeCount == 2) sum += 10;
                else if (likeCount == 3) sum += 100;
                else if (likeCount == 4) sum += 1000;
            }
        }
        return sum;
    }

    static boolean isOOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}