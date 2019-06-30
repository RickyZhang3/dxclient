package org.com.dx.service.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.com.dx.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {
	
	private static final Logger  log = LoggerFactory.getLogger(FileStorageServiceImpl.class);
	
	private Path fileStorageLocation;
	
//	@Autowired
//    public FileStorageService(FileStorageProperties fileStorageProperties) {
//        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
//                .toAbsolutePath().normalize();
//
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception e) {
//        	e.printStackTrace();
////            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
//        }
//    }
	
	public String storeFile(MultipartFile file,String uploadDir) {
		
		this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
		
		log.info("file 存储路径:{}",fileStorageLocation);
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e) {
        	log.error("存储录音文件异常:{}",e);
        	e.printStackTrace();
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        	return null;
        }
    }
}
