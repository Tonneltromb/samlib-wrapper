package com.ymatin.samlib.service;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class ParameterizedTestForISMatchToRegex {

    private static final String REGEX = "^\\s*(Глава\\s([1-9]?){2}$)|^\\s*(Пролог)|\\s*(Эпилог)";
    private static Object[][] DATALIST = {
            {"Глава 11", true},
            {" Глава 11", true},
            {"  Глава 11", true},
            {"  .Глава 11", false},
            {"  глава 11", true},
            {"  Глава 11.", false},
            {"  Глава 0", false},
            {"  Глава", false},
            {"  Глава 111", false},
            {"  Глава 99", true},
            {"  Глава 01", false},
            {"  Глава 1 Глава 1", false},
            {"Пролог", true},
            {" Пролог", true},
            {"  Пролог", true},
            {"  пролог", true},
            {"  Пролог ", false},
            {"  Пролог Пролог", false},
            {"Эпилог", true},
            {" Эпилог", true},
            {"  Эпилог", true},
            {"  Эпилог ", false},
            {"  Эпилог.", false},
            {"  Эпилог Эпилог", false},
            {"  Эпилог Пролог Глава", false},
    };

    private String text;
    private boolean result;

    public ParameterizedTestForISMatchToRegex(String text, boolean result) {
        this.text = text;
        this.result = result;
    }

    @Parameterized.Parameters(name = "{index}: comparing result of text \"{0}\" to regex is: {1}")
    public static Collection<Object[]> data(){ return Arrays.asList(DATALIST); }

    @Test
    public void isMatchToRegex_test(){
        assertThat(new FillDatabaseHelper().isMatchToRegex(REGEX, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE, text), CoreMatchers.is(result));
    }
}
