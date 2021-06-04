package com.controller.command;

import com.model.constants.Constants;
import com.model.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadFileServicesCommand implements Command {
    private final String filePath = "C:\\Program Files\\apache-tomcat-9.0.46\\apache-tomcat-9.0.46\\bin\\";
    private final String fileName = "Services.txt";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ServiceService service = new ServiceService();
        service.exportServicesToFile();
        resp.setContentType(Constants.TEXT_PLAIN);
        resp.setHeader(Constants.CONTENT_DISPOSITION, Constants.ATTACHMENT_FILENAME_SERVICES_TXT);

        try (FileInputStream inputStream = new FileInputStream(filePath + fileName);
             OutputStream out = resp.getOutputStream()) {

            int numBytesRead;
            while ((numBytesRead = inputStream.read()) != -1) {
                out.write(numBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Constants.REDIRECT_USER_BASIS;
    }
}