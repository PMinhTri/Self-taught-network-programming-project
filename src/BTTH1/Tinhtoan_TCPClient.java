package BTTH1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class Tinhtoan_TCPClient {

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
					Tinhtoan_TCPClient window = new Tinhtoan_TCPClient();
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
	public Tinhtoan_TCPClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBounds(100, 100, 651, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nh\u1EADp bi\u1EC3u th\u1EE9c");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 102, 153, 19);
		frame.getContentPane().add(lblNewLabel);
		
		txtNhapChuoi = new JTextField();
		txtNhapChuoi.setColumns(10);
		txtNhapChuoi.setBounds(183, 91, 296, 32);
		frame.getContentPane().add(txtNhapChuoi);
		
		JLabel lblNewLabel_1 = new JLabel("K\u1EBFt qu\u1EA3");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 14));
		lblNewLabel_1.setBounds(89, 154, 74, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtKetQua = new JTextField();
		txtKetQua.setColumns(10);
		txtKetQua.setBounds(183, 142, 296, 32);
		frame.getContentPane().add(txtKetQua);
		
		JButton btnTinhToan = new JButton("T\u00EDnh to\u00E1n");
		btnTinhToan.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				txtKetQua.setText("");
				try 
				{
					TinhToanClient TT = new TinhToanClient(txtNhapChuoi.getText(), txtKetQua);
					TT.start();
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
				}
			}
		});
		btnTinhToan.setFont(new Font("Consolas", Font.BOLD, 12));
		btnTinhToan.setBounds(183, 205, 102, 48);
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
		btnReset.setBounds(377, 205, 102, 48);
		frame.getContentPane().add(btnReset);
		
		JLabel lblNewLabel_2 = new JLabel("T\u00EDnh to\u00E1n bi\u1EC3u th\u1EE9c");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(Color.PINK);
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 16));
		lblNewLabel_2.setBounds(206, 32, 246, 20);
		frame.getContentPane().add(lblNewLabel_2);
	}

}

class TinhToanClient extends Thread
{
	private String txtNhap ="";
	private JTextField txtXuat;
	public TinhToanClient(String txtNhap, JTextField txtXuat) 
	{
		this.txtNhap = txtNhap;
		this.txtXuat = txtXuat;
	}
	
	public void run() 
	{
		try 
		{
			Socket socket = new Socket("localhost",1423);
			DataInputStream din = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(txtNhap);
			String ketQua = din.readUTF();
			txtXuat.setText(ketQua);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
