package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
<#if importControllerJavaPackages??>
<#list importControllerJavaPackages as pkg>
import ${pkg};
</#list>
</#if>

/**
 * ${table.comment!} Controller
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle?c == "true">
@RestController
<#else>
@Controller
</#if>
@RequiredArgsConstructor
@RequestMapping<#if controllerMappingHyphenStyle?c == "true">("/${controllerMappingHyphen}")</#if>
public class ${table.controllerName} {

    private final ${table.serviceName} ${table.serviceName?uncap_first};

}
