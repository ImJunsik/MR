package com.examples.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.servlet.BaseController;
import com.base.servlet.WebException;
import com.examples.domain.Authentication;

@Controller
@RequestMapping(value = "/examples")
public class ExampleLoginController extends BaseController {
    @RequestMapping(value = "/exampleLogin.do")
    public String login(@RequestParam String id, @RequestParam String password, HttpSession session) {
        if (id.equals("esr") && password.equals("esr")) {
            Authentication authentication = new Authentication();
            authentication.setId(id);
            authentication.setName("조용상");
            authentication.setDepartmentNo(3);

            session.setAttribute(Authentication.SESSION_ATTRIBUTE_KEY, authentication);
        } else {
            throw new WebException(messageSource, "login.error", new String[]{id});
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/exampleLogout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}