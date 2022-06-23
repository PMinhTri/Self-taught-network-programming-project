package BTTH1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Stack;


public class Tinhtoan_TCPServer {

	public static void main(String[] args) 
	{
		try {
			ServerSocket sever = new ServerSocket(1423);
			System.out.println("Server is started");
			while (true) {
				System.out.println("Waiting for client");
				Socket socket = sever.accept();
				TinhToanServer TTS = new TinhToanServer(socket);
				TTS.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

class TinhToanServer extends Thread{
	private DataInputStream din =null;
	private DataOutputStream dos =null;

	private Socket socket = null;
	public TinhToanServer(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			din = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			String st = din.readUTF();
			System.out.println("Client send: " + st);
			dos.writeUTF(String.valueOf((tinhTrungTo(st))));
			dos.flush();
			din.close();
			dos.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static int uuTien(String c) {
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
	
	public static String tinhToan(String a, String b, String i) {
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
	
	public static String tinhTrungTo(String chuoi) {
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


