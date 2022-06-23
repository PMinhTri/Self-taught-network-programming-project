package UDPsocket;
import java.io.*;
import java.net.*;
import java.util.*;
public class UDPChatClient {

	public static void main(String[] args) throws Exception{
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		
		while(true) {
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			Scanner sc = new Scanner(System.in);
			String mess = "";
			System.out.print("Enter client message: ");
			mess = sc.nextLine();
			sendData = mess.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9988);
			clientSocket.send(sendPacket); sendData = null;
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String str = new String(receivePacket.getData());
			System.out.println("Server: " + str);
			sc = sc.reset();
		}
	}
}
