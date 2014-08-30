package model;

public abstract class Sorter implements IProcessor{
	//Attributes
	protected KwicModel model;
		
	public Sorter (KwicModel model) {
		this.model = model;
	}
}
