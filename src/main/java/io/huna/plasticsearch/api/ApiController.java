package io.huna.plasticsearch.api;

import io.huna.plasticsearch.engine.index.Indexer;
import io.huna.plasticsearch.engine.search.Searcher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final Indexer indexer;

    private final Searcher searcher;

    @PostMapping(value = "/{name}")
    public String createIndex(@PathVariable(value = "name") String indexName) throws Exception {
        indexer.createIndex(indexName).close();
        return String.format("%s created", indexName);
    }

    @PostMapping(value = "/{name}/{id}")
    public String createDocument(@PathVariable(value = "name") String indexName, @PathVariable("id") String documentId, @RequestBody String body) throws Exception {
        return indexer.createDocument(indexName, documentId, body);
    }

    @GetMapping(value = "/_search")
    public String search(@RequestParam(value = "q") String q, @RequestParam(value = "field") String field) {
        return searcher.search(q, field);
    }
}
