package nl.novi.summerville.springboot.example.security.controller;

import nl.novi.summerville.springboot.example.security.repository.ReservationRepository;
import nl.novi.summerville.springboot.example.security.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    public ReservationRepository reservationRepository;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/all")
    public String allAccess() {
        return testService.generatePublicContent();
    }

    @GetMapping("/user")
    public String userAccess() {
        return testService.generateUserContent();
    }

    @GetMapping("/handyman")
    public String moderatorAccess() {
        return testService.generateModContent();
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return testService.generateAdminContent();
    }



}