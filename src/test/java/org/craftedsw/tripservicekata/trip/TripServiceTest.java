package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {

    /**
     * TODO LIST:
     *  No hay un usuario loggeado
     *  El usuario logeado y el objetivono son amigos
     *  El usuario logeado y el objetivo son amigo y el objetivo tiene viajes
     */

    @Test(expected = UserNotLoggedInException.class)
    public void throw_an_exception_when_there_is_no_logged_user() throws Exception{
        TripService tripService = new TripService();
        tripService.getTripsByUser(new User());
    }
	
}
