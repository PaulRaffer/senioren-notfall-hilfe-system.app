package at.ac.htlhl.sebiorennotfallhilfesystem.views.wristbands;

import at.ac.htlhl.sebiorennotfallhilfesystem.views.list.ListView;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;

import at.ac.htlhl.sebiorennotfallhilfesystem.views.map.MapView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route(value = "wristbands", layout = MainView.class)
@PageTitle("Wristbands")
public class WristbandsView extends SplitLayout {

	public WristbandsView()
	{
		setId("wristbands-view");

		setSizeFull();
		setOrientation(Orientation.VERTICAL);

		addToPrimary(new ListView());
		addToSecondary(new MapView());
	}

}
