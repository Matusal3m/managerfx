module com.manager.managerfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.manager.managerfx to javafx.fxml;
    exports com.manager.managerfx;
    exports com.manager.managerfx.controllers;
    opens com.manager.managerfx.controllers to javafx.fxml;
}