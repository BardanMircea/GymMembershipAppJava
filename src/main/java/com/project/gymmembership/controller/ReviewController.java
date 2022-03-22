package com.project.gymmembership.controller;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.Member;
import com.project.gymmembership.entity.Review;
import com.project.gymmembership.service.ClassService;
import com.project.gymmembership.service.MemberClassRegistrationService;
import com.project.gymmembership.service.MemberService;
import com.project.gymmembership.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberService memberService;
    private final ClassService classService;
    private final MemberClassRegistrationService memberClassRegistrationService;

    @Autowired
    public ReviewController(MemberClassRegistrationService memberClassRegistrationService,
                            ReviewService reviewService, MemberService memberService, ClassService classService){
        this.reviewService = reviewService;
        this.memberService = memberService;
        this.classService = classService;
        this.memberClassRegistrationService = memberClassRegistrationService;
    }

    @GetMapping("/writeReviewForClass")
    public String writeReviewForClass(@RequestParam int memberId, @RequestParam int classId, Model model){

        Member member = memberService.findById(memberId);
        Class theClass = classService.findById(classId);
        Review review = new Review();

        model.addAttribute("member", member);
        model.addAttribute("theClass", theClass);
        model.addAttribute("review", review);


        return "review-form";
    }

    @PostMapping("/saveReviewForClass")
    public String saveReviewForClass(@ModelAttribute Review review, @RequestParam int classId, @RequestParam int memberId, Model model){
        Member member = memberService.findById(memberId);
        Class theClass = classService.findById(classId);

        if (review.getMessage().trim().length() > 0){
            review.setTheClass(theClass);
            review.setAuthor(member.getFirstName());

            reviewService.save(review);

            model.addAttribute("member", member);
            model.addAttribute("registrations", memberClassRegistrationService.getClassRegistrationsForMember(member));
            model.addAttribute("success", "Your Review Was Submitted");
        } else{
            review = new Review();

            model.addAttribute("member", member);
            model.addAttribute("theClass", theClass);
            model.addAttribute("review", review);
            model.addAttribute("emptyMessage", "Cannot Submit An Empty Review");

            return "review-form";
        }

        return "member-details";
    }
}
