package BTTH2;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Stack;


public class Tinhtoan_UDPServer 
{	
	public static void main(String[] args) 
	{
		try 
		{
			DatagramSocket serverSocket = new DatagramSocket(8975);
			System.out.println("Server is started");
			while (true) 
			{
				TinhToanServer TTS = new TinhToanServer(serverSocket);
				TTS.start();	
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
class TinhToanServer extends Thread
{
	private DatagramSocket serverSocket =null;

	public TinhToanServer(DatagramSocket serverSocket) 
	{
		this.serverSocket= serverSocket;
	}
	
	@Override
	public void run() 
	{
		byte[] receiveData = new byte[1024];
		byte[] sendData  = new byte[1024];
		try {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			receiveData = receivePacket.getData();
			InetAddress IPaddr = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String st=new String(receiveData).trim();
			String sendString = String.valueOf((tinhTrungTo(st)));
			sendData = sendString.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPaddr,port);
			serverSocket.send(sendPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int uuTien(String c) 
	{
		if(c.equals("+")||c.equals("-"))
			return 1;
		if(c.equals("*")||c.equals("/"))
			return 2;
		return 0;
	}
	public static int isToanTu(String c) {
		if(uuTien(c)==0) {
			if (!c.equals("(") && !c.equals(")")) {
				return 0;
			}
			else {
				return 1;
			}
		}
		return 2;
	}
	
	public static String tinhToan(String a, String b, String i) 
	{
		double x = Double.parseDouble(a);
		double y= Double.parseDouble(b);
		switch (i) {
		case "+": {

			return x + y + "";
		}
		case "-": {

			return x - y + "";
		}
		case "*": {

			return x * y + "";
		}
		case "/": {

			return x / y + "";
		}
		default:
			return "0";
		}
	}
	
	public static String tinhTrungTo(String chuoi) 
	{
		Stack<String> St = new Stack<String>();
		Stack<String> Sh = new Stack<String>();
		String number = "";
		for (int i = 0; i < chuoi.length(); i++) {
			String kitu = String.valueOf(chuoi.charAt(i));
			if (isToanTu(kitu) == 0 && i !=chuoi.length()-1) {
				number += kitu;
			}else {
				if (isToanTu(kitu) == 0 && i==chuoi.length()-1) {
					number+=kitu;
				}
				if (number.length() > 0) {
					Sh.push(number);
					number = "";
				}
				if (isToanTu(kitu) == 1) {
					if (kitu.equals("(")) St.push("(");
					else if (kitu.equals(")")) {
						while (!St.peek().equals("(")) {
							String a = Sh.pop();
							String b = Sh.pop();
							Sh.push(tinhToan(b, a,St.pop()));
						}
						St.pop();
					}
				}else if (isToanTu(kitu)==2) {
					while (!St.isEmpty()&&uuTien(kitu)<= uuTien(St.peek())) {
						String a = Sh.pop();
						String b = Sh.pop();
						Sh.push(tinhToan(b, a,St.pop()));
					}
					St.push(kitu);
				}
			}
		}
		while (!St.isEmpty()) {
			String a = Sh.pop();
			String b = Sh.pop();
			Sh.push(tinhToan(b, a,St.pop()));
		}
		return Sh.pop();
	}
}