package nl.hava.dapaj.bankapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

    @GetMapping("join_dapaj")
    public String joinForm() {
        return "join_dapaj";
    }

/*    @PostMapping("join_dapaj")
    public String processJoinForm() {
        return null;
    }*/

}