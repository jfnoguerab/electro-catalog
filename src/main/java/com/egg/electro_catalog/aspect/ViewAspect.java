package com.egg.electro_catalog.aspect;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.egg.electro_catalog.annotation.ViewBasePath;

@Aspect
@Component
public class ViewAspect {
// Definimos un listado de prefijos a excluir por defecto
    private static final List<String> DEFAULT_EXCLUDED_PREFIXES = Arrays.asList("redirect:", "forward:");
    private final List<String> globalExcludedPrefixes;

    // Agregamos la opción de tomar los prefijos a excluir desde el "application.properties"
    public ViewAspect(@Value("${view.excluded.prefixes:}") String prefixes) {
        this.globalExcludedPrefixes = Arrays.stream(prefixes.split(","))
                                               .map(String::trim)
                                               .filter(s -> !s.isEmpty())
                                               .collect(Collectors.toList());
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
          "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
          "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
          "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
          "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void requestMappings() {}

    @Pointcut("execution(* com.egg.electro_catalog.controllers.*.*(..))")
    public void controllerMethods() {}

    @Around("controllerMethods() && requestMappings()")
    public Object viewProccesor(ProceedingJoinPoint joinPoint) throws Throwable {
        // Si el retorno de los métodos es un String entendemos que es un método que
        // se usa para cargar una vista
        Object result = joinPoint.proceed(); // Aquí se ejecuta el método original
        if (result instanceof String) {
            String viewTemplate = (String) result;
            List<String> excludedPrefixes = DEFAULT_EXCLUDED_PREFIXES;

            // Obtenemos los prefijos excluidos agregados desde la anotación "@ViewBasePath"
            // solo si existen prefijos específicos por controlador
            Class<?> controller = joinPoint.getTarget().getClass();
            ViewBasePath annotation = controller.getAnnotation(ViewBasePath.class);
            if (annotation != null) {
                excludedPrefixes = Arrays.stream(annotation.excludePrefixes())
                                          .map(String::trim)
                                          .filter(s -> !s.isEmpty())
                                          .collect(Collectors.toList());
                excludedPrefixes.addAll(DEFAULT_EXCLUDED_PREFIXES);
            }

            // Agregamos los prefijos obtenidos de "application.properties"
            if(globalExcludedPrefixes.size() > 0) {
                excludedPrefixes.addAll(globalExcludedPrefixes);
            }

            // Si la vista inicia con alguno de los prefijos excluidos
            // no la modificamos y se retorna tal cual
            if (excludedPrefixes.stream().anyMatch(viewTemplate::startsWith)) {
                return viewTemplate;
            }

            // De lo contrario y sí se usa la anotación "@ViewBasePath"
            // agregamos el prefijo a la ruta de la vista 
            if (annotation != null) {
                String path = annotation.value();
                return path + viewTemplate;
            }
        }
        return result;
    }
}
