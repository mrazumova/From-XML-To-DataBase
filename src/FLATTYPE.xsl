<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
    <xsl:template match="/">
        <xsl:for-each select="/FlatTypes/FlatType">
            <td>
                <xsl:value-of select="concat(@FLTYPEID, ';' ,@NAME, ';', @SHORTNAME, '&#xA;')"/>
            </td>

        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>