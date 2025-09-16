import java.io.*;
import java.util.*;

public class Solution {

    static final int MOD = 1234567891;
    static long[] factorial;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        factorial = new long[1000001];
        factorial[0] = 1;
        for (int i = 1; i <= 1000000; i++) {
            factorial[i] = (factorial[i - 1] * i) % MOD;
        }

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            // nCr = n! / (r! * (n-r)!) % MOD
            // nCr = n! * (r! * (n-r)!)^(MOD-2) % MOD

            // 1. n!
            long n = factorial[N];

            // 2. (r! * (n-r)!)
            long r = (factorial[R] * factorial[N - R]) % MOD;

            // 3. (r! * (n-r)!)^(MOD-2) -> 페르마의 소정리를 이용한 모듈러 역원 계산
            long denominatorInverse = power(r, MOD - 2);
            
            // 4. 최종 결과 계산
            long result = (n * denominatorInverse) % MOD;
            
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    public static long power(long base, int exp) {
        long result = 1;
        
        while (exp > 0) {
            // 지수가 홀수이면, 결과에 밑을 곱해준다.
            if (exp % 2 == 1) {
                result = (result * base) % MOD;
            }
            // 밑을 제곱하고 지수는 반으로 나눈다.
            base = (base * base) % MOD;
            exp /= 2;
        }
        
        return result;
    }
}