package de.mzte.multiutil.modules

import de.mzte.multiutil.RANDOM
import de.mzte.multiutil.module.GuiModule
import de.mzte.multiutil.module.LoadModule
import de.mzte.multiutil.util.*
import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextArea
import javafx.scene.layout.Priority
import javafx.stage.Stage
import tornadofx.*
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

@LoadModule
object RandomCase : GuiModule() {
    override val id = "randomcase"
    override val name = "Random Case"

    override fun Stage.stageModifier() {
        minWidth = 500.0
        minHeight = 200.0
    }

    override val gui
        get() =
            root {
                lateinit var inputBox: TextArea
                lateinit var outputBox: TextArea

                title = name
                vbox {
                    hbox {
                        button("Copy to Clipboard") {
                            hgrow = Priority.ALWAYS
                            maxMaxWidth()
                            action {
                                Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(outputBox.text), null)
                            }
                        }
                        button("Clear") {
                            hgrow = Priority.ALWAYS
                            maxMaxWidth()
                            action {
                                inputBox.text = ""
                                outputBox.text = ""
                            }
                        }
                        togglebutton("Always on Top") {
                            hgrow = Priority.ALWAYS
                            maxMaxWidth()

                            isSelected = false

                            action {
                                (this@root.currentWindow as Stage).isAlwaysOnTop = isSelected
                            }
                        }
                    }

                    hbox {
                        maxMax()
                        grow()
                        scrollpane {
                            fit()
                            grow()
                            vbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
                            hbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
                            inputBox = textarea {
                                onKeyReleased = EventHandler {
                                    outputBox.text = String(text.map {
                                        if(RANDOM.nextBoolean())
                                            it.toUpperCase()
                                        else
                                            it.toLowerCase()
                                    }.toCharArray())
                                }
                            }
                        }

                        scrollpane {
                            fit()
                            grow()
                            vbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
                            hbarPolicy = ScrollPane.ScrollBarPolicy.ALWAYS
                            outputBox = textarea {
                                isEditable = false
                            }
                        }
                    }
                }
            }
}