package com.example.upload.dao.impl;

import com.example.upload.dao.StudentDAO;
import com.example.upload.entity.StudentEntity;
import com.example.upload.until.PersistenceUntil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    EntityManager en;
    EntityTransaction tran;
    public StudentDAOImpl() {
        en = PersistenceUntil.createEntityManagerFactory().createEntityManager();
        tran = en.getTransaction();
    }
    @Override
    public void createStudent(StudentEntity student) {
        try {
            tran.begin();
            en.persist(student);
            tran.commit();
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            tran.rollback();
        }
    }

    @Override
    public void updateStudent(StudentEntity student) {
        try {
            tran.begin();
            en.merge(student);
            tran.commit();
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            tran.rollback();
        }
    }

    @Override
    public StudentEntity getStudentById(int id) {
        return en.find(StudentEntity.class, id);

    }

    @Override
    public void deleteStudent(int id) {
        try {
            tran.begin();
            StudentEntity student = en.find(StudentEntity.class, id);
            if (student != null) {
                en.remove(student);
            }
            tran.commit();
        } catch (Exception ex) {
            System.out.printf(ex.getMessage());
            tran.rollback();
        }
    }

    @Override
    public List<StudentEntity> getAllStudent() {
        List<StudentEntity> students = new ArrayList<>();
        try {
            Query query = en.createQuery("select c from  StudentEntity c");
            return query.getResultList();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return students;
    }
}
