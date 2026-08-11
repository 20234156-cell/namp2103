import java.util.Scanner;

/**
 * Bài 1: Tính tổng số chẵn
 * Nhập n nguyên dương từ bàn phím. Tính s = 2 + 4 + ... + n.
 * Nếu n là số lẻ, tính đến số chẵn lớn nhất nhỏ hơn n.
 */
public class TinhTongSoChan {
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

        int s = 0;
        for (int i = 2; i <= n; i += 2) {
            s += i;
        }

        System.out.println("Tong cac so chan = " + s);
        sc.close();
    }
}
