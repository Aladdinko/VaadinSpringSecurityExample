package vaadinSpringSecurity;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import controller.TestController;

/**
 * Created by maggouh on 10/01/17.
 */

public class LoginMainView extends CustomComponent implements View {

    TestController testController = new TestController();

    public static final String NAME = "admin";

    Label text = new Label();
    VerticalLayout layout;
    Button logout = new Button("Logout", new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
            getSession().setAttribute("user",null);
            getUI().getNavigator().navigateTo(NAME);
        }
    });


    public LoginMainView() {
        layout = new VerticalLayout();
        layout.addComponent(new CssLayout(text, logout));

        setCompositionRoot(layout);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String username = String.valueOf(getSession().getAttribute("user"));
        text.setValue("Hello " + username);
        String value = testController.test();
        layout.addComponent(new Label(value));

    }


}
