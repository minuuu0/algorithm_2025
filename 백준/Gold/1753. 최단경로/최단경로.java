import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int end, weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

public class Main {
    static final int INF = 1_000_000_000;
    static int V, E, K;
    static List<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, w));
        }

        dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) sb.append("INF\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.end;

            if (visited[now]) continue;
            visited[now] = true;

            for (Node next : graph[now]) {
                if (dist[next.end] > dist[now] + next.weight) {
                    dist[next.end] = dist[now] + next.weight;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }
    }
}
