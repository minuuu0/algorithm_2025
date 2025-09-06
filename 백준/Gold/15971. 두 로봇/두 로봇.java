import java.io.*;
import java.util.*;

public class Main{

    static boolean[] visited;
    static List<Node>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int robot1 = Integer.parseInt(st.nextToken());
        int robot2 = Integer.parseInt(st.nextToken());

        if (n == 1 || robot1 == robot2) {
            System.out.println(0);
            return;
        }

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, cost));
            adj[v].add(new Node(u, cost));
        }

        visited = new boolean[n + 1];
        visited[robot1] = true;
        dfs(robot1, robot2, 0, 0);
    }

    private static void dfs(int current, int target, int totalCost, int maxCost) {
        if (current == target) {
            System.out.println(totalCost - maxCost);
            System.exit(0); // 유일한 경로를 찾았으므로 더 탐색할 필요 없음
        }

        for (Node next : adj[current]) {
            if (!visited[next.to]) {
                visited[next.to] = true; // 방문 처리
                // 다음 노드로 재귀 호출
                dfs(next.to, target, totalCost + next.cost, Math.max(maxCost, next.cost));
            }
        }
    }

    static class Node {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
