package org.craftedsw.tripservicekata.trip;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    /**
     * TODO LIST:
     *  El usuario logeado y el objetivo no son amigos
     *  El usuario logeado y el objetivo son amigo y el objetivo tiene viajes
     */



    @Test(expected = UserNotLoggedInException.class)
    public void throw_an_exception_when_there_is_no_logged_user() throws Exception{
        TripService tripService = new TripServiceFake(null);
        tripService.getTripsByUser(new User());
    }

    @Test
    public void should_return_and_empty_triplist_when_logged_user_and_user_are_friend_but_user_not_has_trips() throws Exception{
        User loggedUser = new User();
        User user = mock(User.class);
        TripService tripService = new TripServiceFake(loggedUser);
        when(user.getFriends()).thenReturn(Collections.singletonList(loggedUser));
        List<Trip> tripsByUser = tripService.getTripsByUser(user);
        assertNotNull(tripsByUser);
        assertThat(tripsByUser.size(), is(0));
    }




    private class TripServiceFake extends TripService {
        private User loggedUser;

        public TripServiceFake(User loggedUser){

            this.loggedUser = loggedUser;
        }

        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }

        @Override
        protected List<Trip> findUserTrips(User user) {
            return user.trips();
        }
    }
}
