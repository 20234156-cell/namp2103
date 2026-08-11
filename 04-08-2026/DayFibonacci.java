import java.util.Scanner;

/**
 * Bài 5: Hiển thị dãy Fibonacci
 * Nhập n nguyên dương. Hiển thị n số Fibonacci đầu tiên theo dạng
 * 0 1 1 2 3 5 8 ...
 */
public class DayFibonacci {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;
        do {
            System.out.print("Nhap n nguyen duong: ");
            n = sc.nextInt();
            if (n <= 0) {
                System.out.println("n phai la so nguyen duong. Vui long nhap lai!");
            }
        } while (n <= 0);

        long a = 0, b = 1;
        System.out.print("Day Fibonacci: ");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            long tmp = a + b;
            a = b;
            b = tmp;
        }
        System.out.println();

        sc.close();
    }
}
