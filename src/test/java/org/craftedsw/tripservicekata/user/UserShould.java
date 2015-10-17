package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserShould {

    /**
     * TODO LIST:
     * User has not friends
     * User and logged User are friends
     * User has friends but User and logged User are not friends
     */

    User user = new User();
    User loggedUser = new User();

    @Test
    public void return_false_when_there_is_not_friends() throws Exception {
        boolean friendOf = user.isFriendOf(loggedUser);
        assertThat(friendOf, is(false));
    }

}
