package com.juno.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.juno.domain.FileBean;

@Controller
public class ViewController {

	private static final Logger logger = LoggerFactory.getLogger(ViewController.class);

	@RequestMapping(value = "/file_upload", method = RequestMethod.POST)
	public String procFileUpload(FileBean fileBean, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		String root_path = session.getServletContext().getRealPath("/");
		String attach_path = "resources/files/attach/";
		MultipartFile upload = fileBean.getUpload();
		String filename = "";
		String CKEditorFuncNum = "";
		
		if (upload != null) {
			filename = upload.getOriginalFilename();
			fileBean.setFilename(filename);
			CKEditorFuncNum = fileBean.getCKEditorFuncNum();
			try {
				File file = new File(root_path + attach_path + filename);
				logger.info(root_path + attach_path + filename);
				upload.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String file_path = "/" + attach_path + filename;
		
		model.addAttribute("file_path", file_path);
		model.addAttribute("CKEditorFuncNum", CKEditorFuncNum);
		
		return "fileupload";
	}
}
