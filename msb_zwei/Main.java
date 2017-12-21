/**
 * Project: Match and Scratch Barcodes
 * Module: Main
 * Developed by: Asst. Prof. Dr. Zeeshan Ahmed
 * Organization: UCONN HEALTH
 * Version: MSB-ZWEI
 * Date Published: 12-21-2017
 */

package Match_Barcodes;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class Main extends JFrame {

	String[] args = {};
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Main Match and Scracth Barcodes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btn_Match_Barcodes = new JButton("Match Barcodes");
		btn_Match_Barcodes.setBackground(new Color(255, 255, 255));
		btn_Match_Barcodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Match_Barcodes obj_MB = new Match_Barcodes();
					obj_MB.main(args);

				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		JButton btn_Scratch_Barcodes = new JButton("Scratch Barcodes");
		btn_Scratch_Barcodes.setBackground(new Color(255, 255, 255));
		btn_Scratch_Barcodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Scratch_Barcodes obj_SB = new Scratch_Barcodes();
					obj_SB.main(args);

				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		JButton btn_exit = new JButton("Exit");
		btn_exit.setBackground(new Color(255, 250, 250));
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					System.exit(0);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Programmed by Asst Prof Dr Zeeshan Ahmed");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Match & Scratch Barcodes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btn_Scratch_Barcodes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btn_Match_Barcodes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btn_exit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addContainerGap(201, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btn_Match_Barcodes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_Scratch_Barcodes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_exit)
					.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
