package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar las peticiones generales de la aplicación,
 * como la página de inicio.
 */
@Controller
public class HomeController {

    /**
     * Maneja las peticiones GET a la raíz del sitio ("/").
     * Devuelve el nombre de la vista JSP que se debe renderizar.
     *
     * @return El nombre de la vista ("index") para que Spring Boot resuelva
     * a /WEB-INF/jsp/index.jsp.
     */
    @GetMapping("/")
    public String mostrarPaginaInicio() {
        // Simplemente devolvemos el nombre del archivo JSP sin la extensión.
        // Spring se encargará de encontrarlo gracias a la configuración
        // en application.properties (prefix y suffix).
        return "index";
    }

}
