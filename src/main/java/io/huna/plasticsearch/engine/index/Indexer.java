package io.huna.plasticsearch.engine.index;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.huna.plasticsearch.engine.util.DirectoryUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * The type Indexer.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class Indexer {

    private final static Gson GSON = new GsonBuilder().create();

    @Qualifier("NoriAnalyzer")
    private final Analyzer analyzer;

    /**
     * Create index index writer.
     *
     * @param indexName the index name
     * @return the index writer
     * @throws Exception the exception
     */
    public IndexWriter createIndex(String indexName) throws Exception {
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        return new IndexWriter(DirectoryUtil.getDirectory(indexName), config);
    }

    // create docs
    public String createDocument(String indexName, String id, String body) throws Exception {
        Map<String, Object> json = GSON.fromJson(body, Map.class);
        Document doc = new Document();
        doc.add(TypeHandler.mapField("id", id));
        json.entrySet().forEach(e -> doc.add(TypeHandler.mapField(e.getKey(), e.getValue())));

        IndexWriter indexWriter = this.createIndex(indexName);
        indexWriter.addDocument(doc);
        indexWriter.close();

        return id;
    }

    // update docs

    // delete docs
}
