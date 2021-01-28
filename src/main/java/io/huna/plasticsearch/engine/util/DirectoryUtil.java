package io.huna.plasticsearch.engine.util;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The type Directory util.
 */
public class DirectoryUtil {

    private final static String INDEX_DIR = "indices";

    /**
     * Gets directory.
     *
     * @param indexName the index name
     * @return the directory
     * @throws Exception the exception
     */
    public static Directory getDirectory(String indexName) throws Exception {
        return FSDirectory.open(Files.createDirectories(Paths.get(INDEX_DIR, indexName)));
    }
}
