package UDPsocket;
import java.net.*;
import java.io.*;
import java.util.*;
public class UDPClient {

	public static void main(String[] args) throws Exception {
		try {
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			Scanner sc = new Scanner(System.in);
			String s = "";
			while(true) {
				System.out.print("Nhap chuoi: ");
				s = sc.nextLine();
				sendData = s.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IPAddress,9988);
				clientSocket.send(sendPacket);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				String str = new String(receivePacket.getData());
				System.out.print(str);
				clientSocket.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
