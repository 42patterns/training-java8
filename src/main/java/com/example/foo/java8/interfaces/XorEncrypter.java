package com.example.foo.java8.interfaces;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.function.Function;

public class XorEncrypter implements Encrypter {

    @Override
    public Function<Byte, Byte> function() {
        return (b) -> (byte) (b ^ 0xCC);
    }

}

