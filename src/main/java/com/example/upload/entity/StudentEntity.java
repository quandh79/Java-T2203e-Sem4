package com.example.upload.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String birtday;
    private String phone;

    public StudentEntity(Integer id, String name, String birtday, String phone) {
        this.id = id;
        this.name = name;
        this.birtday = birtday;
        this.phone = phone;
    }

    public StudentEntity() {
    }

    public StudentEntity(String name, String birtday, String phone) {
        this.name = name;
        this.birtday = birtday;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirtday() {
        return birtday;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirtday(String birtday) {
        this.birtday = birtday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
