package com.randioo.config.randioo_excel.po;

import org.dom4j.Element;

public class PackageConfig {
    public String packName;

    public PackageConfig(Element element) {
        packName = element.attributeValue("package");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PackageConfig [packName=").append(packName).append("]");
        return builder.toString();
    }

}
