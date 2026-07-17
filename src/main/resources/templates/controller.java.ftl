package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
#if(${restControllerStyle})
import org.springframework.web.bind.annotation.RestController;
#else
import org.springframework.web.bind.annotation.Controller;
#end
#if(${importControllerJavaPackages})
#foreach(${pkg} in ${importControllerJavaPackages})
import ${pkg};
#end
#end

/**
 * ${table.comment!} Controller
 *
 * @author ${author}
 * @since ${date}
 */
#if(${restControllerStyle})
@RestController
#else
@Controller
#end
@RequiredArgsConstructor
@RequestMapping#if(${controllerMappingHyphenStyle})("${controllerMappingHyphen}")#end
public class ${table.controllerName} {

    private final ${table.serviceName} ${table.serviceName?uncap_first};

}
