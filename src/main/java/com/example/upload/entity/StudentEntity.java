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
    private Date birthday;
    private String phone;
    private String avatar;

    public StudentEntity(Integer id, String name, Date birthday, String phone, String avatar) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.avatar = avatar;
    }

    public StudentEntity() {
    }

    public StudentEntity(String name, Date birthday, String phone, String avatar) {
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() { // Sửa từ getBirtday thành getBirthday
        return this.birthday;
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

    public void setBirthday(Date birthday) { // Sửa từ setBirtday thành setBirthday
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
