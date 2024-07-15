package restreservation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

public class Edittable extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tid;
	private JTextField tcap;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edittable frame = new Edittable();
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
	public Edittable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 964, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tableid");
		lblNewLabel.setBounds(101, 67, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("capacity");
		lblNewLabel_1.setBounds(101, 130, 56, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try

				{

				Class.forName("oracle.jdbc.driver.OracleDriver");

				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","msc");
				Statement st = con.createStatement();
				
				String id = tid.getText();
				int cap = Integer.parseInt(tcap.getText());
				String res="available";
				String ss = "insert into Booking values('"+id+"','"+cap+"','"+res+"')";
				st.executeUpdate(ss);
				JOptionPane.showMessageDialog(null,"values inserted");
				Admin ad = new Admin();
				ad.setVisible(true);
				}
				catch(Exception e1)
				{

					System.out.println(e1);

				}
			}
		});
		btnNewButton.setBounds(61, 217, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try

				{

				Class.forName("oracle.jdbc.driver.OracleDriver");

				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","msc");
				Statement st = con.createStatement();
				
				String id = tid.getText();
				int cap = Integer.parseInt(tcap.getText());
				String res = "available";
				String ss = "DELETE FROM Booking WHERE Tableid = '" + id + "' AND Tcapacity = " + cap;
				st.executeUpdate(ss);

				JOptionPane.showMessageDialog(null,"deleted successfully");
				Admin ad = new Admin();
				ad.setVisible(true);
				}
				catch(Exception e1)
				{

					System.out.println(e1);

				}
			}
		});
		btnNewButton_1.setBounds(195, 217, 85, 21);
		contentPane.add(btnNewButton_1);
		
		tid = new JTextField();
		tid.setBounds(184, 64, 96, 19);
		contentPane.add(tid);
		tid.setColumns(10);
		
		tcap = new JTextField();
		tcap.setBounds(184, 127, 96, 19);
		contentPane.add(tcap);
		tcap.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 52, 419, 290);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel dt = (DefaultTableModel) table.getModel();
				String id = (String) dt.getValueAt(table.getSelectedRow(), 0);
				BigDecimal value = (BigDecimal) dt.getValueAt(table.getSelectedRow(), 1);
				int cap = value.intValue();
				tid.setText(id);
				tcap.setText(String.valueOf(cap));
				
				

			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "capacity", "reservation"
			}
		));
		
		JButton showedit = new JButton("SHOW");
		showedit.addActionListener(new ActionListener() {
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
		showedit.setBounds(688, 10, 85, 21);
		contentPane.add(showedit);
	}
}
