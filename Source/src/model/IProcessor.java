package model;

import java.util.List;

public interface IProcessor {

	public void inputLines(List <List <String>> lines);
	public List<List<String>> getOutputLines();
	
}
