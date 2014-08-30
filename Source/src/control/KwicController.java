package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.KwicModel;
import model.Pair;

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
	private void handleGetResultButtonClick() {
		model.clearData();
		String inputText = this.inputText.getText();
		String ignoredWords = this.ignoredWordsText.getText();
		input(inputText, ignoredWords);
		circularShift();
		alphabetize();
		output();
	}
	
	//Input
	private void input (String inputText, String ignoredText) {
		inputText = inputText.toLowerCase();
		ignoredText = ignoredText.toLowerCase();
		inputTextToModel(inputText);
		inputIgnoredWordsToModel(ignoredText);
	}
	
	//Circular Shift
	private void circularShift () {
		List <Pair<Integer, Integer>> indexesOfCircularShift = new ArrayList<Pair<Integer,Integer>>();
		for (List <String> line : this.model.getLines()) {
			for (String word : line) {
				if (isIgnoredWord(word) || word.matches("\\W"))
					continue;
				int lineIndex = model.getLines().indexOf(line);
				int wordIndex = line.indexOf(word);
				Pair<Integer, Integer> index = new Pair<Integer, Integer>(lineIndex, wordIndex); 
				indexesOfCircularShift.add(index);
			}
		}
		model.setIndexes(indexesOfCircularShift);
	}
	
	//Alphabetizer
	private void alphabetize() {
		for (Pair<Integer, Integer> index : model.getIndexes()) {
			model.insertToAlphabeticalIndexList(index);
		}
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
		List <String> textList = Arrays.asList(textArray);
		return removeEmptyElements(textList);
	}
	
	private List <String> removeEmptyElements (List <String> elements) {
		for (int i = 0; i < elements.size(); i++) {
			String element = elements.get(i);
			if (element == null || element == "") {
				elements.remove(i);
				i--;
			}
		}
		return elements;
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

	private boolean isIgnoredWord (String word) {
		for (String ignoredWord : model.getIgnoredWords()) {
			if (word.equalsIgnoreCase(ignoredWord))
				return true;
		}
		return false;
	}

	private String getLineWithKeyInFront (List <String> line, int index) {
		String result = "";
		for (int i = 0; i < line.size(); i++) {
			if (index == i)
				result = line.get(index) + " " + result;
			else
				result += line.get(i) + " ";
		}
		result = Character.toUpperCase(result.charAt(0)) + result.substring(1);
		return result;
	}
	
}
