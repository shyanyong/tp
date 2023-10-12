package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.LogicManagerPrescription;
import seedu.address.logic.LogicPrescription;
import seedu.address.model.ModelManagerPrescription;
import seedu.address.model.ModelPrescription;
import seedu.address.model.PrescriptionList;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.ReadOnlyUserPrefsPrescription;
import seedu.address.model.UserPrefsPrescription;
import seedu.address.model.util.SampleDataUtilPrescription;
import seedu.address.storage.JsonPrescriptionListStorage;
import seedu.address.storage.JsonUserPrefsStoragePrescription;
import seedu.address.storage.PrescriptionListStorage;
import seedu.address.storage.StorageManagerPrescription;
import seedu.address.storage.StoragePrescription;
import seedu.address.storage.UserPrefsStoragePrescription;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManagerPrescription;

/**
 * Runs the application.
 */
public class MainAppPrescription extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(MainAppPrescription.class);

    protected Ui ui;
    protected LogicPrescription logic;
    protected StoragePrescription storage;
    protected ModelPrescription model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing PrescriptionList ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStoragePrescription userPrefsStorage = new JsonUserPrefsStoragePrescription(
            config.getUserPrefsFilePath());
        UserPrefsPrescription userPrefs = initPrefs(userPrefsStorage);
        PrescriptionListStorage prescriptionListStorage = new JsonPrescriptionListStorage(
            userPrefs.getPrescriptionListFilePath());
        storage = new StorageManagerPrescription(prescriptionListStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManagerPrescription(model, storage);

        ui = new UiManagerPrescription(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private ModelPrescription initModelManager(StoragePrescription storage, ReadOnlyUserPrefsPrescription userPrefs) {
        logger.info("Using data file : " + storage.getPrescriptionListFilePath());

        Optional<ReadOnlyPrescriptionList> prescriptionListOptional;
        ReadOnlyPrescriptionList initialData;
        try {
            prescriptionListOptional = storage.readPrescriptionList();
            if (!((Optional<?>) prescriptionListOptional).isPresent()) {
                logger.info("Creating a new data file " + storage.getPrescriptionListFilePath()
                        + " populated with a sample PrescriptionList.");
            }
            initialData = prescriptionListOptional.orElseGet(SampleDataUtilPrescription::getSamplePrescriptionList);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getPrescriptionListFilePath() + " could not be loaded."
                    + " Will be starting with an empty PrescriptionList.");
            initialData = new PrescriptionList();
        }

        return new ModelManagerPrescription(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            if (!configOptional.isPresent()) {
                logger.info("Creating new config file " + configFilePathUsed);
            }
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataLoadingException e) {
            logger.warning("Config file at " + configFilePathUsed + " could not be loaded."
                    + " Using default config properties.");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefsPrescription initPrefs(UserPrefsStoragePrescription storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefsPrescription initializedPrefs;
        try {
            Optional<UserPrefsPrescription> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefsPrescription());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new UserPrefsPrescription();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting PrescriptionList " + MainAppPrescription.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
