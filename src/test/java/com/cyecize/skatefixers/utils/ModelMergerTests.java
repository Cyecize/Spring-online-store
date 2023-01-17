package com.cyecize.skatefixers.utils;

import com.cyecize.skatefixers.util.ModelMerger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
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

    class ChildOfA extends A {
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

    @BeforeEach
    public void onBefore() {
        this.modelMerger = new ModelMerger();
    }

    @Test
    public void test_merge_SameClassesDifferentInstances_ShouldMerge() {
        A class1 = new A("a", 2, new ArrayList<>());
        A class2 = new A(null, null, null);
        this.modelMerger.merge(class1, class2);
        assertEquals(class1.getA(), class2.getA(), OBJECTS_DIFFER);
        assertEquals(class1.getB(), class2.getB(), OBJECTS_DIFFER);
        assertEquals(class1.getC(), class2.getC(), OBJECTS_DIFFER);
    }

    @Test
    public void testMerge_DifferentClasses_ShouldMerge() {
        A class1 = new A("a", 2, new ArrayList<>());
        B class2 = new B();
        this.modelMerger.merge(class1, class2);
        assertEquals(class1.getA(), class2.getA(), OBJECTS_DIFFER);
        assertEquals(class1.getB(), class2.getB(), OBJECTS_DIFFER);
    }

    @Test
    public void testMerge_DifferentClassesDifferentFieldTypes_ShouldNotPass() {
        A a = new A("2", 2, new ArrayList<>());
        MismatchClass b = new MismatchClass();
        assertThrows(RuntimeException.class, () -> this.modelMerger.merge(a, b));
    }

    @Test
    public void testMerge_DestinationSubclass_ShouldMerge() {
        A a = new A("a", 2, new ArrayList<>());
        ChildOfA a1 = new ChildOfA(null, null, null);
        this.modelMerger.merge(a, a1);
        assertEquals(a.getA(), a1.getA(), OBJECTS_DIFFER);
        assertEquals(a.getB(), a1.getB(), OBJECTS_DIFFER);
        assertEquals(a.getB(), a1.getB(), OBJECTS_DIFFER);
    }

    @Test
    public void testMerge_SourceSubclass_ShouldMerge() {
        A a = new A("a", 2, new ArrayList<>());
        ChildOfA a1 = new ChildOfA(null, null, null);
        this.modelMerger.merge(a1, a);
        assertEquals(a.getA(), a1.getA(), OBJECTS_DIFFER);
        assertEquals(a.getB(), a1.getB(), OBJECTS_DIFFER);
        assertEquals(a.getB(), a1.getB(), OBJECTS_DIFFER);
    }

    @Test
    public void testMerge_SourceNullDestinationNotNull_ShouldOverride() {
        A class1 = new A(null, null, null);
        A class2 = new A("a", 2, new ArrayList<>());
        this.modelMerger.merge(class1, class2);
        assertEquals(class1.getA(), class2.getA(), OBJECTS_DIFFER);
        assertEquals(class1.getB(), class2.getB(), OBJECTS_DIFFER);
        assertEquals(class1.getC(), class2.getC(), OBJECTS_DIFFER);
    }
}
