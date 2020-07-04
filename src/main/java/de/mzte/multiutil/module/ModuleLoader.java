package de.mzte.multiutil.module;

import de.mzte.multiutil.util.ClassHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ModuleLoader {
    private final String pkg;
    private final Map<String, IModule> modules = new HashMap<>();

    public ModuleLoader(String pkg) {
        this.pkg = pkg;
        init();
    }

    private void init() {
        try {
            ClassHelper.getClasses(pkg, true).stream()
                    .filter(c -> IModule.class.isAssignableFrom(c) &&
                            c.isAnnotationPresent(LoadModule.class))
                    .map(c -> {
                        try {
                            return (IModule) c.newInstance();
                        } catch(InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }).peek(m -> System.out.println("Loading Module " + m.getID()))
                    .forEach(this::addModule);

        } catch(ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public void addModule(IModule module) {
        addModule(module, module.getID());
    }

    public void addModule(IModule module, String id) {
        modules.put(id, module);
    }

    public void runModule(String id) {
        modules.computeIfAbsent(id,
                x -> {
                    throw new NoSuchElementException("Command " + id + " Not Found");
                }).run();
    }

    public void runModuleAsync(String id) {
        (new Thread(() -> runModule(id))).start();
    }

    public Map<String, IModule> getModules() {
        return new HashMap<>(modules);
    }
}
