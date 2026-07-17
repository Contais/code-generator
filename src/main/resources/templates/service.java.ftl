package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
#if(${importServiceJavaPackages})
#foreach(${pkg} in ${importServiceJavaPackages})
import ${pkg};
#end
#end

/**
 * ${table.comment!} Service
 *
 * @author ${author}
 * @since ${date}
 */
#if(${generateService})
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
#end
