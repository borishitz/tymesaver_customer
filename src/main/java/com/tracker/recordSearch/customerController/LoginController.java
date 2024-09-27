package com.tracker.recordSearch.customerController;

import com.tracker.recordSearch.domain.Customer;
import com.tracker.recordSearch.dto.CustomerDto;
import com.tracker.recordSearch.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final CustomerService customerService;
    private final BCryptPasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("title", "Customer Login Page");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("page", "Register");
        model.addAttribute("customerDto", new CustomerDto());
        return "registration";
    }

    @PostMapping("/do-register")
    public String registerCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                   BindingResult result,
                                   Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("customerDto", customerDto);
                return "registration";
            }
            String username = customerDto.getUsername();
            Customer customer = customerService.findByUsername(username);
            if (customer != null) {
                model.addAttribute("customerDto", customerDto);
                model.addAttribute("error", "Email has been register!");
                return "registration";
            }
            if (customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
                customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
                customerService.save(customerDto);
                model.addAttribute("success", "Registration successfully!");
            } else {
                model.addAttribute("error", "Password is incorrect");
                model.addAttribute("customerDto", customerDto);
                return "registration";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Server is error, try again later!");
        }
        return "redirect:/login";
    }
}
