package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FuncionarioTest {

    @Test
    public void getINSSOffPoint(){
       Funcionario funcionario = new Funcionario(111111);
       assertEquals(4999.995, funcionario.getINSS());
    }

    @Test
    public void getINSSOnPoint(){
       Funcionario funcionario = new Funcionario(111112);
       assertEquals(5000, funcionario.getINSS());
    }

    @Test
    public void getINSSInPoint(){
       Funcionario funcionario = new Funcionario(50000);
       assertEquals(2250, funcionario.getINSS());
    }

    @Test
    public void getINSSOutPoint(){
       Funcionario funcionario = new Funcionario(200000);
       assertEquals(5000, funcionario.getINSS());
    }

    @Test
    public void getIRPFOffPoint(){
       Funcionario funcionario = new Funcionario(2500);
       assertEquals(0.0, funcionario.getIRPF());
    }

    @Test
    public void getIRPFOnPoint(){
       Funcionario funcionario = new Funcionario(2501);
       assertEquals(0.12, funcionario.getIRPF());
    }

    @Test
    public void getIRPFInPoint(){
       Funcionario funcionario = new Funcionario(2000);
       assertEquals(0.0, funcionario.getIRPF());
    }

    @Test
    public void getIRPFOutPoint(){
       Funcionario funcionario = new Funcionario(5000);
       assertEquals(300, funcionario.getIRPF());
    }

    //Testes adicionados após a primeira análise de cobertura de código

    @Test
    public void getSalarioBrutoTest(){
       Funcionario funcionario = new Funcionario(5000);
       assertEquals(5000, funcionario.getSalarioBruto());
    }

    @Test
    public void setSalarioBrutoValid(){
       Funcionario funcionario = new Funcionario(1);
       assertEquals(1, funcionario.getSalarioBruto());
       funcionario.setSalarioBruto(2000); 
       assertEquals(2000, funcionario.getSalarioBruto());
    }

    @Test
    public void setSalarioBrutoInvalid(){
       Funcionario funcionario = new Funcionario(1);
       try {
        funcionario.setSalarioBruto(-1);
       } catch (Exception e) {
        assertEquals(e.getMessage(), "Salario Negativo");
       }
    }

    @Test
    public void getSalarioLiquidoTest(){
       Funcionario funcionario = new Funcionario(50000);
       assertEquals(42050, funcionario.getSalarioLiquido());
    }

}
