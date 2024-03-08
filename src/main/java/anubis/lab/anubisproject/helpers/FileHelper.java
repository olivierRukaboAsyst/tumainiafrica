package anubis.lab.anubisproject.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileHelper {

    public static Message message;

    public static void saveFile(String uploadDir, Locale locale, String fileName, MultipartFile multipartFile)
            throws IOException {

        Path uploadPath = Paths.get("../webapps/ims/WEB-INF/classes/" + uploadDir);
        Path uploadPath1 = null;
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path uploadPathLocal = Paths.get("src/main/resources/");
            Path uploadPathLocal1 = Paths.get("target/classes/");
            if (Files.isDirectory(uploadPathLocal) && Files.isDirectory(uploadPathLocal1)) {
                uploadPath = Paths.get("src/main/resources/");
                uploadPath1 = Paths.get("target/classes/");
            }
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                if (uploadPath1 != null) {
                    Files.createDirectories(uploadPath1);
                }
            }
            if (!fileName.equalsIgnoreCase("") || fileName != null) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ioe) {
            throw new IOException(message.savefileError(locale));
        }
    }

    public static void deleteDirectoryRecursion(File file, Locale locale) throws IOException {
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectoryRecursion(entry, locale);
                }
            }
        }
        try {
            file.delete();
        } catch (Exception e) {
            throw new IOException(message.deleteFileError(locale));
        }
    }

    public static byte[] getFile(String path, Locale locale) throws IOException {
        ClassPathResource file = new ClassPathResource(path);
        byte[] imageBytes = null;
        try {
            imageBytes = StreamUtils.copyToByteArray(file.getInputStream());
        } catch (Exception e) {
            throw new IOException(message.echec(locale));
        }
        if (imageBytes != null) {
            return imageBytes;
        } else {
            throw new IOException(message.notExiste(locale));
        }

    }

}
