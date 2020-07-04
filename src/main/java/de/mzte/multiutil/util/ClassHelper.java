package de.mzte.multiutil.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassHelper {
    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException If weird stuff happens
     * @throws IOException If weird stuff happens
     */
    public static List<Class<?>> getClasses(String packageName, boolean recursive)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while(resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for(File directory : dirs)
            classes.addAll(findClasses(directory, packageName, recursive));
        return classes;
    }

    /**
     * method used to find all classes in a given directory and subdirs.
     *
     * @param directory The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException If weird stuff happens
     */
    public static List<Class<?>> findClasses(File directory, String packageName, boolean recursive) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if(!directory.exists())
            return classes;
        for(File file : directory.listFiles())
            if(recursive && file.isDirectory())
                classes.addAll(findClasses(file, packageName + "." + file.getName(), true));
            else if(file.getName().endsWith(".class"))
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
        return classes;
    }
}
