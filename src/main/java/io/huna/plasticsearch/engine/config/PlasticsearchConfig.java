package io.huna.plasticsearch.engine.config;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Plasticsearch config.
 */
@Configuration
public class PlasticsearchConfig {

    @Bean(name = "NoriAnalyzer")
    public Analyzer getKoreanAnalyzer() {
        return new KoreanAnalyzer();
    }
}
