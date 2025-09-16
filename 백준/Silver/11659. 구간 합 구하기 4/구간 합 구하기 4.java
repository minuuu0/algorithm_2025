import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] prefix = new int[N + 1];
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = prefix[b] - prefix[a - 1];
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}