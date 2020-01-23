package ru.vsu.touristagency.domain;

import java.util.Objects;

public class Client {
    private Long id;
    private String fullName;
    private Integer numberPhone;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(Integer numberPhone) {
        this.numberPhone = numberPhone;
    }
//Для сравнения
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                fullName.equals(client.fullName) &&
                numberPhone.equals(client.numberPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, numberPhone);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", numberPhone=" + numberPhone +
                '}';
    }
//Конструктор по умолчанию
    public Client() {
    }

    public Client(Long id) {
        this.id = id;
    }

    public Client(Long id, String fullName, Integer numberPhone) {
        this.id = id;
        this.fullName = fullName;
        this.numberPhone = numberPhone;
    }

    public Client(String fullName, Integer numberPhone) {
        this.fullName = fullName;
        this.numberPhone = numberPhone;
    }
}
