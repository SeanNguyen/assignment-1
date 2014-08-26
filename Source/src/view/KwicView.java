package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import control.Configurations;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;


public class KwicView extends JFrame{
	JPanel mainPanel = new JPanel();
	JTextArea titleText = new JTextArea(Configurations.INPUTTEXT_ROWSIZE, Configurations.INPUTTEXT_COLSIZE);
	JTextArea ignoreWordsText = new JTextArea(Configurations.INPUTTEXT_ROWSIZE, Configurations.INPUTTEXT_COLSIZE);
	JTextArea resultText = new JTextArea(Configurations.RESULTTEXT_ROWSIZE, Configurations.RESULTTEXT_COLSIZE);
	JButton getResultButton = new JButton (Configurations.GETRESULT_BUTTON_TEXT);
	JLabel inputLabel = new JLabel(Configurations.TITLE_DEFAULTTEXT);
	JLabel ignoredWordLabel = new JLabel(Configurations.IGNOREDWORD_DEFAULTTEXT);
	JLabel resultLabel = new JLabel(Configurations.RESULT_DEFAULTTEXT);
	
	public KwicView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Configurations.MAINFRAME_WIDTH, Configurations.MAINFRAME_HEIGHT);
		
		mainPanel.add(inputLabel);
		mainPanel.add(titleText);
		mainPanel.add(ignoredWordLabel);
		mainPanel.add(ignoreWordsText);
		mainPanel.add(resultLabel);
		mainPanel.add(resultText);
		mainPanel.add(getResultButton);
		this.add(mainPanel);
		
	}
	
	public String getTitleText () {
		return titleText.getText();
	}
	
	public String getIgnoredWords () {
		return ignoreWordsText.getText();
	}
	
	public void setResultText (String result) {
		this.resultText.setText(result);
	}
	
	public void addResultButtonListener (ActionListener listenForGetResultButton) {
		getResultButton.addActionListener(listenForGetResultButton);
	}
	
	public void displayErrorMessage (String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
