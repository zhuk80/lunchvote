package lunchvote.service;

import lunchvote.model.User;
import lunchvote.model.Vote;
import lunchvote.util.exception.NotFoundException;

import javax.naming.TimeLimitExceededException;
import java.util.List;

/**
 * Created by Evgeniy on 07.05.2017.
 */
public interface UserService {

    User get(int id);

    User getByEmail(String email);

    void delete(int id) throws NotFoundException;

    User save (User user);

    List<User> getAll();

    Vote sendVote(int restrauntId) throws TimeLimitExceededException;
}
