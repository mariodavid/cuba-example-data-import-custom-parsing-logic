package de.diedavids.cuba.cedicpl.converter

import com.haulmont.cuba.core.entity.FileDescriptor
import de.diedavids.cuba.dataimport.converter.*

class CustomDataConverterFactory extends DataConverterFactory {

    ImportDataConverter createTableDataConverter(FileDescriptor fileDescriptor) {
        switch (fileDescriptor.extension) {
            case 'xlsx': return new ExcelImportDataConverter()
            case 'csv': return new CsvColonSeparatorImportDataConverter()
            case 'json': return new JsonImportDataConverter()
            case 'xml': return new XmlImportDataConverter()
            default: throw new FileNotSupportedException()
        }
    }
}
