import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        int[] permutation = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            permutation[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] visited = new boolean[n + 1];
        long totalLcm = 1;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                int cycleLength = 0;
                int currentNode = i;
                
                while (!visited[currentNode]) {
                    visited[currentNode] = true;
                    currentNode = permutation[currentNode];
                    cycleLength++;
                }

                if (cycleLength > 0) {
                    totalLcm = lcm(totalLcm, cycleLength);
                }
            }
        }

        System.out.println(totalLcm);
    }

    public static long gcd(long a, long b) {
        while (b > 0) {
            long temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        return Math.abs(a * b) / gcd(a, b);
    }
}