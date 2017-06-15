package com.juno.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/communityBoard/*")
public class CommunityBoardController {

	private static final Logger logger = LoggerFactory.getLogger(CommunityBoardController.class);

	@RequestMapping(value = "community", method = RequestMethod.GET)
	public void communityPage() {
		
	}
}
