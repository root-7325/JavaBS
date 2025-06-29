package com.root7325.javabs.assets.loader.filter;

import com.opencsv.bean.CsvToBeanFilter;

import java.util.Arrays;

/**
 * @author root7325 on 27.12.2025
 */
public class DataTypeFilter implements CsvToBeanFilter {
    private static final String[] DATA_TYPES = {"string", "int", "boolean"};

    @Override
    public boolean allowLine(String[] line) {
        return Arrays.stream(DATA_TYPES).noneMatch(s -> s.equals(line[0].toLowerCase()));
    }
}
