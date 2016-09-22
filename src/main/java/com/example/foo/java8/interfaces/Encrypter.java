package com.example.foo.java8.interfaces;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.function.Function;
import java.util.function.IntFunction;

public interface Encrypter {

    Function<Byte, Byte> function();

    default byte[] encode(byte[] bytes) {
        final byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; ++i) {
            result[i] = function().apply(bytes[i]);
        }
        return result;
    }

    default byte[] encode(String str, Charset charset) {
		return encode(str.getBytes(charset));
	}

	default byte[] encode(char[] chars, Charset charset) {
		return encode(String.valueOf(chars), charset);
	}

	default byte[] encode(Reader reader, Charset charset) throws IOException {
		return encode(IOUtils.toByteArray(reader, charset));
	}

	default byte[] encode(InputStream is) throws IOException {
		return encode(IOUtils.toByteArray(is));
	}
}
