package ru.vsu.touristagency.domain;

import java.util.Date;
import java.util.Objects;

public class Tourlist {
    private Long id;
    private Long clientId;
    private Long tourId;
    private int status;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        tourId = tourId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourlist tourlist = (Tourlist) o;
        return status == tourlist.status &&
                Objects.equals(id, tourlist.id) &&
                clientId.equals(tourlist.clientId) &&
                tourId.equals(tourlist.tourId) &&
                date.equals(tourlist.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, tourId, status, date);
    }

    @Override
    public String toString() {
        return "Tourlist{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", tourId=" + tourId +
                ", status=" + status +
                ", date=" + date +
                '}';
    }

    public Tourlist() {
    }

    public Tourlist(Long id) {
        this.id = id;
    }

    public Tourlist(Long clientId, Long tourId, int status, Date date) {
        this.clientId = clientId;
        this.tourId = tourId;
        this.status = status;
        this.date = date;
    }

    public Tourlist(Long id, Long clientId, Long tourId, int status, Date date) {
        this.id = id;
        this.clientId = clientId;
        this.tourId = tourId;
        this.status = status;
        this.date = date;
    }
}
