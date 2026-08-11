import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ma sinh vien: ");
        String maSV = sc.nextLine();

        System.out.print("Nhap ho ten: ");
        String hoTen = sc.nextLine();

        double diemChuyenCan = nhapDiem(sc, "Nhap diem chuyen can (0-10): ");
        double diemGiuaKy = nhapDiem(sc, "Nhap diem giua ky (0-10): ");
        double diemCuoiKy = nhapDiem(sc, "Nhap diem cuoi ky (0-10): ");

        double diemTongKet = tinhDiemTongKet(diemChuyenCan, diemGiuaKy, diemCuoiKy);
        String xepLoai = xepLoai(diemTongKet);

        System.out.println();
        System.out.printf("%s - %s - %.2f - %s%n", maSV, hoTen, diemTongKet, xepLoai);
    }

    // Nhap va kiem tra diem: bat buoc trong khoang 0-10, sai thi bao loi va yeu cau nhap lai
    static double nhapDiem(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            double diem;
            try {
                diem = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Diem khong hop le. Vui long nhap lai.");
                continue;
            }
            if (diem < 0 || diem > 10) {
                System.out.println("Diem phai trong khoang 0-10. Vui long nhap lai.");
                continue;
            }
            return diem;
        }
    }

    static double tinhDiemTongKet(double chuyenCan, double giuaKy, double cuoiKy) {
        return chuyenCan * 0.1 + giuaKy * 0.3 + cuoiKy * 0.6;
    }

    static String xepLoai(double diemTongKet) {
        if (diemTongKet >= 8.5) return "A";
        if (diemTongKet >= 7.0) return "B";
        if (diemTongKet >= 5.5) return "C";
        if (diemTongKet >= 4.0) return "D";
        return "F";
    }
}
