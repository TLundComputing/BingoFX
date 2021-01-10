module org.tjl.bingo {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.tjl.bingo to javafx.fxml;
    exports org.tjl.bingo;
}