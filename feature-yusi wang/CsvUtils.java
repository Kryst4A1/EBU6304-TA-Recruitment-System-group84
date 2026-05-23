package cn.bupt.tarecruitment.util;

import java.util.ArrayList;
import java.util.List;

public final class CsvUtils {
    private CsvUtils() {}

    public static List<String> parseLine(String line) {
        List<List<String>> records = parseRecords(line == null ? "" : line);
        return records.isEmpty() ? new ArrayList<>() : records.get(0);
    }

    public static List<List<String>> parseRecords(String text) {
        List<List<String>> records = new ArrayList<>();
        if (text == null || text.isEmpty()) return records;
        List<String> currentRecord = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        boolean fieldStarted = false;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (inQuotes) {
                if (ch == '"') {
                    if (i + 1 < text.length() && text.charAt(i + 1) == '"') {
                        currentField.append('"');
                        i++;
                    } else {
                        inQuotes = false;
                    }
                } else {
                    currentField.append(ch);
                }
                fieldStarted = true;
                continue;
            }

            if (ch == '"') {
                inQuotes = true;
                fieldStarted = true;
            } else if (ch == ',') {
                currentRecord.add(currentField.toString());
                currentField.setLength(0);
                fieldStarted = false;
            } else if (ch == '\n' || ch == '\r') {
                if (ch == '\r' && i + 1 < text.length() && text.charAt(i + 1) == '\n') i++;
                currentRecord.add(currentField.toString());
                currentField.setLength(0);
                if (!isBlankRecord(currentRecord)) records.add(currentRecord);
                currentRecord = new ArrayList<>();
                fieldStarted = false;
            } else {
                currentField.append(ch);
                fieldStarted = true;
            }
        }
        if (fieldStarted || currentField.length() > 0 || !currentRecord.isEmpty()) {
            currentRecord.add(currentField.toString());
            if (!isBlankRecord(currentRecord)) records.add(currentRecord);
        }
        return records;
    }

    private static boolean isBlankRecord(List<String> record) {
        if (record == null || record.isEmpty()) return true;
        for (String field : record) {
            if (field != null && !field.trim().isEmpty()) return false;
        }
        return true;
    }

    public static String toLine(List<String> fields) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < fields.size(); i++) {
            if (i > 0) out.append(',');
            out.append(escape(fields.get(i)));
        }
        return out.toString();
    }

    public static String escape(String field) {
        if (field == null) return "";
        boolean quote = field.indexOf(',') >= 0 || field.indexOf('"') >= 0 || field.indexOf('\n') >= 0 || field.indexOf('\r') >= 0;
        String escaped = field.replace("\"", "\"\"");
        return quote ? "\"" + escaped + "\"" : escaped;
    }
}
