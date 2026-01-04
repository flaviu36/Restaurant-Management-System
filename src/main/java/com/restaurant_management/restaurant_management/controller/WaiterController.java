package com.restaurant_management.restaurant_management.controller;

import com.restaurant_management.restaurant_management.db.Waiter;
import com.restaurant_management.restaurant_management.service.WaiterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/waiters")
@CrossOrigin(origins = "*")
public class WaiterController {

    private final WaiterService waiterService;

    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @GetMapping
    public List<Waiter> getAllWaiters() {
        return waiterService.getAllWaiters();
    }

    @GetMapping("/{id}")
    public Waiter getWaiterById(@PathVariable Integer id) {
        return waiterService.getWaiterById(id);
    }

    @PostMapping
    public Waiter addWaiter(@RequestBody Waiter waiter) {
        return waiterService.addWaiter(waiter);
    }

    @PutMapping("/{id}")
    public Waiter updateWaiter(@PathVariable Integer id, @RequestBody Waiter waiter) {
        return waiterService.updateWaiter(id, waiter);
    }

    @DeleteMapping("/{id}")
    public void deleteWaiter(@PathVariable Integer id) {
        waiterService.deleteWaiter(id);
    }
}
