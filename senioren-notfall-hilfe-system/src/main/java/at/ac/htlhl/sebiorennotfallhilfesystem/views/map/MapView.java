package at.ac.htlhl.sebiorennotfallhilfesystem.views.map;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.Data;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.Location;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.MqttWristband;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.Wristband;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.update.UpdateView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "map", layout = MainView.class)
@PageTitle("Map")
@RouteAlias(value = "", layout = MainView.class)
public class MapView extends UpdateView<MapView> {
    private final LeafletMap map;

    @Autowired
    public MapView()
    {
        setId("map-view");

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        map = new LeafletMap();
        map.setWidthFull();
        add(map);

        // Register for marker clicks
        map.addMarkerClickListener(e -> getUI().ifPresent(ui ->
                ui.navigate("wristband/"+e.getMarker().getDev_id())));

        // Add all known markers to the map
        map.addMarkersAndZoom(Data.wristbands.getAll());

        setUpdateFunction(view -> {
            view.map.removeAllMarkers();
            Data.wristbands.getAll().forEach(wb ->
                    view.map.addMarker(wb));
        });
    }

}
