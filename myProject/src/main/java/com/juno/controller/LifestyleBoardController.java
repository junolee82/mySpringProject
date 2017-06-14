package com.juno.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.juno.domain.LifestyleBoardVo;
import com.juno.domain.PageMaker;
import com.juno.domain.SearchCriteria;
import com.juno.service.BoardService;

@Controller
@RequestMapping("/lifestyleBoard/*")
public class LifestyleBoardController {

	private static final Logger logger = LoggerFactory.getLogger(LifestyleBoardController.class);

	@Inject
	private BoardService<LifestyleBoardVo> service;

	// listLifestyle
	@RequestMapping(value = "lifestyle", method = RequestMethod.GET)
	public void lifestylePage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info(cri.toString());

		model.addAttribute("list", service.listCri(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	// readPage
	@RequestMapping(value = "readLifestyle", method = RequestMethod.GET)
	public void read(int lNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readLifestyle", service.read(lNo));
	}

	// register
	@RequestMapping(value = "registerLifestyle", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("register get......");
	}

	@RequestMapping(value = "registerLifestyle", method = RequestMethod.POST)
	public String registerPOST(LifestyleBoardVo vo) throws Exception {
		logger.info("register post......");
		logger.info(vo.toString());
		service.register(vo);
		return "redirect:/lifestyleBoard/lifestyle";
	}

	// modify
	@RequestMapping(value = "modifyLifestyle", method = RequestMethod.GET)
	public void modifyGET(int lNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readLifestyle", service.read(lNo));
	}
	
	@RequestMapping(value = "modifyLifestyle", method = RequestMethod.POST)
	public String modifyPOST(LifestyleBoardVo vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info(cri.toString());
		service.modify(vo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		logger.info(rttr.toString());
		return "redirect:/lifestyleBoard/lifestyle";
	}
	
	// remove
	@RequestMapping(value = "removeLifestyle", method = RequestMethod.POST)
	public String removeLifestyle(int lNo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(lNo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/lifestyleBoard/lifestyle";
	}

	// getAttach
	@RequestMapping("getAttach/{lNo}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("lNo") int lNo) throws Exception {
		return service.getAttach(lNo);
	}

}
