package control;

import view.KwicView;
import model.KwicModel;

public class KwicInitiator {
	public static void main (String[] args) {
		KwicModel model = new KwicModel();
		KwicView view = new KwicView();
		KwicControl control = new KwicControl(view, model);
		
		view.setVisible(true);
	}
}
