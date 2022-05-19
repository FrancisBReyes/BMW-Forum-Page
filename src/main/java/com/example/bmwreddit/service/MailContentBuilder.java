package com.example.bmwreddit.service;
//CREATE OUR EMAIL MESSAGE USING THE HTML TEMPLATE 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class MailContentBuilder {

	@Autowired
    private TemplateEngine templateEngine;

    String build(String message) {
        //INJECTING THE EMAIL MESSAGE INTO THE HTML TEMPLATE BY SETTING THE MESSAGE INTO
    	//THE CONTEXT OF THE TEMPLATEENGINE
    	Context context = new Context();
        context.setVariable("message", message);
        //PASS
        return templateEngine.process("mailTemplate", context);
    }
}
