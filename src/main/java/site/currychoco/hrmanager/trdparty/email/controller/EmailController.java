package site.currychoco.hrmanager.trdparty.email.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.currychoco.hrmanager.trdparty.email.util.EmailSendUtil;

@RequiredArgsConstructor
@Controller
public class EmailController {

    private final EmailSendUtil emailUtil;

    @ResponseBody
    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception {

        String confirm = emailUtil.sendSimpleMessage(email);

        return confirm;
    }
}
