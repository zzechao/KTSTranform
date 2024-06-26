package com.zhouz.plugin


/**
 * constructor for arouter-auto-register settings
 * @param interfaceName interface to scan
 */
class ScanSetting(interfaceName: String) {
    companion object {
        const val PLUGIN_NAME: String = "com.alibaba.arouter"

        /**
         * The register code is generated into this class
         */
        const val GENERATE_TO_CLASS_NAME = "com/alibaba/android/arouter/core/LogisticsCenter"

        /**
         * you know. this is the class file(or entry in jar file) name
         */
        const val GENERATE_TO_CLASS_FILE_NAME = "$GENERATE_TO_CLASS_NAME.class"


        /**
         * The register code is generated into this method
         */
        const val GENERATE_TO_METHOD_NAME = "loadRouterMap"

        /**
         * The package name of the class generated by the annotationProcessor
         */
        const val ROUTER_CLASS_PACKAGE_NAME = "com/alibaba/android/arouter/routes/"

        /**
         * The package name of the interfaces
         */
        const val INTERFACE_PACKAGE_NAME = "com/alibaba/android/arouter/facade/template/"


        /**
         * register method name in class: {@link #GENERATE_TO_CLASS_NAME}
         */
        const val REGISTER_METHOD_NAME = "register"
    }


    /**
     * scan for classes which implements this interface
     */
    var interfaceName: String = ""

    /**
     * scan result for {@link #interfaceName}
     * class names in this list
     */
    val classList = mutableSetOf<String>()

    init {
        this.interfaceName = INTERFACE_PACKAGE_NAME + interfaceName
    }
}