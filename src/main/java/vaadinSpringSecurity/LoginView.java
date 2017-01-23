package vaadinSpringSecurity;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.Collection;

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

        user = new TextField("User: ");
        user.setRequired(true);
        user.setInputPrompt("enter your email e.g xxx@xx.xx");
        user.addValidator(new EmailValidator("Username must be an email address"));
        user.setInvalidAllowed(false);


        password = new PasswordField("Password: ");
        password.addValidator(new PasswordValidator());
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");


        loginButton = new Button("Login", this);


        VerticalLayout fields = new VerticalLayout(user, password, loginButton);
        fields.setCaption("Please login to access the application.");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();


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
            WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
            AuthenticationProvider authenticationProvider = (AuthenticationProvider) ctx.getBean("customAuthenticationProvider");
            String username = user.getValue();
            String password = this.password.getValue();
            Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean authorized = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

            if (authentication.isAuthenticated()) {
                for (GrantedAuthority grantedAuthority : authorities) {
                    if ("ROLE_ADMIN".equals(grantedAuthority.getAuthority())) {
                        getSession().setAttribute("user", username);
                        getUI().getNavigator().navigateTo(LoginMainView.NAME);
                    } else {
                        getSession().setAttribute("user", username);
                        getUI().getNavigator().navigateTo(AccessDeniedView.NAME);
                        Notification.show("Permission failed : Access denied to the Page please check your permissions.", Notification.Type.ERROR_MESSAGE);
                        this.password.setValue(null);
                        this.password.focus();
                    }
                }
            }
        } catch (BadCredentialsException e) {
            Notification.show("Authentication failed: " + e.getMessage(), Notification.Type.ERROR_MESSAGE);
            this.password.setValue(null);
            this.password.focus();
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        user.focus();
    }

    // Validation du mot de pass
    private static final class PasswordValidator extends
            AbstractValidator<String> {

        public PasswordValidator() {
            super("The password provided is not valid");
        }

        @Override
        protected boolean isValidValue(String value) {
            //
            // le mot de passe doit être supérieur que 8 caractéres
            // et contient au moins 1 chiffre
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
