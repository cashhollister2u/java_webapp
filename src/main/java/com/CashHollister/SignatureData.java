package com.CashHollister;

public class SignatureData {
    private String name;
    private int signatureIndex;

    // Constructor
    public SignatureData(String name, int signatureIndex) {
        this.name = name;
        this.signatureIndex = signatureIndex;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }
}