<#macro out_java_type typename>
    <#assign typeMap = {'string':'String','text':'String','int':'Integer','long':'Long',
    'number':'Long','double':'Double',
    'version':'long','EGender':'EGender',
    'doubleArray':'double[]',
    'boolean':'Boolean','date':'Date','datetime':'Date','decimal':'java.math.BigDecimal'} />
    <#t /><#if typeMap[typename]??>${typeMap[typename]}<#else>Failed to find type for "${typename}"</#if>
</#macro>
<#macro out_dto_type typename>
    <#assign typeMap = {'string':'String','text':'String','int':'Integer','long':'Long',
    'number':'Long','double':'Double',
    'version':'long','EGender':'int',
    'doubleArray':'double[]',
    'boolean':'Boolean','date':'Date','datetime':'Date','decimal':'java.math.BigDecimal'} />
    <#t /><#if typeMap[typename]??>${typeMap[typename]}<#else>Failed to find type for "${typename}"</#if>
</#macro>