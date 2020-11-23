package com.b5wang.javalab.java8recipes.lambdas;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class MaxDistance {

    private final static List<Integer> LIST_INT = new ArrayList<>();
    static {
        for(int i = 1; i < 100000000; i++){
            LIST_INT.add(i);
        }
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        idiomBeforeJava8();
        long spot2 = System.currentTimeMillis();
        java8SequentialStreams();
        long spot3 = System.currentTimeMillis();
        java8ParallelStreams();
        long end = System.currentTimeMillis();

        System.out.println("t1: " + (spot2 - start));
        System.out.println("t2: " + (spot3 - spot2));
        System.out.println("t3: " + (end - spot3));
    }

    private static void idiomBeforeJava8(){
        List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,9,10);
        List<Point> pointList = new ArrayList<>();
        for(Integer i : intList){
            pointList.add(new Point(i % 3, i / 3));
        }
        double maxDistance = Double.MIN_VALUE;
        for(Point p : pointList){
            double distance = p.distance(0,0);
            //System.out.println("x: " + p.getX() + ", y: " + p.getY() + ", distance: " + distance);
            maxDistance = Math.max(distance, maxDistance);
        }
        System.out.println("maxDistance: " + maxDistance);
    }

    public static void java8SequentialStreams(){
        OptionalDouble maxDistance = Arrays.asList(1,2,3,4,5,6,7,9,10)
                .stream()
                .map(i->new Point(i % 3, i / 3))
                .mapToDouble(p->p.distance(0,0))
                .max();
        System.out.println("maxDistance: " + maxDistance.getAsDouble());
    }

    public static void java8ParallelStreams(){
        OptionalDouble maxDistance = Arrays.asList(1,2,3,4,5,6,7,9,10)
                .parallelStream()
                .map(i->new Point(i % 3, i / 3))
                .mapToDouble(p->p.distance(0,0))
                .max();
        System.out.println("maxDistance: " + maxDistance.getAsDouble());
    }

}
