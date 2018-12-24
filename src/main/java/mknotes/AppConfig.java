package mknotes;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

public class AppConfig {

    private static HashMap<String, String> config;

    private static AppConfig instance;

    private AppConfig() {
        config = new HashMap<>();
    }

    /**
     * Lazy load and return the singleton instance
     * @return
     */
    private static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    /**
     * Return the dictionnary of this
     * @return
     */
    private HashMap<String, String> getDictionnary() {
        return config;
    }

    /**
     * Add a new entry in the config dictionnary if the entry doesn't exist
     * @param key the key of the entry
     * @param value the value of the entry
     */
    public static void put(String key, String value) {
        if (getInstance().getDictionnary().get(key) == null) {
            getInstance().getDictionnary().put(key, value);
        }
    }

    /**
     * Get an entry in the config dictionnary
     * @param key
     * @return the value associated with the key
     */
    public static String get(String key) {
        return getInstance().getDictionnary().get(key);
    }

    /**
     * Init the config with the default values
     */
    public static void init() throws Exception {
        File file = new File("mknotes.json");
        JSONObject object = new JSONObject(FileUtils.readFileToString(file));
        AppConfig.put("rootDirectory", object.getString("rootDirectory"));
        AppConfig.put("siteDirectory", object.getString("siteDirectory"));
        AppConfig.put("template", object.getString("template"));
        AppConfig.put("projectName", object.getString("projectName"));
    }

}
