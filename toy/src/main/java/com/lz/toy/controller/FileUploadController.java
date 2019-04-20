package com.lz.toy.controller;

import com.lz.toy.common.controller.SuperController;
import com.lz.toy.common.util.CommonUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文件上传控制器
 * @author liuzhao
 */
@Log4j
@Controller
public class FileUploadController extends SuperController {

	/**
	 * 上传文件
	 * @param file 文件
	 * @return Map
	 * @throws IOException io异常
	 */
	@ResponseBody
	@RequestMapping("/file/upload")
	public Map<String, Object> fileUpload( @RequestParam MultipartFile[] file) throws IOException{
		
		List<String> urls = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		
		try {
			for(MultipartFile myfile : file){  
			        if(myfile.isEmpty()){  
			        	log.warn("文件未上传");
			        }else{
						log.debug("文件长度: " + myfile.getSize());
						log.debug("文件类型: " + myfile.getContentType());
						log.debug("文件名称: " + myfile.getName());
						log.debug("文件原名: " + myfile.getOriginalFilename());
			            String ext =  FilenameUtils.getExtension(myfile.getOriginalFilename());
			            String reName = CommonUtil.randomAlphanumeric(32) + "."+ ext;
			            String cdate = LocalDate.now().toString();
			            String realPath = request.getSession().getServletContext().getRealPath("/upload")+ File.separator +cdate; 
			            FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, reName));
			            urls.add("/upload/"+cdate+"/"+reName);
			        }  
			    }
			result.put("status", "success");
			result.put("urls",urls);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "error");
			return result;
		}  
	}

	@RequestMapping("/file/index")
	public String index(){
		return "/DataTableDemo/addImage";
	}
}
