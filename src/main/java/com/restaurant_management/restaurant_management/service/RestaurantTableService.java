package com.restaurant_management.restaurant_management.service;

import com.restaurant_management.restaurant_management.db.RestaurantTable;
import com.restaurant_management.restaurant_management.db.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;

    public RestaurantTableService(RestaurantTableRepository restaurantTableRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
    }

    // CREATE
    public RestaurantTable addTable(RestaurantTable restaurantTable) {
        return restaurantTableRepository.save(restaurantTable);
    }

    // READ
    public List<RestaurantTable> getAllTables() {
        return restaurantTableRepository.findAll();
    }

    public RestaurantTable getTableById(Integer id) {
        return restaurantTableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table with id " + id + " not found"));
    }

    // UPDATE
    public RestaurantTable updateTable(Integer id, RestaurantTable newRestaurantTable) {
        RestaurantTable existing = restaurantTableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table with id " + id + " not found"));

        existing.setTableNumber(newRestaurantTable.getTableNumber());
        existing.setSeats(newRestaurantTable.getSeats());
        existing.setStatus(newRestaurantTable.isStatus());
        existing.setWaiter(newRestaurantTable.getWaiter());

        return restaurantTableRepository.save(existing);
    }

    // DELETE
    public void deleteTable(Integer id) {
        restaurantTableRepository.deleteById(id);
    }
}
