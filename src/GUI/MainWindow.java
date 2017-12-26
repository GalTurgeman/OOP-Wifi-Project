package GUI;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Algo.AlgoA;
import Algo.ReadWriteInputAlgo2;
import IO.WriteFile;
import Main.INITIAL;

import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.Canvas;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JSlider;

public class MainWindow extends JFrame {
	File file = new File("/Users");
	Desktop desktop = Desktop.getDesktop();
	private JTextField txtEnterIdModel;
	private JTextField txtStartTime;
	private JTextField txtEndTime;
	private JTextField txtLat;
	private JTextField txtLon;
	private JTextField txtAlt;
	private JTextField txtRadius;
	private JLabel lblSaveAsKml;
	private JLabel label_2;
	private JTextField txtEnterIntegerTo;
	private JTextField txtEnterLineasString;
	private JTextField txtMac;
	private JTextField txtSig;
	private JTextField txtSig_1;
	private JTextField txtMac_2;
	private JTextField txtMac_1;
	private JTextField textField;

	public MainWindow() throws IOException {

		super("Wifi Project");
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);

		Icon foldericon=new ImageIcon("Icons/folderIcon2.png");

		ImageIcon CSVicon=new ImageIcon("Icons/CSV.png");
		getContentPane().setLayout(null);

		Canvas Line = new Canvas();
		Line.setBounds(97, 0, 1, 450);
		Line.setBackground(Color.GRAY);
		getContentPane().add(Line);
				JPanel IOWindow = new JPanel();
				IOWindow.setBounds(97, 0, 573, 444);
				IOWindow.setBorder(null);
				
						IOWindow.setBackground(Color.WHITE);
						
								getContentPane().add(IOWindow);
								IOWindow.setLayout(null);
								IOWindow.setVisible(false);
								Canvas canvas = new Canvas();
								canvas.setBounds(10, 180, 553, 1);
								canvas.setBackground(Color.LIGHT_GRAY);
								IOWindow.add(canvas);
								
								JButton btnOpenFolder = new JButton(foldericon);
								btnOpenFolder.setBounds(156, 6, 40, 34);
								IOWindow.add(btnOpenFolder);
								btnOpenFolder.setBackground(Color.WHITE);
								btnOpenFolder.setForeground(Color.WHITE);
								
										btnOpenFolder.setName("open folder");
										JButton btnOpenCsv = new JButton("Open CSV", CSVicon);
										btnOpenCsv.setBounds(201, 6, 97, 36);
										btnOpenCsv.setFont(new Font("Tahoma", Font.PLAIN, 11));
										IOWindow.add(btnOpenCsv);
										
