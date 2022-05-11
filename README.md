# Resource Frogs

Resource Frogs is a mod that adds data driven frogs to the game that can produce resources for you!

## Adding new frogs
On first startup, the mod creates several folders where your custom Resource Frogs will be defined.\
The configuration files for the frogs are in the folder "/config/resource_frogs/frogs".\
Copy Paste this example configuration and tweak it to your liking.\
```
{
  "likes_lava": false,
  "breeding_item": "minecraft:raw_copper",
  "has_glow_feature": false
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

_More extensive docs are in the works_