package com.iokays.work.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "localhost_industrial_enterprise_all_text_2")
public class Enterprise implements Serializable {

    @Id
    @Field(type = FieldType.Text)
    private String eid;

    @Field(type = FieldType.Text)
    private String format_name;
    @Field(type = FieldType.Text)
    private String regist_capi;
    @Field(type = FieldType.Text)
    private String actual_capi;
    @Field(type = FieldType.Text)
    private String scope;
    @Field(type = FieldType.Text)
    private String term_start;
    @Field(type = FieldType.Text)
    private String term_end;
    @Field(type = FieldType.Text)
    private String check_date;
    @Field(type = FieldType.Text)
    private String belong_org;
    @Field(type = FieldType.Text)
    private String oper_name;
    @Field(type = FieldType.Text)
    private String oper_type;
    @Field(type = FieldType.Text)
    private String oper_name_id;
    @Field(type = FieldType.Text)
    private String status;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String longitude;
    @Field(type = FieldType.Text)
    private String latitude;
    @Field(type = FieldType.Text)
    private String gd_longitude;
    @Field(type = FieldType.Text)
    private String gd_latitude;
    @Field(type = FieldType.Text)
    private String collegues_num;
    @Field(type = FieldType.Text)
    private String created_time;
    @Field(type = FieldType.Text)
    private String logo_url;
    @Field(type = FieldType.Text)
    private String econ_type;
    @Field(type = FieldType.Text)
    private String department;
    @Field(type = FieldType.Text)
    private String url;
    @Field(type = FieldType.Text)
    private String title_code;
    @Field(type = FieldType.Text)
    private String regist_capi_new;
    @Field(type = FieldType.Text)
    private String currency_unit;
    private String revoke_reason;
    @Field(type = FieldType.Text)
    private String revoke_date;
    @Field(type = FieldType.Text)
    private String logout_reason;
    @Field(type = FieldType.Text)
    private String logout_date;
    @Field(type = FieldType.Text)
    private String revoked_certificates;
    @Field(type = FieldType.Text)
    private String new_status_code;
    @Field(type = FieldType.Text)
    private String type_new;
    @Field(type = FieldType.Text)
    private String category_new;
    @Field(type = FieldType.Text)
    private String reg_no;
    @Field(type = FieldType.Text)
    private String credit_no;
    @Field(type = FieldType.Text)
    private String org_no;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String start_date;
    @Field(type = FieldType.Text)
    private String province_code;
    @Field(type = FieldType.Text)
    private String district_code;
    @Field(type = FieldType.Text)
    private String city_code;
    @Field(type = FieldType.Text)
    private String area_code;
    @Field(type = FieldType.Text)
    private String province_name;
    @Field(type = FieldType.Text)
    private String city_name;
    @Field(type = FieldType.Text)
    private String area_name;
    @Field(type = FieldType.Text)
    private String telephone;
    private String web_url;
    @Field(type = FieldType.Text)
    private String email;
    @Field(type = FieldType.Text)
    private String address;
    @Field(type = FieldType.Text)
    private String had_branches;
    @Field(type = FieldType.Text)
    private String had_change;
    @Field(type = FieldType.Text)
    private String clear_ccount;
    @Field(type = FieldType.Text)
    private String employees_count;
    @Field(type = FieldType.Text)
    private String partners_count;
    @Field(type = FieldType.Text)
    private String is_cancellation;
    @Field(type = FieldType.Text)
    private String social_security_count;
    @Field(type = FieldType.Text)
    private String industry_code;
    @Field(type = FieldType.Text)
    private String industry_name;
    @Field(type = FieldType.Text)
    private String econ_kind_code;
    @Field(type = FieldType.Text)
    private String econ_name;
    private String had_serious_illegal;
    @Field(type = FieldType.Text)
    private String had_judicial_freezes;
    @Field(type = FieldType.Text)
    private String had_enterprise_investment;
    @Field(type = FieldType.Text)
    private String had_knowledge_properties;
    @Field(type = FieldType.Text)
    private String had_mortgages;
    @Field(type = FieldType.Text)
    private String had_equityquality;
    @Field(type = FieldType.Text)
    private String had_abnormal;
    @Field(type = FieldType.Text)
    private String had_administrative_punishment;
    @Field(type = FieldType.Text)
    private String had_license;
    @Field(type = FieldType.Text)
    private String had_checkups;
    @Field(type = FieldType.Text)
    private String had_double_checkups;
    @Field(type = FieldType.Text)
    private String tag_1;
    @Field(type = FieldType.Text)
    private String tag_2;
    @Field(type = FieldType.Text)
    private String tag_3;
    @Field(type = FieldType.Text)
    private String tag_4;
    @Field(type = FieldType.Text)
    private String tag_5;
    @Field(type = FieldType.Text)
    private String tag_6;
    @Field(type = FieldType.Text)
    private String tag_7;
    @Field(type = FieldType.Text)
    private String tag_8;
    @Field(type = FieldType.Text)
    private String tag_9;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getFormat_name() {
        return format_name;
    }

