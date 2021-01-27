package at.ac.htlhl.sebiorennotfallhilfesystem.views.settings;

import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "settings", layout = MainView.class)
@PageTitle("Settings")
public class SettingsView extends Div {

	public SettingsView()
	{
		setId("settings-view");
		add(new Text("Content placeholder"));
	}

}
