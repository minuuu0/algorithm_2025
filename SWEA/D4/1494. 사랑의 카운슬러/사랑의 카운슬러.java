import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static Node[] points; // 지렁이 담을 배열 
    static boolean[] visited;
    static long min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            
            // Node 객체를 담을 배열 생성
            points = new Node[N];
            visited = new boolean[N];
            min = Long.MAX_VALUE;

            long totalX = 0;
            long totalY = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                
                points[i] = new Node(x, y);
                
                totalX += points[i].x;
                totalY += points[i].y;
            }

            recur(0, 0, totalX, totalY);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.print(sb);
    }

    private static void recur(int idx, int cnt, long totalX, long totalY) {
        if (idx >= N) return;
        
        if (cnt == N / 2) {
            long selectedX = 0;
            long selectedY = 0;

            // 선택된 점(도착점)들의 좌표 합 계산
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    selectedX += points[i].x;
                    selectedY += points[i].y;
                }
            }

            // 최종 벡터 합 계산
            long vectorX = 2 * selectedX - totalX;
            long vectorY = 2 * selectedY - totalY;
            
            // 벡터 합의 크기(제곱)를 계산하고 최솟값 갱신
            long temp = vectorX * vectorX + vectorY * vectorY;
            min = Math.min(min, temp);
            return;
        }

        // 1. 현재 idx점을 도착점으로 선택하는 경우
        visited[idx] = true;
        recur(idx + 1, cnt + 1, totalX, totalY);

        // 2. 현재 idx점을 선택하지 않는경우
        visited[idx] = false;
        recur(idx + 1, cnt, totalX, totalY);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}