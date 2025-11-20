import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static int[][] arr;
    static int currentX, currentY, currentDir; // 0: 북, 1: 동 2: 남 3: 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        currentX = Integer.parseInt(st.nextToken());
        currentY = Integer.parseInt(st.nextToken());
        currentDir = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (true) {
            // 1. 현재 칸이 아직 청소되지 않은 경우 현재 칸을 청소
            if (arr[currentX][currentY] == 0) {
                arr[currentX][currentY] = 2;
                cnt++;
            }

            // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            if (!checkAround()) {
                int nx = currentX - dx[currentDir];
                int ny = currentY - dy[currentDir];

                if (arr[nx][ny] == 1) {
                    // 벽이라 후진 할 수 없으면 작동 멈춤
                    break;
                } else {
                    // 후진할 수 있으면 후진하고 1번으로 돌아감 
                    currentX = nx;
                    currentY = ny;
                    continue;
                }
                
            } else {
                // 3. 현재 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                currentDir = (currentDir + 3) % 4; // 반시계 방향으로 90도 회전
                int nx = currentX + dx[currentDir];
                int ny = currentY + dy[currentDir];
                if (arr[nx][ny] == 0) {
                    currentX = nx;
                    currentY = ny;
                }
            }
            
        }
        System.out.println(cnt);
        
    }

    static boolean checkAround() {
        for (int d = 0; d < 4; d++) {
            int nx = currentX + dx[d];
            int ny = currentY + dy[d];
            if (arr[nx][ny] == 0) {
                return true;
            }
        }
        return false;
    }
}