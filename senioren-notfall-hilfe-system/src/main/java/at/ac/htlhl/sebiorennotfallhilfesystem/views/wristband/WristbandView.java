package at.ac.htlhl.sebiorennotfallhilfesystem.views.wristband;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.Data;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "wristband", layout = MainView.class)
@PageTitle("Wristband")
public class WristbandView extends Div {

	public WristbandView()
	{
		setId("wristband-view");
		//Data.wristbands.getAll().forEach(w -> add(new Text(w.getName())));
	}

}
