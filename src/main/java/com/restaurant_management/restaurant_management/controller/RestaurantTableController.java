package com.restaurant_management.restaurant_management.controller;

import com.restaurant_management.restaurant_management.db.RestaurantTable;
import com.restaurant_management.restaurant_management.service.RestaurantTableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantTableController {

    private final RestaurantTableService restaurantTableService;

    public RestaurantTableController(RestaurantTableService restaurantTableService) {
        this.restaurantTableService = restaurantTableService;
    }

    @GetMapping("/tables")
    public List<RestaurantTable> getTables() {
        return restaurantTableService.getAllTables();
    }

    @GetMapping("/tables/{id}")
    public RestaurantTable getTableById(@PathVariable Integer id) {
        return restaurantTableService.getTableById(id);
    }

    @PostMapping("/table")
    public RestaurantTable addTable(@RequestBody RestaurantTable restaurantTable) {
        return restaurantTableService.addTable(restaurantTable);
    }

    @PutMapping("/table/{id}")
    public RestaurantTable updateTable(@PathVariable Integer id,
                                       @RequestBody RestaurantTable restaurantTable) {
        return restaurantTableService.updateTable(id, restaurantTable);
    }

    @DeleteMapping("/table/{id}")
    public void deleteTable(@PathVariable Integer id) {
        restaurantTableService.deleteTable(id);
    }
}
