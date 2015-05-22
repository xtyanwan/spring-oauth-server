package cc.wdcy.web.controller;

import cc.wdcy.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handle 'client_details' management
 *
 * @author Shengzhao Li
 */
@Controller
public class ClientDetailsController {


    @Autowired
    private OauthService oauthService;


    @RequestMapping("client_details")
    public String clientDetails(Model model) {


        return "clientdetails/client_details";
    }


}