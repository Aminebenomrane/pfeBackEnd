package com.example.pfebackend.config;


import jakarta.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Encrypt implements AttributeConverter<String, String> {

    private final EncryptionUtils encryptionUtils;

    @Autowired
    public Encrypt(EncryptionUtils encryptionUtils) {
        this.encryptionUtils = encryptionUtils;
    }

    @Override
    public String convertToDatabaseColumn(String s) {
        return encryptionUtils.encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return encryptionUtils.decrypt(s);
    }


}