package model;

public abstract class Shifter implements IProcessor{
	//Attributes
	protected KwicModel model;
	
	public Shifter (KwicModel model) {
		this.model = model;
	}
}
