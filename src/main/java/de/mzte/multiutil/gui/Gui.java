package de.mzte.multiutil.gui;

import de.mzte.multiutil.Main;
import de.mzte.multiutil.module.IModule;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class Gui extends JFrame {
    JToggleButton alwaysOnTopButton = new JToggleButton("Always On Top");
    public Gui(List<IModule> modules) {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setTitle("MultiUtil");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(200, 250));
        this.initComponents();
        modules.sort(Comparator.comparing(o -> o.getName().toLowerCase()));
        this.addModules(modules);
        this.setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 0, 0));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        alwaysOnTopButton.addItemListener(a -> this.setAlwaysOnTop(
                this.alwaysOnTopButton.isSelected()));
        panel.add(alwaysOnTopButton);
        JLabel label = new JLabel("Modules");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        this.add(panel);
    }

    private void addModules(List<IModule> modules) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        modules.forEach(m -> {
            JButton button = new JButton(m.getName());
            button.addActionListener(a -> Main.LOADER.runModuleAsync(m.getID()));
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
            panel.add(button);
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);
    }
}
