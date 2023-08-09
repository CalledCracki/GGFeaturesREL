package at.cracki.ggfeaturesrel.files;

import at.cracki.ggfeaturesrel.GGFeatures;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static YamlDocument scoreboardConfig;
    public static YamlDocument perkConfig;
    public static YamlDocument perkDataConfig;

    public static YamlDocument cooldownConfig;

    public static void createFiles(GGFeatures instance) {
        try {
            scoreboardConfig = YamlDocument.create(new File(instance.getDataFolder() + File.separator + "Scoreboard" + File.separator, "/Scoreboard.yml"),
                    instance.getResource("Scoreboard/Scoreboard.yml"), GeneralSettings.DEFAULT,
                    LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
            perkConfig = YamlDocument.create(new File(instance.getDataFolder() + File.separator + "Perks" + File.separator, "/Perks.yml"),
                    instance.getResource("Perks/Perks.yml"), GeneralSettings.DEFAULT,
                    LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
            perkDataConfig = YamlDocument.create(new File(instance.getDataFolder() + File.separator + "Data" + File.separator, "/Perks.yml"),
                    instance.getResource("Data/Perks.yml"), GeneralSettings.DEFAULT,
                    LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
            cooldownConfig = YamlDocument.create(new File(instance.getDataFolder() + File.separator + "Cooldowns" + File.separator, "/Kopf.yml"),
                    instance.getResource("Cooldowns/Kopf.yml"), GeneralSettings.DEFAULT,
                    LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static YamlDocument getScoreboardConfig() {
        return scoreboardConfig;
    }
    public static YamlDocument getPerkConfig() {
        return perkConfig;
    }
    public static YamlDocument getPerkDataConfig() {
        return perkDataConfig;
    }

    public static YamlDocument getCooldownConfig() {
        return cooldownConfig;
    }
}