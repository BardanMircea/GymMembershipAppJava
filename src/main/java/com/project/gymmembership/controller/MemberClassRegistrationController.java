package com.project.gymmembership.controller;

import com.project.gymmembership.entity.Class;
import com.project.gymmembership.entity.MemberClassRegistration;
import com.project.gymmembership.entity.Member;
import com.project.gymmembership.service.MemberClassRegistrationService;
import com.project.gymmembership.service.ClassService;
import com.project.gymmembership.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/memberClassRegistration")
public class MemberClassRegistrationController {

    private final MemberClassRegistrationService memberClassRegistrationService;
    private final MemberService memberService;
    private final ClassService classService;

    @Autowired
    public MemberClassRegistrationController(MemberClassRegistrationService memberClassRegistrationService,
                                             MemberService memberService, ClassService classService){
        this.memberClassRegistrationService = memberClassRegistrationService;
        this.memberService = memberService;
        this.classService = classService;
    }


    @PostMapping ("/showClassRegistrationForm")
    public String showCourseRegistrationForm(@ModelAttribute Member member, Model model){

        try{
            memberService.save(member);
        } catch (Exception e) {
            model.addAttribute("member", member);
            model.addAttribute("duplicateEntry", "A Member With The Same E-mail And/Or Phone Is Already Registered");
            return "member-add";
        }

        addAllClassesAndClassRegistrationToTheModel(member, model);

        return "class-registration";
    }

    @PostMapping()
    public String addClassRegistration( @ModelAttribute MemberClassRegistration memberClassRegistration,
                                                  @RequestParam int memberId, Model model){

        addAndSaveMemberClassRegistration(memberClassRegistration, memberId);

        return "redirect:/members/list";
    }

    @GetMapping("/showMemberDetails")
    public String showMemberDetails(@RequestParam int memberId, Model model){

        Member member = memberService.findById(memberId);

        List<MemberClassRegistration> registrations = memberClassRegistrationService.getClassRegistrationsForMember(member);

        List<Class> classes = new ArrayList<>();

        for(MemberClassRegistration mcr : registrations){
            classes.add(mcr.getTheClass());
        }

        member.setClasses(classes);

        model.addAttribute("registrations", registrations);
        model.addAttribute("member",member);

        return "member-details";
    }

    @PostMapping("/showUpdatedMemberDetails")
    public String showUpdatedMemberDetails(@ModelAttribute MemberClassRegistration memberClassRegistration,
                                           @RequestParam int memberId, Model model){

        try{
            addAndSaveMemberClassRegistration(memberClassRegistration, memberId);

        } catch (EntityExistsException e){
            model.addAttribute("duplicateClassRegistration", "Member Already Registered for This Class.\n" +
                                                                            "See Member Details For Renewing Or Removing The Membership.");

            Member member = memberService.findById(memberId);
            addAllClassesAndClassRegistrationToTheModel(member, model);
            return "add-class-registration";
        }

        Member member = memberService.findById(memberId);

        List<MemberClassRegistration> registrations = memberClassRegistrationService.
                getClassRegistrationsForMember(member);

        model.addAttribute("registrations", registrations);
        model.addAttribute("member", member);

        return "member-details";
    }

    @GetMapping("/showAddNewClassForm")
    public String showAddNewClassForm(@RequestParam int memberId, Model model) {

        Member member = memberService.findById(memberId);

        addAllClassesAndClassRegistrationToTheModel(member, model);

        return "add-class-registration";
    }

    @GetMapping("/removeClassMembership")
    public String removeClassMembership(@RequestParam int memberId, @RequestParam int classId, Model model){

        memberClassRegistrationService.deleteByCompositePrimaryKey(memberId, classId);

        Member member = memberService.findById(memberId);

        List<MemberClassRegistration> registrations = memberClassRegistrationService.
                getClassRegistrationsForMember(member);

        model.addAttribute("registrations", registrations);
        model.addAttribute("member", member);

        return "member-details";
    }

    @GetMapping("/renewMembership")
    public String renewMembership(@RequestParam int classId, @RequestParam int memberId, Model model){
        Member member = memberService.findById(memberId);
        Class theClass = classService.findById(classId);

        MemberClassRegistration memberClassRegistration = memberClassRegistrationService.findByCompositePrimaryKey(member, theClass);

        memberClassRegistration.setPaidOn(Date.valueOf(LocalDate.now()));

        if(theClass.getDescription().contains("One Month")){
            memberClassRegistration.setPaidUntil(Date.valueOf(LocalDate.now().plusMonths(1)));
        } else{
            memberClassRegistration.setPaidUntil(Date.valueOf(LocalDate.now().plusMonths(3)));
        }

        memberClassRegistrationService.update(memberClassRegistration);

        List<MemberClassRegistration> registrations = memberClassRegistrationService.
                getClassRegistrationsForMember(member);

        model.addAttribute("registrations", registrations);
        model.addAttribute("member", member);

        return "member-details";
    }


    private void addAndSaveMemberClassRegistration(MemberClassRegistration memberClassRegistration, int memberId) {
        Class theClass = classService.findByDescription(memberClassRegistration.getTheClass().getDescription());
        Member member = memberService.findById(memberId);

        member.getClasses().add(theClass);

        Date from = Date.valueOf(LocalDate.now());
        Date until;
        if(theClass.getDescription().contains("One Month")){
            until = Date.valueOf(LocalDate.now().plusMonths(1));
        } else{
            until = Date.valueOf(LocalDate.now().plusMonths(3));
        }

        memberClassRegistration.setPaidOn(from);
        memberClassRegistration.setPaidUntil(until);
        memberClassRegistration.setMember(member);
        memberClassRegistration.setTheClass(theClass);

        memberClassRegistrationService.save(memberClassRegistration);
    }

    private void addAllClassesAndClassRegistrationToTheModel(Member member, Model model) {
        MemberClassRegistration memberClassRegistration = new MemberClassRegistration();

        memberClassRegistration.setMember(member);

        List<Class> theClasses = classService.findAll();

        model.addAttribute("theClasses", theClasses);
        model.addAttribute("memberClassRegistration", memberClassRegistration);
    }

}
