package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class Common {

	
	public String fileUpload(HttpSession session
							, MultipartFile file, String category) {
		//서버의 물리적위치 
		String resources 
			= session.getServletContext().getRealPath("resources");
		//D://Study_Spring/...... /iot/resources
		//  /upload/notice/2021/02/03/erf32e33_abc.txt
		String upload = resources + "/upload";
		String folder = upload + "/" + category + "/" 
						+ new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		File f = new File( folder );
		if( ! f.exists() ) f.mkdirs();
		
		String uuid = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		try {
			file.transferTo( new File(folder, uuid) );
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return folder.substring(resources.length()+1) + "/" + uuid;
	}
	
	
	
}