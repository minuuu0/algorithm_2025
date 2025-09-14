import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] mushrooms = new int[10];
        for (int i = 0; i < 10; i++) {
            mushrooms[i] = Integer.parseInt(br.readLine());
        }

        int score = 0; // 현재까지의 누적 점수
        int result = 0; // 100에 가장 가까운 점수

        for (int i = 0; i < 10; i++) {
            score += mushrooms[i];

            if (Math.abs(100 - score) <= Math.abs(100 - result)) {
                if (Math.abs(100 - score) == Math.abs(100 - result)) {
                    result = Math.max(result, score);
                } else {
                    result = score;
                }
            }
        }
        System.out.println(result);
    }
}