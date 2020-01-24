package ru.vsu.touristagency.services;


import ru.vsu.touristagency.domain.Tourlist;
import ru.vsu.touristagency.persistance.TourlistRepository;

import java.util.Date;
import java.util.List;

public class TourlistService {

    private TourlistRepository tourlistRepository;

    public TourlistService() {
        tourlistRepository = new TourlistRepository();
    }

    public Tourlist getTourlist(Long id) {
        return tourlistRepository.find(id);
    }

    public List<Tourlist> getTourslist() {
        return tourlistRepository.findAll();
    }

    public boolean updateTourlist(Object... o) {
        Tourlist t = new Tourlist((long)o[0], (long)o[1], (long)o[2], (Integer) o[3], (Date) o[4]);
        return tourlistRepository.update(t);
    }

    public boolean deleteTourlist(Long id){
        return tourlistRepository.delete(new Tourlist(id));
    }

    public boolean createTourlist(Object... o){
        return tourlistRepository.create(new Tourlist((long)o[1], (long)o[2], (Integer) o[3], (Date) o[4]));
    }

}
