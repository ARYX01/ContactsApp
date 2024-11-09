package com.turing.rubrica;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.Test;


public class JWTSecretGeneratorTest {
    
    @Test
    public void generateSecretKey() {
        SecretKey key = Jwts.SIG.HS512.key().build();
        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println("- GENERATED JWT KEY -");
        System.out.println("KEY: "+ encodedKey);
    }

}
