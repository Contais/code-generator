package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;
<#if importMapperJavaPackages??>
<#list importMapperJavaPackages as pkg>
import ${pkg};
</#list>
</#if>

/**
 * ${table.comment!} Mapper
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
