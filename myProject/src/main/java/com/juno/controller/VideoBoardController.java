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

import com.juno.domain.PageMaker;
import com.juno.domain.SearchCriteria;
import com.juno.domain.VideoBoardVo;
import com.juno.service.VideoBoardService;

@Controller
@RequestMapping("/videoBoard/*")
public class VideoBoardController {

	private static final Logger logger = LoggerFactory.getLogger(VideoBoardController.class);

	@Inject
	private VideoBoardService<VideoBoardVo> service;

	// listVideoBoard
	@RequestMapping(value = "video", method = RequestMethod.GET)
	public void videoPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info(cri.toString());

		model.addAttribute("list", service.listCri(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	// readPage
	@RequestMapping(value = "readVideo", method = RequestMethod.GET)
	public void read(int vNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readVideo", service.read(vNo));
	}

	// register
	@RequestMapping(value = "registerVideo", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("register get......");
	}

	@RequestMapping(value = "registerVideo", method = RequestMethod.POST)
	public String registerPOST(VideoBoardVo vo) throws Exception {
		logger.info("register post......");
		logger.info(vo.toString());
		service.register(vo);
		return "redirect:/videoBoard/video";
	}

	// modify
	@RequestMapping(value = "modifyVideo", method = RequestMethod.GET)
	public void modifyGET(int vNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readVideo", service.read(vNo));
	}

	@RequestMapping(value = "modifyVideo", method = RequestMethod.POST)
	public String modifyPOST(VideoBoardVo vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info(cri.toString());
		service.modify(vo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		logger.info(rttr.toString());
		return "redirect:/videoBoard/video";
	}

	// remove
	@RequestMapping(value = "removeVideo", method = RequestMethod.POST)
	public String removeVideo(int vNo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(vNo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/videoBoard/video";
	}

	// getAttach
	@RequestMapping("getAttach/{mNo}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("vNo") int vNo) throws Exception {
		return service.getAttach(vNo);
	}

	// updateRecommend
	@RequestMapping("updateRecommend/{vNo}")
	@ResponseBody
	public void updateRecommend(@PathVariable("vNo") int vNo) throws Exception {
		service.recommend(vNo);
	}

}
