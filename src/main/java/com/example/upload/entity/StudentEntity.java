package com.example.upload.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="student")
public class StudentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date birtday;
    private String phone;

    public StudentEntity(Integer id, String name, Date birtday, String phone) {
        this.id = id;
        this.name = name;
        this.birtday = birtday;
        this.phone = phone;
    }

    public StudentEntity() {
    }

    public StudentEntity(String name, Date birtday, String phone) {
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

    public Date getBirtday() {
        return this.birtday;
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

    public void setBirtday(Date birtday) {
        this.birtday = birtday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
