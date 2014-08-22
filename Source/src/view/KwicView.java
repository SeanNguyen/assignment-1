package view;

import java.awt.event.ActionListener;

import control.Configurations;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class KwicView extends JFrame{
	JPanel mainPanel = new JPanel();
	JTextArea titleText = new JTextArea(Configurations.INPUTTEXT_ROWSIZE, Configurations.INPUTTEXT_COLSIZE);
	JTextArea ignoreWordsText = new JTextArea(Configurations.INPUTTEXT_ROWSIZE, Configurations.INPUTTEXT_COLSIZE);
	JTextArea resultText = new JTextArea(Configurations.RESULTTEXT_ROWSIZE, Configurations.RESULTTEXT_COLSIZE);
	JButton getResultButton = new JButton (Configurations.GETRESULT_BUTTON_TEXT);
	
	public KwicView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Configurations.MAINFRAME_WIDTH, Configurations.MAINFRAME_HEIGHT);
		mainPanel.add(titleText);
		mainPanel.add(ignoreWordsText);
		mainPanel.add(getResultButton);
		mainPanel.add(resultText);
		this.add(mainPanel);
		
		titleText.setText(Configurations.TITLE_DEFAULTTEXT);
		ignoreWordsText.setText(Configurations.IGNOREDWORD_DEFAULTTEXT);
		resultText.setText(Configurations.RESULT_DEFAULTTEXT);
	}
	
	public String GetTitleText () {
		return titleText.getText();
	}
	
	public String GetIgnoredWords () {
		return ignoreWordsText.getText();
	}
	
	public void SetResultText (String result) {
		this.resultText.setText(result);
	}
	
	public void addResultButtonListener (ActionListener listenForGetResultButton) {
		getResultButton.addActionListener(listenForGetResultButton);
	}
	
	public void displayErrorMessage (String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
