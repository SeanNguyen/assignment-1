package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.AlphabeticalSorter;
import model.CircularShifterIgnoreWord;
import model.KwicModel;
import model.Pair;
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
	@FXML
	private Text noOfInput;
	@FXML
	private Text noOfIgnoredWord;
	@FXML
	private Text noOfResult;
	
	
	public KwicController() {
		this.model = new KwicModel();
	}
	
	@FXML
    private void initialize() {
		this.resultText.setEditable(false);
    }
	
	//Event handlers
	
	@FXML
	private void handleGetResultButtonClick() {
		calculateResult();
		
		noOfInput.setText("" + model.getLines().size());
		noOfIgnoredWord.setText("" + model.getIgnoredWords().size());
		noOfResult.setText("" + model.getAlphabetialIndexes().size());
	}
	
	@FXML
	private void handleClearInputClick() {
		this.inputText.setText(Configurations.EMPTYTEXT);
		this.noOfInput.setText(Configurations.EMPTYTEXT);
	}

	@FXML
	private void handleClearIgnoredWordsClick() {
		this.ignoredWordsText.setText(Configurations.EMPTYTEXT);
		this.noOfIgnoredWord.setText(Configurations.EMPTYTEXT);
	}

	@FXML
	private void handleClearResultClick() {
		this.resultText.setText(Configurations.EMPTYTEXT);
		this.noOfResult.setText(Configurations.EMPTYTEXT);
	}
	
	//Input
	private void input (String inputText, String ignoredText) {
		inputText = inputText.toLowerCase();
		ignoredText = ignoredText.toLowerCase();
		inputTextToModel(inputText);
		inputIgnoredWordsToModel(ignoredText);
	}
	
	//Output
	private void output () {
		String result = "";
		List < List < String > > lines = model.getLines();
		List < Pair<Integer, Integer>> alphabetialIndex = model.getAlphabetialIndexes();
		
		for (Pair<Integer, Integer> index : alphabetialIndex) {
			int lineIndex = index.getFirst();
			int wordIndex = index.getSecond();
			List <String> line = lines.get(lineIndex);
			result += getLineWithKeyInFront(line, wordIndex) + "\n";
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
	
	private void inputTextToModel(String inputText) {
		List <String> lines = splitTextToStringList(inputText, "\n");
		List < List <String>> linesAndwords = new ArrayList<List<String>>();
		for (String line : lines) {
			if (line == null || line.length() <= 0)
				continue;
			List <String> words = splitTextToStringList(line, " "); 
			linesAndwords.add(words);
		}
		model.setLines(linesAndwords);
	}
	
	private void inputIgnoredWordsToModel (String ignoredText) {
		List <String> ignoredWords = splitTextToStringList(ignoredText, "\\W"); // \\W means any non-word char
		this.model.setIgnoredWords(ignoredWords);
	}

	private String getLineWithKeyInFront (List <String> line, int index) {
		String result = "";
		for (int i = 0; i < line.size(); i++) {
			if (index == i)
				result = line.get(index) + " " + result;
			else
				result += line.get(i) + " ";
		}
		
		if (!result.isEmpty()) {
			result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
		}
		return result;
	}
	
	private void calculateResult() {
		model.clearData();
		
		//Input
		String inputText = this.inputText.getText();
		String ignoredText = this.ignoredWordsText.getText();
		input(inputText, ignoredText);
		
		//Shifting the lines
		Shifter shifter = new CircularShifterIgnoreWord(this.model);
		shifter.process();
		
		//Sort by alphabets
		Sorter sorter = new AlphabeticalSorter(this.model);
		sorter.process();
		
		//Output
		output();
	}
}
