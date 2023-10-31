package com.example.bepopcorntime.age_limit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AgeLimitRESTController
{
    @Autowired
    AgeLimitRepository ageLimitRepository;

    @GetMapping("/ageLimits")
    public List<AgeLimit> getAgeLimits()
    {
        List<AgeLimit> ageLimits = ageLimitRepository.findAll();
        return ageLimits;
    }
}
