package com.root7325.javabs.assets.loader.filter;

import com.opencsv.bean.CsvToBeanFilter;
import lombok.AllArgsConstructor;

/**
 * @author root7325 on 27.12.2025
 */
@AllArgsConstructor
public class EmptyLineFilter implements CsvToBeanFilter {
    @Override
    public boolean allowLine(String[] line) {
        return !line[0].isEmpty();
    }
}
