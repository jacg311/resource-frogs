# Resource Frogs

Resource Frogs is a mod that adds data driven frogs to the game that can produce resources for you!

## Adding new frogs

On first startup, the mod creates several folders where your custom Resource Frogs will be defined.\
The configuration files for the frogs are in the folder "/config/resource_frogs/frogs".\
You can Copy-Paste this example configuration and tweak it to your liking.\

```json5
{
  "likes_lava": false,
  "passive": false,
  "has_glow_feature": false,
  "eating_cooldown": 600,
  "breeding_item": "minecraft:raw_copper",
  "food_item": "minecraft:raw_copper",
  "spawn_egg": {
    "primary_color": 12345678,
    "secondary_color": 87654321
  }
}
```

Create a new file with the ID of the frog inside the mentioned folder. Example Name: redstone_frog.json\
This alone is enough to create a new frog! A spawn egg will also be added automatically.\
\
In order to give it a texture, place it inside of the folder "/config/resource_frogs/textures"\
The default texture size of frogs is 48 by 48. But higher resolutions also look great.\
Keep in mind that the name of the file must match the id of the frog.\
So if your ID is redstone_frog, the texture must be named redstone_frog.png\
\
If you choose to add a glowing feature, name the texture <id>_glow.png. Example: redstone_frog_glow.png\
This feature renders the _glow texture as an Overlay, similar to Enderman or Spider eyes. This also works with Iris!

The last folder "/config/resource_frogs/lang" is for the language files for your frogs and spawn eggs.\
This works exactly like regular minecraft language files.

All fields in the config have default values.\
That means you can leave out any field and the mod will automatically fill it anyway.\
Here's a list with all of them with a bit of explanation:
```json5
{
    "likes_lava": false, // if the frog is immune to lava
    "passive": false, // if the frog doesn't need items to produce something
    "has_glow_feature": false, // if the frog should have a glowing texture
    "eating_cooldown": 600, // time in ticks inbetween feeding
    "breeding_item": "minecraft:air", // identifier of item used to breed the frog
    "food_item": "minecraft:air", // identifier of item the frog will consume
    "spawn_egg": {
        "primary_color": 12345678, // color in decimal, may also be hex -> 0xbc614e
        "secondary_color": 87654321 // color in decimal, may also be hex -> 0x5397fb1
    }
}
```