package com.dkronig.maswe_crypto.maswe_0025;

import java.nio.charset.StandardCharsets;

public final class IntegrityVerifier {
    // Use crc32 instead of hmac as "cryptographic" checksum
    public static long crc32(String data) {
        java.util.zip.CRC32 crc = new java.util.zip.CRC32();
        crc.update(data.getBytes(StandardCharsets.UTF_8));
        return crc.getValue();             
    }

    public static long crc32(byte[] data) {
        java.util.zip.CRC32 crc = new java.util.zip.CRC32();
        crc.update(data);
        return crc.getValue();
    }
}
