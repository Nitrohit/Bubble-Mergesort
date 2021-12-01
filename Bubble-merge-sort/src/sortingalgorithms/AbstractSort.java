package sortingalgorithms;

import cnode.CNode;
import view.AnimationController;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class AbstractSort { //vi bruger en abstract class til at kunne samle vores project lidt bedre

  final Color START_COLOR = Color.CRIMSON;
  final Color SELECT_COLOR = Color.CYAN;
  final Color SORTED_COLOR = Color.LIGHTBLUE;

  static int DX;
  static {
    DX = AnimationController.WINDOW_WIDTH / AnimationController.NO_OF_CNODES;
  }

  ParallelTransition colorCNode(CNode[] arr, Color color, int...a) { // her er det bare at ingen eller flere integers kan blive til argumenter for funktionen, dette bruges til Mergesort da den har fat i flere værdier på samme tid
    ParallelTransition pt = new ParallelTransition(); // denne køre animationer i en parallel rækkefølge
    
    for (int i = 0; i < a.length; i++) {
      FillTransition ft = new FillTransition();//ændre farven på noderne som er i et aktivt array
      ft.setShape(arr[a[i]]);
      ft.setToValue(color);
      ft.setDuration(Duration.millis(10));
      pt.getChildren().add(ft);
    }
    return pt;
  }

  ParallelTransition colorCNode(List<CNode> list, Color color) {
	    ParallelTransition pt = new ParallelTransition();
	    
	    for (CNode c : list) { // for each loop
	    	System.out.println(list);
	      FillTransition ft = new FillTransition(); //ændre farven på noderne
	      ft.setShape(c);
	      ft.setToValue(color);
	      ft.setDuration(Duration.millis(10));
	      pt.getChildren().add(ft);
	    }

	    return pt;
	  }

  ParallelTransition swap(CNode[] arr, int i, int j) { // kører en swap funktion til at gøre det muligt at skifte værdier i vores liste
    ParallelTransition pt = new ParallelTransition();

    int dxFactor = j - i;

    pt.getChildren().addAll(arr[i].moveX(DX * dxFactor), arr[j].moveX(-DX * dxFactor)); // her skifter den positionerne af noderne som er lige blevet sorteret på deres nye pladser.

    CNode tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp; // flytter i værdien ind i en midlertidig variable, så dens værdi er vedligeholdt.

    return pt;
  }

  public abstract ArrayList<Transition> startSort(CNode[] arr);
}
