/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stream;

import java.util.List;

/**
 *
 * @author PhanAnh
 */
public class Stream {

    public static void main(String[] args) {
        List<Integer> so = List.of(1,2,3,4,5,6,7,8,9,10);
        
        so.stream().filter(n -> n%2 ==0).map(n -> n*2).forEach(System.out::println);
        
        Integer sum = so.stream().filter(n -> n>5).reduce(0, Integer::sum);
        
                
    }
}
