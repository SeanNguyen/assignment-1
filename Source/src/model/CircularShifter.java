package model;

import java.util.ArrayList;
import java.util.List;

public class CircularShifter extends Shifter implements IProcessor{

	private List<List<String>>lines;
	
	@Override
	public void inputLines(List <List<String>> lines) {
		this.lines = lines;
		
	}

	@Override
	public List <List<String>> getOutputLines() {
		circularShift();
		return this.lines;
	}
	
	//Circular Shift
	private void circularShift () {
		List <List <String>> outputLines = new ArrayList <List<String>>();
		List <Pair<Integer, Integer>> indexesOfCircularShift = new ArrayList<Pair<Integer,Integer>>();
		for (List <String> line : this.lines) {
			for (String word : line) {
				if (isIgnoredWord(word) || word.matches("\\W"))
					continue;
				int lineIndex = this.lines.indexOf(line);
				int wordIndex = line.indexOf(word);
			}
		}
		
		this.lines = outputLines;
	}
	
	private boolean isIgnoredWord(String word){
		return true;
	}
	
}
