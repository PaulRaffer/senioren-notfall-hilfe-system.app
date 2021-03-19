package at.ac.htlhl.sebiorennotfallhilfesystem.views.wristband;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.TTNUplinkMessage;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.MQTTService;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.Wristband;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.update.UpdateView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "wristband", layout = MainView.class)
@PageTitle("Wristband")
public class WristbandView extends UpdateView<WristbandView> implements HasUrlParameter<String> {

	public WristbandView()
	{
		setId("wristband-view");

		setSizeFull();
		setPadding(false);
		setSpacing(false);
	}

	@Override
	public void setParameter(BeforeEvent event, String wristbandName)
	{
		TTNUplinkMessage<Wristband> wb = MQTTService.getInstance().getByDev_id(wristbandName);
		add(new Text(wristbandName));
		add(new Text(wb.getDev_id()));
		add(new Text(String.valueOf(wb.getPayload_fields().getLatitude())));
		add(new Text(String.valueOf(wb.getPayload_fields().getLongitude())));
		add(new Text(String.valueOf(wb.getPayload_fields().getAltitude())));
		add(new Text(String.valueOf(wb.getPayload_fields().getStatus())));
	}
}
