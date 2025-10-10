import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();
            if (input.equals("0")) {
                break;
            }

            int length = input.length();
            int[] numberArr = new int[length];
            for (int i = 0; i < length; i++) {
                numberArr[i] = input.charAt(i) - '0';
            }

            int count = 0;
            
            while (true) {
                boolean isPalindrome = true;
                for (int i = 0; i < length / 2; i++) {
                    if (numberArr[i] != numberArr[length - 1 - i]) {
                        isPalindrome = false;
                        break;
                    }
                }
                
                if (isPalindrome) {
                    System.out.println(count);
                    break;
                }
                
                count++;
                numberArr[length - 1]++;

                for (int i = length - 1; i > 0; i--) {
                    if (numberArr[i] == 10) {
                        numberArr[i] = 0;
                        numberArr[i - 1]++;
                    }
                }
            }
        }
        scanner.close();
    }
}