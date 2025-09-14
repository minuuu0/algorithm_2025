import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
    static int V, E;
    static ArrayList<Edge>[] edges;
    static int[][] dist;
    static int[] parent;
    static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];
        for(int i = 1; i <= V; i++){
            parent[i] = i;
        }
    
        ArrayList<Edge> edges = new ArrayList<>();
        for(int i = 1; i <= E; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            
            edges.add(new Edge(A, B, C));
        }

        Collections.sort(edges);

        long result = 0;
        int edgeCnt = 0;

        for(Edge edge : edges){
            if(union(edge.from, edge.to)){
                result += edge.cost;
                edgeCnt++;

                if(edgeCnt == V-1) break;
            }
        }
            System.out.println(result);
    }

    static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static boolean union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY){
            parent[rootY] = rootX;
            return true;
        }
        return false;
    }

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.cost, o.cost);
        }
    }

}