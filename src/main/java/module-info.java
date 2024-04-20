module d4rk.c47.car_usb_formatter_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens d4rk.c47.tunecraft to javafx.fxml;
    exports d4rk.c47.tunecraft;
    exports d4rk.c47.tunecraft.controllers;
    opens d4rk.c47.tunecraft.controllers to javafx.fxml;
}