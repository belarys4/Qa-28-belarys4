package ru.tinkoff.qa.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="animal")
public class Animal {
    @Id

    int id;

    @Column(name="`name`")
    String name;

    @Column(name="age")
    int age;

    @Column(name="`type`")
    int type;
    @Column(name="sex")
    int sex;
    @Column(name="place")
    int place;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal(int id, String name, int age, int type, int sex, int place) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;
        this.sex = sex;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
