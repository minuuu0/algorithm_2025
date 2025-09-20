import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] customers = new int[n][2];
        int[] xCoords = new int[n];
        int[] yCoords = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            customers[i][0] = Integer.parseInt(st.nextToken());
            customers[i][1] = Integer.parseInt(st.nextToken());
            xCoords[i] = customers[i][0];
            yCoords[i] = customers[i][1];
        }

        Arrays.sort(xCoords);
        Arrays.sort(yCoords);

        int medianX = xCoords[n / 2];
        int medianY = yCoords[n / 2];

        long totalDistance = 0;
        for (int i = 0; i < n; i++) {
            totalDistance += Math.abs(customers[i][0] - medianX);
            totalDistance += Math.abs(customers[i][1] - medianY);
        }

        System.out.println(totalDistance);
    }
}