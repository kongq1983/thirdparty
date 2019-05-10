package com.kq.javassist.wrapper;

import com.kq.javassist.exception.NoSuchPropertyException;
import com.kq.javassist.util.ClassGenerator;
import com.kq.javassist.util.ClassHelper;
import com.kq.javassist.util.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Wrapper
 * @author kq
 * @date 2019-05-10
 */
public class Wrapper {

    private static final Map<Class<?>, Wrapper> WRAPPER_MAP = new ConcurrentHashMap();

    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String[] OBJECT_METHODS = new String[]{"getClass", "hashCode", "toString", "equals"};

    private static AtomicLong WRAPPER_CLASS_COUNTER = new AtomicLong(0L);

    private static final Wrapper OBJECT_WRAPPER = new Wrapper() {

        public String[] getMethodNames() {
            return Wrapper.OBJECT_METHODS;
        }

        public String[] getDeclaredMethodNames() {
            return Wrapper.OBJECT_METHODS;
        }

        public String[] getPropertyNames() {
            return Wrapper.EMPTY_STRING_ARRAY;
        }

        public Class<?> getPropertyType(String pn) {
            return null;
        }

        public Object getPropertyValue(Object instance, String pn) throws NoSuchPropertyException {
            throw new NoSuchPropertyException("Property [" + pn + "] not found.");
        }

        public void setPropertyValue(Object instance, String pn, Object pv) throws NoSuchPropertyException {
            throw new NoSuchPropertyException("Property [" + pn + "] not found.");
        }

        public boolean hasProperty(String name) {
            return false;
        }


        public Object invokeMethod(Object instance, String mn, Class<?>[] types, Object[] args) throws NoSuchMethodException {
            if ("getClass".equals(mn)) {
                return instance.getClass();
            } else if ("hashCode".equals(mn)) {
                return instance.hashCode();
            } else if ("toString".equals(mn)) {
                return instance.toString();
            } else if ("equals".equals(mn)) {
                if (args.length == 1) {
                    return instance.equals(args[0]);
                } else {
                    throw new IllegalArgumentException("Invoke method [" + mn + "] argument number error.");
                }
            } else {
                throw new NoSuchMethodException("Method [" + mn + "] not found.");
            }
        }

    };


        public static Wrapper getWrapper(Class<?> c) {
        while(ClassGenerator.isDynamicClass(c)) {
            c = c.getSuperclass();
        }

        if (c == Object.class) {
            return OBJECT_WRAPPER;
        } else {
            Wrapper ret = (Wrapper)WRAPPER_MAP.get(c);
            if (ret == null) {
                ret = makeWrapper(c);
                WRAPPER_MAP.put(c, ret);
            }

            return ret;
        }
    }



    private static Wrapper makeWrapper(Class<?> c) {
        if (c.isPrimitive()) {
            throw new IllegalArgumentException("Can not create wrapper for primitive type: " + c);
        } else {
            String name = c.getName();
            ClassLoader cl = ClassHelper.getClassLoader(c);
            StringBuilder c1 = new StringBuilder("public void setPropertyValue(Object o, String n, Object v){ ");
            StringBuilder c2 = new StringBuilder("public Object getPropertyValue(Object o, String n){ ");
            StringBuilder c3 = new StringBuilder("public Object invokeMethod(Object o, String n, Class[] p, Object[] v) throws " + InvocationTargetException.class.getName() + "{ ");
            c1.append(name).append(" w; try{ w = ((").append(name).append(")$1); }catch(Throwable e){ throw new IllegalArgumentException(e); }");
            c2.append(name).append(" w; try{ w = ((").append(name).append(")$1); }catch(Throwable e){ throw new IllegalArgumentException(e); }");
            c3.append(name).append(" w; try{ w = ((").append(name).append(")$1); }catch(Throwable e){ throw new IllegalArgumentException(e); }");

            return null;
        }
    }

    private static String arg(Class<?> cl, String name) {
        if (cl.isPrimitive()) {
            if (cl == Boolean.TYPE) {
                return "((Boolean)" + name + ").booleanValue()";
            } else if (cl == Byte.TYPE) {
                return "((Byte)" + name + ").byteValue()";
            } else if (cl == Character.TYPE) {
                return "((Character)" + name + ").charValue()";
            } else if (cl == Double.TYPE) {
                return "((Number)" + name + ").doubleValue()";
            } else if (cl == Float.TYPE) {
                return "((Number)" + name + ").floatValue()";
            } else if (cl == Integer.TYPE) {
                return "((Number)" + name + ").intValue()";
            } else if (cl == Long.TYPE) {
                return "((Number)" + name + ").longValue()";
            } else if (cl == Short.TYPE) {
                return "((Number)" + name + ").shortValue()";
            } else {
                throw new RuntimeException("Unknown primitive type: " + cl.getName());
            }
        } else {
            return "(" + ReflectUtils.getName(cl) + ")" + name;
        }
    }

    private static String args(Class<?>[] cs, String name) {
        int len = cs.length;
        if (len == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < len; ++i) {
                if (i > 0) {
                    sb.append(',');
                }

                sb.append(arg(cs[i], name + "[" + i + "]"));
            }

            return sb.toString();
        }
    }


}
