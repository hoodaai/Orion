package com.synchronization.accountexample;

/**
 */
public class Account {
   private Double baseAmount = 0.0;

   public Double getBaseAmount() {
      return baseAmount;
   }

   public void setBaseAmount(Double baseAmount) {
      this.baseAmount = baseAmount;
   }

   @Override
   public boolean equals(Object o) {
      return true;
   }

   @Override
   public int hashCode() {
      return 1;
   }
}
