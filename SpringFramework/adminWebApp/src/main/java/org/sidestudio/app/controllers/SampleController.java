package org.sidestudio.app.controllers;

import org.sidestudio.app.domain.Book;
import org.sidestudio.app.repository.SampleRepository;
import org.sidestudio.app.services.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * '파일 설명 작성'
 *
 * @author logan
 * @since 2016-03-03
 */
@Controller
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private SampleService sampleService;

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // BCryptPasswordEncoder

    @RequestMapping("/")
    public ModelAndView helloPage() {

        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

        ModelAndView mav = new ModelAndView("hello");
        mav.addObject("title","Hello World");
        mav.addObject("todaytime", Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        return mav;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ModelAndView bookListPage() {

        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

        ModelAndView mav = new ModelAndView("book/book");
        mav.addObject("title", "북 리스트");
        mav.addObject("bookList", sampleRepository.selectBookAllList());

        return mav;
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String createBook(
        Book book
    ) {

        sampleService.insertUser(book);

        return "redirect:/books";
    }

    /**
     * 로그인 페이지 호출
     *
     * @param httpServletRequest
     * @param authentication
     * @param principal
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView mainPage(
        HttpServletRequest httpServletRequest,
        Authentication authentication,
        Principal principal
    ) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView mav = new ModelAndView("login/login");
        mav.addObject("title", "로그인 페이지");
        return mav;
    }

    /**
     * 로그인 성공후 처리 작업
     *
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/loginSuccess")
    public String loginSuccess(

        HttpSession httpSession
    ) {

        // 세션 유지 시간 설정 (초)
        httpSession.setMaxInactiveInterval(60 * 60 * 1); // 세션 1시간으로 설정

        return "redirect:/";
    }
}
