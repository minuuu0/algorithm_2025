import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행 크기
        M = Integer.parseInt(st.nextToken()); // 열 크기
        R = Integer.parseInt(st.nextToken()); // 연산의 수

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int op = Integer.parseInt(st.nextToken());
            operate(op);
        }

        printMap();
    }

    static void operate(int op) {
        switch (op) {
            case 1:
                op1(); // 상하 반전
                break;
            case 2:
                op2(); // 좌우 반전
                break;
            case 3:
                op3(); // 오른쪽 90도 회전
                break;
            case 4:
                op4(); // 왼쪽 90도 회전
                break;
            case 5:
                op5(); // 4분할 시계방향 이동
                break;
            case 6:
                op6(); // 4분할 반시계방향 이동
                break;
        }
    }

    // 1번 연산: 상하 반전
    static void op1() {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[N - 1 - i][j] = map[i][j];
            }
        }
        map = temp;
    }

    // 2번 연산: 좌우 반전
    static void op2() {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][M - 1 - j] = map[i][j];
            }
        }
        map = temp;
    }

    // 3번 연산: 오른쪽으로 90도 회전
    static void op3() {
        int[][] temp = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[j][N - 1 - i] = map[i][j];
            }
        }
        // N, M 교체
        int swap = N;
        N = M;
        M = swap;
        map = temp;
    }

    // 4번 연산: 왼쪽으로 90도 회전
    static void op4() {
        int[][] temp = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[M - 1 - j][i] = map[i][j];
            }
        }
        // N, M
        int swap = N;
        N = M;
        M = swap;
        map = temp;
    }

    // 5번 연산: 4개의 부분 배열을 시계 방향으로 이동
    static void op5() {
        int[][] temp = new int[N][M];
        int nHalf = N / 2;
        int mHalf = M / 2;

        for (int i = 0; i < nHalf; i++) {
            for (int j = 0; j < mHalf; j++) {
                // 1번 그룹 -> 2번 위치
                temp[i][j + mHalf] = map[i][j];
                // 2번 그룹 -> 3번 위치
                temp[i + nHalf][j + mHalf] = map[i][j + mHalf];
                // 3번 그룹 -> 4번 위치
                temp[i + nHalf][j] = map[i + nHalf][j + mHalf];
                // 4번 그룹 -> 1번 위치
                temp[i][j] = map[i + nHalf][j];
            }
        }
        map = temp;
    }

    // 6번 연산: 4개의 부분 배열을 반시계 방향으로 이동
    // 1 -> 4, 4 -> 3, 3 -> 2, 2 -> 1
    static void op6() {
        int[][] temp = new int[N][M];
        int nHalf = N / 2;
        int mHalf = M / 2;

        for (int i = 0; i < nHalf; i++) {
            for (int j = 0; j < mHalf; j++) {
                // 1번 그룹 -> 4번 위치
                temp[i + nHalf][j] = map[i][j];
                // 4번 그룹 -> 3번 위치
                temp[i + nHalf][j + mHalf] = map[i + nHalf][j];
                // 3번 그룹 -> 2번 위치
                temp[i][j + mHalf] = map[i + nHalf][j + mHalf];
                // 2번 그룹 -> 1번 위치
                temp[i][j] = map[i][j + mHalf];
            }
        }
        map = temp;
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}