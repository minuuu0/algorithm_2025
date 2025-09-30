import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] alphabet = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

        String a = br.readLine();
        String b = br.readLine();

        List<Integer> combined = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            combined.add(alphabet[a.charAt(i) - 'A']);
            combined.add(alphabet[b.charAt(i) - 'A']);
        }

        while (combined.size() > 2) {
            List<Integer> next = new ArrayList<>();
            for (int i = 0; i < combined.size() - 1; i++) {
                next.add((combined.get(i) + combined.get(i + 1)) % 10);
            }
            combined = next;
        }

        System.out.println(String.valueOf(combined.get(0)) + String.valueOf(combined.get(1)));
    }
}