package ${package.Entity};

<#if entityLombokModel?c == "true">
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
</#if>
import com.baomidou.mybatisplus.annotation.*;
<#if entitySerialVersionUID?c == "true">
import java.io.Serial;
import java.io.Serializable;
</#if>
<#if importEntityJavaPackages??>
<#list importEntityJavaPackages as pkg>
import ${pkg};
</#list>
</#if>

/**
 * ${table.comment!}
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel?c == "true">
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
</#if>
@TableName("${table.name?lower_case}")
public class ${entity} <#if entitySerialVersionUID?c == "true">implements Serializable</#if> {

<#if entitySerialVersionUID?c == "true">
    @Serial
    private static final long serialVersionUID = 1L;
</#if>

<#list table.fields as field>
    /**
     * ${field.comment!}
     */
<#if field.keyFlag?c == "true">
    @TableId(value = "${field.name}", type = IdType.ASSIGN_ID)
</#if>
    private ${field.propertyType} ${field.propertyName};

</#list>
}
