package com.bladecoder.engine.actions.ui;

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.ui.defaults.DefaultSceneScreen;

@ActionDescription("Change the menu button visibility.")
public class MenuButtonVisibility implements Action {
  @ActionProperty(required = true)
  @ActionPropertyDescription("Menu Button visibility.")
  private boolean visible;
  
  public void init(World w) {}
  
  public boolean run(VerbRunner cb) {
    UI ui = BladeEngine.getAppUI();
    DefaultSceneScreen sceneScreen = (DefaultSceneScreen)ui.getScreen(UI.Screens.SCENE_SCREEN);
    sceneScreen.getMenuButton().setVisible(this.visible);
    return false;
  }
}

