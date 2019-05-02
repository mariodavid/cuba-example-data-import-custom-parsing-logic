# cuba-example-data-import-custom-parsing-logic

This example shows how configure the data-import application component (https://github.com/mariodavid/cuba-component-data-import)
so that custom parsing logic can be applied.

## Custom Parsing Logic

In case any file parsing does not match your import case and it is not possible to directly configure it through the import UI,
it is still possible to override the business logic, that is reponsible for executing the parsing functionality.

The general way of doing it is to extend the `DataConverterFactory` and register the extended class as the spring bean under the name `ddcdi_DataConverterFactory`.

Since most of the classes in data-import are groovy classes, your application has to enable groovy support for the CUBA application.
Also the extended classes need to be written in Groovy.

### spring.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context">

    <!-- Annotation-based beans -->
    <context:component-scan base-package="de.diedavids.cuba.cedicpl"/>


    <bean name="ddcdi_DataConverterFactory" class="de.diedavids.cuba.cedicpl.converter.CustomDataConverterFactory" />

</beans>
```



### web-spring.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:gui="http://schemas.haulmont.com/cuba/spring/cuba-gui.xsd">

    <!-- Annotation-based beans -->
    <context:component-scan base-package="de.diedavids.cuba.cedicpl"/>
    
    <gui:screens base-packages="de.diedavids.cuba.cedicpl.web"/>


    <bean name="ddcdi_DataConverterFactory" class="de.diedavids.cuba.cedicpl.converter.CustomDataConverterFactory" />

</beans>
```


## Custom CSV parsing logic

In this example the CSV parser should be configured to expect `;` as a separator instead of the default `,` character.
In version 0.8.0 of data-import this is not configurable via the UI.


### CustomDataConverterFactory.groovy

The method `createTableDataConverter` has to be overridden. You can then return a new instance of a different `ImportDataConverter` that is specific for your use-case.
In this case I created a `CsvColonSeparatorImportDataConverter` class that extends the default `CsvImportDataConverter` class.

``` 
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
```

### CsvColonSeparatorImportDataConverter.groovy

```
import com.xlson.groovycsv.CsvParser

class CsvColonSeparatorImportDataConverter extends CsvImportDataConverter {

    @Override
    protected Iterator parse(String content) {
        def csvConfiguration = [separator: ";"]
        new CsvParser().parse(csvConfiguration,content)
    }

}
```

In this case I injected the argument `separator` to the CSV parsing library, to change the default behavior. More information on the 
CSV parsing library can be found here: https://github.com/xlson/groovycsv
