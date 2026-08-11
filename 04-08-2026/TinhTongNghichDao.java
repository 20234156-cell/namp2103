import java.util.Scanner;

/**
 * Bài 2: Tính tổng nghịch đảo
 * Nhập n nguyên dương từ bàn phím. Tính s = 1 + 1/2 + ... + 1/n.
 */
public class TinhTongNghichDao {
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

        double s = 0;
        for (int i = 1; i <= n; i++) {
            s += 1.0 / i;
        }

        System.out.printf("Tong nghich dao s = %.4f%n", s);
        sc.close();
    }
}
