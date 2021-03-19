package at.ac.htlhl.sebiorennotfallhilfesystem.views.list;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.TTNUplinkMessage;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.TTNWristbandService;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.Wristband;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.update.UpdateView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;

@Route(value = "list", layout = MainView.class)
@PageTitle("List")
public class ListView extends UpdateView<ListView> {

    private Grid<TTNUplinkMessage<Wristband>> grid = new Grid<>((Class<TTNUplinkMessage<Wristband>>)(Class) TTNUplinkMessage.class);

    public ListView()
    {
        setId("list-view");

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        grid.setItems(TTNWristbandService.getInstance().getAll());
        grid.setColumns(
                "dev_id",
                "metadata.time");
        grid.addColumn(wb -> wb.getPayload_fields().getStatus())   .setHeader("Status");
        grid.addColumn(wb -> wb.getPayload_fields().isEmergency()) .setHeader("Emergency");
        grid.addComponentColumn(wb -> {
            Button endEmergencyBtn = new Button("End");
            endEmergencyBtn.setEnabled(
                    wb.getPayload_fields().isEmergency());
            endEmergencyBtn.addClickListener(event ->
                    wb.getPayload_fields().setEmergency(false));
            return endEmergencyBtn;
        });
        grid.addColumn(wb -> wb.getPayload_fields().getLatitude()) .setHeader("Latitude");
        grid.addColumn(wb -> wb.getPayload_fields().getLongitude()).setHeader("Longitude");
        grid.addColumn(wb -> wb.getPayload_fields().getAltitude()) .setHeader("Altitude");
        grid.addColumn(wb -> wb.getPayload_fields().getVoltage())  .setHeader("Voltage");
        grid.addColumn(wb -> wb.getPayload_fields().getHdop())     .setHeader("Hdop");

        setUpdateFunction(view -> grid.getDataProvider().refreshAll());

        add(grid);
    }
}
