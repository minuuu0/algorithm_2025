import java.util.*;
import java.io.*;

class Main {

    static class Pillar implements Comparable<Pillar>{
        int idx;
        int height;
        
        Pillar(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }

        public int compareTo(Pillar o) {
            return this.idx - o.idx;
        }
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Pillar[] pillars = new Pillar[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            pillars[i] = new Pillar(idx, height);
        }

        Arrays.sort(pillars);

        int highestIdx = 0;
        int highestHeight = 0;

        for (int i = 0; i < N; i++) {
            if (pillars[i].height > highestHeight) {
                highestHeight = pillars[i].height;
                highestIdx = i;
            }
        }
        
        int result = 0;
        
        // 왼쪽부터 가장 높은 곳
        int currentMaxH = 0;
        for (int i = 0; i < highestIdx; i++) {

            if (pillars[i].height > currentMaxH) {
                currentMaxH = pillars[i].height;
            }
            result += currentMaxH * (pillars[i + 1].idx - pillars[i].idx);
        }
        
        // 오른쪽부터
        currentMaxH = 0;
        for (int i = N - 1; i > highestIdx; i--) {
            if (pillars[i].height > currentMaxH) {
                currentMaxH = pillars[i].height;
            }
            result += currentMaxH * (pillars[i].idx - pillars[i - 1].idx);
        }

        result += highestHeight;

        System.out.println(result);
    }
}