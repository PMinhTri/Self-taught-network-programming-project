package BTTH2;
import java.net.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;

public class UDPClientXulichuoi{

	private JFrame frmXLChui;
	private JTextField textField;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UDPClientXulichuoi window = new UDPClientXulichuoi();
					window.frmXLChui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UDPClientXulichuoi() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmXLChui = new JFrame();
		frmXLChui.setTitle("X\u1EED l\u00ED chu\u1ED7i UDP");
		frmXLChui.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmXLChui.setBounds(100, 100, 510, 401);
		frmXLChui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmXLChui.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 47, 476, 48);
		frmXLChui.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 285, 476, 59);
		frmXLChui.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Execute");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					DatagramSocket clientSocket = new DatagramSocket(); //gan cong
					InetAddress IPAddress = InetAddress.getByName("localhost");
					byte[] sendData = new byte[5000];
					byte[] receiveData = new byte[5000];
					String chuoi = textField.getText();
					sendData = chuoi.getBytes("UTF-8");
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,9877);
					clientSocket.send(sendPacket);
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);
					String str = new String(receivePacket.getData());
					textArea.setText("Sau khi Server xu ly: \n" + str);
					clientSocket.close();
				}
				catch (Exception ex)
				{
					
				}
			}
		});
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 21));
		btnNewButton.setBounds(181, 179, 131, 41);
		frmXLChui.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nh\u1EADp chu\u1ED7i : ");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 19));
		lblNewLabel.setBounds(10, 10, 145, 27);
		frmXLChui.getContentPane().add(lblNewLabel);
		
		JLabel lblKtQu = new JLabel("K\u1EBFt qu\u1EA3 : ");
		lblKtQu.setFont(new Font("Consolas", Font.BOLD, 19));
		lblKtQu.setBounds(10, 248, 145, 27);
		frmXLChui.getContentPane().add(lblKtQu);
		
	}
}
