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
	
	public void SetLines (List < List < String >> lines) {
		this.lines = lines;
	}
	
	public List < Pair <Integer, Integer>> GetIndexes() {
		return indexes;
	}
	
	public void SetIndexes (List < Pair <Integer, Integer>> indexes) {
		this.indexes = indexes;
	}
	
	public List < Pair <Integer, Integer>> GetAlphabetialIndexes() {
		return alphabeticalIndexes;
	}
	
	public void SetAlphabeticalIndexes (List < Pair <Integer, Integer>> alphabeticalIndexes) {
		this.alphabeticalIndexes = alphabeticalIndexes;
	}
	
	public List <String> GetIgnoredWords () {
		return ignoredWords;
	}
	
	public void SetIgnoredWords (List <String> ignoredWords) {
		this.ignoredWords = ignoredWords;
	}
	
	public void SwapIndex (int index1, int index2) {
		Pair<Integer, Integer> tempIndex = this.indexes.get(index1);
		this.indexes.set(index1, this.indexes.get(index2));
		this.indexes.set(index2, tempIndex);
	}
	
	public String getWordByIndex (int lineIndex, int wordIndex) {
		List <String> line = this.lines.get(lineIndex);
		return line.get(wordIndex);
	}
}
