package io.huna.plasticsearch.engine.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Search result dto.
 */
@Getter @Setter
public class SearchResultDto {

    private final long total;

    private double maxScore;

    private final List<Hit> hits = new ArrayList<>();

    /**
     * Instantiates a new Search result dto.
     *
     * @param total the total
     */
    @Builder
    public SearchResultDto(long total) {
        this.total = total;
    }
}
