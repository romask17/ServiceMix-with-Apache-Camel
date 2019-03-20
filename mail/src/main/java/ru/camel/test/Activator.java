package ru.camel.test;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.core.osgi.OsgiDefaultCamelContext;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    private CamelContext camelContext;

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("::::START MAIL BUNDLE::::");
        camelContext = new OsgiDefaultCamelContext(bundleContext);
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("imaps://imap.yandex.ru?username=<user>&password=<password>&delete=false&unseen=true&consumer.delay=1")
                        .process(new LoggerClass());

            }
        });
        camelContext.start();
    }

    public void stop(BundleContext bundleContext) throws Exception {
        camelContext.stop();
        System.out.println("::::STOP MAIL BUNDLE::::");
    }
}
