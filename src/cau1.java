import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class cau1 {

	public static void main(String[] args) throws IOException {
		cau1 db = new cau1();
		String chuoi = "";
		int choice;
		System.out.println("Nhap chuoi vao: ");
		chuoi = db.nhapchuoi();
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.println("1:Dao chuoi.");
			System.out.println("2:In hoa.");
			System.out.println("3:In thuong.");
			System.out.println("4:Vua hoa vua thuong.");
			System.out.println("5:Dem so tu co trong chuoi.");
			System.out.println("6:Tan so xuat hien cua cac tu.");
			System.out.println("0:Ket thuc.");
			System.out.println("Vui long chon 1 cong viec: ");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
			{
				System.out.println("----------------------------------------");
				System.out.println("Chuoi sau khi dao: " + db.daochuoi(chuoi));
				System.out.println("----------------------------------------");
			} break;
			case 2:
			{
				System.out.println("----------------------------------------");
				System.out.println("Sau khi in hoa: " + db.inhoa(chuoi));
				System.out.println("----------------------------------------");
			} break;
			case 3:
			{
				System.out.println("----------------------------------------");
				System.out.println("Sau khi in thuong: " + db.inthuong(chuoi));
				System.out.println("----------------------------------------");
			} break;
			case 4:
			{
				System.out.println("----------------------------------------");
				System.out.println("Vua hoa vua thuong: " + db.vuahoavuathuong(chuoi));
				System.out.println("----------------------------------------");
			} break;
			case 5:
			{
				System.out.println("----------------------------------------");
				System.out.println("So tu co trong chuoi: " + db.demtu(chuoi));
				System.out.println("----------------------------------------");
			} break;
			case 6:
			{
				System.out.println("----------------------------------------");
				System.out.println("Bang tan so cua cac tu:");
				db.tanso(chuoi);
				System.out.println("----------------------------------------");
			} break;
			}
		} while (choice != 0);
		
	}
	public String nhapchuoi() throws IOException
	{
		InputStreamReader luongvao = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(luongvao);
		String str = br.readLine();
		return str;
	}
	public String daochuoi(String chuoi)
	{
		String temp = "";
		char[] arr = chuoi.toCharArray();
		for (int i=chuoi.length()-1;i>=0;i--)
		{
			temp += arr[i];
		}
		return temp;
	}
	public String inhoa(String chuoi)
	{
		String temp = chuoi.toUpperCase();
		return temp;
	}
	public String inthuong(String chuoi)
	{
		String temp = chuoi.toLowerCase();
		return temp;
	}
	public String vuahoavuathuong(String chuoi)
	{
		char[] arr = chuoi.toCharArray();
		String temp = "";
		for (int i=0;i<chuoi.length();i++)
		{
			if (arr[i]>='A' && arr[i]<='Z') arr[i] += 32;
			else if (arr[i]>='a' && arr[i]<='z') arr[i] -= 32;
			temp += arr[i];
		}
		return temp;
	}
	public int demtu(String chuoi)
	{
		char[] arr = chuoi.toCharArray();
		int count = 1;
		for (int i=0;i<chuoi.length();i++)
		{
			if (arr[i]==' ') count++;
		}
		return count;
	}
	public void tanso(String chuoi)
	{
		String[] arr = chuoi.split("\\s");
		int n = arr.length;
		for (int i=0; i<n;i++)
		{
			int count = 1;
			for (int j=i+1;j<n;j++)
			{
				if (arr[i].equals(arr[j]))
				{
					count++;
					n = xoaphantu(arr, n, j);
				}
			}
			System.out.println(arr[i] + ": " + count + " lan");
		}
	}
	public int xoaphantu(String[] arr, int n, int vitri)
	{
		for (int i=vitri;i<n-1;i++)
		{
			arr[i] = arr[i+1];
		}
		return n-1;
	}
}
