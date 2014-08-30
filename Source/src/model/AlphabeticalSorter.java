package model;

public class AlphabeticalSorter extends Sorter {

	public AlphabeticalSorter(KwicModel model) {
		super(model);
	}

	@Override
	public void process() {
		for (Pair<Integer, Integer> index : model.getIndexes()) {
			model.insertToAlphabeticalIndexList(index);
		}
	}
}
