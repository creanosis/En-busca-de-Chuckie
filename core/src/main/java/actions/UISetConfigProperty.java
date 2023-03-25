package com.bladecoder.engine.actions.ui;

import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.util.Config;

@ActionDescription("Sets a game configuration property.")
public class UISetConfigProperty implements Action {
  @ActionProperty(required = true)
  @ActionPropertyDescription("Property name")
  private String name;
  
  @ActionProperty
  @ActionPropertyDescription("Property value")
  private String value;
  
  public void init(World w) {}
  
  public boolean run(VerbRunner cb) {
    Config.getInstance().setPref(this.name, this.value);
    Config.getInstance().savePrefs();
    return false;
  }
}
