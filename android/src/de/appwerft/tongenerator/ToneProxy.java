/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package de.appwerft.tongenerator;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiC;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

// This proxy can be created by calling Tonegenerator.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = TonegeneratorModule.class)
public class ToneProxy extends KrollProxy {
	AudioTrack track;

	public ToneProxy() {
		super();
	}

	@Override
	public void handleCreationDict(KrollDict options) {
		double freq = 440.0;
		int duration = 1000;
		super.handleCreationDict(options);

		if (options.containsKey("freq")) {
			freq = options.getDouble("freq");
		}
		if (options.containsKey(TiC.PROPERTY_DURATION)) {
			duration = options.getInt(TiC.PROPERTY_DURATION);
		}
		generateTone(freq, duration);
	}

	@Kroll.method
	public void play() {
		track.play();
	}

	private AudioTrack generateTone(double freqHz, int durationMs) {
		int count = (int) (44100.0 * 2.0 * (durationMs / 1000.0)) & ~1;
		short[] samples = new short[count];
		for (int i = 0; i < count; i += 2) {
			short sample = (short) (Math.sin(2 * Math.PI * i
					/ (44100.0 / freqHz)) * 0x7FFF);
			samples[i + 0] = sample;
			samples[i + 1] = sample;
		}
		AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
				AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
				count * (Short.SIZE / 8), AudioTrack.MODE_STATIC);
		track.write(samples, 0, count);
		return track;
	}
}