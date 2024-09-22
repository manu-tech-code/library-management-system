package org.manuel.librarymanagementsystem.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.manuel.librarymanagementsystem.config.LibraryException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Objects;

import static org.manuel.librarymanagementsystem.enums.FileUploadPaths.UPLOAD_DIR;

@Component
@RequiredArgsConstructor
public class FileUploadUtil {
    private final Cloudinary cloudinary;
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

    public String uploadFileToCloudinary(@NonNull MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true,
                "public_id", file.getName()
        ));
        return uploadResult.get("secure_url").toString();
    }

    public Map getImageDetails(String publicId) throws Exception {
        return cloudinary.api().resource(publicId, ObjectUtils.asMap("quality_analysis", true));
    }
}