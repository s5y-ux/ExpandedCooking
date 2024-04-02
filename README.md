# ExpandedCooking

![Untitled design](https://github.com/s5y-ux/ExpandedCooking/assets/59636597/a9832bc8-6660-41e8-8149-fadcce15cbd0)

https://github.com/s5y-ux/ExpandedCooking/assets/59636597/c8a81e38-560d-44ca-b7fd-cb2c57174dfe

![Minecraft](https://img.shields.io/badge/Minecraft-1.20+-brightgreen.svg)
![Spigot](https://img.shields.io/badge/Spigot-1.20.2-orange.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)
## Package net.ddns.vcccd

This package contains classes related to cooking, leveling up cooking experience, and consuming food in a Minecraft Bukkit server.

### Classes

#### ChangeLevel

This class implements the `CommandExecutor` interface and provides functionality to change the cooking experience level of players.

##### Constructor
- `ChangeLevel(Main main)`: Constructs a `ChangeLevel` object with the specified `Main` instance.

##### Methods
- `onCommand(CommandSender sender, Command command, String label, String[] args)`: Overrides the `onCommand` method to handle the execution of the `/setcookingexp` command.

#### CookedFood

This class implements the `Listener` interface and handles events related to cooking and furnace interactions.

##### Constructor
- `CookedFood(Main main)`: Constructs a `CookedFood` object with the specified `Main` instance.

##### Methods
- `onCampFireCook(CampfireStartEvent event)`: Handles the event when a campfire cooking starts.
- `onFurnaceSmelt(FurnaceSmeltEvent event)`: Handles the event when an item is smelted in a furnace.
- `OnPlayerCook(FurnaceExtractEvent event)`: Handles the event when a player cooks food in a furnace.
- `FurnaceInteract(PlayerInteractEvent event)`: Handles the event when a player interacts with a furnace.
- `FurnacePlace(BlockPlaceEvent event)`: Handles the event when a furnace is placed by a player.

#### EatFood

This class implements the `Listener` interface and handles events related to consuming food.

##### Constructor
- `EatFood(Main main)`: Constructs an `EatFood` object with the specified `Main` instance.

##### Methods
- `onPlayerEat(PlayerItemConsumeEvent event)`: Handles the event when a player consumes food.

#### GetLevel

This class implements the `CommandExecutor` interface and provides functionality to retrieve the cooking experience level of players.

##### Constructor
- `GetLevel(Main main)`: Constructs a `GetLevel` object with the specified `Main` instance.

##### Methods
- `onCommand(CommandSender sender, Command command, String label, String[] args)`: Overrides the `onCommand` method to handle the execution of the `/getcookingexp` command.

#### LevelData

This class stores data related to food items and their corresponding experience values.

##### Methods
- `getFoodExp(Material material)`: Retrieves the experience value of a given food material.

#### Main

This class extends `JavaPlugin` and serves as the main entry point for the plugin.

##### Methods
- `onEnable()`: Initializes the plugin on server start-up.
- `onDisable()`: Cleans up resources on server shutdown.

#### PlayerData

This class handles data storage and retrieval for player cooking experience and quality.

##### Constructor
- `PlayerData(Main main, Player player)`: Constructs a `PlayerData` object for the specified player.

##### Methods
- `getCookingEXP()`: Retrieves the cooking experience of the player.
- `setCookingExp(int newLevel)`: Sets the cooking experience level of the player.
- `getCookQuality()`: Retrieves the quality of food cooked by the player.
- `getChef()`: Retrieves the name of the chef who cooked the food.

### Usage

To use these classes, ensure that you have the required dependencies, such as Bukkit and Bungee APIs, configured in your project. Then, you can instantiate and use objects of the provided classes in your plugin to implement custom cooking, leveling, and food consumption mechanics in your Minecraft server.
