#!/usr/bin/env bash
java -jar target/Tic-tac-toe-1.0-SNAPSHOT.jar
sleep 3

y='Y'
n='N'

until [ $n == $y ]
echo "Do you want to continue (Y/N) : "
read -p "Do you want to continue (Y/N) : " option
do
    if [ $option == $y ]
    then
       java -jar target/Tic-tac-toe-1.0-SNAPSHOT.jar
    elif [ $option == $n ]
    then
       echo "Thank You !!!"
       sleep 1
       break
    else
       echo "Invalid option"
    fi
done