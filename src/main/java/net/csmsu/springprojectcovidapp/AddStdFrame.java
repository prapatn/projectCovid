package net.csmsu.springprojectcovidapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.csmsu.springprojectcovidapp.entity.Student;
import net.csmsu.springprojectcovidapp.service.MyService;

import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

@Component
public class AddStdFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSid;
	private JTextField textFieldSname;
	private JTextField textFieldMajor;

	
	MyService myService;
	
	Student student;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddStdFrame frame = new AddStdFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param myService2 
	 */
	public AddStdFrame(MyService myService2) {
		setType(Type.POPUP);
//		System.out.println(myService+"1");
		this.myService=myService2;
//		System.out.println(myService+"2");
		setForeground(new Color(192, 192, 192));
		setFont(new Font("TH SarabunPSK", Font.BOLD, 18));
		setTitle("ลงทะเบียนนิสิต");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 464);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			Image img = ImageIO.read(getClass().getResource("/icons/vaccination.png"));
			setIconImage(img);
		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println(getClass());
		}
		UIManager.put("OptionPane.messageFont", new Font("TH SarabunPSK", Font.BOLD, 14));
		
		textFieldSid = new JTextField();
		textFieldSid.setFont(new Font("TH SarabunPSK", Font.PLAIN, 16));
		textFieldSid.setColumns(10);
		textFieldSid.setBounds(145, 72, 174, 19);
		contentPane.add(textFieldSid);
		
		textFieldSname = new JTextField();
		textFieldSname.setFont(new Font("TH SarabunPSK", Font.PLAIN, 16));
		textFieldSname.setColumns(10);
		textFieldSname.setBounds(145, 125, 174, 19);
		contentPane.add(textFieldSname);
		
		textFieldMajor = new JTextField();
		textFieldMajor.setFont(new Font("TH SarabunPSK", Font.PLAIN, 16));
		textFieldMajor.setColumns(10);
		textFieldMajor.setBounds(145, 186, 174, 19);
		contentPane.add(textFieldMajor);
		
		JLabel lblNewLabel = new JLabel("รหัสนิสิต");
		lblNewLabel.setFont(new Font("TH SarabunPSK", Font.BOLD, 22));
		lblNewLabel.setBounds(35, 72, 87, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ชื่อนิสิต");
		lblNewLabel_1.setFont(new Font("TH SarabunPSK", Font.BOLD, 22));
		lblNewLabel_1.setBounds(35, 117, 87, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("สาขา");
		lblNewLabel_2.setFont(new Font("TH SarabunPSK", Font.BOLD, 22));
		lblNewLabel_2.setBounds(35, 185, 87, 19);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ชั้นปี");
		lblNewLabel_3.setFont(new Font("TH SarabunPSK", Font.BOLD, 22));
		lblNewLabel_3.setBounds(35, 243, 87, 32);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBoxYear = new JComboBox();
		comboBoxYear.setForeground(Color.BLACK);
		comboBoxYear.setFont(new Font("Segoe UI", Font.BOLD, 20));
		comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
		comboBoxYear.setSelectedIndex(0);
		comboBoxYear.setBounds(145, 243, 58, 26);
		contentPane.add(comboBoxYear);
		
		JButton addStdButton = new JButton("ลงทะเบียน");
		addStdButton.setBackground(new Color(0, 250, 154));
		addStdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				student = new Student();
				if(textFieldSid.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "กรุณากรอกรหัสนิสิต");
				}else {
					student.setSid(textFieldSid.getText());
					student.setSname(textFieldSname.getText());
					student.setMajor(textFieldMajor.getText());
					student.setYear(comboBoxYear.getSelectedItem().toString());
					myService.addStudent(student);
//					if(myService.updateStudent(student)!=null) {
					JOptionPane.showMessageDialog(null, "ลงทะเบียนสำเร็จเรียบร้อย");
//					}else {
//						JOptionPane.showMessageDialog(null, "ลงทะเบียนไม่สำเร็จ");
//					}
					close();
				}
			}
		});
		addStdButton.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		addStdButton.setBounds(55, 350, 114, 39);
		contentPane.add(addStdButton);
		
		JButton cancelButton = new JButton("ยกเลิก");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}

			
		});
		cancelButton.setBackground(new Color(255, 99, 71));
		cancelButton.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		cancelButton.setBounds(205, 350, 114, 39);
		contentPane.add(cancelButton);
	}
	private void close() {
		this.dispose();
	}
}
