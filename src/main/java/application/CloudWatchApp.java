package application;

import media.image.consumer.ImageConsumer;
import media.image.consumer.ImageScoringSubConsumer;
import media.image.consumer.ImageSubConsumer;
import media.image.consumer.UIPublishSubConsumer;
import media.image.consumer.UpdateContrastChartSubConsumer;
import media.image.producer.ImageProducer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.werx.framework.bus.ReflectionBus;

import ui.StartUI;

public class CloudWatchApp {
    private CloudWatchConfig config;

    public void start(String[] args) {
        processCommandLine(args);
        startApplication();

    }

    private void processCommandLine(String[] args) {
        Options options = new Options();
        options.addOption("u", false, "Uses the User Interface");

        CommandLineParser parser = new PosixParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            prUsage();
            System.exit(1);
        }

        if (cmd.hasOption("u")) {
            config.setCommandLine(false);
        }
    }

    private void startApplication() {
        // starts the reflection bus
        ReflectionBus.start();

        // gets the producers, consumers
        ImageProducer producer = InstanceFactory.getImageProducer();
        ImageConsumer consumer = InstanceFactory.getImageConsumer();
        consumer.addSubConsumer(new ImageScoringSubConsumer());
        consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getAppContext().getBean("cloudjudgesubconsumer"));
        consumer.addSubConsumer(new UpdateContrastChartSubConsumer());
        consumer.addSubConsumer((ImageSubConsumer) InstanceFactory.getAppContext().getBean("savecontrastchartsubconsumer"));
        
        if (!config.isCommandLine()) {
            StartUI frame = new StartUI();
            frame.start();
            consumer.addSubConsumer(new UIPublishSubConsumer());
        } else {
            // nothing to do
        }
        producer.start();
        consumer.run();

    }

    private void prUsage() {
        System.err.println("Usage: java cloudFrame <url>");
    }

    public CloudWatchConfig getConfig() {
        return config;
    }

    public void setConfig(CloudWatchConfig config) {
        this.config = config;
    }

}
