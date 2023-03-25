package com.bladecoder.engine.actions.ui;

import com.badlogic.gdx.Gdx;
import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;


@ActionDescription("Continuar.")
public class UIContinue implements Action {
  public void init(World w) {}
  
  public boolean run(VerbRunner cb) {
    UI ui = BladeEngine.getAppUI();
    World world = ui.getWorld();

        if (world.getCurrentScene() == null)
          try {
            world.load();
          } catch (Exception e) {
            Gdx.app.exit();
          }  
        ui.setCurrentScreen(UI.Screens.SCENE_SCREEN);
        return false;

    
}

} /* public general*/
     
    