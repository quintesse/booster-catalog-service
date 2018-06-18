package io.fabric8.launcher.booster.catalog

object LauncherConfiguration {

    private val LAUNCHER_BOOSTER_CATALOG_REPOSITORY = getEnvVarOrSysProp(PropertyName.LAUNCHER_BOOSTER_CATALOG_REPOSITORY,
            "https://github.com/fabric8-launcher/launcher-booster-catalog.git")

    private val LAUNCHER_BOOSTER_CATALOG_REF = getEnvVarOrSysProp(PropertyName.LAUNCHER_BOOSTER_CATALOG_REF,
            "master")

    interface PropertyName {
        companion object {
            const val LAUNCHER_BOOSTER_CATALOG_REPOSITORY = "LAUNCHER_BOOSTER_CATALOG_REPOSITORY"
            const val LAUNCHER_BOOSTER_CATALOG_REF = "LAUNCHER_BOOSTER_CATALOG_REF"
            const val LAUNCHER_BOOSTER_CATALOG_IGNORE_LOCAL = "LAUNCHER_BOOSTER_CATALOG_IGNORE_LOCAL"
        }
    }

    @JvmStatic
    fun ignoreLocalZip(): Boolean {
        return java.lang.Boolean.getBoolean(PropertyName.LAUNCHER_BOOSTER_CATALOG_IGNORE_LOCAL) || java.lang.Boolean.parseBoolean(System.getenv(PropertyName.LAUNCHER_BOOSTER_CATALOG_IGNORE_LOCAL))
    }

    @JvmStatic
    fun boosterCatalogRepositoryURI(): String {
        return LAUNCHER_BOOSTER_CATALOG_REPOSITORY
    }

    @JvmStatic
    fun boosterCatalogRepositoryRef(): String {
        return LAUNCHER_BOOSTER_CATALOG_REF
    }

    private fun getEnvVarOrSysProp(name: String, defaultValue: String): String {
        var value: String? = System.getProperty(name)
        if (value == null || value.isEmpty()) {
            value = System.getenv(name)
        }
        if (value != null && value.isEmpty()) {
            value = null
        }
        return if (value != null) value else defaultValue
    }
}
