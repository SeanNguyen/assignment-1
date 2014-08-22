package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.KwicView;
import model.KwicModel;
import model.Pair;

public class KwicControl {
	private KwicModel model;
	private KwicView view;
	
	public KwicControl() {
		model = new KwicModel();
		view =  new KwicView();
	}
	
	//Input
	private void input (String inputText, String ignoredText) {
		inputText.toLowerCase();
		ignoredText.toLowerCase();
		inputTextToModel(inputText);
		inputIgnoredWordsToModel(ignoredText);
	}
	
	//Circular Shift
	private void circularShift () {
		List <Pair<Integer, Integer>> indexesOfCircularShift = new ArrayList<Pair<Integer,Integer>>();
		for (List <String> line : this.model.GetLines()) {
			for (String word : line) {
				if (isIgnoredWord(word))
					continue;
				int lineIndex = model.GetLines().indexOf(line);
				int wordIndex = line.indexOf(word);
				Pair<Integer, Integer> index = new Pair<Integer, Integer>(lineIndex, wordIndex); 
				indexesOfCircularShift.add(index);
			}
		}
		model.SetIndexes(indexesOfCircularShift);
	}
	
	//Alphabetizer
	private void Alphabetize() {
		int indexSize = model.GetIndexes().size();
		
		//this is normal sort, can change later
		for (int i = 0; i < indexSize; i++) {
			for (int j = 0; j < indexSize; j++) {
				if (j == indexSize - 1)
					continue;
				Pair<Integer, Integer> currentIndex = model.GetIndexes().get(j);
				Pair<Integer, Integer> nextIndex = model.GetIndexes().get(j + 1);
				int lineIndex = currentIndex.getFirst();
				int wordIndex = currentIndex.getSecond();
				int nextLineIndex = nextIndex.getFirst();
				int nextWordIndex = nextIndex.getSecond();
				String currentword = model.getWordByIndex(lineIndex, wordIndex);
				String nextWord = model.getWordByIndex(nextLineIndex, nextWordIndex);
				if (currentword.compareToIgnoreCase(nextWord) > 0) {
					model.SwapIndex(j, j + 1);
				}
			}
		}
	}
	
	//Output
	
	
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
			List <String> words = splitTextToStringList(line, " ");
			linesAndwords.add(words);
		}
		model.SetLines(linesAndwords);
	}
	
	private void inputIgnoredWordsToModel (String ignoredText) {
		List <String> ignoredWords = splitTextToStringList(ignoredText, " ");
		this.model.SetIgnoredWords(ignoredWords);
	}

	private boolean isIgnoredWord (String word) {
		for (String ignoredWord : model.GetIgnoredWords()) {
			if (word.equalsIgnoreCase(ignoredWord))
				return true;
		}
		return false;
	}

}
