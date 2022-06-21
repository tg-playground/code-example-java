package com.taogen.demo.springdataes;

import com.taogen.demo.springdataes.entity.Bank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Taogen
 */
public abstract class AbstractBaseTest implements BaseCrudTest {
    protected Bank getRandomEntityWithoutId() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        Bank bank = new Bank();
        bank.setAccountNumber(ThreadLocalRandom.current().nextLong(10000000000L, 100000000000L));
        bank.setAddress("address" + dateFormat.format(date));
        bank.setAge(ThreadLocalRandom.current().nextInt(0, 100 + 1));
        bank.setBalance(ThreadLocalRandom.current().nextLong(0, 100000000));
        bank.setCity("city" + dateFormat.format(date));
        bank.setEmail("email" + dateFormat.format(date));
        bank.setEmployer("employer" + dateFormat.format(date));
        bank.setFirstName("firstName" + dateFormat.format(date));
        bank.setGender("male");
        bank.setLastName("Jones");
        bank.setState("state" + dateFormat.format(date));
        return bank;
    }
}
