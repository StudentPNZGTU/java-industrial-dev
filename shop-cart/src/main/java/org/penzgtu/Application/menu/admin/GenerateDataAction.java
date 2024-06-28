package org.penzgtu.Application.menu.admin;

import org.penzgtu.Application.data.DataGenerator;
import org.penzgtu.Application.data.DataLoader;
import org.penzgtu.Application.menu.ActionMenu;

public class GenerateDataAction extends ActionMenu {

    private final DataGenerator dataGenerator;
    private final DataLoader dataLoader;
    public GenerateDataAction(DataGenerator dataGenerator, DataLoader dataLoader) {
        super("Generating Entities", "Generate Data");
        this.dataGenerator = dataGenerator;
        this.dataLoader = dataLoader;
    }

    @Override
    public void executeCustomAction() {
        this.println("1. Generate Local Data");
        this.println("2. Fetch Data from API");

        int param = this.prompt("Please enter a number to continue: ", Integer.class);

        if (param == 1) {
            getDataFromDataGenerate();
        } else if (param == 2) {
            getDataFromAPI();
        } else {
            this.goBack();
        }
    }

    public void getDataFromDataGenerate() {
        dataGenerator.init();
        this.println("Data created successfully");
    }
    /*
        https://fakestoreapi.com/docs
     */
    public void getDataFromAPI() {
        this.println("API data loaded successfully");
        dataLoader.loadData();
    }
}
