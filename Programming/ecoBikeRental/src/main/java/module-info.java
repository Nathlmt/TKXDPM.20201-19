module org.tkxdpm20201.Nhom19{
    requires javafx.controls;
    requires transitive javafx.fxml;
    requires java.sql;

    opens org.tkxdpm20201.Nhom19.presentation to javafx.graphics, javafx.fxml;
    exports org.tkxdpm20201.Nhom19;


}