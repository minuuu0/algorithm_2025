import java.io.*;
import java.util.*;

public class Main {
    static int[][] points = new int[4][2];
    static boolean[] visited = new boolean[4];
    static int[] order = new int[3];
    static double minDistance = Double.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        recur(0);

        System.out.println((int) minDistance);
    }

    private static void recur(int depth) {
        if (depth == 3) {
            calculateDistance();
            return;
        }

        for (int i = 1; i < 4; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i;
                recur(depth + 1);
                visited[i] = false;
            }
        }
    }

    private static void calculateDistance() {
        double currentDistance = 0;
        int[] lastPoint = points[0];

        for (int i = 0; i < 3; i++) {
            int[] nextPoint = points[order[i]];
            currentDistance += getDistance(lastPoint, nextPoint);
            lastPoint = nextPoint;
        }

        minDistance = Math.min(minDistance, currentDistance);
    }

    private static double getDistance(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }
}