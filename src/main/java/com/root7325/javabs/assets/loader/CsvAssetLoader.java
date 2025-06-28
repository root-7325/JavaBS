package com.root7325.javabs.assets.loader;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.root7325.javabs.assets.loader.filter.CombinedFilter;
import com.root7325.javabs.assets.loader.filter.DataTypeFilter;
import com.root7325.javabs.assets.loader.filter.EmptyLineFilter;
import com.root7325.javabs.assets.model.Asset;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author root7325 on 28.06.2025
 */
@Slf4j
@AllArgsConstructor
public class CsvAssetLoader<T extends Asset> implements AssetLoader<T> {
    private final Class<T> assetClass;

    @Override
    public List<T> load(String filePath) {
        try (Reader reader = new FileReader(filePath)) {
            List<T> assetList = new CsvToBeanBuilder<T>(reader)
                    .withType(assetClass)
                    .withFilter(new CombinedFilter(
                            new DataTypeFilter(),
                            new EmptyLineFilter()
                    ))
                    .withSeparator(',')
                    .withQuoteChar('"')
                    .build()
                    .parse();

            log.info("Loaded {} {} assets from {}", assetList.size(), assetClass.getSimpleName(), filePath);
            return assetList;
        } catch (IOException ex) {
            log.error("Failed to load asset from: {}", filePath);
            throw new RuntimeException(ex);
        }
    }
}
