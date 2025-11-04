package com.restaurant_management.restaurant_management.controller;

import com.restaurant_management.restaurant_management.db.Waiter;
import com.restaurant_management.restaurant_management.service.WaiterService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/waiters")
public class WaiterController {
    private final WaiterService waiterService;

    public WaiterController(WaiterService waiterService) { this.waiterService = waiterService;}

    @GetMapping
    public List<Waiter> getAllWaiters(){
        return waiterService.getAllWaiters();
    }

    @PostMapping
    public Waiter addWaiter(@RequestBody Waiter waiter){
        return waiterService.addWaiter(waiter);
    }
}
