package at.ac.htlhl.sebiorennotfallhilfesystem.views.list;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.*;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.update.UpdateView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;

@Route(value = "list", layout = MainView.class)
@PageTitle("List")
public class ListView extends UpdateView<ListView> {

    private Grid<TTSUplinkMessage<Wristband>> grid = new Grid<>((Class<TTSUplinkMessage<Wristband>>)(Class) TTSUplinkMessage.class);

    public ListView()
    {
        setId("list-view");

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        grid.setItems(TTSMqttService.getInstance().getAll());
        /*grid.setColumns(
                "dev_id",
                "metadata.time");*/
        /*grid.addColumn(wb -> wb.getPayload_fields().getStatus())   .setHeader("Status");
        grid.addColumn(wb -> wb.getPayload_fields().getLatitude()) .setHeader("Latitude");
        grid.addColumn(wb -> wb.getPayload_fields().getLongitude()).setHeader("Longitude");
        grid.addColumn(wb -> wb.getPayload_fields().getAltitude()) .setHeader("Altitude");
        grid.addColumn(wb -> wb.getPayload_fields().getVoltage())  .setHeader("Voltage");
        grid.addColumn(wb -> wb.getPayload_fields().getHdop())     .setHeader("Hdop");*/

        add(grid);

        setUpdateFunction(view ->
                view.grid.setItems(TTSMqttService.getInstance().getAll()));
    }
}
