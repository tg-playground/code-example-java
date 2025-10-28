package com.taogen.springiocbyxml;

/**
 *
 * @author taogen
 */
public class Test1 {
    public static void main(String[] args) {
        Animal myAnimal = new Dog(); // myAnimal is a Dog object

        if (myAnimal instanceof Dog) {
            System.out.println("myAnimal is an instance of Dog."); // This will print
        }

        if (myAnimal instanceof Animal) {
            System.out.println("myAnimal is an instance of Animal."); // This will print
        }
    }

    static class Animal {
    }

    static class Dog extends Animal {
    }

}
