package de.diedavids.cuba.cedicpl.converter

import com.xlson.groovycsv.CsvParser
import de.diedavids.cuba.dataimport.converter.CsvImportDataConverter

class CsvColonSeparatorImportDataConverter extends CsvImportDataConverter {

    @Override
    protected Iterator parse(String content) {
        def csvConfiguration = [separator: ";"]
        new CsvParser().parse(csvConfiguration,content)
    }

}
