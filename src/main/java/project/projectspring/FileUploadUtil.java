package project.projectspring;



import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUploadUtil {
    public static String saveFile(MultipartFile multipartFile) throws IOException {
    	String uploadDir = "/projectspring_projectspring/images";
 

        String fileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            Files.copy(multipartFile.getInputStream(), uploadPath.resolve(fileName));
        } catch (IOException ex) {
            throw new IOException("Could not save file: " + fileName, ex);
        }

        return fileName; 
    }
}



