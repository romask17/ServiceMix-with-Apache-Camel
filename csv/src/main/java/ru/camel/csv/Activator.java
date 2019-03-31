package ru.camel.csv;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.core.osgi.OsgiDefaultCamelContext;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private CamelContext camelContext;
    private CsvDataFormat csv = new CsvDataFormat();

    public void start(BundleContext bundleContext) throws Exception {
        csv.setDelimiter('|');
        csv.setQuoteDisabled(true);
        csv.setLazyLoad(true);

        System.out.println("::::START CSV BUNDLE::::");
        camelContext = new OsgiDefaultCamelContext(bundleContext);
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file://./in/csv?charset=windows-1251")
                        .split(body().tokenize("\n"))
                        .streaming()
                        .unmarshal(csv)
                        .process(exchange -> {
                            String line = exchange.getIn().getBody(String.class);
                            System.out.println("Processing line: " + line);
                        });

            }
        });
        camelContext.start();
    }

    public void stop(BundleContext bundleContext) throws Exception {
        camelContext.stop();
        System.out.println("::::STOP CSV BUNDLE::::");
    }
}
