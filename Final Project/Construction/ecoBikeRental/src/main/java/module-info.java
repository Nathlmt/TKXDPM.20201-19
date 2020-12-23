module org.tkxdpm20201.Nhom19{
    requires javafx.controls;
    requires transitive javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    requires java.sql;
    requires java.management;
    requires java.naming;
    requires com.zaxxer.hikari;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires org.json;
    requires org.apache.commons.codec;
    requires com.jfoenix;

    opens org.tkxdpm20201.Nhom19.data.model to com.fasterxml.jackson.databind, com.fasterxml.jackson.annotation;
    opens org.tkxdpm20201.Nhom19.presentation.screen to javafx.graphics, javafx.fxml;
    exports org.tkxdpm20201.Nhom19;

}