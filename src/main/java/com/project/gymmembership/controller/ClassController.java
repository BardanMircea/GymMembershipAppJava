package com.project.gymmembership.controller;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Instructor;
import com.project.gymmembership.entity.Review;
import com.project.gymmembership.service.ClassService;
import com.project.gymmembership.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;
    private final InstructorService instructorService;

    @Autowired
    public ClassController(ClassService classService, InstructorService instructorService){
        this.classService = classService;
        this.instructorService = instructorService;
    }

    @GetMapping("/list")
    public String listClasses(Model model){

        model.addAttribute("classes", classService.findAll());

        return "classes-list";
    }

    @GetMapping("/getReviewsForClass")
    public String getReviewsForClass(@RequestParam int classId, Model model){

        Class theClass = classService.findById(classId);

        List<Review> reviews = classService.getReviewsForClass(theClass);

        model.addAttribute("reviews", reviews);
        model.addAttribute("theClass", theClass);

        if(reviews.size() == 0){
            model.addAttribute("noReviewsFound", "No Reviews To Show For This Class.");
        }

        return "show-reviews";
    }

    @GetMapping("/showAddClassForm")
    public String showAddClassForm(Model model){
        Class theClass = new Class();
        List<Instructor> instructors = instructorService.findAll();

        model.addAttribute("theClass", theClass);
        model.addAttribute("instructors", instructors);

        return "class-add-form";
    }

    @PostMapping("/addNewClass")
    public String addNewClass(@ModelAttribute Class theClass){

        Instructor instructor = instructorService.findByName(theClass.getInstructor().getName());
        theClass.setInstructor(instructor);
        classService.save(theClass);

        return "redirect:/classes/list";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam int classId){
        classService.deleteById(classId);

        return "redirect:/classes/list";
    }
}
