package org.penzgtu.Application.menu;

import io.bretty.console.view.MenuView;

public interface MenuFactory {
    /*
        io.bretty.console.view.ActionView;  - служит для представления Menu (его наследует ActionMenu)
        io.bretty.console.view.MenuView;    - служит для представления заголовка дочернего меню
     */
    ActionMenu createActionView();
    MenuView createMenuView();
}

