package com.msionkin;

import java.math.BigInteger;

public class Fraction implements Cloneable {
    private BigInteger a;
    private BigInteger b;

    Fraction(BigInteger a, BigInteger b) {
        this.a = a;
        this.b = b;
    }

    public BigInteger getA() {
        return a;
    }

    public BigInteger getB() {
        return b;
    }

    public void normalize() {
        BigInteger c = a.abs();
        BigInteger d = b.abs();
        while (d.compareTo(BigInteger.valueOf(0)) != 0) {
            BigInteger tmp = c.mod(d);
            c = d;
            d = tmp;
        }
        a = a.divide(c);
        b = b.divide(c);
        if (((a.signum() == -1) && (b.signum() == -1)) || ((a.signum() == 1) && (b.signum() == -1))) {
            a = a.negate();
            b = b.negate();
        }
    }

    public Fraction divide(Fraction f) {
        Fraction fr = new Fraction(a.multiply(f.b), b.multiply(f.a));
        fr.normalize();
        return fr;
    }

    public Fraction multiply(Fraction f) {
        Fraction fr = new Fraction(a.multiply(f.a), b.multiply(f.b));
        fr.normalize();
        return fr;
    }

    public Fraction subtract(Fraction f) {
        Fraction fr = new Fraction((a.multiply(f.b)).subtract(f.a.multiply(b)), b.multiply(f.b));
        fr.normalize();
        return fr;
    }

    public Fraction abs() {
        Fraction fr = new Fraction(a.abs(), b.abs());
        return fr;
    }

    public int compareTo(Fraction f) {
        if (b.compareTo(f.b) == 0) {
            if (a.compareTo(f.a) == 1)
                return 1;
            if (a.compareTo(f.a) == -1)
                return -1;
            return 0;
        } else {
            if ((a.multiply(f.b)).compareTo(f.a.multiply(b)) == 1)
                return 1;
            if ((a.multiply(f.b)).compareTo(f.a.multiply(b)) == -1)
                return -1;
            return 0;
        }
    }
}