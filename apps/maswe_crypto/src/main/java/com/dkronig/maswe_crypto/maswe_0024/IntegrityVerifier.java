package com.dkronig.maswe_crypto.maswe_0024;

import java.nio.charset.StandardCharsets;

public final class IntegrityVerifier {
    /**
     * Uses crc32 instead of hmac as "cryptographic" checksum
     * @param data The string to be verified (banking command)
     * @return The crc32 checksum as long
     */
    public static long crc32(String data) {
        java.util.zip.CRC32 crc = new java.util.zip.CRC32();
        crc.update(data.getBytes(StandardCharsets.UTF_8));
        return crc.getValue();             
    }
}
