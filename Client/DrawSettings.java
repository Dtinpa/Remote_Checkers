import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


//Responsible for creating, showing, and hiding the Settings GUI.
public class DrawSettings extends DrawUI
{
	private JPanel panel;			// main panel of content
	private JLabel lblTitle;
	private JLabel lblIP;
	private JTextField txtIP;
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
		lblIP.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblIP.setMinimumSize(labelSize);
		lblIP.setMaximumSize(labelSize);
		lblIP.setPreferredSize(labelSize);
		
		txtIP = new JTextField();
		txtIP.setFont(BUTTON_FONT);
		txtIP.setMargin(new Insets(0,5,0,5));
		txtIP.setMinimumSize(labelSize);
		txtIP.setMaximumSize(labelSize);
		txtIP.setPreferredSize(labelSize);
		
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
	
	// creates an intermediate panel to help align components correctly
		public JPanel createSettingsEntryPanel()
		{
			JPanel settingsEntryPanel = new JPanel();
			settingsEntryPanel.setLayout(new BoxLayout(settingsEntryPanel, BoxLayout.X_AXIS));
			
			settingsEntryPanel.add(lblIP);
			settingsEntryPanel.add(txtIP);

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
	
	// returns the user-entered value
	public String getIP()
	{ return txtIP.getText().trim(); }
	
	public void populateIP(String ip)
	{ txtIP.setText(ip); }
	
	// display error - field left blank
	public void errorBlank()
	{
		JOptionPane.showMessageDialog(frame, "Server IP cannot be left blank!",
				"Update Settings Error", JOptionPane.ERROR_MESSAGE);
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
