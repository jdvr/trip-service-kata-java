package org.craftedsw.tripservicekata.trip;

import java.util.Arrays;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceShould {


    @Mock
    TripRepository tripRepository;


    @Test(expected = UserNotLoggedInException.class)
    public void throw_an_exception_when_there_is_no_logged_user() throws Exception{
        TripService tripService = new TripServiceFake(null, tripRepository);
        tripService.getTripsByUser(new User());
    }

    @Test
    public void return_and_empty_triplist_when_logged_user_and_user_are_friend_but_user_not_has_trips() throws Exception{
        User loggedUser = new User();
        User user = mock(User.class);
        TripService tripService = new TripServiceFake(loggedUser, tripRepository);
        when(user.isFriendOf(loggedUser)).thenReturn(true);
        List<Trip> tripsByUser = tripService.getTripsByUser(user);
        assertNotNull(tripsByUser);
        assertThat(tripsByUser.size(), is(0));
    }


    @Test
    public void return_and_empty_triplist_when_logged_user_and_user_are_not_friend() throws Exception{
        User loggedUser = new User();
        User user = mock(User.class);
        when(user.isFriendOf(loggedUser)).thenReturn(true);
        TripService tripService = new TripServiceFake(loggedUser, tripRepository);
        List<Trip> tripsByUser = tripService.getTripsByUser(user);
        assertNotNull(tripsByUser);
        assertThat(tripsByUser.size(), is(0));
    }

    @Test
    public void return_user_triplist_when_logged_user_and_user_are_friend() throws Exception{
        User loggedUser = new User();
        User user = mock(User.class);
        TripService tripService = new TripServiceFake(loggedUser, tripRepository);
        when(user.isFriendOf(loggedUser)).thenReturn(true);
        List<Trip> fakeTrips = fakeTripsList();
        when(tripRepository.findTripsBy(user)).thenReturn(fakeTrips);
        List<Trip> tripsByUser = tripService.getTripsByUser(user);
        assertNotNull(tripsByUser);
        assertThat(tripsByUser, is(fakeTrips));
    }

    private List<Trip> fakeTripsList() {
        return Arrays.asList(
                new Trip(),
                new Trip(),
                new Trip()
        );
    }


    private class TripServiceFake extends TripService {
        private User loggedUser;

        public TripServiceFake(User loggedUser, TripRepository tripRepository){

            super(tripRepository);
            this.loggedUser = loggedUser;
        }

        @Override
        protected User getLoggedUser() {
            return loggedUser;
        }


    }
}
