# Generated Assets for Hookshot Heroes

This branch contains a set of license-safe replacement assets and a generator script to produce PNG sprites and WAV audio files locally.

What is included
- assets/LICENSE-ASSETS.md — license for these generated assets (CC0/public domain).
- assets/ASSETS_MANIFEST.md — mapping of suggested original asset filenames to generated replacements.
- assets/README.md — instructions and notes.
- assets/svg/ — simple SVG placeholder sprites and tiles you can preview in a browser.
- assets/scripts/generate_assets.py — Python script that will generate PNG sprites, tiles, and WAV audio (requires Python 3.8+, Pillow).

How to use
1. Clone this branch or switch to it: git checkout generated-assets
2. Install Pillow: pip install pillow
3. Run the generator: python assets/scripts/generate_assets.py
4. The script outputs PNGs to assets/output_images/ and WAVs to assets/output_audio/

Notes
- These replacement assets are intentionally simple programmatic placeholders (pixel-style tiles and sprites, loopable chiptune-like WAVs) so you can validate licensing and swap them into your project quickly.
- If you want hand-polished art or more complex audio, we can iterate or bring in an artist/musician.

