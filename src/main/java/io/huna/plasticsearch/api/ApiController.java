package io.huna.plasticsearch.api;

import io.huna.plasticsearch.engine.dto.SearchResultDto;
import io.huna.plasticsearch.engine.index.Indexer;
import io.huna.plasticsearch.engine.search.Searcher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final Indexer indexer;

    private final Searcher searcher;

    @PutMapping(value = "/{name}")
    public String createIndex(@PathVariable(value = "name") String indexName) throws Exception {
        indexer.createIndex(indexName).close();
        return String.format("%s index created", indexName);
    }

    @PostMapping(value = "/{name}/{id}")
    public String createDocument(@PathVariable(value = "name") String indexName, @PathVariable("id") String documentId, @RequestBody String body) throws Exception {
        return indexer.generateDocument(indexName, documentId, body);
    }

    @GetMapping(value = "/{name}/_search")
    public SearchResultDto search(@PathVariable(value = "name") String indexName, @RequestParam(value = "q") String q, @RequestParam(value = "field") String field) throws Exception {
        return searcher.search(indexName, q, field);
    }

    @GetMapping(value = "/{name}/_search/all")
    public SearchResultDto searchAll(@PathVariable(value = "name") String indexName) throws Exception {
        return searcher.searchAll(indexName);
    }

    @PostMapping(value = "/{name}/_update/{id}")
    public String updateDocument(@PathVariable(value = "name") String indexName, @PathVariable(value = "id") String documentId, @RequestBody String body) throws Exception {
        return indexer.generateDocument(indexName, documentId, body);
    }

    @DeleteMapping(value = "/{name}/_doc/{id}")
    public String deleteDucment(@PathVariable(name = "name") String indexName, @PathVariable(name = "id") String documentId) throws Exception {
        return indexer.deleteDocument(indexName, documentId);
    }

    @DeleteMapping(value = "/{name}/_doc/all")
    public String deleteAllDocuments(@PathVariable(name = "name") String indexName) throws Exception {
        return indexer.deleteAllDocument(indexName);
    }
}
