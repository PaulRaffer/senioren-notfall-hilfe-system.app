package at.ac.htlhl.sebiorennotfallhilfesystem.views.list;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.Data;
import at.ac.htlhl.sebiorennotfallhilfesystem.data.Wristband;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;

import java.util.List;

@Route(value = "list", layout = MainView.class)
@PageTitle("List")
public class ListView extends Div {

    public ListView()
    {
        setId("list-view");
        Data.wristbands.getAll().forEach(w -> add(new Text(w.getName())));
    }

}
