package model;

import java.util.List;

public class CircularShifterIgnoreWord extends CircularShifter{
	
	private List <String> listToIgnore;
	
	public CircularShifterIgnoreWord(List <String> listToIgnore){
		this.listToIgnore = listToIgnore;
	}
	
	private boolean isIgnoredWord(String inputWord){
		
		for (String ignoredWord : this.listToIgnore){
			if (inputWord.equalsIgnoreCase(ignoredWord)){
				return true;
			}
		}
		
		return false;
	}

	
}
