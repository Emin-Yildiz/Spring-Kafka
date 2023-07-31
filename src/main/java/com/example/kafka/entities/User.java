package com.example.kafka.entities;

import com.example.kafka.enums.Role;
public class User {
    private Long id;
    private String userName;
    private int age;
    private Role role;

    public User(Long id, String userName, int age, Role role) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.role = role;
    }

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
