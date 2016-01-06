package com.synchronization.factorialexample;


/**
 * Service to call Factorial
 */
public class FactorialService {


   public static void main(String a[])
      throws Exception {

      Factorial f1 = new Factorial();
      Thread threadOne = new Thread(f1);
      threadOne.setName("ThreadOne");
      threadOne.start();

      Factorial f2 = new Factorial();
      Thread threadTwo = new Thread(f2);
      threadTwo.setName("ThreadTwo");
      threadTwo.start();

      Factorial f3 = new Factorial();
      Thread threadThree = new Thread(f3);
      threadThree.setName("ThreadThree");
      threadThree.start();

   }
}
