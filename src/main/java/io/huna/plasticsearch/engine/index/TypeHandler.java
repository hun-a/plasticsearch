package io.huna.plasticsearch.engine.index;

import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FloatPoint;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.TextField;

public class TypeHandler {

    public static Field mapField(String key, Object value) {
        if (value instanceof Integer) {
            return new IntPoint(key, Integer.parseInt(value.toString()));
        }
        if (value instanceof Long) {
            return new LongPoint(key, Long.parseLong(value.toString()));
        }
        if (value instanceof Float) {
            return new FloatPoint(key, Float.parseFloat(value.toString()));
        }
        if (value instanceof Double) {
            return new DoublePoint(key, Double.parseDouble(value.toString()));
        }
        return new Field(key, value.toString(), TextField.TYPE_STORED);
    }
}
