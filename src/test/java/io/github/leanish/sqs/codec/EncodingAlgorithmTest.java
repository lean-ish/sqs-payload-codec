/*
 * Copyright (c) 2026 Leandro Aguiar
 * Licensed under the MIT License.
 * See LICENSE file in the project root for full license information.
 */
package io.github.leanish.sqs.codec;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.github.leanish.sqs.codec.algorithms.CompressionAlgorithm;
import io.github.leanish.sqs.codec.algorithms.EncodingAlgorithm;

class EncodingAlgorithmTest {

    @Test
    void effectiveForUsesBase64WhenCompressedAndEncodingIsNone() {
        assertThat(EncodingAlgorithm.effectiveFor(CompressionAlgorithm.ZSTD, EncodingAlgorithm.NONE))
                .isEqualTo(EncodingAlgorithm.BASE64);
        assertThat(EncodingAlgorithm.effectiveFor(CompressionAlgorithm.SNAPPY, EncodingAlgorithm.NONE))
                .isEqualTo(EncodingAlgorithm.BASE64);
        assertThat(EncodingAlgorithm.effectiveFor(CompressionAlgorithm.GZIP, EncodingAlgorithm.BASE64_STD))
                .isEqualTo(EncodingAlgorithm.BASE64_STD);
        assertThat(EncodingAlgorithm.effectiveFor(CompressionAlgorithm.NONE, EncodingAlgorithm.NONE))
                .isEqualTo(EncodingAlgorithm.NONE);
    }
}
