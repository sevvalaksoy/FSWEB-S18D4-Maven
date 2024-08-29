package com.workintech.s18d1.util;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.dao.BurgerDaoImpl;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;

public class BurgerValidation {
    public static void isIdValid(Long id) {
        if(id == null || id < 0 ){
            throw new BurgerException("Id is not valid", HttpStatus.BAD_REQUEST);
        }
    }
    public static void exists(BurgerDao burgerDao, long id, boolean existence){
        if(existence){
            if(burgerDao.findById(id)==null){
                throw new BurgerException("Record does not exists", HttpStatus.NOT_FOUND);
            }
        } else {
            if(burgerDao.findById(id)!=null){
                throw new BurgerException("Record already exists", HttpStatus.BAD_REQUEST);
            }
        }
    }
    public static void exists(BurgerDao burgerDao, Burger burger, boolean existence){
        if(existence){
            if(!burgerDao.findAll().contains(burger)){
                throw new BurgerException("Record does not exists", HttpStatus.NOT_FOUND);
            }
        } else {
            if(burgerDao.findAll().contains(burger)){
                throw new BurgerException("Record already exists", HttpStatus.BAD_REQUEST);
            }
        }
    }

    public static void exists(EntityManager entityManager, long id, boolean existence){
        if(existence){
            if(entityManager.find(Burger.class,id)==null){
                throw new BurgerException("Record does not exists", HttpStatus.NOT_FOUND);
            }
        } else {
            if(entityManager.find(Burger.class,id)!=null){
                throw new BurgerException("Record does not exists", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
