package at.ac.htlhl.sebiorennotfallhilfesystem.views.about;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.main.MainView;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
public class AboutView extends VerticalLayout {

    public AboutView()
    {
        setId("about-view");
        add(new Text("Made by Paul Raffer."));
    }

}
