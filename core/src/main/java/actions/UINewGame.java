package com.bladecoder.engine.actions.ui;

import com.badlogic.gdx.Gdx;
import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.util.EngineLogger;



@ActionDescription("Comenzar partida.")
public class UINewGame implements Action {
	
  public void init(World w) {}
 
  public boolean run(VerbRunner cb) {
    UI ui = BladeEngine.getAppUI();
    World world = ui.getWorld();
    try {

		if(world.getCurrentScene() != null){
			
		
	
			world.newGame();
			ui.setCurrentScreen(UI.Screens.SCENE_SCREEN);
			
		}else{
			world.newGame();
			ui.setCurrentScreen(UI.Screens.SCENE_SCREEN);
		}
	 

    } catch (Exception e) {
      EngineLogger.error("IN NEW GAME", e);
      Gdx.app.exit();
    } 
  
    return false; 
  }
}
