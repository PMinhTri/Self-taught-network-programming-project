package BTTH2;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Chat_UDPServer {
	private DatagramSocket serverSocket;
	private int Port;
	public Chat_UDPServer(int Port) {
		this.Port = Port;
	}

	public void startServer() {
		try {
			DatagramSocket server = new DatagramSocket(Port);
				ClientHandler exe = new ClientHandler(server);
				exe.start();
				System.out.println("Server is started!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		Chat_UDPServer server = new Chat_UDPServer(9000);
		server.startServer();
	}
}

class ClientHandler extends Thread{
	public static HashMap<DatagramPacket, Integer> ClientList = new HashMap<>();
	public static HashMap<Integer, InetAddress> PortList = new HashMap<>();
	public static ArrayList<Integer> ListTemp = new ArrayList<>();
	private DatagramSocket socket;
	private DatagramPacket sendPacket;
	private DatagramPacket receivePacket;
	private String Username;
	private InetAddress IPAddress;
	private int Port;

	public ClientHandler(DatagramSocket socket) {
		try {
			this.socket = socket;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		String msgClient;
		try {
			while(true) {
				byte[] receiveData = new byte[5000];
				this.receivePacket = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(receivePacket);
				msgClient = new String(receivePacket.getData());
				IPAddress = receivePacket.getAddress();
				Port = receivePacket.getPort();
				System.out.println(msgClient);
				CheckPacket(receivePacket, IPAddress, Port);
				sendMess(msgClient,Port);
			}
		}catch(Exception e) {
			
		}
		
	}
	
	public void CheckPacket(DatagramPacket Packet, InetAddress IPAddress, int Port) {
		for(DatagramPacket item : ClientList.keySet()) {
			if(item.getAddress().equals(Packet.getAddress())&&item.getPort()==Packet.getPort()) {
				ListTemp.add(item.getPort());
				ClientList.replace(Packet, 0);
			}
			if(!ListTemp.contains(Packet)){
				PortList.put(item.getPort(), item.getAddress());
//				System.out.println(item.getPort());
			}
		}
		ClientList.put(Packet, 0);
	}
	
	public void sendMess(String msg,int Port) {
		for(Map.Entry<Integer, InetAddress> items : PortList.entrySet()) {
			if(items.getKey()!= Port){
				try {
					byte[] sendData = new byte[5000];
					sendData = msg.toString().getBytes("UTF-8");
					this.sendPacket = new DatagramPacket(sendData, sendData.length, items.getValue(), items.getKey());
					socket.send(sendPacket);
			}catch(Exception e) {
				e.printStackTrace();}
			}
		}
	}
	
}