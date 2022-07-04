package com.euexcel.core;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EuExcelService {

    <T> void write(String fileName, List<T> dataList, String sheetName);

    <T> void write(String fileName, List<T> dataList, String sheetName, HttpServletResponse response);

    <T> List<T> read(String fileName);

}
