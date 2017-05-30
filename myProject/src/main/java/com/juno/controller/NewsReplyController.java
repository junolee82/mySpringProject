package com.juno.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juno.service.NewsReplyService;

@RestController
@RequestMapping("/replies")
public class NewsReplyController {
	
	@Inject
	private NewsReplyService service;
	
	

}
