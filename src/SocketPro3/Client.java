package SocketPro3;
import java.io.*;
import java.util.*;
import java.net.*;
public class Client {
	public static void main(String[] args) throws Exception{
		try {
			Socket socket = new Socket("localhost",8000);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			Scanner kb = new Scanner(System.in);
			System.out.print("Client: ");
			while(true) {
				String msg = kb.nextLine();
				dos.writeUTF(msg);
				dos.flush();
				
				String rs = dis.readUTF();
				System.out.println("Server: ");
				System.out.println(rs);
				kb = kb.reset();
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
