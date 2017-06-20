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

import com.juno.domain.MagazineBoardVo;
import com.juno.domain.PageMaker;
import com.juno.domain.SearchCriteria;
import com.juno.service.MagazineBoardService;

@Controller
@RequestMapping("/magazineBoard/*")
public class MagazineBoardController {

	private static final Logger logger = LoggerFactory.getLogger(MagazineBoardController.class);

	@Inject
	private MagazineBoardService service;

	// listMagazine
	@RequestMapping(value = "magazine", method = RequestMethod.GET)
	public void magazinePage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info(cri.toString());

		model.addAttribute("list", service.listMagazineCri(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listMagazineCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	// readPage
	@RequestMapping(value = "readMagazine", method = RequestMethod.GET)
	public void read(int mNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readMagazine", service.read(mNo));
	}

	// register
	@RequestMapping(value = "registerMagazine", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("register get......");
	}

	@RequestMapping(value = "registerMagazine", method = RequestMethod.POST)
	public String registerPOST(MagazineBoardVo vo) throws Exception {
		logger.info("register post......");
		logger.info(vo.toString());
		service.register(vo);
		return "redirect:/magazineBoard/magazine";
	}

	// modify
	@RequestMapping(value = "modifyMagazine", method = RequestMethod.GET)
	public void modifyGET(int mNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readMagazine", service.read(mNo));
	}

	@RequestMapping(value = "modifyMagazine", method = RequestMethod.POST)
	public String modifyPOST(MagazineBoardVo vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info(cri.toString());
		service.modify(vo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		logger.info(rttr.toString());
		return "redirect:/magazineBoard/magazine";
	}

	// remove
	@RequestMapping(value = "removeMagazine", method = RequestMethod.POST)
	public String removeMagazine(int mNo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(mNo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/magazineBoard/magazine";
	}

	// getAttach
	@RequestMapping("getAttach/{mNo}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("mNo") int mNo) throws Exception {
		return service.getAttach(mNo);
	}
	
	// updateRecommend
	@RequestMapping("updateRecommend/{mNo}")
	@ResponseBody
	public void updateRecommend(@PathVariable("mNo") int mNo) throws Exception {
		service.recommend(mNo);
	}	

}
