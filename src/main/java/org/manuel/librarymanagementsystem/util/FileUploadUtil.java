package org.manuel.librarymanagementsystem.util;

import lombok.NonNull;
import org.manuel.librarymanagementsystem.config.LibraryException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.manuel.librarymanagementsystem.enums.FileUploadPaths.UPLOAD_DIR;

@Component
public class FileUploadUtil {
    public String uploadFile(@NonNull MultipartFile file, String uploadPath) throws IOException {
        String filename = file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR.getPath() + uploadPath);
        if(!Files.exists(path)) Files.createDirectories(path);

        try(InputStream inputStream = file.getInputStream()) {
            assert filename != null;
            Path filePath = path.resolve(filename);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new LibraryException(e.getMessage());
        }

        return filename;
    }
}