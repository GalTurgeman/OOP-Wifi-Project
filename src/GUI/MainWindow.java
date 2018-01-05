package GUI;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletionService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import com.teamdev.jxmaps.swing.MapView;

import Algo.AlgoA;
import Algo.AlgoB;
import Algo.AlgoBNews;
import Algo.ReadWriteInputAlgo2;
import FilterInterface.allFilters;
import IO.CreateDB;
import IO.KmlWriter;
import IO.WriteFile;
import Main.INITIAL;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.atom.Link;

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
import javax.swing.JList;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLayeredPane;
import javax.swing.JFormattedTextField;

public class MainWindow extends JFrame {
	public File file = new File("/Users");
	public Desktop desktop = Desktop.getDesktop();
	public static File selFolder;
	public static File selCombCSV;
	private JTextField txtEnterIdModel;
	private JTextField txtStartTime;
	private JTextField txtEndTime;
	private JTextField txtminLAT;
	private JTextField txtminLON;
	private JTextField txtminALT;
	private JLabel SaveKMLtxt;
	private JLabel KMLIcon;
	private JTextField txtAlgoAMAC;
	private JTextField txtAlgoBAsString;
	private JTextField txtALGOB_Mac1;
	private JTextField txtALGBSig1;
	private JTextField txtALGBSig2;
	private JTextField txtALGOB_Mac2;
	private JTextField txtALGOB_Mac3;
	private JTextField txtALGBSig3;
	private static String ReadPath = "toRead";
	private static String WritePath = "toWrite//FullDB.csv";
	private static ArrayList<File> FoldersPaths = new ArrayList<>();
	public allFilters filterOutput =null;

	private static File fileReadPath = new File(ReadPath);
	public static File fileWritePath = new File(WritePath);
	private JTextField txtmaxLAT;
	private JTextField txtmaxLON;
	private JTextField txtmaxALT;

	public static String getReadPath() {
		return ReadPath;
	}

	public static void setReadPath(String readPath) {
		ReadPath = readPath;
	}

	public static String getWritePath() {
		return WritePath;
	}

	public static void setWritePath(String writePath) {
		WritePath = writePath;
	}

	public static File getFileReadPath() {
		return fileReadPath;
	}

	public static void setFileReadPath(File fileReadPath) {
		MainWindow.fileReadPath = fileReadPath;
	}


	public static File getFileWritePath() {
		return fileWritePath;
	}

	public static void setFileWritePath(File fileWritePath) {
		MainWindow.fileWritePath = fileWritePath;
	}


	public MainWindow() throws IOException {

		super("Wifi Project");
		setBackground(Color.WHITE);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);	




		getContentPane().setLayout(null);

		Canvas Line = new Canvas();
		Line.setBounds(97, 0, 1, 450);
		Line.setBackground(Color.GRAY);
		getContentPane().add(Line);

		Icon foldericon=new ImageIcon("Users/gal/git/Wifi_Project/Icons/folderIcon2.png");
		ImageIcon CSVicon=new ImageIcon("Icons/CSV.png");
		//Algo Window
		JPanel AlgoWindow = new JPanel();
		AlgoWindow.setBounds(100, 0, 573, 444);
		getContentPane().add(AlgoWindow);
		AlgoWindow.setBackground(Color.WHITE);
		AlgoWindow.setLayout(null);
		AlgoWindow.setVisible(false);

		Canvas canvas = new Canvas();
		canvas.setBackground(Color.GRAY);
		canvas.setBounds(0, 228, 575, 1);
		AlgoWindow.add(canvas);





		JLabel lblAlgoA = new JLabel("Algo A:");
		lblAlgoA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlgoA.setBackground(Color.WHITE);
		lblAlgoA.setBounds(4, 110, 66, 22);
		AlgoWindow.add(lblAlgoA);

		txtAlgoAMAC = new JTextField();
		txtAlgoAMAC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAlgoAMAC.setBackground(Color.WHITE);
		txtAlgoAMAC.setToolTipText("Enter Mac Address");
		txtAlgoAMAC.setText("Enter Mac Address");
		txtAlgoAMAC.setBounds(92, 110, 170, 22);
		AlgoWindow.add(txtAlgoAMAC);
		txtAlgoAMAC.setColumns(10);

		JLabel lblAlgoB = new JLabel("Algo B:");
		lblAlgoB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlgoB.setBackground(Color.WHITE);
		lblAlgoB.setBounds(6, 249, 64, 22);
		AlgoWindow.add(lblAlgoB);

		txtAlgoBAsString = new JTextField();
		txtAlgoBAsString.setBackground(Color.WHITE);
		txtAlgoBAsString.setText("Enter Line(As string)");
		txtAlgoBAsString.setColumns(10);
		txtAlgoBAsString.setBounds(0, 275, 420, 26);
		AlgoWindow.add(txtAlgoBAsString);

