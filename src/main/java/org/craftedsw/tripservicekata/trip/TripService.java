package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
        if (loggedUser == null)
			throw new UserNotLoggedInException();
        if (user.isFriendOf(loggedUser))
			return findUserTrips(user);

		return Collections.emptyList();
	}

    protected List<Trip> findUserTrips(User user) {
        return tripRepository.findTripsBy(user);
    }

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
