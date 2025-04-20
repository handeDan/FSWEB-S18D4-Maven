package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
public class BurgerController {
    private  BurgerDao burgerDao;

    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> getAll() {
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Burger> getById(@PathVariable Integer id) {
        Burger burger = burgerDao.findById(id);
        // Unexpected error
        if (burger == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(burger);
    }

    @PostMapping
    public ResponseEntity<Burger> save(@RequestBody Burger burger) {
        Burger saved = burgerDao.save(burger);
        return ResponseEntity.ok(saved);
    }

    @PutMapping
    public ResponseEntity<Burger> updateBurger(@RequestBody Burger burger) {
        Burger updated = burgerDao.update(burger);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Burger> update(@PathVariable Integer id, @RequestBody Burger burger) {
        Burger existedBurger = burgerDao.findById(id);
        if(existedBurger == null) {
            throw new BurgerException("Burger not found", HttpStatus.NOT_FOUND);
        }
        burger.setId(id);
        BurgerValidation.validate(burger);
        Burger updated = burgerDao.update(burger);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBurger(@PathVariable Long id) {
        burgerDao.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable Double price) {
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType) {
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable String content) {
        return burgerDao.findByContent(content);
    }

}
