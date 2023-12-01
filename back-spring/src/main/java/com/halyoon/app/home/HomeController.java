package com.halyoon.app.home;


import com.halyoon.app.auth.AuthenticationResponse;
import com.halyoon.app.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/")
public class HomeController {
    @GetMapping
    public String getOrderedList(Model model) {

        Map<String, String> items = new HashMap<>();
        items.put("Stays", "http://localhost:8080/api/stay");
        items.put("Stays Length", "http://localhost:8080/api/stay/length");
        items.put("Like a stay", "http://localhost:8080/api/stay/length");

        model.addAttribute("items", items);

        return "api-list";
    }
}
