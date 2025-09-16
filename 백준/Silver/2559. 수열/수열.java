import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] temps = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            temps[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 첫 번째 윈도우(0부터 K-1까지)의 합을 계산
        int windowSum = 0;
        for (int i = 0; i < K; i++) {
            windowSum += temps[i];
        }

        // 최댓값을 첫 윈도우의 합으로 초기화
        int maxSum = windowSum;

        // 2. 윈도우를 한 칸씩 오른쪽으로 이동하며 계산
        // i는 새로 윈도우에 추가될 원소의 인덱스
        for (int i = K; i < N; i++) {
            // 새로운 원소는 더하고, 맨 왼쪽 원소는 뺀다
            windowSum += temps[i] - temps[i - K];
            
            // 현재 윈도우의 합과 기존 최댓값을 비교하여 갱신
            if (windowSum > maxSum) {
                maxSum = windowSum;
            }
        }

        // 3. 최종 최댓값 출력
        System.out.println(maxSum);
    }
}