package at.ac.htlhl.sebiorennotfallhilfesystem.views.list;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.TTNUplinkMessage;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.MQTTService;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.TTSUplinkMessage;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.Wristband;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.update.UpdateView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;

@Route(value = "list", layout = MainView.class)
@PageTitle("List")
public class ListView extends UpdateView<ListView> {

    private final Grid<TTSUplinkMessage<Wristband>> grid =
            new Grid<>((Class<TTSUplinkMessage<Wristband>>)(Class)
                    TTSUplinkMessage.class);

    public ListView()
    {
        setId("list-view");

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        grid.setItems(MQTTService.getInstance().getAll());
        /*grid.setColumns(
                MQTTService.networkServer == MQTTService.NetworkServer.TTN ? "dev_id" : "application_ids.device_id",
                "metadata.time");*/
        grid.addColumn(TTSUplinkMessage::getDevice_id).setHeader("DevId");
        grid.addColumn(wb -> wb.getPayload().getStatus())
                .setHeader("Status");
        grid.addColumn(wb -> wb.getPayload().isEmergency())
                .setHeader("Emergency");
        grid.addComponentColumn(wb -> {
            Button endEmergencyBtn = new Button("End");
            endEmergencyBtn.setEnabled(
                    wb.getPayload().isEmergency());
            endEmergencyBtn.addClickListener(event ->
                    wb.getPayload().endEmergency());
            return endEmergencyBtn;
        });
        grid.addColumn(wb -> wb.getPayload().getLatitude())
                .setHeader("Latitude");
        grid.addColumn(wb -> wb.getPayload().getLongitude())
                .setHeader("Longitude");
        grid.addColumn(wb -> wb.getPayload().getAltitude())
                .setHeader("Altitude");
        grid.addColumn(wb -> wb.getPayload().getVoltage())
                .setHeader("Voltage");
        grid.addColumn(wb -> wb.getPayload().getHdop())
                .setHeader("Hdop");

        setUpdateFunction(view ->
                grid.getDataProvider().refreshAll());

        add(grid);
    }

}
