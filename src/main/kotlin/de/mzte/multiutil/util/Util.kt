package de.mzte.multiutil.util

import javafx.scene.Parent
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import tornadofx.Fragment
import tornadofx.hgrow
import tornadofx.vgrow

fun Region.maxMaxWidth() {
    maxWidth = Double.MAX_VALUE
}

fun Region.maxMaxHeight() {
    maxHeight = Double.MAX_VALUE
}

fun Region.maxMax() {
    maxMaxWidth()
    maxMaxHeight()
}

fun Region.grow() {
    vgrow = Priority.ALWAYS
    hgrow = Priority.ALWAYS
}

fun ScrollPane.fit() {
    isFitToWidth = true
    isFitToHeight = true
}

fun root(builder: Fragment.() -> Parent): Parent = object : Fragment() {
    override val root = this.builder()
}.root