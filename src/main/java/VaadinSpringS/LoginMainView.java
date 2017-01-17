package VaadinSpringS;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import org.springframework.security.access.annotation.Secured;

/**
 * Created by maggouh on 10/01/17.
 */
@Secured("ROLE_ADMIN")
public class LoginMainView extends CustomComponent implements View {

    public static final String NAME = "admin";

    Label text = new Label();
    Button logout = new Button("Logout", new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
            getSession().setAttribute("user",null);
            getUI().getNavigator().navigateTo(NAME);
        }
    });


    public LoginMainView() {
        setCompositionRoot(new CssLayout(text, logout));
    }

    @Secured("ROLE_ADMIN")
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String username = String.valueOf(getSession().getAttribute("user"));
        text.setValue("Hello " + username);
    }
}
