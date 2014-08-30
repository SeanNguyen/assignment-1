package model;

import java.util.ArrayList;
import java.util.List;

public class CircularShifter extends Shifter{
	
	public CircularShifter(KwicModel model) {
		super(model);
	}

	@Override
	public void process() {
		List <Pair<Integer, Integer>> indexesOfCircularShift = new ArrayList<Pair<Integer,Integer>>();
		for (List <String> line : model.getLines()) {
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
	
	protected boolean isIgnoredWord (String word) {
		return false;
	}
}
