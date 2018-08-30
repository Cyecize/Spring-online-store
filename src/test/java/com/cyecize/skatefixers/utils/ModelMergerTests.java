package com.cyecize.skatefixers.utils;

import com.cyecize.skatefixers.util.ModelMerger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ModelMergerTests {

    private static final String OBJECTS_DIFFER = "objects differ";

    class A {
        private String a;

        private Integer b;

        private List<String> c;

        public A(String a, Integer b, List<String> c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public String getA() {
            return a;
        }

        public Integer getB() {
            return b;
        }

        public List<String> getC() {
            return c;
        }
    }

    class ChildOfA extends A{
        public ChildOfA(String a, Integer b, List<String> c) {
            super(a, b, c);
        }
    }

    class B {
        private String a;

        private Integer b;

        public String getA() {
            return a;
        }

        public Integer getB() {
            return b;
        }
    }

    class MismatchClass {
        private Integer a;
        private String b;
    }

    private ModelMerger modelMerger;

    @Before
    public void onBefore() {
        this.modelMerger = new ModelMerger();
    }

    @Test
    public void test_merge_SameClassesDifferentInstances_ShouldMerge() {
        A class1 = new A("a", 2, new ArrayList<>());
        A class2 = new A(null, null, null);
        this.modelMerger.merge(class1, class2);
        Assert.assertEquals(OBJECTS_DIFFER, class1.getA(), class2.getA());
        Assert.assertEquals(OBJECTS_DIFFER, class1.getB(), class2.getB());
        Assert.assertEquals(OBJECTS_DIFFER, class1.getC(), class2.getC());
    }

    @Test
    public void testMerge_DifferentClasses_ShouldMerge() {
        A class1 = new A("a", 2, new ArrayList<>());
        B class2 = new B();
        this.modelMerger.merge(class1, class2);
        Assert.assertEquals(OBJECTS_DIFFER, class1.getA(), class2.getA());
        Assert.assertEquals(OBJECTS_DIFFER, class1.getB(), class2.getB());
    }

    @Test(expected = RuntimeException.class)
    public void testMerge_DifferentClassesDifferentFieldTypes_ShouldNotPass() {
        A a = new A("2", 2, new ArrayList<>());
        MismatchClass b = new MismatchClass();
        this.modelMerger.merge(a, b);
    }

    @Test
    public void testMerge_DestinationSubclass_ShouldMerge(){
        A a = new A("a", 2, new ArrayList<>());
        ChildOfA a1 = new ChildOfA(null, null, null);
        this.modelMerger.merge(a, a1);
        Assert.assertEquals(OBJECTS_DIFFER, a.getA(), a1.getA());
        Assert.assertEquals(OBJECTS_DIFFER, a.getB(), a1.getB());
        Assert.assertEquals(OBJECTS_DIFFER, a.getB(), a1.getB());
    }

    @Test
    public void testMerge_SourceSubclass_ShouldMerge(){
        A a = new A("a", 2, new ArrayList<>());
        ChildOfA a1 = new ChildOfA(null, null, null);
        this.modelMerger.merge(a1, a);
        Assert.assertEquals(OBJECTS_DIFFER, a.getA(), a1.getA());
        Assert.assertEquals(OBJECTS_DIFFER, a.getB(), a1.getB());
        Assert.assertEquals(OBJECTS_DIFFER, a.getB(), a1.getB());
    }

    @Test
    public void testMerge_SourceNullDestinationNotNull_ShouldOverride(){
        A class1 = new A(null, null, null);
        A class2 = new A("a", 2, new ArrayList<>());
        this.modelMerger.merge(class1, class2);
        Assert.assertEquals(OBJECTS_DIFFER, class1.getA(), class2.getA());
        Assert.assertEquals(OBJECTS_DIFFER, class1.getB(), class2.getB());
        Assert.assertEquals(OBJECTS_DIFFER, class1.getC(), class2.getC());
    }
}
