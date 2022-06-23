package BTTH2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JButton;

public class Chat_UDPClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtMessage;
	private JTextArea textArea;
	private DatagramSocket socket;
    private String username;
    private DatagramSocket clientSocket;
    private DatagramPacket sendPacket;
    private DatagramPacket receivePacket;
    private InetAddress IPAddress;


	/**
	 * Launch the application.
	 * @throws SocketException 
	 * @throws UnknownHostException 
	 */
    
    public void startClient() {
        try {
        	this.clientSocket = new DatagramSocket(); //gan cong
    		this.IPAddress = InetAddress.getByName("localhost");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getUsername(String username) {
		this.username = username;
	}
    
    public void sendMessage() {
    	String messageToSend = username +": ";
		messageToSend += txtMessage.getText().toString();
		byte[] sendData = new byte[5000];
        try {
        		sendData = messageToSend.getBytes();
        		DatagramPacket sendPacket =
        				new DatagramPacket(sendData, sendData.length, IPAddress,9000);
        		clientSocket.send(sendPacket); // gui du lieu cho server
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    public void confirm(String conf)
    {
		byte[] sendData = new byte[5000];
        try {
        		sendData = conf.getBytes();
        		DatagramPacket sendPacket =
        				new DatagramPacket(sendData, sendData.length, IPAddress,9000);
        		clientSocket.send(sendPacket); // gui du lieu cho server
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    
	public static void main(String[] args) throws SocketException, UnknownHostException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat_UDPClient frame = new Chat_UDPClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Chat_UDPClient() {
		setTitle("Chatbox\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(32, 38, 64, 13);
		contentPane.add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(106, 35, 142, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		
		textArea = new JTextArea();
		textArea.setBounds(32, 81, 406, 231);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Message:");
		lblNewLabel_1.setBounds(32, 344, 64, 13);
		contentPane.add(lblNewLabel_1);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(100, 341, 237, 19);
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(353, 340, 85, 21);
		btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
				textArea.setText(textArea.getText() + "\n" + txtMessage.getText());
				txtMessage.setText("");
			}
		});
		contentPane.add(btnSend);
		
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(353, 34, 85, 21);
		btnConnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtName.disable();
				btnSend.enable();
				getUsername(txtName.getText().toString());
				confirm(txtName.getText() + " entered the chat box!");
			}
		});
		contentPane.add(btnConnect);
		
		JButton btnChange = new JButton("Change");
		btnChange.setBounds(258, 34, 85, 21);
		btnChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtName.enable();
				btnSend.disable();
			}
		});
		contentPane.add(btnChange);
		
		try {
			startClient();
			ListenMess m = new ListenMess();m.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public class ListenMess extends Thread{
		public void run() {
			String msgFromGroupChat;            
            while (true) {
                try {
                	byte[] receiveData = new byte[5000];
                	DatagramPacket receivePacket = 
                			new DatagramPacket(receiveData, receiveData.length);
                	clientSocket.receive(receivePacket);
        			//lay du lieu tu packet nhan duoc
        			msgFromGroupChat = new String(receivePacket.getData());
                    textArea.setText(textArea.getText()+ "\n" +msgFromGroupChat);
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
		}
	}
}
