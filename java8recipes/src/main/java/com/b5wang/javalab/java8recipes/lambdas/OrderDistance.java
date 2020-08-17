package com.b5wang.javalab.java8recipes.lambdas;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class OrderDistance {

    public static void main(String[] args){
        // anonymous inner class
        Comparator<Point> byX = new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2) {
                return Double.compare(p1.getX(),p2.getX());
            }
        };

        // lambda expression
        Comparator<Point> byXLambda = (p1,p2) -> Double.compare(p1.getX(),p2.getX());
        Comparator<Point> byYLambda = (p1,p2) -> Double.compare(p1.getY(),p2.getY());

        //
        Function<Point,Double> keyExtractor = p -> p.getX();
        Comparator<Double> keyComparer = (d1,d2) -> Double.compare(d1,d2);

        Comparator<Point> compareByX1 = (p1,p2) -> keyComparer.compare(keyExtractor.apply(p1),keyExtractor.apply(p2));

        Comparator<Point> compareByX2 = (p1,p2) -> keyExtractor.apply(p1).compareTo(keyExtractor.apply(p2));

        Comparator<Point> compareByX3 = Comparator.comparing(p -> p.getX());


        List<Integer> intList = Arrays.asList(7,9,10);

        intList.stream()
                .map(i -> new Point(i % 3 , i / 3))
                .sorted(Comparator.comparing(p -> p.distanceSq(0,0)))
                .forEach(p -> System.out.printf("%f, %f\n", p.getX(), p.getY()));

//        intList.stream()
//                .map(i -> new Point(i % 3 , i / 3))
//                .forEach(p -> System.out.printf("%f, %f\n", p.getY(), p.getX()));

    }

}
