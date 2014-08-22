package model;

import java.util.ArrayList;
import java.util.List;

public class KwicModel {
	private List < List < String >> lines;
	private List < Pair <Integer, Integer>> indexes;
	private List < Pair <Integer, Integer>> alphabeticalIndexes;
	private List < String > ignoredWords;
	
	public KwicModel() {
		lines = new ArrayList < List < String >>();
		indexes = new ArrayList < Pair < Integer, Integer >>();
		alphabeticalIndexes = new ArrayList < Pair < Integer, Integer >>();
		ignoredWords = new ArrayList <String>();
	}
	
	//public methods
	public List < List < String >> GetLines() {
		return lines;
	}
	
	public List < Pair <Integer, Integer>> GetIndexes() {
		return indexes;
	}
	
	public List < Pair <Integer, Integer>> GetAlphabetialIndexes() {
		return alphabeticalIndexes;
	}
	
	public List <String> GetIgnoredWords () {
		return ignoredWords;
	}
}
