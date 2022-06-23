package UDPsocket;
import java.net.*;
import java.io.*;
import java.util.*;
public class UDPServer {
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
	public static void main(String[] args) throws Exception{
		try {
			DatagramSocket serverSocket = new DatagramSocket(9988);
			System.out.println("Server is started");
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			String kq = "";
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
				serverSocket.receive(receivePacket);
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				String s = new String(receivePacket.getData());
				kq = "In hoa : "+s.toUpperCase().trim()+"\n"+"In thuong : "+s.toLowerCase().trim()+"\n"+
				"Vua hoa vua thuong: "+vuahoavuathuong(s).trim()+"\n";
				System.out.println(kq);
				sendData = kq.toString().getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IPAddress, port);
				serverSocket.send(sendPacket);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
