package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
<#if importServiceImplJavaPackages??>
<#list importServiceImplJavaPackages as pkg>
import ${pkg};
</#list>
</#if>

/**
 * ${table.comment!} Service 实现
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@RequiredArgsConstructor
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {


}
