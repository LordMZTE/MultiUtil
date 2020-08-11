package de.mzte.multiutil.module

interface IModule {
    fun run()
    val id: String
    val name: String
}