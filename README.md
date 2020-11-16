# fabric-loader
## GrossFabricHackers' Fork

The loader for mods under Fabric. It provides target-independent mod loading facilities and useful abstractions for other mods to use.

## License

Licensed under the Apache License 2.0.
Significant changes from upstream include
- The removal of Loom
- The removal of all code that pertains to Minecraft
- The removal of `EnvType#CLIENT` and `EnvType#SERVER`, which are replaced with `EnvType#UNIVERSAL`