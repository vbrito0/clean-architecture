package com.pratice.java.port.output;

public interface LogPort {
    void info(String descricao, Object... parametros);
    void debug(String descricao, Object... parametros);
    void warn(String descricao, Object... parametros);
    void error(String descricao, Object... parametros);
}
