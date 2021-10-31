package ru.sberbank.denisov26.javacard.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    public static class RestResponse {
        private String string1;
        private String string2;

        public RestResponse(String string1, String string2) {
            this.string1 = string1;
            this.string2 = string2;
        }

        public String getString1() {
            return string1;
        }

        public void setString1(String string1) {
            this.string1 = string1;
        }

        public String getString2() {
            return string2;
        }

        public void setString2(String string2) {
            this.string2 = string2;
        }
    }

    @GetMapping(value = "/somebody", produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse responseMethod(String name) {
       return new RestResponse("Hello my dear friend, ", name);
    }

    @GetMapping(value = "/somebody1"/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    public String responseMethod(String name, String name1) {
        return "webapp/WEB-INF/views/MyPage";
    }
}
