package com.b5wang.javalab.java8recipes.collectionsframework.equalsnhashcode;

class Money {
    protected int amount;
    protected String currency;

    Money(int amount,String currency){
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * equals() contract:
     * 1. reflexive(自反) always equals to itself
     * 2. symmetric(对称) x.equals(y), also y.equals(x)
     * 3. transitive(传递) x.equals(y), y.equals(z), there should be x.equals(z)
     * 4. consistent(一致性) no randomness allowed
     * */
    public boolean equals(Object o){
        if(o == null){
            return false;
        }

        if(this == o){
            return true;
        }

        if(!(o instanceof Money)){
            return false;
        }

        Money that = (Money)o;
        if(this.amount != that.amount){
            return false;
        }

        if(this.currency == null && that.currency == null){// (1)
            return true;
        }

        if(this.currency != null && this.currency.equals(that.currency)){
            return true;
        }
        // else this.currency == null, don't compare it again,
        // because we know that.currency must not null, it is not possible to be equal
        // otherwise (1) has been true

        return false;
    }

    public static void main(String[] args){
        Money m1 = new Money(100,"US");
        Money m2 = new Money(100,null);
        Money m3 = new Money(100,"YUAN");
        Money m4 = new Money(100,"US");
        Money m5 = new Money(1200,"US");
        Money m6 = new Money(200,null);
        Money m7 = new Money(100,null);

        System.out.println("m1 == m1: " + m1.equals(m1));
        System.out.println("m1 == m2: " + m1.equals(m2));
        System.out.println("m1 == m3: " + m1.equals(m3));
        System.out.println("m1 == m4: " + m1.equals(m4));
        System.out.println("m1 == m5: " + m1.equals(m5));
        System.out.println("m1 == m6: " + m1.equals(m6));
        System.out.println("m1 == m7: " + m1.equals(m7));
        System.out.println("m2 == m6: " + m2.equals(m6));
        System.out.println("m2 == m7: " + m2.equals(m7));

        System.out.println("hash value of m1: " + m1.hashCode());
        System.out.println("hash value of m4: " + m4.hashCode());
    }
}
