<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >
    <xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
    <xsl:template match="/">
        <xsl:for-each select="/AddressObjects/Object">
            <td>
                <xsl:value-of select="concat(@AOID, ';' ,@AOGUID, ';' ,@FORMALNAME, ';' ,@OFFNAME, ';' ,@SHORTNAME, ';' ,@AOLEVEL,
                  ';' ,@REGIONCODE, ';' ,@AREACODE, ';' ,@AUTOCODE, ';' ,@CITYCODE, ';' ,@CTARCODE, ';' ,@PLACECODE, ';' ,@PLANCODE,
                  ';' ,@STREETCODE, ';' ,@EXTRCODE, ';' ,@SEXTCODE, ';' ,@PLAINCODE, ';' ,@CODE, ';' ,@CURRSTATUS, ';' ,@ACTSTATUS,
                  ';' ,@LIVESTATUS, ';' ,@CENTSTATUS, ';' ,@OPERSTATUS, ',' ,@IFNSFL, ';' ,@IFNSUL, ';' ,@OKATO, ';' ,@POSTALCODE,
                  ';', @STARTDATE, ';', @ENDDATE, ';', @UPDATEDATE, ';', @DIVTYPE, '&#xA;')"/>
            </td>

        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>