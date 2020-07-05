package de.mzte.multiutil.modules.randomcase;

import javax.swing.*;

public class BottomPanel extends JPanel {
    protected final JEditorPane inputPane = new JEditorPane();
    protected final JEditorPane outputPane = new JEditorPane();

    protected BottomPanel() {
        JScrollPane inputScroll = new JScrollPane(inputPane);
        JScrollPane outputScroll = new JScrollPane(outputPane);
        inputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        outputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        outputPane.setEditable(false);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(inputScroll);
        this.add(outputScroll);
    }
}
