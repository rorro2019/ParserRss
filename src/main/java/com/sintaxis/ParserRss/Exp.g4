grammar Exp;

/* Este será el punto de entrada de nuestro analizador. */
eval
    :    additionExp
    ;

/* La suma y la resta tienen la precedencia más baja. */
additionExp
    :    multiplyExp
         ( '+' multiplyExp
         | '-' multiplyExp
         )*
    ;


/* La multiplicación y la división tienen una precedencia más alta. */
multiplyExp
    :    atomExp
         ( '*' atomExp
         | '/' atomExp
         )*
    ;

/* Un átomo de expresión es la parte más pequeña de una expresión: un número. O
      cuando encontramos paréntesis, estamos haciendo una llamada recursiva al
      regla 'adiciónExp'. Como puede ver, un 'atomExp' tiene la prioridad más alta.*/
atomExp
    :    Number
    |    '(' additionExp ')'
    ;

/* Un número: puede ser un valor entero o un valor decimal */
Number
    :    ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

/* Vamos a ignorar todos los espacios en blanco*/
WS
    :   (' ' | '\t' | '\r'| '\n') -> skip
    ;