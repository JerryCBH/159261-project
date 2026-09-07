# ASSETS_MANIFEST

This manifest maps the repository's original asset categories to the generated replacements in this branch. Use it as a quick reference when swapping assets.

Original (possible) path -> Replacement path (generated-assets branch)

- Images/GameLogo.png -> assets/output_images/ui/game_logo.png
- Images/characters.png -> assets/output_images/sprites/lidia_walk_0.png (and _1/_2 frames)
- Images/consumables.png -> assets/output_images/tiles/coin.png, assets/output_images/tiles/veggie.png
- Images/dungeon1.png, Images/dungeon2.png -> assets/output_images/tiles/dungeon_floor.png (tileset)
- Images/boss1.png, Images/boss2.png -> assets/output_images/sprites/boss_minotaur.png, assets/output_images/sprites/boss_ghostwizard.png
- Audio/* -> assets/output_audio/*.wav

Keep in mind these are programmatic placeholders; replace them with hand-drawn assets later if desired.
