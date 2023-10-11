package com.example.upload.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(value = "/upload-file")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Writer writer = resp.getWriter();
        writer.write("<html><body>");
        writer.write("<form action='upload-file' method='post' enctype='multipart/form-data'>");
        writer.write("<input type='file' name='fileToUpload' />");
        writer.write("<input type='submit' value='Upload File' />");
        writer.write("</form>");
        writer.write("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Đường dẫn đến thư mục lưu trữ file
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploadfiles";
        File uploadDirectory = new File(uploadPath);

        // Tạo thư mục nếu nó chưa tồn tại
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        // Sử dụng Commons FileUpload để xử lý yêu cầu upload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                // Xác định tên file và đường dẫn đến file
                String fileName = new File(item.getName()).getName();
                String filePath = uploadPath + File.separator + fileName;
                File uploadedFile = new File(filePath);

                // Lưu file lên thư mục upload
                item.write(uploadedFile);

                // Có thể thực hiện thêm xử lý ở đây
            }
            resp.getWriter().write("File đã được tải lên thành công!");
        } catch (Exception e) {
            resp.getWriter().write("Lỗi khi tải file lên: " + e.getMessage());
        }
    }

}
