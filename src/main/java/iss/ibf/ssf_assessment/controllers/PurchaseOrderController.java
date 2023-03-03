package iss.ibf.ssf_assessment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import iss.ibf.ssf_assessment.models.Cart;
import iss.ibf.ssf_assessment.models.Item;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path={"/", "/view1.html"})
public class PurchaseOrderController {
    
    @GetMapping
    public String getView1(Model model, HttpSession session) {
        session.invalidate();
        // Cart cart = (Cart) session.getAttribute("cart");
        // if (null == cart) {
        //     cart = new Cart();
        //     session.setAttribute("cart", cart);
        // }
        model.addAttribute("item", new Item());
        model.addAttribute("cart", new Cart());

        return "view1";
    }

    @PostMapping
    public String postData(@Valid Item item, BindingResult result, Model model, HttpSession session) {
        
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
}