    public void setFormat_name(String format_name) {
        this.format_name = format_name;
    }

    public String getRegist_capi() {
        return regist_capi;
    }

    public void setRegist_capi(String regist_capi) {
        this.regist_capi = regist_capi;
    }

    public String getActual_capi() {
        return actual_capi;
    }

    public void setActual_capi(String actual_capi) {
        this.actual_capi = actual_capi;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTerm_start() {
        return term_start;
    }

    public void setTerm_start(String term_start) {
        this.term_start = term_start;
    }

    public String getTerm_end() {
        return term_end;
    }

    public void setTerm_end(String term_end) {
        this.term_end = term_end;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public String getBelong_org() {
        return belong_org;
    }

    public void setBelong_org(String belong_org) {
        this.belong_org = belong_org;
    }

    public String getOper_name() {
        return oper_name;
    }

    public void setOper_name(String oper_name) {
        this.oper_name = oper_name;
    }

    public String getOper_type() {
        return oper_type;
    }

    public void setOper_type(String oper_type) {
        this.oper_type = oper_type;
    }

    public String getOper_name_id() {
        return oper_name_id;
    }

    public void setOper_name_id(String oper_name_id) {
        this.oper_name_id = oper_name_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getGd_longitude() {
        return gd_longitude;
    }

    public void setGd_longitude(String gd_longitude) {
        this.gd_longitude = gd_longitude;
    }

    public String getGd_latitude() {
        return gd_latitude;
    }

    public void setGd_latitude(String gd_latitude) {
        this.gd_latitude = gd_latitude;
    }

    public String getCollegues_num() {
        return collegues_num;
    }

    public void setCollegues_num(String collegues_num) {
        this.collegues_num = collegues_num;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getEcon_type() {
        return econ_type;
    }

    public void setEcon_type(String econ_type) {
        this.econ_type = econ_type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle_code() {
        return title_code;
    }

    public void setTitle_code(String title_code) {
        this.title_code = title_code;
    }

    public String getRegist_capi_new() {
        return regist_capi_new;
    }

    public void setRegist_capi_new(String regist_capi_new) {
        this.regist_capi_new = regist_capi_new;
    }

    public String getCurrency_unit() {
        return currency_unit;
    }

    public void setCurrency_unit(String currency_unit) {
        this.currency_unit = currency_unit;
    }

    public String getRevoke_reason() {
        return revoke_reason;
    }

    public void setRevoke_reason(String revoke_reason) {
        this.revoke_reason = revoke_reason;
    }

    public String getRevoke_date() {
        return revoke_date;
    }

    public void setRevoke_date(String revoke_date) {
        this.revoke_date = revoke_date;
    }

    public String getLogout_reason() {
        return logout_reason;
    }

    public void setLogout_reason(String logout_reason) {
        this.logout_reason = logout_reason;
    }

    public String getLogout_date() {
        return logout_date;
    }

    public void setLogout_date(String logout_date) {
        this.logout_date = logout_date;
    }

    public String getRevoked_certificates() {
        return revoked_certificates;
    }

    public void setRevoked_certificates(String revoked_certificates) {
        this.revoked_certificates = revoked_certificates;
    }

    public String getNew_status_code() {
        return new_status_code;
    }

    public void setNew_status_code(String new_status_code) {
        this.new_status_code = new_status_code;
    }

    public String getType_new() {
        return type_new;
    }

    public void setType_new(String type_new) {
        this.type_new = type_new;
    }

    public String getCategory_new() {
        return category_new;
    }

    public void setCategory_new(String category_new) {
        this.category_new = category_new;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getCredit_no() {
        return credit_no;
    }

    public void setCredit_no(String credit_no) {
        this.credit_no = credit_no;
    }

    public String getOrg_no() {
        return org_no;
    }

    public void setOrg_no(String org_no) {
        this.org_no = org_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHad_branches() {
        return had_branches;
    }

    public void setHad_branches(String had_branches) {
        this.had_branches = had_branches;
    }

    public String getHad_change() {
        return had_change;
    }

    public void setHad_change(String had_change) {
        this.had_change = had_change;
    }

    public String getClear_ccount() {
        return clear_ccount;
    }

    public void setClear_ccount(String clear_ccount) {
        this.clear_ccount = clear_ccount;
    }

    public String getEmployees_count() {
        return employees_count;
    }

    public void setEmployees_count(String employees_count) {
        this.employees_count = employees_count;
    }

    public String getPartners_count() {
        return partners_count;
    }

    public void setPartners_count(String partners_count) {
        this.partners_count = partners_count;
    }

    public String getIs_cancellation() {
        return is_cancellation;
    }

    public void setIs_cancellation(String is_cancellation) {
        this.is_cancellation = is_cancellation;
    }

    public String getSocial_security_count() {
        return social_security_count;
    }

    public void setSocial_security_count(String social_security_count) {
        this.social_security_count = social_security_count;
    }

    public String getIndustry_code() {
        return industry_code;
    }

    public void setIndustry_code(String industry_code) {
        this.industry_code = industry_code;
    }

    public String getIndustry_name() {
        return industry_name;
    }

    public void setIndustry_name(String industry_name) {
        this.industry_name = industry_name;
    }

    public String getEcon_kind_code() {
        return econ_kind_code;
    }

    public void setEcon_kind_code(String econ_kind_code) {
        this.econ_kind_code = econ_kind_code;
    }

    public String getEcon_name() {
        return econ_name;
    }

    public void setEcon_name(String econ_name) {
        this.econ_name = econ_name;
    }

    public String getHad_serious_illegal() {
        return had_serious_illegal;
    }

    public void setHad_serious_illegal(String had_serious_illegal) {
        this.had_serious_illegal = had_serious_illegal;
    }

    public String getHad_judicial_freezes() {
        return had_judicial_freezes;
    }

    public void setHad_judicial_freezes(String had_judicial_freezes) {
        this.had_judicial_freezes = had_judicial_freezes;
    }

    public String getHad_enterprise_investment() {
        return had_enterprise_investment;
    }

    public void setHad_enterprise_investment(String had_enterprise_investment) {
        this.had_enterprise_investment = had_enterprise_investment;
    }

    public String getHad_knowledge_properties() {
        return had_knowledge_properties;
    }

    public void setHad_knowledge_properties(String had_knowledge_properties) {
        this.had_knowledge_properties = had_knowledge_properties;
    }

    public String getHad_mortgages() {
        return had_mortgages;
    }

    public void setHad_mortgages(String had_mortgages) {
        this.had_mortgages = had_mortgages;
    }

    public String getHad_equityquality() {
        return had_equityquality;
    }

    public void setHad_equityquality(String had_equityquality) {
        this.had_equityquality = had_equityquality;
    }

    public String getHad_abnormal() {
        return had_abnormal;
    }

    public void setHad_abnormal(String had_abnormal) {
        this.had_abnormal = had_abnormal;
    }

    public String getHad_administrative_punishment() {
        return had_administrative_punishment;
    }

    public void setHad_administrative_punishment(String had_administrative_punishment) {
        this.had_administrative_punishment = had_administrative_punishment;
    }

    public String getHad_license() {
        return had_license;
    }

    public void setHad_license(String had_license) {
        this.had_license = had_license;
    }

    public String getHad_checkups() {
        return had_checkups;
    }

    public void setHad_checkups(String had_checkups) {
        this.had_checkups = had_checkups;
    }

    public String getHad_double_checkups() {
        return had_double_checkups;
    }

    public void setHad_double_checkups(String had_double_checkups) {
        this.had_double_checkups = had_double_checkups;
    }

    public String getTag_1() {
        return tag_1;
    }

    public void setTag_1(String tag_1) {
        this.tag_1 = tag_1;
    }

    public String getTag_2() {
        return tag_2;
    }

    public void setTag_2(String tag_2) {
        this.tag_2 = tag_2;
    }

    public String getTag_3() {
        return tag_3;
    }

    public void setTag_3(String tag_3) {
        this.tag_3 = tag_3;
    }

    public String getTag_4() {
        return tag_4;
    }

    public void setTag_4(String tag_4) {
        this.tag_4 = tag_4;
    }

    public String getTag_5() {
        return tag_5;
    }

    public void setTag_5(String tag_5) {
        this.tag_5 = tag_5;
    }

    public String getTag_6() {
        return tag_6;
    }

    public void setTag_6(String tag_6) {
        this.tag_6 = tag_6;
    }

    public String getTag_7() {
        return tag_7;
    }

    public void setTag_7(String tag_7) {
        this.tag_7 = tag_7;
    }

    public String getTag_8() {
        return tag_8;
    }

    public void setTag_8(String tag_8) {
        this.tag_8 = tag_8;
    }

    public String getTag_9() {
        return tag_9;
    }

    public void setTag_9(String tag_9) {
        this.tag_9 = tag_9;
    }
}

