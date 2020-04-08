package com.foodOrder.controllers;

import com.foodOrder.model.User;
import com.foodOrder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Scanner;

/**
 * Author: Hosanna Gabe-Oji.
 * Project: foodOrder.
 * Date: 4/8/2020.
 */

@RestController
public class LoginController {

    static String extractPostRequestBody(HttpServletRequest request) throws IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService userService;



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username", defaultValue = "null") String username,
                        HttpServletRequest request,
                        HttpServletRequest response,
                        HttpSession session)
    {
        if((request.getAttribute("IsValidRequest")!= null) && request.getAttribute("IsValidRequest").equals("false")){
            return "Invalid Request! Token Mismatch Error!";
        }

        String headerPassword = request.getHeader("password").trim();


        if(session.isNew()){
            session.setMaxInactiveInterval(1000);
            for (User user : userService.findAll()) {
                if (user.getUsername().equals(username.trim())) {
                    if (bCryptPasswordEncoder.matches(headerPassword,user.getPassword())) {
                        System.out.println("Valid Credentials");
                        if (!user.isLoggedIn()) {
                            user.setLoggedIn(true);
                            session.setAttribute("u", username);
                            userService.save(user);
                            System.out.println("****user toke is****");
                            System.out.println("randomtoken"+username);
                            session.setAttribute("token","randomtoken"+username);

                            return "User Logged In Successfully!";
                        } else {
                            System.out.println("****user toke is****");
                            System.out.println("randomtoken"+username);

                            return "User Already Logged In";

                        }
                    } else
                        return "Password Incorrect Error!!";
                }
            }
            return "User Name Not Found";
        }

        return "User Already Logged In";
    }
}
