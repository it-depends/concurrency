/***
 * Excerpted from "Seven Concurrency Models in Seven Weeks",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/pb7con for more book information.
***/
// package com.paulbutcher;

import java.util.Random;

class LinkedList {

  public static void main(String[] args) throws InterruptedException {
    final HandOver handOver = new HandOver();
    final Single single = new Single();
    final Random random = new Random();

    class HandOverTestThread extends Thread {
      public void run() {
        for (int i = 0; i < 10000; ++i)
          handOver.insert(random.nextInt());
        System.out.println();
        System.out.println("Hand Done.");
        System.out.flush();
      }
    }

    class SingleTestThread extends Thread {
      public void run() {
        for (int i = 0; i < 10000; ++i)
          single.insert(random.nextInt());
        System.out.println();
        System.out.println("Single Done.");
        System.out.flush();
      }
    }

    class CountingThread extends Thread {
      public void run() {
        while (!interrupted()) {
          System.out.print("\r" + handOver.size() + " - " + single.size());
          System.out.flush();
        }
      }
    }

    Thread h1 = new HandOverTestThread();
    Thread h2 = new HandOverTestThread();
    Thread s1 = new SingleTestThread();
    Thread s2 = new SingleTestThread();
    Thread t3 = new CountingThread();

    s1.start();
    h1.start(); 
    h2.start();
    s2.start(); 
    t3.start();
    h1.join(); h2.join();
    s1.join(); s2.join();
    t3.interrupt();

    System.out.println();
    System.out.println("\r" + handOver.size() + " - " + single.size());

    if (handOver.size() != 20000)
      System.out.println("*** Wrong size!");

    if (!handOver.isSorted())
      System.out.println("*** Not sorted!");

    if (single.size() != 20000)
      System.out.println("*** Wrong size!");

    if (!single.isSorted())
      System.out.println("*** Not sorted!");
  }
}