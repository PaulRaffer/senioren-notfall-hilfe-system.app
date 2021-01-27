package at.ac.htlhl.sebiorennotfallhilfesystem.views.list;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;

@Route(value = "list", layout = MainView.class)
@PageTitle("List")
public class ListView extends Div {

    public ListView()
    {
        setId("list-view");
        add(new Text("Content placeholder"));
    }

}
