package com.bladecoder.engine.actions.ui;

import com.bladecoder.engine.BladeEngine;
import com.bladecoder.engine.actions.Action;
import com.bladecoder.engine.actions.ActionDescription;
import com.bladecoder.engine.actions.ActionProperty;
import com.bladecoder.engine.actions.ActionPropertyDescription;
import com.bladecoder.engine.model.VerbRunner;
import com.bladecoder.engine.model.World;
import com.bladecoder.engine.ui.UI;
import java.util.Map;
import java.util.Properties;

@ActionDescription("Temporal properties persistence between chapters.")
public class StaticTmpProps implements Action {

  private enum Operation {
     RESET, SAVE, LOAD;
  }
  
  private static final Properties tmp = new Properties();
  
  @ActionProperty(required = true)
  @ActionPropertyDescription("The system.")
  private Operation operation = Operation.RESET;
  
  public void init(World w) {}
  
  public boolean run(VerbRunner cb) {
    UI ui = BladeEngine.getAppUI();
    World world = ui.getWorld();
    if (this.operation == Operation.RESET) {
      tmp.clear();
    } else if (this.operation == Operation.SAVE) {
      tmp.clear();
      tmp.putAll(world.getCustomProperties());
    } else if (this.operation == Operation.LOAD) {
      for (Map.Entry<Object, Object> entry : tmp.entrySet())
        world.getCustomProperties().put((String)entry.getKey(), (String)entry.getValue()); 
    } 
    return false;
  }
}
