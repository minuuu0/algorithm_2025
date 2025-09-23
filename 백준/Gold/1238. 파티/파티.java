import java.util.*;
import java.io.*;

class Main {

    static int N, M, X;
    static List<Edge>[] graph, reverseGraph;
    static int INF = Integer.MAX_VALUE;

    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class State implements Comparable<State> {
        int idx;
        int dist;

        State(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(State o) {
            return this.dist - o.dist;
        }
    }

    static int[] dijkstra(int start, List<Edge>[] graphToSearch) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State current = pq.poll();

            if (dist[current.idx] != current.dist) {
                continue;
            }

            for (Edge next : graphToSearch[current.idx]) {
                if (dist[next.to] > dist[current.idx] + next.cost) {
                    dist[next.to] = dist[current.idx] + next.cost;
                    pq.offer(new State(next.to, dist[next.to]));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        reverseGraph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 정방향
            graph[from].add(new Edge(to, cost));
            // 역방향
            reverseGraph[to].add(new Edge(from, cost));
        }

        // 1. X에서 각 마을로 돌아오는 최단 시간
        int[] distFromX = dijkstra(X, graph);

        // 2. 각 마을에서 X로 가는 최단 시간
        int[] distToX = dijkstra(X, reverseGraph);

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            int roundTripTime = distToX[i] + distFromX[i];
            if (roundTripTime > maxTime) {
                maxTime = roundTripTime;
            }
        }

        System.out.println(maxTime);
    }
}