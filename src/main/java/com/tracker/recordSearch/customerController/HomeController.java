package com.tracker.recordSearch.customerController;


import com.tracker.recordSearch.domain.Category;
import com.tracker.recordSearch.domain.Customer;
import com.tracker.recordSearch.domain.ShoppingCart;
import com.tracker.recordSearch.dto.TripDto;
import com.tracker.recordSearch.service.CategoryService;
import com.tracker.recordSearch.service.CityService;
import com.tracker.recordSearch.service.CustomerService;
import com.tracker.recordSearch.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CustomerService customerService;

    private final TripService tripService;
    private final PasswordEncoder passwordEncoder;

    private final CategoryService categoryService;
    private final CityService cityService;

    @RequestMapping(value = {"/home","","/"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpSession session) {
        model.addAttribute("title", "Home");
        model.addAttribute("page", "Home");
        List<Category> categories = categoryService.findAllByActivatedTrue();
        List<TripDto> trips = tripService.trips();
        model.addAttribute("trips", trips);
        model.addAttribute("categories", categories);
        if (principal != null) {
            Customer customer = customerService.findByUsername(principal.getName());
            session.setAttribute("username", customer.getFirstName() + " " + customer.getLastName());
            ShoppingCart shoppingCart = customer.getCart();
            if (shoppingCart != null) {
                session.setAttribute("totalItems", shoppingCart.getTotalItems());
            }
        }
        return "index";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("title", "Contact");
        model.addAttribute("page", "Contact");
        return "contact-us";
    }

}
