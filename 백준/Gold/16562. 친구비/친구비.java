import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int N, M, K;
    static int[] arr;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int sum;
    static int min;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && arr[i] <= K) {
                min = arr[i];
                dfs(i);
                K -= min;
                sum += min;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                System.out.println("Oh no");
                return;
            }
        }
        System.out.println(sum);
    }

    static void dfs(int current) {
        visited[current] = true;
        min = Math.min(min, arr[current]);
        for (int next : graph[current]) {
            if (visited[next]) continue;
            dfs(next);
        }
    }
}