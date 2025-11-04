package com.restaurant_management.restaurant_management.service;

import com.restaurant_management.restaurant_management.db.Table;
import com.restaurant_management.restaurant_management.db.TableRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TableService {
    private final TableRepository tableRepository;
    public TableService(TableRepository tableRepository) {this.tableRepository = tableRepository;}
    public Table addTable(Table table){return tableRepository.save(table);}
    public List<Table> getAllTables(){return tableRepository.findAll();}
    public void deleteTable(Table table){tableRepository.delete(table);}
    public void updateTable(Table table, Table newTable){
        Table myTable = tableRepository.findById(table.getId()).get();
        myTable = newTable;
        tableRepository.save(myTable);
    }
}
