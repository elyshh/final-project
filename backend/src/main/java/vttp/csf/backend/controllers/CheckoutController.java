// package vttp.csf.backend.controllers;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
// public class CheckoutController {
    
//     @Value("${STRIPE_KEY}")
//     private String stripeKey;

//     @RequestMapping("/checkout")
//     public String checkout(Model model) {
//         // To add another model
//         model.addAttribute("stripeKey", stripeKey);
//         model.addAttribute("currency", ChargeRequest.Currency.EUR);
//         return "checkout";
//     }

// }
