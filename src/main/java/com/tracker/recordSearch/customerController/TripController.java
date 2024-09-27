package com.tracker.recordSearch.customerController;

import com.tracker.recordSearch.domain.Category;
import com.tracker.recordSearch.domain.TravelAgency;
import com.tracker.recordSearch.domain.Trip;
import com.tracker.recordSearch.dto.CategoryDto;
import com.tracker.recordSearch.dto.TripDto;
import com.tracker.recordSearch.repository.TravelAgencyRepo;
import com.tracker.recordSearch.repository.TripRepository;
import com.tracker.recordSearch.service.CategoryService;
import com.tracker.recordSearch.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    private final TripRepository tripRepository;

    private final CategoryService categoryService;

    @Autowired
    TravelAgencyRepo travelAgencyRepo;

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("page", "Trips");
        model.addAttribute("title", "Menu");
        List<Category> categories = categoryService.findAllByActivatedTrue();
        List<TripDto> trips = tripService.trips();
        model.addAttribute("trips", trips);
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/trip-detail/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        TripDto product = tripService.getById(id);
        List<TripDto> productDtoList = tripService.findAllByCategory(product.getCategory().getName());
        model.addAttribute("products", productDtoList);
        model.addAttribute("title", "Product Detail");
        model.addAttribute("page", "Product Detail");
        model.addAttribute("productDetail", product);
        return "product-detail";
    }

    @GetMapping("/shop-detail")
    public String shopDetail(Model model) {
        List<CategoryDto> categories = categoryService.getCategoriesAndSize();
        model.addAttribute("categories", categories);
        List<TripDto> products = tripService.randomTrip();
        List<TripDto> listView = tripService.listViewTrips();
        model.addAttribute("productViews", listView);
        model.addAttribute("title", "Shop Detail");
        model.addAttribute("page", "Shop Detail");
        model.addAttribute("products", products);
        return "shop-detail";
    }

    @GetMapping("/high-price")
    public String filterHighPrice(Model model) {
        List<CategoryDto> categories = categoryService.getCategoriesAndSize();
        model.addAttribute("categories", categories);
        List<TripDto> trips = tripService.filterHighTrips();
        List<TripDto> listView = tripService.listViewTrips();
        model.addAttribute("title", "Shop Detail");
        model.addAttribute("page", "Shop Detail");
        model.addAttribute("tripViews", listView);
        model.addAttribute("trips", trips);
        return "shop-detail";
    }

    @GetMapping("/lower-price")
    public String filterLowerPrice(Model model) {
        List<CategoryDto> categories = categoryService.getCategoriesAndSize();
        model.addAttribute("categories", categories);
        List<TripDto> trips = tripService.filterLowerTrips();
        List<TripDto> listView = tripService.listViewTrips();
        model.addAttribute("tripViews", listView);
        model.addAttribute("title", "Shop Detail");
        model.addAttribute("page", "Shop Detail");
        model.addAttribute("trips", trips);
        return "shop-detail";
    }

    @GetMapping("/find-trips/{id}")
    public String productsInCategory(@PathVariable("id") Long id, Model model) {
        List<CategoryDto> categoryDtos = categoryService.getCategoriesAndSize();
        List<TripDto> tripDtos = tripService.findByCategoryId(id);
        List<TripDto> listView = tripService.listViewTrips();
        model.addAttribute("productViews", listView);
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("title", tripDtos.get(0).getCategory().getName());
        model.addAttribute("page", "Trip-Category");
        model.addAttribute("trips", tripDtos);
        return "products";
    }

    @GetMapping("/search-trip")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        List<CategoryDto> categoryDtos = categoryService.getCategoriesAndSize();
        List<TripDto> tripDtos = tripService.searchTrips(keyword);
        List<TripDto> listView = tripService.listViewTrips();
        model.addAttribute("tripViews", listView);
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("title", "Search Trips");
        model.addAttribute("page", "Result Search");
        model.addAttribute("trips", tripDtos);
        return "trips";
    }

//    @GetMapping("/new/trip/{id}")
//    public String showNewRProductForm(@PathVariable("id") Long id, Model model){
//        TravelAgency agency = travelAgencyRepo.findById(id).get();
//        model.addAttribute("trip",new Trip());
//        model.addAttribute("agency", agency);
//        return "product/addProduct";
//    }

//    @PostMapping("/save-product")
//    public String saveProduct(@ModelAttribute("tripDto") TripDto product,
//                              @RequestParam("imageTrip") MultipartFile imageProduct,
//                              RedirectAttributes redirectAttributes, Principal principal) {
//        try {
//            if (principal == null) {
//                return "redirect:/login";
//            }
//            tripService.save(imageProduct, product);
//            redirectAttributes.addFlashAttribute("success", "Add new trip successfully!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("error", "Failed to add new trip!");
//        }
//        return "redirect:/products/0";
//    }

    //find all trip in a travel agency
    @RequestMapping(value = "/travel/agency/trips/{id}")
    public String getTripByTravelAgencyId(@PathVariable Long id,Model model){
        List<Trip> travelAgencyTrip = tripService.getTripByTravelAgencyId(id);
        TravelAgency travelAgency = travelAgencyRepo.findById(id).get();
        model.addAttribute("travelAgencyTrip",travelAgencyTrip);
        model.addAttribute("travelAgency", travelAgency);
//        model.addAttribute("cartCount", GlobalData.cart.size());
//        model.addAttribute("cartCountTrip", GlobalTrips.cart.size());
        return "trip/travelAgencyTrips";
    }

    //SEE THE FULL PROFILE OF TRIP
    @GetMapping("/trip/detail/{id}")
    public String showTripDetail(@PathVariable("id") Long id, Model model){
        Trip trip = tripRepository.findById(id).get();
//        model.addAttribute("cartCount", GlobalData.cart.size());
//        model.addAttribute("cartCountTrip", GlobalTrips.cart.size());
        model.addAttribute("trip", trip);
        return "trip/tripDetail";
    }

}
