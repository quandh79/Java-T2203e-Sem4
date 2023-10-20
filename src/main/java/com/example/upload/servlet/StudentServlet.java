package com.example.upload.servlet;

import com.example.upload.dao.StudentDAO;
import com.example.upload.dao.impl.StudentDAOImpl;
import com.example.upload.entity.StudentEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        studentDAO = new StudentDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<html><body>");
        out.write("<form action='student-servlet' method='POST'>");
        out.write("<input type='text' name='studentName' placeholder='Student Name' />");
        out.write("<input type='text' name='studentBirthday' placeholder='Birthday' />");
        out.write("<input type='text' name='studentPhone' placeholder='Phone' />");
        out.write("<input type='submit' value='Submit' />");
        out.write("</form>");

        // Hiển thị danh sách sinh viên
        out.write("<h1>Danh sách sinh viên</h1>");
        out.write("<table>");
        out.write("<tr><th>Ma SV</th><th>Name</th><th>Birtday</th><th>phone</th></tr>");

        List<StudentEntity> studentList = studentDAO.getAllStudent();
        for (StudentEntity student : studentList) {
            out.write("<tr>");
            out.write("<td>"+student.getId()+"</td>");
            out.write("<td>"+student.getName()+"</td>");
            out.write("<td>"+student.getBirtday()+"</td>");
            out.write("<td>"+student.getPhone()+"</td>");
            out.write("</tr>");
        }

        out.write("</table>");

        out.write("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("studentName");
        String birthday = req.getParameter("studentBirthday");
        String phone = req.getParameter("studentPhone");

        if (name != null && birthday != null && phone != null) {
            StudentEntity newStudent = new StudentEntity(null,name,birthday,phone);
            System.out.println("11111"+newStudent.getName());

            try {
                studentDAO.createStudent(newStudent);
                resp.sendRedirect("student-servlet"); // Redirect back to the form page
            } catch (Exception e) {
                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
                out.write("<html><body>");
                out.write("<p>Error: " + e.getMessage() + "</p>");
                out.write("</body></html>");
            }
        }
    }
}
