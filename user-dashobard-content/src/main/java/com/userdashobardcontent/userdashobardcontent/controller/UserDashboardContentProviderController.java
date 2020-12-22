package com.userdashobardcontent.userdashobardcontent.controller;

import com.userdashobardcontent.userdashobardcontent.service.UserDashboardContentProvider;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@AllArgsConstructor
public class UserDashboardContentProviderController {

    private UserDashboardContentProvider userDashboardContentProvider;

    /*An REST endpoint which provides dashboard content according to search key*/
    @GetMapping("/content/{searchKey}")
    public List<String> getTailoredUserDashboardContent(@PathVariable String searchKey)
    {
        return userDashboardContentProvider.getDashboardContentAccordingToSearchKey(searchKey);
    }
}
