package com.ymatin.samlib.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FillDatabaseHelperTest {

    private FillDatabaseHelper dbHelper = new FillDatabaseHelper();

    @Test
    public void extractSamlibIdFromUrl() {
        String samlibId = "a/author";

        String url = "http://samlib.ru/" + samlibId + "/indexvote.shtml";
        String result = dbHelper.extractSamlibIdFromUrl(url);
        assertEquals(samlibId, result);

        url = "samlib.ru/" + samlibId + "/indexvote.shtml";
        result = dbHelper.extractSamlibIdFromUrl(url);
        assertEquals(samlibId, result);

        url = samlibId + "/indexvote.shtml";
        result = dbHelper.extractSamlibIdFromUrl(url);
        assertEquals(samlibId, result);

        url = "samlib.ru/" + samlibId;
        result = dbHelper.extractSamlibIdFromUrl(url);
        assertEquals(samlibId, result);

        url = samlibId;
        result = dbHelper.extractSamlibIdFromUrl(url);
        assertEquals(samlibId, result);

        url = "http://samlib.ru/" + samlibId + "/";
        result = dbHelper.extractSamlibIdFromUrl(url);
        assertEquals(samlibId, result);
    }
}