package com.b5wang.javalab.java8recipes.collectionsframework.equalsnhashcode;

import java.util.Objects;

class WrongVoucher extends Money{

    protected String store;

    WrongVoucher(int amount, String currency) {
        super(amount, currency);
    }

    WrongVoucher(int amount, String currency, String store) {
        super(amount, currency);
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }

        if (this == o) {
            return true;
        }

        if(!(o instanceof WrongVoucher)){
            return false;
        }
        WrongVoucher that = (WrongVoucher)o;

        boolean amountEquals = this.amount == that.amount;
        boolean currencyEquals = (this.currency == null && that.currency == null) ||
                (this.currency != null && this.currency.equals(that.currency));
        boolean storeEquals = (this.store == null && that.store == null) ||
                (this.store != null && this.store.equals(that.store));

        return amountEquals && currencyEquals && storeEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(store);
    }

    public static void main(String[] args){
        // instanceof
        System.out.println("null instanceof xxx: " + (null instanceof WrongVoucher));
        WrongVoucher wv = new WrongVoucher(100,"USD");
        System.out.println("null instanceof xxx: " + (wv instanceof Money));

        // wrong equals method implementation
        // inheritance breaks / violates the symmetric. In essence, Voucher and Money shouldn't have inheritance relationship.
        WrongVoucher voucher = new WrongVoucher(99,"USD","Amazon");
        Money money = new Money(99,"USD");
        System.out.println("money.equals(voucher): " + money.equals(voucher));
        System.out.println("voucher.equals(money): " + voucher.equals(money));
    }
}
