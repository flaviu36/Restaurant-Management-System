package com.restaurant_management.restaurant_management.service;

import com.restaurant_management.restaurant_management.db.Waiter;
import com.restaurant_management.restaurant_management.db.WaiterRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WaiterService {
    private final WaiterRepository waiterRepository;
    public WaiterService(WaiterRepository waiterRepository) {this.waiterRepository = waiterRepository;}
    public Waiter addWaiter(Waiter waiter) {return waiterRepository.save(waiter);}
    public List<Waiter> getAllWaiters() {return waiterRepository.findAll();}
    public void deleteWaiter(Waiter waiter) {waiterRepository.delete(waiter);}
    public void updateWaiter(Waiter waiter, Waiter newWaiter) {
        Waiter myWaiter = waiterRepository.findById(waiter.getId()).get();
        myWaiter = newWaiter;
        waiterRepository.save(myWaiter);
    }
}
