package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .contains("Tetrahedron")
                .startsWith("T");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(1, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .contains("object")
                .startsWith("Unk");
    }

    @Test
    void numberOfVerticesSix() {
        Box box = new Box(6, 6);
        int result = box.getNumberOfVertices();
        assertThat(result).isNotNull()
                .isPositive()
                .isLessThan(7)
                .isNotZero()
                .isEven();
    }


    @Test
    void numberOfVerticesFour() {
        Box box = new Box(4, 4);
        String result = String.valueOf(box.getNumberOfVertices());
        assertThat(result).isNotNull()
                .isNotEmpty()
                .isEqualTo(String.valueOf(4));
    }

    @Test
    void checkIsExist() {
        Box box = new Box(0, 1);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isNotNull();
    }

    @Test
    void checkIsNotExist() {
        Box box = new Box(6, 0);
        boolean result = box.isExist();
        assertThat(result).isEqualTo(false)
                .isFalse()
                .isNotNull();
    }

    @Test
    void checkGetAreaDefault() {
        Box box = new Box(1, 10);
        double res = box.getArea();
        assertThat(res).isEqualTo(0)
                .isNotNull()
                .isLessThan(1);
    }

    @Test
    void checkGetAreaSix() {
        Box box = new Box(6, 3);
        double res = box.getArea();
        assertThat(res)
                .isEqualTo(54)
                .isCloseTo(54, withPrecision(0.01d))
                .isLessThan(55);
    }
}