package com.cqut.util;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewGenerator {
    static String DEAFULT_FAIL_URL = "error";

    public static ModelAndView GENERATE_FAIL_VIEW() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(DEAFULT_FAIL_URL);
        return modelAndView;
    }

    public static ModelAndView GENERATE_FAIL_VIEW(String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(DEAFULT_FAIL_URL);
        modelAndView.addObject("failMessage", message);
        return modelAndView;
    }


}
