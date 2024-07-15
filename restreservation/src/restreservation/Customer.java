package restreservation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

public class Customer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField dtext;
	private JTable table_1;
	private JTextField t3;
	private JTextField t4;
	private JTextField tid;
	private JTextField tcap;
	private JTextField Ctext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
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
	public Customer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1019, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tid = new JTextField();
		tid.setBounds(141, 117, 96, 19);
		contentPane.add(tid);
		tid.setColumns(10);
		
		tcap = new JTextField();
		tcap.setBounds(141, 161, 96, 19);
		contentPane.add(tcap);
		tcap.setColumns(10);
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane();
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

		scrollPane.setBounds(393, 57, 415, 165);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "capacity"
			}
		));
		
		JButton avtab = new JButton("Available table");
		avtab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
			            Class.forName("oracle.jdbc.driver.OracleDriver");
			            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "msc");
			            String sdate = dtext.getText();
			            String query = "SELECT Tableid, Tcapacity " +
			                           "FROM Booking " +
			                           "WHERE Tableid NOT IN (SELECT Tableid FROM customer WHERE date_column = ?)";
			            PreparedStatement pst = con.prepareStatement(query);
			            pst.setString(1, sdate);
			            ResultSet rs = pst.executeQuery();
			            table.setModel(DbUtils.resultSetToTableModel(rs));
			        } catch (Exception e1) {
			            e1.printStackTrace();
			        }
	        }
			
		});
		avtab.setBounds(119, 201, 118, 21);
		contentPane.add(avtab);
		
		JLabel lblNewLabel = new JLabel("Date");
		lblNewLabel.setBounds(45, 75, 45, 13);
		contentPane.add(lblNewLabel);
		
		dtext = new JTextField();
		dtext.setBounds(141, 72, 96, 19);
		contentPane.add(dtext);
		dtext.setColumns(10);
		table_1 = new JTable();
		t3 = new JTextField();
		t3.setBounds(141, 323, 96, 19);
		contentPane.add(t3);
		t3.setColumns(10);
		
		t4 = new JTextField();
		t4.setBounds(141, 381, 96, 19);
		contentPane.add(t4);
		t4.setColumns(10);
		JScrollPane scrollPane_1 = new JScrollPane();
		table_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	DefaultTableModel dt = (DefaultTableModel) table_1.getModel();
		    	Object tabObject = dt.getValueAt(table_1.getSelectedRow(), 3);
		    	Object ddObject = dt.getValueAt(table_1.getSelectedRow(), 4);

		    	String tab = null;
		    	String dd = null;

		    	if (tabObject instanceof java.sql.Timestamp) {
		    	    java.sql.Timestamp tabTimestamp = (java.sql.Timestamp) tabObject;
		    	    tab = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tabTimestamp);
		    	} else {
		    	    tab = (String) tabObject;
		    	}

		    	if (ddObject instanceof java.sql.Timestamp) {
		    	    java.sql.Timestamp ddTimestamp = (java.sql.Timestamp) ddObject;
		    	    dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ddTimestamp);
		    	} else {
		    	    dd = (String) ddObject;
		    	}

		    	t3.setText(tab);
		    	t4.setText(dd);

		        }
		    
		});


		scrollPane_1.setBounds(393, 296, 496, 142);
		contentPane.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CID", "CNAME", "CPHONE", "TID", "DATE"
			}
		));
		
		JLabel lblNewLabel_3 = new JLabel("TID");
		lblNewLabel_3.setBounds(45, 326, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("DATE");
		lblNewLabel_4.setBounds(45, 384, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		
		
		JButton btnNewButton_1 = new JButton("CANCEL BOOKING");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				    Class.forName("oracle.jdbc.driver.OracleDriver");
				    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "msc");

				        
				        
				        String id = Ctext.getText(); // Replace with actual id
				        String tabid = ""; // Assuming tid is a JTextField
				        String dd = ""; // Assuming dtext is a JTextField
				        
				        // Prepare the UPDATE statement with placeholders
				        String updateQuery = "UPDATE customer SET Tableid = ?, date_column = ? WHERE Cusid = ?";
				        PreparedStatement updateStmt = con.prepareStatement(updateQuery);
				        
				        // Set values for placeholders
				        updateStmt.setString(1, tabid);
				        updateStmt.setString(2, dd);
				        updateStmt.setString(3, id);
				        
				        // Execute the UPDATE statement
				        int rowsAffected = updateStmt.executeUpdate();
				        
				        if (rowsAffected > 0) {
				            JOptionPane.showMessageDialog(null, "Cancelled successfully");
				        } else {
				            JOptionPane.showMessageDialog(null, "Cancellation failed");
				        }
				        
				        // Close resources
				        updateStmt.close();
			
				    con.close();
				} catch (Exception e1) {
				    e1.printStackTrace();
				}

				
			}
		});
		btnNewButton_1.setBounds(46, 440, 149, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("BOOKING");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    Class.forName("oracle.jdbc.driver.OracleDriver");
				    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "msc");
				    String id = Ctext.getText();
				    
				    // Create the SQL query with a placeholder for the customer ID
				    String query = "SELECT * FROM customer WHERE Cusid = ?";
				    
				    // Prepare the statement with the SQL query
				    PreparedStatement pstmt = con.prepareStatement(query);
				    
				    // Set the value of the placeholder to the customer ID
				    pstmt.setString(1, id);
				    
				    // Execute the query
				    ResultSet rs = pstmt.executeQuery();
				    
				    // Set the result set to the table_1 component (assuming it's a JTable)
				    table_1.setModel(DbUtils.resultSetToTableModel(rs));
				    
				    // Close resources
				    rs.close();
				    pstmt.close();
				    con.close();
				} catch (Exception e2) {
				    e2.printStackTrace(); // Print the exception stack trace for debugging
				}

			}
		});
		btnNewButton_2.setBounds(576, 259, 96, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Book Table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				    Class.forName("oracle.jdbc.driver.OracleDriver");
				    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "msc");

				    // Prepare the statement to retrieve the phone number
				    PreparedStatement pstmt = con.prepareStatement("SELECT Cusphone FROM customer");
				    ResultSet rs = pstmt.executeQuery();
				    
				    if (rs.next()) {
				        // Retrieve phone number
				        int phone = rs.getInt("Cusphone");
				        
				        // Get other values needed for updating
				        String id = Ctext.getText(); // Replace with actual id
				        String tabid = tid.getText(); // Assuming tid is a JTextField
				        String dd = dtext.getText(); // Assuming dtext is a JTextField
				        
				        // Prepare the UPDATE statement with placeholders
				        String updateQuery = "UPDATE customer SET Tableid = ?, date_column = ? WHERE Cusid = ?";
				        PreparedStatement updateStmt = con.prepareStatement(updateQuery);
				        
				        // Set values for placeholders
				        updateStmt.setString(1, tabid);
				        updateStmt.setString(2, dd);
				        updateStmt.setString(3, id);
				        
				        // Execute the UPDATE statement
				        int rowsAffected = updateStmt.executeUpdate();
				        
				        if (rowsAffected > 0) {
				            JOptionPane.showMessageDialog(null, "Customer information updated successfully");
				        } else {
				            JOptionPane.showMessageDialog(null, "No customer found with the provided ID");
				        }
				        
				        // Close resources
				        updateStmt.close();
				    } else {
				        JOptionPane.showMessageDialog(null, "No phone numbers found");
				    }
				    
				    // Close resources
				    rs.close();
				    pstmt.close();
				    con.close();
				} catch (Exception e1) {
				    e1.printStackTrace();
				}


			}
		});
		btnNewButton.setBounds(24, 201, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("TID");
		lblNewLabel_1.setBounds(45, 120, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("capacity");
		lblNewLabel_2.setBounds(45, 164, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Cusid");
		lblNewLabel_5.setBounds(45, 38, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		Ctext = new JTextField();
		Ctext.setBounds(141, 35, 96, 19);
		contentPane.add(Ctext);
		Ctext.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("LOGOUT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_3.setBounds(840, 10, 118, 21);
		contentPane.add(btnNewButton_3);
		
		
	}
}
