package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.AlphabeticalSorter;
import model.CircularShifterIgnoreWord;
import model.KwicModel;
import model.Shifter;
import model.Sorter;

public class KwicController {
	private KwicModel model;
	@FXML
	private TextArea inputText;
	@FXML
	private TextArea ignoredWordsText;
	@FXML
	private TextArea resultText;
	
	public KwicController() {
		this.model = new KwicModel();
	}
	
	@FXML
    private void initialize() {
		this.resultText.setEditable(false);
    }
	
	@FXML
	private void handleGetResultButtonClick() {
		model.clearData();
		String inputText = this.inputText.getText();
		String ignoredWords = this.ignoredWordsText.getText();
		input(inputText, ignoredWords);
	}
	
	//Input
	private void input (String inputText, String ignoredText) {
		inputText = inputText.toLowerCase();
		ignoredText = ignoredText.toLowerCase();
		convertTextToList(inputText);
		inputIgnoredWordsToModel(ignoredText);
	}
	
	//Output
	private void output (List <List <String>> lines) {
		String result = "";
		
		for (List<String> line : lines){
			result += getLineWithKeyInFront(line);
		}
		this.resultText.setText(result);
	}
	
	//Private helper methods
	private List <String> splitTextToStringList (String text, String regex) {
		String[] textArray = text.split(regex);
		List <String> textList = new LinkedList<String>(Arrays.asList(textArray));
		textList.removeAll(Arrays.asList("", null));
		for (int i = 0; i < textList.size(); i++) {
			String trimedWord = textList.get(i).trim();
			textList.set(i, trimedWord);
		}
		return textList;
	}
	
	private List<List<String>> convertTextToList(String inputText) {
		List <String> lines = splitTextToStringList(inputText, "\n");
		List < List <String>> linesAndWords = new ArrayList<List<String>>();
		for (String line : lines) {
			if (line == null || line.length() <= 0)
				continue;
			List <String> words = splitTextToStringList(line, Configurations.WHITESPACE); 
			linesAndWords.add(words);
		}
		return linesAndWords;
	}
	
	private void inputIgnoredWordsToModel (String ignoredText) {
		List <String> ignoredWords = splitTextToStringList(ignoredText, "\\W"); // \\W means any non-word char
		this.model.setIgnoredWords(ignoredWords);
	}

	private boolean isIgnoredWord (String word) {
		for (String ignoredWord : model.getIgnoredWords()) {
			if (word.equalsIgnoreCase(ignoredWord))
				return true;
		}
		return false;
	}

	private String getLineWithKeyInFront (List <String> line) {
		
		String result = "";
		for (String word : line){
			result += word;
		}
		
		result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
		return result;
	}
	
	private void calculateResult() {
		String inputText = this.inputText.getText();
		String ignoredText = this.ignoredWordsText.getText();
		model.clearData();
		List <List <String>> myLines = convertTextToList(inputText);
		List <String> ignoreList = splitTextToStringList(ignoredText, Configurations.WHITESPACE);
		
		//Shifting the lines
		Shifter shifter = new CircularShifterIgnoreWord(ignoreList);
		shifter.inputLines(myLines);
		myLines = shifter.getOutputLines();
		
		//Sort by alphabets
		Sorter sorter = new AlphabeticalSorter();
		sorter.inputLines(myLines);
		myLines = sorter.getOutputLines();
		
		output(myLines);
	}
}
