package io.ravitej.core.framework.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ravit on 13/12/2016.
 */
class ConfigHelpers {

    private static File searchConfigFileInClasspath(String filename) {
        Stream<File> streamFiles = new ArrayList<>(FileUtils.listFiles(new File("."), new RegexFileFilter(filename), TrueFileFilter.INSTANCE))
                .stream().filter(f -> !f.getAbsolutePath().contains("target"));

        List<File> files = new ArrayList<>();
        streamFiles.forEach(files::add);

        if (files.size() == 0)
            throw new Error("Config file with name [" + filename + "] could not be found in your classpath.");
        if (!files.get(0).isFile())
            throw new Error("The file [" + files.get(0).getAbsolutePath() + "] is not a normal file.");
        return files.get(0);
    }

    static Config initConfig(String filename, String section) {
        File envConfig = searchConfigFileInClasspath(filename);
        return ConfigFactory.parseFile(envConfig).getConfig(section).resolve();
    }

    static Config initConfig(String filename) {
        File envConfig = searchConfigFileInClasspath(filename);
        return ConfigFactory.parseFile(envConfig).resolve();
    }
}
