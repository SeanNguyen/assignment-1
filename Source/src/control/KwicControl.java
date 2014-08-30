package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.KwicView;
import model.AlphabeticalSorter;
import model.CircularShifterIgnoreWord;
import model.KwicModel;
import model.Pair;
import model.Shifter;
import model.Sorter;

public class KwicControl {
	private KwicModel model;
	private KwicView view;
	private String WHITESPACE = " ";
	
	public static void main (String[] args) {
		KwicModel model = new KwicModel();
		KwicView view = new KwicView();
		KwicControl control = new KwicControl(view, model);
		
		view.setVisible(true);
	}
	
	public KwicControl(KwicView view, KwicModel model) {
		this.view = view;
		this.model = model;
		
		view.addResultButtonListener(new GetResultListener());
	}
	
	//Input
	private void input (String inputText, String ignoredText) {
		inputText = inputText.toLowerCase();
		ignoredText = ignoredText.toLowerCase();
		convertTextToList(inputText);
		inputIgnoredWordsToModel(ignoredText);
	}
	
	//Alphabetizer
	private void alphabetize() {
		for (Pair<Integer, Integer> index : model.getIndexes()) {
			model.insertToAlphabeticalIndexList(index);
		}
	}
	
	//Output
	private void output (List <List <String>> lines) {
		String result = "";
		
		for (List<String> line : lines){
			result += getLineWithKeyInFront(line);
		}
		
		view.setResultText(result);
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
	
	private List<List<String>> convertTextToList(String inputText) {
		List <String> lines = splitTextToStringList(inputText, "\n");
		List < List <String>> linesAndWords = new ArrayList<List<String>>();
		for (String line : lines) {
			if (line == null || line.length() <= 0)
				continue;
			List <String> words = splitTextToStringList(line, WHITESPACE); 
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
		String inputText = view.getTitleText();
		String ignoredText = view.getIgnoredWords();
		model.clearData();
		System.out.println("Input: " + inputText);
		System.out.println("Ignore: " + ignoredText);
		
		List <List <String>> myLines = convertTextToList(inputText);
		List <String> ignoreList = splitTextToStringList(ignoredText,WHITESPACE);
		
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
	
	private class GetResultListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			calculateResult();
		}
		
	}
	
}