		JLabel label = new JLabel("Algo B:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBackground(Color.WHITE);
		label.setBounds(6, 325, 61, 16);
		AlgoWindow.add(label);

		txtALGOB_Mac1 = new JTextField();
		txtALGOB_Mac1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtALGOB_Mac1.setBackground(Color.WHITE);
		txtALGOB_Mac1.setText("MAC1");
		txtALGOB_Mac1.setColumns(10);
		txtALGOB_Mac1.setBounds(53, 319, 121, 26);
		AlgoWindow.add(txtALGOB_Mac1);

		txtALGBSig1 = new JTextField();
		txtALGBSig1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtALGBSig1.setBackground(Color.WHITE);
		txtALGBSig1.setText("Sig1");
		txtALGBSig1.setColumns(10);
		txtALGBSig1.setBounds(168, 319, 45, 26);
		AlgoWindow.add(txtALGBSig1);

		txtALGBSig2 = new JTextField();
		txtALGBSig2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtALGBSig2.setBackground(Color.WHITE);
		txtALGBSig2.setText("Sig2");
		txtALGBSig2.setColumns(10);
		txtALGBSig2.setBounds(326, 319, 45, 26);
		AlgoWindow.add(txtALGBSig2);

		txtALGOB_Mac2 = new JTextField();
		txtALGOB_Mac2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtALGOB_Mac2.setBackground(Color.WHITE);
		txtALGOB_Mac2.setText("MAC2");
		txtALGOB_Mac2.setColumns(10);
		txtALGOB_Mac2.setBounds(211, 319, 121, 26);
		AlgoWindow.add(txtALGOB_Mac2);

		txtALGOB_Mac3 = new JTextField();
		txtALGOB_Mac3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtALGOB_Mac3.setBackground(Color.WHITE);
		txtALGOB_Mac3.setText("MAC3");
		txtALGOB_Mac3.setColumns(10);
		txtALGOB_Mac3.setBounds(371, 319, 121, 26);
		AlgoWindow.add(txtALGOB_Mac3);

		JLabel lblUserLOC = new JLabel("");
		lblUserLOC.setBackground(Color.WHITE);
		lblUserLOC.setBounds(14, 374, 452, 64);
		AlgoWindow.add(lblUserLOC);

		txtALGBSig3 = new JTextField();
		txtALGBSig3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtALGBSig3.setBackground(Color.WHITE);
		txtALGBSig3.setText("Sig3");
		txtALGBSig3.setColumns(10);
		txtALGBSig3.setBounds(486, 319, 45, 26);
		AlgoWindow.add(txtALGBSig3);

		JButton btnAlgoB_ByString = new JButton("Run");
		btnAlgoB_ByString.setBackground(Color.WHITE);
		btnAlgoB_ByString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LineAsString
				CreateDB.WifiSamplesDB.clear();
				String n =JOptionPane.showInputDialog("Enter interger to limit Algo A:", 3);
				int number= Integer.parseInt(n);
				AlgoBNews b = new AlgoBNews(txtAlgoBAsString.getText(), number);
				System.out.println(b.getPoint());
				lblUserLOC.setText(b.getPoint());

			}
		});
		btnAlgoB_ByString.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAlgoB_ByString.setIcon(new ImageIcon(MainWindow.class.getResource("/check_mark.png")));
		btnAlgoB_ByString.setBounds(466, 271, 94, 36);
		AlgoWindow.add(btnAlgoB_ByString);

		JButton btnAlgoB_3MAC = new JButton("Run");
		btnAlgoB_3MAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDB.WifiSamplesDB.clear();
				//e4:95:6e:40:87:1a
				//00:1a:dd:e3:06:e4
				//00:1a:dd:f5:e9:25
				String n =JOptionPane.showInputDialog("Enter interger to limit Algo A:", 3);
				int number= Integer.parseInt(n);
				AlgoBNews b = new AlgoBNews("e4:95:6e:40:87:1a","-20","00:1a:dd:e3:06:e4","-53","00:1a:dd:f5:e9:25","-58", 3);
