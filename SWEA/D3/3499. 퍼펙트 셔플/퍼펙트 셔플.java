import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine()); // 카드의 개수
            String[] cards = new String[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                cards[i] = st.nextToken();
            }

            // 결과를 저장할 배열
            String[] shuffledDeck = new String[N];

            // 덱을 나누는 기준점 계산
            // N이 6이면 (6+1)/2 = 3. 첫 번째 덱은 0, 1, 2 (3개)
            // N이 5이면 (5+1)/2 = 3. 첫 번째 덱은 0, 1, 2 (3개)
            int firstDeckSize = (N + 1) / 2;

            int p1 = 0; // 첫 번째 덱 포인터
            int p2 = firstDeckSize; // 두 번째 덱 포인터
            
            for (int i = 0; i < N; i++) {
                // i가 짝수일 때는 첫 번째 덱에서 카드를 가져옴
                if (i % 2 == 0) {
                    shuffledDeck[i] = cards[p1];
                    p1++;
                } 
                // i가 홀수일 때는 두 번째 덱에서 카드를 가져옴
                else {
                    shuffledDeck[i] = cards[p2];
                    p2++;
                }
            }
            
            // 출력 형식에 맞게 StringBuilder를 사용하여 출력
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t);
            for (String card : shuffledDeck) {
                sb.append(" ").append(card);
            }
            System.out.println(sb.toString());
        }
    }
}