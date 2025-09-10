import java.util.*;
import java.io.*;

class Solution {
    static int N, M;
    static List<Integer>[] graphA;
    static List<Integer>[] graphB;
    static boolean[] visited;
    static int result;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            result = 0;

            graphA = new List[N + 1];
            graphB = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                graphA[i] = new ArrayList<>();
                graphB[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graphA[a].add(b);
                graphB[b].add(a);
            }

            for (int i = 1; i <= N; i++) {
                visited = new boolean[N + 1];
                dfs(i, graphA);
                dfs(i, graphB);
                int temp = 0;
                for (int j = 1; j <= N; j++) {
                    if (visited[j]) {
                        temp++;
                    }
                }
                if (temp == N) result++;
            }
            System.out.println("#" + tc + " " + result);
        }
    }

    static void dfs(int idx, List<Integer>[] graph) {
        visited[idx] = true;

        for (int num : graph[idx]) {
            if (!visited[num]) {
                dfs(num, graph);
            }
        }
    }
}