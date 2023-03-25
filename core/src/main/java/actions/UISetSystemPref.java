package com.bladecoder.engine.actions.ui;

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.model.MusicManager;
import com.bladecoder.engine.model.SceneSoundManager;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.VoiceManager;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.util.Config;

@ActionDescription("Sets some preference value for a system.")
public class UISetSystemPref implements Action {

  enum System {
  MUSIC_VOLUME, EFFECTS_VOLUME, VOICES_VOLUME;
}
  @ActionProperty(required = true)
  @ActionPropertyDescription("The system.")
  private System system = System.MUSIC_VOLUME;
  
  @ActionProperty(required = true)
  @ActionPropertyDescription("The value.")
  private String value;
  
  World uiWorld;
  
  public void init(World w) {
    this.uiWorld = w;
  }
  
  public boolean run(VerbRunner cb) {
    UI ui = BladeEngine.getAppUI();
    World gameWorld = ui.getWorld();
    if (this.system == System.MUSIC_VOLUME) {
      MusicManager.VOLUME_MULTIPLIER = Float.parseFloat(this.value);
      this.uiWorld.getMusicManager().setVolume(this.uiWorld.getMusicManager().getVolume());
      gameWorld.getMusicManager().setVolume(gameWorld.getMusicManager().getVolume());
    } else if (this.system == System.EFFECTS_VOLUME) {
      SceneSoundManager.VOLUME_MULTIPLIER = Float.parseFloat(this.value);
    } else if (this.system == System.VOICES_VOLUME) {
      VoiceManager.VOLUME_MULTIPLIER = Float.parseFloat(this.value);
      this.uiWorld.getCurrentScene().getTextManager().getVoiceManager()
        .setVolume(this.uiWorld.getCurrentScene().getTextManager().getVoiceManager().getVolume());
      gameWorld.getCurrentScene().getTextManager().getVoiceManager()
        .setVolume(gameWorld.getCurrentScene().getTextManager().getVoiceManager().getVolume());
    } 
    Config.getInstance().setPref(this.system.name(), this.value);
    Config.getInstance().savePrefs();
    return false;
  }
}
