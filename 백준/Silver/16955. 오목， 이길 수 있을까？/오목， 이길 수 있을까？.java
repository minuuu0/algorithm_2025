import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[][] arr = new char[10][10];
        
        for (int i = 0; i < 10; i++) {
            String s = br.readLine();
            for (int j = 0; j < 10; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        // 10x10 보드를 순회하며 '.' (빈 칸)을 찾음
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // 현재 칸이 빈 칸이 아니면 건너뜀
                if (arr[i][j] != '.') continue;
                
                boolean canWin = false; // 이기면 true가 됨
                int r = i;
                int c = j;

                // 1. 가로(horizontal) 검사
                int cnt = 0;
                // 왼쪽
                for (int k = c - 1; k >= 0 && arr[r][k] == 'X'; k--) cnt++;
                // 오른쪽
                for (int k = c + 1; k < 10 && arr[r][k] == 'X'; k++) cnt++;
                if (cnt >= 4) canWin = true;

                // 2. 세로(vertical) 검사
                if (!canWin) {
                    cnt = 0;
                    // 위쪽
                    for (int k = r - 1; k >= 0 && arr[k][c] == 'X'; k--) cnt++;
                    // 아래쪽
                    for (int k = r + 1; k < 10 && arr[k][c] == 'X'; k++) cnt++;
                    if (cnt >= 4) canWin = true;
                }

                // 3. '/' 대각선 검사
                if (!canWin) {
                    cnt = 0;
                    // 왼쪽 아래
                    for (int k = r + 1, l = c - 1; k < 10 && l >= 0 && arr[k][l] == 'X'; k++, l--) cnt++;
                    // 오른쪽 위
                    for (int k = r - 1, l = c + 1; k >= 0 && l < 10 && arr[k][l] == 'X'; k--, l++) cnt++;
                    if (cnt >= 4) canWin = true;
                }

                // 4. '\' 대각선 검사
                if (!canWin) {
                    cnt = 0;
                    // 오른쪽 아래
                    for (int k = r + 1, l = c + 1; k < 10 && l < 10 && arr[k][l] == 'X'; k++, l++) cnt++;
                    // 왼쪽 위
                    for (int k = r - 1, l = c - 1; k >= 0 && l >= 0 && arr[k][l] == 'X'; k--, l--) cnt++;
                    if (cnt >= 4) canWin = true;
                }
                

                // 만약 (i, j)에 돌을 놓았을 때 이길 수 있다면
                if (canWin) {
                    System.out.println(1);
                    return;
                }
            }
        }

        System.out.println(0);
    }
}