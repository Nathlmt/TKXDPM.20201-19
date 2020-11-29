module org.tkxdpm20201.Nhom19{
    requires javafx.controls;
    requires transitive javafx.fxml;
    requires java.sql;
    requires java.management;
    requires java.naming;
    requires com.zaxxer.hikari;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires org.json;
    requires org.apache.commons.codec;

    opens org.tkxdpm20201.Nhom19.presentation to javafx.graphics, javafx.fxml;
    exports org.tkxdpm20201.Nhom19;

}