package at.ac.htlhl.sebiorennotfallhilfesystem.views.list;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.Data;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.Location;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.Wristband;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;

import java.util.List;

@Route(value = "list", layout = MainView.class)
@PageTitle("List")
public class ListView extends VerticalLayout {
    public Grid<Wristband> grid = new Grid<>(Wristband.class);

    private UpdateThread update;

    public ListView()
    {
        setId("list-view");

        setSizeFull();
        setPadding(false);
        setSpacing(false);

        grid.setItems(Data.wristbands.getAll());
        grid.setColumns(
                "dev_id",
                "payload_fields.status",
                "payload_fields.latitude",
                "payload_fields.longitude",
                "payload_fields.altitude",
                "payload_fields.voltage",
                "payload_fields.hdop");

        add(grid);
    }

    @Override
    protected void onAttach(AttachEvent e)
    {
        update = new UpdateThread(e.getUI(), this);
        update.start();
    }

    @Override
    protected void onDetach(DetachEvent e)
    {
        update.interrupt();
        update = null;
    }

    private static class UpdateThread extends Thread {
        private final UI ui;
        private final ListView view;

        public UpdateThread(final UI ui, final ListView view)
        {
            this.ui = ui;
            this.view = view;
        }

        @Override
        public void run()
        {
            try {
                while (true) {
                    Thread.sleep(1000);
                    ui.access(() -> {
                        view.grid.setItems(Data.wristbands.getAll());
                    });
                }
            }
            catch (InterruptedException e) {
                // We don't case if sleep is interrupted.
            }
        }
    }
}
