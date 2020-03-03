package cn.weiqiubin.converter;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class intconverterBoolean extends BaseTypeHandler<Boolean> {



    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {

        if(aBoolean == true){
            preparedStatement.setInt(i,1);
        }else {

            preparedStatement.setInt(i,0);
        }
    }

    @Override
    public Boolean getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int sex = resultSet.getInt(s);

/*        if(sex == 1){
            return true;
        }else {
            return false;
        }*/

      return sex == 1 ? true : false;

    }

    @Override
    public Boolean getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex = resultSet.getInt(i);
        return sex == 1 ? true : false;
    }

    //存储过程
    @Override
    public Boolean getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex = callableStatement.getInt(i);
        return sex == 1 ? true : false;
    }


}
