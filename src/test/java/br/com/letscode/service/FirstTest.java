package br.com.letscode.service;

import org.junit.*;

public class FirstTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("iniciando classe de teste before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("after class");

    }

    @Before
    public void before() {
        System.out.println("executando o método a cada teste before");
    }

    @After
    public void after() {
        System.out.println("executando o método a cada fim de teste after");
    }

    @Test
    public void test() {
        System.out.println("iniciando teste");

    }

    @Test
    public void secondTest() {
        System.out.println("iniciando segundo teste");

    }

}
