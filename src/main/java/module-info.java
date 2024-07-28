module com.manager.managerfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.compiler;

    opens com.manager.managerfx.model.entities to javafx.base;
    opens com.manager.managerfx to javafx.fxml;

    exports com.manager.managerfx;
    exports com.manager.managerfx.controllers;
    opens com.manager.managerfx.controllers to javafx.fxml;
}