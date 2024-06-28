package org.penzgtu.Application.menu.admin;

import io.bretty.console.view.MenuView;
import org.penzgtu.Application.data.DataGenerator;
import org.penzgtu.Application.data.DataLoader;
import org.penzgtu.Application.services.DatabaseService;

public class AdminMenu extends MenuView {
    public AdminMenu(DatabaseService databaseService, DataGenerator dataGenerator, DataLoader dataLoader) {
        super("SQL-lite database", "admin");
        this.addMenuItem(new AdminAction(databaseService));
        this.addMenuItem(new GenerateDataAction(dataGenerator, dataLoader));
    }
}
