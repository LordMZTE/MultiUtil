package de.mzte.multiutil.module

import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

abstract class GuiModule() : IModule {
    override fun run() {
        with(Stage()) {
            stageModifier()
            title = name
            scene = Scene(gui)
            show()
        }
    }

    open fun Stage.stageModifier() {}

    abstract val gui: Parent
}