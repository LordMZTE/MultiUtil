package de.mzte.multiutil.gui

import de.mzte.multiutil.LOADER
import de.mzte.multiutil.util.maxMaxHeight
import de.mzte.multiutil.util.maxMaxWidth
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Priority
import javafx.stage.Stage
import tornadofx.*

class Gui : View() {
    init {
        title = "MultiUtil"
    }

    override val root = vbox {
        minWidth = 1000.0
        togglebutton("Always on top") {
            isSelected = false
            useMaxWidth = true
            action {
                (this@Gui.currentWindow as Stage).isAlwaysOnTop = isSelected
            }
        }

        scrollpane {
            isFitToWidth = true
            vgrow = Priority.ALWAYS
            maxMaxHeight()
            vbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS

            vbox {
                for(module in LOADER.modules)
                    button(module.value.name) {
                        hgrow = Priority.ALWAYS
                        maxMaxWidth()
                        action {
                            LOADER.runModule(module.key)
                        }
                    }
            }
        }
    }
}