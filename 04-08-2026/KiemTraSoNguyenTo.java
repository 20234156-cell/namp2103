import java.util.Scanner;

/**
 * Bài 3: Kiểm tra số nguyên tố
 * Nhập một số nguyên n. Kiểm tra n có phải là số nguyên tố hay không.
 */
public class KiemTraSoNguyenTo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so nguyen n: ");
        int n = sc.nextInt();

        if (isPrime(n)) {
            System.out.println(n + " la so nguyen to.");
        } else {
            System.out.println(n + " khong phai la so nguyen to.");
        }

        sc.close();
    }

    static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
