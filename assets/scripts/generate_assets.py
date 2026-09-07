"""
Simple asset generator for Hookshot Heroes - generates placeholder PNG sprites/tiles and WAV audio.
Generates programmatic pixel-style sprites and simple chiptune-like WAV loops.

Requires: Pillow
Install: pip install pillow
Run: python generate_assets.py
"""
from PIL import Image, ImageDraw
import os
import math
import wave
import struct

OUT_IMG_DIR = os.path.join(os.path.dirname(__file__), '..', 'output_images')
OUT_AUDIO_DIR = os.path.join(os.path.dirname(__file__), '..', 'output_audio')

os.makedirs(OUT_IMG_DIR, exist_ok=True)
os.makedirs(OUT_AUDIO_DIR, exist_ok=True)

SPRITES_DIR = os.path.join(OUT_IMG_DIR, 'sprites')
TILES_DIR = os.path.join(OUT_IMG_DIR, 'tiles')
UI_DIR = os.path.join(OUT_IMG_DIR, 'ui')

os.makedirs(SPRITES_DIR, exist_ok=True)
os.makedirs(TILES_DIR, exist_ok=True)
os.makedirs(UI_DIR, exist_ok=True)

# simple function to create a rectangle character sprite
def create_character(name, color, out_dir, w=32, h=48, frames=3):
    for f in range(frames):
        img = Image.new('RGBA', (w, h), (0,0,0,0))
        draw = ImageDraw.Draw(img)
        # body
        draw.rounded_rectangle([0,0,w,h], radius=4, fill=color)
        # legs (vary by frame)
        leg_offset = 1 if f==0 else (-1 if f==1 else 0)
        draw.rectangle([6, h-18+leg_offset, 12, h-6+leg_offset], fill=(30,30,30))
        draw.rectangle([20, h-18-leg_offset, 26, h-6-leg_offset], fill=(30,30,30))
        # face
        draw.ellipse([8,6,14,12], fill=(255,255,255))
        draw.ellipse([18,6,24,12], fill=(255,255,255))
        draw.ellipse([10,8,12,10], fill=(0,0,0))
        draw.ellipse([20,8,22,10], fill=(0,0,0))
        fname = os.path.join(out_dir, f"{name}_walk_{f}.png")
        img.save(fname)
        print('Wrote', fname)

# tiles
def create_tile(name, color1, color2, out_dir, size=32):
    img = Image.new('RGBA', (size, size), color1)
    draw = ImageDraw.Draw(img)
    draw.rectangle([4,4,size-4,size-4], fill=color2)
    fname = os.path.join(out_dir, f"{name}.png")
    img.save(fname)
    print('Wrote', fname)

# UI logo
def create_logo(out_dir):
    img = Image.new('RGBA', (128,128), (30,30,30))
    draw = ImageDraw.Draw(img)
    draw.ellipse([12,12,116,116], fill=(200,120,40))
    draw.text((16,60), "Hookshot", fill=(255,255,255))
    fname = os.path.join(out_dir, 'game_logo.png')
    img.save(fname)
    print('Wrote', fname)

# simple wav generator: sine-based chiptune-like loop
def write_wav(filename, seconds=4, freq=220.0, sample_rate=22050, melody=None):
    if melody is None:
        melody = [(freq, 0.4), (freq*1.5,0.4), (freq*2.0,0.4), (freq*1.2,0.4)]
    samples = []
    for note_freq, dur in melody:
        n_samples = int(sample_rate * dur)
        for i in range(n_samples):
            t = i / sample_rate
            # square-ish wave
            s = 0.6 * (1.0 if math.sin(2*math.pi*note_freq*t) > 0 else -1.0)
            samples.append(s)
    # repeat to fill seconds
    total_samples = int(sample_rate * seconds)
    if len(samples) < total_samples:
        multiplier = math.ceil(total_samples / len(samples))
        samples = (samples * multiplier)[:total_samples]
    else:
        samples = samples[:total_samples]

    # write wav
    with wave.open(filename, 'w') as wf:
        wf.setnchannels(1)
        wf.setsampwidth(2)
        wf.setframerate(sample_rate)
        for s in samples:
            val = int(max(-32767, min(32767, int(s * 32767))))
            wf.writeframes(struct.pack('<h', val))
    print('Wrote', filename)

# Generate assets
create_character('lidia', (107,142,35,255), SPRITES_DIR)
create_character('shura', (30,144,255,255), SPRITES_DIR)

create_tile('dungeon_floor', (139,69,19,255), (210,180,140,255), TILES_DIR)
create_tile('dungeon_wall', (47,79,79,255), (112,128,144,255), TILES_DIR)
create_tile('lava', (139,0,0,255), (255,69,0,255), TILES_DIR)
create_tile('treasure', (245,222,179,255), (218,165,32,255), TILES_DIR)

create_logo(UI_DIR)

# audio
write_wav(os.path.join(OUT_AUDIO_DIR, 'title_loop.wav'), seconds=6, freq=220.0)
write_wav(os.path.join(OUT_AUDIO_DIR, 'dungeon_loop.wav'), seconds=8, freq=110.0)
write_wav(os.path.join(OUT_AUDIO_DIR, 'sfx_hookshot.wav'), seconds=1, freq=880.0)

print('Done. Generated assets in', OUT_IMG_DIR, 'and', OUT_AUDIO_DIR)
