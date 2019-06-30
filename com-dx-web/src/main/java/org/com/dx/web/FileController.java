package org.com.dx.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.io.IOUtils;
import org.com.dx.common.RespData;
import org.com.dx.config.FileStorageProperties;
import org.com.dx.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传录音文件
 * @author ricky
 *
 */
@RestController
@RequestMapping("/dx")
public class FileController {
	private static final Logger  log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileStorageService fileStorageService; 
	
	@Autowired
	private FileStorageProperties fileStorageProperties;
	
	@Autowired  
    ResourceLoader resourceLoader;  
	
	@PostMapping("/uploadFile")
    public RespData<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			String fileName = fileStorageService.storeFile(file,fileStorageProperties.getUploadDir());

	        log.info("存储的文件名称：{}",fileName);
	        return new RespData<String>(RespData.SUCCESS,RespData.DEFAULT_MSG,fileName);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("上传文件失败:{}",e);
			return new RespData<String>(RespData.FAIL,RespData.ERROR_MSG,null);
		}

    }
	
	@RequestMapping(value = "/getAudio", method = {RequestMethod.GET})
    public ResponseEntity<byte[]> getAudio(@RequestParam("fileName") String fileName) {
    	
    	log.info("fileName:{}",fileName);
    	
        String filePath = fileStorageProperties.getUploadDir()+File.separator+fileName;  
        byte[] body = null;
		try {
			body = IOUtils.toByteArray(resourceLoader.getResource("file:"+filePath).getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}  
		
        String audioFile = filePath.substring(filePath.lastIndexOf('/')+1, filePath.length());  
        
        log.info("audioFile:{}",audioFile);
        
        HttpHeaders headers=new HttpHeaders();
        String finalFileNameString = "";
        try {
        	finalFileNameString = URLEncoder.encode(audioFile,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        headers.add("Content-Disposition", "attachment;filename="+finalFileNameString);  
        return new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);  

    }
}
