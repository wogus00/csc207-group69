package data_access;

import entity.Project;
import entity.ProjectFactory
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileProjectDataAccessObject implements CreateProjectDataAcessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Project> projects = new HashMap<>();

    private ProjectFactory projectFactory;

    public FileProjectDataAccessObject(String csvPath, ProjectFactory projectFactory) throws IOException {
        this.projectFactory = projectFactory;

        csvFile = new File(csvPath);
        headers.put("projectName", 0);
        headers.put("leaderEmail", 1);
        headers.put("memberEmails", 2);

        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String projectName = String.valueOf(col[headers.get("projectName")]);
                    String leaderEmail = String.valueOf(col[headers.get("leaderEmail")]);
                    String memberEmails = String.valueOf(col[headers.get("memberEmails")]);
                    Project project = projectFactory.create(projectName, leaderEmail, memberEmails);
                    projects.put(projectName, project);
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean checkProjectName(String newProjectName) {
        return projects.containsKey(newProjectName);
    }

    public Project get(String projectName) {
        return projects.get(projectName);
    }
}