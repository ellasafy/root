package io.neo.structure.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Created by lunjianchang on 5/31/16.
 */
public class MD5HashFunction implements HashFunction {


    MessageDigest digest;

    public MD5HashFunction() {
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

            throw new IllegalArgumentException("This should never happen");
        }
    }

    public int hash(Object s) {
        digest.reset();
        byte[] hash = digest.digest(s.toString().getBytes());

        // HACK just take the first 4 digits and make it an integer.
        // apparently this is what other algorithms use to turn it into an int
        // value.

        // http://github.com/dustin/java-memcached-client/blob/9b2b4be73ee4a74bf6d0cf47f89c33753a5b5329/src/main/java/net/spy/memcached/HashAlgorithm.java
        int h0 = (hash[0] & 0xFF);
        int h1 = (hash[1] & 0xFF) << 8;
        int h2 = (hash[2] & 0xFF) << 16;
        int h3 = (hash[3] & 0xFF) << 24;

        int val = h0 + h1 + h2 + h3;
        return val;
    }

}