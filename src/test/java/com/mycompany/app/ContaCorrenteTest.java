package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaCorrenteTest {
    private ContaCorrente conta;

    @BeforeEach
    public void setup(){
        conta = new ContaCorrente("12345", "Giovanni Masoni");
    }

    @Test
    public void shouldGetAccountNumber(){
        assertEquals("12345", conta.getNumeroConta());
        assertNotEquals("54321", conta.getNumeroConta());
    }

    @Test
    public void shouldGetAccountHolderName(){
        assertEquals("Giovanni Masoni", conta.getNomeCorrentista());
        assertNotEquals("Gabriel Tabajara", conta.getNomeCorrentista());
    }

    @Test
    public void shouldGetAccountCategory(){
        assertEquals("Silver", conta.getCategoria());
    }

    @Test
    public void shouldUpgradeAccountCategory(){
        assertEquals("Silver", conta.getCategoria());
        conta.deposito(50000);
        assertEquals("Gold", conta.getCategoria());
        conta.deposito(200000);
        assertEquals("Platinum", conta.getCategoria());
        conta.deposito(5000);
        assertEquals("Platinum", conta.getCategoria());
    }

    @Test
    public void shouldDowngradeAccountCategory(){
        conta.deposito(50000);
        conta.deposito(200000); // CurrentBalance == 252000
        conta.retirada(62000); // CurrentBalance == 190000
        assertEquals("Platinum", conta.getCategoria());
        conta.retirada(90000); // CurrentBalance == 100000
        assertEquals("Platinum", conta.getCategoria());
        conta.retirada(1); // CurrentBalance == 99999
        assertEquals("Gold", conta.getCategoria());
        conta.retirada(74999); // CurrentBalance == 25000
        assertEquals("Gold", conta.getCategoria());
        conta.retirada(1); // CurrentBalance == 24999
        assertEquals("Silver", conta.getCategoria());
        conta.retirada(20000);
        assertEquals("Silver", conta.getCategoria());
    }       

    @Test
    public void shouldNotUpgradeDirectlyFromSilverToPlatinum(){
        assertEquals("Silver", conta.getCategoria());
        conta.deposito(250000);
        assertNotEquals("Platinum", conta.getCategoria());
        assertEquals("Gold", conta.getCategoria());
        conta.deposito(1);
        assertEquals("Platinum", conta.getCategoria());
    }

    @Test
    public void shouldNotDowngradeDirectlyFromPlatinumToSilver(){
        conta.deposito(50000);
        conta.deposito(200000); // CurrentBalance == 252000
        assertEquals("Platinum", conta.getCategoria());
        conta.retirada(240000);
        assertNotEquals("Silver", conta.getCategoria());
        assertEquals("Gold", conta.getCategoria());
        conta.retirada(1);
        assertEquals("Silver", conta.getCategoria());
    }

    @Test
    public void shouldGetAccountBalance(){
        assertEquals(0, conta.getSaldo());
        conta.deposito(15);
        assertEquals(15, conta.getSaldo());
        conta.deposito(50);
        assertEquals(65, conta.getSaldo());
        conta.retirada(20);
        assertEquals(45, conta.getSaldo());
    }

    @Test
    public void shouldReturnTrueIfDepositValueIsValid(){
        assertEquals(true, conta.deposito(16));
        assertEquals(true, conta.deposito(1));
        assertEquals(false, conta.deposito(0));
        assertEquals(false, conta.deposito(-15));
    }

    @Test
    public void shouldValueDeposit(){
        conta.deposito(55000); //Account -> Gold => deposit + 1%
        conta.deposito(1000);
        assertEquals(56010, conta.getSaldo());
        conta.deposito(5000);
        assertEquals(61060, conta.getSaldo());
        conta.deposito(139000); //Account -> Platinum => deposit + 2.5%
        assertEquals(201450, conta.getSaldo());
        conta.deposito(1000);
        assertEquals(202475, conta.getSaldo());
        conta.deposito(500);
        assertEquals(202987.5, conta.getSaldo());
    }

    @Test
    public void shouldReturnTrueIfWithdrawValueIsValid(){
        conta.deposito(400);
        assertEquals(true, conta.retirada(250));
        assertEquals(true, conta.retirada(120));
        assertEquals(false, conta.retirada(300));
        assertEquals(false, conta.retirada(0));
        assertEquals(false, conta.retirada(-15));
    }

}
