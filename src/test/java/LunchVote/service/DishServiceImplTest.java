package LunchVote.service;

import LunchVote.model.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static LunchVote.DishTestData.*;
import static org.junit.Assert.*;

/**
 * Created by evgeniy on 02.06.2017.
 */
public class DishServiceImplTest extends ServiceImplAbstract  {

    @Autowired
    private DishService service;

    @Test
    public void get() throws Exception {
        assertEquals(DISH1.toString(),service.get(100002).toString());
    }

    @Test
    public void save() throws Exception {
        Dish created = getCreated();
        Dish dish = service.save(new Dish(null, "Created dish", 1.00, LocalDate.of(2015, Month.JUNE, 1), 100010));
        created.setId(100014);
        assertEquals(created.toString(), dish.toString());
    }

    @Test
    public void delete() throws Exception {
        service.delete(DISH1.getId());
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4, DISH3, DISH2).toString(), service.getAll().toString());
    }

    @Test
    public void getByDate() throws Exception {
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4).toString(), service.getByDate(LocalDate.of(2015, Month.MAY, 31)).toString());
    }

    @Test
    public void getByDateRestrauntID() throws Exception {
        assertEquals(Arrays.asList(DISH4, DISH5).toString(), service.getByDateRestrauntID(LocalDate.of(2015, Month.MAY, 31), 100009).toString());
    }

    @Test
    public void getAll() throws Exception {
        assertEquals(Arrays.asList(DISH6, DISH5, DISH4, DISH3, DISH2, DISH1).toString(), service.getAll().toString());
    }

}