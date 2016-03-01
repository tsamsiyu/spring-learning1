package ts.edu.controllers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class UploadController {

    private static final Logger logger = Logger.getLogger(UploadController.class.getName());

    @RequestMapping(value = "/upload-form", method = RequestMethod.GET)
    public String uploadForm() {
        return "uploadfile";
    }

    @RequestMapping(value = "/upload-file", method = RequestMethod.POST)
    public @ResponseBody String uploadFile(@RequestParam("file")MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String name = file.getOriginalFilename();
                String webRoot = System.getProperty("catalina.home");
                File dir = new File(webRoot + File.separator + "tmpFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                return "File have been succesfully uploaded";
            } catch (IOException e) {
                return e.getMessage();
            }
        } else {
            return "file was not be sent.";
        }
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "IOException exception! check arguments!")
    @ExceptionHandler(IOException.class)
    public void handleIOException() {
        logger.error("IO exception");
    }

}
