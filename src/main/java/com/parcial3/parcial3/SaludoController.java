package com.parcial3.parcial3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api")
public class SaludoController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/saludo")
    public String obtenerSaludo(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("saludo", null, locale);
    }
}
