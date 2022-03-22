package com.project.gymmembership.controller;

import com.project.gymmembership.entity.Instructor;
import com.project.gymmembership.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService){
        this.instructorService = instructorService;
    }

    @GetMapping("/list")
    public String listInstructors(Model model){

        List<Instructor> instructors = instructorService.findAll();

        int max = 0;
        if (instructors.size() > 0) {
            max = instructors.get(0).getClasses().size();
            for (Instructor instructor : instructors) {
                if (instructor.getClasses().size() > max) {
                    max = instructor.getClasses().size();
                }
            }
        }

        model.addAttribute("maxClassListSize", max);
        model.addAttribute("instructors", instructors);

        return "instructors-list";
    }

    @GetMapping("/delete")
    public String deleteInstructor(@RequestParam int instructorId){
        instructorService.deleteById(instructorId);

        return "redirect:/instructors/list";

    }
}
