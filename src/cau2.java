import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class cau2 {

	public static void main(String[] args) throws IOException {
		cau2 db = new cau2();
		int choice;
		Scanner sc = new Scanner(System.in);
		int n;
		do
		{
			System.out.println("Nhap vao so nguyen duong n: ");
			n = db.NhapSo();
		} while (n<=0);
		do
		{
			System.out.println("1:Tinh tong cac chu so cua so nguyen duong nhap vao.");
			System.out.println("2:In ra so dao nguoc cua so nguyen duong nhap vao.");
			System.out.println("3:Kiem tra so nguyen duong nhap vao co thuoc day so Fibonaci khong?");
			System.out.println("4:Kiem tra dao nguoc cua m co phai so doi xung khong?");
			System.out.println("0:Thoat.");
			System.out.println("Nhap so thu tu: ");
			choice = sc.nextInt();
			switch (choice)
			{
				case 1: {
					System.out.println("----------------------------------------");
					System.out.println("Tong cac chu so : " + db.TongCacChuSo(n));
					System.out.println("----------------------------------------");
				} break;
				case 2: {
					System.out.println("----------------------------------------");
					System.out.println("So dao nguoc la : " + db.DaoNguocSo(n));
					System.out.println("----------------------------------------");
				} break;
				case 3: {
					System.out.println("----------------------------------------");
					if (db.KiemTraFibonaci(n)==true) System.out.println(n + " la so thuoc day Fibonaci.");
					else System.out.println(n + " khong thuoc day Fibonaci.");
					System.out.println("----------------------------------------");
				} break;
				case 4: {
					System.out.println("----------------------------------------");
					if (n == db.DaoNguocSo(n)) System.out.println(db.DaoNguocSo(n) + " la so doi xung.");
					else System.out.println(db.DaoNguocSo(n) + " khong phai so doi xung.");
					System.out.println("----------------------------------------");
				} break;
			}
		} while (choice!=0);
	}
	public int NhapSo() throws IOException
	{
		InputStreamReader luongvao = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(luongvao);
		String n = br.readLine();
		return Integer.parseInt(n);
	}
	public int TongCacChuSo(int n)
	{
		int tong = 0;
		do
		{
			tong += n %10;
		} while ((n/=10) != 0);
		return tong;
	}
	public int DaoNguocSo(int n)
	{
		int dao = 0;
		int temp = 0;
		while(n>0) {
			temp = n%10;
			dao = dao*10 + temp;
			n = n/10;
		}
		return dao;
	}
	public boolean KiemTraFibonaci(int n)
	{
		int first_num = 0;
		int second_num = 1;
		int next_num = 0;
		if (n==0) return true;
		if (n==1) return true;
		while (next_num < n)
		{
			next_num = first_num + second_num;
			first_num = second_num;
			second_num = next_num;
			if (n == next_num) return true;
		}
		return false;
	}
}
