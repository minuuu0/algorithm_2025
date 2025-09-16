import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    private static final long MOD = 1_000_000_007L;

    public static long power(long base, long exp) {
        long res = 1L;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        // prefixW[i]: s의 첫 i개 문자 중 'W'의 개수
        int[] prefixW = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixW[i] = prefixW[i - 1] + (s.charAt(i - 1) == 'W' ? 1 : 0);
        }

        // suffixE[i]: s의 i번째 문자부터 끝까지 중 'E'의 개수
        // 배열 크기를 n+2로 하여 suffixE[i+1] 접근 시 오류 방지
        int[] suffixE = new int[n + 2];
        for (int i = n; i >= 1; i--) {
            suffixE[i] = suffixE[i + 1] + (s.charAt(i - 1) == 'E' ? 1 : 0);
        }

        long ans = 0;
        // 문자열을 순회하며 'H'를 찾음
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == 'H') {
                // 현재 'H' 이전의 'W' 개수
                long countW = prefixW[i];
                // 현재 'H' 이후의 'E' 개수
                long countE = suffixE[i];

                // 'E'를 2개 이상 선택하는 경우의 수 계산
                // (전체 부분집합 수) - (0개 선택) - (1개 선택)
                long waysToChooseEs = (power(2, countE) - countE - 1);

                waysToChooseEs = waysToChooseEs % MOD;
                
                // (W 개수) * (E를 2개 이상 고르는 경우의 수)
                long term = (countW * waysToChooseEs) % MOD;
                ans = (ans + term) % MOD;
            }
        }

        System.out.println(ans);
    }
}