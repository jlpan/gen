package top.jlpan.template;

/**
 * @author panliang
 * @version 1.0
 * @ProjectName gen
 * @Description entity 模板
 * @Date 2020/1/20 14:38
 */
public class EntityTemplate extends AbstractTemplate {

    @Override
    public String getTemplateString() {
        return "package ;\n" +
                "\n" +
                "#foreach ($column in $columns)\n" +
                "#if($column.attrType == 'Date')\n" +
                "import java.util.Date;\n" +
                "#break\n" +
                "#end\n" +
                "#end\n" +
                "#foreach ($column in $columns)\n" +
                "#if($column.attrType == 'BigDecimal')\n" +
                "import java.math.BigDecimal;\n" +
                "#break\n" +
                "#end\n" +
                "#end\n" +
                "#if($primaryKey)\n" +
                "import javax.persistence.GeneratedValue;\n" +
                "import javax.persistence.GenerationType;\n" +
                "import javax.persistence.Id;\n" +
                "import javax.persistence.Table;\n" +
                "#end\n" +
                "\n" +
                "import lombok.Data;\n" +
                "import lombok.EqualsAndHashCode;\n" +
                "\n" +
                "/**\n" +
                " * @author ${author}\n" +
                " * @version ${version}\n" +
                " * @ProjectName ${classCapName}\n" +
                " * @Description ${tableComment}\n" +
                " * @Date ${createDate}\n" +
                " */\n" +
                "@Data\n" +
                "@Table(name = \"${tableName}\")\n" +
                "public class ${classCapName} {\n" +
                "\t\n" +
                "#foreach ($column in $columns)\n" +
                "#if($column.attrLowName == $primaryKey.attrLowName)\n" +
                "\t/** $column.columnComment */\n" +
                "\t@Id\n" +
                "\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n" +
                "\tprivate $column.attrType $column.attrLowName;\n" +
                "#else\n" +
                "\t/** $column.columnComment */\n" +
                "\tprivate $column.attrType $column.attrLowName;\n" +
                "#end\n" +
                "#end\n" +
                "\n" +
                "}\n";
    }

}
