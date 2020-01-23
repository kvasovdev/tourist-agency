package ru.vsu.touristagency.persistance;

import org.junit.jupiter.api.Test;

import ru.vsu.touristagency.domain.Tourlist;

import java.util.Date;
import java.util.List;

class TourlistRepositoryTest {

    @Test
    void create() {

        Tourlist c = new Tourlist((long)1, (long)5, (long)3, 1, new Date());
        IRepository<Tourlist> rep = new TourlistRepository();
        rep.create(c);
        //rep.create(c2);
    }

    @Test
    void findAll() {
        List<Tourlist> c;
        IRepository<Tourlist> rep = new TourlistRepository();
        c = rep.findAll();
        for (Tourlist cl : c){
            System.out.println(cl);
        }
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}