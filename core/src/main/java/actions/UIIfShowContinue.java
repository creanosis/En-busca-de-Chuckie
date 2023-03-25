package com.bladecoder.engine.actions.ui;

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.AbstractIfAction;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;

@ActionDescription("Show the continue button?")
public class UIIfShowContinue extends AbstractIfAction {
  public void init(World w) {}
  
  public boolean run(VerbRunner cb) {
    World world = BladeEngine.getAppUI().getWorld();

      if (world.getCurrentScene() != null || world.savedGameExists()){
       gotoElse(cb); 
      
    }

    return false;
  }
}