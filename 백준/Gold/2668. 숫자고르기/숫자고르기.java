import java.util.*;
 
public class Main {    
    
    static ArrayList<Integer> list;
    static boolean[] visited;
    static int[] num;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int n = scan.nextInt();
        num = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            num[i] = scan.nextInt();
        }
        
        list = new ArrayList<>();
        visited = new boolean[n + 1];
        for(int i = 1; i <= n; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }
        
        Collections.sort(list);
        System.out.println(list.size());
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }    
    
    public static void dfs(int start, int target) {
        if(visited[num[start]] == false) {
            visited[num[start]] = true;
            dfs(num[start], target);
            visited[num[start]] = false;
        }
        if(num[start] == target) list.add(target);
    }
}
