package at.ac.htlhl.sebiorennotfallhilfesystem.views.main;

import java.util.Optional;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.*;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.settings.SettingsView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.map.MapView;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.list.ListView;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.about.AboutView;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * The main view is a top-level placeholder for other views.
 */
@CssImport(value = "./styles/views/main/main-view.css", themeFor = "vaadin-app-layout")
@CssImport("./styles/views/main/main-view.css")
@PWA(name = "Senioren-Notfall-Hilfe-System", shortName = "Senioren-Notfall-Hilfe-System", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
public class MainView extends AppLayout {

    private final Tabs menu;

    public MainView()
    {
        Data.wristbands = new WristbandService();
        for (int i = 0; i < 3; i++) {
            Data.wristbands.add(new MqttWristband(
                    "tcp://eu.thethings.network:1883",
                    "ApplicationServer",
                    "senioren-notfall-hilfe-system",
                    "ttn-account-v2.xqhU5_c5SPEKLNDpg38Ah5er2XqEZI0Gxt_iobseDmQ",
                    "#"));

            /*Data.wristbands.add(new MqttWristband(
                    "tcp://10.0.0.9:1883",
                    "ApplicationServer",
                    "my-new-application",
                    "NNSXS.75BJZKY5I6IVDGAOVXLXOXOW6DB2Y7A534P53KA.JTZIWSFG6LADZD2YBLF2YJUV5PL2572J5TFC7N3AJENYOKCU6D7A",
                    "#"));*/
        }

        HorizontalLayout header = createHeader();
        menu = createMenuTabs();
        addToNavbar(createTopBar(header, menu));
    }

    private VerticalLayout createTopBar(HorizontalLayout header, Tabs menu)
    {
        VerticalLayout layout = new VerticalLayout();
        layout.getThemeList().add("dark");
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(header, menu);
        return layout;
    }

    private HorizontalLayout createHeader()
    {
        HorizontalLayout header = new HorizontalLayout();
        header.setPadding(false);
        header.setSpacing(false);
        header.setWidthFull();
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setId("header");
        Image logo = new Image("images/logo.png", "Senioren-Notfall-Hilfe-System logo");
        logo.setId("logo");
        header.add(logo);
        Avatar avatar = new Avatar();
        avatar.setId("avatar");
        header.add(new H1("Senioren-Notfall-Hilfe-System"));
        header.add(avatar);
        return header;
    }

    private static Tabs createMenuTabs()
    {
        final Tabs tabs = new Tabs();
        tabs.getStyle().set("max-width", "100%");
        tabs.add(getAvailableTabs());
        return tabs;
    }

    private static Tab[] getAvailableTabs()
    {
        return new Tab[]{
                createTab("Map", MapView.class),
                createTab("List", ListView.class),
                createTab("Settings", SettingsView.class),
                createTab("About", AboutView.class)};
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget)
    {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation()
    {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
    }

    private Optional<Tab> getTabForComponent(Component component)
    {
        return menu.getChildren().filter(tab ->
                ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }
}
