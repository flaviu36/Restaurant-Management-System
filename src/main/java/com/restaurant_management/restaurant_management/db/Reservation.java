package com.restaurant_management.restaurant_management.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String email;
    private LocalDate date;
    private Time time;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "waiter_id")
        private Waiter waiter;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "reservation_table",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "table_id")
    )
    private List<RestaurantTable> restaurantTables = new ArrayList<>();

    @Override
    public String toString() {
        return "{"
                + "\"id\": " + id + ", "
                + "\"customerName\": \"" + customerName + "\", "
                + "\"date\": \"" + date + "\", "
                + "\"time\": \"" + time + "\""
                + "}";
    }
}