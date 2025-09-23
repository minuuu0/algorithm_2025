import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int N, K, L;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 팀원 세명의 레이팅 합 K 이상
        L = Integer.parseInt(st.nextToken()); // 모든 팀원의 레이팅이 L 이상

        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());

            if (num1 >= L && num2 >= L && num3 >= L) {
                if (num1 + num2 + num3 >= K) {
                    count++;
                    list.add(num1);
                    list.add(num2);
                    list.add(num3);
                }
            }
        }

        System.out.println(count);
        
        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num).append(" ");
        }
        
        System.out.println(sb);
    }
}