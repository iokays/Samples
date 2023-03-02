package com.iokays.work.domain;

import com.google.common.base.Preconditions;
import org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JasyptService {

    @Value("${jasypt.encryptor.password}")
    private String password;

    @Value("${jasypt.encryptor.algorithm:PBEWithMD5AndDES}")
    private String algorithm;


    public void run(final String input) {
        Preconditions.checkNotNull(input);
        var param = new String[]{"input=" + input, "password=" + this.password, "algorithm=" + this.algorithm};
        JasyptPBEStringEncryptionCLI.main(param);
    }


}
