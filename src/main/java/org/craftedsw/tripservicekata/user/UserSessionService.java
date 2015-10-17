package org.craftedsw.tripservicekata.user;

public abstract class UserSessionService {
    public User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
}
