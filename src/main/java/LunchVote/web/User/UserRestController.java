package lunchvote.web.User;

import lunchvote.model.Restraunt;
import lunchvote.model.Vote;
import lunchvote.service.RestrauntService;
import lunchvote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.naming.TimeLimitExceededException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static lunchvote.web.User.UserRestController.USER_REST_URL;

/**
 * Created by evgeniy on 09.06.2017.
 */
@RestController
@RequestMapping(value = USER_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    static final String USER_REST_URL = "/rest/users";

    private UserService userService;

    private RestrauntService restrauntService;

    @Autowired
    public UserRestController(UserService userService, RestrauntService restrauntService) {
        this.userService = userService;
        this.restrauntService = restrauntService;
    }

    @GetMapping(value = "/vote/{id}")
    public Vote sendVote(@PathVariable("id") int id) throws TimeLimitExceededException {
        return userService.sendVote(id);
    }

    @GetMapping(value = "/menu")
    public List<Restraunt> getAllWithTodayMenu() {
        return restrauntService.getAllWithMenuByDate(LocalDate.now());
    }

}