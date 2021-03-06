package model;

public class CircularShifterIgnoreWord extends CircularShifter{
	 
	public CircularShifterIgnoreWord(KwicModel model) {
		super(model);
	}

	@Override
	protected boolean isIgnoredWord(String word){
		for (String ignoredWord : model.getIgnoredWords()) {
			if (word.equalsIgnoreCase(ignoredWord))
				return true;
		}
		return false;
	}

	
}
