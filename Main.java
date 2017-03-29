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
		setTitle("Match and Scracth Barcodes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btn_Match_Barcodes = new JButton("Match Barcodes");
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btn_Scratch_Barcodes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btn_exit)
						.addComponent(btn_Match_Barcodes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(323, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btn_Match_Barcodes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_Scratch_Barcodes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_exit)
					.addContainerGap(169, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
