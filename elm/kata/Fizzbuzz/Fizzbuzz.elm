module Fizzbuzz exposing (..)

import Html exposing (..)

main : Html msg

main = 
--    div [] [ text toString List.map fizzbuzz numbers ]
    div [] [ text "Hello World!" ]

fizzbuzz : Int -> String
fizzbuzz number = 
    if number % 15 == 0 then 
        "FizzBuzz"
    else 
        if number % 5 == 0 then 
            "Buzz"
        else 
            if number % 3 == 0 then
                "Fizz"
            else toString number

numbers = [ 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13 , 14 , 15 , 16 , 17 , 18 , 19 , 20 , 21 , 22 , 23 , 24 , 25 ]


