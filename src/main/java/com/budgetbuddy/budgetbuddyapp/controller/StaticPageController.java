package com.budgetbuddy.budgetbuddyapp.controller;// FormController.java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaticPageController {

    @GetMapping("/pexp")
    public String serveAddExpensePage() {
        return "forward:/add-personal-expense.html";
    }

    @GetMapping("/signup")
    public String serveSignupPage() {
        return "forward:/signup.html";
    }

    @GetMapping("/login")
    public String serveLoginPage() {
        return "forward:/login.html";
    }

    @GetMapping("/dashboard")
    public String serveDashboardPage() {
        return "forward:/dashboard.html";
    }
}