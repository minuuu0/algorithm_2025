import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int startR = Integer.parseInt(st.nextToken()) - 1;
        int startC = Integer.parseInt(st.nextToken()) - 1;

        char[][] board = new char[10][10];
        boolean[] unsafeRows = new boolean[10];
        boolean[] unsafeCols = new boolean[10];

        for (int i = 0; i < 10; i++) {
            String line = br.readLine();
            for (int j = 0; j < 10; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'o') {
                    unsafeRows[i] = true;
                    unsafeCols[j] = true;
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;

        // 모든 칸을 순회하며 가장 가까운 안전한 칸 찾기
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                // 해당 칸이 안전한 경우
                if (!unsafeRows[r] && !unsafeCols[c]) {
                    int distance = Math.abs(startR - r) + Math.abs(startC - c);
                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                }
            }
        }

        System.out.println(minDistance);
    }
}