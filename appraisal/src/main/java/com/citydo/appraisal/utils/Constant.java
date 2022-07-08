package com.citydo.appraisal.utils;

/**
 * 常量类
 *
 * @author yangjb
 */
public class Constant {

    /**
     * 常量状态码
     *
     * @author yangjb
     */
    public interface BaseCode {

        /**
         * 成功标识
         */
        int SUCCESS_CODE = ConstantNumber.ZERO;

        /**
         * 失败标识
         */
        int FAIL_CODE = ConstantNumber.ONE;

        /**
         * 没有登录权限代码
         */
        int NO_AUTH_CODE = 3000;

        /**
         * 逻辑删除标识
         */
        int IS_DELETE = ConstantNumber.ONE;

        /**
         * 逻辑未删除标识
         */
        int IS_NOT_DELETE = ConstantNumber.ZERO;

        /**
         * 英文逗号
         */
        String COMMA = ",";

        /**
         * 制表符
         */
        String TAB = "\t";

        /**
         * 点
         */
        String SPOT = ".";

        /**
         * 冒号
         */
        String COLON = ":";

        String DEV = "dev";
    }

    /**
     * 常量数字
     *
     * @author yangjb
     */
    public interface ConstantNumber {
        int ZERO = 0;
        int ONE = 1;
        int TWO = 2;
        int THREE = 3;
        int FOUR = 4;
        int FIVE = 5;
        int SIX = 6;
        int SEVEN = 7;
        int EIGHT = 8;
        int NINE = 9;
        int TEN = 10;

        int TWELVE = 12;
        int SIXTEEN = 16;
        int NEGATIVE_ONE = -1;
        int TEN_THOUSAND = 10000;
        int TWO_THOUSAND = 1500;
    }

    /**
     * 文件后缀
     *
     * @author yangjb
     */
    public interface FileSuffix {
        /**
         * Word2003版本
         */
        String TXT = "txt";
        /**
         * Word2007版本
         */
        String DAT = "dat";
    }

    public interface ConstantVlidation {

        /**
         * Vlidation校验失败
         *
         * @author yangjb
         */
        String CONVERT_FAILED = "Failed to convert property value of type";
    }

    public interface Training {
        /**
         * 重试次数
         */
        int RETRY_TIMES = 3;
    }

    /**
     * 数据字典常量类
     *
     * @author yangjb
     */
    public interface Dict {
        /**
         * 数据库大类型
         */
        String TYPE_CODE_DB_SOURCE_TYPE = "dbSourceType";

        /**
         * 数据库小类型
         */
        String TYPE_CODE_DB_TYPE = "dbType";

        /**
         * 过滤Schema
         */
        String TYPE_CODE_SCHEMA_FILTER = "schemaFilter";
    }

    /**
     * ER 数据源类型
     *
     * @author yangjb
     */
    public interface Er {
        /**
         * MYSQL
         */
        String DB_MYSQL = "mysql";

        /**
         * MYSQL
         */
        String DB_ORACLE = "oracle";

        /**
         * ODPS
         */
        String DB_ODPS = "odps";
    }

    /**
     * 批量识别常量
     *
     * @author yangjb
     */
    public interface Exploration {
        /**
         * 正在运行
         */
        String EX_RUNNING_CODE = "1000";

        /**
         * 确认使用状态
         */
        String EX_CONFIRM_USE_CODE = "3000";

        /**
         * 保存状态
         */
        String EX_SAVE_CODE = "2000";

        /**
         * 运行失败（包含调用失败）
         */
        String EX_FAIL_CODE = "9999";
        /**
         * 运行成功
         */
        String EX_SUCCESS_CODE = "0000";

        /**
         * 标准标签表
         */
        String TRAINING_BZ_LABEL = "bz_label";

        /**
         * 标准字段标签关系表
         */
        String TRAINING_BZ_COLUMN_LABEL_MAPPING = "bz_column_label_mapping";

        /**
         * 标准字段视图
         */
        String EX_V_BZ_COLUMN = "v_bz_table_without_label";

        /**
         * 标准训练ID前缀
         */
        String BZ_TRAINING_ID_PREFIX = "BZ";

        /**
         * 批量识别ID前缀
         */
        String EXPLORATION_ID_PREFIX = "EX";

        /**
         * 识别分隔符
         */
        String EX_TABLE_COLUMN_SEPARATOR = "@";
        /**
         * 有识别结果
         */
        String IS_MATCH = "1";

        /**
         * 没有识别到结果
         */
        String IS_NOT_MATCH = "2";

        /**
         * 识别关系-确认
         */
        String MAPPING_STATUS_CONFIRM_1 = "1";

        /**
         * 识别关系-待定
         */
        String MAPPING_STATUS_UNDETERMINED_2 = "2";

