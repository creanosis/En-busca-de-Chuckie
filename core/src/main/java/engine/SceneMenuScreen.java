package com.bladecoder.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.bladecoder.engine.assets.EngineAssetManager;
import com.bladecoder.engine.model.InteractiveActor;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;
import com.bladecoder.engine.ui.defaults.DefaultSceneScreen;
import com.bladecoder.engine.util.EngineLogger;

public class SceneMenuScreen extends DefaultSceneScreen {
  private static final String POINTER_ENTER_VERB = "pointer-enter";
  
  private static final String POINTER_EXIT_VERB = "pointer-exit";
  
  private static final String START_VERB = "start";
    
  private World uiWorld;
    
  private InteractiveActor pointerInActor = null;
  
  private boolean startVerbLaunched = false;

  public void setUI(UI ui) {
    this.uiWorld = new World();
    super.setUI(ui);
    getMenuButton().setVisible(false);
    setShowHotspotsFeature(false);
  }

  public World getWorld() {
    return this.uiWorld;
  }


 
  public void render(float delta) {
    World world = getWorld();
    if (world.getAssetState() == World.AssetState.LOAD_ASSETS || world
      .getAssetState() == World.AssetState.LOAD_ASSETS_AND_INIT_SCENE) {
      world.update(delta);
      if (world.getAssetState() != World.AssetState.LOADED)
        EngineAssetManager.getInstance().finishLoading(); 
    } 
    if (!this.startVerbLaunched) {
      this.startVerbLaunched = true;
      if (world.getCurrentScene().getVerb("start") != null || world
        .getVerbManager().getVerb("start", null, null) != null) {
        world.getCurrentScene().runVerb("start");
        return;
      } 
    } 
    super.render(delta);
    runPointerEnterExitVerb(world);
  }
  
  private void runPointerEnterExitVerb(World world) {
    if (world.getAssetState() != World.AssetState.LOADED || Gdx.input.isPeripheralAvailable(Input.Peripheral.MultitouchScreen) || world
      .inCutMode() || world.hasDialogOptions() || world.isPaused())
      return; 
    InteractiveActor newPointerInActor = getWorld().getInteractiveActorAtInput(getViewport(), 0.0F);
    if (newPointerInActor != this.pointerInActor) {
      if (this.pointerInActor != null && world.getCurrentScene().getActor(this.pointerInActor.getId(), true) != null && this.pointerInActor
        .getVerb("pointer-exit") != null)
        this.pointerInActor.runVerb("pointer-exit", null); 
      this.pointerInActor = newPointerInActor;
      if (this.pointerInActor != null && this.pointerInActor.getVerb("pointer-enter") != null)
        this.pointerInActor.runVerb("pointer-enter", null); 
    } 
  }
  
  public void hide() {
    getWorld().dispose();
  }
  
  public void show() {
    try {
      this.uiWorld.loadWorldDesc();
      this.uiWorld.loadChapter("ui", null, false);
    } catch (Exception e) {
      EngineLogger.error("EXITING: " + e.getMessage());
      Gdx.app.exit();
    } 
    super.show();
  }

}
