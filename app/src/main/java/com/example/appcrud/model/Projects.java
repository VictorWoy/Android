package com.example.appcrud.model;

import java.io.Serializable;

public class Projects implements Serializable {

    private Long id;
    private String titleProject;
    private String descriptionProject;
    private String startDateProject;
    private String endDateProject;
    private int amountPeopleProject;

    public  String toString() {
        return titleProject.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleProject() {
        return titleProject;
    }

    public void setTitleProject(String titleProject) {
        this.titleProject = titleProject;
    }

    public String getDescriptionProject() {
        return descriptionProject;
    }

    public void setDescriptionProject(String descriptionProject) {
        this.descriptionProject = descriptionProject;
    }

    public String getStartDateProject() {
        return startDateProject;
    }

    public void setStartDateProject(String startDateProject) {
        this.startDateProject = startDateProject;
    }

    public String getEndDateProject() {
        return endDateProject;
    }

    public void setEndDateProject(String endDateProject) {
        this.endDateProject = endDateProject;
    }

    public int getAmountPeopleProject() {
        return amountPeopleProject;
    }

    public void setAmountPeopleProject(int amountPeopleProject) {
        this.amountPeopleProject = amountPeopleProject;
    }
}