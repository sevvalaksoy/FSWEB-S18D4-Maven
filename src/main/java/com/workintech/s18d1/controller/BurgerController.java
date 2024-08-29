package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/burgers")
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

    @GetMapping("/findByPrice")
    public List<Burger> getBurgerByPrice(@RequestBody double price){
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/findByBreadType")
    public List<Burger> getBurgerByBreadType(@RequestBody BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }
    @GetMapping("/findByContent")
    public List<Burger> getBurgerByContent(@RequestBody String content){
        return burgerDao.findByContent(content);
    }

    @PutMapping("/{id}")
    public Burger actualize(@PathVariable Long id){
        BurgerValidation.isIdValid(id);
        Burger burger = burgerDao.findById(id);
        return burgerDao.update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger delete(@PathVariable Long id){
        BurgerValidation.isIdValid(id);
        return burgerDao.remove(id);
    }
}
