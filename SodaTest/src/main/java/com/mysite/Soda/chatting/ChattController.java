package com.mysite.Soda.chatting;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ChattController {

    private final ChattService chattService;

    @PostMapping("/mychatt")
    public String chatt(@RequestBody List<String> selectedItems, Model model) {
        List<InviteMemberVO> inviteMembers = chattService.inviteMember(selectedItems.toArray(new String[0]));
        model.addAttribute("inviteMembers", inviteMembers);

        return "chatting";
    }
}


