package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledEmailCreatorService {

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;


    public String buildScheduledEmail(String message) {

        List<String> functionalityOfSceduledEmail = new ArrayList<>();
        functionalityOfSceduledEmail.add("You can see your created task");
        functionalityOfSceduledEmail.add("Provides connection with Trello Account");
        functionalityOfSceduledEmail.add("Application allows sending tasks to Trello");

        Context context2 = new Context();
        context2.setVariable("message", message);
        context2.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context2.setVariable("button", "See your created tasks");
        context2.setVariable("admin_name", adminConfig.getAdminName());
        context2.setVariable("company_details", companyConfig.getCompanyName() + "\n" + companyConfig.getCompanyEmail());
        context2.setVariable("goodbye_message", "Thanks");
        context2.setVariable("show_button", false);
        context2.setVariable("is_friend", true);
        context2.setVariable("admin_config", adminConfig);
        context2.setVariable("application_functionality", functionalityOfSceduledEmail);
        return templateEngine.process("mail/scheduled-email", context2);
    }

}
