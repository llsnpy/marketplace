package by.mironenko.marketplace.controller;

import by.mironenko.marketplace.service.coding.PasswordCoder;

public class Main {
    public static void main(String[] args) {

        PasswordCoder coder = new PasswordCoder();

        System.out.println(coder.encrypt("holder"));
    }
}
