package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSessionService;

public class TripService {

    private UserSessionService userSessionService;
    private final TripRepository tripRepository;

    public TripService(UserSessionService userSessionService, TripRepository tripRepository) {
        this.userSessionService = userSessionService;
        this.tripRepository = tripRepository;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (notLoggedUser())  throw new UserNotLoggedInException();

        return user.isFriendOf(getLoggedUser()) ?
                findUserTrips(user) : Collections.emptyList();

    }

    private boolean notLoggedUser() {
        return getLoggedUser() == null;
    }

    protected List<Trip> findUserTrips(User user) {
        return tripRepository.findTripsBy(user);
    }

	protected User getLoggedUser() {
		return userSessionService.getLoggedUser();
	}

}
