package BTTH1;
import java.net.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;

public class TCPClientXulichuoi {

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
					TCPClientXulichuoi window = new TCPClientXulichuoi();
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
	public TCPClientXulichuoi() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmXLChui = new JFrame();
		frmXLChui.setTitle("X\u1EED l\u00ED chu\u1ED7i TCP/IP");
		frmXLChui.getContentPane().setBackground(new Color(0, 191, 255));
		frmXLChui.setBounds(100, 100, 510, 544);
		frmXLChui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmXLChui.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 47, 476, 48);
		frmXLChui.getContentPane().add(textField);
		textField.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 285, 476, 212);
		frmXLChui.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("Execute");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Socket socket = new Socket("localhost",6969);
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					String msg = textField.getText();
					dos.writeUTF(msg);
					String result = dis.readUTF();
					textArea.setText(result);
					socket.close();
				}catch(Exception ex){
					  System.out.print("error" + ex);
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
