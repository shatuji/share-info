package me.twocat.shareinfo.util.pojotuils;

import java.util.Random;
import java.util.stream.IntStream;

public class GeneratorUtils {
  /**
   * generator 6 digit number
   * @return
   */
  public static String generatorRandom6Digit(int len)
  {
    int[] intss = {0,1,2,3,4,5,6,7,8,9};
    StringBuffer sb= new StringBuffer();
    for(int i = 0 ; i < len ; i++ )
      sb.append(intss[new Random().nextInt(10)]);
    return sb.toString();
  }

  public static  void main(String[] args){
    int js = new Random().nextInt(10);
    for(int j = 0 ; j < 200 ;j++)
    System.out.println( "laugh --->" + generatorRandom6Digit(8));
  }
}
