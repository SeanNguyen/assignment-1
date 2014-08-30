package model;

import java.util.ArrayList;
import java.util.List;

public class CircularShifter extends Shifter{

	private String NON_CHAR = "\\W"; 
	private List<List<String>>lines;
	
	public CircularShifter(){
		this.lines = new ArrayList <List<String>>();
	}
	
	@Override
	public void inputLines(List <List<String>> lines) {
		this.lines = lines;
		
	}

	@Override
	public List<List<String>> getOutputLines() {
		circularShift();
		return this.lines;
	}
	
	//Circular Shift
	private void circularShift () {
		List <List <String>> outputLines = new ArrayList <List<String>>();
		for (List <String> line : this.lines) {
			List <String> tempLine = new ArrayList<String>();
			for (String word : line) {
				
				//Shift the list
				tempLine = line.subList(1, line.size()-1);
				//tempLine.add(word);
				
				if (isIgnoredWord(word) || word.matches(NON_CHAR)){
					continue;
				}else{
					outputLines.add(tempLine);
				}

			}
		}
		
		this.lines = outputLines;
	}
	
	private boolean isIgnoredWord(String word){
		return false;
	}
	
}
