package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlphabeticalSorter extends Sorter {

	private List<String> lines;
	private String WHITESPACE = " "; //TODO To remove too
	
	//TODO Change all the List<List<String> to List <String>
	@Override
	public void inputLines(List<List<String>> lines) {
	
		this.lines = new ArrayList<String>();
		
		for (List<String> line : lines){
			String tempString = "";
			for(String word: line){
				tempString.concat(word);
			}
			this.lines .add(tempString);
		}
		
	}
	
	private void sortByAlpha(){
		Collections.sort(this.lines);
	}

	@Override
	public List<List<String>> getOutputLines() {
		
		sortByAlpha();
		
		List <List <String>> outputLines = new ArrayList <List<String>>();
		for (String line: this.lines){
			outputLines.add(splitTextToStringList(line, WHITESPACE));
			
		}
		
		return outputLines;
	}
	
	//TODO The following two methods should not be needed (i.e: should remove) after changing to List<String>
	private List <String> splitTextToStringList (String text, String regex) {
		String[] textArray = text.split(regex);
		List <String> textList = Arrays.asList(textArray);
		return textList;
	}

}
