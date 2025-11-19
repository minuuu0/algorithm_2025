import java.util.*;
import java.io.*;

class Main {

    static int[][] board = new int[19][19];
    static int type = -1; 
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {

                if (board[i][j] != 0) {
                    if (checkWin(i, j, board[i][j])) {
                        System.out.println(board[i][j]);
                        if (type == 3) {
                            System.out.println((i + 4 + 1) + " " + (j - 4 + 1));
                        } else {
                            System.out.println((i + 1) + " " + (j + 1)); 
                        }
                        return;
                    }
                }
                
            }
        }
        System.out.println(0);
    }
    
    static boolean checkWin(int x, int y, int color) {
        
        if (y + 4 < 19) {
            if (y - 1 < 0 || board[x][y - 1] != color) {
                int cnt = 1;
                for (int k = 1; k <= 4; k++) {
                    if (board[x][y + k] == color) cnt++;
                    else break;
                }
                if (cnt == 5) {
                    if (y + 5 >= 19 || board[x][y + 5] != color) {
                        type = 0;
                        return true;
                    }
                }
            }
        }


        if (x + 4 < 19) {
            if (x - 1 < 0 || board[x - 1][y] != color) {
                int cnt = 1;
                for (int k = 1; k <= 4; k++) {
                    if (board[x + k][y] == color) cnt++;
                    else break;
                }
                if (cnt == 5) {
                    if (x + 5 >= 19 || board[x + 5][y] != color) {
                        type = 1;
                        return true;
                    }
                }
            }
        }

        if (x + 4 < 19 && y + 4 < 19) {
            if (x - 1 < 0 || y - 1 < 0 || board[x - 1][y - 1] != color) {
                int cnt = 1;
                for (int k = 1; k <= 4; k++) {
                    if (board[x + k][y + k] == color) cnt++;
                    else break;
                }
                if (cnt == 5) {
                    if (x + 5 >= 19 || y + 5 >= 19 || board[x + 5][y + 5] != color) {
                        type = 2;
                        return true;
                    }
                }
            }
        }

        if (x + 4 < 19 && y - 4 >= 0) {
            if (x - 1 < 0 || y + 1 >= 19 || board[x - 1][y + 1] != color) {
                int cnt = 1;
                for (int k = 1; k <= 4; k++) {
                    if (board[x + k][y - k] == color) cnt++;
                    else break;
                }
                if (cnt == 5) {
                    if (x + 5 >= 19 || y - 5 < 0 || board[x + 5][y - 5] != color) {
                        type = 3;
                        return true;
                    }
                }
            }
        }

        return false;
    }
}