package java_core_api.api_java_core.mapper.handler;

import java_core_api.api_java_core.enums.StatusNotaFiscal;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class StatusNotaFiscalTypeHandler extends BaseTypeHandler<StatusNotaFiscal> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, StatusNotaFiscal parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getCodigo());
    }

    @Override
    public StatusNotaFiscal getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int codigo = rs.getInt(columnName);
        return StatusNotaFiscal.fromCodigo(codigo);
    }

    @Override
    public StatusNotaFiscal getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int codigo = rs.getInt(columnIndex);
        return StatusNotaFiscal.fromCodigo(codigo);
    }

    @Override
    public StatusNotaFiscal getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int codigo = cs.getInt(columnIndex);
        return StatusNotaFiscal.fromCodigo(codigo);
    }
}
