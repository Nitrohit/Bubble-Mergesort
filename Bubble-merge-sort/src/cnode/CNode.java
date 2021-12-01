package cnode;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CNode extends Rectangle {

  private int value;

  public CNode(int n) {
    this.value = n;
  } // opstiller vores node

  public int getValue() {
    return this.value; // g�r det muligt at f� v�rdien p� en hvilkensomhelt node
  }

  //denne funktion flytter p� vores noder
  public TranslateTransition moveX(int x) {
    TranslateTransition t = new TranslateTransition();
    t.setNode(this);
    t.setDuration(Duration.millis(10));
    t.setByX(x);

    return t; 
  }

}