//				AlgoBNews b = new AlgoBNews(txtALGOB_Mac1.getText(),txtALGBSig1.getText(),txtALGOB_Mac2.getText(),txtALGBSig2.getText(),txtALGOB_Mac3.getText(),txtALGBSig3.getText(), number);
				System.out.println(b.getPoint());
				lblUserLOC.setText(b.getPoint());

			}
		});
		btnAlgoB_3MAC.setBackground(Color.WHITE);
		btnAlgoB_3MAC.setIcon(new ImageIcon(MainWindow.class.getResource("/check_mark.png")));
		btnAlgoB_3MAC.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAlgoB_3MAC.setBounds(466, 343, 94, 36);
		AlgoWindow.add(btnAlgoB_3MAC);

		JButton AlgoAbtn = new JButton("Run");
		AlgoAbtn.setBackground(Color.WHITE);
		AlgoAbtn.setSelectedIcon(new ImageIcon(MainWindow.class.getResource("/check_mark.png")));
		AlgoAbtn.setHorizontalAlignment(SwingConstants.LEFT);
		AlgoAbtn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		AlgoAbtn.setIcon(new ImageIcon(MainWindow.class.getResource("/check_mark.png")));

		AlgoAbtn.setBounds(466, 105, 94, 36);
		AlgoWindow.add(AlgoAbtn);

		JLabel AlgoHeader = new JLabel("Algorithms");
		AlgoHeader.setBackground(Color.WHITE);
		AlgoHeader.setFont(new Font("Tahoma", Font.PLAIN, 20));
		AlgoHeader.setBounds(225, 5, 101, 26);
		AlgoWindow.add(AlgoHeader);

		JLabel lblFindRouter = new JLabel("Find Router");
		lblFindRouter.setBackground(Color.WHITE);
		lblFindRouter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFindRouter.setBounds(227, 61, 101, 26);
		AlgoWindow.add(lblFindRouter);

		JLabel lblRouterLOC = new JLabel("");
		lblRouterLOC.setBackground(Color.WHITE);
		lblRouterLOC.setBounds(14, 138, 513, 64);
		AlgoWindow.add(lblRouterLOC);

		JLabel lblFindUser = new JLabel("Find User");
		lblFindUser.setBackground(Color.WHITE);
		lblFindUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFindUser.setBounds(225, 230, 101, 26);
		AlgoWindow.add(lblFindUser);



		AlgoAbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Mac =txtAlgoAMAC.getText();
				if(!Mac.contains(":")) {
					JOptionPane.showMessageDialog(AlgoWindow,
							"MAC Address is invalid !!!",
							"Check Input",
							JOptionPane.WARNING_MESSAGE,new ImageIcon(MainWindow.class.getResource("/risk.png")));
				}
				else {
					String n =JOptionPane.showInputDialog("Enter interger to limit Algo A:", 3);
					int number= Integer.parseInt(n);
					AlgoA a = new AlgoA(number);
					lblRouterLOC.setText(a.toString());
					n="";
					Mac="";
				}
			}
		});

		JPanel IOWindow = new JPanel();
		IOWindow.setBounds(97, 0, 573, 444);
		getContentPane().add(IOWindow);
		IOWindow.setBackground(Color.WHITE);
		IOWindow.setLayout(null);
		IOWindow.setVisible(false);
		Canvas IOWindowLine = new Canvas();
		IOWindowLine.setBounds(10, 180, 553, 1);
		IOWindowLine.setBackground(Color.GRAY);
		IOWindow.add(IOWindowLine);

		JLabel NumberOfRecords = new JLabel("0");
		NumberOfRecords.setBackground(Color.WHITE);
		NumberOfRecords.setBounds(217, 276, 50, 16);
		NumberOfRecords.setFont(new Font("Tahoma", Font.PLAIN, 13));
		IOWindow.add(NumberOfRecords);

		JLabel NumberOfMAC = new JLabel("" + CreateDB.MacCounter);
		NumberOfMAC.setBackground(Color.WHITE);
		NumberOfMAC.setBounds(217, 223, 50, 16);
		NumberOfMAC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		IOWindow.add(NumberOfMAC);

		JButton OpenFolderbtn = new JButton(new ImageIcon(MainWindow.class.getResource("/folder_open.png")));
		OpenFolderbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		OpenFolderbtn.setText("Open Folder");
		OpenFolderbtn.setBounds(118, 7, 150, 50);
		IOWindow.add(OpenFolderbtn);
		OpenFolderbtn.setBackground(Color.WHITE);
		OpenFolderbtn.setForeground(Color.BLACK);

		OpenFolderbtn.setName("open folder");
		JButton OpenCSVbtn = new JButton("Open CSV",new ImageIcon(MainWindow.class.getResource("/excel.png")));
		OpenCSVbtn.setBackground(Color.WHITE);
		OpenCSVbtn.setForeground(Color.BLACK);
		OpenCSVbtn.setBounds(280, 7, 150, 50);
		OpenCSVbtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		IOWindow.add(OpenCSVbtn);


		JLabel RouterIcon = new JLabel("");
		RouterIcon.setBackground(Color.WHITE);
		RouterIcon.setBounds(10, 200, 61, 50);
		RouterIcon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		RouterIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/router.png")));
		IOWindow.add(RouterIcon);

		JLabel numMAClabel = new JLabel("Number of MACs:");
		numMAClabel.setBackground(Color.WHITE);
		numMAClabel.setBounds(72, 222, 125, 16);
		numMAClabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		IOWindow.add(numMAClabel);

		JLabel numRecords = new JLabel("Number of records:");
		numRecords.setBackground(Color.WHITE);
		numRecords.setBounds(72, 275, 133, 16);
		numRecords.setFont(new Font("Tahoma", Font.PLAIN, 15));
		IOWindow.add(numRecords);

		JLabel RecordsIcon = new JLabel("");
		RecordsIcon.setBackground(Color.WHITE);
		RecordsIcon.setBounds(10, 260, 50, 50);
		RecordsIcon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		RecordsIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/records.png")));
		IOWindow.add(RecordsIcon);

		JLabel delDBIcon = new JLabel("");
		delDBIcon.setBackground(Color.WHITE);
		delDBIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateDB.ClearDB();
				CreateDB.MacCounter = 0;
				CreateDB.Records =0;
				NumberOfMAC.setText(""+CreateDB.MacCounter);
				NumberOfRecords.setText(""+CreateDB.Records);
				JOptionPane.showMessageDialog(IOWindow,
						"DB was delete successfully!",
						"Delte DB",
						JOptionPane.PLAIN_MESSAGE,new ImageIcon(MainWindow.class.getResource("/check_mark.png")));
				repaint();

			}
		});
		delDBIcon.setBounds(24, 374, 64, 64);
		delDBIcon.setFont(new Font("Tahoma", Font.PLAIN, 11));
		delDBIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/trash_can.png")));
		IOWindow.add(delDBIcon);

		JLabel DelDBtxt = new JLabel("Delete DB");
		DelDBtxt.setBackground(Color.WHITE);
		DelDBtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateDB.ClearDB();
				CreateDB.MacCounter = 0;
				CreateDB.Records =0;
				NumberOfMAC.setText(""+CreateDB.MacCounter);
				NumberOfRecords.setText(""+CreateDB.getFullDB().size());
				JOptionPane.showMessageDialog(IOWindow,
						"DB was delete successfully!",
						"Delte DB",
						JOptionPane.PLAIN_MESSAGE,new ImageIcon(MainWindow.class.getResource("/check_mark.png")));
				repaint();

			}
		});

		DelDBtxt.setBounds(92, 395, 89, 24);
		DelDBtxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		IOWindow.add(DelDBtxt);

		SaveKMLtxt = new JLabel("Save as KML");
		SaveKMLtxt.setBackground(Color.WHITE);
		SaveKMLtxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(CreateDB.getFullDB().isEmpty()) {
					JOptionPane.showMessageDialog(IOWindow,
							"DB is empty!",
							"DB is empty",
							JOptionPane.ERROR_MESSAGE,new ImageIcon(MainWindow.class.getResource("/risk.png")));
				}
				else {
					KmlWriter k = new KmlWriter(CreateDB.getFullDB());
					JOptionPane.showMessageDialog(IOWindow,
							"KML was export sucessfuly!\n File in: "+INITIAL.getWritePathForKML(),
							"KML Export",
							JOptionPane.PLAIN_MESSAGE,new ImageIcon(MainWindow.class.getResource("/check_mark.png")));
				}
			}
		});
		SaveKMLtxt.setBounds(255, 402, 99, 16);
		SaveKMLtxt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		IOWindow.add(SaveKMLtxt);
		KMLIcon = new JLabel("");
		KMLIcon.setBackground(Color.WHITE);
		KMLIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(IOWindow,
						"KML was export sucessfuly!",
						"KML Export",
						JOptionPane.PLAIN_MESSAGE,new ImageIcon(MainWindow.class.getResource("/check_mark.png")));
			}
		});
		KMLIcon.setBounds(190, 374, 64, 64);
		KMLIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/location.png")));
		KMLIcon.setFont(new Font("Tahoma", Font.PLAIN, 11));
		IOWindow.add(KMLIcon);

		JLabel lblSaveToCsv = new JLabel("Save to CSV");
		lblSaveToCsv.setBackground(Color.WHITE);
		lblSaveToCsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(CreateDB.getFullDB().isEmpty()) {
					JOptionPane.showMessageDialog(IOWindow,
							"DB is empty!",
							"DB is empty",
							JOptionPane.ERROR_MESSAGE,new ImageIcon(MainWindow.class.getResource("/risk.png")));
				}
				else {

					CreateDB.WriteToCSVFullDB(CreateDB.getFullDB());
					JOptionPane.showMessageDialog(IOWindow,
							"CSV was export sucessfuly!\n File in: "+INITIAL.getSaveToFullDBPath(),
							"CSV Export",
							JOptionPane.PLAIN_MESSAGE,new ImageIcon(MainWindow.class.getResource("/check_mark.png")));
					System.out.println(INITIAL.getSaveToFullDBPath());
				}
			}
		});
		lblSaveToCsv.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSaveToCsv.setBounds(438, 395, 99, 22);
		IOWindow.add(lblSaveToCsv);

		JLabel label_3 = new JLabel("");
		label_3.setBackground(Color.WHITE);
		label_3.setIcon(new ImageIcon(MainWindow.class.getResource("/excel.png")));
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(382, 374, 64, 64);
		IOWindow.add(label_3);

		JLabel lblFilterInfo = new JLabel("Filter Info");
		lblFilterInfo.setBackground(Color.WHITE);
		lblFilterInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFilterInfo.setBounds(385, 222, 99, 16);
		IOWindow.add(lblFilterInfo);

		JLabel FilterIcon = new JLabel("");
		FilterIcon.setBackground(Color.WHITE);
		FilterIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/filter.png")));
		FilterIcon.setBounds(314, 200, 50, 50);
		IOWindow.add(FilterIcon);

		JLabel txtFilterInfo = new JLabel("");
		txtFilterInfo.setBackground(Color.WHITE);
		txtFilterInfo.setBounds(285, 250, 278, 86);
		IOWindow.add(txtFilterInfo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				CreateDB.Undo();
				System.out.println("FULL DB : " + CreateDB.FullDB.size());
				System.out.println("TEMP FULL DB : " + CreateDB.TempFullDB.size());
				NumberOfMAC.setText(""+CreateDB.MacCounter);
				NumberOfRecords.setText(""+CreateDB.Records);
				repaint();
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(MainWindow.class.getResource("/backup.png")));
		lblNewLabel_2.setBounds(531, 211, 32, 32);
		IOWindow.add(lblNewLabel_2);

		Canvas canvas_3 = new Canvas();
		canvas_3.setBackground(Color.GRAY);
		canvas_3.setBounds(273, 180, 1, 166);
		IOWindow.add(canvas_3);


		OpenCSVbtn.addActionListener(new ActionListener() {
			@Override
			//Csv Button
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc;
				jfc = new JFileChooser();     
				File f = new File(System.getProperty("user.dir"));
				jfc.setCurrentDirectory(f);
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.setFileFilter(new FileNameExtensionFilter("CSV Files only", "csv","CSV"));
				jfc.showOpenDialog(OpenCSVbtn);
				selCombCSV= jfc.getSelectedFile();
				CreateDB cDBC = new CreateDB(selCombCSV, 1);
				NumberOfMAC.setText(""+CreateDB.MacCounter);
				NumberOfRecords.setText(""+CreateDB.Records);
				repaint();
			}
		});
		OpenFolderbtn.addActionListener(new ActionListener() {

			@Override
			//Folder Button!
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc;
				File f = new File(System.getProperty("user.dir"));
				jfc = new JFileChooser(f.getParent());     
				jfc.setCurrentDirectory(f);
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfc.showOpenDialog(OpenFolderbtn);
				selFolder = jfc.getSelectedFile();
				System.out.println(selFolder.getAbsolutePath());
				FoldersPaths.add(selFolder);
				INITIAL.setReadPath(selFolder.getAbsolutePath());
				CreateDB cDBF = new CreateDB(selFolder, 0);
				NumberOfMAC.setText(""+CreateDB.MacCounter);
				NumberOfRecords.setText(""+CreateDB.Records);
				repaint();
			}
		});

		//FilterWindow
		JPanel FilterWindow = new JPanel();
		FilterWindow.setBounds(100, 0, 573, 444);
		getContentPane().add(FilterWindow);
		FilterWindow.setBackground(Color.WHITE);
		FilterWindow.setLayout(null);
		FilterWindow.setVisible(false);
		//End IOWindow

		JLabel lblNewLabel = new JLabel("Filter");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(225, 5, 45, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FilterWindow.add(lblNewLabel);

		Canvas canvas_2 = new Canvas();
		canvas_2.setBackground(Color.GRAY);
		canvas_2.setBounds(0, 150, 567, 1);
		FilterWindow.add(canvas_2);

		Canvas canvas_1 = new Canvas();
		canvas_1.setBackground(Color.GRAY);
		canvas_1.setBounds(0, 270, 567, 1);
		FilterWindow.add(canvas_1);

		JLabel lblFilterById = new JLabel("Filter by ID:");
		lblFilterById.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFilterById.setBackground(Color.WHITE);
		lblFilterById.setBounds(34, 125, 84, 16);
		FilterWindow.add(lblFilterById);

		txtEnterIdModel = new JTextField();
		txtEnterIdModel.setBackground(Color.WHITE);
		txtEnterIdModel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEnterIdModel.setEnabled(false);
		txtEnterIdModel.setBounds(130, 118, 130, 26);
		FilterWindow.add(txtEnterIdModel);
		txtEnterIdModel.setColumns(10);

		JLabel lblFilterByTime = new JLabel("Filter By Time:");
		lblFilterByTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFilterByTime.setBackground(Color.WHITE);
		lblFilterByTime.setBounds(34, 212, 101, 16);
		FilterWindow.add(lblFilterByTime);

		txtStartTime = new JTextField();
		txtStartTime.setBackground(Color.WHITE);
		txtStartTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtStartTime.setEnabled(false);
		txtStartTime.setBounds(187, 208, 130, 26);
		FilterWindow.add(txtStartTime);
		txtStartTime.setColumns(10);

		txtEndTime = new JTextField();
		txtEndTime.setBackground(Color.WHITE);
		txtEndTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtEndTime.setEnabled(false);
		txtEndTime.setColumns(10);
		txtEndTime.setBounds(335, 208, 130, 26);
		FilterWindow.add(txtEndTime);

		JLabel label_1 = new JLabel("-");
		label_1.setBackground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(317, 213, 21, 16);
		FilterWindow.add(label_1);

		JLabel lblFilterByGeo = new JLabel("Filter By Geo:");
		lblFilterByGeo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFilterByGeo.setBackground(Color.WHITE);
		lblFilterByGeo.setBounds(34, 312, 101, 16);
		FilterWindow.add(lblFilterByGeo);

		txtminLAT = new JTextField();
		txtminLAT.setBackground(Color.WHITE);
		txtminLAT.setText("0");
		txtminLAT.setEnabled(false);
		txtminLAT.setColumns(10);
		txtminLAT.setBounds(195, 297, 73, 26);
		FilterWindow.add(txtminLAT);

		txtminLON = new JTextField();
		txtminLON.setBackground(Color.WHITE);
		txtminLON.setText("0");
		txtminLON.setEnabled(false);
		txtminLON.setColumns(10);
		txtminLON.setBounds(265, 297, 73, 26);
		FilterWindow.add(txtminLON);

		txtminALT = new JTextField();
		txtminALT.setBackground(Color.WHITE);
		txtminALT.setText("0");
		txtminALT.setToolTipText("0");
		txtminALT.setEnabled(false);
		txtminALT.setColumns(10);
		txtminALT.setBounds(335, 297, 73, 26);
		FilterWindow.add(txtminALT);

		JCheckBox checkBoxByTime = new JCheckBox("");
		checkBoxByTime.setBackground(Color.WHITE);
		JCheckBox checkboxBYID = new JCheckBox("");
		checkboxBYID.setBackground(Color.WHITE);
		JCheckBox checkBoxBYGEO = new JCheckBox("");
		checkBoxBYGEO.setBackground(Color.WHITE);


		checkboxBYID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					txtEnterIdModel.setEnabled(true);
				}
				else {
					txtEnterIdModel.setEnabled(false);
				}
			}
		});

		checkboxBYID.setBounds(10, 122, 28, 23);
		FilterWindow.add(checkboxBYID);


		checkBoxByTime.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					txtEndTime.setEnabled(true);
					txtStartTime.setEnabled(true);
				}
				else {
					txtEndTime.setEnabled(false);
					txtStartTime.setEnabled(false);
				}
			}
		});
		checkBoxByTime.setBounds(10, 208, 28, 23);
		FilterWindow.add(checkBoxByTime);


		checkBoxBYGEO.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					txtminALT.setEnabled(true);
					txtminLAT.setEnabled(true);
					txtminLON.setEnabled(true);
					txtmaxALT.setEnabled(true);
					txtmaxLAT.setEnabled(true);
					txtmaxLON.setEnabled(true);
				}
				else {
					txtminALT.setEnabled(false);
					txtminLAT.setEnabled(false);
					txtminLON.setEnabled(false);
					txtmaxALT.setEnabled(false);
					txtmaxLAT.setEnabled(false);
					txtmaxLON.setEnabled(false);
				}
			}
		});
		checkBoxBYGEO.setBounds(10, 308, 28, 23);
		FilterWindow.add(checkBoxBYGEO);

		JCheckBox chckbxAnd = new JCheckBox("And");
		chckbxAnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxAnd.setBackground(Color.WHITE);
		chckbxAnd.setBounds(151, 42, 60, 23);
		FilterWindow.add(chckbxAnd);

		JCheckBox chckbxOr = new JCheckBox("Or");
		chckbxOr.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxOr.setBackground(Color.WHITE);
		chckbxOr.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					chckbxAnd.setEnabled(false);
				}
				else {
					chckbxAnd.setEnabled(true);
				}

			}
		});
		chckbxOr.setBounds(362, 42, 60, 23);
		FilterWindow.add(chckbxOr);

		chckbxAnd.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
					chckbxOr.setEnabled(false);
					if(checkboxBYID.isSelected())
						if(checkBoxByTime.isSelected())
							checkBoxBYGEO.setEnabled(false);
					if(checkboxBYID.isSelected())
						if(checkBoxBYGEO.isSelected())
							checkBoxByTime.setEnabled(false);
					if(checkBoxByTime.isSelected())
						if(checkBoxBYGEO.isSelected())
							checkboxBYID.setEnabled(false);
				}
				else {
					chckbxOr.setEnabled(true);
				}
			}
		});
		JCheckBox chckbxNot = new JCheckBox("Not");
		chckbxNot.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxNot.setBackground(Color.WHITE);
		chckbxNot.setBounds(10, 96, 60, 23);
		FilterWindow.add(chckbxNot);

		JCheckBox checkBox = new JCheckBox("Not");
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox.setBackground(Color.WHITE);
		checkBox.setBounds(10, 185, 60, 23);
		FilterWindow.add(checkBox);

		JCheckBox checkBox_1 = new JCheckBox("Not");
		checkBox_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		checkBox_1.setBackground(Color.WHITE);
		checkBox_1.setBounds(10, 289, 60, 23);
		FilterWindow.add(checkBox_1);

		JButton btnNewButton = new JButton("Apply Filters");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDB.TempFullDB.addAll(CreateDB.FullDB);
				CreateDB.RecordsToSave = CreateDB.Records;
				allFilters filter338 = null;

				txtFilterInfo.setText(FilterInfo(chckbxAnd, chckbxOr, checkboxBYID, checkBoxBYGEO, checkBoxByTime));
				if(!checkBoxBYGEO.isSelected()) {
					filter338 = new allFilters(false,checkBoxByTime.isSelected(), checkboxBYID.isSelected(), checkBoxBYGEO.isSelected(), 
							chckbxAnd.isSelected(), chckbxOr.isSelected(), 
							txtStartTime.getText(), txtEndTime.getText(), txtEnterIdModel.getText(), 
							txtminLAT.getText(), txtmaxLAT.getText(), txtminLON.getText(), 
							txtmaxLON.getText(), txtminALT.getText(),txtmaxALT.getText());
					CreateDB.ClearDB();
					CreateDB.FullDB.addAll(filter338.resultColl);
					System.out.println("FILTER : " + filter338.resultColl.size());
					System.out.println("CREATE DB " + CreateDB.FullDB.size());
					CreateDB.getMacCounter(CreateDB.getFullDB());
					CreateDB.getRecords(CreateDB.getFullDB());
					NumberOfMAC.setText(""+CreateDB.MacCounter);
					NumberOfRecords.setText(""+CreateDB.Records);
					System.out.println(CreateDB.MacCounter);
					System.out.println(CreateDB.Records);
					repaint();
					//					System.out.println(txtEnterIdModel.getText());
				}
				else{
					//LAT 			LON 			ALT
					//32.10198667  // 35.20976127  // 690
					//32.10503984 //	 35.21099522 //	690
					//SM-G950F   // 			   //
					CreateDB.TempFullDB.addAll(CreateDB.FullDB);
					filter338 = new allFilters(false,checkBoxByTime.isSelected(), checkboxBYID.isSelected(), checkBoxBYGEO.isSelected(), chckbxAnd.isSelected(), chckbxOr.isSelected() ,
							txtStartTime.getText(), txtEndTime.getText(), txtEnterIdModel.getText(),
							(txtminLAT.getText()), (txtmaxLAT.getText()), 
							(txtminLON.getText()), (txtmaxLON.getText()),
							(txtminALT.getText()), (txtmaxALT.getText()));
					CreateDB.RecordsToSave = CreateDB.Records;
					CreateDB.ClearDB();
					CreateDB.FullDB.addAll(filter338.resultColl);
					System.out.println("FILTER : " + filter338.resultColl.size());
					System.out.println("CREATE DB " + CreateDB.FullDB.size());
					NumberOfMAC.setText(""+CreateDB.MacCounter);
					NumberOfRecords.setText(""+CreateDB.Records);
					System.out.println("MAC COUNTER : " + CreateDB.MacCounter);
					System.out.println("RECORDS NUMBER : " +CreateDB.Records);
					repaint();
				}
			}
		});
		btnNewButton.setBounds(55, 397, 117, 29);
		FilterWindow.add(btnNewButton);

		JButton btnLoadFilter = new JButton("Load Filter");
		btnLoadFilter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLoadFilter.setBackground(Color.WHITE);
		btnLoadFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allFilters al = null;
				try {
					FileInputStream fileIn = new FileInputStream("/Users/gal/Desktop/1.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					al = (allFilters)in.readObject();
					in.close();
					fileIn.close();
				} catch (IOException i) {
					System.out.println(i.getMessage());
					return;
				} catch (ClassNotFoundException c) {
					System.out.println("class not found");
					System.out.println(c);
					return;
				}
				System.err.println("Filter Load");
			}
		});
		btnLoadFilter.setBounds(227, 397, 117, 29);
		FilterWindow.add(btnLoadFilter);

		JButton btnSaveFilter = new JButton("Save Filter");
		btnSaveFilter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSaveFilter.setBackground(Color.WHITE);
		btnSaveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allFilters a = new allFilters(true,checkBoxByTime.isSelected(), checkboxBYID.isSelected(), checkBoxBYGEO.isSelected(), 
						chckbxAnd.isSelected(), chckbxOr.isSelected(), 
						txtStartTime.getText(), txtEndTime.getText(), txtEnterIdModel.getText(), 
						txtminLAT.getText(), txtmaxLAT.getText(), txtminLON.getText(), 
						txtmaxLON.getText(), txtminALT.getText(),txtmaxALT.getText()); 
				try {
					FileOutputStream fileoutput = new FileOutputStream("/Users/gal/Desktop/1.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileoutput);
					out.writeObject(a);
					out.close();
					fileoutput.close();
					System.err.println("Filter Save");
				}
				catch (IOException i) {
					i.printStackTrace();
				}
			}
		}
				);
		btnSaveFilter.setBounds(399, 397, 117, 29);
		FilterWindow.add(btnSaveFilter);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBackground(Color.WHITE);

		lblNewLabel_1.setIcon(new ImageIcon(MainWindow.class.getResource("/info.png")));
		lblNewLabel_1.setBounds(496, 12, 64, 64);
		FilterWindow.add(lblNewLabel_1);

		txtmaxLAT = new JTextField();
		txtmaxLAT.setBackground(Color.WHITE);
		txtmaxLAT.setText("0");
		txtmaxLAT.setEnabled(false);
		txtmaxLAT.setColumns(10);
		txtmaxLAT.setBounds(195, 349, 73, 26);
		FilterWindow.add(txtmaxLAT);

		txtmaxLON = new JTextField();
		txtmaxLON.setBackground(Color.WHITE);
		txtmaxLON.setText("0");
		txtmaxLON.setEnabled(false);
		txtmaxLON.setColumns(10);
		txtmaxLON.setBounds(265, 349, 73, 26);
		FilterWindow.add(txtmaxLON);

		txtmaxALT = new JTextField();
		txtmaxALT.setBackground(Color.WHITE);
		txtmaxALT.setText("0");
		txtmaxALT.setEnabled(false);
		txtmaxALT.setColumns(10);
		txtmaxALT.setBounds(335, 349, 73, 26);
		FilterWindow.add(txtmaxALT);

		JLabel lblMin = new JLabel("Min");
		lblMin.setBackground(Color.WHITE);
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMin.setBounds(280, 277, 45, 25);
		FilterWindow.add(lblMin);

		JLabel lblMax = new JLabel("Max");
		lblMax.setBackground(Color.WHITE);
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMax.setBounds(280, 324, 45, 25);
		FilterWindow.add(lblMax);

		JLabel lblYyyymmddHhmmss = new JLabel("yyyy-MM-dd HH:mm:ss");
		lblYyyymmddHhmmss.setForeground(Color.GRAY);
		lblYyyymmddHhmmss.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYyyymmddHhmmss.setBackground(Color.WHITE);
		lblYyyymmddHhmmss.setBounds(225, 189, 164, 16);
		FilterWindow.add(lblYyyymmddHhmmss);



		//IOWINDOW


		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String ans = "\n";
				if(chckbxAnd.isSelected()) {
					if(checkboxBYID.isSelected()) {
						ans+= "Model : "+txtEnterIdModel.getText()+" ";
					}
					if(checkBoxBYGEO.isSelected()) {
						ans+=" && LOC: Min[" + txtminLAT.getText()+","+txtminLON.getText()+","+txtminALT.getText()+"]"+
								" Max[" + txtmaxLAT.getText() +","+txtmaxLON.getText()+","+txtmaxALT.getText()+"]";
					}
					if(checkBoxByTime.isSelected()) {
						ans+=" && Time: Start["+txtStartTime.getText()+"]"+" End[" + txtEndTime.getText()+ " ]";  
					}
				}
				else if (chckbxOr.isSelected()) {
					if(checkboxBYID.isSelected()) {
						ans+= "Model : "+txtEnterIdModel.getText()+" ";
					}
					if(checkBoxBYGEO.isSelected()) {
						ans+=" || LOC: Min[" + txtminLAT.getText()+","+txtminLON.getText()+","+txtminALT.getText()+"]"+
								" Max[" + txtmaxLAT.getText() +","+txtmaxLON.getText()+","+txtmaxALT.getText()+"]";
					}
					if(checkBoxByTime.isSelected()) {
						ans+=" || Time: Start["+txtStartTime.getText()+"]"+" End[" + txtEndTime.getText()+ " ]";  
					}
				}
				else {
					if(checkboxBYID.isSelected()) {
						ans+= "Model : "+txtEnterIdModel.getText()+" ";
					}
					if(checkBoxBYGEO.isSelected()) {
						ans+=" LOC: Min[" + txtminLAT.getText()+","+txtminLON.getText()+","+txtminALT.getText()+"]"+
								" Max[" + txtmaxLAT.getText() +","+txtmaxLON.getText()+","+txtmaxALT.getText()+"]";
					}
					if(checkBoxByTime.isSelected()) {
						ans+=" Time: Start["+txtStartTime.getText()+"]"+" End[" + txtEndTime.getText()+ " ]";  
					}
				}
				JOptionPane.showMessageDialog(IOWindow,
						"Filter Info: \n "+ans,
						"Filter Info",
						JOptionPane.INFORMATION_MESSAGE,new ImageIcon(MainWindow.class.getResource("/info.png")));
			}
		});

		JButton btnFilter = new JButton("Filter");
		btnFilter.setBackground(Color.WHITE);
		btnFilter.setBounds(18, 86, 62, 55);
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IOWindow.setVisible(false);
				AlgoWindow.setVisible(false);
				FilterWindow.setVisible(true);
			}
		});
		JButton btnHome = new JButton("IO");
		btnHome.setBackground(Color.WHITE);
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
		btnAlgo.setBackground(Color.WHITE);
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
		btnExit.setBackground(Color.WHITE);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		btnExit.setBounds(18, 415, 67, 29);
		getContentPane().add(btnExit);


		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Path path = Paths.get("/Users/gal/git/Wifi_Project/toRead");
					WatchService watchService;
					watchService = path.getFileSystem().newWatchService();

					path.register(watchService,
							StandardWatchEventKinds.ENTRY_CREATE,
							StandardWatchEventKinds.ENTRY_MODIFY,
							StandardWatchEventKinds.ENTRY_DELETE);

					while(true) {
						WatchKey watchKey = null;
						try {
							watchKey = watchService.take();

						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int counter = 0;
						for (WatchEvent<?> event : watchKey.pollEvents()) {
							if(event.kind().name().equals("ENTRY_MODIFY")) {
								CreateDB.ClearDB();
								JOptionPane.showMessageDialog(IOWindow,
										"DB Was Changed !!!",
										"WOW WOW WOW",
										JOptionPane.WARNING_MESSAGE);
								for (int i = 0; i < FoldersPaths.size(); i++) {
									CreateDB cDBF = new CreateDB(FoldersPaths.get(i), 0);
									System.err.println(FoldersPaths.get(i));
								}
							}
							NumberOfMAC.setText(""+CreateDB.MacCounter);
							NumberOfRecords.setText(""+CreateDB.Records);
							repaint();
						}
						if(!watchKey.reset()) {
							watchKey.cancel();
							watchService.close();
						}
					}
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}).start();

		setBounds(100, 100, 676, 472);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static File getSelFolder() {
		return selFolder;
	}

	public static void setSelFolder(File selFolder) {
		MainWindow.selFolder = selFolder;
	}

	public static File getSelCombCSV() {
		return selCombCSV;
	}

	public static void setSelCombCSV(File selCombCSV) {
		MainWindow.selCombCSV = selCombCSV;
	}
	public static void refresh() {

	}

	public static void main(String[] args) throws IOException {
		new MainWindow().setVisible(true);

		//		Thread t = new Thread(new Watcher());
		//		t.start();



		//		WriteFile tt= new WriteFile();
		//		tt.ReWriteTheData();

		//		AlgoA a = new AlgoA(3);
		//		ReadWriteInputAlgo2 RIA2 = new ReadWriteInputAlgo2(INITIAL.getReadPathForAlgoBInput(),3);


	}
	public String FilterInfo(JCheckBox chckbxAnd , JCheckBox chckbxOr, JCheckBox BYID ,JCheckBox GEO, JCheckBox BYTime  ) {
		String ans = "\n";
		if(chckbxAnd.isSelected()) {
			if(BYID.isSelected()) {
				ans+= "Model : "+txtEnterIdModel.getText()+" ";
			}
			if(GEO.isSelected()) {
				ans+=" && LOC: Min[" + txtminLAT.getText()+","+txtminLON.getText()+","+txtminALT.getText()+"]"+
						" Max[" + txtmaxLAT.getText() +","+txtmaxLON.getText()+","+txtmaxALT.getText()+"]";
			}
			if(BYTime.isSelected()) {
				ans+=" && Time: Start["+txtStartTime.getText()+"]"+" End[" + txtEndTime.getText()+ " ]";  
			}
		}
		else if (chckbxOr.isSelected()) {
			if(BYID.isSelected()) {
				ans+= "Model : "+txtEnterIdModel.getText()+" ";
			}
			if(GEO.isSelected()) {
				ans+=" && LOC: Min[" + txtminLAT.getText()+","+txtminLON.getText()+","+txtminALT.getText()+"]"+
						" Max[" + txtmaxLAT.getText() +","+txtmaxLON.getText()+","+txtmaxALT.getText()+"]";
			}
			if(BYTime.isSelected()) {
				ans+=" && Time: Start["+txtStartTime.getText()+"]"+" End[" + txtEndTime.getText()+ " ]";  
			}
		}
		else {
			if(BYID.isSelected()) {
				ans+= "Model : "+txtEnterIdModel.getText()+" ";
			}
			if(GEO.isSelected()) {
				ans+=" && LOC: Min[" + txtminLAT.getText()+","+txtminLON.getText()+","+txtminALT.getText()+"]"+
						" Max[" + txtmaxLAT.getText() +","+txtmaxLON.getText()+","+txtmaxALT.getText()+"]";
			}
			if(BYTime.isSelected()) {
				ans+=" && Time: Start["+txtStartTime.getText()+"]"+" End[" + txtEndTime.getText()+ " ]";  
			}
		}
		return ans;
	}
	public JLabel getLblSaveAsKml() {
		return SaveKMLtxt;
	}
	public JLabel getLabel_2() {
		return KMLIcon;
	}
}