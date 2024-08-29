package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/burger")
public class BurgerController {
    private BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao){
        this.burgerDao =burgerDao;
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger){
        BurgerValidation.exists(burgerDao, burger, false);
        return burgerDao.save(burger);
    }

    @GetMapping
    public List<Burger> getBurgers(){
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger getBurger(@PathVariable Long id){
        BurgerValidation.isIdValid(id);
        BurgerValidation.exists(burgerDao, id, true);
        return burgerDao.findById(id);
    }

    @GetMapping("/price/{price}")
    public List<Burger> getBurgerByPrice(@PathVariable double price){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> getBurgerByBreadType(@PathVariable BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }
    @GetMapping("/content/{content}")
    public List<Burger> getBurgerByContent(@PathVariable String content){
        return burgerDao.findByContent(content);
    }

    @PutMapping("/{id}")
    public Burger actualize(@PathVariable Long id){
        BurgerValidation.isIdValid(id);
        Burger burger = burgerDao.findById(id);
        return burgerDao.update(burger);
    }

    @PutMapping
    public Burger actualize(@RequestBody Burger burger){
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger delete(@PathVariable Long id){
        BurgerValidation.isIdValid(id);
        return burgerDao.remove(id);
    }
}
