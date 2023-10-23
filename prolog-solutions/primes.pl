prime(N) :-
    N > 1,
    is_prime(N, 2).

is_prime(N, Divisor) :-
    Divisor > sqrt(N),
    !.
is_prime(N, Divisor) :-
    N mod Divisor =\= 0,
    NextDivisor is Divisor + 1,
    is_prime(N, NextDivisor).

composite(N) :- \+ prime(N).

find_first_div(N, Divisor, I) :- N mod I =:= 0, Divisor is I, !.
find_first_div(N, Divisor, I) :- I1 is I + 1, find_first_div(N, Divisor, I1).

prime_divisors(1, []) :- !.
prime_divisors(N, [H | T]) :- 
	find_first_div(N, Divisor, 2), 
	H is Divisor, 
	N1 is N // H, 
	prime_divisors(N1, T), !.

cube_divisors(1, []) :- !.	
cube_divisors(N, [H,H,H| T]) :-
	find_first_div(N, Divisor, 2), 
	H is Divisor, 
	N1 is N // H, 
	cube_divisors(N1, T), !.		
