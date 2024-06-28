package org.penzgtu.Application.menu;

import io.bretty.console.view.ActionView;

import com.github.freva.asciitable.AsciiTable;

public abstract class ActionMenu extends ActionView implements ActionInterface {

    public ActionMenu(String runningTitle, String nameInMenu) {
        super(runningTitle, nameInMenu);
    }

    @Override
    public void executeCustomAction() {
        // set it up for yourself
    }
    @Override
    public void outAsciiTable(String[] header, String[][] data) {
        this.println(AsciiTable.getTable(header, data));
    }

    public void clear() {
        this.print("\u001b[H\u001b[2J");
    }
}