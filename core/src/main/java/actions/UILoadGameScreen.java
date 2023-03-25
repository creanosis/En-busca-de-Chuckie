package com.bladecoder.engine.actions.ui;

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;

@ActionDescription("CargarSalvar partida")
public class UILoadGameScreen implements Action {
  public void init(World w) {}
  
  public boolean run(VerbRunner cb) {
    UI ui = BladeEngine.getAppUI();
    ui.setCurrentScreen(UI.Screens.LOAD_GAME_SCREEN);
    return false;
  }
}
