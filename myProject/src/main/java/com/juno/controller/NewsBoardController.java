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

import com.juno.domain.NewsBoardVo;
import com.juno.domain.PageMaker;
import com.juno.domain.SearchCriteria;
import com.juno.service.NewsBoardService;

@Controller
@RequestMapping("/newsBoard/*")
public class NewsBoardController {

	private static final Logger logger = LoggerFactory.getLogger(NewsBoardController.class);

	@Inject
	private NewsBoardService service;

	// listNews
	@RequestMapping(value = "news", method = RequestMethod.GET)
	public void newsPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info(cri.toString());

		model.addAttribute("list", service.listNewsCriteria(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listNewsCount(cri));
		model.addAttribute("pageMaker", pageMaker);

	}

	// readPage
	@RequestMapping(value = "readNews", method = RequestMethod.GET)
	public void read(int newsNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readNews", service.read(newsNo));
	}

	// register
	@RequestMapping(value = "registerNews", method = RequestMethod.GET)
	public void resistGET() {
		logger.info("resist get......");
	}

	@RequestMapping(value = "registerNews", method = RequestMethod.POST)
	public String registPOST(NewsBoardVo vo) throws Exception {
		logger.info("regist post......");
		logger.info(vo.toString());
		service.register(vo);
		return "redirect:/newsBoard/news";
	}

	// modify
	@RequestMapping(value = "modifyNews", method = RequestMethod.GET)
	public void modifyGET(int newsNo, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("readNews", service.read(newsNo));
	}

	@RequestMapping(value = "modifyNews", method = RequestMethod.POST)
	public String modifyPOST(NewsBoardVo vo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info(cri.toString());
		service.modify(vo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		logger.info(rttr.toString());
		return "redirect:/newsBoard/news";
	}

	// remove
	@RequestMapping(value = "removeNews", method = RequestMethod.POST)
	public String removeNews(int newsNo, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(newsNo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/newsBoard/news";
	}

	// upload
	@RequestMapping("getAttach/{newsNo}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("newsNo") int newsNo) throws Exception {
		return service.getAttach(newsNo);
	}
	
	// updateRecommend
	@RequestMapping("updateRecommend/{newsNo}")
	@ResponseBody
	public void updateRecommend(@PathVariable("newsNo") int newsNo) throws Exception {
		service.recommend(newsNo);
	}

}
