package vaadinSpringSecurity;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * Created by maggouh on 10/01/17.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        new Navigator(this,this);

        getNavigator().addView(LoginView.NAME, LoginView.class);

        getNavigator().addView(LoginMainView.NAME, LoginMainView.class);

        getNavigator().addView(AccessDeniedView.NAME, AccessDeniedView.class);


        /*
         * On utilise le change handler pour assurer que la page est toujours rediriger
         * vers le LoginView en cas l'utilisateur n'est pas connecté
         */
        getNavigator().addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                // vérifier l'utilisateur est connecté
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof LoginView;

                if(!isLoggedIn && !isLoginView) {
                    // Rediriger toujours vers LoginView si l'utilisateur n'est toujours pas connecté
                    getNavigator().navigateTo(LoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // Si on essai d'accéder le LoginMainView directement ,
                    // on annule
                    return false;
                }
                    return true;

            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
    }
}