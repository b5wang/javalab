package com.b5wang.javalab.java8recipes.collectionsframework.equalsnhashcode;

class Voucher {

    private Money price;

    private String store;

    Voucher(int amount, String currency, String store){
        this.price = new Money(amount, currency);
        this.store = store;
    }

    public boolean equals(Object o){
        if(o == null){
            return false;
        }

        if(this == o){
            return true;
        }

        if(!(o instanceof Voucher)){
            return false;
        }
        Voucher that = (Voucher)o;
        boolean priceEquals = (this.price == null && that.price == null) ||
                (this.price != null && this.price.equals(that.price));
        boolean storeEquals = (this.store == null && that.store == null) ||
                (this.store != null && this.store.equals(that.store));

        return priceEquals && storeEquals;
    }

    public static void main(String[] args){
        Voucher v1 = new Voucher(99, "USD","Taobao");
        Voucher v2 = new Voucher(99, "USD","Taobao");
        Voucher v3 = new Voucher(99, "USD","Amazon");
        Voucher v4 = new Voucher(109, "USD","Taobao");
        System.out.println("v1.equals(v2): " + v1.equals(v2));
        System.out.println("v1.equals(v3): " + v1.equals(v3));
        System.out.println("v1.equals(v4): " + v1.equals(v4));
    }

}
