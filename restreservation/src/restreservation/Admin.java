package restreservation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable custab;
	private JScrollPane scrollPane_1;
	private JButton cusbook;
	private JButton edbtn;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 966, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(456, 30, 471, 143);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "capacity", "reservation"
			}
		));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(456, 214, 471, 224);
		contentPane.add(scrollPane_1);
		
		custab = new JTable();
		scrollPane_1.setViewportView(custab);
		custab.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"cusid", "name", "phone", "tid"
			}
		));
		
		JButton tbook = new JButton("Table");
		tbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try

				{

				Class.forName("oracle.jdbc.driver.OracleDriver");

				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","msc");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from Booking");
				table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e1)
				{

					System.out.println(e1);

				}
			}
		});
		tbook.setBounds(632, 0, 85, 21);
		contentPane.add(tbook);
		
		cusbook = new JButton("customer");
		cusbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try

				{

				Class.forName("oracle.jdbc.driver.OracleDriver");

				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","msc");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from customer");
				custab.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e2)
				{

					System.out.println(e2);

				}
			}
			
		});
		cusbook.setBounds(632, 183, 85, 21);
		contentPane.add(cusbook);
		
		edbtn = new JButton("Edit table");
		edbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Edittable eet= new Edittable();
				eet.setVisible(true);
			}
		});
		edbtn.setBounds(94, 90, 119, 21);
		contentPane.add(edbtn);
		
		btnNewButton = new JButton("LOGOUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(94, 150, 119, 21);
		contentPane.add(btnNewButton);
	}
}
