package UDPsocket;
import java.io.*;
import java.net.*;
import java.util.*;
public class UDPTimeClient {

	public static void main(String[] args) throws Exception{
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		sendData = "getData".getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9988);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		String str = new String(receivePacket.getData());
		System.out.println(str);
		clientSocket.close();
	}
}
