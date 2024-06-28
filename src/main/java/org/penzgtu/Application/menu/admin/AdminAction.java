package org.penzgtu.Application.menu.admin;

import org.hibernate.exception.GenericJDBCException;
import org.penzgtu.Application.menu.ActionMenu;
import org.penzgtu.Application.services.DatabaseService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@PropertySource("classpath:application.properties")
public class AdminAction extends ActionMenu {

    private DatabaseService databaseService;

    public AdminAction(DatabaseService databaseService) {
        super("ADMIN", "sql queries");
        this.databaseService = databaseService;
    }

    @Override
    public void executeCustomAction() {
        String SQL = this.prompt("SQL request: ", String.class);
        try {
            List<Object[]> tablesObjects = databaseService.executeQuery(SQL);

            if (!tablesObjects.isEmpty()) {
                String[] header = new String[tablesObjects.get(0).length];
                for (int i = 0; i < header.length; i++) {
                    header[i] = "Column " + (i + 1);
                }

                String[][] data = new String[tablesObjects.size()][];
                for (int i = 0; i < tablesObjects.size(); i++) {
                    Object[] row = tablesObjects.get(i);
                    String[] stringRow = new String[row.length];
                    for (int j = 0; j < row.length; j++) {
                        stringRow[j] = row[j] != null ? row[j].toString() : "NULL";
                    }
                    data[i] = stringRow;
                }
                outAsciiTable(header, data);
            } else {
                this.println("No results found.");
            }
        } catch (ClassCastException | GenericJDBCException ex) {
            if (ex.getMessage().contains("Запрос не вернул результатов")) {
                System.err.println("No results found." + ex.getMessage() + "\n");
            } else {
                List<String> tablesObjects = databaseService.executeQueryForStrings(SQL);

                if (!tablesObjects.isEmpty()) {
                    String[] header = {"List of result"};
                    String[][] data = new String[tablesObjects.size()][1];
                    for (int i = 0; i < tablesObjects.size(); i++) {
                        data[i][0] = tablesObjects.get(i);
                    }
                    outAsciiTable(header, data);
                }
            }
        }
        this.goBack();
    }
}

