include linux-yocto.inc

KERNEL_FEATURES_INTEL_COMMON_remove_dmo-eapi = "features/amt/mei/mei.scc"
KERNEL_FEATURES_INTEL_COMMON_append_dmo-eapi += "features/mei/mei.scc"
LINUX_VERSION_dmo-eapi = "4.8+"
