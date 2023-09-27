package com.pollServicePollSystem.userPollSystem;

import java.time.LocalDate;

public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String address;
    private LocalDate joiningDate;
    private Boolean isRegistered;


    public UserResponse(){}

    public UserResponse(Long id, String firstName, String lastName, String email, Integer age, String address, LocalDate joiningDate, Boolean is_registered) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.address = address;
        this.joiningDate = joiningDate;
        this.isRegistered = is_registered;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public Boolean getIsRegistered() {
        return isRegistered;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setIsRegistered(Boolean is_registered) {
        this.isRegistered = is_registered;
    }
}
