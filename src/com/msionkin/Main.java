package com.msionkin;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Fraction[][] array = new Fraction[n][n + 1];
        Fraction[] x = new Fraction[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= n; ++j) {
                array[i][j] = new Fraction(BigInteger.valueOf(scanner.nextInt()), BigInteger.valueOf(1));
            }
        }
        for (int k = 0; k < n; ++k) {
            func(k, n, array);
            for (int i = k + 1; i < n; ++i) {
                Fraction m = array[i][k].divide(array[k][k]);
                for (int j = k; j <= n; ++j) {
                    array[i][j] = array[i][j].subtract(m.multiply(array[k][j]));
                }
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            x[i] = array[i][n].divide(array[i][i]);
            if (i != n - 1) {
                for (int j = i + 1; j < n; ++j)
                    x[i] = x[i].subtract((array[i][j].multiply(x[j])).divide(array[i][i]));
            }
        }
        for (int i = 0; i < n; ++i) {
            if (x[i].getB().compareTo(BigInteger.valueOf(1)) == 0)
                System.out.println("x" + (i + 1) + "=" + x[i].getA());
            else
                System.out.println("x" + (i + 1) + "=" + x[i].getA() + "/" + x[i].getB());
        }
    }

    public static void func(int k, int n, Fraction array[][]) {
        Fraction max = array[k][k].abs();
        int r = k;
        for (int i = k + 1; i < n; ++i) {
            if (array[i][k].abs().compareTo(max) == 1) {
                max = array[i][k].abs();
                r = i;
            }
        }
        if (r != k) {
            for (int j = 0; j <= n; ++j) {
                Fraction c = array[k][j];
                array[k][j] = array[r][j];
                array[r][j] = c;
            }
        }
    }
    }



