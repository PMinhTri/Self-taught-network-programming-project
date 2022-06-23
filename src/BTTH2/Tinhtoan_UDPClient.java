package BTTH2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class Tinhtoan_UDPClient {

	private JFrame frame;
	private JTextField txtNhapChuoi;
	private JTextField txtKetQua;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tinhtoan_UDPClient window = new Tinhtoan_UDPClient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tinhtoan_UDPClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 633, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtNhapChuoi = new JTextField();
		txtNhapChuoi.setBounds(195, 102, 296, 32);
		frame.getContentPane().add(txtNhapChuoi);
		txtNhapChuoi.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nh\u1EADp bi\u1EC3u th\u1EE9c");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel.setBounds(43, 113, 132, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("K\u1EBFt qu\u1EA3");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_1.setBounds(43, 165, 132, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtKetQua = new JTextField();
		txtKetQua.setBounds(195, 153, 296, 32);
		frame.getContentPane().add(txtKetQua);
		txtKetQua.setColumns(10);
		
		JButton btnTinhToan = new JButton("T\u00EDnh to\u00E1n");
		btnTinhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txtKetQua.setText("");
				try {
					TinhToanClient TT = new TinhToanClient(txtNhapChuoi.getText(), txtKetQua);
					TT.start();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnTinhToan.setFont(new Font("Consolas", Font.BOLD, 12));
		btnTinhToan.setBounds(195, 216, 102, 48);
		frame.getContentPane().add(btnTinhToan);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				txtNhapChuoi.setText("");
				txtKetQua.setText("");
			}
		});
		btnReset.setFont(new Font("Consolas", Font.BOLD, 12));
		btnReset.setBounds(383, 214, 102, 48);
		frame.getContentPane().add(btnReset);
		
		JLabel lblNewLabel_2 = new JLabel("T\u00EDnh to\u00E1n bi\u1EC3u th\u1EE9c");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 16));
		lblNewLabel_2.setBackground(Color.PINK);
		lblNewLabel_2.setBounds(187, 40, 259, 20);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
class TinhToanClient extends Thread{
	private String txtNhap ="";
	private JTextField txtXuat;
	public TinhToanClient(String txtNhap, JTextField txtXuat) 
	{
		this.txtNhap = txtNhap;
		this.txtXuat = txtXuat;
	}
	@Override
	public void run() {
		try 
		{
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPaddr = InetAddress.getByName("localhost");
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			sendData = txtNhap.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPaddr,8975);
			clientSocket.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			String ketQuaString = new String(receivePacket.getData());
			txtXuat.setText(ketQuaString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
