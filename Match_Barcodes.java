	/********************************************************           
    * Match_Barcodes 											* 
    * Author:  Dr. Zeeshan AHMED, Ph.D.					    	*   
    * Organization:	The Jackson Laboratory, USA 				*   
    *                                                      		*   
    * Programming Language: Java                           		*
    * Date Code Submitted: 03-29-2017							*   
    ********************************************************/ 

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

public class Match_Barcodes extends JFrame {

	private JPanel contentPane;
	private JTextArea rtxt_Forward_Start_Adapters;
	private JTextArea rtxt_Forward_End_Adapters;
	private JTextArea rtxt_Forward_Start_Barcodes;
	private JTextArea rtxt_Forward_End_Barcodes;

	private JTextArea rtxt_Reverse_Start_Adapters;
	private JTextArea rtxt_Reverse_End_Adapters;
	private JTextArea rtxt_Reverse_Start_Barcodes;
	private JTextArea rtxt_Reverse_End_Barcodes;

	private JLabel lbl_Forward_Start_Adapters;
	private JLabel lbl_Reverse_End_Adapter;
	private JLabel lbl_Forward_End_Adapters;
	private JLabel lbl_Reverse_Start_Adapter;
	
	private JLabel lbl_Forward_Start_Barcodes;
	private JLabel lbl_Forward_End_Barcodes;
	private JLabel lbl_Reverse_End_Barcode;
	private JLabel lbl_Reverse_Start_Barcode;

	private JLabel lbl_FastQ_File_Path_Name;
	private JLabel lbl_Count_Reads;
	private JLabel lbl_Count_Forward_Start_Adapters;
	private JLabel lbl_Count_Forward_End_Adapters;
	private JLabel lbl_Count_Reverse_Start_Adapters;
	private JLabel lbl_Count_Reverse_End_Adapters;
	private JLabel lbl_Count_Forward_Start_Barcodes;
	private JLabel lbl_Count_Forward_End_Barcodes;
	private JLabel lbl_Count_Reverse_Start_Barcodes;
	private JLabel lbl_Count_Reverse_End_Barcodes;
	
	private JLabel lbl_Total_Individual_Barcode_Matches;
	private JLabel lbl_Total_Multiple_Barcode_Matches;
	private JLabel lbl_Total_Individual_Adapter_Matches;
	private JLabel lbl_Total_Multiple_Adapter_Matches;
	private JLabel lbl_Total_Sorted_Multiple_Barcode_Matches;
	private JLabel lbl_Total_Sorted_Multiple_Barcode_Combinations;
	private JLabel lbl_Total_Sorted_Multiple_Barcode_Combinations_Forward;
	private JLabel lbl_Total_Sorted_Multiple_Barcode_Combinations_Reverse;
	private JLabel lbl_Total_UnMatched_Reads;
	
	private JCheckBox chk_Reverse_Bases_Order;
	private JTable JTable_Individual_Barcodes;
	private JCheckBox chk_Pick_Number_of_Bases;
	private JCheckBox chk_Match_Reverse_Sequence;
	private JCheckBox chk_DistinctCombination_Froward;
	private JCheckBox chk_DistinctCombination_Reverse;
	private JTextArea textArea_FalsePositive;
	private JCheckBox chk_OnlyCombinationsWithoutReads;

	
	DefaultTableModel Obj_DTM_UnMatched_Reads; 

	String str_ColumnName_UnMatched_Reads[] = new String[] { 	
															"#",
															"Read #",
															"Bases",
															"Sequence"
														};

	DefaultTableModel Obj_DTM_Single_Adapter; 

	String str_ColumnName_Sinlge_Adapter[] = new String[] { 	
															"#",
															"Read #",
															"Direction",
															"Start Adapter",
															"End Adater",
															"Bases",
															"Sequence"
														};

	
	DefaultTableModel Obj_DTM_Paired_Adapters;
	
	String str_ColumnName_Paired_Adapter[] = new String[] { 	
															"#",
															"Read #",
															"Direction",
															"Start Adapter",
															"End Adater",
															"Bases",
															"Sequence"
														};

	DefaultTableModel Obj_DTM_Individual_Barcodes; 
	
	String str_ColumnName_Individual_Barcodes[] = new String[] { 	
														"#",
														"Read #",
														"Direction",
														"Start Barcode",
														"End Barcode",
														"Bases",
														"Sequence"
													};
	
	public static String Multiple_Barcodes_Number = "";
	public static String Multiple_Barcodes_Read_Number = "";
	public static String Multiple_Barcodes_Forward_Reverse = "";
	public static String Multiple_Barcodes_Start_Barcode = "";
	public static String Multiple_Barcodes_End_Barcode = "";
	public static String Multiple_Barcodes_Bases = "";
	public static String Multiple_Barcodes_Sequence = "";

	DefaultTableModel Obj_DTM_Multiple_Barcodes; 
	public static int Selected_Row_Number_JTable_Multiple_Barcodes = 0;
	
	String str_ColumnName_Multiplel_Barcodes[] = new String[] { 	
														"Number",
														"Read Number",
														"Direction",
														"Start Barcode",
														"End Barcode",
														"Bases",
														"Sequence"
													};
	
	DefaultTableModel Obj_DTM_Sorted_Multiple_Barcodes_Matches;
	
	String str_ColumnName_Sorted_Multiple_Barcodes_Matches[] = new String[] { 	
																			"#",
																			"Read #",
																			"Direction",
																			"Start Barcode",
																			"End Barcode",
																			"Bases",
																			"Sequence"
																		};

	DefaultTableModel Obj_DTM_Distinct_FR_Barcode_Combinations;
	
	String str_ColumnName_Distinct_FR_Barcode_Combinations[] = new String[] { 	
																			"#",
																			"Read #",
																			"Direction",
																			"Start Barcode",
																			"End Barcode",
																			"Bases",
																			"Sequence"
																		};
	
	public static String str_Input_FastQ_File_Name_Path="NA";
	public static String str_Head="Head";
	public static String str_Tail="Tail";
	
	public static String str_Forward_Sequence = "Forward";
	public static String str_Reverse_Sequence = "Reverse";
	
	private JTable JTable_Multiple_Barcodes;
	private JTable JTable_Multiple_Adapters;
	private JTable JTable_Individual_Adapters;
	private JTextField txt_Start_Bases_Length;
	private JTextField txt_End_Bases_Length;
	private JTable JTable_Sorted_Multiple_Barcodes_Matches;
	private JTable JTable_UnMatched_Reads;
	private JTable JTable_Distinct_FR_Combinations;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Match_Barcodes frame = new Match_Barcodes();
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

	public void Create_JTable_Columns_Individual_Barcodes()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Individual_Barcodes = new DefaultTableModel(str_ColumnName_Individual_Barcodes, 0);
		    JTable_Individual_Barcodes.setModel(Obj_DTM_Individual_Barcodes);
		    
		    TableColumnModel columnModel = JTable_Individual_Barcodes.getColumnModel();
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

	public void Create_JTable_Columns_Multiple_Barcodes()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Multiple_Barcodes = new DefaultTableModel(str_ColumnName_Multiplel_Barcodes, 0);
			JTable_Multiple_Barcodes.setModel(Obj_DTM_Multiple_Barcodes);

			TableColumnModel columnModel = JTable_Multiple_Barcodes.getColumnModel();
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

	public void Create_JTable_Columns_Single_Adapter()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Single_Adapter = new DefaultTableModel(str_ColumnName_Sinlge_Adapter, 0);
		    JTable_Individual_Adapters.setModel(Obj_DTM_Single_Adapter);
		    
		    TableColumnModel columnModel = JTable_Individual_Adapters.getColumnModel();
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

	public void Create_JTable_Columns_Paired_Adapters()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Paired_Adapters = new DefaultTableModel(str_ColumnName_Paired_Adapter, 0);
			JTable_Multiple_Adapters.setModel(Obj_DTM_Paired_Adapters);

			TableColumnModel columnModel = JTable_Multiple_Adapters.getColumnModel();
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

	public void Create_JTable_Distinct_FR_Barcode_Combinations()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Distinct_FR_Barcode_Combinations = new DefaultTableModel(str_ColumnName_Distinct_FR_Barcode_Combinations, 0);
			JTable_Distinct_FR_Combinations.setModel(Obj_DTM_Distinct_FR_Barcode_Combinations);

			TableColumnModel columnModel = JTable_Distinct_FR_Combinations.getColumnModel();
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

