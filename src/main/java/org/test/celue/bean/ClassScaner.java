package org.test.celue.bean;

import org.assertj.core.util.Arrays;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
public class ClassScaner implements ResourceLoaderAware {

    private final List<TypeFilter> includeFilters = new LinkedList<>();
    private final List<TypeFilter> excludeFilters = new LinkedList<>();

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);

    public static Set<Class<?>> scan(String basePackage,Class<? extends Annotation>... annotations){
        return ClassScaner.scan(StringUtils.tokenizeToStringArray(basePackage,",;\t\n"),annotations);
    }

    public static Set<Class<?>> scan(String[] basePackages, Class<? extends Annotation>... annotation) {
        ClassScaner cs = new ClassScaner();

        if (!Arrays.isNullOrEmpty(annotation)) {
            for (Class anno : annotation) {
                cs.addIncludeFilter(new AnnotationTypeFilter(anno));
            }
        }

        Set<Class<?>> classes = new HashSet<>();
        for (String s : basePackages) {
            classes.addAll(cs.doScan(s));
        }

        return classes;
    }

    private Collection<Class<?>> doScan(String basePackage) {
        Set<Class<?>> classes = new HashSet<>();
        try {
            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + org.springframework.util.ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage)) + "/**/*.class";
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (int i = 0; i < resources.length; i++) {
                Resource resource = resources[i];
                MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                if ((includeFilters.size() == 0 && excludeFilters.size() == 0) || matchs(metadataReader)) {
                    try {
                        classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    protected  boolean matchs(MetadataReader metadataReader) throws IOException {
        for(TypeFilter tf : this.excludeFilters){
            if(tf.match(metadataReader,this.metadataReaderFactory)){
                return false;
            }
        }
        for(TypeFilter tf : this.includeFilters){
            if(tf.match(metadataReader,this.metadataReaderFactory)){
                return true;
            }
        }
        return false;
    }

    private void addIncludeFilter(AnnotationTypeFilter annotationTypeFilter) {
        this.includeFilters.add(annotationTypeFilter);
    }

    public void addExcludeFilter(AnnotationTypeFilter annotationTypeFilter){
        this.excludeFilters.add(annotationTypeFilter);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

    public void resetFilter(boolean useDefaultFilters){
        this.includeFilters.clear();
        this.excludeFilters.clear();
    }
}
