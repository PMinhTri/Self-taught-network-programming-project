package UDPsocket;
import java.io.*;
import java.net.*;
import java.util.*;
public class UDPTimeServer {

	public static void main(String[] args) throws Exception{
		DatagramSocket serverSocket = new DatagramSocket(9988);
		System.out.println("Server is started");
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while(true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String request = new String(receivePacket.getData());
//			System.out.println(request);
			if(request.trim().equals("getData")) {
				sendData = new Date().toString().getBytes();
			}else {
				sendData = "Server not know what you want?".getBytes();
			}
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);
			serverSocket.send(sendPacket);;
		}

	}

}
