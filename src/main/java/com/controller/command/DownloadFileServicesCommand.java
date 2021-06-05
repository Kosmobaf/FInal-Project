package com.controller.command;

import com.controller.Path;
import com.model.service.ServiceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadFileServicesCommand implements Command {
    private static final String ATTACHMENT_FILENAME_SERVICES_TXT = "attachment; filename=Services.txt";
    private static final String CONTENT_DISPOSITION = "Content-disposition";
    private static final String TEXT_PLAIN = "text/plain";
    private static final String FILE_PATH = "C:\\Program Files\\apache-tomcat-9.0.46\\apache-tomcat-9.0.46\\bin\\";
    private static final String FILE_NAME = "Services.txt";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        ServiceService service = new ServiceService();
        service.exportServicesToFile();

        resp.setContentType(TEXT_PLAIN);
        resp.setHeader(CONTENT_DISPOSITION, ATTACHMENT_FILENAME_SERVICES_TXT);

        try (FileInputStream inputStream = new FileInputStream(FILE_PATH + FILE_NAME);
             OutputStream out = resp.getOutputStream()) {

            int numBytesRead;
            while ((numBytesRead = inputStream.read()) != -1) {
                out.write(numBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Path.REDIRECT_USER_BASIS;
    }
}
