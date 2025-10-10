import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static int[] mix;
    static int[] move;

    static boolean[] visited;

    static int dfs(int start) {
        int current = start;
        int cycleLength = 0;

        while (!visited[current]) {
            visited[current] = true;
            current = move[current];
            cycleLength++;
        }

        return cycleLength;
    }

    public static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int getLcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }

    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        mix = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            mix[i] = Integer.parseInt(st.nextToken());
        }

        move = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            move[mix[i]] = i; // mix[i] 위치의 카드는 i위치로 이동
        }

        visited = new boolean[N + 1];
        int lcm = 1;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                // 아직 방문 안 한 위치에서 사이클 탐색 

                int cycleLength = dfs(i);
                if (cycleLength > 0) {
                    lcm = getLcm(lcm, cycleLength);
                }
            }
        }

        System.out.println(lcm);
    }
    
}