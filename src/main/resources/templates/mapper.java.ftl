package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
#if(${mapperAnnotationClass})
import ${mapperAnnotationClass};
#end
#if(${importMapperJavaPackages})
#foreach(${pkg} in ${importMapperJavaPackages})
import ${pkg};
#end
#end

/**
 * ${table.comment!} Mapper
 *
 * @author ${author}
 * @since ${date}
 */
#if(${mapperAnnotationClass})
@${mapperAnnotationClass}
#end
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
