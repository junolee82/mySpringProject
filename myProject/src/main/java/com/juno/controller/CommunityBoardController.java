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

import com.juno.domain.CommunityBoardVo;
import com.juno.domain.PageMaker;
import com.juno.domain.SearchCriteria;
import com.juno.service.CommunityBoardService;

@Controller
@RequestMapping("/communityBoard/*")
public class CommunityBoardController {

	private static final Logger logger = LoggerFactory.getLogger(CommunityBoardController.class);

	@Inject
	private CommunityBoardService<CommunityBoardVo> service;

	// listCommunityBoard
	@RequestMapping(value = "community", method = RequestMethod.GET)
	public void communityPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info(cri.toString());

		model.addAttribute("list", service.listCri(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	// readPage
	@RequestMapping(value = "readCommunity", method = RequestMethod.GET)
	public void read(int cNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readCommunity", service.read(cNo));
	}

	// register
	@RequestMapping(value = "registerCommunity", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("register get......");
	}

	@RequestMapping(value = "registerCommunity", method = RequestMethod.POST)
	public String registerPOST(CommunityBoardVo vo) throws Exception {
		logger.info("register post......");
		logger.info(vo.toString());
		service.register(vo);
		return "redirect:/communityBoard/community";
	}

	// modify
	@RequestMapping(value = "modifyCommunity", method = RequestMethod.GET)
	public void modifyGET(int cNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readCommunity", service.read(cNo));
	}

	@RequestMapping(value = "modifyCommunity", method = RequestMethod.POST)
	public String modifyPOST(CommunityBoardVo vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info(cri.toString());
		service.modify(vo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		logger.info(rttr.toString());
		return "redirect:/communityBoard/community";
	}

	// remove
	@RequestMapping(value = "removeCommunity", method = RequestMethod.POST)
	public String removeCommunity(int cNo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(cNo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/communityBoard/community";
	}

	// getAttach
	@RequestMapping("getAttach/{cNo}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("cNo") int cNo) throws Exception {
		return service.getAttach(cNo);
	}
	
	// updateRecommend
	@RequestMapping("updateRecommend/{cNo}")
	@ResponseBody
	public void updateRecommend(@PathVariable("cNo") int cNo) throws Exception {
		service.recommend(cNo);
	}

}
