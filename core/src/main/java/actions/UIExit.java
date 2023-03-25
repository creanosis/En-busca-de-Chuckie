package com.bladecoder.engine.actions.ui;

import com.badlogic.gdx.Gdx;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.Action;

@ActionDescription("Salir al escritorio")
public class UIExit implements Action {
  public void init(World w) {}
    
  public boolean run(VerbRunner cb) {
    Gdx.app.exit();
    return false;
  }
}
