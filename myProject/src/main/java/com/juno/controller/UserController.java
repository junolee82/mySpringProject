package com.juno.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juno.domain.UserVo;
import com.juno.dto.LoginDTO;
import com.juno.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Inject
	private UserService service;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {

	}

	@RequestMapping(value = "loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {
		UserVo vo = service.login(dto);

		if (vo == null) {
			return;
		}

		model.addAttribute("userVo", vo);

	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		Object obj = session.getAttribute("login");

		if (obj != null) {
			// UserVo vo = (UserVo) obj;

			session.removeAttribute("login");
			session.invalidate();

		}

		return "user/logout";
	}

	@RequestMapping(value = "join", method = RequestMethod.GET)
	public void joinGET() {

	}

	@RequestMapping(value = "join", method = RequestMethod.POST)
	public String joinPOST(UserVo vo) throws Exception {
		logger.info("join......");
		logger.info(vo.toString());
		service.join(vo);
		return "redirect:/main";
	}

	@RequestMapping(value = "checkId/{uId}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Boolean> chechId(@PathVariable("uId") String uId) throws Exception {
		ResponseEntity<Boolean> entity = null;
		
		try {
			UserVo id = service.checkId(uId);			

			boolean chechIdVal = false;

			if (id == null) {
				chechIdVal = true;
			}

			entity = new ResponseEntity<Boolean>(chechIdVal, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

}
