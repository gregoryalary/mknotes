package mknotes;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.apache.commons.io.FileUtils.forceMkdir;

public final class ProjectCreator {

    public String projectName;

    public File projectDirectory;

    private String templateContent;

    public ProjectCreator(String projectName) {
        this.projectName = projectName.replace("/", "");
    }

    /**
     * Create the projectname/ projectnames/notes projectname/site
     * projectname/.template directories
     * and the projectname/.template/template.html and projectname/mknotes.json files
     * @throws Exception
     */
    public void generateProjectDirectory() throws Exception {
        createProjectDirectoryIfDoesntExist();
        createsNoteDirectory();
        createsSiteDirectory();
        createTemplate();
        initMknoteJson();
    }

    /**
     * Create an empty directory with the project name if no file exist
     * with this name
     * @throws Exception
     */
    public void createProjectDirectoryIfDoesntExist() throws Exception {
        projectDirectory = new File(projectName);
        if (projectDirectory.exists()) {
            throw new Exception("The directory " + projectName + "/ already exist.");
        } else {
            forceMkdir(projectDirectory);
            FileUtils.cleanDirectory(projectDirectory);
        }
    }

    /**
     * Create the empty notes/ directory
     * @throws Exception
     */
    public void createsNoteDirectory() throws Exception {
        File noteDirectory = new File(projectDirectory + "/notes");
        forceMkdir(noteDirectory);
        FileUtils.cleanDirectory(noteDirectory);
    }

    /**
     * Create the empty site/ directory
     * @throws Exception
     */
    public void createsSiteDirectory() throws Exception {
        File siteDirectory = new File(projectDirectory + "/site");
        forceMkdir(siteDirectory);
        FileUtils.cleanDirectory(siteDirectory);
    }

    /**
     * Create the .template/ directory and add the file template.html in the new directory
     * The file will contain the default template content
     * @throws Exception
     */
    public void createTemplate() throws Exception {
        File templateDirectory = new File(projectDirectory + "/.template");
        forceMkdir(templateDirectory);
        FileUtils.cleanDirectory(templateDirectory);
        File template = new File(projectDirectory + "/.template/template.html");
        FileUtils.writeStringToFile(template, getTemplateContent());
    }

    /**
     * Create and write the file mknotes.json in the root directory
     * with the default values and the project name
     * @throws Exception
     */
    public void initMknoteJson() throws Exception {
        File mknotesJson = new File(projectDirectory + "/mknotes.json");
        JSONObject object = new JSONObject();
        object.accumulate("rootDirectory", "notes/")
                .accumulate("siteDirectory", "site/")
                .accumulate("template", ".template/template.html")
                .accumulate("projectName", projectName);
        FileUtils.writeStringToFile(mknotesJson, object.toString());
    }

    /**
     * The html content of the template
     */
    private String getTemplateContent() throws Exception {
        if (templateContent == null) {
            InputStream in = getClass().getResourceAsStream("/template-default.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            templateContent = org.apache.commons.io.IOUtils.toString(reader);
        }
        return templateContent;
    }

}
