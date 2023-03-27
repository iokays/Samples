package com.iokays.work.domain;

import org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI;

public class JasyptPBEStringEncryptionCLITest {
    public static void main(String[] args) {
        var param = new String[]{"input=123456", "password=Afei@2018", "algorithm=PBEWithMD5AndDES"};
        JasyptPBEStringEncryptionCLI.main(param);
    }
}
