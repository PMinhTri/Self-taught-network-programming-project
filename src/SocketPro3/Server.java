package SocketPro3;
import java.io.*;
import java.util.*;
import java.net.*;
public class Server {
	public static int demtu(String chuoi)
	{
		char[] arr = chuoi.toCharArray();
		int count = 1;
		for (int i=0;i<chuoi.length();i++)
		{
			if (arr[i]==' ') count++;
		}
		return count;
	}
	public static String daochuoi(String chuoi)
	{
		String temp = "";
		char[] arr = chuoi.toCharArray();
		for (int i=chuoi.length()-1;i>=0;i--)
		{
			temp += arr[i];
		}
		return temp;
	}
	public static String vuahoavuathuong(String chuoi)
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
	public static void main(String[] args) throws Exception {
		try {
			ServerSocket server = new ServerSocket(8000);
			System.out.println("Server is started");
			Socket socket = server.accept();
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			Scanner kb = new Scanner(System.in);
			while(true) {
				String s = dis.readUTF();
				String res = "";
				res += (s.toUpperCase()+"\n");
				res += (s.toLowerCase()+"\n");
				res += (vuahoavuathuong(s)+"\n");
				res += (daochuoi(s)+"\n");
				res += (demtu(s)+"\n");
				dos.writeUTF(res);
				dos.flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}