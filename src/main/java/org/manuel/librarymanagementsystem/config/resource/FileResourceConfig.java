package org.manuel.librarymanagementsystem.config.resource;

import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.manuel.librarymanagementsystem.enums.FileUploadPaths.UPLOAD_DIR;

@Configuration
public class FileResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
       Path uploadDir = Paths.get(UPLOAD_DIR.getPath());
       String path = uploadDir.toFile().getAbsolutePath();
       registry.addResourceHandler("/uploads/**")
               .addResourceLocations("file:/"+path+"/");
    }
}
