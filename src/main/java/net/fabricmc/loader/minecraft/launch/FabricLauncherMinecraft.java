package net.fabricmc.loader.minecraft.launch;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.launch.common.FabricLauncher;
import net.fabricmc.loader.launch.common.FabricLauncherBase;
import net.fabricmc.loader.util.Arguments;

import java.io.File;
import java.nio.file.Path;

public abstract class FabricLauncherMinecraft extends FabricLauncherBase {

	public static Path minecraftJar;

	public static File getLaunchDirectory(Arguments argMap) {
		return new File(argMap.getOrDefault("gameDir", "."));
	}

	@Override
	protected Path deobfuscate(String gameId, String gameVersion, Path gameDir, Path jarFile, FabricLauncher launcher) {
		Path result = super.deobfuscate(gameId, gameVersion, gameDir, jarFile, launcher);
		if (minecraftJar == null) {
			minecraftJar = result;
		}
		return result;
	}

	public static void processArgumentMap(Arguments argMap, EnvType envType) {
		switch (envType) {
			case CLIENT:
				if (!argMap.containsKey("accessToken")) {
					argMap.put("accessToken", "FabricMC");
				}

				if (!argMap.containsKey("version")) {
					argMap.put("version", "Fabric");
				}

				String versionType = "";
				if(argMap.containsKey("versionType") && !argMap.get("versionType").equalsIgnoreCase("release")){
					versionType = argMap.get("versionType") + "/";
				}
				argMap.put("versionType", versionType + "Fabric");

				if (!argMap.containsKey("gameDir")) {
					argMap.put("gameDir", FabricLauncherMinecraft.getLaunchDirectory(argMap).getAbsolutePath());
				}
				break;
			case SERVER:
				argMap.remove("version");
				argMap.remove("gameDir");
				argMap.remove("assetsDir");
				break;
		}
	}

}
