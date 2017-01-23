package vaadinSpringSecurity;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

/**
 * Created by Maggouh on 18/01/17.
 */
public class AccessDeniedView extends CustomComponent implements View {

    public static final String NAME = "error";

    Label text = new Label();

    Button back = new Button("Back", new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
            getSession().setAttribute("user",null);
            getUI().getNavigator().navigateTo(NAME);
        }
    });

    public AccessDeniedView() {
        setCompositionRoot(new CssLayout(text, back));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        text.setValue(String.format("You do not have permission to access to this view: %s", LoginMainView.NAME ));
    }
}


