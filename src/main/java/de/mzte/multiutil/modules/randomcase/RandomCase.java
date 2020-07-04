package de.mzte.multiutil.modules.randomcase;

import de.mzte.multiutil.Main;
import de.mzte.multiutil.module.IModule;
import de.mzte.multiutil.module.LoadModule;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@LoadModule
public class RandomCase implements IModule {

    @Override
    public void run() {
        new Gui();
    }

    @Override
    public String getID() {
        return "randomcase";
    }

    @Override
    public String getName() {
        return "Random Case";
    }

    private class Gui extends JFrame {
        TopPanel topPanel;
        BottomPanel bottomPanel;

        protected Gui() {
            this.setTitle(RandomCase.this.getName());
            this.setLocationRelativeTo(null);
            this.setMinimumSize(new Dimension(500, 200));
            this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
            this.initComponents();
            this.initActionListeners();
            this.setVisible(true);
        }

        private void initActionListeners() {
            //Copy Button
            topPanel.copyButton.addActionListener(a -> Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(
                            new StringSelection(bottomPanel.outputPane.getText()),
                            null
                    )
            );

            //Clear Button
            topPanel.clearButton.addActionListener(a -> {
                bottomPanel.inputPane.setText("");
                updateOutput();
            });

            //Always on top button
            topPanel.alwaysOnTopButton.addItemListener(a -> this.setAlwaysOnTop(
                    topPanel.alwaysOnTopButton.isSelected()));

            //Randomize Text
            bottomPanel.inputPane.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    updateOutput();
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    updateOutput();
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    updateOutput();
                }
            });
        }

        private void updateOutput() {
            String text = bottomPanel.inputPane.getText();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < text.length(); i++)
                sb.append(Main.RANDOM.nextBoolean() ? Character.toUpperCase(text.charAt(i)) :
                        Character.toLowerCase(text.charAt(i)));
            bottomPanel.outputPane.setText(sb.toString());
        }

        private void initComponents() {
            topPanel = new TopPanel();
            bottomPanel = new BottomPanel();
            this.add(topPanel);
            this.add(bottomPanel);
        }
    }
}
