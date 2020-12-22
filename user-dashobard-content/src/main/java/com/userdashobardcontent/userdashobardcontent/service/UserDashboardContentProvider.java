package com.userdashobardcontent.userdashobardcontent.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserDashboardContentProvider {

    private static Map<String,List<String>> dashboardContentAccordingToSerachKey=new HashMap<>();

    static
    {
        dashboardContentAccordingToSerachKey.put("Jeans",List.of("Levis","Wrangler","Flying Machine"));
        dashboardContentAccordingToSerachKey.put("T-Shirts",List.of("UCB","H&M","Flying Machine"));
        dashboardContentAccordingToSerachKey.put("Shoes",List.of("Redchief","Bluechief","Flying Machine"));
        dashboardContentAccordingToSerachKey.put("Watch",List.of("Casio","Titan","FastTrack"));
    }

    public List<String> getDashboardContentAccordingToSearchKey(String serachkey)
    {
        return dashboardContentAccordingToSerachKey.get(serachkey);
    }
}
