package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LinkTerminalController {

    @PostMapping("do_link_terminal")
    public String doLinkTerminalHandler(Model model) {
        return "";
    }
}
