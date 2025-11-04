package com.restaurant_management.restaurant_management.controller;

import com.restaurant_management.restaurant_management.db.Table;
import com.restaurant_management.restaurant_management.service.TableService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) { this.tableService = tableService;}

    @GetMapping
    public List<Table> getAllTables(){
        return tableService.getAllTables();
    }

    @PostMapping
    public Table addTable(@RequestBody Table table){
        return tableService.addTable(table);
    }
}
