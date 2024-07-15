package restreservation;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class login {

	private JFrame frame;
	private JTextField tname;
	private JPasswordField tpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.getContentPane().setBackground(new Color(143, 169, 184));
		frame.setBounds(100, 100, 798, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("userid");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(125, 62, 85, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(125, 123, 70, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		tname = new JTextField();
		tname.setBounds(245, 59, 96, 19);
		frame.getContentPane().add(tname);
		tname.setColumns(10);
		
		tpass = new JPasswordField();
		tpass.setBounds(245, 127, 96, 19);
		frame.getContentPane().add(tpass);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"customer", "admin"}));
		comboBox.setBounds(171, 182, 101, 21);
		frame.getContentPane().add(comboBox);
		
		JButton logbutton = new JButton("login");
		logbutton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent ae) {
				try

				{

				Class.forName("oracle.jdbc.driver.OracleDriver");

				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","msc");

				PreparedStatement pst= con.prepareStatement("select * from login where logid=? and logpass=?");

				pst.setString(1, tname.getText());
				pst.setString(2, tpass.getText());
				String scomb = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				ResultSet rs=pst.executeQuery();
				
				if(rs.next() && scomb.equals("customer"))
				{
					String sid = rs.getString("logid"); 
				    String spass = rs.getString("logpass");
					if(!sid.equals("admin") && !spass.equals("admin")) {
						System.out.println("login successfull");
						JOptionPane.showMessageDialog(null,"Login successfull");
						Customer cs = new Customer();
						cs.setVisible(true);
					}
					else {
						System.out.println("login failed");
						JOptionPane.showMessageDialog(null,"Invalid ");
					}
				}
				else if(scomb.equals("admin"))
		        {
					String sid = rs.getString("logid"); 
				    String spass = rs.getString("logpass");
				    if(sid.equals("admin") && spass.equals("admin")) {
				    	System.out.println("login successfull");
						JOptionPane.showMessageDialog(null,"Admin Login successfull");
						Admin as = new Admin();
						as.setVisible(true);
				    }
				    else {
						System.out.println("login failed");
						JOptionPane.showMessageDialog(null,"Invalid ");
					}
					
				}
				else {
					System.out.println("login failed");
					JOptionPane.showMessageDialog(null,"Invalid ");
				}
				rs.close();
				pst.close();
				}
			
				

				catch(Exception e)

				{

					System.out.println(e);

				}
			}	
			

		});
		logbutton.setBounds(136, 237, 85, 21);
		frame.getContentPane().add(logbutton);
		
		JButton exitbutton = new JButton("exit");
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
			}	
		});
		exitbutton.setBounds(269, 237, 85, 21);
		frame.getContentPane().add(exitbutton);
		
		
		
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
