package com.bkcell.security.dict.dao.sql;

public class ParamCodeSql {
    public String selectParamCodeSql(){
        return "SELECT * FROM param_code_tb ORDER BY type ASC, `code` ASC";
    }
}