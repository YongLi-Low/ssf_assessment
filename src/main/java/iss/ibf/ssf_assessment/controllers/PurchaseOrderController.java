package iss.ibf.ssf_assessment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iss.ibf.ssf_assessment.models.Cart;
import iss.ibf.ssf_assessment.models.Item;
import iss.ibf.ssf_assessment.models.ShippingAddress;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
// @RequestMapping(path={"/", "/view1.html"})
public class PurchaseOrderController {
    
    @GetMapping(path={"/", "/view1.html"})
    public String getView1(Model model, HttpSession session) {
        session.invalidate();
        
        model.addAttribute("item", new Item());
        model.addAttribute("cart", new Cart());

        return "view1";
    }

    // Keep adding items to cart
    @PostMapping(path = "/")
    public String addItem(@Valid Item item, BindingResult result, Model model, HttpSession session) {
        
        Cart cart = (Cart)session.getAttribute("cart");

        if (null == cart) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        if (result.hasErrors()) {
            model.addAttribute("item", item);
            model.addAttribute("cart", cart);
            return "view1";
        }

        cart.addItemToCart(item);
        System.out.println(cart.toString());
        model.addAttribute("item", item);
        model.addAttribute("cart", cart);
        return "view1";
    }
    // Clicking next to Shipping Address page
    @GetMapping(path = "/shippingaddress")
    public String addAddress(Model model, HttpSession session) {
        
        Cart cart = (Cart)session.getAttribute("cart");
        
        // if (result.hasErrors()) {
        //     model.addAttribute("shippingaddress", shippingAddress);
        //     model.addAttribute("cart", cart);
        //     return "view2";
        // }

        System.out.println(cart.toString());

        model.addAttribute("shippingaddress", new ShippingAddress());
        // model.addAttribute("cart", cart);

        return "view2";
    }

    @PostMapping(path = "/shippingaddress/checkout")
    public String checkout(@Valid ShippingAddress shippingAddress, BindingResult result, Model model, HttpSession session) {
        Cart cart = (Cart)session.getAttribute("cart");
        
        if (result.hasErrors()) {
            model.addAttribute("shippingaddress", shippingAddress);
            model.addAttribute("cart", cart);
            return "view2";
        }

        System.out.println(cart.toString());

        model.addAttribute("shippingaddress", new ShippingAddress());
        model.addAttribute("cart", cart);

        return "view3";
    }

}
