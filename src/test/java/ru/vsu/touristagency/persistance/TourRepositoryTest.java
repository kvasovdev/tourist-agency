package ru.vsu.touristagency.persistance;

import org.junit.jupiter.api.Test;

import ru.vsu.touristagency.domain.Tour;

import java.util.List;

class TourRepositoryTest {

    @Test
    void create() {
        Tour t = new Tour((long)1, "Rus", "Hotel", true, 12.2d);
        Tour t2 = new Tour((long)2, "Rus", "Hotel", false, 11.1d);

        IRepository<Tour> rep = new TourRepository();
        rep.create(t);
        rep.create(t2);
    }

    @Test
    void findAll() {
        List<Tour> t;
        IRepository<Tour> rep = new TourRepository();
        t = rep.findAll();
        for (Tour tl : t){
            System.out.println(tl);
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}