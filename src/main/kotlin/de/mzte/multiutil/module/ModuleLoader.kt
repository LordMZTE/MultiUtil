package de.mzte.multiutil.module

import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class ModuleLoader(val pkg: String) {
    val modules = mutableMapOf<String, IModule>()

    init {
        loadModules()
    }

    @Suppress("UNCHECKED_CAST")
    private fun loadModules() {
        val refs = Reflections(pkg, SubTypesScanner(false))
        refs.getSubTypesOf(IModule::class.java)
            .map {it.kotlin}
            .filter {it.annotations.any {a -> a.annotationClass == LoadModule::class}}
            .map {it.objectInstance ?: it}
            .forEach {if(it is IModule) addModule(it) else addClass(it as KClass<out IModule>)}
    }

    @JvmOverloads
    fun addModule(module: IModule, id: String = module.id) {
        modules[id] = module
    }

    fun addClass(clazz: KClass<out IModule>) = addModule(clazz.createInstance())

    fun runModule(id: String) = modules.getOrElse(id) {throw NoSuchElementException("Command $id not found")}.run()
}