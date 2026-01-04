package com.restaurant_management.restaurant_management.service;

import com.restaurant_management.restaurant_management.db.Waiter;
import com.restaurant_management.restaurant_management.db.WaiterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaiterService {

    private final WaiterRepository waiterRepository;

    public WaiterService(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    // CREATE
    public Waiter addWaiter(Waiter waiter) {
        return waiterRepository.save(waiter);
    }

    // READ
    public List<Waiter> getAllWaiters() {
        return waiterRepository.findAll();
    }

    public Waiter getWaiterById(Integer id) {
        return waiterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Waiter with id " + id + " not found"));
    }

    // UPDATE
    public Waiter updateWaiter(Integer id, Waiter newWaiter) {
        Waiter existingWaiter = getWaiterById(id);

        existingWaiter.setName(newWaiter.getName());
        existingWaiter.setEmail(newWaiter.getEmail());

        return waiterRepository.save(existingWaiter);
    }

    // DELETE
    public void deleteWaiter(Integer id) {
        waiterRepository.deleteById(id);
    }
}
