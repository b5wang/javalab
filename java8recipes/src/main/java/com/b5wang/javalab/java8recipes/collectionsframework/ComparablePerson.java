package com.b5wang.javalab.java8recipes.collectionsframework;

import java.util.*;

class ComparablePerson implements Comparable<ComparablePerson>{

    private String name;

    private int age;

    ComparablePerson(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Rules:
     * 1. sgn(x.compareTo(y)) == -sgn(y.compareTo(x))
     * 2. If (x.compareTo(y) == 0 and y.compareTo(z) == 0, it implies x.compareTo(z)
     * 3. x.compareTo(y)==0 implies sgn(x.compareTo(z)) == sgn(y.compareTo(z))
     * */
    @Override
    public int compareTo(ComparablePerson o) {
        return age - o.age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof ComparablePerson)) {
            return false;
        }

        ComparablePerson that = (ComparablePerson) o;
        return Objects.equals(age, that.age) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString(){
        return "{name: " + name +", age: " + age + "}";
    }

    // Test
    public static void main(String[] args){
        ComparablePerson cp1 = new ComparablePerson("Tom",25);
        ComparablePerson cp2 = new ComparablePerson("Tom",25);
        ComparablePerson cp3 = new ComparablePerson("Jerry",30);

        Set<ComparablePerson> set1 = new HashSet<>();
        set1.add(cp1);
        set1.add(cp1);
        set1.add(cp3);
        System.out.println(Arrays.toString(set1.toArray()));// Sequence is based on hashCode mode array length

        Set<ComparablePerson> set2 = new TreeSet<>();// Iteration ensures order
        set2.add(cp1);
        set2.add(cp2);
        set2.add(cp3);
        System.out.println(Arrays.toString(set2.toArray()));// Sequence is based on compareTo method

        Set<ComparablePerson> set3 = new LinkedHashSet<>();
        set3.add(cp1);
        set3.add(cp2);
        set3.add(cp3);
        System.out.println(Arrays.toString(set3.toArray()));// Sequence follows adding order

        Set<ComparablePerson> set4 = new TreeSet<>((p1,p2) -> p2.getAge() - p1.getAge());
        set4.add(cp1);
        set4.add(cp2);
        set4.add(cp3);
        System.out.println(Arrays.toString(set4.toArray()));
    }
}
