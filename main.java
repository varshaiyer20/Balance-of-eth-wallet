package com.example;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

public class Main 
{
public static void main( String[] args ) throws Exception
{
final Web3j client = Web3j.build(

new HttpService("https://mainnet.infura.io/v3/d601871f60154ab5b2880046defae7c8")

);

final String ethAddress = "0xd776B7317847613dC2c71614da49B08405106E79";
final EthGetBalance balanceResponse = client
.ethGetBalance(ethAddress, DefaultBlockParameter.valueOf("latest")).sendAsync()
.get(10, TimeUnit.SECONDS);


final BigInteger unscaledBalance = balanceResponse.getBalance();
final BigDecimal scaledBalance = new BigDecimal(unscaledBalance)
.divide(new BigDecimal(1000000000000000000L), 18, RoundingMode.HALF_UP);
System.out.println(scaledBalance);
System.out.println(unscaledBalance);

}
}
