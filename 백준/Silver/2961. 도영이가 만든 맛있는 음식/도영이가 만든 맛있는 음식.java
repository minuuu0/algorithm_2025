import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int[][] arr;
    static int n;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        recur(0, 1, 0);

        System.out.println(min);
    }

    static void recur(int idx, int sour, int bitter) {
        
        if (idx == n) {
            if (bitter != 0) {
                min = Math.min(min, Math.abs(sour - bitter));
            }
            return;
        }

        // 현재 재료를 선택한 경우
        recur(idx + 1, sour * arr[idx][0], bitter + arr[idx][1]);
        // 현재 재료를 선택하지 않은경우
        recur(idx + 1, sour, bitter);
    }
}