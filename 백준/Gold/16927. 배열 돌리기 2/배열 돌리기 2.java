import java.util.*;
import java.io.*;

class Main {

    static int N, M, R;
    static int[][] arr;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 필요한 만큼만 돌림
        rotateLayers();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void rotateLayers() {
        int layers = Math.min(N, M) / 2;
        
        for (int i = 0; i < layers; i++) {
            int n = N - 2 * i;
            int m = M - 2 * i;
            
            int perimeter = 2 * (n + m) - 4;
            
            int rotateCount = R % perimeter;
            
            for (int k = 0; k < rotateCount; k++) {
                rotateOne(i, n, m);
            }
        }
    }


    static void rotateOne(int start, int h, int w) {

        int rStart = start;
        int rEnd = start + h - 1;
        int cStart = start;
        int cEnd = start + w - 1;

        int temp = arr[rStart][cStart];

        for (int j = cStart; j < cEnd; j++) {
            arr[rStart][j] = arr[rStart][j + 1];
        }

        for (int i = rStart; i < rEnd; i++) {
            arr[i][cEnd] = arr[i + 1][cEnd];
        }

        for (int j = cEnd; j > cStart; j--) {
            arr[rEnd][j] = arr[rEnd][j - 1];
        }

        for (int i = rEnd; i > rStart; i--) {
            arr[i][cStart] = arr[i - 1][cStart];
        }

        arr[rStart + 1][cStart] = temp;
    }
}