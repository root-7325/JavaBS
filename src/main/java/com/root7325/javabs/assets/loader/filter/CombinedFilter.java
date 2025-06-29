package com.root7325.javabs.assets.loader.filter;

import com.opencsv.bean.CsvToBeanFilter;

/**
 * @author root7325 on 28.06.2025
 */
public class CombinedFilter implements CsvToBeanFilter {
    private final CsvToBeanFilter[] filters;

    public CombinedFilter(CsvToBeanFilter... filters) {
        this.filters = filters;
    }

    @Override
    public boolean allowLine(String[] strings) {
        for (CsvToBeanFilter filter : filters) {
            if (!filter.allowLine(strings)) {
                return false;
            }
        }
        return true;
    }
}