												JButton btnClearDb = new JButton("Clear DB");
												btnClearDb.setBounds(303, 6, 97, 36);
												btnClearDb.setFont(new Font("Tahoma", Font.PLAIN, 13));
												btnClearDb.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
													}
												});
												IOWindow.add(btnClearDb);
												
														JLabel NumberOfRecords = new JLabel(""+INITIAL.WifiSamples.size());
														NumberOfRecords.setBounds(217, 276, 25, 16);
														NumberOfRecords.setFont(new Font("Tahoma", Font.PLAIN, 13));
														IOWindow.add(NumberOfRecords);
														
																JLabel NumberOfMAC = new JLabel(""+WriteFile.MacCounter);
																NumberOfMAC.setBounds(217, 223, 61, 16);
																NumberOfMAC.setFont(new Font("Tahoma", Font.PLAIN, 13));
																IOWindow.add(NumberOfMAC);
																
																		JLabel RouterIcon = new JLabel("");
																		RouterIcon.setBounds(10, 200, 61, 50);
																		RouterIcon.setFont(new Font("Tahoma", Font.PLAIN, 15));
																		RouterIcon.setIcon(new ImageIcon("Icons/router.png"));
																		IOWindow.add(RouterIcon);
																		
																				JLabel numMAClabel = new JLabel("Number of MACs:");
																				numMAClabel.setBounds(72, 222, 125, 16);
																				numMAClabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
																				IOWindow.add(numMAClabel);
																				
																						JLabel numRecords = new JLabel("Number of records:");
																						numRecords.setBounds(72, 275, 133, 16);
																						numRecords.setFont(new Font("Tahoma", Font.PLAIN, 15));
																						IOWindow.add(numRecords);
																						
																								JLabel RecordsIcon = new JLabel("");
																								RecordsIcon.setBounds(10, 260, 50, 50);
																								RecordsIcon.setFont(new Font("Tahoma", Font.PLAIN, 15));
																								RecordsIcon.setIcon(new ImageIcon("Icons/records.png"));
																								IOWindow.add(RecordsIcon);
																								
																										JLabel lblDelteDb = new JLabel("");
																										lblDelteDb.addMouseListener(new MouseAdapter() {
																											@Override
																											public void mouseClicked(MouseEvent e) {
																												JOptionPane.showMessageDialog(IOWindow,
																														"DB was delete successfully!",
																														"Delte DB",
																														JOptionPane.PLAIN_MESSAGE);
																											}
																										});
																										lblDelteDb.setBounds(26, 400, 25, 25);
																										lblDelteDb.setFont(new Font("Tahoma", Font.PLAIN, 11));
																										lblDelteDb.setIcon(new ImageIcon("Icons/del.png"));
																										IOWindow.add(lblDelteDb);
																										
																												JLabel lblDeleteDb = new JLabel("Delete DB");
																												lblDeleteDb.addMouseListener(new MouseAdapter() {
																													@Override
																													public void mouseClicked(MouseEvent e) {
																														JOptionPane.showMessageDialog(IOWindow,
																																"DB was delete successfully!",
																																"Delte DB",
																																JOptionPane.PLAIN_MESSAGE);
																													}
																												});
																												lblDeleteDb.setBounds(72, 405, 61, 16);
																												lblDeleteDb.setFont(new Font("Tahoma", Font.PLAIN, 13));
																												IOWindow.add(lblDeleteDb);
																												
																														lblSaveAsKml = new JLabel("Save as KML");
																														lblSaveAsKml.addMouseListener(new MouseAdapter() {
																															@Override
																															public void mouseClicked(MouseEvent e) {
																																JOptionPane.showMessageDialog(IOWindow,
																																		"KML was export sucessfuly!",
																																		"KML Export",
																																		JOptionPane.PLAIN_MESSAGE);
																															}
																														});
																														lblSaveAsKml.setBounds(202, 405, 76, 16);
																														lblSaveAsKml.setFont(new Font("Tahoma", Font.PLAIN, 13));
																														IOWindow.add(lblSaveAsKml);
																														
																																label_2 = new JLabel("");
																																label_2.addMouseListener(new MouseAdapter() {
																																	@Override
																																	public void mouseClicked(MouseEvent e) {

																																		JOptionPane.showMessageDialog(IOWindow,
																																				"KML was export sucessfuly!",
																																				"KML Export",
																																				JOptionPane.PLAIN_MESSAGE);
																																	}
																																});
																																label_2.setBounds(156, 395, 32, 32);
																																label_2.setIcon(new ImageIcon("Icons/KML.png"));
																																label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
																																IOWindow.add(label_2);
																																
																																		btnOpenCsv.addActionListener(new ActionListener() {
																																
																																			@Override
																																			public void actionPerformed(ActionEvent arg0) {
																																				JFileChooser jfc;
																																				jfc = new JFileChooser();     
																																				File f = new File(System.getProperty("user.dir"));
																																				jfc.setCurrentDirectory(f);
																																				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
																																				jfc.setFileFilter(new FileNameExtensionFilter("CSV Files only", "csv"));
																																				jfc.showOpenDialog(btnOpenCsv);
																																				File selFile =null;
																																				selFile = jfc.getSelectedFile();
																																			}
																																		});
																																		btnOpenFolder.addActionListener(new ActionListener() {

																																			@Override
																																			public void actionPerformed(ActionEvent arg0) {
																																				JFileChooser jfc;
																																				jfc = new JFileChooser();     
																																				File f = new File(System.getProperty("user.dir"));
																																				jfc.setCurrentDirectory(f);
																																				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
																																				jfc.showOpenDialog(btnOpenFolder);
																																				File selFile = jfc.getSelectedFile();
																																				System.out.println(selFile.getAbsolutePath());
																																			}
																																		});
		
				JPanel FilterWindow = new JPanel();
				FilterWindow.setBackground(Color.WHITE);
				FilterWindow.setBounds(97, 0, 573, 444);
				getContentPane().add(FilterWindow);
				FilterWindow.setLayout(null);
				FilterWindow.setVisible(false);
				
						JLabel lblNewLabel = new JLabel("Filter");
						lblNewLabel.setBounds(225, 5, 45, 25);
						lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
						FilterWindow.add(lblNewLabel);
						
								Canvas canvas_2 = new Canvas();
								canvas_2.setBackground(Color.LIGHT_GRAY);
								canvas_2.setBounds(0, 150, 567, 1);
								FilterWindow.add(canvas_2);
								
										Canvas canvas_1 = new Canvas();
										canvas_1.setBackground(Color.LIGHT_GRAY);
										canvas_1.setBounds(0, 300, 567, 1);
										FilterWindow.add(canvas_1);
										
												JLabel lblFilterById = new JLabel("Filter by ID:");
												lblFilterById.setBounds(16, 100, 73, 16);
												FilterWindow.add(lblFilterById);
												
														txtEnterIdModel = new JTextField();
														txtEnterIdModel.setText("Enter ID model");
														txtEnterIdModel.setBounds(94, 96, 130, 26);
														FilterWindow.add(txtEnterIdModel);
														txtEnterIdModel.setColumns(10);
														
																JLabel lblFilterByTime = new JLabel("Filter By Time:");
																lblFilterByTime.setBounds(16, 212, 91, 16);
																FilterWindow.add(lblFilterByTime);
																
																		txtStartTime = new JTextField();
																		txtStartTime.setText("Start time");
																		txtStartTime.setBounds(112, 207, 130, 26);
																		FilterWindow.add(txtStartTime);
																		txtStartTime.setColumns(10);
																		
																				txtEndTime = new JTextField();
																				txtEndTime.setText("End time");
																				txtEndTime.setColumns(10);
																				txtEndTime.setBounds(260, 207, 130, 26);
																				FilterWindow.add(txtEndTime);
																				
																						JLabel label_1 = new JLabel("-");
																						label_1.setBounds(245, 212, 21, 16);
																						FilterWindow.add(label_1);
																						
																								JButton TimeFilterButton = new JButton("Filter");
																								TimeFilterButton.setBounds(450, 207, 117, 29);
																								FilterWindow.add(TimeFilterButton);
																								
																										JButton IDbuttonFilter = new JButton("Filter");
																										IDbuttonFilter.setBounds(450, 96, 117, 29);
																										FilterWindow.add(IDbuttonFilter);
																										
																												JLabel lblFilterByGeo = new JLabel("Filter By Geo:");
																												lblFilterByGeo.setBounds(16, 316, 91, 16);
																												FilterWindow.add(lblFilterByGeo);
																												
																														txtLat = new JTextField();
																														txtLat.setText("LAT");
																														txtLat.setColumns(10);
																														txtLat.setBounds(112, 311, 73, 26);
																														FilterWindow.add(txtLat);
																														
																																JButton button = new JButton("Filter");
																																button.setBounds(450, 311, 117, 29);
																																FilterWindow.add(button);
																																
																																		txtLon = new JTextField();
																																		txtLon.setText("LON");
																																		txtLon.setColumns(10);
																																		txtLon.setBounds(197, 311, 73, 26);
																																		FilterWindow.add(txtLon);
																																		
																																				txtAlt = new JTextField();
																																				txtAlt.setText("ALT");
																																				txtAlt.setColumns(10);
																																				txtAlt.setBounds(279, 311, 73, 26);
																																				FilterWindow.add(txtAlt);
																																				
																																						txtRadius = new JTextField();
																																						txtRadius.setText("Radius");
																																						txtRadius.setColumns(10);
																																						txtRadius.setBounds(364, 311, 73, 26);
																																						FilterWindow.add(txtRadius);
		
		JPanel AlgoWindow = new JPanel();
		AlgoWindow.setBackground(Color.WHITE);
		AlgoWindow.setBounds(97, 0, 573, 444);
		getContentPane().add(AlgoWindow);
		AlgoWindow.setLayout(null);
		AlgoWindow.setVisible(false);

		JButton btnFilter = new JButton("Filter");
		btnFilter.setBounds(18, 86, 62, 55);
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IOWindow.setVisible(false);
				AlgoWindow.setVisible(false);
				FilterWindow.setVisible(true);
			}
		});
		
		
		
		JLabel lblAlgoA = new JLabel("Algo A:");
		lblAlgoA.setBounds(6, 110, 61, 16);
		AlgoWindow.add(lblAlgoA);
		
		txtEnterIntegerTo = new JTextField();
		txtEnterIntegerTo.setText("Enter integer to limit");
		txtEnterIntegerTo.setBounds(60, 105, 153, 26);
		AlgoWindow.add(txtEnterIntegerTo);
		txtEnterIntegerTo.setColumns(10);
		
		JLabel lblAlgoB = new JLabel("Algo B:");
		lblAlgoB.setBounds(6, 191, 61, 16);
		AlgoWindow.add(lblAlgoB);
		
		txtEnterLineasString = new JTextField();
		txtEnterLineasString.setText("Enter Line(As string)");
		txtEnterLineasString.setColumns(10);
		txtEnterLineasString.setBounds(60, 186, 153, 26);
		AlgoWindow.add(txtEnterLineasString);
		
		JLabel label = new JLabel("Algo B:");
		label.setBounds(6, 241, 61, 16);
		AlgoWindow.add(label);
		
		txtMac = new JTextField();
		txtMac.setText("MAC1");
		txtMac.setColumns(10);
		txtMac.setBounds(60, 236, 121, 26);
		AlgoWindow.add(txtMac);
		
		txtSig = new JTextField();
		txtSig.setText("Sig1");
		txtSig.setColumns(10);
		txtSig.setBounds(175, 236, 45, 26);
		AlgoWindow.add(txtSig);
		
		txtSig_1 = new JTextField();
		txtSig_1.setText("Sig2");
		txtSig_1.setColumns(10);
		txtSig_1.setBounds(346, 236, 45, 26);
		AlgoWindow.add(txtSig_1);
		
		txtMac_2 = new JTextField();
		txtMac_2.setText("MAC2");
		txtMac_2.setColumns(10);
		txtMac_2.setBounds(231, 236, 121, 26);
		AlgoWindow.add(txtMac_2);
		
		txtMac_1 = new JTextField();
		txtMac_1.setText("MAC3");
		txtMac_1.setColumns(10);
		txtMac_1.setBounds(403, 236, 121, 26);
		AlgoWindow.add(txtMac_1);
		
		textField = new JTextField();
		textField.setText("Sig2");
		textField.setColumns(10);
		textField.setBounds(518, 236, 45, 26);
		AlgoWindow.add(textField);
		JButton btnHome = new JButton("IO");
		btnHome.setBounds(18, 19, 62, 55);
		getContentPane().add(btnHome);
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilterWindow.setVisible(false);
				AlgoWindow.setVisible(false);
				IOWindow.setVisible(true);
			}
		});
		btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(btnFilter);
		
		JButton btnAlgo = new JButton("Algo");
		btnAlgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilterWindow.setVisible(false);
				IOWindow.setVisible(false);
				AlgoWindow.setVisible(true);
			}
		});
		btnAlgo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAlgo.setBounds(18, 153, 62, 55);
		getContentPane().add(btnAlgo);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("FASD");
			}
		});
		btnExit.setBounds(6, 237, 67, 29);
		getContentPane().add(btnExit);




		setBounds(100, 100, 676, 472);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws IOException {
		WriteFile tt = new WriteFile();
		tt.ReWriteTheData();

		AlgoA a = new AlgoA(3);
		ReadWriteInputAlgo2 RIA2 = new ReadWriteInputAlgo2(INITIAL.getReadPathForAlgoBInput(),3);
		new MainWindow().setVisible(true);
	}
	public JLabel getLblSaveAsKml() {
		return lblSaveAsKml;
	}
	public JLabel getLabel_2() {
		return label_2;
	}
}