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
			List <String> tempLine = line;
			
			for (String word : line) {
				
				if (isIgnoredWord(word) || word.matches(NON_CHAR)){
					continue;
				}else{
					outputLines.add(tempLine);
				}

			}
			
			for (String word : tempLine){
				System.out.println(word);
			}
		}
		
		
		this.lines = outputLines;
	}
	
	private boolean isIgnoredWord(String word){
		return false;
	}
	
}
