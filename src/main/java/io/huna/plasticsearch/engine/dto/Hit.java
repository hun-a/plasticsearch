package io.huna.plasticsearch.engine.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * The type Hit.
 */
@Getter @Setter
public class Hit {

    private final String index;

    private String id;

    private final Float score;

    private final Map<String, Object> source;

    /**
     * Instantiates a new Hit.
     *
     * @param index  the index
     * @param score  the score
     * @param source the source
     */
    @Builder
    public Hit(String index, float score, Map<String, Object> source) {
        this.index = index;
        this.score = score;
        this.source = source;
    }
}
