package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserShould {


    User user = new User();
    User loggedUser = new User();

    @Test
    public void return_false_when_there_is_not_friends() throws Exception {
        boolean friendOf = user.isFriendOf(loggedUser);
        assertThat(friendOf, is(false));
    }

    @Test
    public void return_true_when_they_are_friends() throws Exception {
        user.addFriend(loggedUser);
        boolean friendOf = user.isFriendOf(loggedUser);
        assertThat(friendOf, is(true));
    }

    @Test
    public void return_true_when_they_are_not_friends_but_user_has_more_friends() throws Exception {
        user.addFriend(new User());
        user.addFriend(new User());
        user.addFriend(new User());
        user.addFriend(new User());
        boolean friendOf = user.isFriendOf(loggedUser);
        assertThat(friendOf, is(false));
    }

}
