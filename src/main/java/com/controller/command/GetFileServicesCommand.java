package com.controller.command;

import com.model.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GetFileServicesCommand implements Command {
    private final int ARBITARY_SIZE = 1048;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ServiceService service = new ServiceService();
        service.exportServicesToFile();
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=Services.txt");

        try (InputStream in = req.getServletContext().getResourceAsStream("/META-INF/Services.txt");
             OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/WEB-INF/user/getFileServices.jsp";
    }
}
