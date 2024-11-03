package com.example.demo.controller;

import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobController {

    @Autowired
    private JobRepository jobRepository; // Injecting the repository directly

    @PostMapping("/postjob")
    public String jobRegistration(@ModelAttribute Job job) {
        System.out.println(job.toString());
        jobRepository.save(job);
        return "redirect:/jobs" ;
    }


   @GetMapping("/jobs")
    public String showAllJobs(
            @RequestParam(required = false) String filterJobId,
            @RequestParam(required = false) String filterTitle,
            @RequestParam(required = false) String filterCompany,
            @RequestParam(required = false) String filterLocation,
            @RequestParam(required = false) String filterDescription,
            @RequestParam(required = false) String filterLink,
            Model model) {
        
        List<Job> allJobs = jobRepository.findAll();
        
        // Apply filters if they are provided
        if (filterJobId != null && !filterJobId.isEmpty()) {
            allJobs = allJobs.stream()
                    .filter(job -> String.valueOf(job.getId()).contains(filterJobId))
                    .collect(Collectors.toList());
        }
        if (filterTitle != null && !filterTitle.isEmpty()) {
            allJobs = allJobs.stream()
                    .filter(job -> job.getTitle().toLowerCase().contains(filterTitle.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (filterCompany != null && !filterCompany.isEmpty()) {
            allJobs = allJobs.stream()
                    .filter(job -> job.getCompany().toLowerCase().contains(filterCompany.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (filterLocation != null && !filterLocation.isEmpty()) {
            allJobs = allJobs.stream()
                    .filter(job -> job.getLocation().toLowerCase().contains(filterLocation.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (filterDescription != null && !filterDescription.isEmpty()) {
            allJobs = allJobs.stream()
                    .filter(job -> job.getDescription().toLowerCase().contains(filterDescription.toLowerCase()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("jobs", allJobs);
        return "jobs";
    }


    
}


