import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            
            // Lower Bound와 Upper Bound를 찾아 개수 계산
            // 개수 = (target을 초과하는 첫 위치) - (target 이상인 첫 위치)
            int count = upperBound(target, arr, N) - lowerBound(target, arr, N);
            sb.append(count).append(" ");
        }
        
        System.out.println(sb.toString());
    }

    
    // target 값 '이상'이 처음으로 나타나는 인덱스를 반환 (Lower Bound)
    static int lowerBound(int target, int[] arr, int N) {
        int s = 0; 
        int e = N;

        while (s < e) {
            int mid = (s + e) / 2;

            // arr[mid]가 target보다 '크거나 같으면'
            // mid가 답이거나, mid의 왼쪽에 답이 있을 수 있음
            if (arr[mid] >= target) {
                e = mid;
            } 
            // arr[mid]가 target보다 '작으면'
            // mid와 그 왼쪽은 절대 답이 될 수 없음
            else {
                s = mid + 1;
            }
        }
        
        // s와 e가 만나는 지점이 lower bound
        return s; 
    }

    static int upperBound(int target, int[] arr, int N) {
        int s = 0;
        int e = N; 

        while (s < e) {
            int mid = (s + e) / 2;


            if (arr[mid] > target) {
                e = mid;
            }
            else {
                s = mid + 1;
            }
        }
        
        return s;
    }
}