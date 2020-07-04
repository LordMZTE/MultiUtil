package de.mzte.multiutil;

import de.mzte.multiutil.gui.Gui;
import de.mzte.multiutil.module.ModuleLoader;

import java.util.ArrayList;

public class Main {
    public static final ModuleLoader LOADER = new ModuleLoader("de.mzte.multiutil.modules");
    public static final Gui GUI = new Gui(new ArrayList<>(LOADER.getModules().values()));

    public static void main(String[] args) {

    }
}
