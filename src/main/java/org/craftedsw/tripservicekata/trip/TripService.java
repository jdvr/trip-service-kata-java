package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.craftedsw.tripservicekata.user.UserSessionService;

public class TripService {

    private UserSessionService userSessionService;
    private final TripRepository tripRepository;

    public TripService(UserSessionService userSessionService, TripRepository tripRepository) {
        this.userSessionService = userSessionService;
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
		return userSessionService.getLoggedUser();
	}

}
