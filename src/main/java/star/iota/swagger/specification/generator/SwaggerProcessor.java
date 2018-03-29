package star.iota.swagger.specification.generator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.gson.Gson;
import star.iota.swagger.specification.generator.base.*;
import star.iota.swagger.specification.generator.core.*;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

public class SwaggerProcessor extends AbstractProcessor {

    /**
     * init()方法会被注解处理工具调用，并输入ProcessingEnvironment参数。
     * ProcessingEnvironment提供很多有用的工具类Elements, Types 和 Filer
     *
     * @param processingEnv 提供给 processor 用来访问工具框架的环境
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    /**
     * 这相当于每个处理器的主函数main()，你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件。
     * 输入参数RoundEnvironment，可以让你查询出包含特定注解的被注解元素
     *
     * @param annotations 请求处理的注解类型
     * @param roundEnv    有关当前和以前的信息环境
     * @return 如果返回 true，则这些注解已声明并且不要求后续 Processor 处理它们；
     * 如果返回 false，则这些注解未声明并且可能要求后续 Processor 处理它们
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println(roundEnv.getRootElements());
        System.out.println("=================================================================");
        System.out.println("生成开始>>>");
        Properties properties = Properties.get().load();
        String buildPath = properties.getBuildPath();
        if (isBlank(buildPath)) {
            System.out.println("未生成文件，路径为空");
            return false;
        }
        String filePath = buildPath + "\\"
                + properties.getFileName()
                .replaceAll("\\$\\{projectName}", properties.getProjectName())
                .replaceAll("\\$\\{apiVersion}", properties.getApiVersion())
                .replaceAll("\\$\\{apiTitle}", properties.getApiTitle());
        System.out.println("生成文件路径：" + filePath + ".json|yaml");
        Map<String, Object> specification = new LinkedHashMap<String, Object>();
        bindProperties(properties, specification);
        Map<String, Object> path = new LinkedHashMap<String, Object>();
        Set<LinkedHashMap<String, Object>> tagsDesc = new LinkedHashSet<LinkedHashMap<String, Object>>();
        for (TypeElement typeElement : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(typeElement)) {
                Api api = element.getAnnotation(Api.class);
                if (api.hide()) continue;
                Map<String, Object> method = new LinkedHashMap<String, Object>();
                Map<String, Object> params = new LinkedHashMap<String, Object>();
                setTag(api, params);
                setTags(tagsDesc, api);
                setSummary(api, params);
                setDescription(api, params);
                setApiExternalDocs(api, params);
                setOperationId(api, params);
                setProduces(api, params);
                setConsumers(api, params);
                setParameters(api, params);
                setSchemes(api, params);
                setResponses(api, params);
                setSecurity(api, params);
                isDeprecated(api, params);
                method.put(api.method().method(), params);
                path.put(api.url(), method);
                specification.put("tags", tagsDesc);
                specification.put("paths", path);
                createJsonAndYaml(specification, filePath);
            }
        }
        System.out.println("生成结束<<<");
        System.out.println("=================================================================");
        return false;
    }

    private void isDeprecated(Api api, Map<String, Object> params) {
        boolean deprecated = api.deprecated();
        if (deprecated) {
            params.put("deprecated", true);
        }
    }

    private void setApiExternalDocs(Api api, Map<String, Object> params) {
        ExtDoc extDoc = api.extDocs();
        if (!isBlank(extDoc.desc()) || !isBlank(extDoc.url())) {
            Map<String, String> doc = new HashMap<String, String>();
            doc.put("description", extDoc.desc());
            doc.put("url", extDoc.url());
            params.put("externalDocs", doc);
        }
    }

    private void setTag(Api api, Map<String, Object> params) {
        String[] tags = api.tags();
        if (tags.length > 0) {
            params.put("tags", tags);
        }
    }

    private void setTags(Set<LinkedHashMap<String, Object>> tagsDesc, Api api) {
        TagDesc[] tagDescs = api.tagsDesc();
        if (tagDescs.length > 0) {
            for (TagDesc tagDesc : tagDescs) {
                LinkedHashMap<String, Object> d = new LinkedHashMap<String, Object>();
                d.put("name", tagDesc.name());
                d.put("description", tagDesc.desc());
                tagsDesc.add(d);
            }
        }
    }

    private void setSummary(Api api, Map<String, Object> params) {
        String summary = api.summary();
        if (!isBlank(summary)) {
            params.put("summary", summary);
        }
    }

    private void setDescription(Api api, Map<String, Object> params) {
        StringBuilder desc = new StringBuilder(api.desc());
        String[] additional = api.additional();
        if (additional.length > 0) {
            desc.append("\n");
            for (String s : additional) {
                desc.append("\n").append(s);
            }
        }
        params.put("description", desc.toString());
    }

    private void setOperationId(Api api, Map<String, Object> params) {
        String operationId = api.operationId();
        if (operationId.length() > 0) {
            params.put("operationId", operationId);
        }
    }

    private void setProduces(Api api, Map<String, Object> params) {
        MIME[] producesArr = api.produces();
        if (producesArr.length > 0) {
            Set<String> produces = new LinkedHashSet<String>();
            for (MIME mime : producesArr) {
                produces.add(mime.mime());
            }
            params.put("produces", produces);
        }
    }

    private void setConsumers(Api api, Map<String, Object> params) {
        MIME[] consumesArr = api.consumes();
        Set<String> consumes = new LinkedHashSet<String>();
        if (consumesArr.length > 0) {
            for (MIME mime : consumesArr) {
                consumes.add(mime.mime());
            }
            params.put("consumes", consumes);
        }
    }

    private void setParameters(Api api, Map<String, Object> params) {
        Param[] parametersArr = api.parameters();
        if (parametersArr.length > 0) {
            Set<LinkedHashMap<String, Object>> pa = new LinkedHashSet<LinkedHashMap<String, Object>>();
            for (Param parameter : parametersArr) {
                LinkedHashMap<String, Object> p = new LinkedHashMap<String, Object>();
                p.put("name", parameter.name());
                p.put("in", parameter.in().param());
                p.put("description", parameter.desc());
                p.put("required", parameter.required());
                CollectionFormat collectionFormat = parameter.collectionFormat();
                if (collectionFormat != CollectionFormat.CSV) {
                    p.put("collectionFormat", collectionFormat.format());
                }
                if (parameter.in() == In.BODY) {
                    Schema schema = parameter.schema();
                    Map<String, String> s = new HashMap<String, String>();
                    s.put("$ref", "#/definitions/" + schema.$ref());
                    p.put("schema", s);
                } else {
                    boolean empty = parameter.empty();
                    if (empty) {
                        p.put("allowEmptyValue", true);
                    }
                    DataType dataType = parameter.type();
                    if (dataType == DataType.ARRAY) {
                        Items items = parameter.items();
                        Map<String, Object> its = new LinkedHashMap<String, Object>();
                        DataType type = items.type();
                        its.put("type", type.type());
                        its.put("format", type.format());
                        String[] $enum = items.$enum();
                        if ($enum.length > 0) {
                            switch (type) {
                                case INTEGER:
                                    List<Integer> integers = new ArrayList<Integer>();
                                    for (String s : $enum) {
                                        integers.add(Integer.parseInt(s));
                                    }
                                    its.put("enum", integers);
                                    break;
                                case LONG:
                                    List<Long> longs = new ArrayList<Long>();
                                    for (String s : $enum) {
                                        longs.add(Long.parseLong(s));
                                    }
                                    its.put("enum", longs);
                                    break;
                                case FLOAT:
                                    List<Float> floats = new ArrayList<Float>();
                                    for (String s : $enum) {
                                        floats.add(Float.parseFloat(s));
                                    }
                                    its.put("enum", floats);
                                    break;
                                case DOUBLE:
                                    List<Double> doubles = new ArrayList<Double>();
                                    for (String s : $enum) {
                                        doubles.add(Double.parseDouble(s));
                                    }
                                    its.put("enum", doubles);
                                    break;
                                case BOOLEAN:
                                    List<Boolean> booleans = new ArrayList<Boolean>();
                                    for (String s : $enum) {
                                        booleans.add(Boolean.parseBoolean(s));
                                    }
                                    its.put("enum", booleans);
                                    break;
                                default:
                                    its.put("enum", $enum);
                            }
                        }
                        String def = items.def();
                        if (!isBlank(def)) {
                            its.put("default", def);
                        }
                        CollectionFormat format = items.collectionFormat();
                        if (format != CollectionFormat.CSV) {
                            its.put("collectionFormat", format.format());
                        }
                        p.put("items", its);
                    } else if (dataType == DataType.FILE) {
                        p.put("type", dataType.type());
                    } else {
                        p.put("type", dataType.type());
                        p.put("format", dataType.format());
                        String defaultVal = parameter.def();
                        if (!isBlank(defaultVal)) {
                            p.put("default", defaultVal);
                        }
                    }
                }
                pa.add(p);
            }
            params.put("parameters", pa);
        }
    }

    private void setSchemes(Api api, Map<String, Object> params) {
        Scheme[] schemesArr = api.schemes();
        if (schemesArr.length > 0) {
            Set<String> schemes = new LinkedHashSet<String>();
            for (Scheme s : schemesArr) {
                schemes.add(s.scheme());
            }
            params.put("schemes", schemes);
        }
    }

    private void setResponses(Api api, Map<String, Object> params) {
        Resp[] responsesArr = api.responses();
        if (responsesArr.length > 0) {
            Map<String, Object> responses = new LinkedHashMap<String, Object>();
            for (Resp response : responsesArr) {
                Map<String, Object> p = new LinkedHashMap<String, Object>();
                p.put("description", response.desc());
                responses.put(response.code(), p);
            }
            params.put("responses", responses);
        }
    }

    private void setSecurity(Api api, Map<String, Object> params) {
        Sec[] securityArr = api.security();
        if (securityArr.length > 0) {
            Set<LinkedHashMap<String, Object>> security = new LinkedHashSet<LinkedHashMap<String, Object>>();
            for (Sec sec : securityArr) {
                LinkedHashMap<String, Object> i = new LinkedHashMap<String, Object>();
                Set<String> s = new LinkedHashSet<String>(Arrays.asList(sec.values()));
                i.put(sec.key(), s);
                security.add(i);
            }
            params.put("security", security);
        }
    }

    private void createJsonAndYaml(Map<String, Object> spec, String filePath) {
        String jsonString = new Gson().toJson(spec);
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath + ".json"), "UTF-8");
            osw.write(jsonString);
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JsonNode jsonNodeTree = new ObjectMapper().readTree(jsonString);
            String yaml = new YAMLMapper().writeValueAsString(jsonNodeTree);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath + ".yaml"), "UTF-8");
            osw.write(yaml);
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void bindProperties(Properties properties, Map<String, Object> spec) {
        spec.put("swagger", "2.0");
        LinkedHashMap<String, Object> info = new LinkedHashMap<String, Object>();
        String apiDesc = properties.getApiDesc();
        if (!isBlank(apiDesc)) info.put("description", apiDesc);
        String apiVersion = properties.getApiVersion();
        if (!isBlank(apiVersion)) info.put("version", apiVersion);
        String apiTitle = properties.getApiTitle();
        if (!isBlank(apiTitle)) info.put("title", apiTitle);
        String termsOfService = properties.getTermsOfService();
        if (!isBlank(termsOfService)) info.put("termsOfService", termsOfService);
        String licenseName = properties.getLicenseName();
        String licenseUrl = properties.getLicenseUrl();
        if (!isBlank(licenseName) || !isBlank(licenseUrl)) {
            Map<String, String> license = new LinkedHashMap<String, String>();
            if (!isBlank(licenseName)) license.put("name", licenseName);
            if (!isBlank(licenseUrl)) license.put("url", licenseUrl);
            info.put("license", license);
        }
        String email = properties.getEmail();
        if (!isBlank(email)) {
            HashMap<String, String> e = new HashMap<String, String>();
            e.put("email", email);
            info.put("contact", e);
        }
        spec.put("info", info);
        String apiHost = properties.getApiHost();
        if (!isBlank(apiHost)) spec.put("host", apiHost);
        String apiBasePath = properties.getApiBasePath();
        if (!isBlank(apiBasePath)) spec.put("basePath", apiBasePath);
        String scheme = properties.getSchemes();
        if (!isBlank(scheme)) {
            String[] schemesArr = scheme.split(",");
            Set<String> schemes = new HashSet<String>(Arrays.asList(schemesArr));
            spec.put("schemes", schemes);
        }
    }

    private boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称
     *
     * @return 注解器所支持的注解类型集合，如果没有这样的类型，则返回一个空集合
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<String>();
        annotations.add(Api.class.getCanonicalName());
        return annotations;
    }

    /**
     * 指定使用的Java版本，通常这里返回SourceVersion.latestSupported()，默认返回SourceVersion.RELEASE_6
     *
     * @return 使用的Java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
