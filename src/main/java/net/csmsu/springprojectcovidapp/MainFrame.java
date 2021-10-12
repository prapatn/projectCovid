package net.csmsu.springprojectcovidapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.csmsu.springprojectcovidapp.entity.Student;
import net.csmsu.springprojectcovidapp.entity.VaccineRegi;
import net.csmsu.springprojectcovidapp.service.MyService;

import java.awt.Window.Type;
import javax.swing.JTabbedPane;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

@Component
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable tableStd;

	private String selectStd = "";
	@Autowired
	AddStdFrame addframe;
	
	@Autowired
	MyService myService;
	
//	@Autowired
	UpdateStdFrame updateframe;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTable tableQVac;
	
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame frame = new MainFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setBackground(new Color(135, 206, 250));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				List<Student> students = myService.getAllStudent();
				reTableStd(students);
				
				VaccineRegi vaccines = myService.getAllVaccine();
				//reTableVac(vaccines);
				
				
			}

			

		});
		setTitle("Covid Registration for Student");
		try {
			Image img = ImageIO.read(getClass().getResource("/icons/syringe.png"));
			setIconImage(img);
		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println(getClass());
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 583);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		UIManager.put("OptionPane.messageFont", new Font("TH SarabunPSK", Font.BOLD, 14));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(192, 192, 192));
		tabbedPane.setFont(new Font("TH SarabunPSK", Font.BOLD, 18));
		tabbedPane.setBounds(10, 10, 766, 526);
		contentPane.add(tabbedPane);

		JPanel panelStd = new JPanel();
		panelStd.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("จัดการข้อมูลลงทะเบียนนิสิต", null, panelStd, null);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		panelStd.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 88, 558, 393);
		panelStd.add(scrollPane);

		tableStd = new JTable();
		tableStd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tableStd.getSelectedRow() > -1) {
					System.out.println(tableStd.getValueAt(tableStd.getSelectedRow(), 0).toString());
					selectStd = tableStd.getValueAt(tableStd.getSelectedRow(), 0).toString();
				}
			}
		});
		tableStd.setFont(new Font("TH SarabunPSK", Font.PLAIN, 16));
		tableStd.getTableHeader().setFont(new Font("TH SarabunPSK", Font.BOLD, 18));
		scrollPane.setViewportView(tableStd);

		JButton searchStdButton = new JButton("ค้นหานิสิตที่ลงทะเบียนแล้ว");
		searchStdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Student> std = myService.getStudentBySid(textField.getText());
				if (std.isPresent()) {
					Student s = std.get();
					List<Student> students = new ArrayList<Student>();
					students.add(s);
					reTableStd(students);
				} else {
					List<Student> st = myService.getStudentBySname(textField.getText());
					if (st.isEmpty())
						JOptionPane.showMessageDialog(null, "ไม่พบข้อมูลนิสิตที่ค้นหา");
					else
						reTableStd(st);
				}
			}
		});
		searchStdButton.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		searchStdButton.setBounds(160, 10, 171, 21);
		panelStd.add(searchStdButton);

		textField = new JTextField();
		textField.setFont(new Font("TH SarabunPSK", Font.PLAIN, 16));
		textField.setBounds(44, 11, 106, 19);
		panelStd.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("*ค้นหาด้วยชื่อหรือรหัส");
		lblNewLabel.setFont(new Font("TH SarabunPSK", Font.ITALIC, 16));
		lblNewLabel.setBounds(42, 35, 124, 13);
		panelStd.add(lblNewLabel);

		JButton addStdButton = new JButton();
		addStdButton.setBackground(Color.WHITE);
		addStdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addframe= new AddStdFrame(myService);
				addframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				addframe.setVisible(true);
			}
		});
		setIconBtn(addStdButton, "/icons/add.png");
		addStdButton.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		addStdButton.setBounds(614, 88, 137, 76);
		
		panelStd.add(addStdButton);

		JButton updateStdButton = new JButton("");
		updateStdButton.setBackground(Color.WHITE);
		updateStdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectStd == "")
					JOptionPane.showMessageDialog(null, "กรุณาเลือกข้อมูลที่ต้องการแก้ไขบนตารางก่อนกดปุ่ม");
				else {
					Optional<Student> std = myService.getStudentBySid(selectStd);
					if (std.isPresent()) {
						Student s = std.get();
						updateframe = new UpdateStdFrame(myService,s);
						updateframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						updateframe.setVisible(true);
					}
					
					
				}
				
			}
		});
		setIconBtn(updateStdButton, "/icons/edit.png");
		updateStdButton.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		updateStdButton.setBounds(614, 189, 137, 76);
		panelStd.add(updateStdButton);

		JButton deleteStdButton = new JButton("");
		deleteStdButton.setBackground(Color.WHITE);
		deleteStdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectStd == "")
					JOptionPane.showMessageDialog(null, "กรุณาเลือกข้อมูลที่ต้องการลบบนตารางก่อนกดปุ่ม");
				else {
					int n = JOptionPane.showConfirmDialog(null,
							"ต้องการจะลบข้อมูลของนิสิตเลขรหัส " + selectStd + " ใช่ไหม?", "ลบข้อมูลนิสิตที่ลงทะเบียน", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						myService.deleteStdBySid(selectStd);
					}
				}
			}
		});
		setIconBtn(deleteStdButton, "/icons/trash.png");
		deleteStdButton.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		deleteStdButton.setBounds(614, 283, 137, 76);
		panelStd.add(deleteStdButton);

		JButton refreshStdButton = new JButton();
		setIconBtn(refreshStdButton, "/icons/refresh.png");
		refreshStdButton.setBackground(Color.WHITE);
		refreshStdButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Student> students = myService.getAllStudent();
				reTableStd(students);
				textField.setText("");
				selectStd="";
			}
		});
		refreshStdButton.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		refreshStdButton.setBounds(614, 380, 137, 68);
		panelStd.add(refreshStdButton);

		JLabel lblNewLabel_1 = new JLabel("*กดปุ่มเพื่อแสดงข้อมูลทั้งหมด");
		lblNewLabel_1.setFont(new Font("TH SarabunPSK", Font.ITALIC, 16));
		lblNewLabel_1.setBounds(624, 454, 157, 27);
		panelStd.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("รายชื่อนิสิตที่ลงทะเบียนแล้ว");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("TH SarabunPSK", Font.BOLD, 20));
		lblNewLabel_2.setBounds(46, 57, 558, 21);
		panelStd.add(lblNewLabel_2);
		
		JPanel panelQueVaccine = new JPanel();
		panelQueVaccine.setLayout(null);
		panelQueVaccine.setBackground(Color.WHITE);
		tabbedPane.addTab("จัดการคิวฉีดวัคชีน", null, panelQueVaccine, null);
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 88, 558, 393);
		panelQueVaccine.add(scrollPane_1);
		
		tableQVac = new JTable();
		tableQVac.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		scrollPane_1.setViewportView(tableQVac);
		
		JButton searchQVacButton = new JButton("ค้นหาคิวที่ลงทะเบียนแล้ว");
		searchQVacButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchQVacButton.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		searchQVacButton.setBounds(160, 10, 171, 21);
		panelQueVaccine.add(searchQVacButton);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("TH SarabunPSK", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(44, 11, 106, 19);
		panelQueVaccine.add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("*ค้นหาด้วยชื่อหรือรหัส");
		lblNewLabel_3.setFont(new Font("TH SarabunPSK", Font.ITALIC, 16));
		lblNewLabel_3.setBounds(42, 35, 124, 13);
		panelQueVaccine.add(lblNewLabel_3);
		
		JButton addStdButton_1 = new JButton();
		addStdButton_1.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		addStdButton_1.setBackground(Color.WHITE);
		addStdButton_1.setBounds(614, 88, 137, 76);
		panelQueVaccine.add(addStdButton_1);
		
		JButton updateStdButton_1 = new JButton("");
		updateStdButton_1.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		updateStdButton_1.setBackground(Color.WHITE);
		updateStdButton_1.setBounds(614, 189, 137, 76);
		panelQueVaccine.add(updateStdButton_1);
		
		JButton deleteStdButton_1 = new JButton("");
		deleteStdButton_1.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		deleteStdButton_1.setBackground(Color.WHITE);
		deleteStdButton_1.setBounds(614, 283, 137, 76);
		panelQueVaccine.add(deleteStdButton_1);
		
		JButton refreshStdButton_1 = new JButton();
		refreshStdButton_1.setFont(new Font("TH SarabunPSK", Font.BOLD, 16));
		refreshStdButton_1.setBackground(Color.WHITE);
		refreshStdButton_1.setBounds(614, 380, 137, 68);
		panelQueVaccine.add(refreshStdButton_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("*กดปุ่มเพื่อแสดงข้อมูลทั้งหมด");
		lblNewLabel_1_1.setFont(new Font("TH SarabunPSK", Font.ITALIC, 16));
		lblNewLabel_1_1.setBounds(624, 454, 157, 27);
		panelQueVaccine.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("รายการคิวที่ลงทะเบียนแล้ว");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("TH SarabunPSK", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(46, 57, 558, 21);
		panelQueVaccine.add(lblNewLabel_2_1);
	}

	private void reTableStd(List<Student> students) {

		DefaultTableModel model = new DefaultTableModel();
		Object[] col = { "รหัสนิสิต", "ชื่อนิสิต", "สาขา", "ชั้นปี" };
		model.setColumnIdentifiers(col);
		for (Student student : students) {
			Object[] row = { student.getSid(), student.getSname(), student.getMajor(), student.getYear() };
			model.addRow(row);
		}
		tableStd.setModel(model);

	}

	private void setIconBtn(JButton Button, String string) {
		try {
			Image img = ImageIO.read(getClass().getResource(string));
			
			Button.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
			System.out.println(getClass());
		}
	}
	private void reTableVac(List<VaccineRegi> vaccines) {
		DefaultTableModel model = new DefaultTableModel();
		Object[] col = { "รหัสคิว", "วันที่นัด", "รอบฉีด", "นิสิตที่จะฉีด" , "ชนิดของวัคชีน" };
		model.setColumnIdentifiers(col);
		for (VaccineRegi vaccine : vaccines) {
			Object[] row = { vaccine.getIdv(),vaccine.getDate(),vaccine.getRound(),vaccine.getStudent().getSname(),vaccine.getTypeVaccine().getNametype() };
			model.addRow(row);
		}
		tableStd.setModel(model);
		
	}
}
