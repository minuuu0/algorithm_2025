import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][3];
        
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        
        int max = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    int count = 0;
                    for(int l = 0; l < N; l++){
                        if(arr[i][0] >= arr[l][0] && arr[j][1] >= arr[l][1] && arr[k][2] >= arr[l][2]){
                            count++;
                        }
                    }
                    if(count >= K){
                        max = Math.min(max, arr[i][0] + arr[j][1] + arr[k][2]);
                    }
                }
            }
        }
        System.out.println(max);
    }
}