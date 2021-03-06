package at.ac.htlhl.sebiorennotfallhilfesystem.views.main;

import java.util.Optional;

import at.ac.htlhl.sebiorennotfallhilfesystem.data.*;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.settings.SettingsView;
import at.ac.htlhl.sebiorennotfallhilfesystem.views.wristbands.WristbandsView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
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
@Push
@CssImport(value = "./styles/views/main/main-view.css", themeFor = "vaadin-app-layout")
@CssImport("./styles/views/main/main-view.css")
@PWA(name = "Senioren-Notfall-Hilfe-System", shortName = "Senioren-Notfall-Hilfe-System", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
public class MainView extends AppLayout {

    private final Tabs menu;

    public MainView()
    {
        LoginOverlay login = createLogin();
        HorizontalLayout header = createHeader();
        menu = createMenuTabs();
        addToNavbar(createTopBar(login, header, menu));
    }

    private VerticalLayout createTopBar(
            LoginOverlay login, HorizontalLayout header, Tabs menu)
    {


        VerticalLayout layout = new VerticalLayout();
        layout.getThemeList().add("dark");
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(login, header, menu);
        return layout;
    }

    private LoginOverlay createLogin()
    {
        // Login-component-object
        LoginOverlay login = new LoginOverlay();
        login.setTitle("Senioren-Notfall-Hilfe-System");
        login.setDescription("");
        login.addLoginListener(e -> {
            if (e.getUsername().equals("test")
                    && e.getPassword().equals("1234")) {
                // Close login-overlay
                login.close();
            }
            else {
                // Don't disable when user entered
                // wrong username or password.
                login.setEnabled(true);
            }
        });

        // Open login-overlay when page is loaded
        login.setOpened(true);
        return login;
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
                //createTab("Map", MapView.class),
                //createTab("List", ListView.class),
                createTab("Wristbands", WristbandsView.class),
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
