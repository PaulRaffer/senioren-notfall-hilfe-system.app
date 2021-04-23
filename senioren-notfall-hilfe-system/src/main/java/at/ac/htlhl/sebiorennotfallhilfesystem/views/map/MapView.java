package at.ac.htlhl.sebiorennotfallhilfesystem.views.map;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.MQTTService;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.update.UpdateView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "map", layout = MainView.class)
@PageTitle("Map")
@RouteAlias(value = "", layout = MainView.class)
public class MapView extends UpdateView<MapView> {

    private final LeafletMap map;

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
        map.addMarkerClickListener(e ->
            getUI().ifPresent(ui ->
                ui.navigate(
                    "wristband/"+e.getMarker().getDev_id())));

        // Add all known markers to the map
        map.addMarkersAndZoom(
                MQTTService.getInstance().getAll());

        setUpdateFunction(view -> {
            view.map.removeAllMarkers();
            MQTTService.getInstance().getAll()
                    .forEach(view.map::addMarker);
        });
    }

}
