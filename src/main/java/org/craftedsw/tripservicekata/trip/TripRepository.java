package org.craftedsw.tripservicekata.trip;

import java.util.List;

import org.craftedsw.tripservicekata.user.User;

public abstract class TripRepository {
    public List<Trip> findTripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }
}
