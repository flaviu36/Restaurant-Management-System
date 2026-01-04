package com.restaurant_management.restaurant_management.db;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Table(name = "restaurant_table")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer tableNumber;
    private Integer seats;
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "waiter_id")
    private Waiter waiter;

    @ManyToMany(mappedBy = "restaurantTables", fetch = FetchType.EAGER)
    private List<Reservation> reservations;
}
