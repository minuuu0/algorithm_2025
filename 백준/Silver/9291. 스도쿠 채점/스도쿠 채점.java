import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= testCases; t++) {
            int[][] board = new int[9][9];
            
            if (t > 1) {
                br.readLine();
            }

            for (int i = 0; i < 9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            sb.append("Case ").append(t).append(": ");
            if (isValidSudoku(board)) {
                sb.append("CORRECT\n");
            } else {
                sb.append("INCORRECT\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean isValidSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[10];
            boolean[] colCheck = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int rowNum = board[i][j];
                int colNum = board[j][i];

                if (rowCheck[rowNum]) return false;
                rowCheck[rowNum] = true;

                if (colCheck[colNum]) return false;
                colCheck[colNum] = true;
            }
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                boolean[] boxCheck = new boolean[10];
                for (int row = i; row < i + 3; row++) {
                    for (int col = j; col < j + 3; col++) {
                        int num = board[row][col];
                        if (boxCheck[num]) return false;
                        boxCheck[num] = true;
                    }
                }
            }
        }

        return true;
    }
}