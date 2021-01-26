package io.huna.plasticsearch.engine.index;

import lombok.RequiredArgsConstructor;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type Indexer.
 */
@Service
@RequiredArgsConstructor
public class Indexer {

    private final static String INDEX_DIR = "indices";

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
        Directory directory = FSDirectory.open(Files.createDirectories(Paths.get(INDEX_DIR, indexName)));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        return new IndexWriter(directory, config);
    }

    // create docs

    // update docs

    // delete docs
}
