package com.buam.nlcsv;

import com.buam.utils.csv.CsvConfiguration;
import com.buam.utils.csv.CsvUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NLCSVWindow extends JFrame {

	private JTextField textField;

	private static CsvConfiguration csvConfig;
	private JTextField xOffsetField;
	private JTextField yOffsetField;
	private JTextField skipVerticesField;
	private JTextField railDistanceField;

	private static HelpDialog dialog;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		dialog = new HelpDialog();
		csvConfig = new CsvConfiguration(new String[] {"No.", "PosX", "PosY", "PosZ","FrontX", "FrontY", "FrontZ","LeftX", "LeftY", "LeftZ","UpX", "UpY", "UpZ"}, '\t');
		EventQueue.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				NLCSVWindow frame = new NLCSVWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NLCSVWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NLCSVWindow.class.getResource("/assets/icon.png")));
		
		setTitle("NoLimits Track to CSV Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 330);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCsvFile = new JLabel("CSV File:");
		panel.add(lblCsvFile);
		
		textField = new JTextField();
		textField.setToolTipText("File name");
		panel.add(textField);
		textField.setColumns(26);
		
		JButton btnChooseCsvFile = new JButton();
		Action action_1 = new SwingAction_1();
		btnChooseCsvFile.setAction(action_1);
		btnChooseCsvFile.setText("Choose");
		panel.add(btnChooseCsvFile);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Settings");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		JLabel lblXoffset = new JLabel("X-Offset:");
		panel_2.add(lblXoffset);
		
		xOffsetField = new JTextField();
		xOffsetField.setHorizontalAlignment(SwingConstants.RIGHT);
		xOffsetField.setText("0");
		panel_2.add(xOffsetField);
		xOffsetField.setColumns(5);

		JLabel lblYoffset = new JLabel("Y-Offset:");
		panel_2.add(lblYoffset);
		
		yOffsetField = new JTextField();
		panel_2.add(yOffsetField);
		yOffsetField.setText("0");
		yOffsetField.setHorizontalAlignment(SwingConstants.RIGHT);
		yOffsetField.setColumns(5);

		JPanel panel_7= new JPanel();
		panel_1.add(panel_7);

		JLabel lblSkipVertices = new JLabel("Skip Vertices:");
		panel_7.add(lblSkipVertices);

		skipVerticesField = new JTextField();
		panel_7.add(skipVerticesField);
		skipVerticesField.setText("0");
		skipVerticesField.setHorizontalAlignment(SwingConstants.RIGHT);
		skipVerticesField.setColumns(5);

		JLabel lblRailDistance = new JLabel("Rail Distance:");
		panel_7.add(lblRailDistance);

		railDistanceField = new JTextField();
		railDistanceField.setToolTipText("Radial distance from the center spline. If > 0, separate left and right rail CSV files are also generated.");
		panel_7.add(railDistanceField);
		railDistanceField.setText("0");
		railDistanceField.setHorizontalAlignment(SwingConstants.RIGHT);
		railDistanceField.setColumns(5);

		Component verticalStrut = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut);
		
		JButton btnConvert = new JButton();
		panel_1.add(btnConvert);
		Action action_2 = new SwingAction_2();
		btnConvert.setAction(action_2);
		btnConvert.setText("Convert");
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton helpBtn = new JButton("?");
		helpBtn.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(helpBtn, BorderLayout.EAST);
		Action action_3 = new SwingAction_3();
		helpBtn.setAction(action_3);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Light Pattern Creator File (.lwo):");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5, BorderLayout.SOUTH);
		
		textField_1 = new JTextField();
		panel_5.add(textField_1);
		textField_1.setColumns(32);
		
		JButton btnChooseobjFile = new JButton();
		panel_5.add(btnChooseobjFile);
		Action action = new SwingAction();
		btnChooseobjFile.setAction(action);
		btnChooseobjFile.setText("Choose");
		
		//setActions(btnChoosecsvFile, btnConvert, btnChooseobjFile, this);
		
		/*btnChoosecsvFile.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.chooseCsvFilePath(instance);
			}
		});
		btnConvert.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals("")) {
					main.process(textField.getText(), instanceWindow);
				} else {
					JOptionPane.showMessageDialog(instance, "Please choose a CSV filename", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		btnChooseobjFile.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.chooseObjFile(instance);
			}
		});*/
	}

	public void complete() {
		JOptionPane.showMessageDialog(this, "LWO-File successfully converted to NL2 CSV!");
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			chooseLwoFile();
		}
	}
	
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			chooseCsvFilePath();
		}
	}

	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "SwingAction_2");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			float xOffset = 0f;
			try {
				xOffset = Float.parseFloat(xOffsetField.getText());
			} catch(NumberFormatException nfe) {
				showErrorMessage("X-Offset: Invalid number (Examples: 0, 0.0, 1.5 ...)");
				return;
			}
			float yOffset = 0f;
			try {
				yOffset = Float.parseFloat(yOffsetField.getText());
			} catch(NumberFormatException nfe) {
				showErrorMessage("Y-Offset: Invalid number (Examples: 0, 0.0, 1.5 ...)");
				return;
			}
			int skipVertices = 0;
			try {
				skipVertices = Integer.parseInt(skipVerticesField.getText());
			} catch(NumberFormatException nfe) {
				showErrorMessage("Skip Vertices: Invalid number (Examples: 0, 1, 2 ...)");
				return;
			}
			float railDistance = 0f;
			try {
				railDistance = Float.parseFloat(railDistanceField.getText());
			} catch(NumberFormatException nfe) {
				showErrorMessage("Rail Distance: Invalid number (Examples: 0, 0.5, 1.25 ...)");
				return;
			}
			if(railDistance < 0) {
				showErrorMessage("Rail Distance: Value must be 0 or positive");
				return;
			}
			process(textField_1.getText(), textField.getText(), xOffset, yOffset, skipVertices, railDistance);
		}
	}
	
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	

	public void chooseLwoFile() {
		JFileChooser pathChooser = new JFileChooser();
		FileNameExtensionFilter objFilter = new FileNameExtensionFilter("LightWave-Object File", "lwo");
		pathChooser.setFileFilter(objFilter);
		int returnVal = pathChooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			textField_1.setText(pathChooser.getSelectedFile().getAbsolutePath());
		}
	}
	
	public void chooseCsvFilePath() {
		JFileChooser csvChooser = new JFileChooser();
		FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("Comma-Seperated-Values File (csv)", "csv");
		csvChooser.setFileFilter(csvFilter);
		int returnVal = csvChooser.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String csvPath = csvChooser.getSelectedFile().getAbsolutePath();
			if(!csvPath.substring(csvPath.length()-4).equals(".csv")) csvPath += ".csv";
				
			textField.setText(csvPath);
		}
	}
	
	public void process(String path, String csvFilePath, float xOffset, float yOffset, int skipVertices, float railDistance) {
		if(path.equals("")) {
			JOptionPane.showMessageDialog(this, "Please choose an LWO-File", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} else if(csvFilePath.equals("")) {
			JOptionPane.showMessageDialog(this, "Please choose a path for the CSV-File", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(path.substring(path.length()-4).equals(".lwo")) {
			processLwo(path, csvFilePath, xOffset, yOffset, skipVertices, railDistance);
		} else {
			showErrorMessage("Specified file is not a .lwo file");
		}
	}

	public void processLwo(String path, String csvFilePath, float xOffset, float yOffset, int skipVertices, float railDistance) {

		LWOLoader loader = new LWOLoader(path, this);
		List<Vector3> vertices = loader.vertices;

		List<Vector3[]> spline = new ArrayList<>();

		int counter = 0;
		Vector3[] temp = new Vector3[8];

		for(Vector3 vertex : vertices) {
			counter++;
			temp[counter-1] = vertex;

			if(counter == 8) {
				counter = 0;
				spline.add(new Vector3[] {temp[1], temp[0], temp[5], temp[3]});
			}
		}
		if(skipVertices != 0) {
			List<Vector3[]> toRemove = new ArrayList<>();
			for(int i = 0; i < spline.size(); i++) {
				if(i % (skipVertices + 1) != 0) {
					toRemove.add(spline.get(i));
				}
			}
			for(Vector3[] vertex : toRemove) {
				spline.remove(vertex);
			}
		}

		// Center spline (existing behaviour).
		CsvUtils.write(csvConfig, buildCsvData(spline, xOffset, yOffset), csvFilePath);

		// When a rail distance is given, also export a left and a right rail spline.
		// Each rail position is the center position shifted along the node's local
		// left unit vector (which already rolls/banks with the track), so the rails
		// stay parallel to the center spline - and to each other - at every point,
		// including through turns and banked sections. The orientation vectors
		// (Left/Up) are identical for all three splines.
		if(railDistance > 0) {
			CsvUtils.write(csvConfig, buildCsvData(spline, xOffset + railDistance, yOffset), railFilePath(csvFilePath, "left"));
			CsvUtils.write(csvConfig, buildCsvData(spline, xOffset - railDistance, yOffset), railFilePath(csvFilePath, "right"));
		}

		this.complete();
	}

	/**
	 * Builds the NL2 CSV rows for the given spline.
	 *
	 * @param leftOffset distance to shift each node's position along its local left unit vector
	 * @param upOffset   distance to shift each node's position along its local up unit vector
	 */
	private List<List<String>> buildCsvData(List<Vector3[]> spline, float leftOffset, float upOffset) {
		List<List<String>> csvData = new ArrayList<>();

		for(Vector3[] node : spline) {
			List<String> csvLine = new ArrayList<>();

			Vector3 pos = Vector3.getMiddleValue(node);
			Vector3 left = Vector3.getLeftVector(node, pos);
			Vector3 up = Vector3.getUpVector(node, pos);

			pos = new Vector3((pos.getX() + left.getX()*leftOffset), (pos.getY() + left.getY()*leftOffset), (pos.getZ() + left.getZ()*leftOffset));
			pos = new Vector3((pos.getX() + up.getX()*upOffset), (pos.getY() + up.getY()*upOffset), (pos.getZ() + up.getZ()*upOffset));

			// No.
			csvLine.add(Integer.toString(spline.indexOf(node) + 1));
			// PosX
			csvLine.add(fd(Float.toString(pos.getX())));
			// PosY
			csvLine.add(fd(Float.toString(pos.getY())));
			// PosZ
			csvLine.add(fd(Float.toString(pos.getZ())));
			// FrontX
			csvLine.add("0");
			// FrontY
			csvLine.add("0");
			// FrontZ
			csvLine.add("0");
			// LeftX
			csvLine.add(fd(Float.toString(left.getX())));
			// LeftY
			csvLine.add(fd(Float.toString(left.getY())));
			// LeftZ
			csvLine.add(fd(Float.toString(left.getZ())));
			// UpX
			csvLine.add(fd(Float.toString(up.getX())));
			// UpY
			csvLine.add(fd(Float.toString(up.getY())));
			// UpZ
			csvLine.add(fd(Float.toString(up.getZ())));

			csvData.add(csvLine);
		}

		return csvData;
	}

	/**
	 * Derives a rail file path from the chosen center CSV path by inserting a
	 * suffix (e.g. "myTrack.csv" -> "myTrack_left.csv").
	 */
	private String railFilePath(String csvFilePath, String suffix) {
		if(csvFilePath.toLowerCase().endsWith(".csv")) {
			return csvFilePath.substring(0, csvFilePath.length() - 4) + "_" + suffix + ".csv";
		}
		return csvFilePath + "_" + suffix + ".csv";
	}
	
	private String fd(String string) {
		int decimalIndex = string.indexOf('.');
		if(string.length() >= decimalIndex + 6) {
			return string.substring(0, decimalIndex + 5);
		}
		return string;
	}

	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "?");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			dialog.setVisible(true);
		}
	}
}
