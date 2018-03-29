package star.iota.swagger.specification.generator;

import java.io.*;

public class Properties {
    /**
     * 生成swagger specification 文件的路径
     */
    private static final String BUILD_PATH = "buildPath";
    /**
     * 生成swagger specification 文件的名字
     */
    private static final String FILE_NAME = "fileName";
    /**
     * 项目的名称
     */
    private static final String PROJECT_NAME = "projectName";
    /**
     * 扫描包的路径，若为空，则扫描全部包
     */
    private static final String SCAN_PATH = "scanPath";
    /**
     * 该文档的描述
     */
    private static final String API_DESC = "apiDesc";
    /**
     * api文档的版本号
     */
    private static final String API_VERSION = "apiVersion";
    /**
     * 该api文档的标题
     */
    private static final String API_TITLE = "apiTitle";
    /**
     * 主机地址
     */
    private static final String API_HOST = "apiHost";
    /**
     * 基本地址，例如：/api
     */
    private static final String API_BASE_PATH = "apiBasePath";
    /**
     * 服务条款地址
     */
    private static final String TERMS_OF_SERVICE = "termsOfService";
    /**
     * 邮箱地址
     */
    private static final String EMAIL = "email";
    /**
     * 协议名称
     */
    private static final String LICENSE_NAME = "licenseName";
    /**
     * 协议地址
     */
    private static final String LICENSE_URL = "licenseUrl";

    /**
     * 默认的请求协议
     */
    private static final String SCHEMES = "schemes";


    private java.util.Properties properties;

    private Properties() {
        this.properties = new java.util.Properties();
    }

    public Properties load(String path) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
            properties.load(new BufferedReader(new InputStreamReader(bis, "utf-8")));
            properties.load(bis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Properties load() {
        try {
            String path = System.getProperty("user.dir") + File.separator + "swagger.properties";
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
            properties.load(new BufferedReader(new InputStreamReader(bis, "utf-8")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public static Properties get() {
        return new Properties();
    }

    public String getFileName() {
        return properties.getProperty(FILE_NAME);
    }

    public String getProjectName() {
        return properties.getProperty(PROJECT_NAME);
    }

    public String getApiTitle() {
        return properties.getProperty(API_TITLE);
    }

    public String getScanPath() {
        return properties.getProperty(SCAN_PATH);
    }

    public String getApiHost() {
        return properties.getProperty(API_HOST);
    }

    public String getApiVersion() {
        return properties.getProperty(API_VERSION);
    }

    public String getApiBasePath() {
        return properties.getProperty(API_BASE_PATH);
    }

    public String getBuildPath() {
        return properties.getProperty(BUILD_PATH);
    }

    public String getApiDesc() {
        return properties.getProperty(API_DESC);
    }

    public String getEmail() {
        return properties.getProperty(EMAIL);
    }

    public String getTermsOfService() {
        return properties.getProperty(TERMS_OF_SERVICE);
    }

    public String getLicenseName() {
        return properties.getProperty(LICENSE_NAME);
    }

    public String getLicenseUrl() {
        return properties.getProperty(LICENSE_URL);
    }

    public String getSchemes() {
        return properties.getProperty(SCHEMES);
    }

}
