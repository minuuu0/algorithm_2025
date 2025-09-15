import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Integer>[] graph;
    static boolean[] visited; // DFS 탐색 시 방문 여부 확인용

    /**
     * currentNode를 루트로 하는 서브트리의 최소 얼리 어답터 수를 계산하여 반환합니다.
     * @param currentNode 현재 노드
     * @return int[]{ 
     * [0]: currentNode가 일반인일 때의 최소 비용,
     * [1]: currentNode가 얼리 어답터일 때의 최소 비용 
     * }
     */
    static int[] solve(int currentNode) {
        visited[currentNode] = true;

        // 1. 자신의 비용을 먼저 계산합니다.
        // 내가 일반인일 때의 비용은 0, 얼리 어답터일 때는 1(나 자신)로 시작합니다.
        int[] myCosts = {0, 1};

        // 2. 자식들의 비용을 재귀적으로 얻어와 내 비용에 더합니다.
        for (int child : graph[currentNode]) {
            if (!visited[child]) {
                // 자식 서브트리의 결과를 재귀 호출로 받아옵니다.
                int[] childCosts = solve(child);

                // 내가 '일반인'이라면(myCosts[0]), 자식은 '반드시 얼리 어답터'여야 합니다.
                myCosts[0] += childCosts[1];

                // 내가 '얼리 어답터'라면(myCosts[1]), 자식은 둘 중 더 저렴한 쪽을 선택합니다.
                myCosts[1] += Math.min(childCosts[0], childCosts[1]);
            }
        }
        
        // 계산이 완료된 나의 두 가지 경우의 비용을 부모에게 반환합니다.
        return myCosts;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 양방향 그래프로 간선 정보 저장
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        // 1번 노드를 임의의 루트로 설정하고 탐색 시작
        int[] finalResult = solve(1);

        // 최종 결과는 루트가 일반인일 때와 얼리 어답터일 때 중 더 작은 값입니다.
        System.out.println(Math.min(finalResult[0], finalResult[1]));
    }
}