package com.bkcell.security.dict.dao.sql;

public class ParamValueSql {
    public String selectParamValueSql(){
        return "SELECT * FROM param_value_tb ORDER BY `Name` ASC ";
    }
}