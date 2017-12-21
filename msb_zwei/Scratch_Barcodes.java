/**
 * Project: Match and Scratch Barcodes
 * Module: Scratch Barcodes
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Font;

public class Scratch_Barcodes extends JFrame {

	private JPanel contentPane;
	private JTextArea rtxt_First_Forward;
	private JTextArea rtxt_First_Reverse;
	private JTextArea rtxt_Second_Forward;
	private JTextArea rtxt_Second_Reverse;

	private JTextArea rtxt_Third_Forward;
	private JTextArea rtxt_Third_Reverse;
	private JTextArea rtxt_Fourth_Forward;
	private JTextArea rtxt_Fourth_Reverse;

	private JLabel lbl_First_Forward;
	private JLabel lbl_Third_Forward;
	private JLabel lbl_First_Reverse;
	private JLabel lbl_Third_Reverse;
	
	private JLabel lbl_Second_Forward;
	private JLabel lbl_Second_Reverse;
	private JLabel lbl_Fourth_Forward;
	private JLabel lbl_Fourth_Reverse;

	private JLabel lbl_FastQ_File_Path_Name;
	private JLabel lbl_Count_Reads;
	private JLabel lbl_Count_First_Forward;
	private JLabel lbl_Count_First_Reverse;
	private JLabel lbl_Count_Second_Reverse;
	private JLabel lbl_Count_Second_Forward;
	private JLabel lbl_Count_Third_Forward;
	private JLabel lbl_Count_Third_Reverse;
	private JLabel lbl_Count_Fourth_Reverse;
	private JLabel lbl_Count_Fourth_Forward;
	
	private JLabel lbl_Total_Third;
	private JLabel lbl_Total_Fourth;
	private JLabel lbl_Total_First_Matches;
	private JLabel lbl_Total_Second;
	private JLabel lbl_Total_UnMatched_Reads;

	private JCheckBox chk_Open_Dir_Multiple_Files;
	private JCheckBox chk_Human_Sequences;
	
	DefaultTableModel Obj_DTM_UnMatched_Reads; 

	String str_ColumnName_UnMatched_Reads[] = new String[] { 	
															"#",
															"Read #",
															"Bases",
															"Sequence"
														};

	DefaultTableModel Obj_DTM_First; 
	String str_ColumnName_First[] = new String[] { 	
													"#",
													"Read #",
													"Direction",
													"First Forward",
													"First Reverse",
													"Bases",
													"Sequence"
												};

	
	DefaultTableModel Obj_DTM_Second;
	String str_ColumnName_Second[] = new String[] { 	
													"#",
													"Read #",
													"Direction",
													"Second Forward",
													"Second Reverse",
													"Bases",
													"Sequence"
												};

	DefaultTableModel Obj_DTM_Third; 
	String str_ColumnName_Third[] = new String[] { 	
													"#",
													"Read #",
													"Direction",
													"Third Forward",
													"Third Reverse",
													"Bases",
													"Sequence"
												};
	
	DefaultTableModel Obj_DTM_Fourth; 
	String str_ColumnName_Fourth[] = new String[] { 	
													"Number",
													"Read Number",
													"Direction",
													"Fourth Forward",
													"Fourth Reverse",
													"Bases",
													"Sequence"
												};

	
	DefaultTableModel Obj_DTM_Results; 
	String str_ColumnName_Results[] = new String[] {
													"No.",
													"FastQ File Name",
													"Total Reads",
													"Un-Matched",
													"First Forward",
													"First Reverse",
													"Second Forward",
													"Second Reverse",
													"Third Forward",
													"Third Reverse",
													"Fourth Forward",
													"Fourth Reverse",
													"Total Matches + Un-Matches"
												};
	

	public static int Loaded_FastQ_File_Number = 0;

	public static int Selected_Row_Number_JTable_Fourth = 0;

	public static String Multiple_Barcodes_Number = "";
	public static String Multiple_Barcodes_Read_Number = "";
	public static String Multiple_Barcodes_Forward_Reverse = "";
	public static String Multiple_Barcodes_Start_Barcode = "";
	public static String Multiple_Barcodes_End_Barcode = "";
	public static String Multiple_Barcodes_Bases = "";
	public static String Multiple_Barcodes_Sequence = "";

	
	public static String str_Input_FastQ_File_Name_Path	="NA";
	public static String str_Input_FastQ_Dir_Name_Path ="NA";
	public static String str_Head="Head";
	public static String str_Tail="Tail";
	
	public static String str_Forward_Sequence = "Forward";
	public static String str_Reverse_Sequence = "Reverse";
	
	private JTable JTable_UnMatched_Reads;
	private JTable JTable_First;
	private JTable JTable_Third_S;
	private JTable JTable_First_S;
	private JTable JTable_Second_S;
	private JTable JTable_Third;
	private JTable JTable_Fourth_S;
	private JTable JTable_Results;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scratch_Barcodes frame = new Scratch_Barcodes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void Create_JTable_Columns_UnMatched_Reads()
	{
		try 
		{
			// create object of table model
			Obj_DTM_UnMatched_Reads = new DefaultTableModel(str_ColumnName_UnMatched_Reads, 0);
		    JTable_UnMatched_Reads.setModel(Obj_DTM_UnMatched_Reads);
		    
		    TableColumnModel columnModel = JTable_UnMatched_Reads.getColumnModel();
		    columnModel.getColumn(0).setPreferredWidth(50);
		    columnModel.getColumn(1).setPreferredWidth(50);
		    columnModel.getColumn(2).setPreferredWidth(50);
		    columnModel.getColumn(3).setPreferredWidth(80000);
		    
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Create_JTable_Columns_First()
	{
		try 
		{
			// create object of table model
			Obj_DTM_First = new DefaultTableModel(str_ColumnName_First, 0);
		    JTable_First_S.setModel(Obj_DTM_First);
		    
		    TableColumnModel columnModel = JTable_First_S.getColumnModel();
		    columnModel.getColumn(0).setPreferredWidth(50);
		    columnModel.getColumn(1).setPreferredWidth(50);
		    columnModel.getColumn(2).setPreferredWidth(80);
		    columnModel.getColumn(3).setPreferredWidth(100);
		    columnModel.getColumn(4).setPreferredWidth(100);
		    columnModel.getColumn(5).setPreferredWidth(50);
		    columnModel.getColumn(6).setPreferredWidth(80000);
		    
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Create_JTable_Columns_Second()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Second = new DefaultTableModel(str_ColumnName_Second, 0);
			JTable_Second_S.setModel(Obj_DTM_Second);

			TableColumnModel columnModel = JTable_Second_S.getColumnModel();
		    columnModel.getColumn(0).setPreferredWidth(50);
		    columnModel.getColumn(1).setPreferredWidth(50);
		    columnModel.getColumn(2).setPreferredWidth(80);
		    columnModel.getColumn(3).setPreferredWidth(100);
		    columnModel.getColumn(4).setPreferredWidth(100);
		    columnModel.getColumn(5).setPreferredWidth(50);
		    columnModel.getColumn(6).setPreferredWidth(80000);

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Create_JTable_Columns_Third()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Third = new DefaultTableModel(str_ColumnName_Third, 0);
		    JTable_Third_S.setModel(Obj_DTM_Third);
		    
		    TableColumnModel columnModel = JTable_Third_S.getColumnModel();
		    columnModel.getColumn(0).setPreferredWidth(50);
		    columnModel.getColumn(1).setPreferredWidth(50);
		    columnModel.getColumn(2).setPreferredWidth(80);
		    columnModel.getColumn(3).setPreferredWidth(100);
		    columnModel.getColumn(4).setPreferredWidth(100);
		    columnModel.getColumn(5).setPreferredWidth(50);
		    columnModel.getColumn(6).setPreferredWidth(80000);
		    
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Create_JTable_Columns_Fourth()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Fourth = new DefaultTableModel(str_ColumnName_Fourth, 0);
			JTable_Fourth_S.setModel(Obj_DTM_Fourth);

			TableColumnModel columnModel = JTable_Fourth_S.getColumnModel();
		    columnModel.getColumn(0).setPreferredWidth(50);
		    columnModel.getColumn(1).setPreferredWidth(90);
		    columnModel.getColumn(2).setPreferredWidth(80);
		    columnModel.getColumn(3).setPreferredWidth(100);
		    columnModel.getColumn(4).setPreferredWidth(100);
		    columnModel.getColumn(5).setPreferredWidth(50);
		    columnModel.getColumn(6).setPreferredWidth(80000);

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void Create_JTable_Columns_Results()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Results = new DefaultTableModel(str_ColumnName_Results, 0);
			JTable_Results.setModel(Obj_DTM_Results);

			TableColumnModel columnModel = JTable_Results.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(50);
			columnModel.getColumn(1).setPreferredWidth(250);
		    columnModel.getColumn(2).setPreferredWidth(80);
		    columnModel.getColumn(3).setPreferredWidth(80);
		    columnModel.getColumn(4).setPreferredWidth(80);
		    columnModel.getColumn(5).setPreferredWidth(80);
		    columnModel.getColumn(6).setPreferredWidth(80);
		    columnModel.getColumn(7).setPreferredWidth(80);
		    columnModel.getColumn(8).setPreferredWidth(80);
		    columnModel.getColumn(9).setPreferredWidth(80);
		    columnModel.getColumn(10).setPreferredWidth(80);
		    columnModel.getColumn(11).setPreferredWidth(80);
		    columnModel.getColumn(12).setPreferredWidth(80);
		    
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Start_Analysis
	 * @param str_File_Name_Path
	 */
	public void Start_Analysis(String str_File_Name_Path)
	{
		try
		{
			if(!str_File_Name_Path.equals(""))
			{
		 		String str_Lines = "";
		 		
		 		Create_JTable_Columns_UnMatched_Reads();
		 		Create_JTable_Columns_First();
		 		Create_JTable_Columns_Second();
		 		Create_JTable_Columns_Third();
		 		Create_JTable_Columns_Fourth();
		 		
				FileReader reader = new FileReader(str_File_Name_Path);
				BufferedReader bufferedReader = new BufferedReader(reader);
			
				////////////
				String str_First_Forward 	= rtxt_First_Forward.getText().toString();
				str_First_Forward = str_First_Forward.replaceAll("\\s","");	// Kill spaces
				
				String str_First_Reverse 	= rtxt_First_Reverse.getText().toString();
				str_First_Reverse = str_First_Reverse.replaceAll("\\s","");	// Kill spaces

				String str_Second_Forward 	= rtxt_Second_Forward.getText().toString();
				str_Second_Forward = str_Second_Forward.replaceAll("\\s","");	// Kill spaces
				
				String str_Second_Reverse 	= rtxt_Second_Reverse.getText().toString();
				str_Second_Reverse = str_Second_Reverse.replaceAll("\\s","");

				
				String str_Third_Forward 	= rtxt_Third_Forward.getText().toString();
				str_Third_Forward = str_Third_Forward.replaceAll("\\s","");	// Kill spaces

				String str_Third_Reverse 	= rtxt_Third_Reverse.getText().toString();
				str_Third_Reverse = str_Third_Reverse.replaceAll("\\s","");	// Kill spaces
				
				String str_Fourth_Forward 	= rtxt_Fourth_Forward.getText().toString();
				str_Fourth_Forward = str_Fourth_Forward.replaceAll("\\s","");
				
				String str_Fourth_Reverse 	= rtxt_Fourth_Reverse.getText().toString();
				str_Fourth_Reverse = str_Fourth_Reverse.replaceAll("\\s","");
				
				///////////
				
				int int_FastQ_FileLine_Count = 0;
				int int_FastQ_Read_Count = 0;
				
				int int_UnMatched_Reads_Count = 0;
				boolean flag_UnMatched_Reads_Count = true;
				
				int int_First_Forward_count = 0;
				int int_First_Reverse_count = 0;
				
				int int_Second_Forward_count = 0;
				int int_Second_Reverse_count = 0;
				
				int int_Third_Forward_count = 0;
				int int_Third_Reverse_count = 0;
				
				int int_Fourth_Forward_count = 0;
				int int_Fourth_Reverse_count = 0;
				
				int int_Bases_Count_Pointer =2;
				
				int int_JTable_Total_First_Count =0;
				int int_JTable_Total_Second_Count =0;
				int int_JTable_Total_Third_Count =0;
				int int_JTable_Total_Fourth_Count =0;
				
				boolean flag_First_Match = false;
				boolean flag_Second_Match = false;
				boolean flag_Third_Match = false;
				boolean flag_Fourth_Match = false;
				
				while((str_Lines = bufferedReader.readLine())!=null)
				{
					int_FastQ_FileLine_Count++;
					String str_TotalNumberOfBases = Integer.toString(str_Lines.length());
					
					if(int_FastQ_FileLine_Count==int_Bases_Count_Pointer)
					{
						int_FastQ_Read_Count++;

						/////////////////// First Forward ////////////////////
						if(str_Lines.contains(str_First_Forward))
						{
							int_First_Forward_count++;
							int_JTable_Total_First_Count++;
							Obj_DTM_First.addRow(new Object[] {Integer.toString(int_JTable_Total_First_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence ,  str_First_Forward, "NA", str_TotalNumberOfBases, str_Lines});
							flag_UnMatched_Reads_Count = false;
							flag_First_Match = true;
						}

						/////////////////// First Reverse 
						if(str_Lines.contains(str_First_Reverse))
						{
							int_First_Reverse_count++;
							int_JTable_Total_First_Count++;
							Obj_DTM_First.addRow(new Object[] {Integer.toString(int_JTable_Total_First_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence ,  "NA", str_First_Reverse, str_TotalNumberOfBases, str_Lines});
							flag_UnMatched_Reads_Count = false;
							flag_First_Match = true;
						}
						
						
						if(flag_First_Match == false)
						{
							/////////////////// Second Forward ////////////////////
							if(str_Lines.contains(str_Second_Forward))
							{
								int_Second_Forward_count++;
								int_JTable_Total_Second_Count++;
								Obj_DTM_Second.addRow(new Object[] {Integer.toString(int_JTable_Total_First_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence ,  str_First_Forward, "NA", str_TotalNumberOfBases, str_Lines});
								flag_UnMatched_Reads_Count = false;
								flag_Second_Match = true;
							}
	
							/////////////////// Second Reverse 
							if(str_Lines.contains(str_Second_Reverse))
							{
								int_Second_Reverse_count++;
								int_JTable_Total_Second_Count++;
								Obj_DTM_Second.addRow(new Object[] {Integer.toString(int_JTable_Total_First_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence ,  "NA", str_First_Reverse, str_TotalNumberOfBases, str_Lines});
								flag_UnMatched_Reads_Count = false;
								flag_Second_Match = true;
							}
						}

						if(flag_First_Match == false && flag_Second_Match == false)
						{
							/////////////////// Third Forward ////////////////////
							if(str_Lines.contains(str_Third_Forward))
							{
								int_Third_Forward_count++;
								int_JTable_Total_Third_Count++;
								Obj_DTM_Third.addRow(new Object[] {Integer.toString(int_JTable_Total_First_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence ,  str_First_Forward, "NA", str_TotalNumberOfBases, str_Lines});
								flag_UnMatched_Reads_Count = false;
								flag_Third_Match = true;
							}

							/////////////////// Third Reverse 
							if(str_Lines.contains(str_Third_Reverse))
							{
								int_Third_Reverse_count++;
								int_JTable_Total_Third_Count++;
								Obj_DTM_Third.addRow(new Object[] {Integer.toString(int_JTable_Total_First_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence ,  "NA", str_First_Reverse, str_TotalNumberOfBases, str_Lines});
								flag_UnMatched_Reads_Count = false;
								flag_Third_Match = true;
							}
							
						}
						
						if(flag_First_Match == false && flag_Second_Match == false && flag_Third_Match == false)
						{
							/////////////////// Fourth Forward ////////////////////
							if(str_Lines.contains(str_Third_Forward))
							{
								int_Fourth_Forward_count++;
								int_JTable_Total_Fourth_Count++;
								Obj_DTM_Fourth.addRow(new Object[] {Integer.toString(int_JTable_Total_First_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence ,  str_First_Forward, "NA", str_TotalNumberOfBases, str_Lines});
								flag_UnMatched_Reads_Count = false;
								flag_Fourth_Match = true;
							}

							/////////////////// Fourth Reverse 
							if(str_Lines.contains(str_Fourth_Reverse))
							{
								int_Fourth_Reverse_count++;
								int_JTable_Total_Fourth_Count++;
								Obj_DTM_Fourth.addRow(new Object[] {Integer.toString(int_JTable_Total_First_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence ,  "NA", str_First_Reverse, str_TotalNumberOfBases, str_Lines});
								flag_UnMatched_Reads_Count = false;
								flag_Fourth_Match = true;
							}
						}
						
						
						/////////////////// UnMatched Reads ////////////////////
												
						if(flag_UnMatched_Reads_Count == true && flag_First_Match == false && flag_Second_Match == false && flag_Third_Match == false && flag_Fourth_Match == false)
						{
							{
								int_UnMatched_Reads_Count++;
								Obj_DTM_UnMatched_Reads.addRow(new Object[] {Integer.toString(int_UnMatched_Reads_Count), Integer.toString(int_FastQ_Read_Count), str_TotalNumberOfBases, str_Lines});	
							}
						}
		
						//Reset Flags
						flag_UnMatched_Reads_Count = true;
						flag_First_Match = false;
						flag_Second_Match = false;
						flag_Third_Match = false;
						flag_Fourth_Match = false;

						int_Bases_Count_Pointer = int_Bases_Count_Pointer +4;
					}
		        }
				
				int int_Reads_count = int_FastQ_FileLine_Count/4;
				
				lbl_Count_Reads.setText( Integer.toString(int_Reads_count));
				
				lbl_Count_First_Forward.setText( Integer.toString(int_First_Forward_count));
				lbl_Count_First_Reverse.setText( Integer.toString(int_First_Reverse_count));
				
				lbl_Count_Second_Reverse.setText( Integer.toString(int_Second_Forward_count));
				lbl_Count_Second_Forward.setText( Integer.toString(int_Second_Reverse_count));
				
				lbl_Count_Third_Forward.setText( Integer.toString(int_Third_Forward_count));
				lbl_Count_Third_Reverse.setText( Integer.toString(int_Third_Reverse_count));
				
				lbl_Count_Fourth_Reverse.setText( Integer.toString(int_Fourth_Forward_count));
				lbl_Count_Fourth_Forward.setText( Integer.toString(int_Fourth_Reverse_count));

				
				lbl_Total_First_Matches.setText(Integer.toString(int_JTable_Total_First_Count));
				lbl_Total_Second.setText(Integer.toString(int_JTable_Total_Second_Count));
				lbl_Total_Third.setText(Integer.toString(int_JTable_Total_Third_Count));
				lbl_Total_Fourth.setText(Integer.toString(int_JTable_Total_Fourth_Count));
				
				lbl_Total_UnMatched_Reads.setText(Integer.toString(int_UnMatched_Reads_Count));
				
				String[] arr_File_Path_Name = str_File_Name_Path.split("/");
				String str_File_Name_Only = arr_File_Path_Name[arr_File_Path_Name.length-1];
				
				int count_Total_Matches_and_UnMatches = int_UnMatched_Reads_Count + int_First_Forward_count + int_First_Reverse_count + int_Second_Forward_count + int_Second_Reverse_count + int_Third_Forward_count + int_Third_Reverse_count + int_Fourth_Forward_count + int_Fourth_Reverse_count;
				
				//Add Results
				Obj_DTM_Results.addRow(new Object[] {Integer.toString(Loaded_FastQ_File_Number), str_File_Name_Only, Integer.toString(int_Reads_count), Integer.toString(int_UnMatched_Reads_Count), Integer.toString(int_First_Forward_count), Integer.toString(int_First_Reverse_count), Integer.toString(int_Second_Forward_count), Integer.toString(int_Second_Reverse_count), Integer.toString(int_Third_Forward_count), Integer.toString(int_Third_Reverse_count), Integer.toString(int_Fourth_Forward_count), Integer.toString(int_Fourth_Reverse_count), Integer.toString(count_Total_Matches_and_UnMatches)});

				
				reader.close();
			}
 		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public Scratch_Barcodes() {
		setResizable(false);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Scratch_Barcodes.class.getResource("/images/compre_64.png")));
		setBackground(new Color(255, 255, 255));
		setTitle("Match Barcodes : Developed by Asst. Prof. Dr. Zeeshan AHMED");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 1430, 1032);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_Reverse_Adapters_Barcodes = new JPanel();
		panel_Reverse_Adapters_Barcodes.setBackground(new Color(224, 255, 255));
		
		JPanel panel_Tabs = new JPanel();
		panel_Tabs.setBackground(Color.WHITE);
		
		JButton btn_FastQ_File = new JButton("Load FastQ File");
		btn_FastQ_File.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
	  		 		{
						if(chk_Open_Dir_Multiple_Files.isSelected())
						{
			  		 		JFileChooser DirChooser = new JFileChooser();
			  		 		DirChooser.setCurrentDirectory(new java.io.File("/Users/zahmed/Desktop"));	//Default location is "."
			  		 		DirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			  		 		int returnValue = DirChooser.showOpenDialog(null);			
			  		 		
			  		 		if (returnValue == JFileChooser.APPROVE_OPTION) 
			  		 		{
			  		 			File selected_Dir = DirChooser.getSelectedFile();
			  		 			str_Input_FastQ_Dir_Name_Path = selected_Dir.toString();
								lbl_FastQ_File_Path_Name.setText(str_Input_FastQ_Dir_Name_Path);
			  					//JOptionPane.showMessageDialog(null, str_Input_FastQ_Dir_Name_Path, "Dir Location", JOptionPane.PLAIN_MESSAGE);

			  		 		}

						}
						else
						{
			  		 		JFileChooser fileChooser = new JFileChooser();
			  		 		fileChooser.setCurrentDirectory(new java.io.File("/Users/zahmed/Desktop"));	//Default location is "."
			  		 		int returnValue = fileChooser.showOpenDialog(null);			
			  		 	
			  		 		if (returnValue == JFileChooser.APPROVE_OPTION) 
			  		 		{
			  		 			// Return Path to OPEN FILE
								str_Input_FastQ_File_Name_Path = fileChooser.getSelectedFile().toString();
								lbl_FastQ_File_Path_Name.setText(str_Input_FastQ_File_Name_Path);
							}
						}
	  		 		}
	  		 		catch(Exception ex)
	  		 		{
	  		 			System.out.print(ex);
	  		 		}
			}
		});
		
		lbl_FastQ_File_Path_Name = new JLabel("...");
		
		JPanel panel_Forward_Adapters_Barcodes = new JPanel();
		panel_Forward_Adapters_Barcodes.setBackground(SystemColor.window);
		
		JPanel panel_Results = new JPanel();
		panel_Results.setBackground(SystemColor.window);
		
		JLabel lblNewLabel_8 = new JLabel("Total Number of Reads:");
		lblNewLabel_8.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_8.setForeground(Color.BLUE);
		
		lbl_Count_Reads = new JLabel("...");
		lbl_Count_Reads.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lbl_Count_Reads.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_20 = new JLabel("Developed by Asst. Prof. Dr. Zeeshan AHMED");
		lblNewLabel_20.setForeground(new Color(0, 0, 255));
		lblNewLabel_20.setBackground(new Color(0, 0, 255));
		lblNewLabel_20.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Tabs, GroupLayout.DEFAULT_SIZE, 1399, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_FastQ_File)
							.addGap(18)
							.addComponent(lbl_FastQ_File_Path_Name, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_8)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Count_Reads))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_Forward_Adapters_Barcodes, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_Reverse_Adapters_Barcodes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_Results, GroupLayout.PREFERRED_SIZE, 802, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_20))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_Results, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_Reverse_Adapters_Barcodes, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_Forward_Adapters_Barcodes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_FastQ_File)
						.addComponent(lbl_FastQ_File_Path_Name))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(lbl_Count_Reads))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_Tabs, GroupLayout.PREFERRED_SIZE, 487, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_20)
					.addGap(7))
		);
		
		JScrollPane scrollPane__Results = new JScrollPane();
		
		JButton btn_Export_Results = new JButton("Export Results");
		btn_Export_Results.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Results.getRowCount() > 0)
					{
						Write_To_Excel obj_CSV = new Write_To_Excel();
						obj_CSV.WriteToExcel(JTable_Results, str_ColumnName_Results);
						
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton btn_Start_Analysis = new JButton("Start Analysis");
		btn_Start_Analysis.setForeground(new Color(0, 0, 153));
		btn_Start_Analysis.setFont(new Font("Dialog", Font.PLAIN, 16));
		btn_Start_Analysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
			 		Create_JTable_Columns_Results();
				    Loaded_FastQ_File_Number =0;

					if(chk_Open_Dir_Multiple_Files.isSelected())
					{
						if(!str_Input_FastQ_Dir_Name_Path.equals("NA"))
						{
							File selected_Dir = new File(str_Input_FastQ_Dir_Name_Path);
							File[] listOfFiles = selected_Dir.listFiles();
							
							for (File file : listOfFiles) 
							{
							    if (file.isFile()) 
							    {
							    	String str_FastQ_FileName_Path = str_Input_FastQ_Dir_Name_Path +"/"+ file.getName();
							    	
							    	Loaded_FastQ_File_Number++;
									Start_Analysis(str_FastQ_FileName_Path);

							    }
							}
						}
					}
					else
					{
						if(!str_Input_FastQ_File_Name_Path.equals("NA"))
						{
					    	Loaded_FastQ_File_Number++;
							Start_Analysis(str_Input_FastQ_File_Name_Path);
						}
					}
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		chk_Human_Sequences = new JCheckBox("Default Human Sequences");
		chk_Human_Sequences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(chk_Human_Sequences.isSelected())
					{
						rtxt_First_Forward.setText("TCTCCAACAA");
						rtxt_First_Reverse.setText("TTGTTGGAGA");
						
						rtxt_Second_Forward.setText("CGAGGGCCAC");
						rtxt_Second_Reverse.setText("GTGGCCCTCG");
						
						rtxt_Third_Forward.setText("CAGAGGCCGG");
						rtxt_Third_Reverse.setText("CCGGCCTCTG");
						
						rtxt_Fourth_Forward.setText("CCCGAGAACC");
						rtxt_Fourth_Reverse.setText("GGTTCTCGGG");						
						
					}
					else
					{
						rtxt_First_Forward.setText("TGACATAGAA");
						rtxt_First_Reverse.setText("TTCTATGTCA");
						
						rtxt_Second_Forward.setText("TTGGCATAAC");
						rtxt_Second_Reverse.setText("GTTATGCCAA");
						
						rtxt_Third_Forward.setText("AATAGTCAGT");
						rtxt_Third_Reverse.setText("ACTGACTATT");
						
						rtxt_Fourth_Forward.setText("GACCGAAGGC");
						rtxt_Fourth_Reverse.setText("GCCTTCGGTC");						
					}
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		chk_Open_Dir_Multiple_Files = new JCheckBox("Open Dir. Multiple Files");
		chk_Open_Dir_Multiple_Files.setSelected(true);
		GroupLayout gl_panel_Results = new GroupLayout(panel_Results);
		gl_panel_Results.setHorizontalGroup(
			gl_panel_Results.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Results.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Results.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane__Results, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
						.addGroup(gl_panel_Results.createSequentialGroup()
							.addComponent(chk_Human_Sequences)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chk_Open_Dir_Multiple_Files)
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addComponent(btn_Start_Analysis, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_Export_Results)))
					.addContainerGap())
		);
		gl_panel_Results.setVerticalGroup(
			gl_panel_Results.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_Results.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane__Results, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Results.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btn_Export_Results, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel_Results.createParallelGroup(Alignment.BASELINE)
							.addComponent(chk_Human_Sequences, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(chk_Open_Dir_Multiple_Files))
						.addComponent(btn_Start_Analysis, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		JTable_Results = new JTable();
		scrollPane__Results.setViewportView(JTable_Results);
		panel_Results.setLayout(gl_panel_Results);
		JTable_Results.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		
		JLabel lblNewLabel = new JLabel("First - Forward");
		
		lbl_First_Forward = new JLabel("...");
		
		JLabel lblNewLabel_1 = new JLabel("First - Reverse");
		lbl_First_Reverse = new JLabel("...");
		
		JScrollPane scrollPane_First_Forward = new JScrollPane();
		
		rtxt_First_Forward = new JTextArea();
		rtxt_First_Forward.setText("TGACATAGAA");
		scrollPane_First_Forward.setViewportView(rtxt_First_Forward);
		
		JScrollPane scrollPane_Forward_End_Adapters = new JScrollPane();
		
		rtxt_First_Reverse = new JTextArea();
		rtxt_First_Reverse.setText("TTCTATGTCA");
		scrollPane_Forward_End_Adapters.setViewportView(rtxt_First_Reverse);
		
		JLabel lblNewLabel_4 = new JLabel("Secod Forward");
		
		lbl_Second_Forward = new JLabel("...");
		
		JScrollPane scrollPane_Second_Forward = new JScrollPane();
		
		rtxt_Second_Forward = new JTextArea();
		rtxt_Second_Forward.setText("TTGGCATAAC");
		scrollPane_Second_Forward.setViewportView(rtxt_Second_Forward);
		
		JLabel lblNewLabel_6 = new JLabel("Second Reverse");
		
		lbl_Second_Reverse = new JLabel("...");
		
		JScrollPane scrollPane_Second_Reverse = new JScrollPane();
		
		rtxt_Second_Reverse = new JTextArea();
		rtxt_Second_Reverse.setText("GTTATGCCAA");
		scrollPane_Second_Reverse.setViewportView(rtxt_Second_Reverse);
		GroupLayout gl_panel_Forward_Adapters_Barcodes = new GroupLayout(panel_Forward_Adapters_Barcodes);
		gl_panel_Forward_Adapters_Barcodes.setHorizontalGroup(
			gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
					.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_6)
									.addComponent(lbl_First_Reverse)
									.addComponent(lblNewLabel_1)
									.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
										.addComponent(lbl_First_Forward)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel)))
								.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
							.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
								.addGap(7)
								.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.TRAILING)
									.addComponent(lbl_Second_Forward)
									.addComponent(lblNewLabel_4))
								.addPreferredGap(ComponentPlacement.UNRELATED)))
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbl_Second_Reverse)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane_Second_Reverse, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane_Second_Forward, 0, 0, Short.MAX_VALUE)
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_First_Forward, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_Forward_End_Adapters, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		gl_panel_Forward_Adapters_Barcodes.setVerticalGroup(
			gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
					.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addGap(36)
							.addComponent(lbl_First_Forward)
							.addGap(49)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbl_First_Reverse))
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_First_Forward, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_Forward_End_Adapters, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addGap(109)
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Second_Reverse))
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_Second_Forward, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addGap(8)
									.addComponent(lbl_Second_Forward)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_Second_Reverse, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel_Forward_Adapters_Barcodes.setLayout(gl_panel_Forward_Adapters_Barcodes);
		
		JTabbedPane tabbedPane_Barcodes = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_Barcodes.setBackground(new Color(224, 255, 255));
		
		GroupLayout gl_panel_Tabs = new GroupLayout(panel_Tabs);
		gl_panel_Tabs.setHorizontalGroup(
			gl_panel_Tabs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Tabs.createSequentialGroup()
					.addGap(10)
					.addComponent(tabbedPane_Barcodes, GroupLayout.DEFAULT_SIZE, 1231, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_Tabs.setVerticalGroup(
			gl_panel_Tabs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Tabs.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane_Barcodes, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(191, Short.MAX_VALUE))
		);
		
		JPanel panel_UnMatchedReads = new JPanel();
		panel_UnMatchedReads.setBackground(new Color(224, 255, 255));
		tabbedPane_Barcodes.addTab("UnMatched-Reads", null, panel_UnMatchedReads, null);
		
		JScrollPane scrollPane_UnMatched_Reads = new JScrollPane();
		
		JButton btn_Export_UnMatched_Reads = new JButton("Export");
		btn_Export_UnMatched_Reads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_UnMatched_Reads.getRowCount() > 0)
					{
						Write_To_Excel obj_CSV = new Write_To_Excel();
						obj_CSV.WriteToExcel(JTable_UnMatched_Reads, str_ColumnName_UnMatched_Reads);
						
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_28 = new JLabel("Total Un-Matched Reads:");
		
		lbl_Total_UnMatched_Reads = new JLabel("...");
		GroupLayout gl_panel_UnMatchedReads = new GroupLayout(panel_UnMatchedReads);
		gl_panel_UnMatchedReads.setHorizontalGroup(
			gl_panel_UnMatchedReads.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_UnMatchedReads.createSequentialGroup()
					.addGroup(gl_panel_UnMatchedReads.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_UnMatchedReads.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_UnMatched_Reads, GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE))
						.addGroup(gl_panel_UnMatchedReads.createSequentialGroup()
							.addComponent(lblNewLabel_28)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_UnMatched_Reads)
							.addPreferredGap(ComponentPlacement.RELATED, 1073, Short.MAX_VALUE)
							.addComponent(btn_Export_UnMatched_Reads)))
					.addContainerGap())
		);
		gl_panel_UnMatchedReads.setVerticalGroup(
			gl_panel_UnMatchedReads.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_UnMatchedReads.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_UnMatchedReads.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_Export_UnMatched_Reads)
						.addComponent(lblNewLabel_28)
						.addComponent(lbl_Total_UnMatched_Reads))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_UnMatched_Reads, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTable_UnMatched_Reads = new JTable();
		scrollPane_UnMatched_Reads.setViewportView(JTable_UnMatched_Reads);
		panel_UnMatchedReads.setLayout(gl_panel_UnMatchedReads);
		JTable_UnMatched_Reads.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JPanel jpanle_First = new JPanel();
		jpanle_First.setBackground(new Color(255, 255, 255));
		tabbedPane_Barcodes.addTab("First", null, jpanle_First, null);
		
		JLabel lblNewLabel_19 = new JLabel("Total Matches:");
		
		lbl_Total_First_Matches = new JLabel("...");
		
		JScrollPane scrollPane_First = new JScrollPane();
		
		JButton btn_Export_First = new JButton("Export");
		btn_Export_First.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_First.getRowCount()>0)
					{
						Write_To_Excel obj_CSV = new Write_To_Excel();
						obj_CSV.WriteToExcel(JTable_First, str_ColumnName_First);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_9 = new JLabel("First Forward");
		lblNewLabel_9.setForeground(Color.BLUE);
		
		lbl_Count_First_Forward = new JLabel("...");
		lbl_Count_First_Forward.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_10 = new JLabel("First Reverse");
		lblNewLabel_10.setForeground(Color.BLUE);
		
		lbl_Count_First_Reverse = new JLabel("...");
		lbl_Count_First_Reverse.setForeground(Color.BLUE);
		GroupLayout gl_jpanle_First = new GroupLayout(jpanle_First);
		gl_jpanle_First.setHorizontalGroup(
			gl_jpanle_First.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_First.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_First.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_First, GroupLayout.DEFAULT_SIZE, 1373, Short.MAX_VALUE)
						.addGroup(gl_jpanle_First.createSequentialGroup()
							.addComponent(lblNewLabel_19)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbl_Total_First_Matches)
							.addGap(18)
							.addComponent(lblNewLabel_9)
							.addGap(18)
							.addComponent(lbl_Count_First_Forward)
							.addGap(30)
							.addComponent(lblNewLabel_10)
							.addGap(18)
							.addComponent(lbl_Count_First_Reverse)
							.addPreferredGap(ComponentPlacement.RELATED, 902, Short.MAX_VALUE)
							.addComponent(btn_Export_First)))
					.addContainerGap())
		);
		gl_jpanle_First.setVerticalGroup(
			gl_jpanle_First.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_First.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_First.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpanle_First.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_19)
							.addComponent(lbl_Count_First_Reverse)
							.addComponent(lbl_Total_First_Matches)
							.addComponent(lblNewLabel_9)
							.addComponent(lbl_Count_First_Forward)
							.addComponent(lblNewLabel_10))
						.addComponent(btn_Export_First))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_First, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTable_First_S = new JTable();
		scrollPane_First.setViewportView(JTable_First_S);
		jpanle_First.setLayout(gl_jpanle_First);
		JTable_First_S.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		
		JPanel jpanle_Second = new JPanel();
		jpanle_Second.setBackground(new Color(224, 255, 255));
		tabbedPane_Barcodes.addTab("Second", null, jpanle_Second, null);
		
		JLabel lblNewLabel_21 = new JLabel("Total Matches:");
		
		lbl_Total_Second = new JLabel("...");
		
		JScrollPane scrollPane_Second = new JScrollPane();
		
		JButton btn_Export_Second = new JButton("Export");
		btn_Export_Second.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Second_S.getRowCount()>0)
					{
						Write_To_Excel obj_CSV = new Write_To_Excel();
						obj_CSV.WriteToExcel(JTable_Second_S, str_ColumnName_Second);
					}		
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_11 = new JLabel("Second Forward");
		lblNewLabel_11.setForeground(Color.RED);
		
		lbl_Count_Second_Forward = new JLabel("...");
		lbl_Count_Second_Forward.setForeground(Color.RED);
		
		JLabel lblNewLabel_12 = new JLabel("Second Reverse");
		lblNewLabel_12.setForeground(Color.RED);
		
		lbl_Count_Second_Reverse = new JLabel("...");
		lbl_Count_Second_Reverse.setForeground(Color.RED);
		GroupLayout gl_jpanle_Second = new GroupLayout(jpanle_Second);
		gl_jpanle_Second.setHorizontalGroup(
			gl_jpanle_Second.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpanle_Second.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Second.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_Second, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1373, Short.MAX_VALUE)
						.addGroup(gl_jpanle_Second.createSequentialGroup()
							.addComponent(lblNewLabel_21)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_Second)
							.addGap(18)
							.addComponent(lblNewLabel_11)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Count_Second_Forward)
							.addGap(35)
							.addComponent(lblNewLabel_12)
							.addGap(6)
							.addComponent(lbl_Count_Second_Reverse)
							.addPreferredGap(ComponentPlacement.RELATED, 893, Short.MAX_VALUE)
							.addComponent(btn_Export_Second)))
					.addContainerGap())
		);
		gl_jpanle_Second.setVerticalGroup(
			gl_jpanle_Second.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_Second.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Second.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_Export_Second)
						.addGroup(gl_jpanle_Second.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_21)
							.addComponent(lbl_Total_Second)
							.addComponent(lbl_Count_Second_Forward)
							.addComponent(lblNewLabel_11))
						.addComponent(lblNewLabel_12)
						.addComponent(lbl_Count_Second_Reverse))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_Second, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTable_Second_S = new JTable();
		scrollPane_Second.setViewportView(JTable_Second_S);
		jpanle_Second.setLayout(gl_jpanle_Second);
		JTable_Second_S.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		
		JPanel jpanle_Third = new JPanel();
		jpanle_Third.setBackground(new Color(255, 255, 255));
		tabbedPane_Barcodes.addTab("Third", null, jpanle_Third, null);
		
		JScrollPane scrollPane_Individual_Barcodes = new JScrollPane();
		
		JTable_Third_S = new JTable();
		JTable_Third_S.setBackground(new Color(255, 255, 255));
		scrollPane_Individual_Barcodes.setViewportView(JTable_Third_S);
		JTable_Third_S.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JLabel lblNewLabel_17 = new JLabel("Total Matches:");
		
		lbl_Total_Third = new JLabel("...");
		
		JButton btn_Export_Third = new JButton("Export");
		btn_Export_Third.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Third.getRowCount()>0)
					{
						Write_To_Excel obj_CSV = new Write_To_Excel();
						obj_CSV.WriteToExcel(JTable_Third, str_ColumnName_Third);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_13 = new JLabel("Third Forward");
		lblNewLabel_13.setForeground(Color.BLUE);
		
		lbl_Count_Third_Forward = new JLabel("...");
		lbl_Count_Third_Forward.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_14 = new JLabel("Third Reverse");
		lblNewLabel_14.setForeground(Color.BLUE);
		
		lbl_Count_Third_Reverse = new JLabel("...");
		lbl_Count_Third_Reverse.setForeground(Color.BLUE);
		GroupLayout gl_jpanle_Third = new GroupLayout(jpanle_Third);
		gl_jpanle_Third.setHorizontalGroup(
			gl_jpanle_Third.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_Third.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Third.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_Individual_Barcodes, GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
						.addGroup(gl_jpanle_Third.createSequentialGroup()
							.addComponent(lblNewLabel_17)
							.addGap(18)
							.addComponent(lbl_Total_Third)
							.addGap(20)
							.addComponent(lblNewLabel_13)
							.addGap(18)
							.addComponent(lbl_Count_Third_Forward)
							.addGap(18)
							.addComponent(lblNewLabel_14)
							.addGap(18)
							.addComponent(lbl_Count_Third_Reverse)
							.addPreferredGap(ComponentPlacement.RELATED, 678, Short.MAX_VALUE)
							.addComponent(btn_Export_Third)))
					.addContainerGap())
		);
		gl_jpanle_Third.setVerticalGroup(
			gl_jpanle_Third.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_Third.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Third.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpanle_Third.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_jpanle_Third.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_13)
								.addComponent(lbl_Count_Third_Forward)
								.addComponent(lblNewLabel_14)
								.addComponent(lbl_Count_Third_Reverse))
							.addGroup(gl_jpanle_Third.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_17)
								.addComponent(lbl_Total_Third)))
						.addComponent(btn_Export_Third))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_Individual_Barcodes, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
					.addContainerGap())
		);
		jpanle_Third.setLayout(gl_jpanle_Third);
		
		JPanel jpanle_Fourth = new JPanel();
		jpanle_Fourth.setBackground(new Color(224, 255, 255));
		tabbedPane_Barcodes.addTab("Fourth", null, jpanle_Fourth, null);
		
		JScrollPane scrollPane_Fourth = new JScrollPane();
		
		JLabel lblNewLabel_18 = new JLabel("Total Matches:");
		
		lbl_Total_Fourth = new JLabel("...");
		
		JButton btn_Export_MultipleBarcodes = new JButton("Export");
		btn_Export_MultipleBarcodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Fourth_S.getRowCount()>0)
					{
						Write_To_Excel obj_CSV = new Write_To_Excel();
						obj_CSV.WriteToExcel(JTable_Fourth_S, str_ColumnName_Fourth);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_15 = new JLabel("Fourth Forward");
		lblNewLabel_15.setForeground(Color.RED);
		
		lbl_Count_Fourth_Forward = new JLabel("...");
		lbl_Count_Fourth_Forward.setForeground(Color.RED);
		
		JLabel lblNewLabel_16 = new JLabel("Fourth Reverse");
		lblNewLabel_16.setForeground(Color.RED);
		
		lbl_Count_Fourth_Reverse = new JLabel("...");
		lbl_Count_Fourth_Reverse.setForeground(Color.RED);
		
		GroupLayout gl_jpanle_Fourth = new GroupLayout(jpanle_Fourth);
		gl_jpanle_Fourth.setHorizontalGroup(
			gl_jpanle_Fourth.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_Fourth.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Fourth.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_Fourth, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1196, Short.MAX_VALUE)
						.addGroup(gl_jpanle_Fourth.createSequentialGroup()
							.addComponent(lblNewLabel_18)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_Fourth)
							.addGap(18)
							.addComponent(lblNewLabel_15)
							.addGap(18)
							.addComponent(lbl_Count_Fourth_Forward)
							.addGap(18)
							.addComponent(lblNewLabel_16)
							.addGap(18)
							.addComponent(lbl_Count_Fourth_Reverse)
							.addPreferredGap(ComponentPlacement.RELATED, 673, Short.MAX_VALUE)
							.addComponent(btn_Export_MultipleBarcodes)))
					.addContainerGap())
		);
		gl_jpanle_Fourth.setVerticalGroup(
			gl_jpanle_Fourth.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpanle_Fourth.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Fourth.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpanle_Fourth.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_18)
							.addComponent(lbl_Total_Fourth)
							.addComponent(lblNewLabel_15)
							.addComponent(lbl_Count_Fourth_Forward)
							.addComponent(lblNewLabel_16)
							.addComponent(lbl_Count_Fourth_Reverse))
						.addComponent(btn_Export_MultipleBarcodes))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_Fourth, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
		);
		
		JTable_Fourth_S = new JTable();
		JTable_Fourth_S.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
	    			
	    			if(JTable_Fourth_S.getRowCount() > 0)
	       			{		       				
	       				Selected_Row_Number_JTable_Fourth = JTable_Fourth_S.getSelectedRow();
	       			}

	    			
	    		}
	    		catch(Exception ex)
	    		{
					ex.printStackTrace();
	    		}
			}
		});
		JTable_Fourth_S.setBackground(new Color(255, 255, 255));
		scrollPane_Fourth.setViewportView(JTable_Fourth_S);
		JTable_Fourth_S.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jpanle_Fourth.setLayout(gl_jpanle_Fourth);


		panel_Tabs.setLayout(gl_panel_Tabs);
		
		JLabel lblNewLabel_2 = new JLabel("Third Forward");
		
		JScrollPane scrollPane_Third_Forward = new JScrollPane();
		
		rtxt_Third_Forward = new JTextArea();
		rtxt_Third_Forward.setText("AATAGTCAGT");
		scrollPane_Third_Forward.setViewportView(rtxt_Third_Forward);
		
		JLabel lblNewLabel_3 = new JLabel("Third Reverse");
		
		JScrollPane scrollPane_Third_Reverse = new JScrollPane();
		lbl_Third_Forward = new JLabel("...");
		lbl_Third_Reverse = new JLabel("...");
		
		JLabel lblNewLabel_5 = new JLabel("Fourth Forward");
		
		lbl_Fourth_Forward = new JLabel("...");
		
		JScrollPane scrollPane_Fourth_Forward = new JScrollPane();
		
		rtxt_Fourth_Forward = new JTextArea();
		rtxt_Fourth_Forward.setText("GACCGAAGGC");
		scrollPane_Fourth_Forward.setViewportView(rtxt_Fourth_Forward);
		
		JLabel lblNewLabel_7 = new JLabel("Fourth Reverse");
		lbl_Fourth_Reverse = new JLabel("...");
		
		JScrollPane scrollPane_Fourth_Reverse = new JScrollPane();
		
		rtxt_Fourth_Reverse = new JTextArea();
		rtxt_Fourth_Reverse.setText("GCCTTCGGTC");
		scrollPane_Fourth_Reverse.setViewportView(rtxt_Fourth_Reverse);
		
		GroupLayout gl_panel_Reverse_Adapters_Barcodes = new GroupLayout(panel_Reverse_Adapters_Barcodes);
		gl_panel_Reverse_Adapters_Barcodes.setHorizontalGroup(
			gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2)
						.addComponent(lbl_Third_Forward, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(lbl_Third_Reverse, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(lbl_Fourth_Forward)
						.addComponent(lblNewLabel_7)
						.addComponent(lbl_Fourth_Reverse))
					.addGap(18)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_Fourth_Reverse, 0, 0, Short.MAX_VALUE)
						.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.LEADING, false)
							.addComponent(scrollPane_Fourth_Forward, 0, 0, Short.MAX_VALUE)
							.addComponent(scrollPane_Third_Forward, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
							.addComponent(scrollPane_Third_Reverse)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_Reverse_Adapters_Barcodes.setVerticalGroup(
			gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(8)
							.addComponent(lbl_Third_Forward))
						.addComponent(scrollPane_Third_Forward, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Third_Reverse))
						.addComponent(scrollPane_Third_Reverse, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_Fourth_Forward, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addGap(8)
							.addComponent(lbl_Fourth_Forward)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_Fourth_Reverse, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_7)
							.addGap(4)
							.addComponent(lbl_Fourth_Reverse)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		rtxt_Third_Reverse = new JTextArea();
		scrollPane_Third_Reverse.setViewportView(rtxt_Third_Reverse);
		rtxt_Third_Reverse.setText("ACTGACTATT");
		panel_Reverse_Adapters_Barcodes.setLayout(gl_panel_Reverse_Adapters_Barcodes);
		contentPane.setLayout(gl_contentPane);
	}
}
