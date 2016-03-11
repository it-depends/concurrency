/***
 * Excerpted from "Seven Concurrency Models in Seven Weeks",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/pb7con for more book information.
***/
// package com.paulbutcher;

// import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
  static final int diners = 5;

  public static void main(String[] args) throws InterruptedException {
    Philosopher[] philosophers = new Philosopher[diners];
    Object table = new Object();
    
    for (int i = 0; i < diners; ++i)
      philosophers[i] = new Philosopher(table);
    for (int i = 0; i < diners; ++i) {
      philosophers[i].setLeft(philosophers[(i + diners - 1) % diners]);
      philosophers[i].setRight(philosophers[(i + 1) % diners]);
      philosophers[i].start();
    }
    for (int i = 0; i < diners; ++i)
      philosophers[i].join();
  }
}
