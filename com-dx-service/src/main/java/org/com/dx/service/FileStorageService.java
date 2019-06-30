package org.com.dx.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
	
	String storeFile(MultipartFile file, String uploadDir);
}
