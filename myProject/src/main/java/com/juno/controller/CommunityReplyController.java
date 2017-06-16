package com.juno.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.juno.domain.CommunityReplyVo;
import com.juno.domain.Criteria;
import com.juno.domain.PageMaker;
import com.juno.service.CommunityReplyService;

@RestController
@RequestMapping("/cReplies")
public class CommunityReplyController {

	@Inject
	private CommunityReplyService<CommunityReplyVo> service;

	@RequestMapping(value = "/{cNo}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listReply(@PathVariable("cNo") int cNo, @PathVariable("page") int page) {
		ResponseEntity<Map<String, Object>> entity = null;

		Criteria cri = new Criteria();
		cri.setPage(page);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		try {
			Map<String, Object> paramMap = new HashMap<>();
			List<CommunityReplyVo> vo = service.listReply(cNo, cri);
			paramMap.put("listReply", vo);

			int replyCount = service.count(cNo);
			pageMaker.setTotalCount(replyCount);
			paramMap.put("pageMaker", pageMaker);

			entity = new ResponseEntity<Map<String, Object>>(paramMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	// registerReply
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> registerReply(@RequestBody CommunityReplyVo vo) {
		ResponseEntity<String> entity = null;

		try {
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	// modifyReply
	@RequestMapping(value = "/{rNo}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> modifyReply(@PathVariable("rNo") int rNo, @RequestBody CommunityReplyVo vo) {
		ResponseEntity<String> entity = null;

		try {
			vo.setrNo(rNo);
			service.modifyReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

	// removeReply
	@RequestMapping(value = "/{rNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeReply(@PathVariable("rNo") int rNo) {
		ResponseEntity<String> entity = null;

		try {
			service.removeReply(rNo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

}
