package com.example.petshop.model;

public class SpeciesDog {
    private int id;
    private String name;
    private String description;
    private boolean status;

    public SpeciesDog(int id, String name,String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public SpeciesDog(String name, String description, boolean status) {
        this.name = name;
        this.description = description;
        this.status = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SpeciesDog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
