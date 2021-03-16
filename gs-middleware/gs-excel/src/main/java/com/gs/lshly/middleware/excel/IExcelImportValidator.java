package com.gs.lshly.middleware.excel;

public interface IExcelImportValidator<T> {

    String ERROR_DATA = "excel_error_data";

    String ERROR_COUNT = "excel_error_count";

    String ERROR_MSG = "excel_error_msg";

    boolean validate(Integer rowIndex, T t);

}
