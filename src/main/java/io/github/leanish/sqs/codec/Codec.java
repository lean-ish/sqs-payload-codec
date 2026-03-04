/*
 * Copyright (c) 2026 Leandro Aguiar
 * Licensed under the MIT License.
 * See LICENSE file in the project root for full license information.
 */
package io.github.leanish.sqs.codec;

import io.github.leanish.sqs.codec.algorithms.CompressionAlgorithm;
import io.github.leanish.sqs.codec.algorithms.compression.Compressor;
import io.github.leanish.sqs.codec.algorithms.encoding.Base64Encoder;

class Codec {

    private static final Base64Encoder BASE64_ENCODER = new Base64Encoder();

    private final Compressor compressor;
    private final boolean compressionEnabled;

    Codec(CompressionAlgorithm compressionAlgorithm) {
        this.compressor = compressionAlgorithm.implementation();
        this.compressionEnabled = compressionAlgorithm != CompressionAlgorithm.NONE;
    }

    public byte[] encode(byte[] payload) {
        if (!compressionEnabled) {
            return payload;
        }

        byte[] compressed = compressor.compress(payload);
        return BASE64_ENCODER.encode(compressed);
    }

    public byte[] decode(byte[] payload) {
        if (!compressionEnabled) {
            return payload;
        }

        byte[] decoded = BASE64_ENCODER.decode(payload);
        return compressor.decompress(decoded);
    }
}
