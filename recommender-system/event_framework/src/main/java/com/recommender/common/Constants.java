package com.recommender.common;

/**
 * 
 */
public class Constants {
    public enum Events {
        BOUGHT  ("Bought"),
        BROWSED("Browsed");

        private String val;

        Events(String str) {
            this.val = str;
        }

        public String getVal() {
            return val;
        }
    }
}
