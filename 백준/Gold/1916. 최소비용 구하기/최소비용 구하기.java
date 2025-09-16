import java.util.*;
import java.io.*;

class Main {

    static int N, M;
    static List<Edge>[] graph;
    static int[] dist;
    static final int INF = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N + 1];

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        System.out.println(dist[end]);
    }

    static void dijkstra(int start) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(start, 0));
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int curIdx = current.idx;
            int curCost = current.dist;

            if (curCost > dist[curIdx]) continue;

            for (Edge next : graph[curIdx]) {
                if (dist[next.to] > dist[curIdx] + next.cost) {
                    dist[next.to] = dist[curIdx] + next.cost;
                    pq.offer(new State(next.to, dist[next.to]));
                }
            }
        }
    }

    static class State implements Comparable<State>{
        int idx;
        int dist;

        State (int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        public int compareTo(State o) {
            return this.dist - o.dist;
        }
    }

    static class Edge {
        int to;
        int cost;
        Edge (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}