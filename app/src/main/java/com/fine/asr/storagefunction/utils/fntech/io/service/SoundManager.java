package com.fine.asr.storagefunction.utils.fntech.io.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.fine.asr.storagefunction.R;

import java.util.HashMap;
import java.util.Map;

public class SoundManager
{
	private SoundPool mSoundPool,soundPool;
	private HashMap mSoundPoolMap;
	private AudioManager mAudioManager;
	private Context mContext;
	private Map<Integer, Integer> soundMap; 
	public SoundManager()
	{
		
	}
	
	public void initSounds( Context theContext )
	{
//		mContext = theContext;
//		mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0 );
//		mSoundPoolMap = new HashMap<Integer, Integer>();
//		mAudioManager = (AudioManager)mContext.getSystemService( Context.AUDIO_SERVICE );
		soundPool=new SoundPool(1,AudioManager.STREAM_MUSIC,0);
	    soundMap=new HashMap<Integer, Integer>();
	    soundMap.put(1,soundPool.load(theContext, R.raw.ok,1));
	}
	
	public void addSound( int index, int soundId )
	{
		mSoundPoolMap.put( index, mSoundPool.load( mContext, soundId,index ) );
	}
	
	public void playSound( int index, float volume )
	{
		int streamVolume = mAudioManager.getStreamVolume( AudioManager.STREAM_MUSIC );
		mSoundPool.play( (Integer)mSoundPoolMap.get( index ), streamVolume * volume, streamVolume * volume, 1, 0, 1f );
	}
	
	public void playSound( int index )
	{
		playSound( index, 1.0f );
	}
	public void playSound(){
		soundPool.play(soundMap.get(1),1,1,0,0,1);
	}
	public void playLoopedSound( int index )
	{
		int streamVolume = mAudioManager.getStreamVolume( AudioManager.STREAM_MUSIC );
		mSoundPool.play( (Integer)mSoundPoolMap.get( index ), streamVolume, streamVolume, 1, -1, 1f );
	}
}
