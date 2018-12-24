package mknotes;

import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.concurrent.Callable;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Picocli interfact of the App
 */
@CommandLine.Command(
        description = "A simple command line tool to export notes.",
        name = "mknotes", mixinStandardHelpOptions = true, version = "mknotes 0.1"
)
public class App implements Callable<Void> {

    /** Export the project */
    @CommandLine.Option(names = { "-s", "--site"}, description = "Export the note project.")
    private boolean site;

    /** Create a new project with PROJECT_NAME for its name */
    @CommandLine.Option(names = { "-c", "--create" }, paramLabel = "PROJECT_NAME", description = "The name of the note project.")
    private String projectName;

    /** Print the exception stack traces */
    @CommandLine.Option(names = {"--errortrace"}, description = "Print the error traces")
    private boolean errorTrace;

    /**
     * Create a new project or export it
     * depending on the command parameters
     */
    @Override
    public Void call() throws Exception {
        try {
            if (site) { // Export the website
                siteCommand();
            } else if (projectName != null) { // Create a new project
                createCommand();
            } else { // No args, print the help
                CommandLine.usage(this, System.out);
            }
        } catch (Exception e) {
            if (errorTrace) {
                e.printStackTrace();
            } else {
                System.out.println(ansi().fg(RED).a(e.getMessage()));
                System.out.println(ansi().fg(YELLOW).a("An error happened, use the --errortrace option to print the error trace."));
            }
        }
        return null;
    }

    /**
     * Export the notes
     * @throws Exception
     */
    private void siteCommand() throws Exception {
        AppConfig.init();
        (new SiteGenerator()).generateSite();
        System.out.println(ansi().fg(GREEN).a("Done!"));
        System.out.println(ansi().fg(WHITE).a("Notes exported in ").fg(GREEN).a(AppConfig.get("siteDirectory")));
    }

    /**
     * Create a new directory for the notes project
     * @throws Exception
     */
    private void createCommand() throws Exception {
        (new ProjectCreator(projectName)).generateProjectDirectory();
        System.out.println(ansi().fg(GREEN).a("Done!"));
        System.out.println(ansi().fg(WHITE).a("Project created in ").fg(GREEN).a(projectName + "/"));
    }

    public static void main(String[] args) throws Exception {
        AnsiConsole.systemInstall(); // allow colors on terminal
        CommandLine.call(new App(), args);
    }
}
