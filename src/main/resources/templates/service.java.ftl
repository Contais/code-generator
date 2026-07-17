package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
<#if importServiceJavaPackages??>
<#list importServiceJavaPackages as pkg>
import ${pkg};
</#list>
</#if>

/**
 * ${table.comment!} Service
 *
 * @author ${author}
 * @since ${date}
 */
<#if generateService?c == "true">
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
</#if>
