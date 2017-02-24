import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


//Responsible for creating, showing, and hiding the Settings GUI.
public class DrawSettings extends DrawUI
{
	private JPanel panel;			// main panel of content
	private JLabel lblTitle;
	private JLabel lblIP;
	private JTextField txtIP;
	private JLabel lblPort;
	private JTextField txtPort;
	private JButton btnSave;		// saves settings information and moves to MainScreen
	private JButton btnCancel;		// moves to MainScreen
	
	
	DrawSettings()		// calls createElements(), drawElements(), and registerEventHandlers()
	{ super(); }
	
	@Override
	public void createElements()		// initializes each GUI element
	{
		panel = new JPanel(); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

		lblTitle = new JLabel("Settings");
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(TITLE_FONT);
		
		lblIP = new JLabel("Server IP Address:");
		lblIP.setFont(BUTTON_FONT);
		
		txtIP = new JTextField();
		txtIP.setFont(BUTTON_FONT);
		
		lblPort = new JLabel("Server Port:");
		lblPort.setFont(BUTTON_FONT);
		
		txtPort = new JTextField();
		txtPort.setFont(BUTTON_FONT);
		
		btnSave = new JButton("Save");
		btnSave.setActionCommand("Save");
		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSave.setFont(BUTTON_FONT);
		btnSave.setToolTipText("Save Settings information");
		btnSave.setMinimumSize(buttonSize);
		btnSave.setMaximumSize(buttonSize);
		btnSave.setPreferredSize(buttonSize);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setActionCommand("Cancel");
		btnCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCancel.setFont(BUTTON_FONT);
		btnCancel.setToolTipText("Return to Main Screen");
		btnCancel.setMinimumSize(buttonSize);
		btnCancel.setMaximumSize(buttonSize);
		btnCancel.setPreferredSize(buttonSize);
	}
	
	@Override
	public void drawElements()			// places GUI elements
	{
		panel.add(Box.createRigidArea(new Dimension(0, 50)));
		panel.add(lblTitle);
		panel.add(Box.createRigidArea(new Dimension(0, 120)));
		panel.add(createSettingsEntryPanel());
		panel.add(Box.createRigidArea(new Dimension(0, 120)));
		panel.add(createButtonsPanel());
		frame.add(panel);
	}
	
	// Creates a GroupLayout to properly align labels and text boxes.  Repurposed code from
		// an earlier project.
	public JPanel createSettingsEntryPanel()
	{
		JPanel settingsEntryPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(settingsEntryPanel);
		settingsEntryPanel.setLayout(groupLayout);
			
		// creates a sequential group for the horizontal axis.  The sequential group contains
			// two parallel groups, one containing the labels and the other the second column
			// of objects.  Putting the labels in a parallel group along the horizontal axis
			// positions them at the same x-location.
		GroupLayout.SequentialGroup rows = groupLayout.createSequentialGroup();
		rows.addGap(50);
		rows.addGroup(groupLayout.createParallelGroup().
				addComponent(lblIP).
				addComponent(lblPort));
		rows.addGap(25);
		rows.addGroup(groupLayout.createParallelGroup().
				addComponent(txtIP).
				addComponent(txtPort));
		rows.addGap(50);
		groupLayout.setHorizontalGroup(rows);

		// creates a sequential group for the vertical axis.  The sequential group contains
			// parallel groups that align the contents along the baseline.  Each parallel group
			// contains a label and the labeled component.  By using a sequential group, the labels
			// and corresponding components are positioned vertically one after another.
		GroupLayout.SequentialGroup columns = groupLayout.createSequentialGroup();
		columns.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
				addComponent(lblIP).addComponent(txtIP));
		columns.addGap(25);
		columns.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
				addComponent(lblPort).addComponent(txtPort));
		groupLayout.setVerticalGroup(columns);
		
		return settingsEntryPanel;
	}
	
	// creates an intermediate panel to help align components correctly
	public JPanel createButtonsPanel()
	{
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
		
		buttonsPanel.add(btnCancel);
		buttonsPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		buttonsPanel.add(btnSave);

		return buttonsPanel;
	}
	
	@Override
	public void registerEventHandlers()		// adds click handlers to buttons
	{
		btnSave.addActionListener(Settings.getSettings());
		btnCancel.addActionListener(Settings.getSettings());
	}
	
	@Override
	public void show()
	{
		frame.add(panel);
        frame.revalidate();
        frame.repaint();
	}
	
	@Override
	public void hide()
	{ frame.remove(panel); }
}
