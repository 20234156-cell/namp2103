import java.util.Scanner;

/**
 * Bài 4: Kiểm tra và phân loại tam giác
 * Nhập 3 số a, b, c. Kiểm tra có phải 3 cạnh tam giác không.
 * Nếu có, xác định loại tam giác.
 */
public class KiemTraTamGiac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap canh a: ");
        double a = sc.nextDouble();
        System.out.print("Nhap canh b: ");
        double b = sc.nextDouble();
        System.out.print("Nhap canh c: ");
        double c = sc.nextDouble();

        // Kiem tra bat dang thuc tam giac (canh phai duong)
        if (a > 0 && b > 0 && c > 0
                && a + b > c && a + c > b && b + c > a) {
            System.out.println("a, b, c la 3 canh cua mot tam giac.");
            System.out.println("Loai tam giac: " + phanLoai(a, b, c));
        } else {
            System.out.println("a, b, c KHONG phai la 3 canh cua tam giac.");
        }

        sc.close();
    }

    static String phanLoai(double a, double b, double c) {
        boolean deu = (a == b && b == c);
        boolean can = (a == b || b == c || a == c);

        // Kiem tra vuong bang dinh ly Pytago
        double a2 = a * a, b2 = b * b, c2 = c * c;
        boolean vuong = (Math.abs(a2 + b2 - c2) < 1e-9)
                || (Math.abs(a2 + c2 - b2) < 1e-9)
                || (Math.abs(b2 + c2 - a2) < 1e-9);

        if (deu) {
            return "Tam giac deu";
        }
        if (vuong && can) {
            return "Tam giac vuong can";
        }
        if (vuong) {
            return "Tam giac vuong";
        }
        if (can) {
            return "Tam giac can";
        }
        return "Tam giac thuong";
    }
}