        /**
         * 识别关系-废弃
         */
        String MAPPING_STATUS_DISCARD_3 = "3";

        /**
         * odps数据源类型
         */
        String DATASOURCE_TYPE_OF_ODPS = "4";

        /**
         * 关系型数据库
         */
        String DATASOURCE_TYPE_OF_RELATIONAL = "1";

        String EXPLORATION_LIB_ID_TYPECODE = "exploration";
        String EXPLORATION_LIB_ID_CODE = "knowledgeLibId";
    }


    /**
     * 数据发现常量
     *
     * @author yangjb
     */
    public interface DFind {

        /**
         * 是否上传类型 1是2否
         */
        String IS_UPLOAD = "1";
        /**
         * 是否上传类型 1是2否
         */
        String NOT_UPLOAD = "2";
        /**
         * 普通cube
         */
        String DATA_CUBE_TYPE_1 = "1";
        /**
         * 跑批cube 年
         */
        String DATA_CUBE_TYPE_2 = "2";
        /**
         * 跑批cube 月
         */
        String DATA_CUBE_TYPE_3 = "3";
        /**
         * 跑批cube 周
         */
        String DATA_CUBE_TYPE_4 = "4";
        /**
         * 跑批cube 日
         */
        String DATA_CUBE_TYPE_5 = "5";

        /**
         * 普通二维数据文件
         */
        String DATA_TYPE_1 = "1";
        /**
         * 跑批二维数据文件 年
         */
        String DATA_TYPE_2 = "2";
        /**
         * 跑批二维数据文件 月
         */
        String DATA_TYPE_3 = "3";
        /**
         * 跑批二维数据文件 周
         */
        String DATA_TYPE_4 = "4";
        /**
         * 跑批二维数据文件 日
         */
        String DATA_TYPE_5 = "5";

    }

    /**
     * 城市操作系统常量
     */
    public interface CityOS {

        /**
         * 快捷方式标识 1是2否
         */
        String IS_SHORTCUT = "1";

        /**
         * 快捷方式标识 1是2否
         */
        String NOT_SHORTCUT = "2";

        /**
         * 桌面标识 1是2否
         */
        Integer IS_DESKTOP = 1;
        /**
         * 桌面标识 1是2否
         */
        Integer NOT_DESKTOP = 2;

        /**
         * 删除级别 0正常 1一级删除可还原 2二级删除不可还原
         */
        String NOT_DELETE = "0";
        /**
         * 删除级别 0正常 1一级删除可还原 2二级删除不可还原
         */
        String FIRST_DELETE = "1";
        /**
         * 删除级别 0正常 1一级删除可还原 2二级删除不可还原
         */
        String SECOND_DELETE = "2";

        /**
         * 系统文件标识 1是2否
         */
        String IS_SYSTEM_DEFAULT = "1";
        /**
         * 系统文件标识 1是2否
         */
        String NOT_SYSTEM_DEFAULT = "2";

        /**
         * 文件类型 1/3/4 都为文件夹类型 1普通文件夹 3回收站 4我的文件夹 2数据文件类型
         */
        String DIR = "1";
        /**
         * 文件类型 1/3/4 都为文件夹类型 1普通文件夹 3回收站 4我的文件夹 2数据文件类型
         */
        String DATA = "2";
        /**
         * 文件类型 1/3/4 都为文件夹类型 1普通文件夹 3回收站 4我的文件夹 2数据文件类型
         */
        String RECYCLE = "3";
        /**
         * 文件类型 1/3/4 都为文件夹类型 1普通文件夹 3回收站 4我的文件夹 2数据文件类型
         */
        String MY_DIR = "4";
        /**
         * 多维数据文件
         */
        String DATA_CUBE = "5";
        /**
         * 码表文件夹类型
         */
        String DIC_DIR = "6";
        /**
         * 码表文件类型
         */
        String DIC_FILE = "7";

        /**
         * 使用默认名称创建新文件夹和快捷方式的最大数量;
         */
        int MAX_NEW_SHORTCUT_DIR_NUM = 1;
        /**
         * 使用默认名称创建新文件夹和快捷方式的最大数量;
         */
        int MAX_NEW_DIR_NUM = 20;
        /**
         * 是否只查询文件夹类型
         */
        String ONLY_DIRS_FLAG = "1";

        /**
         * 应用程序文件类型
         */
        String APP_FILE = "10";


        String IS_EMPTY = "1";
        String NOT_EMPTY = "2";
        String IS_READ = "1";
        String NOT_READ = "2";

        String UPLOAD_THREAD_NAME = "WINDOW_UPLOAD_FILE_";

        String DEFAULT_PIC = "https://cityos-system.oss-cn-hangzhou.aliyuncs.com/desktop_icon_normal.png";
    }
}
