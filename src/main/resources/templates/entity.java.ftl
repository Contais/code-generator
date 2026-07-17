package ${package.Entity};

#if(${entityLombokModel})
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
#end
#if(${activeRecord})
import com.baomidou.mybatisplus.annotation.*;
#end
#if(${entitySerialVersionUID})
import java.io.Serial;
#end
#if(${entityFieldUseJavaDoc})
import java.io.Serializable;
#end
#if(${importEntityJavaPackages})
#foreach(${pkg} in ${importEntityJavaPackages})
import ${pkg};
#end
#end

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @since ${date}
 */
#if(${entityLombokModel})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
#end
#if(${activeRecord})
@TableName("${table.name}")
#end
public class ${entity} #if(${entitySerialVersionUID}) implements Serializable#end {

#if(${entitySerialVersionUID})
    @Serial
    private static final long serialVersionUID = 1L;
#end
#foreach($field in ${table.fields})
#if(${field.keyFlag})
    @TableId(value = "${field.name}", type = IdType.AUTO)
#end
#foreach($attr in ${field.annotationAttributesList})
    @${attr.type}(${attr.importType} = "${attr.value}")
#end
    /**
     * ${field.comment!}
     */
    private ${field.propertyType} ${field.propertyName};

#end
}
