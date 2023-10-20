package com.example.upload.servlet;

import com.example.upload.dao.StudentDAO;
import com.example.upload.dao.impl.StudentDAOImpl;
import com.example.upload.entity.StudentEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class ListStudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    StudentEntity student = studentDAO.getStudentById(id);
                    System.out.println("Student List Size:11111111111 " + student.getId());
                    request.setAttribute("student", student);
                    request.getRequestDispatcher("list-student.jsp").forward(request, response);
                    break;
                case "delete":
                    int deleteId = Integer.parseInt(request.getParameter("id"));
                    studentDAO.deleteStudent(deleteId);
                    response.sendRedirect("student");
                    break;
            }
        }
        else {
            List<StudentEntity> studentList = studentDAO.getAllStudent();
            request.setAttribute("studentList", studentList);
            if (response.isCommitted()) {
                // Thực hiện xử lý khác ở đây hoặc trả về lỗi
            } else {
                request.getRequestDispatcher("list-student.jsp").forward(request, response);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            StudentEntity student = new StudentEntity(id, name, birthday, phone);
            studentDAO.updateStudent(student);
            response.sendRedirect("student");
        } else {
            String name = request.getParameter("name");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            StudentEntity newStudent = new StudentEntity(name, birthday, phone);
            studentDAO.createStudent(newStudent);
            response.sendRedirect("student");
        }
    }
}
