package com.project.gymmembership.controller;

import com.project.gymmembership.entity.Member;
import com.project.gymmembership.entity.MemberClassRegistration;
import com.project.gymmembership.service.MemberClassRegistrationService;
import com.project.gymmembership.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberClassRegistrationService memberClassRegistrationService;

    @Autowired
    public MemberController(MemberService memberService, MemberClassRegistrationService memberClassRegistrationService) {
        this.memberService = memberService;
        this.memberClassRegistrationService = memberClassRegistrationService;
    }


    @GetMapping("/list")
    public String listMembers(Model model) {

        model.addAttribute("members", memberService.findAll());

        return "members-list";
    }

    @GetMapping("/showAddMemberForm")
    public String showAddMemberForm(Model model){
        Member member = new Member();

        model.addAttribute("member", member);

        return "member-add";
    }

    @GetMapping("/delete")
    public String deleteMemberById(@RequestParam int memberId){
        memberService.deleteById(memberId);

        return "redirect:/members/list";
    }

    @GetMapping("/update")
    public String showUpdateMemberForm(@RequestParam int memberId, Model model){

        model.addAttribute("member", memberService.findById(memberId));

        return "member-update";
    }

    @PostMapping("/update")
    public String updateMember(@ModelAttribute("member") Member member, Model model){

        List<MemberClassRegistration> registrations = memberClassRegistrationService.getClassRegistrationsForMember(member);

        member.setMemberClassRegistrations(registrations);

        for(MemberClassRegistration mcr : registrations){
            member.getClasses().add(mcr.getTheClass());
        }

        try{
            memberService.updateMember(member);
        } catch (RuntimeException e) {
            model.addAttribute("member", member);
            model.addAttribute("duplicateEntry", "A Member With The Same E-mail And/Or Phone Is Already Registered");
            return "member-update";
        }

        model.addAttribute("member", member);
        model.addAttribute("registrations", registrations);

        return "member-details";
    }

    @GetMapping("/search")
    public String search(@RequestParam String searchWord, Model model){
        List<Member> members = memberService.searchForMembers(searchWord);

        model.addAttribute("members", members);
        model.addAttribute("searchWord", searchWord);

        if (members.size() == 0){
            model.addAttribute("notFound", "No Member Found Matching '" + searchWord + "'");
        }

        return "search-result";
    }

}
