package org.tjl.bingo;


import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.Optional;

public class PrimaryController {
    
    private BingoGame bingo;
    @FXML private Label caller;
    @FXML private Button setupBtn;
    @FXML private Button bingoBtn;
    @FXML private Button cancelBtn;
    @FXML private Button calledNums;
    @FXML private Button nextNums;
    @FXML private Label games;
    
    // This sets the game up
    @FXML
    public void setupGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Wager?");
        alert.setContentText("Are you playing for money?");
        ButtonType yesBtn = new ButtonType("Yes");
        ButtonType noBtn = new ButtonType("No");
        alert.getButtonTypes().setAll(yesBtn, noBtn);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == yesBtn){
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Kitty");
            dialog.setContentText("How much is in the kitty?");
            Optional<String> dialogResult = dialog.showAndWait();
            dialogResult.ifPresent(s -> bingo = new BingoGame(Float.parseFloat(s)));
        }
        else if(result.get() == noBtn){
            bingo = new BingoGame();
        }
        games.setText(bingo.getGame());
        setupBtn.setDisable(true);
        bingoBtn.setDisable(false);
        cancelBtn.setDisable(false);
        calledNums.setDisable(false);
        nextNums.setDisable(false);
    }
    
    @FXML
    public void nextNum(){
        int num = bingo.nextNumber();
        if(num != -1){
            caller.setText(String.valueOf(num));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Numbers have ran out");
            alert.showAndWait();
        }
    }

    @FXML
    public void showCalled(){
        int[] called = bingo.getCalledNums();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Called Numbers");
        alert.setHeaderText("Called Numbers");
        alert.setContentText(Arrays.toString(called));
        alert.showAndWait();
    }

    @FXML
    public void cancelGame(){
        caller.setText("##");
        games.setText("!!");
        setupBtn.setDisable(false);
        bingoBtn.setDisable(true);
        cancelBtn.setDisable(true);
        calledNums.setDisable(true);
        nextNums.setDisable(true);
    }

    @FXML
    public void gotBingo(){
        Alert confimation = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Result");
        confimation.setTitle("Have you got a bingo?");
        confimation.setContentText("Has the person called all their numbers in this list:\n" + Arrays.toString(bingo.getCalledNums()));
        Optional<ButtonType> result = confimation.showAndWait();
        if (result.get() == ButtonType.OK){
            alert.setContentText("You have won: " + bingo.bingoWinner());
            alert.showAndWait();
            games.setText(bingo.getGame());
        } else {
            alert.setContentText("No Winner this time");
            alert.showAndWait();
        }
    }
}
