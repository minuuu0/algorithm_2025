import java.util.*;
import java.io.*;

class Solution {
    static int n, result;
    static int[][] arr = new int[4001][4001];
    static Queue<Atom> atom;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            n = Integer.parseInt(br.readLine());
            atom = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2; // y를 가로로 
                int x = 4000 - (Integer.parseInt(st.nextToken()) + 1000) * 2; // x를 세로로
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                atom.add(new Atom(x, y, dir, k));
                arr[x][y] = k;
            }

            result = 0;
            bfs();
            System.out.println("#" + tc + " " + result);
        }
    }

    static void bfs() {
        while(!atom.isEmpty()) {
            // 1. 큐에서 원자를 하나씩 꺼내어 처리
            Atom a = atom.poll();

            // 2. 충돌 감지
            if (arr[a.x][a.y] != a.k) {
                result += arr[a.x][a.y]; // 현재 위치의 모든 에너지를 답에 추가
                arr[a.x][a.y] = 0; // 충돌 원자 소멸 
                continue;
            }
            int nx = a.x + dx[a.d];
            int ny = a.y + dy[a.d];

            if (nx < 0 || ny < 0 || nx > 4000 || ny > 4000) {
                arr[a.x][a.y] = 0;
                continue;
            }

            // 3. 원자 이동처리 
            if (arr[nx][ny] == 0) { // 빈공간이면 이동 후 큐에 다시 추가 
                arr[nx][ny] = a.k;
                atom.add(new Atom(nx, ny, a.d, a.k));
            } else {
                arr[nx][ny] += a.k; // 이미 원자가 있으면 에너지 합산 (추후 충돌 처리)
            }

            // 4. 원자 이동 후 위치 비우기 
            arr[a.x][a.y] = 0;
            
        }
    }

    static class Atom {
        int x, y, d, k;
        Atom(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this. k = k;
        }
    }
}