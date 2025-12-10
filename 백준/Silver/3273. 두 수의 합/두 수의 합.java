import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int count = 0;
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == x) {
                count++;
                left++;
                right--;    // 중복된 수가 없다고 가정할 때 양쪽 다 좁힘
            } else if (sum < x) {
                left++;     // 합이 작으므로 더 큰 수를 더하기 위해 left 이동
            } else {
                right--;    // 합이 크므로 더 작은 수를 더하기 위해 right 이동
            }
        }

        System.out.println(count);
    }
}