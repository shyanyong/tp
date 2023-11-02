package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;

/**
 * A class to access date data stored as a json file on the hard disk.
 */
public class JsonTodaysDate {

    private static final Logger logger = LogsCenter.getLogger(JsonTodaysDate.class);

    private Path filePath;

    public JsonTodaysDate(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTodaysDateFilePath() {
        return filePath;
    }

    @Override
    public Date readTodaysDate(Path filepath) throws DataLoadingException {
        return readTodaysDate(filePath);
    }

    @Override
    public void SaveTodaysDate(Date todaysDate, Path filepath) throws IOException {
        requireNonNull(todaysDate);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTodaysDate(todaysDate), filePath);
    }

}