	public void Create_JTable_Columns_Sorted_Multiple_Barcodes_Matches()
	{
		try 
		{
			// create object of table model
			Obj_DTM_Sorted_Multiple_Barcodes_Matches = new DefaultTableModel(str_ColumnName_Sorted_Multiple_Barcodes_Matches, 0);
			JTable_Sorted_Multiple_Barcodes_Matches.setModel(Obj_DTM_Sorted_Multiple_Barcodes_Matches);

			TableColumnModel columnModel = JTable_Sorted_Multiple_Barcodes_Matches.getColumnModel();
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

	/**
	 * Sort_Multiple_Barcodes_Matches
	 */
	public void Sort_Multiple_Barcodes_Matches()
	{
		try
		{
			if(JTable_Multiple_Barcodes.getRowCount()>0)
			{
				
				String str_Forward_Start_Barcodes 	= rtxt_Forward_Start_Barcodes.getText().toString();
				str_Forward_Start_Barcodes = str_Forward_Start_Barcodes.replaceAll("\\s","");	// Kill spaces
				
				String str_Forward_End_Barcodes 	= rtxt_Forward_End_Barcodes.getText().toString();
				str_Forward_End_Barcodes = str_Forward_End_Barcodes.replaceAll("\\s","");
				
				String str_Reverse_Start_Barcodes 	= rtxt_Reverse_Start_Barcodes.getText().toString();
				str_Reverse_Start_Barcodes = str_Reverse_Start_Barcodes.replaceAll("\\s","");
				
				String str_Reverse_End_Barcodes 	= rtxt_Reverse_End_Barcodes.getText().toString();
				str_Reverse_End_Barcodes = str_Reverse_End_Barcodes.replaceAll("\\s","");
				
				String[] arr_Split_Forward_Start_Barcodes 	= str_Forward_Start_Barcodes.split(",");
				String[] arr_Split_Forward_End_Barcodes 	= str_Forward_End_Barcodes.split(",");
				
				String[] arr_Split_Reverse_Start_Barcodes 	= str_Reverse_Start_Barcodes.split(",");
				String[] arr_Split_Reverse_End_Barcodes 	= str_Reverse_End_Barcodes.split(",");

				
				int length_Forward_Start_Barcodes 	= arr_Split_Forward_Start_Barcodes.length;
				int length_Forward_End_Barcodes 	= arr_Split_Forward_End_Barcodes.length;
				int length_Reverse_Start_Barcodes 	= arr_Split_Reverse_Start_Barcodes.length;
				int length_Reverse_End_Barcodes 	= arr_Split_Reverse_End_Barcodes.length;
				
				int count_Combination = 0;
				int count_Combination_Forward = 0;
				int count_Combination_Reverse = 0;
				boolean flag_header_comnindation = true; 

				int int_Total_Reads_Count_for_a_Combination = 0;
				//////////////////////////////
				//Forward Barcode Combinations
				//////////////////////////////
				
					if(chk_DistinctCombination_Froward.isSelected())
					{
						for(int start_count =0; start_count < length_Forward_Start_Barcodes; start_count++)
						{
							for(int end_count =0; end_count < length_Forward_End_Barcodes; end_count++)
							{
								flag_header_comnindation = true;
								int_Total_Reads_Count_for_a_Combination = 0;
								
								for(int loopCount = 0; loopCount < JTable_Multiple_Barcodes.getRowCount(); loopCount++)
								{
									Multiple_Barcodes_Number 			= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 0).toString();
									Multiple_Barcodes_Read_Number 		= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 1).toString();
									Multiple_Barcodes_Forward_Reverse 	= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 2).toString();
									Multiple_Barcodes_Start_Barcode 	= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 3).toString();
									Multiple_Barcodes_End_Barcode 		= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 4).toString();
									Multiple_Barcodes_Bases 			= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 5).toString();
									Multiple_Barcodes_Sequence 			= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 6).toString();
									
									if( Multiple_Barcodes_Start_Barcode.equals(arr_Split_Forward_Start_Barcodes[start_count]) && Multiple_Barcodes_End_Barcode.equals(arr_Split_Forward_End_Barcodes[end_count])) 
									{
								
										if(flag_header_comnindation == true)	
										{
											count_Combination++;
											count_Combination_Forward++;
											
											Obj_DTM_Sorted_Multiple_Barcodes_Matches.addRow(new Object[] {count_Combination, 
																											"Head", 
																											"Forward",
																											arr_Split_Forward_Start_Barcodes[start_count],
																											arr_Split_Forward_End_Barcodes[end_count],
																											"",
																											""
																											});	
											flag_header_comnindation = false;
										}
										
										if(!chk_OnlyCombinationsWithoutReads.isSelected())
										{
											Obj_DTM_Sorted_Multiple_Barcodes_Matches.addRow(new Object[] {count_Combination, 
													Multiple_Barcodes_Read_Number, 
													"",
													"",
													"",
													Multiple_Barcodes_Bases,
													Multiple_Barcodes_Sequence
													});	
										}
										int_Total_Reads_Count_for_a_Combination++;
									}
								}
								

								if (flag_header_comnindation == false)
								{

									if(!chk_OnlyCombinationsWithoutReads.isSelected())
									{
										Obj_DTM_Sorted_Multiple_Barcodes_Matches.addRow(new Object[] {count_Combination, 
												"Tail", 
												"Total Reads:",
												int_Total_Reads_Count_for_a_Combination,
												"..........",
												"..........",
												".........."
												});	
										
									}
								}
							}
						}
					}	
						//////////////////////////////
						//Reverse Barcode Combinations
						//////////////////////////////
					if(chk_DistinctCombination_Reverse.isSelected())
					{
						if(chk_Match_Reverse_Sequence.isSelected())
						{
							if (chk_Reverse_Bases_Order.isSelected())
							{
								//Reversing order
								for (int start_count =0; start_count < length_Reverse_Start_Barcodes; start_count++)
								{
									arr_Split_Reverse_Start_Barcodes[start_count] = new StringBuffer(arr_Split_Reverse_Start_Barcodes[start_count]).reverse().toString();
								}
								
								//Reversing order
								for (int end_count =0; end_count < length_Reverse_End_Barcodes; end_count++)
								{
									arr_Split_Reverse_End_Barcodes[end_count] = new StringBuffer(arr_Split_Reverse_End_Barcodes[end_count]).reverse().toString();
								}
							}
							
							for(int start_count =0; start_count < length_Reverse_Start_Barcodes; start_count++)
							{
								for(int end_count =0; end_count < length_Reverse_End_Barcodes; end_count++)
								{
									flag_header_comnindation = true;
									int_Total_Reads_Count_for_a_Combination =0;
									
									for(int loopCount = 0; loopCount < JTable_Multiple_Barcodes.getRowCount(); loopCount++)
									{
										Multiple_Barcodes_Number 			= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 0).toString();
										Multiple_Barcodes_Read_Number 		= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 1).toString();
										Multiple_Barcodes_Forward_Reverse 	= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 2).toString();
										Multiple_Barcodes_Start_Barcode 	= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 3).toString();
										Multiple_Barcodes_End_Barcode 		= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 4).toString();
										Multiple_Barcodes_Bases 			= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 5).toString();
										Multiple_Barcodes_Sequence 			= JTable_Multiple_Barcodes.getModel().getValueAt(loopCount, 6).toString();
										
										if( Multiple_Barcodes_Start_Barcode.equals(arr_Split_Reverse_Start_Barcodes[start_count]) && Multiple_Barcodes_End_Barcode.equals(arr_Split_Reverse_End_Barcodes[end_count])) 
										{
									
											if(flag_header_comnindation == true)	
											{
												count_Combination++;
												count_Combination_Reverse++;
												
												Obj_DTM_Sorted_Multiple_Barcodes_Matches.addRow(new Object[] {count_Combination, 
																												"Head", 
																												"Reverse",
																												arr_Split_Reverse_Start_Barcodes[start_count],
																												arr_Split_Reverse_End_Barcodes[end_count],
																												"",
																												""
																												});
												flag_header_comnindation = false;
											}
											
											if(!chk_OnlyCombinationsWithoutReads.isSelected())
											{
												Obj_DTM_Sorted_Multiple_Barcodes_Matches.addRow(new Object[] {count_Combination, 
																												Multiple_Barcodes_Read_Number, 
																												"",
																												"",
																												"",
																												Multiple_Barcodes_Bases,
																												Multiple_Barcodes_Sequence
																												});
												
											}
											
											int_Total_Reads_Count_for_a_Combination++;
										}
									}
									if (flag_header_comnindation == false)
									{

										if(!chk_OnlyCombinationsWithoutReads.isSelected())
										{
											Obj_DTM_Sorted_Multiple_Barcodes_Matches.addRow(new Object[] {count_Combination, 
																												"Tail", 
																												"Total Reads: ", 
																												int_Total_Reads_Count_for_a_Combination,
																												"..........",
																												"..........",
																												".........."
																												});	
											
										}
										
									}
								}
													
							}
						}
					}
					
					int int_total_rows =0 ;
					
					if(!chk_OnlyCombinationsWithoutReads.isSelected())
					{
						int_total_rows = JTable_Sorted_Multiple_Barcodes_Matches.getRowCount() - (count_Combination *2);	//		
					}	
				
				lbl_Total_Sorted_Multiple_Barcode_Matches.setText(Integer.toString(int_total_rows));
				lbl_Total_Sorted_Multiple_Barcode_Combinations.setText(Integer.toString(count_Combination));

				lbl_Total_Sorted_Multiple_Barcode_Combinations_Forward.setText(Integer.toString(count_Combination_Forward));
				lbl_Total_Sorted_Multiple_Barcode_Combinations_Reverse.setText(Integer.toString(count_Combination_Reverse));
				
				Obj_DTM_Sorted_Multiple_Barcodes_Matches.addRow(new Object[] {	"Reads Matched=" + Integer.toString(int_total_rows), 
																				"", 
																				"Combinations=" + Integer.toString(count_Combination),
																				"",
																				"Forward=" +Integer.toString(count_Combination_Forward),
																				"",
																				"Reverse=" + Integer.toString(count_Combination_Reverse),
																				});
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Get_Distinct_FR_Barcode_Combinations
	 */
	public void Get_Common_FR_Barcode_Combinations()
	{
		try
		{
			for(int upper_row_count =0; upper_row_count < JTable_Sorted_Multiple_Barcodes_Matches.getRowCount(); upper_row_count++)
			{
				Multiple_Barcodes_Number 			= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(upper_row_count, 0).toString();
				Multiple_Barcodes_Read_Number 		= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(upper_row_count, 1).toString();
				Multiple_Barcodes_Forward_Reverse 	= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(upper_row_count, 2).toString();
				Multiple_Barcodes_Start_Barcode 	= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(upper_row_count, 3).toString();
				Multiple_Barcodes_End_Barcode 		= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(upper_row_count, 4).toString();
				Multiple_Barcodes_Bases 			= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(upper_row_count, 5).toString();
				Multiple_Barcodes_Sequence 			= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(upper_row_count, 6).toString();
				
				if(Multiple_Barcodes_Read_Number.equals(str_Head))
				{
					for(int inner_row_count = upper_row_count +1; inner_row_count < JTable_Sorted_Multiple_Barcodes_Matches.getRowCount(); inner_row_count++)
					{
						String inner_Multiple_Barcodes_Number 			= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(inner_row_count, 0).toString();
						String inner_Multiple_Barcodes_Read_Number 		= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(inner_row_count, 1).toString();
						String inner_Multiple_Barcodes_Forward_Reverse 	= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(inner_row_count, 2).toString();
						String inner_Multiple_Barcodes_Start_Barcode 	= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(inner_row_count, 3).toString();
						String inner_Multiple_Barcodes_End_Barcode 		= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(inner_row_count, 4).toString();
						String inner_Multiple_Barcodes_Bases 			= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(inner_row_count, 5).toString();
						String inner_Multiple_Barcodes_Sequence 		= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(inner_row_count, 6).toString();
						
						if(inner_Multiple_Barcodes_Read_Number.equals(str_Head))
						{
							if(Multiple_Barcodes_Start_Barcode.equals(inner_Multiple_Barcodes_Start_Barcode) && Multiple_Barcodes_End_Barcode.equals(inner_Multiple_Barcodes_End_Barcode) && !Multiple_Barcodes_Forward_Reverse.equals(inner_Multiple_Barcodes_Forward_Reverse))
							{
								Obj_DTM_Distinct_FR_Barcode_Combinations.addRow(new Object[] {Multiple_Barcodes_Number,
																									Multiple_Barcodes_Read_Number,
																									Multiple_Barcodes_Forward_Reverse + " : "+ inner_Multiple_Barcodes_Forward_Reverse,
																									Multiple_Barcodes_Start_Barcode,
																									Multiple_Barcodes_End_Barcode,
																									"",
																									""
																									});	
							}
							
						}
						
					}
					
				}
				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Get_Disticnt_FR_Barcode_Combinations
	 */
	public void Get_Disticnt_FR_Barcode_Combinations()
	{
		try
		{
			for(int row_count =0; row_count < JTable_Sorted_Multiple_Barcodes_Matches.getRowCount()-1; row_count++)	//Minus in loop is to skip last row
			{
				Multiple_Barcodes_Number 			= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(row_count, 0).toString();
				Multiple_Barcodes_Read_Number 		= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(row_count, 1).toString();
				Multiple_Barcodes_Forward_Reverse 	= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(row_count, 2).toString();
				Multiple_Barcodes_Start_Barcode 	= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(row_count, 3).toString();
				Multiple_Barcodes_End_Barcode 		= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(row_count, 4).toString();
				Multiple_Barcodes_Bases 			= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(row_count, 5).toString();
				Multiple_Barcodes_Sequence 			= JTable_Sorted_Multiple_Barcodes_Matches.getModel().getValueAt(row_count, 6).toString();
				
				if(Multiple_Barcodes_Read_Number.equals(str_Head) || Multiple_Barcodes_Read_Number.equals(str_Tail))
				{
						//skip
				}
				else
				{
					Obj_DTM_Distinct_FR_Barcode_Combinations.addRow(new Object[] {Multiple_Barcodes_Number,
							Multiple_Barcodes_Read_Number,
							Multiple_Barcodes_Forward_Reverse,
							Multiple_Barcodes_Start_Barcode,
							Multiple_Barcodes_End_Barcode,
							Multiple_Barcodes_Bases,
							Multiple_Barcodes_Sequence
							});
				}
				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Find_False_Positive_Reads
	 */
	public void Find_False_Positive_Reads()
	{
		try
		{
			int b_count =  JTable_Multiple_Barcodes.getRowCount();
			JOptionPane.showMessageDialog(null, "Matched # " +  b_count , "False-Positive", JOptionPane.PLAIN_MESSAGE);

			int u_count  = JTable_UnMatched_Reads.getRowCount();
			JOptionPane.showMessageDialog(null, "Un-Matched # " +  u_count , "False-Positive", JOptionPane.PLAIN_MESSAGE);
			
			boolean flag = true; 
			
			for(int barcode_count=0 ; barcode_count < b_count; barcode_count++)
			{
				Multiple_Barcodes_Number 			= JTable_Multiple_Barcodes.getModel().getValueAt(barcode_count, 0).toString();
				Multiple_Barcodes_Read_Number 		= JTable_Multiple_Barcodes.getModel().getValueAt(barcode_count, 1).toString();
				Multiple_Barcodes_Forward_Reverse 	= JTable_Multiple_Barcodes.getModel().getValueAt(barcode_count, 2).toString();
				Multiple_Barcodes_Start_Barcode 	= JTable_Multiple_Barcodes.getModel().getValueAt(barcode_count, 3).toString();
				Multiple_Barcodes_End_Barcode 		= JTable_Multiple_Barcodes.getModel().getValueAt(barcode_count, 4).toString();
				Multiple_Barcodes_Bases 			= JTable_Multiple_Barcodes.getModel().getValueAt(barcode_count, 5).toString();
				Multiple_Barcodes_Sequence 			= JTable_Multiple_Barcodes.getModel().getValueAt(barcode_count, 6).toString();
			
				for(int unmatched_count=0 ; unmatched_count < u_count; unmatched_count++)
				{
					String Unmatched_Number 		= JTable_UnMatched_Reads.getModel().getValueAt(unmatched_count, 0).toString();
					String Unmatched_Reads_Number 	= JTable_UnMatched_Reads.getModel().getValueAt(unmatched_count, 1).toString();
					String Unmatched_Reads_Bases 	= JTable_UnMatched_Reads.getModel().getValueAt(unmatched_count, 2).toString();
					String Unmatched_Reads_Sequence = JTable_Multiple_Barcodes.getModel().getValueAt(barcode_count, 3).toString();

					//if(Multiple_Barcodes_Sequence.equals(Unmatched_Reads_Sequence))
					if(Multiple_Barcodes_Read_Number.equals(Unmatched_Reads_Number) && Multiple_Barcodes_Bases.equals(Unmatched_Reads_Bases))
					{
						//JOptionPane.showMessageDialog(null, "Read # " + Multiple_Barcodes_Read_Number + "Bases # " + Multiple_Barcodes_Bases, "False-Positive", JOptionPane.PLAIN_MESSAGE);
						System.out.println("Read# " + Multiple_Barcodes_Read_Number + " Bases# " + Multiple_Barcodes_Bases + " Barcode_line# " + Multiple_Barcodes_Number + " UnMatch_line# " + Unmatched_Number );
						flag = false;
					}
				}
			}
			
			if(flag==true)
			{
				JOptionPane.showMessageDialog(null, "No False Positive Read Found", "False-Positive", JOptionPane.PLAIN_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void Find_Positive_Positive_Reads()
	{
		try
		{
			int b_count =  JTable_Multiple_Barcodes.getRowCount();
			JOptionPane.showMessageDialog(null, "Matched # " +  b_count , "False-Positive", JOptionPane.PLAIN_MESSAGE);
			
			boolean flag = true;
			
			for(int forward_count=0 ; forward_count < b_count; forward_count++)
			{
				boolean positive_positive_falg = false;

				Multiple_Barcodes_Number 			= JTable_Multiple_Barcodes.getModel().getValueAt(forward_count, 0).toString();
				Multiple_Barcodes_Read_Number 		= JTable_Multiple_Barcodes.getModel().getValueAt(forward_count, 1).toString();
				Multiple_Barcodes_Forward_Reverse 	= JTable_Multiple_Barcodes.getModel().getValueAt(forward_count, 2).toString();
				Multiple_Barcodes_Start_Barcode 	= JTable_Multiple_Barcodes.getModel().getValueAt(forward_count, 3).toString();
				Multiple_Barcodes_End_Barcode 		= JTable_Multiple_Barcodes.getModel().getValueAt(forward_count, 4).toString();
				Multiple_Barcodes_Bases 			= JTable_Multiple_Barcodes.getModel().getValueAt(forward_count, 5).toString();
				Multiple_Barcodes_Sequence 			= JTable_Multiple_Barcodes.getModel().getValueAt(forward_count, 6).toString();
			
				for(int reverse_count=0 ; reverse_count < b_count; reverse_count++)
				{
					String Rev_Number = JTable_Multiple_Barcodes.getModel().getValueAt(reverse_count, 0).toString();
					String Rev_Reads_Number = JTable_Multiple_Barcodes.getModel().getValueAt(reverse_count, 1).toString();
					String Rev_Reads_Forward_Reverse = JTable_Multiple_Barcodes.getModel().getValueAt(reverse_count, 2).toString();
					
					if(Multiple_Barcodes_Read_Number.equals(Rev_Reads_Number))
					{
						if (positive_positive_falg == true)
						{
							JOptionPane.showMessageDialog(null, "Read# " + Multiple_Barcodes_Read_Number + "-" +  Rev_Reads_Number + "\n"+ 
																" line# " + Multiple_Barcodes_Number + "-" + Rev_Number + "\n"+
																" Direction"+ Multiple_Barcodes_Forward_Reverse + "-" + Rev_Reads_Forward_Reverse , 
																"False-Positive", JOptionPane.PLAIN_MESSAGE);
							
							System.out.println("Read# " + Multiple_Barcodes_Read_Number + "-" +  Rev_Reads_Number +
									" line# " + Multiple_Barcodes_Number + "-" + Rev_Number + 
									" Direction: "+ Multiple_Barcodes_Forward_Reverse + "-" + Rev_Reads_Forward_Reverse );
						}

						positive_positive_falg = true;
						flag = false;
					}
				}
			
			}
			
			if(flag==true)
			{
				JOptionPane.showMessageDialog(null, "No Positive + Read Found", "False-Positive", JOptionPane.PLAIN_MESSAGE);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
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
		 		Create_JTable_Columns_Individual_Barcodes();
		 		Create_JTable_Columns_Multiple_Barcodes();
		 		Create_JTable_Columns_Single_Adapter();
		 		Create_JTable_Columns_Paired_Adapters();
		 		Create_JTable_Columns_Sorted_Multiple_Barcodes_Matches();
		 		Create_JTable_Distinct_FR_Barcode_Combinations();
		 		
				FileReader reader = new FileReader(str_File_Name_Path);
				BufferedReader bufferedReader = new BufferedReader(reader);
			
				////////////////////////////////////////////
				String str_Forward_Start_Adapters 	= rtxt_Forward_Start_Adapters.getText().toString();
				String str_Forward_End_Adapters 	= rtxt_Forward_End_Adapters.getText().toString();
				String str_Reverse_Start_Adapters 	= rtxt_Reverse_Start_Adapters.getText().toString();
				String str_Reverse_End_Adapters 	= rtxt_Reverse_End_Adapters.getText().toString();
				
				//Reverse Order
				if (chk_Reverse_Bases_Order.isSelected())
				{
					str_Reverse_Start_Adapters = new StringBuffer(str_Reverse_Start_Adapters).reverse().toString();
					str_Reverse_End_Adapters = new StringBuffer(str_Reverse_End_Adapters).reverse().toString();
				}
				
				String str_Forward_Start_Barcodes 	= rtxt_Forward_Start_Barcodes.getText().toString();
				str_Forward_Start_Barcodes = str_Forward_Start_Barcodes.replaceAll("\\s","");	// Kill spaces
				
				String str_Forward_End_Barcodes 	= rtxt_Forward_End_Barcodes.getText().toString();
				str_Forward_End_Barcodes = str_Forward_End_Barcodes.replaceAll("\\s","");
				
				String str_Reverse_Start_Barcodes 	= rtxt_Reverse_Start_Barcodes.getText().toString();
				str_Reverse_Start_Barcodes = str_Reverse_Start_Barcodes.replaceAll("\\s","");
				
				String str_Reverse_End_Barcodes 	= rtxt_Reverse_End_Barcodes.getText().toString();
				str_Reverse_End_Barcodes = str_Reverse_End_Barcodes.replaceAll("\\s","");
				
				String[] arr_Split_Forward_Start_Barcodes 	= str_Forward_Start_Barcodes.split(",");
				String[] arr_Split_Forward_End_Barcodes 	= str_Forward_End_Barcodes.split(",");
				
				String[] arr_Split_Reverse_Start_Barcodes 	= str_Reverse_Start_Barcodes.split(",");
				String[] arr_Split_Reverse_End_Barcodes 	= str_Reverse_End_Barcodes.split(",");

				int length_Forward_Start_Barcodes 	= arr_Split_Forward_Start_Barcodes.length;
				int length_Forward_End_Barcodes 	= arr_Split_Forward_End_Barcodes.length;
				int length_Reverse_Start_Barcodes 	= arr_Split_Reverse_Start_Barcodes.length;
				int length_Reverse_End_Barcodes 	= arr_Split_Reverse_End_Barcodes.length;


				////////////////////////////////////////////
				
				int int_FastQ_FileLine_Count = 0;
				int int_FastQ_Read_Count = 0;
				
				int int_UnMatched_Reads_Count = 0;
				boolean flag_UnMatched_Reads_Count = true;
				
				int int_Forward_Start_Adapter_count = 0;
				int int_Forward_End_Adapter_count = 0;
				int int_Reverse_Start_Adapter_count = 0;
				int int_Reverse_End_Adapter_count = 0;
				
				int int_Forward_Start_Barcode_count = 0;
				int int_Forward_End_Barcode_count = 0;
				int int_Reverse_Start_Barcode_count = 0;
				int int_Reverse_End_Barcode_count = 0;

				
				int int_Bases_Count_Pointer =2;
				
				int int_JTable_Individual_Adapter_Lines_Count =0;
				int int_JTable_Multiple_Adapter_Lines_Count =0;
				
				int int_JTable_Individual_Barcode_Lines_Count =0;
				int int_JTable_Multiple_Barcode_Lines_Count =0;
				
				while((str_Lines = bufferedReader.readLine())!=null)
				{
					int_FastQ_FileLine_Count++;
					String str_TotalNumberOfBases = Integer.toString(str_Lines.length());
					
					if(int_FastQ_FileLine_Count==int_Bases_Count_Pointer)
					{
						int_FastQ_Read_Count++;
						
						
						String str_Start_Sub_String = str_Lines;
						String str_End_Sub_String= str_Lines;	

						//Check if need to match complete sequence or only some bases from start and end of sequence
						if(chk_Pick_Number_of_Bases.isSelected())
						{
							if(!txt_Start_Bases_Length.getText().equals("") && !txt_End_Bases_Length.getText().equals(""))
							{
								int int_start_bases = Integer.parseInt(txt_Start_Bases_Length.getText());
								int int_end_bases = Integer.parseInt(txt_Start_Bases_Length.getText());
								
								if(str_Lines.length() > int_start_bases)
								{
									 str_Start_Sub_String = str_Lines.subSequence(0, int_end_bases).toString();
								}

								if(str_Lines.length() > int_end_bases)
								{
									int int_sub =str_Lines.length() - int_end_bases;
									 str_End_Sub_String =  str_Lines.subSequence(int_sub, str_Lines.length()).toString();	
								}	
								
							}
							else
							{
								JOptionPane.showMessageDialog(null, "In correct value of Start/End Bases", "Missing Data", JOptionPane.PLAIN_MESSAGE);

							}
							
						}


						/////////////////// ADAPTERS ////////////////////
						if(str_Start_Sub_String.contains(str_Forward_Start_Adapters))
						//if(str_Lines.contains(str_Forward_Start_Adapters))
						{
							int_Forward_Start_Adapter_count++;
							int_JTable_Individual_Adapter_Lines_Count++;
							Obj_DTM_Single_Adapter.addRow(new Object[] {Integer.toString(int_JTable_Individual_Adapter_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence ,  str_Forward_Start_Adapters, "NA", str_TotalNumberOfBases, str_Lines});
							
							if(str_End_Sub_String.contains(str_Forward_End_Adapters))
							{
								int_JTable_Multiple_Adapter_Lines_Count++;
								Obj_DTM_Paired_Adapters.addRow(new Object[] {Integer.toString(int_JTable_Multiple_Adapter_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence ,  str_Forward_Start_Adapters, str_Forward_End_Adapters, str_TotalNumberOfBases, str_Lines});							

							}

						}

						if(str_End_Sub_String.contains(str_Forward_End_Adapters))
						//if(str_Lines.contains(str_Forward_End_Adapters))
						{
							int_Forward_End_Adapter_count++;
							int_JTable_Individual_Adapter_Lines_Count++;
							Obj_DTM_Single_Adapter.addRow(new Object[] {Integer.toString(int_JTable_Individual_Adapter_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence ,  "NA", str_Forward_End_Adapters, str_TotalNumberOfBases, str_Lines});							
						}

						
						
						//Check if it is required to match in reverse order, paired sequences
						if(chk_Match_Reverse_Sequence.isSelected())
						{
							if(str_Start_Sub_String.contains(str_Reverse_Start_Adapters))
							{
								int_Reverse_Start_Adapter_count++;
								int_JTable_Individual_Adapter_Lines_Count++;
								Obj_DTM_Single_Adapter.addRow(new Object[] {Integer.toString(int_JTable_Individual_Adapter_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence ,  str_Reverse_Start_Adapters, "NA", str_TotalNumberOfBases, str_Lines});

								if(str_End_Sub_String.contains(str_Reverse_End_Adapters))
								{
									int_JTable_Multiple_Adapter_Lines_Count++;
									Obj_DTM_Paired_Adapters.addRow(new Object[] {Integer.toString(int_JTable_Multiple_Adapter_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence ,  str_Reverse_Start_Adapters, str_Reverse_End_Adapters, str_TotalNumberOfBases, str_Lines});							

								}
								
							}
							
							if(str_End_Sub_String.contains(str_Reverse_End_Adapters))
							{
								int_Reverse_End_Adapter_count++;
								int_JTable_Individual_Adapter_Lines_Count++;
								Obj_DTM_Single_Adapter.addRow(new Object[] {Integer.toString(int_JTable_Individual_Adapter_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence ,  "NA", str_Reverse_End_Adapters, str_TotalNumberOfBases, str_Lines});
						
							}
							
						}

						/////////////////// BARCODES ////////////////////
						///////////////////	
						//Forwards - Start
						///////////////////
						for(int count =0; count < length_Forward_Start_Barcodes; count++)
						{
							//if( str_Lines.contains(arr_Split_Forward_Start_Barcodes[count]))
							if( str_Start_Sub_String.contains(arr_Split_Forward_Start_Barcodes[count]))
							{
								int_Forward_Start_Barcode_count++;
								int_JTable_Individual_Barcode_Lines_Count++;
								
								Obj_DTM_Individual_Barcodes.addRow(new Object[] {Integer.toString(int_JTable_Individual_Barcode_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence ,  arr_Split_Forward_Start_Barcodes[count], "NA", str_TotalNumberOfBases, str_Lines});
								
								for(int end_count =0; end_count < length_Forward_End_Barcodes; end_count++)
								{
									//if(str_Lines.contains(arr_Split_Forward_End_Barcodes[end_count]))
									if(str_End_Sub_String.contains(arr_Split_Forward_End_Barcodes[end_count]))
									{
										int_JTable_Multiple_Barcode_Lines_Count++;
										Obj_DTM_Multiple_Barcodes.addRow(new Object[] {Integer.toString(int_JTable_Multiple_Barcode_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence,  arr_Split_Forward_Start_Barcodes[count], arr_Split_Forward_End_Barcodes[end_count], str_TotalNumberOfBases, str_Lines});
										flag_UnMatched_Reads_Count = false;
									}
								}

							}
						}
						
						
						//Forwards - End
						for(int count =0; count < length_Forward_End_Barcodes; count++)
						{
							//if( str_Lines.contains(arr_Split_Forward_End_Barcodes[count]))
							if(str_End_Sub_String.contains(arr_Split_Forward_End_Barcodes[count]))
							{
								int_Forward_End_Barcode_count++;
								int_JTable_Individual_Barcode_Lines_Count++;
								
								Obj_DTM_Individual_Barcodes.addRow(new Object[] {Integer.toString(int_JTable_Individual_Barcode_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Forward_Sequence,  "NA", arr_Split_Forward_End_Barcodes[count], str_TotalNumberOfBases, str_Lines});
							}
						}

						/////////////////////////
						// Reverse Sequence Match
						/////////////////////////
						if(chk_Match_Reverse_Sequence.isSelected())
						{
							if (chk_Reverse_Bases_Order.isSelected())
							{
								//Reversing order
								for (int start_count =0; start_count < length_Reverse_Start_Barcodes; start_count++)
								{
									arr_Split_Reverse_Start_Barcodes[start_count] = new StringBuffer(arr_Split_Reverse_Start_Barcodes[start_count]).reverse().toString();
								}
								
								//Reversing order
								for (int end_count =0; end_count < length_Reverse_End_Barcodes; end_count++)
								{
									arr_Split_Reverse_End_Barcodes[end_count] = new StringBuffer(arr_Split_Reverse_End_Barcodes[end_count]).reverse().toString();
								}
							}

							
							//Reverse - Start
							for(int count =0; count < length_Reverse_Start_Barcodes; count++)
							{
								if( str_Start_Sub_String.contains(arr_Split_Reverse_Start_Barcodes[count]))
								{
									int_Reverse_Start_Barcode_count++;
									int_JTable_Individual_Barcode_Lines_Count++;
									
									Obj_DTM_Individual_Barcodes.addRow(new Object[] {Integer.toString(int_JTable_Individual_Barcode_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence,  arr_Split_Reverse_Start_Barcodes[count], "NA", str_TotalNumberOfBases, str_Lines});
									
									for(int end_count =0; end_count < length_Reverse_End_Barcodes; end_count++)
									{
										if(str_End_Sub_String.contains(arr_Split_Reverse_End_Barcodes[end_count]))
										{
											int_JTable_Multiple_Barcode_Lines_Count++;
											Obj_DTM_Multiple_Barcodes.addRow(new Object[] {Integer.toString(int_JTable_Multiple_Barcode_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence, arr_Split_Reverse_Start_Barcodes[count], arr_Split_Reverse_End_Barcodes[end_count], str_TotalNumberOfBases, str_Lines});
											
											//To find positive matches
											if(flag_UnMatched_Reads_Count == false)
											{
												//System.out.println(int_FastQ_Read_Count);
												String msg = "Read Duplicated: " + Integer.toString(int_FastQ_Read_Count);
												textArea_FalsePositive.append(msg);
											}
											
											flag_UnMatched_Reads_Count = false;
											
										}
									}

								}
							}

							//Reverse - End
							for(int count =0; count < length_Reverse_End_Barcodes; count++)
							{

								if( str_End_Sub_String.contains(arr_Split_Reverse_End_Barcodes[count]))
								{
									int_Reverse_End_Barcode_count++;
									int_JTable_Individual_Barcode_Lines_Count++;
									
									Obj_DTM_Individual_Barcodes.addRow(new Object[] {Integer.toString(int_JTable_Individual_Barcode_Lines_Count), Integer.toString(int_FastQ_Read_Count), str_Reverse_Sequence,  "NA", arr_Split_Reverse_End_Barcodes[count], str_TotalNumberOfBases, str_Lines});
								}
							}

						}
						
						if(flag_UnMatched_Reads_Count == true)
						{
							
							//if(Integer.parseInt(str_TotalNumberOfBases) >= 5000)
							{
								int_UnMatched_Reads_Count++;
								Obj_DTM_UnMatched_Reads.addRow(new Object[] {Integer.toString(int_UnMatched_Reads_Count), Integer.toString(int_FastQ_Read_Count), str_TotalNumberOfBases, str_Lines});	
							}
							
						}
						
						flag_UnMatched_Reads_Count = true;
						
						int_Bases_Count_Pointer = int_Bases_Count_Pointer +4;
					}
					
		        }
				
				int int_Reads_count = int_FastQ_FileLine_Count/4;
				
				lbl_Count_Reads.setText( Integer.toString(int_Reads_count));
				lbl_Count_Forward_Start_Adapters.setText( Integer.toString(int_Forward_Start_Adapter_count));
				lbl_Count_Forward_End_Adapters.setText( Integer.toString(int_Forward_End_Adapter_count));
				lbl_Count_Reverse_Start_Adapters.setText( Integer.toString(int_Reverse_Start_Adapter_count));
				lbl_Count_Reverse_End_Adapters.setText( Integer.toString(int_Reverse_End_Adapter_count));
				
				lbl_Count_Forward_Start_Barcodes.setText( Integer.toString(int_Forward_Start_Barcode_count));
				lbl_Count_Forward_End_Barcodes.setText( Integer.toString(int_Forward_End_Barcode_count));
				lbl_Count_Reverse_Start_Barcodes.setText( Integer.toString(int_Reverse_Start_Barcode_count));
				lbl_Count_Reverse_End_Barcodes.setText( Integer.toString(int_Reverse_End_Barcode_count));

				
				lbl_Total_Individual_Barcode_Matches.setText(Integer.toString(int_JTable_Individual_Barcode_Lines_Count));
				lbl_Total_Multiple_Barcode_Matches.setText(Integer.toString(int_JTable_Multiple_Barcode_Lines_Count));
				
				lbl_Total_Individual_Adapter_Matches.setText(Integer.toString(int_JTable_Individual_Adapter_Lines_Count));
				lbl_Total_Multiple_Adapter_Matches.setText(Integer.toString(int_JTable_Multiple_Adapter_Lines_Count));

				lbl_Total_UnMatched_Reads.setText(Integer.toString(int_UnMatched_Reads_Count));
				
				//JOptionPane.showMessageDialog(null, "Reads: " + int_Reads_count , "Information" ,JOptionPane.PLAIN_MESSAGE);
				
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
	public Match_Barcodes() {
		setBackground(new Color(255, 255, 255));
		setTitle("Match Barcodes");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 1440, 1003);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel_Reverse_Adapters_Barcodes = new JPanel();
		panel_Reverse_Adapters_Barcodes.setBackground(new Color(192, 192, 192));
		
		JPanel panel_Tabs = new JPanel();
		panel_Tabs.setBackground(Color.WHITE);
		
		JButton btn_GetCount = new JButton("Count Input Adatpers & Barcodes");
		btn_GetCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String str_Forward_Start_Adapters 	= rtxt_Forward_Start_Adapters.getText().toString();
					String str_Forward_End_Adapters 	= rtxt_Forward_End_Adapters.getText().toString();
					String str_Reverse_Start_Adapters 	= rtxt_Reverse_Start_Adapters.getText().toString();
					String str_Reverse_End_Adapters 	= rtxt_Reverse_End_Adapters.getText().toString();
					
					
					String str_Forward_Start_Barcodes 	= rtxt_Forward_Start_Barcodes.getText().toString();
					String str_Forward_End_Barcodes 	= rtxt_Forward_End_Barcodes.getText().toString();
					String str_Reverse_Start_Barcodes 	= rtxt_Reverse_Start_Barcodes.getText().toString();
					String str_Reverse_End_Barcodes 	= rtxt_Reverse_End_Barcodes.getText().toString();
					
					
					if(!str_Forward_Start_Adapters.equals("") && !str_Forward_End_Adapters.equals("") )
					{
						String[] arr_Split_Forward_Start_Adapters 	= str_Forward_Start_Adapters.split(",");
						String[] arr_Split_Forward_End_Adapters 	= str_Forward_End_Adapters.split(",");
						
						int length_Forward_Start_Adapters 	= arr_Split_Forward_Start_Adapters.length;
						int length_Forward_End_Adapters 	= arr_Split_Forward_End_Adapters.length;
						
						lbl_Forward_Start_Adapters.setText(Integer.toString(length_Forward_Start_Adapters));
						lbl_Forward_End_Adapters.setText(Integer.toString(length_Forward_End_Adapters));
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Forward Adapter Length Mismatches", "Missing Data", JOptionPane.PLAIN_MESSAGE);
					}
					
					if(!str_Reverse_Start_Adapters.equals("") && !str_Reverse_End_Adapters.equals("") )
					{
						String[] arr_Split_Reverse_Start_Adapters 	= str_Reverse_Start_Adapters.split(",");
						String[] arr_Split_Reverse_End_Adapters 	= str_Reverse_End_Adapters.split(",");
						
						int length_Reverse_Start_Adapters 	= arr_Split_Reverse_Start_Adapters.length;
						int length_Reverse_End_Adapters 	= arr_Split_Reverse_End_Adapters.length;

						lbl_Reverse_End_Adapter.setText(Integer.toString(length_Reverse_Start_Adapters));
						lbl_Reverse_Start_Adapter.setText(Integer.toString(length_Reverse_End_Adapters));

					}
					else
					{
						JOptionPane.showMessageDialog(null, "Reverse Adapter Length Mismatches", "Missing Data", JOptionPane.PLAIN_MESSAGE);
					}
					
					if(!str_Forward_Start_Barcodes.equals("") && !str_Forward_End_Barcodes.equals("") )
					{
						String[] arr_Split_Forward_Start_Barcodes 	= str_Forward_Start_Barcodes.split(",");
						String[] arr_Split_Forward_End_Barcodes 	= str_Forward_End_Barcodes.split(",");
						
						int length_Forward_Start_Barcodes 	= arr_Split_Forward_Start_Barcodes.length;
						int length_Forward_End_Barcodes 	= arr_Split_Forward_End_Barcodes.length;

						lbl_Forward_Start_Barcodes.setText(Integer.toString(length_Forward_Start_Barcodes));
						lbl_Forward_End_Barcodes.setText(Integer.toString(length_Forward_End_Barcodes));

					}
					else
					{
						JOptionPane.showMessageDialog(null, "Forwards Barcodes Length Mismatches", "Missing Data", JOptionPane.PLAIN_MESSAGE);
					}
					
					if(!str_Reverse_Start_Barcodes.equals("") && !str_Reverse_End_Barcodes.equals("") )
					{
						String[] arr_Split_Reverse_Start_Barcodes 	= str_Reverse_Start_Barcodes.split(",");
						String[] arr_Split_Reverse_End_Barcodes 	= str_Reverse_End_Barcodes.split(",");
						
						int length_Reverse_Start_Barcodes 	= arr_Split_Reverse_Start_Barcodes.length;
						int length_Reverse_End_Barcodes 	= arr_Split_Reverse_End_Barcodes.length;

						
						lbl_Reverse_End_Barcode.setText(Integer.toString(length_Reverse_Start_Barcodes));
						lbl_Reverse_Start_Barcode.setText(Integer.toString(length_Reverse_End_Barcodes));

					}
					else
					{
						JOptionPane.showMessageDialog(null, "Reverse Barcodes Length Mismatches", "Missing Data", JOptionPane.PLAIN_MESSAGE);
					}

				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		JButton btn_FastQ_File = new JButton("Load FastQ File");
		btn_FastQ_File.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
	  		 		{
		  		 		String str_Lines = "";

		  		 		JFileChooser fileChooser = new JFileChooser();
		  		 		int returnValue = fileChooser.showOpenDialog(null);			
		  		 	
		  		 		if (returnValue == JFileChooser.APPROVE_OPTION) 
		  		 		{
		  		 			// Return Path to OPEN FILE
							str_Input_FastQ_File_Name_Path = fileChooser.getSelectedFile().toString();
							lbl_FastQ_File_Path_Name.setText(str_Input_FastQ_File_Name_Path);
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
		
		JButton btn_Start_Analysis = new JButton("Start Analysis");
		btn_Start_Analysis.setForeground(new Color(0, 0, 153));
		btn_Start_Analysis.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btn_Start_Analysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(!str_Input_FastQ_File_Name_Path.equals("NA"))
					{
						Start_Analysis(str_Input_FastQ_File_Name_Path);
					}
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		lbl_Count_Reads = new JLabel("...");
		lbl_Count_Reads.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lbl_Count_Reads.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_8 = new JLabel("Total Number of Reads:");
		lblNewLabel_8.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_8.setForeground(Color.BLUE);
		
		JPanel panel_Options = new JPanel();
		panel_Options.setBackground(new Color(255, 255, 255));
		
		chk_Reverse_Bases_Order = new JCheckBox("Reverse Bases Order");
		chk_Reverse_Bases_Order.setToolTipText("Reverse Bases Order");
		
		chk_Match_Reverse_Sequence = new JCheckBox("Match Reverse Sequence as welll.");
		chk_Match_Reverse_Sequence.setSelected(true);
		
		chk_Pick_Number_of_Bases = new JCheckBox("Start & End Bases #");
		chk_Pick_Number_of_Bases.setSelected(true);
		
		JLabel lblNewLabel_20 = new JLabel("Bases Start");
		
		txt_Start_Bases_Length = new JTextField();
		txt_Start_Bases_Length.setText("40");
		txt_Start_Bases_Length.setColumns(10);
		
		JLabel lblNewLabel_22 = new JLabel("End");
		
		txt_End_Bases_Length = new JTextField();
		txt_End_Bases_Length.setText("40");
		txt_End_Bases_Length.setColumns(10);
		GroupLayout gl_panel_Options = new GroupLayout(panel_Options);
		gl_panel_Options.setHorizontalGroup(
			gl_panel_Options.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Options.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Options.createParallelGroup(Alignment.LEADING)
						.addComponent(chk_Reverse_Bases_Order)
						.addComponent(chk_Match_Reverse_Sequence)
						.addComponent(chk_Pick_Number_of_Bases)
						.addGroup(gl_panel_Options.createSequentialGroup()
							.addComponent(lblNewLabel_20)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txt_Start_Bases_Length, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_22)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txt_End_Bases_Length, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_panel_Options.setVerticalGroup(
			gl_panel_Options.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Options.createSequentialGroup()
					.addComponent(chk_Match_Reverse_Sequence)
					.addGap(12)
					.addComponent(chk_Reverse_Bases_Order)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chk_Pick_Number_of_Bases)
					.addGap(18)
					.addGroup(gl_panel_Options.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_20)
						.addComponent(txt_Start_Bases_Length, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_22)
						.addComponent(txt_End_Bases_Length, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(7, Short.MAX_VALUE))
		);
		panel_Options.setLayout(gl_panel_Options);
		
		JButton btnNewButton = new JButton("Postive +");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Multiple_Barcodes.getRowCount() > 0)
					{
						Find_Positive_Positive_Reads();
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton btn_FalsePositiveReads = new JButton("False Positive");
		btn_FalsePositiveReads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Multiple_Barcodes.getRowCount() > 0 && JTable_UnMatched_Reads.getRowCount() > 0)
					{
						Find_False_Positive_Reads();
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Default Barcodes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rtxt_Forward_Start_Barcodes.setText("CCTAGAGT, CTATTAAG, TAGATCGC, CTCTCTAT, TATCCTCT, AGAGTAGA, GTAAGGAG, ACTGCATA, AAGGAGTA, CTAAGCCT, TCGACTAG, TTCTAGCT, GCGTAAGA, AAGGCTAT, GAGCCTTA, TTATGCGA ");
				rtxt_Forward_End_Barcodes.setText("CGTACTAG, TAAGGCGA, AGGCAGAA, TCCTGAGC, GGACTCCT, TAGGCATG, CTCTCTAC, CAGAGAGG, GCTACGCT, CGAGGCTG, AAGAGGCA, GTAGAGGA, GCTCATGA, ATCTCAGG");
				rtxt_Reverse_Start_Barcodes.setText("TCGCCTTA, CTAGTACG, TTCTGCCT, GCTCAGGA, AGGAGTCC, CATGCCTA, GTAGAGAG, CCTCTCTG, AGCGTAGC, CAGCCTCG, TGCCTCTT, TCCTCTAC, TCATGAGC, CCTGAGAT");
				rtxt_Reverse_End_Barcodes.setText("GCGATCTA, ATAGAGAG, AGAGGATA, TCTACTCT, CTCCTTAC, TATGCAGT, TACTCCTT, AGGCTTAG, CTAGTCGA, AGCTAGAA, ACTCTAGG, TCTTACGC, CTTAATAG, ATAGCCTT, TAAGGCTC, TCGCATAA");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_Tabs, GroupLayout.DEFAULT_SIZE, 1418, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(panel_Forward_Adapters_Barcodes, GroupLayout.PREFERRED_SIZE, 543, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panel_Reverse_Adapters_Barcodes, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btn_FastQ_File)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lbl_FastQ_File_Path_Name, GroupLayout.PREFERRED_SIZE, 971, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_8)
									.addGap(12)
									.addComponent(lbl_Count_Reads))
								.addComponent(btn_Start_Analysis, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
								.addComponent(panel_Options, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn_GetCount, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addComponent(btn_FalsePositiveReads, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 142, Short.MAX_VALUE)
										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_Forward_Adapters_Barcodes, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btn_FastQ_File)
								.addComponent(lbl_FastQ_File_Path_Name))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(panel_Options, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btn_GetCount)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(btn_FalsePositiveReads)
									.addComponent(btnNewButton))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnNewButton_1)
								.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
								.addComponent(btn_Start_Analysis, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(lbl_Count_Reads)
									.addComponent(lblNewLabel_8)))
							.addComponent(panel_Reverse_Adapters_Barcodes, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)))
					.addGap(6)
					.addComponent(panel_Tabs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("Forward - Start - Adapters");
		
		lbl_Forward_Start_Adapters = new JLabel("...");
		
		JLabel lblNewLabel_1 = new JLabel("Forward - End - Adapters");
		lbl_Forward_End_Adapters = new JLabel("...");
		
		JScrollPane scrollPane_Forward_Start_Adapters = new JScrollPane();
		
		rtxt_Forward_Start_Adapters = new JTextArea();
		scrollPane_Forward_Start_Adapters.setViewportView(rtxt_Forward_Start_Adapters);
		
		JScrollPane scrollPane_Forward_End_Adapters = new JScrollPane();
		
		rtxt_Forward_End_Adapters = new JTextArea();
		scrollPane_Forward_End_Adapters.setViewportView(rtxt_Forward_End_Adapters);
		
		JLabel lblNewLabel_4 = new JLabel("Forward - Start - Barcodes");
		
		lbl_Forward_Start_Barcodes = new JLabel("...");
		
		JScrollPane scrollPane_Forward_Start_Barcodes = new JScrollPane();
		
		rtxt_Forward_Start_Barcodes = new JTextArea();
		scrollPane_Forward_Start_Barcodes.setViewportView(rtxt_Forward_Start_Barcodes);
		
		JLabel lblNewLabel_6 = new JLabel("Forward - End - Barcodes");
		
		lbl_Forward_End_Barcodes = new JLabel("...");
		
		JScrollPane scrollPane_Forward_End_Barcodes = new JScrollPane();
		
		rtxt_Forward_End_Barcodes = new JTextArea();
		scrollPane_Forward_End_Barcodes.setViewportView(rtxt_Forward_End_Barcodes);
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
									.addComponent(lbl_Forward_End_Adapters)
									.addComponent(lblNewLabel_1)
									.addComponent(lbl_Forward_Start_Adapters)
									.addComponent(lblNewLabel))
								.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE))
							.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
								.addGap(7)
								.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.TRAILING)
									.addComponent(lbl_Forward_Start_Barcodes)
									.addComponent(lblNewLabel_4))
								.addPreferredGap(ComponentPlacement.UNRELATED)))
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbl_Forward_End_Barcodes)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane_Forward_End_Barcodes, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane_Forward_Start_Barcodes, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane_Forward_End_Adapters)
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_Forward_Start_Adapters, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		gl_panel_Forward_Adapters_Barcodes.setVerticalGroup(
			gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
					.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbl_Forward_Start_Adapters)
							.addGap(49)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbl_Forward_End_Adapters))
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_Forward_Start_Adapters, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_Forward_End_Adapters, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addGap(109)
							.addComponent(lblNewLabel_6)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Forward_End_Barcodes))
						.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_panel_Forward_Adapters_Barcodes.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_Forward_Start_Barcodes, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_Forward_Adapters_Barcodes.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addGap(8)
									.addComponent(lbl_Forward_Start_Barcodes)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_Forward_End_Barcodes, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
					.addContainerGap(12, GroupLayout.PREFERRED_SIZE))
		);
		panel_Forward_Adapters_Barcodes.setLayout(gl_panel_Forward_Adapters_Barcodes);
		
		JTabbedPane tabbedPane_Barcodes = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel lblNewLabel_26 = new JLabel("This software is developed by Zeeshan AHMED, Ph.D. at the Genome Technologies, The Jackson Laboratory for Genomic Medicine, USA.");
		lblNewLabel_26.setForeground(new Color(0, 0, 153));
		
		GroupLayout gl_panel_Tabs = new GroupLayout(panel_Tabs);
		gl_panel_Tabs.setHorizontalGroup(
			gl_panel_Tabs.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_Tabs.createSequentialGroup()
					.addGroup(gl_panel_Tabs.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Tabs.createSequentialGroup()
							.addGap(12)
							.addComponent(lblNewLabel_26))
						.addGroup(gl_panel_Tabs.createSequentialGroup()
							.addContainerGap()
							.addComponent(tabbedPane_Barcodes, GroupLayout.DEFAULT_SIZE, 1406, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_Tabs.setVerticalGroup(
			gl_panel_Tabs.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_Tabs.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane_Barcodes, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_26)
					.addContainerGap())
		);
		
		JPanel panel_UnMatchedReads = new JPanel();
		tabbedPane_Barcodes.addTab("UnMatched-Reads", null, panel_UnMatchedReads, null);
		
		JScrollPane scrollPane_UnMatched_Reads = new JScrollPane();
		
		JButton btn_Export_UnMatched_Reads = new JButton("Export");
		btn_Export_UnMatched_Reads.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_UnMatched_Reads.getRowCount() > 0)
					{
						CLA_Write_To_Excel_CSV obj_CSV = new CLA_Write_To_Excel_CSV();
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
				.addGroup(gl_panel_UnMatchedReads.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_UnMatchedReads.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_UnMatched_Reads, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1373, Short.MAX_VALUE)
						.addGroup(gl_panel_UnMatchedReads.createSequentialGroup()
							.addComponent(lblNewLabel_28)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_UnMatched_Reads)
							.addPreferredGap(ComponentPlacement.RELATED, 1111, Short.MAX_VALUE)
							.addComponent(btn_Export_UnMatched_Reads)))
					.addContainerGap())
		);
		gl_panel_UnMatchedReads.setVerticalGroup(
			gl_panel_UnMatchedReads.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_UnMatchedReads.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addGroup(gl_panel_UnMatchedReads.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_Export_UnMatched_Reads)
						.addGroup(gl_panel_UnMatchedReads.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_28)
							.addComponent(lbl_Total_UnMatched_Reads)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_UnMatched_Reads, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JTable_UnMatched_Reads = new JTable();
		scrollPane_UnMatched_Reads.setViewportView(JTable_UnMatched_Reads);
		panel_UnMatchedReads.setLayout(gl_panel_UnMatchedReads);
		JTable_UnMatched_Reads.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JPanel jpanle_Individual_Adapters = new JPanel();
		tabbedPane_Barcodes.addTab("Adapter Matches", null, jpanle_Individual_Adapters, null);
		
		JLabel lblNewLabel_19 = new JLabel("Total Matches:");
		
		lbl_Total_Individual_Adapter_Matches = new JLabel("...");
		
		JScrollPane scrollPane_Individual_Adapters = new JScrollPane();
		
		JButton btn_Export_Sinple_Adapters = new JButton("Export");
		btn_Export_Sinple_Adapters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Individual_Adapters.getRowCount()>0)
					{
						CLA_Write_To_Excel_CSV obj_CSV = new CLA_Write_To_Excel_CSV();
						obj_CSV.WriteToExcel(JTable_Individual_Adapters, str_ColumnName_Sinlge_Adapter);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_9 = new JLabel("Forward - Start - Adapters:");
		lblNewLabel_9.setForeground(Color.BLUE);
		
		lbl_Count_Forward_Start_Adapters = new JLabel("...");
		lbl_Count_Forward_Start_Adapters.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_10 = new JLabel("Forward - End - Adapters:");
		lblNewLabel_10.setForeground(Color.BLUE);
		
		lbl_Count_Forward_End_Adapters = new JLabel("...");
		lbl_Count_Forward_End_Adapters.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_11 = new JLabel("Reverse - Start - Adapters:");
		lblNewLabel_11.setForeground(Color.RED);
		
		lbl_Count_Reverse_End_Adapters = new JLabel("...");
		lbl_Count_Reverse_End_Adapters.setForeground(Color.RED);
		
		JLabel lblNewLabel_12 = new JLabel("Reverse - End - Adapters:");
		lblNewLabel_12.setForeground(Color.RED);
		
		lbl_Count_Reverse_Start_Adapters = new JLabel("...");
		lbl_Count_Reverse_Start_Adapters.setForeground(Color.RED);
		GroupLayout gl_jpanle_Individual_Adapters = new GroupLayout(jpanle_Individual_Adapters);
		gl_jpanle_Individual_Adapters.setHorizontalGroup(
			gl_jpanle_Individual_Adapters.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_Individual_Adapters.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Individual_Adapters.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_Individual_Adapters, GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
						.addGroup(gl_jpanle_Individual_Adapters.createSequentialGroup()
							.addComponent(lblNewLabel_19)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbl_Total_Individual_Adapter_Matches)
							.addGap(18)
							.addComponent(lblNewLabel_9)
							.addGap(18)
							.addComponent(lbl_Count_Forward_Start_Adapters)
							.addGap(30)
							.addComponent(lblNewLabel_10)
							.addGap(18)
							.addComponent(lbl_Count_Forward_End_Adapters)
							.addGap(28)
							.addComponent(lblNewLabel_11)
							.addGap(18)
							.addComponent(lbl_Count_Reverse_End_Adapters)
							.addGap(18)
							.addComponent(lblNewLabel_12)
							.addGap(18)
							.addComponent(lbl_Count_Reverse_Start_Adapters)
							.addPreferredGap(ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
							.addComponent(btn_Export_Sinple_Adapters)))
					.addContainerGap())
		);
		gl_jpanle_Individual_Adapters.setVerticalGroup(
			gl_jpanle_Individual_Adapters.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_Individual_Adapters.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Individual_Adapters.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpanle_Individual_Adapters.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_19)
							.addComponent(lbl_Count_Forward_End_Adapters)
							.addComponent(lblNewLabel_11)
							.addComponent(lbl_Count_Reverse_End_Adapters)
							.addComponent(lblNewLabel_12)
							.addComponent(lbl_Count_Reverse_Start_Adapters)
							.addComponent(lbl_Total_Individual_Adapter_Matches)
							.addComponent(lblNewLabel_9)
							.addComponent(lbl_Count_Forward_Start_Adapters)
							.addComponent(lblNewLabel_10))
						.addComponent(btn_Export_Sinple_Adapters))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_Individual_Adapters, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTable_Individual_Adapters = new JTable();
		scrollPane_Individual_Adapters.setViewportView(JTable_Individual_Adapters);
		jpanle_Individual_Adapters.setLayout(gl_jpanle_Individual_Adapters);
		JTable_Individual_Adapters.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		
		JPanel jpanle_Multiple_Adapters = new JPanel();
		tabbedPane_Barcodes.addTab("Paired Adapters Matches", null, jpanle_Multiple_Adapters, null);
		
		JLabel lblNewLabel_21 = new JLabel("Total Matches:");
		
		lbl_Total_Multiple_Adapter_Matches = new JLabel("...");
		
		JScrollPane scrollPane_Multiple_Adapters = new JScrollPane();
		
		JButton btn_Export_MultipleAdapters = new JButton("Export");
		btn_Export_MultipleAdapters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Multiple_Adapters.getRowCount()>0)
					{
						CLA_Write_To_Excel_CSV obj_CSV = new CLA_Write_To_Excel_CSV();
						obj_CSV.WriteToExcel(JTable_Multiple_Adapters, str_ColumnName_Paired_Adapter);
					}		
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		GroupLayout gl_jpanle_Multiple_Adapters = new GroupLayout(jpanle_Multiple_Adapters);
		gl_jpanle_Multiple_Adapters.setHorizontalGroup(
			gl_jpanle_Multiple_Adapters.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_jpanle_Multiple_Adapters.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Multiple_Adapters.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_Multiple_Adapters, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
						.addGroup(gl_jpanle_Multiple_Adapters.createSequentialGroup()
							.addComponent(lblNewLabel_21)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_Multiple_Adapter_Matches)
							.addPreferredGap(ComponentPlacement.RELATED, 924, Short.MAX_VALUE)
							.addComponent(btn_Export_MultipleAdapters)))
					.addContainerGap())
		);
		gl_jpanle_Multiple_Adapters.setVerticalGroup(
			gl_jpanle_Multiple_Adapters.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_Multiple_Adapters.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Multiple_Adapters.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_Export_MultipleAdapters)
						.addGroup(gl_jpanle_Multiple_Adapters.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_21)
							.addComponent(lbl_Total_Multiple_Adapter_Matches)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_Multiple_Adapters, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTable_Multiple_Adapters = new JTable();
		scrollPane_Multiple_Adapters.setViewportView(JTable_Multiple_Adapters);
		jpanle_Multiple_Adapters.setLayout(gl_jpanle_Multiple_Adapters);
		JTable_Multiple_Adapters.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		
		JPanel jpanle_Individual_Barcodes = new JPanel();
		tabbedPane_Barcodes.addTab("Barcode Matches", null, jpanle_Individual_Barcodes, null);
		
		JScrollPane scrollPane_Individual_Barcodes = new JScrollPane();
		
		JTable_Individual_Barcodes = new JTable();
		JTable_Individual_Barcodes.setBackground(new Color(255, 255, 255));
		scrollPane_Individual_Barcodes.setViewportView(JTable_Individual_Barcodes);
		JTable_Individual_Barcodes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JLabel lblNewLabel_17 = new JLabel("Total Matches:");
		
		lbl_Total_Individual_Barcode_Matches = new JLabel("...");
		
		JButton btn_Export_Individual_Barcodes = new JButton("Export");
		btn_Export_Individual_Barcodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Individual_Barcodes.getRowCount()>0)
					{
						CLA_Write_To_Excel_CSV obj_CSV = new CLA_Write_To_Excel_CSV();
						obj_CSV.WriteToExcel(JTable_Individual_Barcodes, str_ColumnName_Individual_Barcodes);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_13 = new JLabel("Forward - Start - Barcodes:");
		lblNewLabel_13.setForeground(Color.BLUE);
		
		lbl_Count_Forward_Start_Barcodes = new JLabel("...");
		lbl_Count_Forward_Start_Barcodes.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_14 = new JLabel("Forward - End - Barcodes:");
		lblNewLabel_14.setForeground(Color.BLUE);
		
		lbl_Count_Forward_End_Barcodes = new JLabel("...");
		lbl_Count_Forward_End_Barcodes.setForeground(Color.BLUE);
		
		JLabel lblNewLabel_15 = new JLabel("Reverse - Start - Barcodes:");
		lblNewLabel_15.setForeground(Color.RED);
		
		lbl_Count_Reverse_End_Barcodes = new JLabel("...");
		lbl_Count_Reverse_End_Barcodes.setForeground(Color.RED);
		
		JLabel lblNewLabel_16 = new JLabel("Reverse - End - Barcodes:");
		lblNewLabel_16.setForeground(Color.RED);
		
		lbl_Count_Reverse_Start_Barcodes = new JLabel("...");
		lbl_Count_Reverse_Start_Barcodes.setForeground(Color.RED);
		GroupLayout gl_jpanle_Individual_Barcodes = new GroupLayout(jpanle_Individual_Barcodes);
		gl_jpanle_Individual_Barcodes.setHorizontalGroup(
			gl_jpanle_Individual_Barcodes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_Individual_Barcodes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Individual_Barcodes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpanle_Individual_Barcodes.createSequentialGroup()
							.addComponent(scrollPane_Individual_Barcodes, GroupLayout.DEFAULT_SIZE, 1119, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gl_jpanle_Individual_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_17)
							.addGap(18)
							.addComponent(lbl_Total_Individual_Barcode_Matches)
							.addGap(20)
							.addComponent(lblNewLabel_13)
							.addGap(18)
							.addComponent(lbl_Count_Forward_Start_Barcodes)
							.addGap(18)
							.addComponent(lblNewLabel_14)
							.addGap(18)
							.addComponent(lbl_Count_Forward_End_Barcodes)
							.addGap(18)
							.addComponent(lblNewLabel_15)
							.addGap(18)
							.addComponent(lbl_Count_Reverse_End_Barcodes)
							.addGap(18)
							.addComponent(lblNewLabel_16)
							.addGap(18)
							.addComponent(lbl_Count_Reverse_Start_Barcodes)
							.addPreferredGap(ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
							.addComponent(btn_Export_Individual_Barcodes)
							.addContainerGap())))
		);
		gl_jpanle_Individual_Barcodes.setVerticalGroup(
			gl_jpanle_Individual_Barcodes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpanle_Individual_Barcodes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Individual_Barcodes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpanle_Individual_Barcodes.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_jpanle_Individual_Barcodes.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_13)
								.addComponent(lbl_Count_Forward_Start_Barcodes)
								.addComponent(lblNewLabel_14)
								.addComponent(lbl_Count_Forward_End_Barcodes)
								.addComponent(lblNewLabel_15)
								.addComponent(lbl_Count_Reverse_End_Barcodes)
								.addComponent(lblNewLabel_16)
								.addComponent(lbl_Count_Reverse_Start_Barcodes))
							.addGroup(gl_jpanle_Individual_Barcodes.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_17)
								.addComponent(lbl_Total_Individual_Barcode_Matches)))
						.addComponent(btn_Export_Individual_Barcodes))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_Individual_Barcodes, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
					.addContainerGap())
		);
		jpanle_Individual_Barcodes.setLayout(gl_jpanle_Individual_Barcodes);
		
		JPanel jpanle_Multiple_Barcodes = new JPanel();
		tabbedPane_Barcodes.addTab("Paired Barcode Matches", null, jpanle_Multiple_Barcodes, null);
		
		JScrollPane scrollPane_Multiple_Barcodes = new JScrollPane();
		
		JLabel lblNewLabel_18 = new JLabel("Total Matches:");
		
		lbl_Total_Multiple_Barcode_Matches = new JLabel("...");
		
		JButton btn_Export_MultipleBarcodes = new JButton("Export");
		btn_Export_MultipleBarcodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Multiple_Barcodes.getRowCount()>0)
					{
						CLA_Write_To_Excel_CSV obj_CSV = new CLA_Write_To_Excel_CSV();
						obj_CSV.WriteToExcel(JTable_Multiple_Barcodes, str_ColumnName_Multiplel_Barcodes);
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton btn_Sort_Matches = new JButton("Create Paired Barcodes Combinations");
		btn_Sort_Matches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(chk_DistinctCombination_Froward.isSelected() || chk_DistinctCombination_Reverse.isSelected())
					{
						if(JTable_Multiple_Barcodes.getRowCount()>0)
						{
							//Clear Project Data Table
			       	        DefaultTableModel Obj_DTM = (DefaultTableModel)JTable_Sorted_Multiple_Barcodes_Matches.getModel();
			       	        while(Obj_DTM.getRowCount() > 0){
			       	           for(int i = 0 ; i < Obj_DTM.getRowCount();i++){
			       	        	Obj_DTM.removeRow(i);
			       	           }
			       	        }
			       	        
			       	        //Go for matching
							Sort_Multiple_Barcodes_Matches();	
						}	
					}	
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		chk_DistinctCombination_Froward = new JCheckBox("Forward");
		chk_DistinctCombination_Froward.setSelected(true);
		chk_DistinctCombination_Froward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		chk_DistinctCombination_Reverse = new JCheckBox("Reverse");
		chk_DistinctCombination_Reverse.setSelected(true);
		chk_DistinctCombination_Reverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btn_Delete_MultipleBarcodes = new JButton("Delete Row");
		btn_Delete_MultipleBarcodes.setForeground(Color.RED);
		btn_Delete_MultipleBarcodes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Multiple_Barcodes.getRowCount()>0)
					{
						int int_Decision = JOptionPane.showConfirmDialog(null, "Do you really want to Delete selected Read ?", "Warning", JOptionPane.YES_NO_OPTION);
						if (int_Decision == 0) 
		                { 
							DefaultTableModel objDTM_tmp =  (DefaultTableModel) JTable_Multiple_Barcodes.getModel();
							objDTM_tmp.removeRow(Selected_Row_Number_JTable_Multiple_Barcodes);
		                }
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JScrollPane scrollPane_FalsePostive = new JScrollPane();
		
		chk_OnlyCombinationsWithoutReads = new JCheckBox("Only Combinations - without Reads");
		
		GroupLayout gl_jpanle_Multiple_Barcodes = new GroupLayout(jpanle_Multiple_Barcodes);
		gl_jpanle_Multiple_Barcodes.setHorizontalGroup(
			gl_jpanle_Multiple_Barcodes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpanle_Multiple_Barcodes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Multiple_Barcodes.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_Multiple_Barcodes, GroupLayout.DEFAULT_SIZE, 1373, Short.MAX_VALUE)
						.addGroup(gl_jpanle_Multiple_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_18)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_Multiple_Barcode_Matches)
							.addPreferredGap(ComponentPlacement.RELATED, 580, Short.MAX_VALUE)
							.addComponent(chk_OnlyCombinationsWithoutReads)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chk_DistinctCombination_Reverse)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chk_DistinctCombination_Froward)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_Sort_Matches)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_Export_MultipleBarcodes))
						.addGroup(gl_jpanle_Multiple_Barcodes.createSequentialGroup()
							.addComponent(scrollPane_FalsePostive, GroupLayout.DEFAULT_SIZE, 1241, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btn_Delete_MultipleBarcodes)))
					.addContainerGap())
		);
		gl_jpanle_Multiple_Barcodes.setVerticalGroup(
			gl_jpanle_Multiple_Barcodes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpanle_Multiple_Barcodes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jpanle_Multiple_Barcodes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jpanle_Multiple_Barcodes.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_18)
							.addComponent(lbl_Total_Multiple_Barcode_Matches))
						.addGroup(gl_jpanle_Multiple_Barcodes.createParallelGroup(Alignment.BASELINE)
							.addComponent(btn_Export_MultipleBarcodes)
							.addComponent(btn_Sort_Matches)
							.addComponent(chk_DistinctCombination_Froward)
							.addComponent(chk_DistinctCombination_Reverse)
							.addComponent(chk_OnlyCombinationsWithoutReads)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_Multiple_Barcodes, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jpanle_Multiple_Barcodes.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane_FalsePostive, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_Delete_MultipleBarcodes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(15))
		);
		
		textArea_FalsePositive = new JTextArea();
		textArea_FalsePositive.setForeground(Color.RED);
		scrollPane_FalsePostive.setViewportView(textArea_FalsePositive);
		
		JTable_Multiple_Barcodes = new JTable();
		JTable_Multiple_Barcodes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
	    			
	    			if(JTable_Multiple_Barcodes.getRowCount() > 0)
	       			{		       				
	       				Selected_Row_Number_JTable_Multiple_Barcodes = JTable_Multiple_Barcodes.getSelectedRow();
	       			}

	    			
	    		}
	    		catch(Exception ex)
	    		{
					ex.printStackTrace();
	    		}
			}
		});
		JTable_Multiple_Barcodes.setBackground(new Color(255, 255, 255));
		scrollPane_Multiple_Barcodes.setViewportView(JTable_Multiple_Barcodes);
		JTable_Multiple_Barcodes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jpanle_Multiple_Barcodes.setLayout(gl_jpanle_Multiple_Barcodes);
		
		JPanel panel_Distinct_Multiple_Barcodes_Matches = new JPanel();
		tabbedPane_Barcodes.addTab("Barcode Combinations", null, panel_Distinct_Multiple_Barcodes_Matches, null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btn_Export_Sorted_Multiple_Barcode_Matches = new JButton("Export");
		btn_Export_Sorted_Multiple_Barcode_Matches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Sorted_Multiple_Barcodes_Matches.getRowCount() > 0)
					{
						CLA_Write_To_Excel_CSV obj_CSV = new CLA_Write_To_Excel_CSV();
						obj_CSV.WriteToExcel(JTable_Sorted_Multiple_Barcodes_Matches, str_ColumnName_Sorted_Multiple_Barcodes_Matches);
						
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_23 = new JLabel("Total Matches:");
		
		lbl_Total_Sorted_Multiple_Barcode_Matches = new JLabel("...");
		
		JLabel lblNewLabel_24 = new JLabel("Total Combinations:");
		
		lbl_Total_Sorted_Multiple_Barcode_Combinations = new JLabel("...");
		
		JLabel lblNewLabel_25 = new JLabel("Total Forward Combincations:");
		
		lbl_Total_Sorted_Multiple_Barcode_Combinations_Forward = new JLabel("...");
		
		lbl_Total_Sorted_Multiple_Barcode_Combinations_Reverse = new JLabel("...");
		
		JLabel lblNewLabel_27 = new JLabel("Total Reverse Combincations:");
		
		JButton btn_Common_FR_Barcode_Combinations = new JButton("Common FR Combinations");
		btn_Common_FR_Barcode_Combinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Sorted_Multiple_Barcodes_Matches.getRowCount() > 0)
					{
						//Clear Project Data Table
		       	        DefaultTableModel Obj_DTM = (DefaultTableModel)JTable_Distinct_FR_Combinations.getModel();
		       	        while(Obj_DTM.getRowCount() > 0){
		       	           for(int i = 0 ; i < Obj_DTM.getRowCount();i++){
		       	        	Obj_DTM.removeRow(i);
		       	           }
		       	        }
		       	        
		       	        //Go for matching
		       	        Get_Common_FR_Barcode_Combinations();	
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		
		JButton btn_Distinct_FR_Barcode_Combinations = new JButton("Distinct FR Combinations");
		btn_Distinct_FR_Barcode_Combinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Sorted_Multiple_Barcodes_Matches.getRowCount() > 0)
					{
						//Clear Project Data Table
		       	        DefaultTableModel Obj_DTM = (DefaultTableModel)JTable_Distinct_FR_Combinations.getModel();
		       	        while(Obj_DTM.getRowCount() > 0){
		       	           for(int i = 0 ; i < Obj_DTM.getRowCount();i++){
		       	        	Obj_DTM.removeRow(i);
		       	           }
		       	        }
		       	        
		       	        //Go for matching
		       	        Get_Disticnt_FR_Barcode_Combinations();	
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		GroupLayout gl_panel_Distinct_Multiple_Barcodes_Matches = new GroupLayout(panel_Distinct_Multiple_Barcodes_Matches);
		gl_panel_Distinct_Multiple_Barcodes_Matches.setHorizontalGroup(
			gl_panel_Distinct_Multiple_Barcodes_Matches.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Distinct_Multiple_Barcodes_Matches.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Distinct_Multiple_Barcodes_Matches.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1373, Short.MAX_VALUE)
						.addGroup(gl_panel_Distinct_Multiple_Barcodes_Matches.createSequentialGroup()
							.addComponent(lblNewLabel_23)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_Sorted_Multiple_Barcode_Matches)
							.addGap(29)
							.addComponent(lblNewLabel_24)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_Sorted_Multiple_Barcode_Combinations)
							.addGap(39)
							.addComponent(lblNewLabel_25)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_Sorted_Multiple_Barcode_Combinations_Forward)
							.addGap(42)
							.addComponent(lblNewLabel_27)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Total_Sorted_Multiple_Barcode_Combinations_Reverse)
							.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
							.addComponent(btn_Distinct_FR_Barcode_Combinations)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_Common_FR_Barcode_Combinations)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_Export_Sorted_Multiple_Barcode_Matches)))
					.addContainerGap())
		);
		gl_panel_Distinct_Multiple_Barcodes_Matches.setVerticalGroup(
			gl_panel_Distinct_Multiple_Barcodes_Matches.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Distinct_Multiple_Barcodes_Matches.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Distinct_Multiple_Barcodes_Matches.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Distinct_Multiple_Barcodes_Matches.createParallelGroup(Alignment.BASELINE)
							.addComponent(btn_Export_Sorted_Multiple_Barcode_Matches)
							.addComponent(btn_Common_FR_Barcode_Combinations)
							.addComponent(btn_Distinct_FR_Barcode_Combinations))
						.addGroup(gl_panel_Distinct_Multiple_Barcodes_Matches.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_23)
							.addComponent(lbl_Total_Sorted_Multiple_Barcode_Matches)
							.addComponent(lblNewLabel_24)
							.addComponent(lbl_Total_Sorted_Multiple_Barcode_Combinations)
							.addComponent(lblNewLabel_25)
							.addComponent(lbl_Total_Sorted_Multiple_Barcode_Combinations_Forward)
							.addComponent(lblNewLabel_27)
							.addComponent(lbl_Total_Sorted_Multiple_Barcode_Combinations_Reverse)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTable_Sorted_Multiple_Barcodes_Matches = new JTable();
		scrollPane.setViewportView(JTable_Sorted_Multiple_Barcodes_Matches);
		panel_Distinct_Multiple_Barcodes_Matches.setLayout(gl_panel_Distinct_Multiple_Barcodes_Matches);
		JTable_Sorted_Multiple_Barcodes_Matches.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JPanel panel_Distinct_FR_Combinations = new JPanel();
		tabbedPane_Barcodes.addTab("Common/Disinct Combinations", null, panel_Distinct_FR_Combinations, null);
		
		JScrollPane scrollPane_Distinct_FR_Combinations = new JScrollPane();
		
		JButton btn_Export_Distinct_FR_Combinations = new JButton("Export CSV");
		btn_Export_Distinct_FR_Combinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					if(JTable_Distinct_FR_Combinations.getRowCount() > 0)
					{
						CLA_Write_To_Excel_CSV obj_CSV = new CLA_Write_To_Excel_CSV();
						obj_CSV.WriteToExcel(JTable_Distinct_FR_Combinations, str_ColumnName_Distinct_FR_Barcode_Combinations);
						
					}
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton btn_CreateFasta_Distinct_FR_Combinations = new JButton("Create FASTA");
		btn_CreateFasta_Distinct_FR_Combinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
  		 		{
					if(JTable_Distinct_FR_Combinations.getRowCount() > 0)
					{
						//Write File Line by Line
						JFileChooser chooser = new JFileChooser();
						int retrival = chooser.showSaveDialog(null);
		 		  		if (retrival == JFileChooser.APPROVE_OPTION) 
					    {
					    	String str_File_Path_Name = chooser.getSelectedFile().toString();
					    	str_File_Path_Name = str_File_Path_Name + ".fasta";
							FileWriter writer = new FileWriter(str_File_Path_Name, true);

					    	for(int row_count =0; row_count < JTable_Distinct_FR_Combinations.getRowCount()-1; row_count++)	//Minus in loop is to skip last row
							{
								Multiple_Barcodes_Number 			= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 0).toString();
								Multiple_Barcodes_Read_Number 		= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 1).toString();
								Multiple_Barcodes_Forward_Reverse 	= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 2).toString();
								Multiple_Barcodes_Start_Barcode 	= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 3).toString();
								Multiple_Barcodes_End_Barcode 		= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 4).toString();
								Multiple_Barcodes_Bases 			= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 5).toString();
								Multiple_Barcodes_Sequence 			= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 6).toString();
								
								//if(!Multiple_Barcodes_Sequence.contains(".") && !Multiple_Barcodes_Sequence.contains(""))
								{
									writer.write(Multiple_Barcodes_Sequence +"\n");
								}

							}
							writer.close();
							JOptionPane.showMessageDialog(null, "FastA file is created!", "Notification", JOptionPane.PLAIN_MESSAGE);

					    }
					}
  		 		}
  		 		catch(Exception ex)
  		 		{
  		 			System.out.print(ex);
  		 		}
			}
		});
		
		JButton btn_CreateFastQ_Distinct_FR_Combinations = new JButton("Create FastQ");
		btn_CreateFastQ_Distinct_FR_Combinations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
  		 		{
					
					int int_FastQ_FileLine_Count = 0;

					String FastQ_Header = "";
					String FastQ_Sequence = "";
					String FastQ_Delimieter = "";
					String FastQ_QualityScore = "";

					boolean while_loog_Flag = true;

					
					//Write File Line by Line
					JFileChooser chooser = new JFileChooser();
					int retrival = chooser.showSaveDialog(null);
	 		  		if (retrival == JFileChooser.APPROVE_OPTION) 
				    {
				    	String str_File_Path_Name = chooser.getSelectedFile().toString();
				    	str_File_Path_Name = str_File_Path_Name + ".fastq";
						FileWriter writer = new FileWriter(str_File_Path_Name, true);
						String str_Lines = "";
						
						for(int row_count =0; row_count < JTable_Distinct_FR_Combinations.getRowCount(); row_count++)	//Minus in loop is to skip last row
						{
							Multiple_Barcodes_Number 			= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 0).toString();
							Multiple_Barcodes_Read_Number 		= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 1).toString();
							Multiple_Barcodes_Forward_Reverse 	= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 2).toString();
							Multiple_Barcodes_Start_Barcode 	= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 3).toString();
							Multiple_Barcodes_End_Barcode 		= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 4).toString();
							Multiple_Barcodes_Bases 			= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 5).toString();
							Multiple_Barcodes_Sequence 			= JTable_Distinct_FR_Combinations.getModel().getValueAt(row_count, 6).toString();
							
							FileReader reader = new FileReader(str_Input_FastQ_File_Name_Path);
							BufferedReader bufferedReader = new BufferedReader(reader);
							while_loog_Flag= true;
									
							while((str_Lines = bufferedReader.readLine())!=null && while_loog_Flag == true)
							{
								int_FastQ_FileLine_Count++;
								
								FastQ_Header 		= 	str_Lines;
								FastQ_Sequence 		=	bufferedReader.readLine();
								FastQ_Delimieter 	=	bufferedReader.readLine();
								FastQ_QualityScore 	=	bufferedReader.readLine();
								
								
								if(int_FastQ_FileLine_Count == Integer.parseInt(Multiple_Barcodes_Read_Number))
								{
									writer.write(FastQ_Header+ "\n");
										writer.write(FastQ_Sequence + "\n");
											writer.write(FastQ_Delimieter + "\n");
													writer.write(FastQ_QualityScore + "\n");
									
													while_loog_Flag= false;
								}
							}
							
							reader.close();
							int_FastQ_FileLine_Count = 0;
						}
				    
						writer.close();
						JOptionPane.showMessageDialog(null, "FastQ file is created!", "Notification", JOptionPane.PLAIN_MESSAGE);

				    }

  		 		}
  		 		catch(Exception ex)
  		 		{
  		 			System.out.print(ex);
  		 		}
			}
		});
		GroupLayout gl_panel_Distinct_FR_Combinations = new GroupLayout(panel_Distinct_FR_Combinations);
		gl_panel_Distinct_FR_Combinations.setHorizontalGroup(
			gl_panel_Distinct_FR_Combinations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Distinct_FR_Combinations.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Distinct_FR_Combinations.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_Distinct_FR_Combinations, GroupLayout.DEFAULT_SIZE, 1373, Short.MAX_VALUE)
						.addGroup(gl_panel_Distinct_FR_Combinations.createSequentialGroup()
							.addComponent(btn_CreateFastQ_Distinct_FR_Combinations)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_CreateFasta_Distinct_FR_Combinations)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_Export_Distinct_FR_Combinations)))
					.addContainerGap())
		);
		gl_panel_Distinct_FR_Combinations.setVerticalGroup(
			gl_panel_Distinct_FR_Combinations.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Distinct_FR_Combinations.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Distinct_FR_Combinations.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_Export_Distinct_FR_Combinations)
						.addComponent(btn_CreateFasta_Distinct_FR_Combinations)
						.addComponent(btn_CreateFastQ_Distinct_FR_Combinations))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_Distinct_FR_Combinations, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTable_Distinct_FR_Combinations = new JTable();
		scrollPane_Distinct_FR_Combinations.setViewportView(JTable_Distinct_FR_Combinations);
		panel_Distinct_FR_Combinations.setLayout(gl_panel_Distinct_FR_Combinations);
		JTable_Distinct_FR_Combinations.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


		panel_Tabs.setLayout(gl_panel_Tabs);
		
		JLabel lblNewLabel_2 = new JLabel("Reverse - End - Adapters");
		
		JScrollPane scrollPane_Reverse_Start_Adapters = new JScrollPane();
		
		rtxt_Reverse_Start_Adapters = new JTextArea();
		scrollPane_Reverse_Start_Adapters.setViewportView(rtxt_Reverse_Start_Adapters);
		
		JLabel lblNewLabel_3 = new JLabel("Reverse - Start - Adapters");
		
		JScrollPane scrollPane_Reverse_End_Adapters = new JScrollPane();
		lbl_Reverse_End_Adapter = new JLabel("...");
		lbl_Reverse_Start_Adapter = new JLabel("...");
		
		JLabel lblNewLabel_5 = new JLabel("Reverse - End - Barcodes");
		
		lbl_Reverse_End_Barcode = new JLabel("...");
		
		JScrollPane scrollPane_Reverse_Start_Barcodes = new JScrollPane();
		
		rtxt_Reverse_Start_Barcodes = new JTextArea();
		scrollPane_Reverse_Start_Barcodes.setViewportView(rtxt_Reverse_Start_Barcodes);
		
		JLabel lblNewLabel_7 = new JLabel("Reverse - Start - Barcodes");
		lbl_Reverse_Start_Barcode = new JLabel("...");
		
		JScrollPane scrollPane_Reverse_End_Barcodes = new JScrollPane();
		
		rtxt_Reverse_End_Barcodes = new JTextArea();
		scrollPane_Reverse_End_Barcodes.setViewportView(rtxt_Reverse_End_Barcodes);
		
		GroupLayout gl_panel_Reverse_Adapters_Barcodes = new GroupLayout(panel_Reverse_Adapters_Barcodes);
		gl_panel_Reverse_Adapters_Barcodes.setHorizontalGroup(
			gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2)
						.addComponent(lbl_Reverse_End_Adapter, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(lbl_Reverse_Start_Adapter, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(lbl_Reverse_End_Barcode)
						.addComponent(lblNewLabel_7)
						.addComponent(lbl_Reverse_Start_Barcode))
					.addGap(18)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.LEADING, false)
						.addComponent(scrollPane_Reverse_End_Barcodes, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane_Reverse_Start_Barcodes, 0, 0, Short.MAX_VALUE)
						.addComponent(scrollPane_Reverse_Start_Adapters, GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
						.addComponent(scrollPane_Reverse_End_Adapters))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		gl_panel_Reverse_Adapters_Barcodes.setVerticalGroup(
			gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(8)
							.addComponent(lbl_Reverse_End_Adapter))
						.addComponent(scrollPane_Reverse_Start_Adapters, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_Reverse_Start_Adapter))
						.addComponent(scrollPane_Reverse_End_Adapters, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_Reverse_Start_Barcodes, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addGap(8)
							.addComponent(lbl_Reverse_End_Barcode)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Reverse_Adapters_Barcodes.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_Reverse_End_Barcodes, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Reverse_Adapters_Barcodes.createSequentialGroup()
							.addComponent(lblNewLabel_7)
							.addGap(4)
							.addComponent(lbl_Reverse_Start_Barcode)))
					.addContainerGap(7, Short.MAX_VALUE))
		);
		
		rtxt_Reverse_End_Adapters = new JTextArea();
		scrollPane_Reverse_End_Adapters.setViewportView(rtxt_Reverse_End_Adapters);
		panel_Reverse_Adapters_Barcodes.setLayout(gl_panel_Reverse_Adapters_Barcodes);
		contentPane.setLayout(gl_contentPane);
	}
}
