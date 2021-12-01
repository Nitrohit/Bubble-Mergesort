package view;

import cnode.CNode;
import util.RandomCNodes;
import sortingalgorithms.*;

import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AnimationController extends BorderPane {

  public static final int WINDOW_WIDTH = 800;
  public static final int WINDOW_HEIGHT = 500;
  public static final int XGAP = 10;
  public static final int BUTTONROW_BOUNDARY = 100;

  public static int NO_OF_CNODES = 40;

  private static AbstractSort abstractSort;

  private Pane display;
  private HBox buttonRow;

  private Button sortButton;
  private Button randomButton;
  private ChoiceBox<AbstractSort> choiceBox;

  private CNode[] cnodes;

  @SuppressWarnings("static-access")
public AnimationController() {
    this.display = new Pane(); //sætter vores display op (vinduet)
    this.buttonRow = new HBox();	// laver vores knapper igennem en hbox

    this.setCenter(display); 
    this.setBottom(buttonRow);

    this.sortButton = new Button("Sortér");
    this.randomButton = new Button("Bland");
    this.choiceBox = new ChoiceBox<>();

    this.cnodes = RandomCNodes.randomCNodes(NO_OF_CNODES);

    buttonRow.getChildren().add(sortButton);
    buttonRow.getChildren().add(randomButton);
    buttonRow.getChildren().add(choiceBox);

    buttonRow.setAlignment(Pos.CENTER);

    for (Node b : buttonRow.getChildren()) {
      buttonRow.setMargin(b, new Insets(5, 5, 20, 5)); // størrelse og margin imellem knapperne og choiceboksen
    }


    List<AbstractSort> abstractSortList = new ArrayList<>();
    abstractSortList.add(new BubbleSort());
    abstractSortList.add(new MergeSort());

    display.getChildren().addAll(Arrays.asList(cnodes));

    sortButton.setOnAction(event -> { //dette gør man ikke kan blande dem mens koden 
      sortButton.setDisable(true);
      randomButton.setDisable(true);

      abstractSort = choiceBox.getSelectionModel().getSelectedItem();

      SequentialTransition sq = new SequentialTransition(); //dette objekt kører animationer i en sekvens

      sq.getChildren().addAll(abstractSort.startSort(cnodes)); //sætter vores noder ind i animations sekvensen

      sq.setOnFinished(e -> { 
    	  randomButton.setDisable(false); //gør så man kan blande dem igen når den allerede har sorteret
      });

      sq.play();

    });

    randomButton.setOnAction(event -> {
      sortButton.setDisable(false);
      display.getChildren().clear();

      cnodes = RandomCNodes.randomCNodes(NO_OF_CNODES); // kører random koden når der bliver trykket på knappen

      display.getChildren().addAll(Arrays.asList(cnodes));
    });

    choiceBox.setItems(FXCollections.observableArrayList(
      abstractSortList // sætter vores muligheder ind i listen
    ));



    choiceBox.setConverter(new StringConverter<AbstractSort>() {
        @Override
        public String toString(AbstractSort abstractSort) {
       
            return abstractSort.getClass().getSimpleName(); // giver dem deres reelle navne så de ikke får latterligt lange navne
          
        }

        public AbstractSort fromString(String s) {
            return null;
          }
    
      });

  }

}
