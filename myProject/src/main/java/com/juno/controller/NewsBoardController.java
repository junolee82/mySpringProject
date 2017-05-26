package com.juno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/newsBoard/*")
public class NewsBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsBoardController.class);
	
	// listAll
	@RequestMapping(value = "news", method = RequestMethod.GET)
	public void newsPage() {
		logger.info("news page......");
		
		
		
	}
	
}
