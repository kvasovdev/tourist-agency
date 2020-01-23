package ru.vsu.touristagency.services;

import ru.vsu.touristagency.domain.Client;
import ru.vsu.touristagency.domain.Tour;
import ru.vsu.touristagency.persistance.TourRepository;

import java.util.List;

public class TourService {

    private TourRepository tourRepository;

    public TourService() {
        tourRepository = new TourRepository();
    }

    public Tour getTour(Long id) {
        return tourRepository.find(id);
    }

    public List<Tour> getTours() {
        return tourRepository.findAll();
    }

    public boolean updateTour(Object... o) {
        Tour t = new Tour((long)o[0], (String)o[1], (String)o[2], (Boolean) o[3], (Double) o[4]);
        return tourRepository.update(t);
    }

    public boolean deleteTour(Long id){
        return tourRepository.delete(new Tour(id));
    }

    public boolean createTour(Object... o){
        return tourRepository.create(new Tour((String)o[1], (String)o[2], (Boolean) o[3], (Double) o[4]));
    }




}
