package VaadinSpringS;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Created by maggouh on 10/01/17.
 */

public class LoginView extends CustomComponent implements View, Button.ClickListener {

    public static final String NAME = "";
    private final TextField user;
    private final PasswordField password;


    private final Button loginButton;


    public LoginView() {

        setSizeFull();
        // Create the user input field
        user = new TextField("User: ");
        user.setRequired(true);
        user.setInputPrompt("enter your email e.g xxx@xx.xx");
        user.addValidator(new EmailValidator("Username must be an email address"));
        user.setInvalidAllowed(false);

        // Create the password input field

        password = new PasswordField("Password: ");
        password.addValidator(new PasswordValidator());
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");

        // Create login button
        loginButton = new Button("Login", this);

        //Add both to a panel
        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        fields.setCaption("Please login to access the application.");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        //The view root layout
        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {

        if (!user.isValid() || !password.isValid()) {
            return;
        }
        try {
            String username = user.getValue();
            String password = this.password.getValue();

            WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
            AuthenticationManager authenticationManager = (AuthenticationManager) ctx.getBean("org.springframework.security.authenticationManager");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            if (authentication.isAuthenticated()) {
                getSession().setAttribute("user", authentication.getName());
                getUI().getNavigator().navigateTo(LoginMainView.NAME);
            } else {
                Notification.show("The user or password is not valid");
                this.password.setValue(null);
                this.password.focus();
            }
        } catch (BadCredentialsException e) {
            Notification.show("The user or password is not valid");
            this.password.setValue(null);
            this.password.focus();
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        user.focus();
    }

    // Validator for validating the passwords
    private static final class PasswordValidator extends
            AbstractValidator<String> {

        public PasswordValidator() {
            super("The password provided is not valid");
        }

        @Override
        protected boolean isValidValue(String value) {
            //
            // Password must be at least 8 characters long and contain at least
            // one number
            //
            if (value != null
                    && (value.length() < 8 || !value.matches(".*\\d.*"))) {
                return false;
            }
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }

}
