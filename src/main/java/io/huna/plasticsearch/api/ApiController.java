package io.huna.plasticsearch.api;

import io.huna.plasticsearch.engine.index.Indexer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final Indexer indexer;

    @PostMapping(value = "/index")
    public String createIndex(@RequestParam(value = "name") String indexName) throws Exception {
        indexer.createIndex(indexName);
        return "GOOD";
    }
}
