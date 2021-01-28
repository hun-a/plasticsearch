package io.huna.plasticsearch.engine.search;

import io.huna.plasticsearch.engine.dto.Hit;
import io.huna.plasticsearch.engine.dto.SearchResultDto;
import io.huna.plasticsearch.engine.util.DirectoryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Searcher {

    @Qualifier("NoriAnalyzer")
    private final Analyzer analyzer;

    public SearchResultDto search(String indexName, String q, String field) throws Exception {
        Directory directory = DirectoryUtil.getDirectory(indexName);
        DirectoryReader reader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(reader);

        QueryParser queryParser = new QueryParser(field, analyzer);
        Query query = queryParser.parse(q);
        log.info("{}", query.toString());
        TopDocs topDocs = indexSearcher.search(query, 10);

        SearchResultDto resultDto = SearchResultDto.builder()
                .total(topDocs.totalHits.value)
                .build();
        for (ScoreDoc sDoc: topDocs.scoreDocs) {
            Document doc = indexSearcher.doc(sDoc.doc);
            Map<String, Object> map = new HashMap<>();
            Hit hit = Hit.builder()
                    .index(indexName)
                    .score(sDoc.score)
                    .source(map)
                    .build();
            for (IndexableField f: doc.getFields()) {
                map.put(f.name(), doc.get(f.name()));
                if ("id".equals(f.name())) {
                    hit.setId(doc.get(f.name()));
                }
            }
            resultDto.getHits().add(hit);
            resultDto.setMaxScore(resultDto.getHits().get(0).getScore());
        }

        reader.close();
        directory.close();

        return resultDto;
    }
}
