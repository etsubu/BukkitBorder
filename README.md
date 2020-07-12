# Bukkit Border plugin
This plugin is used for Bukkit API and can be used to create bedrock borders within a map which can be used for PVP matches.

## Requirements
Build has been tested with java jdk 11 and craftbukkit 1.16.1

## How to build
```
git clone git@github.com:etsubu/BukkitBorder.git
cd BukkitBorder
gradlew clean build
```

Plugin file is located under build/libs/

## How to use
You can use the plugin within in game if you have OP permissions available. \
/border size \
where size is a number that defines the area size. The size is calculated to each direction from your character \
meaning that \
/border 100 \
will create borders with each wall having length of 200 blocks, and your character being in the middle of the area will \
have distance of 100 blocks to each wall.

After starting the border build process please note that depending on the server this can cause some lag while the build is \
in progress. The build runs with a scheduled task and the process will thus take some time to finish but the server \
should stay available during that.