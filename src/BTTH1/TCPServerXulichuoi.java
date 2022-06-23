package BTTH1;
import java.io.*;
import java.net.*;

public class TCPServerXulichuoi {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(6969);
		System.out.print("Server is started!");
		while(true) {
			Socket socket = server.accept();
			ServerExecute execute = new ServerExecute(socket);
			execute.start();
		}
		
	}
}


class ServerExecute extends Thread {
	private Socket connect;
	public ServerExecute(Socket connect) {
		this.connect = connect;
		
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
	
	public void run() {
		DataOutputStream dos = null;
		DataInputStream dis = null;
		try {
		
			dos = new DataOutputStream(connect.getOutputStream());
			dis = new DataInputStream(connect.getInputStream());
			while(true) {
				String s = dis.readUTF();
				System.out.println(s);
				String res = "";
				res += ("In hoa: "+s.toUpperCase()+"\n");
				res += ("In thuong: "+s.toLowerCase()+"\n");
				res += ("Vua hoa vua thuong: "+vuahoavuathuong(s)+"\n");
				res += ("Dao chuoi: "+daochuoi(s)+"\n");
				res += ("Dem tu: "+demtu(s)+"\n");
				dos.writeUTF(res);
				dos.flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}