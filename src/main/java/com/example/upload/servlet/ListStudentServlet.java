package com.example.upload.servlet;

import com.example.upload.dao.StudentDAO;
import com.example.upload.dao.impl.StudentDAOImpl;
import com.example.upload.entity.StudentEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/student")
@MultipartConfig(
        fileSizeThreshold = 1024*1024*10,
        maxFileSize = 1024*1024*100,
        maxRequestSize = 1024*1024*100
)
public class ListStudentServlet extends HttpServlet {
    private StudentDAO studentDAO;
    private final int pageSize = 3;

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
            int pageNumber = request.getParameter("pageNumber") != null ? Integer.parseInt(request.getParameter("pageNumber")) : 1;
            int pageSize = request.getParameter("pageSize") != null ? Integer.parseInt(request.getParameter("pageSize")) : 3;

            List<StudentEntity> studentList = studentDAO.getStudentsWithPagination(pageNumber, pageSize);
            int totalStudents = studentDAO.getAllStudent().size();
            int totalPages = (int) Math.ceil((double) totalStudents / pageSize);

            request.setAttribute("studentList", studentList);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("totalPages", totalPages);

            if (response.isCommitted()) {
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
            String birthdayStr = request.getParameter("birthday");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            try {
                birthday = formatter.parse(birthdayStr);
            } catch (ParseException e) {
                e.printStackTrace(); // Handle the exception properly
            }
            String phone = request.getParameter("phone");
            //upload anh
            //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Thay đổi thành đường dẫn tới thư mục upload của bạn
            String uploadDirectory = "C:\\path\\to\\upload\\directory";

            // Tạo thư mục upload nếu nó chưa tồn tại
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            Part part = request.getPart("avatar");
            String fileName = part.getSubmittedFileName();

            StudentEntity student = new StudentEntity(id, name, birthday, phone,fileName);
            studentDAO.updateStudent(student);
            response.sendRedirect("student");
        } else {
            String name = request.getParameter("name");
            String birthdayStr = request.getParameter("birthday");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            try {
                birthday = formatter.parse(birthdayStr);
            } catch (ParseException e) {
                e.printStackTrace(); // Handle the exception properly
            }
            String phone = request.getParameter("phone");

            // Thay đổi thành đường dẫn tới thư mục upload của bạn
            String uploadDirectory = "G:\\APTECH\\JavaSwing\\upload\\FileUpload";

            // Tạo thư mục upload nếu nó chưa tồn tại
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            Part part = request.getPart("avatar");
            String fileName = part.getSubmittedFileName();
            String uploadPath = uploadDirectory + File.separator + fileName;
            File file = new File(uploadPath);
            try (InputStream input = part.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File has been saved at: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            StudentEntity newStudent = new StudentEntity(name, birthday, phone,fileName);
            studentDAO.createStudent(newStudent);
            response.sendRedirect("student");
        }
    }
}
