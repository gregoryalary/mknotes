package gregoryalary;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

/**
 * Utility class made to extract the site architecture from
 * the root folder
 * @author gregoryalary
 */
public final class StructureExtractor {

    private StructureExtractor() { /* Private empty constructor */ }

    public static Collection<File> getRootStructure(String path) {
        File rootDir = new File(path);
        Collection<File> files = FileUtils.listFiles(rootDir, new String[] {"md"}, true);
        return files;
    }

    public static Collection<File> getRootStructure() {
        return getRootStructure(AppConfig.get("rootDirectory"));
    }

}
