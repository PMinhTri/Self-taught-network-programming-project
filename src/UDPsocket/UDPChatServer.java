package UDPsocket;
import java.io.*;
import java.net.*;
import java.util.*;
public class UDPChatServer {

	public static void main(String[] args) throws Exception{
		DatagramSocket serverSocket = new DatagramSocket(9988);
		System.out.println("Server is started");
		while(true) {
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			Scanner sc = new Scanner(System.in);
			String mess = "";
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String clientMess = new String(receivePacket.getData(),"UTF-8");
			System.out.println("Client: "+clientMess);
			System.out.print("Enter server message: ");
			mess = sc.nextLine();
			sendData = mess.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
			serverSocket.send(sendPacket); sendData = null;
			sc = sc.reset();
		}

	}

}
