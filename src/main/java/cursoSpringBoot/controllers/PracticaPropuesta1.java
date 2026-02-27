package cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contolador para verificar palíndromos
 */
@RestController

public class PracticaPropuesta1 {

    /**
     * EndPoint para verificar si la palabra es un palíndromo.
     * @param word la palabra a verificar.
     * @return Un mensaje indicando si la palabra es un palindromo o no.
     */
    @GetMapping("/validar-palindromo/{word}")
    public String Palindrome(@PathVariable String word){

        if (isPalindrome(word)){
            return "La palabra "+word+" es un palíndromo";
        } else {
            return "La palabra "+word+" NO es un palíndromo";
        }

    }

    /**
     * Método para verificar si la palabra es un palíndromo.
     * @param word la palabra a verificar.
     * @return true si la palabra es un palíndromo, false en caso contrario.}
     */
    private boolean isPalindrome (String word){
        int length= word.length();

        for (int i=0; i<length/2; i++){
            if (word.charAt(i)!=word.charAt(length-i-1))
                return false;
        }

        return true;
    }

}
