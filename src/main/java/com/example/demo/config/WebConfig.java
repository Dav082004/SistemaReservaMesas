package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Configuración Web del Sistema de Reservas
 * 
 * Esta clase configura:
 * - Resolución de vistas JSP
 * - Manejo de recursos estáticos (CSS, JS, imágenes)
 * - Configuración del patrón MVC
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    
    /**
     * Configura el resolvedor de vistas para archivos JSP
     * Define que las vistas se encuentran en /WEB-INF/jsp/ y tienen extensión .jsp
     * 
     * @return ViewResolver configurado para JSP con JSTL
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    /**
     * Configura el manejo de recursos estáticos
     * Permite acceso directo a archivos CSS, JavaScript e imágenes
     * 
     * @param registry Registro de manejadores de recursos
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapea URLs /css/** a archivos en /webapp/css/
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");
        
        
        // Mapea URLs /images/** a archivos en /webapp/images/
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/images/");
    }
}
