package io.huna.plasticsearch.api;

import io.huna.plasticsearch.engine.index.Indexer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final Indexer indexer;

    @PostMapping(value = "/{name}")
    public String createIndex(@PathVariable(value = "name") String indexName) throws Exception {
        indexer.createIndex(indexName).close();
        return String.format("%s created", indexName);
    }

    @PostMapping(value = "/{name}/{id}")
    public String createDocument(@PathVariable(value = "name") String indexName, @PathVariable("id") String documentId, @RequestBody String body) throws Exception {
        return indexer.createDocument(indexName, documentId, body);
    }
}
