package mknotes;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;

import static org.apache.commons.io.FileUtils.forceMkdir;

public final class ProjectCreator {

    public String projectName;

    public File projectDirectory;

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
        FileUtils.writeStringToFile(template, TEMPLATE_CONTENT);
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
    private static final String TEMPLATE_CONTENT =
            "<!doctype html>\n" +
                    "<html>\n" +
                    "\n" +
                    "<head>\n" +
                    "    <title>{{#PAGETITLE#}}</title>\n" +
                    "    <meta charset=\"utf8\">\n" +
                    "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" +
                    "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.13.1/styles/github.min.css\">\n" +
                    "    <style>\n" +
                    "        html, body {\n" +
                    "            height: 100%;\n" +
                    "        }\n" +
                    "\n" +
                    "        .body-wrapper {\n" +
                    "            height : 100vh;\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        .sidebar {\n" +
                    "            background-color: black;\n" +
                    "            color: white;\n" +
                    "        }\n" +
                    "\n" +
                    "        .list-title {\n" +
                    "            margin-top: .3rem;\n" +
                    "            margin-bottom: .3rem;\n" +
                    "            color: lightgray;\n" +
                    "        }\n" +
                    "\n" +
                    "        .side-list {\n" +
                    "            list-style-type: none;\n" +
                    "            padding-left: 20px;\n" +
                    "            font-size: 20px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .first-side-list {\n" +
                    "            list-style-type: none;\n" +
                    "            padding-left: 0px;\n" +
                    "            font-size: 25px;\n" +
                    "        }\n" +
                    "\n" +
                    "        .sidebar-sub-title {\n" +
                    "            color: lightgray;\n" +
                    "        }\n" +
                    "\n" +
                    "        .sidebar-hr {\n" +
                    "            border-color: gray;\n" +
                    "        }\n" +
                    "\n" +
                    "        h1 {\n" +
                    "            margin-bottom: 30px;\n" +
                    "            margin-top: 30px;\n" +
                    "            font-weight : bold;\n" +
                    "        }\n" +
                    "\n" +
                    "        .title {\n" +
                    "            font-weight : bold;\n" +
                    "            font-size: 3rem;\n" +
                    "            color: black;\n" +
                    "        }\n" +
                    "\n" +
                    "        .link-sidebar:hover {\n" +
                    "            color: white;\n" +
                    "            text-decoration: none;\n" +
                    "        }\n" +
                    "\n" +
                    "        p {\n" +
                    "            text-align: justify;\n" +
                    "        }\n" +
                    "\n" +
                    "        img {\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .col-content {\n" +
                    "            max-height: 100vh;\n" +
                    "            overflow-y: scroll;\n" +
                    "        }\n" +
                    "\n" +
                    "    </style>\n" +
                    "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.13.1/highlight.min.js\"></script>\n" +
                    "    <script>hljs.initHighlightingOnLoad();</script>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"row container-fluid body-wrapper\">\n" +
                    "        <div class=\"col-3 sidebar\">\n" +
                    "            <h1 class=\"m-2 sidebar-title\">{{#PROJECTNAME#}}</h1>\n" +
                    "            <h6 class=\"m-2 sidebar-sub-title\">mknotes</h6>\n" +
                    "            <hr class=\"sidebar-hr\">\n" +
                    "            {{#SIDEBAR#}}\n" +
                    "        </div>\n" +
                    "        <div class=\"col-9 col-content\">\n" +
                    "            <div class=\"container mt-4\">\n" +
                    "                <h1 class=\"title\">{{#TITLE#}}</h1>\n" +
                    "                <hr>\n" +
                    "                <div>\n" +
                    "                    {{#CONTENT#}}\n" +
                    "                </div>\n" +
                    "                <hr>\n" +
                    "                <h6 class=\"lastmodified mt-4 mb-4 pull-right text-right\">{{#LASTMODIFIED#}}</h6>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";

}
