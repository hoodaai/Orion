package com.recommender.algo;


import java.util.*;

/**
 * Created by varun on 02/07/15.
 */
public class CollaborativeFiltering {

    private void findEuclideanDistanceScore(HashMap<String, Map<String, Integer>> database) {
        for (Map.Entry<String, Map<String, Integer>> productSet: database.entrySet()) {
            Map<String, Integer> masterUserRatingMap = productSet.getValue();
            System.out.println("master = " + productSet.getKey() + ": "+ productSet.getValue());
            // make another copy of map excluding p1 product
            Map<String, Map<String, Integer>> mapCopy = new HashMap(database);
            mapCopy.remove(productSet.getKey());

            for (Map.Entry<String, Map<String, Integer>> productCopySet: mapCopy.entrySet()) {
                Map<String, Integer> userRatingMap = productCopySet.getValue();
                Map<String, Integer> commonUsers = findCommonUsers(masterUserRatingMap, userRatingMap);

                List<Integer> cordArray = new ArrayList<Integer>();
                for(Map.Entry<String, Integer> entry: commonUsers.entrySet()){
                    //System.out.println("common users = " + entry.getKey() + ": "+ entry.getValue());
                    cordArray.add(masterUserRatingMap.get(entry.getKey()));
                    cordArray.add(userRatingMap.get(entry.getKey()));
                }

                for(Integer i: cordArray){
                    System.out.println("cordArray = " + i);
                }
                double  xDiff = cordArray.get(0)-cordArray.get(1);
                double  xSqr  = Math.pow(xDiff, 2);

                double yDiff = cordArray.get(2)-cordArray.get(3);
                double ySqr = Math.pow(yDiff, 2);

                double output   = Math.sqrt(xSqr + ySqr);

                System.out.println("Distance = " + output);
                //save distance into hashmap


            }




        }

        // take first product from map. and iterate following process for rest of the products in map.

        // get two common products from the secondry map
        // find Euclidean distance
        //commonuser1 = findCommonUsers(product, nextPRoduct)
        // x1 = p1.commonuser1.val
        // x2 = p2.commonuser1.cal

        // y1 = p1.commonuser2.val
        // y2 = p2.commonuser2.val


        // get euclidean distance
        // p1-p2, p1-p3, p1-p4.....p1-pn now rank them. use sort()
        // intermediate result can be stored in arraylist
        // create final data structure which contains product id and list of recommended product ids rank-wise.
        // to recommend similar products just show these product.

    }

    private Map<String, Integer> findCommonUsers(Map<String, Integer> userRatings, Map<String, Integer> nextUserRatings ){
        Map<String, Integer> match = new HashMap<String, Integer>();
        for(Map.Entry<String, Integer> entry: userRatings.entrySet()){
            if(match.get(entry.getKey())!=null){
                Integer rating = match.get(entry.getKey());
                match.put(entry.getKey(), rating+1);
            } else {
                match.put(entry.getKey(), 1);
            }

        }

        for(Map.Entry<String, Integer> entry: nextUserRatings.entrySet()){
            if(match.get(entry.getKey())!=null){
                Integer rating = match.get(entry.getKey());
                match.put(entry.getKey(), rating+1);
            } else {
                match.put(entry.getKey(), 1);
            }

        }

        Iterator it = match.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if((Integer)pair.getValue()!=2){
                it.remove();
            }

        }

        for(Map.Entry<String, Integer> entry: match.entrySet()){
            System.out.println(entry.getKey() +" : " + entry.getValue());

        }

        return match;


    }

    public static void main(String a[]){
        HashMap<String, Map<String, Integer>> database = new HashMap<String, Map<String, Integer>>();
        Map<String, Integer> ratingMap = new HashMap<String, Integer>();
        ratingMap.put("uesr1", 2);
        ratingMap.put("uesr2", 3);
        ratingMap.put("uesr3", 2);
        database.put("OnePlusOne", ratingMap);

        Map<String, Integer> ratingMap2 = new HashMap<String, Integer>();
        ratingMap2.put("uesr1", 1);
        ratingMap2.put("uesr2", 2);
        ratingMap2.put("uesr4", 2);
        database.put("MotoG", ratingMap2);


        new CollaborativeFiltering().findEuclideanDistanceScore(database);
    }

}
