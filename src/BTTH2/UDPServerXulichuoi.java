package BTTH2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServerXulichuoi {

	public static void main(String[] args) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9877);
		System.out.println("Server is started");
		while (true)
		{
			ServerXuLy server = new ServerXuLy(serverSocket);
			server.start();
		}
	}

}

class ServerXuLy extends Thread{
	private DatagramSocket socket;
	public ServerXuLy(DatagramSocket socket)
	{
		this.socket = socket;
	}
	public String inthuong(String chuoi)
	{
		String temp = chuoi.toLowerCase();
		return temp;
	}
	public String inhoa(String chuoi)
	{
		String temp = chuoi.toUpperCase();
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
			if (arr[i]==' ' && arr[i+1]!=' ') count++;
		}
		return count;
	}
	@Override
	public void run() {
				byte[] sendData = new byte[5000];
				byte[] receiveData = new byte[5000];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				try {
					socket.receive(receivePacket);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String str = new String(receivePacket.getData());
				System.out.println(str);
				String ChuoiThuong = inthuong(str);
				String ChuoiInHoa = inhoa(str);
				String ChuoiVuaHoaVuaThuong = vuahoavuathuong(str);
				int SoTu = demtu(str);
				String ketqua="";
				ketqua = "In hoa : "+str.toUpperCase().trim()+"\n"+"In thuong : "+str.toLowerCase().trim()+"\n"+
						"Vua hoa vua thuong: "+vuahoavuathuong(str).trim()+"\n" + "So tu: "+SoTu;
				InetAddress IPAddress = receivePacket.getAddress();
				int port = receivePacket.getPort();
				try {
					sendData = ketqua.toString().getBytes("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,port);
				try {
					socket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
}