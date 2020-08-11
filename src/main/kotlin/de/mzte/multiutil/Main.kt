@file:JvmName("Main")

package de.mzte.multiutil

import de.mzte.multiutil.gui.Gui
import de.mzte.multiutil.module.ModuleLoader
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.stage.Stage
import tornadofx.App
import tornadofx.launch
import java.util.*

val LOADER = ModuleLoader("de.mzte.multiutil.modules")
val RANDOM = Random()

fun main(args: Array<out String>) {
    launch<MainApp>(*args)
}

class MainApp : App(Gui::class) {
    override fun start(stage: Stage) = with(stage) {
        minWidth = 250.0
        minHeight = 400.0

        height = minHeight
        width = minWidth
        super.start(this)
        onHidden = EventHandler {Platform.exit()}
    }
}
