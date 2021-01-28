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
import org.apache.lucene.index.Term;
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

    /**
     * Update document string.
     *
     * @param indexName  the index name
     * @param documentId the document id
     * @param body       the body
     * @return the string
     * @throws Exception the exception
     */
    public String generateDocument(String indexName, String documentId, String body) throws Exception {
        Document doc = this.createDocument(documentId, body);
        Term term = new Term("id", documentId);
        IndexWriter indexWriter = this.createIndex(indexName);
        indexWriter.updateDocument(term, doc);
        indexWriter.commit();
        indexWriter.close();

        return documentId;
    }

    private Document createDocument(String documentId, String body) {
        Map<String, Object> json = GSON.fromJson(body, Map.class);
        Document doc = new Document();
        doc.add(TypeHandler.mapField("id", documentId));
        json.entrySet().forEach(e -> doc.add(TypeHandler.mapField(e.getKey(), e.getValue())));
        return doc;
    }

    /**
     * Delete document string.
     *
     * @param indexName  the index name
     * @param documentId the document id
     * @return the string
     * @throws Exception the exception
     */
    public String deleteDocument(String indexName, String documentId) throws Exception {
        Term term = new Term("id", documentId);
        IndexWriter indexWriter = this.createIndex(indexName);
        indexWriter.deleteDocuments(term);
        indexWriter.commit();
        indexWriter.close();

        return documentId;
    }

    /**
     * Delete all document string.
     *
     * @param indexName the index name
     * @return the string
     * @throws Exception the exception
     */
    public String deleteAllDocument(String indexName) throws Exception {
        IndexWriter indexWriter = this.createIndex(indexName);
        indexWriter.deleteAll();
        indexWriter.commit();
        indexWriter.close();

        return String.format("All document(s) deleted in the %s", indexName);
    }
}
