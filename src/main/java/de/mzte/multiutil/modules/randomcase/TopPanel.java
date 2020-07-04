package de.mzte.multiutil.modules.randomcase;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    protected final JButton copyButton = new JButton("Copy");
    protected final JButton clearButton = new JButton("Clear");
    protected final JToggleButton alwaysOnTopButton = new JToggleButton("Always On Top");

    protected TopPanel() {
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        this.setLayout(new GridLayout());
        this.add(copyButton);
        this.add(clearButton);
        this.add(alwaysOnTopButton);
    }
}
