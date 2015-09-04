<#macro hasTypes propList typeList>
  <#list typeList as type>
    <#assign has_var='has_type_'+type/>
    <@"<#assign ${has_var}=false>"?interpret />
    <#list propList as prop>
      <#if type == prop.type>
        <@"<#assign ${has_var}=true>"?interpret /><#break>
      </#if>
    </#list>
  </#list>
</#macro>

<#macro hasFields propList names>
  <#list propList as prop>
    <#list names as name><#assign has_var='has_field_'+name/>
    <#if name == prop.name>
      <@"<#assign ${has_var}=true>"?interpret />
    <#else>
      <@"<#assign ${has_var}=false>"?interpret />
    </#if>
    </#list>
  </#list>
</#macro>
<#function check_enum type_name>
  <#if ['EGender','EAccountType','ECardType','EPaymentOrderStatus','EThumbnailSize'
  ]?seq_contains(type_name)>
    <#return true/>
  <#else>
    <#return false />
  </#if>
</#function>
<#function slash_url str>
  <#if str[0] == '/'><#return str />
  <#else><#return '/'+str>
  </#if>
</#function>