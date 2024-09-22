package org.manuel.librarymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

    // Set your Cloudinary credentials

//    @Bean
//    String SendImageToCloudinary() throws Exception {
//        Dotenv dotenv = Dotenv.load();
//        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
//        cloudinary.config.secure = true;
//        System.out.println("cloudName: " + cloudinary.config.cloudName);
//
//
//// Upload the image
//        Map params1 = ObjectUtils.asMap(
//                "use_filename", true,
//                "unique_filename", false,
//                "overwrite", true
//        );
//
//        System.out.println("upload image" +
//                cloudinary.uploader().upload("https://cloudinary-devs.github.io/cld-docs-assets/assets/images/coffee_cup.jpg", params1));
//
//        // Get the asset details
//        Map params2 = ObjectUtils.asMap(
//                "quality_analysis", true
//        );
//
//        System.out.println("get image: " +
//                cloudinary.api().resource("coffee_cup", params2));
//
//        return "Image uploaded to Cloudinary";
//    }
}
